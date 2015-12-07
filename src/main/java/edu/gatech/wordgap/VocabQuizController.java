package edu.gatech.wordgap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.att.api.oauth.OAuthService;
import com.att.api.oauth.OAuthToken;
import com.att.api.speech.service.TtsService;

import edu.gatech.wordgap.spring.jdbc.dao.ProfilesDAO;
import edu.gatech.wordgap.spring.jdbc.dao.VocabQuizDAO;
import edu.gatech.wordgap.spring.jdbc.model.Kid;
import edu.gatech.wordgap.spring.jdbc.model.Question;
import edu.gatech.wordgap.spring.jdbc.model.Score;
import edu.gatech.wordgap.spring.jdbc.model.Stat;
import edu.gatech.wordgap.spring.jdbc.model.VocabQuizAnswer;
import edu.gatech.wordgap.spring.jdbc.model.VocabQuizQuestion;
import edu.gatech.wordgap.spring.jdbc.model.Word;
import edu.gatech.wordgap.util.MultiKeyMap;
import edu.gatech.wordgap.util.StatHelper;

@Controller
public class VocabQuizController {
	
	private @Autowired
	VocabQuizDAO quizDAO;
	
	private @Autowired
	ProfilesDAO profilesDAO;
	
	private static List<Question> questions;
	private static List<Word> words;
	
	private static MultiKeyMap map;
	
	@RequestMapping(value = "/put/score", method = RequestMethod.GET, produces = { MediaType.APPLICATION_OCTET_STREAM_VALUE })
	public void recordQuizResponse(@RequestParam("word") String word, @RequestParam("sid") int sid,
			@RequestParam("qid") int qid, @RequestParam("correct") boolean correct){
		quizDAO.recordResponse(sid, qid, word, correct);
	}
	
	public static MultiKeyMap getMap() {
		return map;
	}
	
	@RequestMapping(value = "/get/tts", method = RequestMethod.GET, produces = { MediaType.APPLICATION_OCTET_STREAM_VALUE })
	public HttpEntity<byte[]> getVoiceResponse(@RequestParam("text") String text, @RequestParam("sid") int sid, HttpServletResponse response)
	{
		Kid kid = profilesDAO.getKid(sid);
		String lang = kid.getSpeech_language();
		String xarg = "";
		if(lang != null && lang.equalsIgnoreCase("spanish"))
		{
			lang = "es-us";
			xarg = "voice=rosa";
		}
			
		else
			lang = "en-us";			
		
        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("audio", "vnd.wav"));
        
        byte[] audio = null;
        
		try {
            // Use the app account settings from developer.att.com for the following
            // values. Make sure that the API scope is set to TTS to access the 
            // Text To Speech method of the Speech API before retrieving the 
            // App Key and App Secret.
            
            final String fqdn = "https://api.att.com";

            // Enter the value from the 'App Key' field obtained at developer.att.com 
            // in your app account.
            final String clientId = "2cnvefbgnb77vpfj6dgrltneskaublsm";

            // Enter the value from the 'App Secret' field obtained at developer.att.com 
            // in your app account.
            final String clientSecret = "fvdh7u136mdo29c0prrg76022sk7niwz";

            // Create a service to request an OAuth token.
            OAuthService osrvc = new OAuthService(fqdn, clientId, clientSecret);
                        
            // Get the OAuth access token using the API scope set to TTS for 
            // the Text To Speech method of the Speech API.
            OAuthToken token = osrvc.getToken("TTS");

            // Create the service to interact with the Speech API.
            TtsService ttsService = new TtsService(fqdn, token);

            // Send the request to obtain the audio.
            audio = ttsService.sendRequest("text/plain", text, xarg, lang);
            

            // Print the following message. The call has succeeded, otherwise 
            // an exception would be thrown.
            System.out.println("Successfully got audio file!");
            
            header.setContentLength(audio.length);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return new HttpEntity<byte[]>(audio, header);
	}
	
