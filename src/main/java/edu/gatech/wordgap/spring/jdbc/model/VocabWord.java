package edu.gatech.wordgap.spring.jdbc.model;

public class VocabWord {
	  private String word;
	  private String sentence1;
	  private String sentence2;
	  private String sentence3;
	  private String part_speech;
	  private String definition;
	  private String fill_blank;
	  private String analogy;
	  
	public String getAnalogy() {
		return analogy;
	}
	public void setAnalogy(String analogy) {
		this.analogy = analogy;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public String getSentence1() {
		return sentence1;
	}
	public void setSentence1(String sentence1) {
		this.sentence1 = sentence1;
	}
	public String getSentence2() {
		return sentence2;
	}
	public void setSentence2(String sentence2) {
		this.sentence2 = sentence2;
	}
	public String getSentence3() {
		return sentence3;
	}
	public void setSentence3(String sentence3) {
		this.sentence3 = sentence3;
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
	public String getFill_blank() {
		return fill_blank;
	}
	public void setFill_blank(String fill_blank) {
		this.fill_blank = fill_blank;
	}
	
}
