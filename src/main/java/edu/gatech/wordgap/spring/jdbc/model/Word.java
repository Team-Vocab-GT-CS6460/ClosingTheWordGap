package edu.gatech.wordgap.spring.jdbc.model;

public class Word {
	private String word;
	private String part_speech;
	private String definition;
	private String content_area;
	private String word_es;
	private String definition_es;
	
	public String getDefinition_es() {
		return definition_es;
	}
	public void setDefinition_es(String definition_es) {
		this.definition_es = definition_es;
	}
	public String getWord_es() {
		return word_es;
	}
	public void setWord_es(String word_es) {
		this.word_es = word_es;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public String getPart_speech() {
		return part_speech;
	}
	public void setPart_speech(String part_speech) {
		this.part_speech = part_speech;
	}
	public String getDefinition() {
		return definition;
	}
	public void setDefinition(String definition) {
		this.definition = definition;
	}
	public String getContent_area() {
		return content_area;
	}
	public void setContent_area(String content_area) {
		this.content_area = content_area;
	}
	@Override
	public String toString() {
		return word;
	}
}
