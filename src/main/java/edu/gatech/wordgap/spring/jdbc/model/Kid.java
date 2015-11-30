package edu.gatech.wordgap.spring.jdbc.model;

public class Kid {
	private int id;
	private String name;
	private String icon;
	private long lastActivity;
	private String print_language = "english";
	private String speech_language = "english";
	private String activity;
	private String word_relationship;
	private String word_types;
	private String sentence_types;
	
	/* 
		id = some integer
		name = their name
		icon = their icon
		print_language = english, spanish
		speech_language = english, spanish
		activity = sentences, definitions, analogies
		word_relationships = antonyms, synonyms
		word_types = actions, adjectives, directional, positional
		sentence_types = definitions, comparisons, causes, sequencing
		
			***Everything but languages should default to null
	 */

	public String getPrint_language() {
		return print_language;
	}
	public String getWord_relationship() {
		return word_relationship;
	}
	public void setWord_relationship(String word_relationship) {
		this.word_relationship = word_relationship;
	}
	public String getWord_types() {
		return word_types;
	}
	public void setWord_types(String word_types) {
		this.word_types = word_types;
	}
	public String getSentence_types() {
		return sentence_types;
	}
	public void setSentence_types(String sentence_types) {
		this.sentence_types = sentence_types;
	}
	public void setPrint_language(String print_language) {
		this.print_language = print_language;
	}
	public String getSpeech_language() {
		return speech_language;
	}
	public void setSpeech_language(String speech_language) {
		this.speech_language = speech_language;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public long getLastActivity() {
		return lastActivity;
	}
	public void setLastActivity(long lastActivity) {
		this.lastActivity = lastActivity;
	}
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}

}
