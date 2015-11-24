package edu.gatech.wordgap.util;

import java.util.Map;

import edu.gatech.wordgap.spring.jdbc.model.VocabQuizQuestion;

public class MultiKeyMap {
	
	private VocabQuizQuestion[] questions;
	private Map<String, Object> maps;
	
	public VocabQuizQuestion[] getQuestions() {
		return questions;
	}
	public void setQuestions(VocabQuizQuestion[] questions) {
		this.questions = questions;
	}
	public Map<String, Object> getMaps() {
		return maps;
	}
	public void setMaps(Map<String, Object> maps) {
		this.maps = maps;
	}
	
}
