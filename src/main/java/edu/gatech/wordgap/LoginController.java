package edu.gatech.wordgap;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
//	private @Autowired PatientDAO patientDAO;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(ModelMap model, Principal principal, @RequestParam(value = "pid", required=false) String pid) {
		if(pid != null)
			model.addAttribute("pid", pid);
		if(principal != null)
			return "profile";
		return "login";
	}

	@RequestMapping(value = "/loginError", method = RequestMethod.GET)
	public String loginError(ModelMap model) {
		model.addAttribute("error", "true");
		return "login";
	}
}