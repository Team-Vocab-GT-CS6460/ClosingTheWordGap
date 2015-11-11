package edu.gatech.wordgap.spring.jdbc.dao;

import java.util.List;

import edu.gatech.wordgap.spring.jdbc.model.VocabWord;

public interface VocabQuizDAO {
	
	public List<VocabWord> getWordList();

}
