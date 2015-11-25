package edu.gatech.wordgap.spring.jdbc.model;

public class Kid {
	private int id;
	private String name;
	private String icon;
	private String language = "English";
	private long lastActivity;
	private String strategyEnglish = "smart";
	private String strategySpanish = "smart";

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
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public long getLastActivity() {
		return lastActivity;
	}
	public void setLastActivity(long lastActivity) {
		this.lastActivity = lastActivity;
	}
	public String getStrategyEnglish() {
		return strategyEnglish;
	}
	public void setStrategyEnglish(String strategyEnglish) {
		this.strategyEnglish = strategyEnglish;
	}
	public String getStrategySpanish() {
		return strategySpanish;
	}
	public void setStrategySpanish(String strategySpanish) {
		this.strategySpanish = strategySpanish;
	}

}
