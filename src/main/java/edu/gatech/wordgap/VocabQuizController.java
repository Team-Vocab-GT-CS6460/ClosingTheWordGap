package edu.gatech.wordgap;

import java.util.ArrayList;
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

import edu.gatech.wordgap.spring.jdbc.dao.VocabQuizDAO;
import edu.gatech.wordgap.spring.jdbc.model.Question;
import edu.gatech.wordgap.spring.jdbc.model.VocabQuizAnswer;
import edu.gatech.wordgap.spring.jdbc.model.VocabQuizQuestion;
import edu.gatech.wordgap.spring.jdbc.model.Word;
import edu.gatech.wordgap.util.MultiKeyMap;

@Controller
public class VocabQuizController {
	
	private @Autowired
	VocabQuizDAO quizDAO;
	
	private static List<Question> questions;
	private static List<Word> words;
	
	private static MultiKeyMap map;
	
	//use for local testing without database
	/*
	@RequestMapping(value = "/get/quiz", method = RequestMethod.GET)
	public @ResponseBody String getVocabQuiz()
	{
		return "[{\"question\":\"When something is ________, it is not warm or cold.\",\"correctAnswer\":2,\"answers\":[{\"word\":\"calm\",\"definition\":\"not angry or excited; still or unmoving\",\"imagePath\":\"/images/calm.png\",\"audioPath\":\"\"},{\"word\":\"cool\",\"definition\":\"having a temperature that is between warm and cold\",\"imagePath\":\"/images/cool.png\",\"audioPath\":\"\"},{\"word\":\"fog\",\"definition\":\"a cloud floating near to the ground\",\"imagePath\":\"/images/fog.png\",\"audioPath\":\"\"},{\"word\":\"cloudy\",\"definition\":\"covered with or full of clouds\",\"imagePath\":\"/images/cloudy.png\",\"audioPath\":\"\"}]},{\"question\":\"We can swim in the lake when the water is ________.\",\"correctAnswer\":4,\"answers\":[{\"word\":\"flake\",\"definition\":\"a small, thin piece of something\",\"imagePath\":\"/images/flake.png\",\"audioPath\":\"\"},{\"word\":\"fog\",\"definition\":\"a cloud floating near to the ground\",\"imagePath\":\"/images/fog.png\",\"audioPath\":\"\"},{\"word\":\"calm\",\"definition\":\"not angry or excited; still or unmoving\",\"imagePath\":\"/images/calm.png\",\"audioPath\":\"\"},{\"word\":\"calm\",\"definition\":\"not angry or excited; still or unmoving\",\"imagePath\":\"/images/calm.png\",\"audioPath\":\"\"}]},{\"question\":\"We can swim in the lake when the water is ________.\",\"correctAnswer\":1,\"answers\":[{\"word\":\"calm\",\"definition\":\"not angry or excited; still or unmoving\",\"imagePath\":\"/images/calm.png\",\"audioPath\":\"\"},{\"word\":\"cloud\",\"definition\":\"a group of water drops or pieces of ice in the air from which rain, hail, or snow may fall\",\"imagePath\":\"/images/cloud.png\",\"audioPath\":\"\"},{\"word\":\"calm\",\"definition\":\"not angry or excited; still or unmoving\",\"imagePath\":\"/images/calm.png\",\"audioPath\":\"\"},{\"word\":\"cloud\",\"definition\":\"a group of water drops or pieces of ice in the air from which rain, hail, or snow may fall\",\"imagePath\":\"/images/cloud.png\",\"audioPath\":\"\"}]},{\"question\":\"We couldn't see the Sun because the sky was ________.\",\"correctAnswer\":4,\"answers\":[{\"word\":\"cloud\",\"definition\":\"a group of water drops or pieces of ice in the air from which rain, hail, or snow may fall\",\"imagePath\":\"/images/cloud.png\",\"audioPath\":\"\"},{\"word\":\"fall\",\"definition\":\"the season between summer and winter when temperatures get cooler and days get shorter\",\"imagePath\":\"/images/fall.png\",\"audioPath\":\"\"},{\"word\":\"flake\",\"definition\":\"a small, thin piece of something\",\"imagePath\":\"/images/flake.png\",\"audioPath\":\"\"},{\"word\":\"cloudy\",\"definition\":\"covered with or full of clouds\",\"imagePath\":\"/images/cloudy.png\",\"audioPath\":\"\"}]},{\"question\":\"Please ________ out the candle.\",\"correctAnswer\":3,\"answers\":[{\"word\":\"fog\",\"definition\":\"a cloud floating near to the ground\",\"imagePath\":\"/images/fog.png\",\"audioPath\":\"\"},{\"word\":\"cloud\",\"definition\":\"a group of water drops or pieces of ice in the air from which rain, hail, or snow may fall\",\"imagePath\":\"/images/cloud.png\",\"audioPath\":\"\"},{\"word\":\"blow\",\"definition\":\"to create moving air\",\"imagePath\":\"/images/blow.png\",\"audioPath\":\"\"},{\"word\":\"flake\",\"definition\":\"a small, thin piece of something\",\"imagePath\":\"/images/flake.png\",\"audioPath\":\"\"}]},{\"question\":\"Autumn is the season before winter.\",\"correctAnswer\":1,\"answers\":[{\"word\":\"autumn\",\"definition\":\"the season after summer and before winter\",\"imagePath\":\"/images/autumn.png\",\"audioPath\":\"\"},{\"word\":\"autumn\",\"definition\":\"the season after summer and before winter\",\"imagePath\":\"/images/autumn.png\",\"audioPath\":\"\"},{\"word\":\"autumn\",\"definition\":\"the season after summer and before winter\",\"imagePath\":\"/images/autumn.png\",\"audioPath\":\"\"},{\"word\":\"cool\",\"definition\":\"having a temperature that is between warm and cold\",\"imagePath\":\"/images/cool.png\",\"audioPath\":\"\"}]},{\"question\":\"That ________ in the sky might bring rain.\",\"correctAnswer\":3,\"answers\":[{\"word\":\"blow\",\"definition\":\"to create moving air\",\"imagePath\":\"/images/blow.png\",\"audioPath\":\"\"},{\"word\":\"fall\",\"definition\":\"the season between summer and winter when temperatures get cooler and days get shorter\",\"imagePath\":\"/images/fall.png\",\"audioPath\":\"\"},{\"word\":\"cloud\",\"definition\":\"a group of water drops or pieces of ice in the air from which rain, hail, or snow may fall\",\"imagePath\":\"/images/cloud.png\",\"audioPath\":\"\"},{\"word\":\"cold\",\"definition\":\"having a low temperature\",\"imagePath\":\"/images/cold.png\",\"audioPath\":\"\"}]},{\"question\":\"The Sun rises in the ________ and sets in the west.\",\"correctAnswer\":2,\"answers\":[{\"word\":\"dew\",\"definition\":\"drops of water that form on things at night\",\"imagePath\":\"/images/dew.png\",\"audioPath\":\"\"},{\"word\":\"east\",\"definition\":\"the direction of the sunrise; the opposite of west\",\"imagePath\":\"/images/east.png\",\"audioPath\":\"\"},{\"word\":\"fog\",\"definition\":\"a cloud floating near to the ground\",\"imagePath\":\"/images/fog.png\",\"audioPath\":\"\"},{\"word\":\"calm\",\"definition\":\"not angry or excited; still or unmoving\",\"imagePath\":\"/images/calm.png\",\"audioPath\":\"\"}]},{\"question\":\"A ________ of snow landed on my tongue.\",\"correctAnswer\":3,\"answers\":[{\"word\":\"autumn\",\"definition\":\"the season after summer and before winter\",\"imagePath\":\"/images/autumn.png\",\"audioPath\":\"\"},{\"word\":\"cold\",\"definition\":\"having a low temperature\",\"imagePath\":\"/images/cold.png\",\"audioPath\":\"\"},{\"word\":\"flake\",\"definition\":\"a small, thin piece of something\",\"imagePath\":\"/images/flake.png\",\"audioPath\":\"\"},{\"word\":\"fog\",\"definition\":\"a cloud floating near to the ground\",\"imagePath\":\"/images/fog.png\",\"audioPath\":\"\"}]},{\"question\":\"The Sun rises in the ________ and sets in the west.\",\"correctAnswer\":1,\"answers\":[{\"word\":\"east\",\"definition\":\"the direction of the sunrise; the opposite of west\",\"imagePath\":\"/images/east.png\",\"audioPath\":\"\"},{\"word\":\"cold\",\"definition\":\"having a low temperature\",\"imagePath\":\"/images/cold.png\",\"audioPath\":\"\"},{\"word\":\"cool\",\"definition\":\"having a temperature that is between warm and cold\",\"imagePath\":\"/images/cool.png\",\"audioPath\":\"\"},{\"word\":\"autumn\",\"definition\":\"the season after summer and before winter\",\"imagePath\":\"/images/autumn.png\",\"audioPath\":\"\"}]}]";
	}
	*/
	@RequestMapping(value = "/put/score", method = RequestMethod.GET, produces = { MediaType.APPLICATION_OCTET_STREAM_VALUE })
	public void recordQuisResponse(@RequestParam("word") String word, @RequestParam("sid") int sid,
			@RequestParam("qid") int qid, @RequestParam("correct") boolean correct){
		quizDAO.recordResponse(sid, qid, word, correct);
	}
	
