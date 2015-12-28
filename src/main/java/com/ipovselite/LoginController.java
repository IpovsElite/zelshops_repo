package com.ipovselite;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
	
	private UserDAO userDAO;
	
	@Autowired
	public LoginController(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	@RequestMapping(value="/login", method = {RequestMethod.GET})
	public String loginGet(HttpServletRequest request, HttpSession session, ModelMap model) {
		User user = new User();
		model.addAttribute("loginForm", user);
		String msg=request.getParameter("msg");
		model.addAttribute("msg",msg);
		return "login_form";	
	}
	@RequestMapping(value="/login", method = {RequestMethod.POST})
	public String loginPost(@ModelAttribute("loginForm") User user,Map<String,Object> model,HttpSession session) {
		User currentUser=null;
		String currentAccess=null;
		if ((currentUser=userDAO.isValid(user.getUsername(),user.getPassword()))!=null) {
			switch (currentUser.getAccess()) {
			case Admin: currentAccess="Администратор";break;
			case Moderator: currentAccess="Модератор";break;
			default: break;
			}
			session.setAttribute("currentUser", currentUser);
			session.setAttribute("currentAccess", currentAccess);
			return "redirect:search";
		}
		else
			return "redirect:login?msg=fail";
	}
	
	@RequestMapping(value="/logout", method = {RequestMethod.GET})
	public String logoutGet(Map<String,Object> model,HttpSession session) {	
		User currentUser=null;
		session.setAttribute("currentUser", currentUser);
		session.setAttribute("currentAccess", null);
		return "redirect:/search";
	}
	@RequestMapping(value="/logout", method = {RequestMethod.POST})
	public String logoutPost(Map<String,Object> model) {
		return "redirect:/search";
	}
}
