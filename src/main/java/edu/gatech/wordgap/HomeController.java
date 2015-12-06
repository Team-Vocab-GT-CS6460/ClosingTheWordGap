package edu.gatech.wordgap;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.gatech.wordgap.spring.jdbc.dao.ProfilesDAO;
import edu.gatech.wordgap.spring.jdbc.dao.VocabQuizDAO;
import edu.gatech.wordgap.spring.jdbc.model.Kid;
import edu.gatech.wordgap.spring.jdbc.model.Question;
import edu.gatech.wordgap.spring.jdbc.model.Score;
import edu.gatech.wordgap.spring.jdbc.model.Stat;
import edu.gatech.wordgap.util.StatHelper;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	private @Autowired ProfilesDAO profilesDAO;
	private @Autowired VocabQuizDAO quizDAO;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		model.addAttribute("newKid", new Kid());
		return "profile";
	}

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String profile(Locale locale, Model model) {
		logger.info("Welcome to Profile!");
		model.addAttribute("newKid", new Kid());
		return "profile";
	}

    @RequestMapping(value="/profile", method=RequestMethod.POST)
    public String addProfile(@ModelAttribute("newKid") Kid kid, Model model) {
		logger.info("adding kid: " + kid.getName());
        profilesDAO.addKid(kid);
		model.addAttribute("newKid", new Kid());
        return "profile";
    }

	@RequestMapping(value = "/get/profiles", method = RequestMethod.GET)
	public @ResponseBody List<Kid> getProfiles(Locale locale, Model model) {
		logger.info("getting profiles");
		List<Kid> kids = profilesDAO.getKids();
		return kids;
	}

	@RequestMapping(value = "/delete/profile", method = RequestMethod.GET)
	public String deleteProfile(Locale locale, Model model, @RequestParam("kid") String kid) {
		logger.info("deleting profile");
		int id = Integer.parseInt(kid);
		profilesDAO.removeKid(id);
		model.addAttribute("newKid", new Kid());
		return "profile";
	}

	@RequestMapping(value = "/activities", method = {RequestMethod.POST, RequestMethod.GET} )
	public String activities(Locale locale, Model model, @RequestParam(value = "kid", required = false) String kid, HttpServletRequest request) {
		logger.info("Welcome to Activities!");
		if(kid=="" || kid == null){
			kid = request.getSession().getAttribute("kid").toString();
		}
		else{
			request.getSession().setAttribute("kid",kid);
		}
		int id = Integer.parseInt(kid);
		Kid kidObj = profilesDAO.getKid(id);
		String print_lang = kidObj.getPrint_language();
		logger.info(kidObj.getName());
		model.addAttribute("kid", kidObj);
		model.addAttribute("print_lang", print_lang);
		return "home";
	}

	@RequestMapping(value = "/stats", method = RequestMethod.GET)
	public String stats(Locale locale, Model model) {
		logger.info("Welcome to Stats!");
		List<Score> scores = quizDAO.getScores();
		List<Integer> params = new ArrayList<Integer>();
		List<Question> questions = new ArrayList<Question>();
		for(Score score : scores)
			params.add(score.getQuestion_id());
		if(params.size() > 0)
			questions = quizDAO.getQuestions(params);
		List<Stat> stats = StatHelper.buildStatistics(scores, questions);
		Map<String, String> keyStats = getKeyStats(stats);
		model.addAttribute("bestCategory", keyStats.get("bestName"));
		model.addAttribute("bestCategoryEfficiency", keyStats.get("best"));
		model.addAttribute("worstCategory", keyStats.get("worstName"));
		model.addAttribute("worstCategoryEfficiency", keyStats.get("worst"));
		return "stats";
	}

	private Map<String, String> getKeyStats(List<Stat> stats) {
		int best = 0;
		int worst = 100;
		String bestName = "";
		String worstName = "";
		for(Stat stat : stats) {
			int correct = stat.getCorrect();
			int total = stat.getTotal();
			int performance = 100 * correct / total;
			if(performance > best) {
				best = performance;
				bestName = stat.getName();
			}
			if(performance < worst) {
				worst = performance;
				worstName = stat.getName();
			}
		}
		HashMap<String, String> keyStats = new HashMap<String, String>();
		if(!bestName.isEmpty()) {
			keyStats.put("bestName", bestName);
			keyStats.put("best", best + "%");
		}
		if(!worstName.isEmpty()) {
			keyStats.put("worstName", worstName);
			keyStats.put("worst", worst + "%");
		}
		return keyStats;
	}

	@RequestMapping(value = "/kid_stats", method = RequestMethod.POST)
	public String kidStats(Locale locale, Model model, @RequestParam("kid") String kid) {
		logger.info("Welcome to Kid Stats!");
		int id = Integer.parseInt(kid);
		Kid kidObj = profilesDAO.getKid(id);
		logger.info(kidObj.getName());
		model.addAttribute("kid", kidObj);
		return "kid_stats";
	}

	@RequestMapping(value = "/get/kid_stats", method = RequestMethod.GET)
	public @ResponseBody List<Stat> getKidStats(Locale locale, Model model, @RequestParam("kid") String kid) {
		logger.info("getting kid stats");
		int id = Integer.parseInt(kid);
		List<Score> scores = quizDAO.getScoresById(id);
		List<Integer> params = new ArrayList<Integer>();
		List<Question> questions = new ArrayList<Question>();
		for(Score score : scores)
			params.add(score.getQuestion_id());
		if(params.size() > 0)
			questions = quizDAO.getQuestions(params);
		List<Stat> stats = StatHelper.buildStatistics(scores, questions);
		return stats;
	}	

	@RequestMapping(value = "/save/settings", method = RequestMethod.GET)
	public String saveSettings(Locale locale, Model model, 
			@RequestParam("kid") String kid, 
			@RequestParam("speech_language") String speech_language,
			@RequestParam("print_language") String print_language,
			@RequestParam("activity") String activity,
			@RequestParam("word_relationship") String word_relationship,
			@RequestParam("word_types") String word_types,
			@RequestParam("sentence_types") String sentence_types) {
		logger.info("saving settings " + speech_language + ", " + activity + " and " + word_relationship + " for kid " + kid);
		int id = Integer.parseInt(kid);
		profilesDAO.updateKidSettings(id, speech_language, print_language, word_relationship, word_types, sentence_types, activity);
		model.addAttribute("newKid", new Kid());
		return "profile";
	}

	@RequestMapping(value = "/management", method = RequestMethod.GET)
	public String management(Locale locale, Model model) {
		logger.info("Welcome to Management!");
		return "management";
	}

	@RequestMapping(value = "/kid_mgt", method = RequestMethod.POST)
	public String kidManagement(Locale locale, Model model, @RequestParam("kid") String kid) {
		logger.info("Welcome to Kid Management!");
		int id = Integer.parseInt(kid);
		Kid kidObj = profilesDAO.getKid(id);
		logger.info(kidObj.getName());
		model.addAttribute("kid", kidObj);
		return "kid_mgt";
	}

}
