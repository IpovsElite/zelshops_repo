package com.ipovselite;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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
	private List<Shop> shopList=new ArrayList<Shop>();
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
	public Class<? extends Annotation> annotationType() {
		// TODO Auto-generated method stub
		return null;
	}

	public String value() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