	public static MultiKeyMap getMap() {
		return map;
	}
	
	@RequestMapping(value = "/get/tts", method = RequestMethod.GET, produces = { MediaType.APPLICATION_OCTET_STREAM_VALUE })
	public HttpEntity<byte[]> getVoiceResponse(@RequestParam("text") String text, HttpServletResponse response)
	{
        //response.getOutputStream().write(audio);
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
            final String clientId = "comi8ciao1ufrdnflppyi48tpvmatodp";

            // Enter the value from the 'App Secret' field obtained at developer.att.com 
            // in your app account.
            final String clientSecret = "ekowie0gvkiiwqwfnxedjmu9wsryztcx";

            // Create a service to request an OAuth token.
            OAuthService osrvc = new OAuthService(fqdn, clientId, clientSecret);

            // Get the OAuth access token using the API scope set to TTS for 
            // the Text To Speech method of the Speech API.
            OAuthToken token = osrvc.getToken("TTS");

            // Create the service to interact with the Speech API.
            TtsService ttsService = new TtsService(fqdn, token);

            // Send the request to obtain the audio.
            audio = ttsService.sendRequest("text/plain", text, "");

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
	public @ResponseBody List<VocabQuizQuestion> getVocabQuiz()
	{
		if(map == null)
			initializeMap();
		
		List<VocabQuizQuestion> questionList = new ArrayList<VocabQuizQuestion>();
		Map<String,Boolean> questionMap = new HashMap<String,Boolean>();
		Random randomGenerator = new Random();
		
		for(int i=0;i<10;i++)
		{
			boolean repick = true;
			int wi = -1;
		
			while(repick)
			{
				wi = randomGenerator.nextInt(words.size());
				Boolean found = questionMap.get(words.get(wi).getWord());
				if(found == null)
				{
					repick=false;
					questionMap.put(words.get(wi).getWord(), new Boolean(true));
				}
			}
			
			Word word = words.get(wi);
			List<Integer> iList = map.getMaps().get(word.toString());
			
			int qi = randomGenerator.nextInt(iList.size());
			Question question = map.getQuestions()[iList.get(qi)];
			String sentence = question.getQuestion();
			
			VocabQuizQuestion vqq = new VocabQuizQuestion();
			
			vqq.setQid(question.getId());
			vqq.setQuestion(sentence.replaceAll("(?i)"+Pattern.quote(word.getWord()), "________"));
	        vqq.setTtsString(sentence.replaceAll("(?i)"+Pattern.quote(word.getWord()), "blank"));
	        
	        VocabQuizAnswer[] answers = new VocabQuizAnswer[4];
	        VocabQuizAnswer answer = buildAnswerOption(word);
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
	        		answers[j] = buildAnswerOption(words.get(indexa));
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
	
	private VocabQuizAnswer buildAnswerOption(Word word) {
		VocabQuizAnswer answer = new VocabQuizAnswer();
		answer.setWord(word.getWord());
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
