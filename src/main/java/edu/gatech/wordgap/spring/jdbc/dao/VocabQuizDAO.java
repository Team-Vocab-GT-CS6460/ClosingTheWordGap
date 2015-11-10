package edu.gatech.wordgap.spring.jdbc.dao;

import java.util.List;

import edu.gatech.wordgap.spring.jdbc.model.WordList;

public interface VocabQuizDAO {
	
	public List<WordList> getWordList();

}
