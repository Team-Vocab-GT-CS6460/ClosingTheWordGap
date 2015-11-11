package edu.gatech.wordgap;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.gatech.wordgap.spring.jdbc.dao.VocabQuizDAO;
import edu.gatech.wordgap.spring.jdbc.model.VocabQuizAnswer;
import edu.gatech.wordgap.spring.jdbc.model.VocabQuizQuestion;
import edu.gatech.wordgap.spring.jdbc.model.VocabWord;

@Controller
public class VocabQuizController {
	
	private @Autowired
	VocabQuizDAO quizDAO;
	
	@RequestMapping(value = "/get/quiz", method = RequestMethod.GET)
	public @ResponseBody List<VocabQuizQuestion> getVocabQuiz()
	{
		Random randomGenerator = new Random();
	    
		List<VocabWord> wordList = quizDAO.getWordList();
		for(VocabWord word: wordList)
		{
			word.setFill_blank(word.getFill_blank().replace(word.getWord(), "________"));
		}
		List<VocabQuizQuestion> questionList = new ArrayList<VocabQuizQuestion>();
		
		for(int i=0; i<10; i++)
		{
			int index = randomGenerator.nextInt(wordList.size());
			VocabWord word = wordList.get(index);
	        word.setFill_blank(word.getFill_blank().replace(word.getWord(), "________"));
	        VocabQuizQuestion question = new VocabQuizQuestion();
	        question.setQuestion(word.getFill_blank());
	        VocabQuizAnswer[] answers = new VocabQuizAnswer[4];
	        VocabQuizAnswer answer = buildAnswerOption(word);
	        answers[new Random().nextInt(answers.length)] = answer;
	        
	        for(int j=0; j<4; j++)
	        {
	        	if(answers[j] == null)
	        	{
	        		index = randomGenerator.nextInt(wordList.size());
	        		answers[j] = buildAnswerOption(wordList.get(index));
	        	}
	        	else
	        	{
	        		question.setCorrectAnswer(j+1);
	        	}
	        }
	        question.setAnswers(answers);
	        questionList.add(question);
		}
		return questionList;
	}

	private VocabQuizAnswer buildAnswerOption(VocabWord word) {
		VocabQuizAnswer answer = new VocabQuizAnswer();
		answer.setWord(word.getWord());
		answer.setDefinition(word.getDefinition());
		answer.setImagePath("/images/" + word.getWord() + ".png");
		answer.setAudioPath("");
		return answer;
	}
	

}
