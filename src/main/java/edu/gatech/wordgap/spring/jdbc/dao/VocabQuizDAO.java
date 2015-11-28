package edu.gatech.wordgap.spring.jdbc.dao;

import java.util.List;

import edu.gatech.wordgap.spring.jdbc.model.Question;
import edu.gatech.wordgap.spring.jdbc.model.Score;
import edu.gatech.wordgap.spring.jdbc.model.Word;

public interface VocabQuizDAO {
	
	public List<Word> getWords();
	
	public List<Question> getQuestions();
	
	public void recordResponse(int sid, int qid, String word, boolean correct);
	
	public List<Score> getScoresById(int sid);
	
	public List<Score> getScores();

	public List<Question> getQuestions(List<Integer> params);

}
