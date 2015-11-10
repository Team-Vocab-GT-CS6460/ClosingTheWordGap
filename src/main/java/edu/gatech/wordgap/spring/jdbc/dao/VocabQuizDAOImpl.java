package edu.gatech.wordgap.spring.jdbc.dao;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import edu.gatech.wordgap.spring.jdbc.model.WordList;


public class VocabQuizDAOImpl implements VocabQuizDAO {

	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<WordList> getWordList() {
		String sql = "select * from wordgap.wordlist";
		Object[] args = new Object[] {};
		List<WordList> requestList  = jdbcTemplate.query(sql, args, new BeanPropertyRowMapper<WordList>(WordList.class));
		return requestList;
	}


}
