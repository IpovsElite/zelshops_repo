package com.ipovselite;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class HomeController implements Controller {
	@Autowired
	private ShopDAO shopDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	private List<Shop> shopList=new ArrayList<Shop>();
	private User currentUser;
	
	public User getCurrentUser() {
		return currentUser;
	}
	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}
	private  boolean isFirstVisit=true;
	@Bean
	public boolean isFirstVisit() {
		return isFirstVisit;
	}
	public void setFirstVisit(boolean isFirstVisit) {
		this.isFirstVisit = isFirstVisit;
	}
	public List<Shop> getShopList() {
		return shopList;
	}
	public void setShopList(List<Shop> shopList) {
		this.shopList=shopList;
	}
	private static final Logger logger = Logger.getLogger(HomeController.class);
	
	@RequestMapping(value="/search",method={RequestMethod.GET})
	public String home(ModelMap model) {
		SearchParameters searchForm = new SearchParameters();
		model.addAttribute("searchForm", searchForm);
		List<String> specList = new ArrayList<String>();
		specList.add("Еда");
		specList.add("Здоровье");
		specList.add("Одежда");
		specList.add("Электроника");
		specList.add("Спорт");
		model.addAttribute("specList", specList);
		model.addAttribute("isFirstVisit",isFirstVisit);
		logger.debug("Added specList to the model.");
		if (!shopList.isEmpty()) {
			List<Shop> tempList=shopList;
			model.addAttribute("shopList", tempList);
			logger.debug("Added shopList to the model.");
			for (Shop s : tempList)
				logger.debug("Name: "+s.getName());
		}
		else {
			logger.error("shopList is empty!");
		}
		return "search_result1";
		
	}
	
	@RequestMapping(value="/search",method={RequestMethod.POST})
	public String getSearchResult(@ModelAttribute("searchForm") SearchParameters searchParam, Map<String,Object> model) {
		if (searchParam!=null) {
			shopList = shopDAO.search(searchParam);
			logger.debug("Assigned shopList:");
			for (Shop s : shopList) {
				logger.debug("Name: "+s.getName());
			}
		}
		else
			logger.error("searchParam is null!");
		isFirstVisit=false;
		return "redirect:/search";
		
	}
	@RequestMapping(value="/login",method={RequestMethod.GET})
	public String loginForm(HttpServletRequest request, ModelMap model) {
		User user = new User();
		model.addAttribute("loginForm", user);
		String msg=request.getParameter("msg");
		if (msg==null)
			msg="";
		else
			if (msg=="fail")
				msg="Неверный пользователь или пароль";
		model.addAttribute("msg",msg);
		return "login_form";
		
	}
	
	@RequestMapping(value="/login",method={RequestMethod.POST})
	public String getLoginResult(@ModelAttribute("loginForm") User user,Map<String,Object> model) {
		
		if ((currentUser=userDAO.isValid(user.getUsername(),user.getPassword()))!=null) {
			switch (currentUser.getAccess()) {
			case Admin: return "admin";
			case Moderator: return "moderator";
			default: return "search_result1";
			}
		} 
		else
			return "redirect:login?msg=fail";
	}
	
	public Class<? extends Annotation> annotationType() {
		// TODO Auto-generated method stub
		return null;
	}

	public String value() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
