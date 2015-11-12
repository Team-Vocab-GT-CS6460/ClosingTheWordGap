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
	
	//use for local testing without database
	/*
	@RequestMapping(value = "/get/quiz", method = RequestMethod.GET)
	public @ResponseBody String getVocabQuiz()
	{
		return "[{\"question\":\"When something is ________, it is not warm or cold.\",\"correctAnswer\":2,\"answers\":[{\"word\":\"calm\",\"definition\":\"not angry or excited; still or unmoving\",\"imagePath\":\"/images/calm.png\",\"audioPath\":\"\"},{\"word\":\"cool\",\"definition\":\"having a temperature that is between warm and cold\",\"imagePath\":\"/images/cool.png\",\"audioPath\":\"\"},{\"word\":\"fog\",\"definition\":\"a cloud floating near to the ground\",\"imagePath\":\"/images/fog.png\",\"audioPath\":\"\"},{\"word\":\"cloudy\",\"definition\":\"covered with or full of clouds\",\"imagePath\":\"/images/cloudy.png\",\"audioPath\":\"\"}]},{\"question\":\"We can swim in the lake when the water is ________.\",\"correctAnswer\":4,\"answers\":[{\"word\":\"flake\",\"definition\":\"a small, thin piece of something\",\"imagePath\":\"/images/flake.png\",\"audioPath\":\"\"},{\"word\":\"fog\",\"definition\":\"a cloud floating near to the ground\",\"imagePath\":\"/images/fog.png\",\"audioPath\":\"\"},{\"word\":\"calm\",\"definition\":\"not angry or excited; still or unmoving\",\"imagePath\":\"/images/calm.png\",\"audioPath\":\"\"},{\"word\":\"calm\",\"definition\":\"not angry or excited; still or unmoving\",\"imagePath\":\"/images/calm.png\",\"audioPath\":\"\"}]},{\"question\":\"We can swim in the lake when the water is ________.\",\"correctAnswer\":1,\"answers\":[{\"word\":\"calm\",\"definition\":\"not angry or excited; still or unmoving\",\"imagePath\":\"/images/calm.png\",\"audioPath\":\"\"},{\"word\":\"cloud\",\"definition\":\"a group of water drops or pieces of ice in the air from which rain, hail, or snow may fall\",\"imagePath\":\"/images/cloud.png\",\"audioPath\":\"\"},{\"word\":\"calm\",\"definition\":\"not angry or excited; still or unmoving\",\"imagePath\":\"/images/calm.png\",\"audioPath\":\"\"},{\"word\":\"cloud\",\"definition\":\"a group of water drops or pieces of ice in the air from which rain, hail, or snow may fall\",\"imagePath\":\"/images/cloud.png\",\"audioPath\":\"\"}]},{\"question\":\"We couldn't see the Sun because the sky was ________.\",\"correctAnswer\":4,\"answers\":[{\"word\":\"cloud\",\"definition\":\"a group of water drops or pieces of ice in the air from which rain, hail, or snow may fall\",\"imagePath\":\"/images/cloud.png\",\"audioPath\":\"\"},{\"word\":\"fall\",\"definition\":\"the season between summer and winter when temperatures get cooler and days get shorter\",\"imagePath\":\"/images/fall.png\",\"audioPath\":\"\"},{\"word\":\"flake\",\"definition\":\"a small, thin piece of something\",\"imagePath\":\"/images/flake.png\",\"audioPath\":\"\"},{\"word\":\"cloudy\",\"definition\":\"covered with or full of clouds\",\"imagePath\":\"/images/cloudy.png\",\"audioPath\":\"\"}]},{\"question\":\"Please ________ out the candle.\",\"correctAnswer\":3,\"answers\":[{\"word\":\"fog\",\"definition\":\"a cloud floating near to the ground\",\"imagePath\":\"/images/fog.png\",\"audioPath\":\"\"},{\"word\":\"cloud\",\"definition\":\"a group of water drops or pieces of ice in the air from which rain, hail, or snow may fall\",\"imagePath\":\"/images/cloud.png\",\"audioPath\":\"\"},{\"word\":\"blow\",\"definition\":\"to create moving air\",\"imagePath\":\"/images/blow.png\",\"audioPath\":\"\"},{\"word\":\"flake\",\"definition\":\"a small, thin piece of something\",\"imagePath\":\"/images/flake.png\",\"audioPath\":\"\"}]},{\"question\":\"Autumn is the season before winter.\",\"correctAnswer\":1,\"answers\":[{\"word\":\"autumn\",\"definition\":\"the season after summer and before winter\",\"imagePath\":\"/images/autumn.png\",\"audioPath\":\"\"},{\"word\":\"autumn\",\"definition\":\"the season after summer and before winter\",\"imagePath\":\"/images/autumn.png\",\"audioPath\":\"\"},{\"word\":\"autumn\",\"definition\":\"the season after summer and before winter\",\"imagePath\":\"/images/autumn.png\",\"audioPath\":\"\"},{\"word\":\"cool\",\"definition\":\"having a temperature that is between warm and cold\",\"imagePath\":\"/images/cool.png\",\"audioPath\":\"\"}]},{\"question\":\"That ________ in the sky might bring rain.\",\"correctAnswer\":3,\"answers\":[{\"word\":\"blow\",\"definition\":\"to create moving air\",\"imagePath\":\"/images/blow.png\",\"audioPath\":\"\"},{\"word\":\"fall\",\"definition\":\"the season between summer and winter when temperatures get cooler and days get shorter\",\"imagePath\":\"/images/fall.png\",\"audioPath\":\"\"},{\"word\":\"cloud\",\"definition\":\"a group of water drops or pieces of ice in the air from which rain, hail, or snow may fall\",\"imagePath\":\"/images/cloud.png\",\"audioPath\":\"\"},{\"word\":\"cold\",\"definition\":\"having a low temperature\",\"imagePath\":\"/images/cold.png\",\"audioPath\":\"\"}]},{\"question\":\"The Sun rises in the ________ and sets in the west.\",\"correctAnswer\":2,\"answers\":[{\"word\":\"dew\",\"definition\":\"drops of water that form on things at night\",\"imagePath\":\"/images/dew.png\",\"audioPath\":\"\"},{\"word\":\"east\",\"definition\":\"the direction of the sunrise; the opposite of west\",\"imagePath\":\"/images/east.png\",\"audioPath\":\"\"},{\"word\":\"fog\",\"definition\":\"a cloud floating near to the ground\",\"imagePath\":\"/images/fog.png\",\"audioPath\":\"\"},{\"word\":\"calm\",\"definition\":\"not angry or excited; still or unmoving\",\"imagePath\":\"/images/calm.png\",\"audioPath\":\"\"}]},{\"question\":\"A ________ of snow landed on my tongue.\",\"correctAnswer\":3,\"answers\":[{\"word\":\"autumn\",\"definition\":\"the season after summer and before winter\",\"imagePath\":\"/images/autumn.png\",\"audioPath\":\"\"},{\"word\":\"cold\",\"definition\":\"having a low temperature\",\"imagePath\":\"/images/cold.png\",\"audioPath\":\"\"},{\"word\":\"flake\",\"definition\":\"a small, thin piece of something\",\"imagePath\":\"/images/flake.png\",\"audioPath\":\"\"},{\"word\":\"fog\",\"definition\":\"a cloud floating near to the ground\",\"imagePath\":\"/images/fog.png\",\"audioPath\":\"\"}]},{\"question\":\"The Sun rises in the ________ and sets in the west.\",\"correctAnswer\":1,\"answers\":[{\"word\":\"east\",\"definition\":\"the direction of the sunrise; the opposite of west\",\"imagePath\":\"/images/east.png\",\"audioPath\":\"\"},{\"word\":\"cold\",\"definition\":\"having a low temperature\",\"imagePath\":\"/images/cold.png\",\"audioPath\":\"\"},{\"word\":\"cool\",\"definition\":\"having a temperature that is between warm and cold\",\"imagePath\":\"/images/cool.png\",\"audioPath\":\"\"},{\"word\":\"autumn\",\"definition\":\"the season after summer and before winter\",\"imagePath\":\"/images/autumn.png\",\"audioPath\":\"\"}]}]";
	}
	*/
	
	//use for testing with db
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
