package edu.gatech.wordgap.spring.jdbc.dao;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import edu.gatech.wordgap.spring.jdbc.model.Question;
import edu.gatech.wordgap.spring.jdbc.model.Score;
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
	
	public void recordResponse(int sid, int qid, String word, boolean correct)
	{
		String sql = "insert into wordgap.scores (student_id, question_id, word, correct, test_date) values (?,?,?,?, current_date)";
		Object[] args = new Object[] {sid, qid, word, correct};
		jdbcTemplate.update(sql, args);
	}

	@Override
	public List<Score> getScoresById(int sid) {
		String sql = "select * from wordgap.scores where student_id = ?";
		Object[] args = new Object[] {sid};
		List<Score> scoreList  = jdbcTemplate.query(sql, args, new BeanPropertyRowMapper<Score>(Score.class));
		return scoreList;
	}
	
	@Override
	public List<Score> getScores() {
		String sql = "select * from wordgap.scores";
		Object[] args = new Object[] {};
		List<Score> scoreList  = jdbcTemplate.query(sql, args, new BeanPropertyRowMapper<Score>(Score.class));
		return scoreList;
	}

	@Override
	public List<Question> getQuestions(List<Integer> params) {
		String sql = "select * from wordgap.questions where id in (";
		for(int i=0; i<params.size(); i++){
			sql += params.get(i);
			if(i<params.size()-1)
				sql += ",";
		}
		sql += ")";
		Object[] args = new Object[] {};
		List<Question> questionList  = jdbcTemplate.query(sql, args, new BeanPropertyRowMapper<Question>(Question.class));
		return questionList;
	}

}