	@RequestMapping(value = "/get/quiz", method = RequestMethod.GET)
	public @ResponseBody List<VocabQuizQuestion> getVocabQuiz(@RequestParam("sid") int sid)
	{
		Kid kid = profilesDAO.getKid(sid);
		String print_lang = kid.getPrint_language();
		String speech_lang = kid.getSpeech_language();
		
		if(map == null)
			initializeMap();
		
		List<VocabQuizQuestion> questionList = new ArrayList<VocabQuizQuestion>();
		Map<String,Boolean> questionMap = new HashMap<String,Boolean>();
		Random randomGenerator = new Random();

		String activity = kid.getActivity();
		String sentenceType = kid.getSentence_types();
		String relationship = kid.getWord_relationship();
		String wordType = kid.getWord_types();
		
		List<Score> scores = quizDAO.getScoresById(kid.getId());
		List<Integer> params = new ArrayList<Integer>();
		List<Question> questions = new ArrayList<Question>();
		for(Score score : scores)
			params.add(score.getQuestion_id());
		if(params.size() > 0)
			questions = quizDAO.getQuestions(params);
		List<Stat> stats = StatHelper.buildStatistics(scores, questions);
		
		Map<String, Double> statMap = new HashMap<String, Double>();
		for(Stat stat : stats)
		{
			Double grade = (double) stat.getCorrect()/ (double) stat.getTotal();
			statMap.put(stat.getName(), grade);
		}
		
		if(activity == null || activity.equalsIgnoreCase("smart"))
			activity = getActivity(statMap);
		if(sentenceType == null || sentenceType.equalsIgnoreCase("smart"))
			sentenceType = getSentenceType(statMap);
		if(relationship == null || relationship.equalsIgnoreCase("smart"))
			relationship = getRelationship(statMap);
		if(wordType == null || wordType.equalsIgnoreCase("smart"))
			wordType = getWordType(statMap);
		
		Map<String, Stat> wordStats = StatHelper.getWordStats(scores);
		
		
		System.out.println("wordtype: " + wordType + "activity: " + activity + "relationship: " + relationship + "sentenceType: " + sentenceType);
		
		for(int i=0;i<10;i++)
		{			
						
			boolean repick = true;
			int pick_attempt = 0;
			int wi = -1;
		
			while(repick)
			{
				wi = randomGenerator.nextInt(words.size());
				Boolean found = questionMap.get(words.get(wi).getWord());
				if(found == null)
				{
					Stat stat = wordStats.get(words.get(wi));
					double score = 0.0;
					if(stat != null)
						score = (double) stat.getCorrect() / (double) stat.getTotal();
					if(stat == null || stat.getTotal() < 4 || score < 0.75 || pick_attempt >= 5)
					{
						repick=false;
						pick_attempt=0;
						questionMap.put(words.get(wi).getWord(), new Boolean(true));
					}
					else
						pick_attempt++;
				}
			}
			
			Word word = words.get(wi);
			List<Integer> iList = map.getMaps().get(word.toString());
			List<Integer> nList = new ArrayList<Integer>();
			int n = 0;
			
			for(Integer index : iList)
			{
				List<Integer> aList = map.getMaps().get(activity);
				List<Integer> wList = map.getMaps().get(wordType);
				List<Integer> rList = map.getMaps().get(relationship);
				List<Integer> sList = map.getMaps().get(sentenceType);
				
				if(aList != null && aList.contains(index))
					n++;
				if(wList != null && wList.contains(index))
					n++;
				if(rList != null && rList.contains(index))
					n++;
				if(sList != null && sList.contains(index))
					n++;
				
				for(int j=0; j<(2^n); j++)
					nList.add(index);
			}
			
			int ni = randomGenerator.nextInt(nList.size());
			int qi = nList.get(ni);
			Question question = map.getQuestions()[qi];
			
			String sentence_es = question.getQuestion_es();
			String sentence = question.getQuestion();
			
			VocabQuizQuestion vqq = new VocabQuizQuestion();
			vqq.setQid(question.getId());
			
			if(print_lang != null && print_lang.equalsIgnoreCase("spanish"))
			{
				vqq.setQuestion(sentence_es.replaceAll("(?i)"+Pattern.quote(word.getWord_es()), "________"));
			}
			else
			{
				vqq.setQuestion(sentence.replaceAll("(?i)"+Pattern.quote(word.getWord()), "________"));
			}
			
			if(speech_lang != null && speech_lang.equalsIgnoreCase("spanish"))
			{
				vqq.setTtsString(sentence_es.replaceAll("(?i)"+Pattern.quote(word.getWord_es()), "blanco"));
			}
			else
			{
				vqq.setTtsString(sentence.replaceAll("(?i)"+Pattern.quote(word.getWord()), "blank"));
			}
	        
	        VocabQuizAnswer[] answers = new VocabQuizAnswer[4];
	        VocabQuizAnswer answer = buildAnswerOption(word, print_lang, speech_lang);
	        answers[new Random().nextInt(answers.length)] = answer;
	        Map<String,Boolean> answerMap = new HashMap<String,Boolean>();
	        answerMap.put(word.getWord(), new Boolean(true));
	        
	        for(int j=0; j<4; j++)
	        {
	        	
	        	if(answers[j] == null)
	        	{
	        		boolean repicka = true;
	    			int indexa = -1;
	    			
	    			while(repicka)
	    			{
	    				indexa = randomGenerator.nextInt(words.size());
	    				Boolean found = answerMap.get(words.get(indexa).getWord());
	    				if(found == null)
	    				{
	    					repicka=false;
	    					answerMap.put(words.get(indexa).getWord(), new Boolean(true));
	    				}
	    			}
	        		answers[j] = buildAnswerOption(words.get(indexa),print_lang, speech_lang);
	        	}
	        	else
	        	{
	        		vqq.setCorrectAnswer(j+1);
	        	}
	        }
	        vqq.setAnswers(answers);
	        questionList.add(vqq);
		
		}
		return questionList;
		
	}
	
