package edu.gatech.wordgap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.gatech.wordgap.spring.jdbc.dao.VocabQuizDAO;
import edu.gatech.wordgap.spring.jdbc.model.WordList;

@Controller
public class QuizController {
	
	private @Autowired
	VocabQuizDAO quizDAO;
	
	@RequestMapping(value = "/get/wordlist", method = RequestMethod.GET)
	public List<WordList> getWordList()
	{
		List<WordList> wordList = quizDAO.getWordList();
		return wordList;
	}
	

}
