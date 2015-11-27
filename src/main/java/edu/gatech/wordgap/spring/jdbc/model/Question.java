package edu.gatech.wordgap.spring.jdbc.model;

public class Question {
	private String question;
	private String keywords;
	private String content_area;
	private String vocab;
	private int id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getKeywords() {
		return keywords;
	}
	public String getContent_area() {
		return content_area;
	}
	public String getVocab() {
		return vocab;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String[] getKeywordsSplit() {
		if(keywords != null)
			return keywords.split(",");
		return new String[0];
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String[] getContentSplit() {
		if(content_area != null)
			return content_area.split(",");
		return new String[0];
	}
	public void setContent_area(String content_area) {
		this.content_area = content_area;
	}
	public String[] getVocabSplit() {
		if(vocab != null)
			return vocab.split(",");
		return new String[0];
	}
	public void setVocab(String vocab) {
		this.vocab = vocab;
	}
	
		
}
