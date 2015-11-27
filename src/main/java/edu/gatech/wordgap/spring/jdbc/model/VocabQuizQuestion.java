package edu.gatech.wordgap.spring.jdbc.model;

public class VocabQuizQuestion {
	private int qid;
	private String question;
	private String ttsString;
	private int correctAnswer;
	private VocabQuizAnswer[] answers;
	
	public String getTtsString() {
		return ttsString;
	}
	public void setTtsString(String ttsString) {
		this.ttsString = ttsString;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public int getCorrectAnswer() {
		return correctAnswer;
	}
	public void setCorrectAnswer(int j) {
		this.correctAnswer = j;
	}
	public VocabQuizAnswer[] getAnswers() {
		return answers;
	}
	public void setAnswers(VocabQuizAnswer[] answers) {
		this.answers = answers;
	}
	public int getQid() {
		return qid;
	}
	public void setQid(int qid) {
		this.qid = qid;
	}
	
}
