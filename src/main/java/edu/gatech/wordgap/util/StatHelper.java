package edu.gatech.wordgap.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.gatech.wordgap.spring.jdbc.model.Question;
import edu.gatech.wordgap.spring.jdbc.model.Score;
import edu.gatech.wordgap.spring.jdbc.model.Stat;

public class StatHelper {

	public static List<Stat> buildStatistics(List<Score> scores, List<Question> questions) {
		Map<Integer, Question> qMap = new HashMap<Integer, Question>();
		Map<String, Stat> statMap = new HashMap<String, Stat>();
		for(Question q : questions)
			qMap.put(q.getId(), q);
		for(Score s: scores)
		{
			int qid = s.getQuestion_id();
			Question q = qMap.get(qid);
			for(String k : q.getKeywordsSplit()){
				Stat stat = statMap.get(k.trim());
				if(stat == null)
				{
					stat = new Stat();
					stat.setName(k.trim());
					stat.setCorrect(0);
					stat.setTotal(0);
				}
				int correct = stat.getCorrect();
				int total = stat.getTotal();
				
				stat.setTotal(total + 1);
				if(s.getCorrect())
				{
					stat.setCorrect(correct + 1);
				}
				statMap.put(k.trim(), stat);
			}
		}
		return new ArrayList<Stat> (statMap.values());
	}
	
	public static Map<String, Stat> getWordStats(List<Score> scores)
	{
		Map<String, Stat> statMap = new HashMap<String, Stat>();
		for(Score score : scores)
		{
			Stat stat = statMap.get(score.getWord());
			if(stat == null)
			{
				stat = new Stat();
				stat.setName(score.getWord());
				stat.setCorrect(0);
				stat.setTotal(0);
			}
			if(score.getCorrect())
				stat.setCorrect(stat.getCorrect() + 1);
			stat.setTotal(stat.getTotal() + 1);
			statMap.put(stat.getName(), stat);
		}
		return statMap;
	}

}
