package edu.gatech.wordgap.spring.jdbc.dao;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import edu.gatech.wordgap.spring.jdbc.model.Question;
import edu.gatech.wordgap.spring.jdbc.model.Word;


public class VocabQuizDAOImpl implements VocabQuizDAO {

	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<Word> getWords() {
		String sql = "select * from wordgap.words";
		Object[] args = new Object[] {};
		List<Word> wordList  = jdbcTemplate.query(sql, args, new BeanPropertyRowMapper<Word>(Word.class));
		return wordList;
	}
	
	public List<Question> getQuestions() {
		String sql = "select * from wordgap.questions";
		Object[] args = new Object[] {};
		List<Question> questionList  = jdbcTemplate.query(sql, args, new BeanPropertyRowMapper<Question>(Question.class));
		return questionList;
	}

}
