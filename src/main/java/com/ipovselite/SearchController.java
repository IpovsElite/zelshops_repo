package com.ipovselite;

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
	@Autowired
	private ShopDAO shopDAO;	
	@Autowired
	private ISpecService specService;
	private static final Logger logger = Logger.getLogger(SearchController.class);
	
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
		List<Shop> shopList=(List<Shop>)session.getAttribute("shopList");
		if (shopList!=null)
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
