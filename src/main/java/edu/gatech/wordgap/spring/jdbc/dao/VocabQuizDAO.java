package edu.gatech.wordgap.spring.jdbc.dao;

import java.util.List;

import edu.gatech.wordgap.spring.jdbc.model.Question;
import edu.gatech.wordgap.spring.jdbc.model.Word;

public interface VocabQuizDAO {
	
	public List<Word> getWords();
	
	public List<Question> getQuestions();
	
	public void recordResponse(String sid, String qid, String word, boolean correct);

}
