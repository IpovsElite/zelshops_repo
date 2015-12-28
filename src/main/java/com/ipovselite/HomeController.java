package com.ipovselite;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class HomeController {

	@RequestMapping(method = {RequestMethod.GET})
	public String home() {
		return "redirect:/search";
	}
	@RequestMapping(value="/currentloc", method = {RequestMethod.GET})
	public String currentLocGet(HttpServletRequest request, HttpSession session, ModelMap model) {
		return "current_loc";	
	}	
}
