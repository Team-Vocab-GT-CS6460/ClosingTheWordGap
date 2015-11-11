package edu.gatech.wordgap.spring.jdbc.model;

public class VocabQuizQuestion {
	private String question;
	private int correctAnswer;
	private VocabQuizAnswer[] answers;
	
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
	
}
