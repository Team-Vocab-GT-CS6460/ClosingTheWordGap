package edu.gatech.wordgap.spring.jdbc.model;

public class VocabQuizAnswer {
	private String word;
	private String definition;
	private String imagePath;
	private String audioPath;
	private String ttsString;
	
	public String getTtsString() {
		return ttsString;
	}
	public void setTtsString(String ttsString) {
		this.ttsString = ttsString;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public String getDefinition() {
		return definition;
	}
	public void setDefinition(String definition) {
		this.definition = definition;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getAudioPath() {
		return audioPath;
	}
	public void setAudioPath(String audioPath) {
		this.audioPath = audioPath;
	}	
}
