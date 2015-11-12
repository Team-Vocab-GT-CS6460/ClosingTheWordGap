package edu.gatech.wordgap.spring.jdbc.model;


public class Kid {
	private String name;
	private String language = "English";
	private int points;
	private long lastActivity;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public long getLastActivity() {
		return lastActivity;
	}
	public void setLastActivity(long lastActivity) {
		this.lastActivity = lastActivity;
	}

}
