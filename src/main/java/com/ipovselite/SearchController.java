package com.ipovselite;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

@Controller
@RequestMapping("/search")
public class SearchController {
	
	private ShopDAO shopDAO;	
	private ISpecService specService;
	private static final Logger logger = Logger.getLogger(SearchController.class);
	
	@Autowired
	public SearchController(ShopDAO shopDAO, ISpecService specService) {
		this.shopDAO = shopDAO;
		this.specService = specService;
	}
	@RequestMapping(method = {RequestMethod.GET})
	public String searchGet(ModelMap model,HttpSession session) {
		boolean isFirstVisit;
		SearchParameters searchForm = (SearchParameters) session.getAttribute("searchParam");
		if (searchForm==null)
			searchForm=new SearchParameters();
		model.addAttribute("searchForm", searchForm);
		model.addAttribute("specList", specService.getSpecList());
		if (session.getAttribute("isFirstVisit")==null )
			isFirstVisit=true;
		else {
			if (session.getAttribute("isFirstVisit").equals("true"))
				isFirstVisit=true;
			else
				isFirstVisit=false;
		}
		model.addAttribute("isFirstVisit",isFirstVisit);
		model.addAttribute("currentUser",session.getAttribute("currentUser"));
		model.addAttribute("currentAccess",session.getAttribute("currentAccess"));
		logger.debug("Added specList to the model.");
		Object shopListObj=session.getAttribute("shopList");
		List<?> shopList_q=null;
		List<Shop> shopList = new ArrayList<Shop>();
		if (shopListObj instanceof List<?>) {
		   shopList_q = (List<?>) shopListObj;
		   if (shopList_q.size()>0) {
			   for (int i=0;i<shopList_q.size();i++) {
			   Object o = shopList_q.get(i);
			   if (o instanceof Shop) {
				   Shop s = (Shop)o;
				   shopList.add(s);
			   }
		   }
			   }
		}
		if (shopList!=null)
			if (!shopList.isEmpty()) {
				//List<Shop> tempList=shopList;
				model.addAttribute("shopList", shopList);
				logger.debug("Added shopList to the model.");
			}
			else {
				logger.error("shopList is empty!");
			}
		return "search_result1";		
	}
	@RequestMapping(method = {RequestMethod.POST})
	public String searchPost(@ModelAttribute("searchForm") SearchParameters searchParam, Map<String,Object> model,HttpSession session) {
		if (searchParam!=null) {
			List<Shop> shopList = shopDAO.search(searchParam);
			logger.debug("Assigned shopList:");
			for (Shop s : shopList) {
				logger.debug("Name: "+s.getName());
			}
			if (searchParam.getIsGeoEnabled()) {
				logger.debug("GEOTAGGING ON: "+searchParam.getCurrentLat()+" "+searchParam.getCurrentLng());
				shopList=Geotagging.GetNearestShops(searchParam.getCurrentLat(), searchParam.getCurrentLng(), 1, shopList);
			}
			session.setAttribute("shopList", shopList);
			session.setAttribute("searchParam", searchParam);
		}
		else
			logger.error("searchParam is null!");
		boolean isFirstVisit=false;
		session.setAttribute("isFirstVisit", isFirstVisit);
		return "redirect:/search";	
	}
}
