package edu.gatech.wordgap.spring.jdbc.model;

public class Question {
	private String question;
	private String keywords;
	private String content_area;
	private String vocab;
	
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getContent_area() {
		return content_area;
	}
	public void setContent_area(String content_area) {
		this.content_area = content_area;
	}
	public String getVocab() {
		return vocab;
	}
	public void setVocab(String vocab) {
		this.vocab = vocab;
	}
	
		
}
