package edu.gatech.wordgap.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.gatech.wordgap.spring.jdbc.model.Question;

public class MultiKeyMap {
	
	private Question[] questions;
	private Map<String, List<Integer>> maps;
	
	public MultiKeyMap() {
		this.maps = new HashMap<String, List<Integer>>();
	}
	
	public Question[] getQuestions() {
		return questions;
	}
	public void setQuestions(Question[] questions) {
		this.questions = questions;
	}
	public Map<String, List<Integer>> getMaps() {
		return maps;
	}
	public void setMaps(Map<String, List<Integer>> maps) {
		this.maps = maps;
	}
	
}