	private String getWordType(Map<String, Double> statMap) {
		String[] types = {"actions", "adjectives", "directional", "positional"};
		List<Double> values = new ArrayList<Double>();
		for(String type : types)
		{
			if(statMap.get(type) == null)
				values.add(0.0);
			else
				values.add(statMap.get(type));
		}
		for(int i=0; i<values.size(); i++)
		{
			if(values.get(i) == Collections.min(values))
				return types[i];
		}
		return types[0];
	}

	private String getRelationship(Map<String, Double> statMap) {
		String[] types = {"synonyms", "antonyms"};
		List<Double> values = new ArrayList<Double>();
		for(String type : types)
		{
			if(statMap.get(type) == null)
				values.add(0.0);
			else
				values.add(statMap.get(type));
		}
		for(int i=0; i<values.size(); i++)
		{
			if(values.get(i) == Collections.min(values))
				return types[i];
		}
		return types[0];
	}

	private String getSentenceType(Map<String, Double> statMap) {
		String[] types = {"definitions", "comparisons", "causes", "sequencing"};
		List<Double> values = new ArrayList<Double>();
		for(String type : types)
		{
			if(statMap.get(type) == null)
				values.add(0.0);
			else
				values.add(statMap.get(type));
		}
		for(int i=0; i<values.size(); i++)
		{
			if(values.get(i) == Collections.min(values))
				return types[i];
		}
		return types[0];
	}

	private String getActivity(Map<String, Double> statMap) {
		//sentences, analogies, definitions
		String[] types = {"sentences", "analogies", "definitions"};
		List<Double> values = new ArrayList<Double>();
		for(String type : types)
		{
			if(statMap.get(type) == null)
				values.add(0.0);
			else
				values.add(statMap.get(type));
		}
		for(int i=0; i<values.size(); i++)
		{
			if(values.get(i) == Collections.min(values))
				return types[i];
		}
		return types[0];
	}

	private VocabQuizAnswer buildAnswerOption(Word word, String print_lang, String speech_lang) {
		VocabQuizAnswer answer = new VocabQuizAnswer();
		if(print_lang != null && print_lang.equalsIgnoreCase("spanish"))
			answer.setWord(word.getWord_es());
		else
			answer.setWord(word.getWord());
		if(speech_lang != null && speech_lang.equalsIgnoreCase("spanish"))
			answer.setTtsString(word.getWord_es());
		else
			answer.setTtsString(word.getWord());
		if(print_lang != null && print_lang.equalsIgnoreCase("spanish"))
			answer.setDefinition(word.getDefinition_es());
		else 
			answer.setDefinition(word.getDefinition());
		answer.setImagePath("/images/" + word.getWord() + ".png");
		answer.setAudioPath("");
		return answer;
	}
	
	private void upsertKey(String key, int index) {
		List<Integer> m = (List<Integer>) map.getMaps().get(key);
		if(m != null)
			m.add(index);
		else
		{
			m = new ArrayList<Integer>();
			m.add(index);
			map.getMaps().put(key, m);
		}
	}
	
	private void initializeMap()
	{
		questions = quizDAO.getQuestions();
		words = quizDAO.getWords();
		
		map = new MultiKeyMap();
		
		map.setQuestions((Question[]) questions.toArray(new Question[questions.size()]));
		
		for(int i=0; i<map.getQuestions().length; i++)
		{
			Question question = map.getQuestions()[i];
			String[] content = question.getContentSplit();
			String[] keywords = question.getKeywordsSplit();
			String[] vocab = question.getVocabSplit();
			for(String c: content)
			{
				upsertKey(c, i);
			}
			for(String k: keywords)
			{
				upsertKey(k, i);
			}
			for(String v: vocab)
			{
				upsertKey(v, i);
			}
		}
	}
	
}
