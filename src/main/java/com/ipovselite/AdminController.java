package com.ipovselite;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminController {

	private ShopDAO shopDAO;
	private ISpecService specService;
	private ShopValidator shopValidator;
	private static final Logger logger = Logger.getLogger(AdminController.class);
	
	@Autowired
	public AdminController(ShopDAO shopDAO, ISpecService specService, ShopValidator shopValidator) {
		this.shopDAO = shopDAO;
		this.specService = specService;
		this.shopValidator = shopValidator;
	}
	@RequestMapping(value="/addshop", method = {RequestMethod.GET})
	public String addShopGet(HttpServletRequest request, HttpSession session, ModelMap model) {
		Shop shop = (Shop)session.getAttribute("addShop");
		if (shop == null)
			shop = new Shop();
		String msg = request.getParameter("msg");
		if (msg!=null) {
			Map<String, String> map = (Map<String, String>)session.getAttribute("errors");
			model.addAttribute("errors", map);
			session.removeAttribute("errors");
			session.removeAttribute("addShop");
		}
		model.addAttribute("specList", specService.getSpecList());
		model.addAttribute("msg", msg);
		model.addAttribute("shopForm", shop);
		return "add_shop";
	}
	@RequestMapping(value="/addshop", method = {RequestMethod.POST})
	public String addShopPost(@ModelAttribute("shopForm") Shop shop,Map<String,Object> model,HttpSession session) {
		Map<String, String> errors = shopValidator.validate(shop);
		if (!errors.isEmpty()) {
			session.setAttribute("errors", errors);
			session.setAttribute("addShop", shop);
			return "redirect:addshop?msg=fail";
		}
		shopDAO.addShop(shop);
		return "redirect:/search";
	}
	@RequestMapping(value="/newshops", method = {RequestMethod.GET})
	public String newShopsGet(HttpServletRequest request, HttpSession session, ModelMap model) {	
		List<Shop> shopList=shopDAO.findByStatus(0);
		model.addAttribute("shopList0", shopList);
		return "new_shops";
	}
	@RequestMapping(value="/inactiveshops", method = {RequestMethod.GET})
	public String inactiveShopsGet(HttpServletRequest request, HttpSession session, ModelMap model) {	
		List<Shop> shopList=shopDAO.findByStatus(1);
		model.addAttribute("shopList1", shopList);
		return "inactive_shops";
	}
	@RequestMapping(value="/makeactive", method = {RequestMethod.GET})
	public String makeActive(HttpServletRequest request, HttpSession session, ModelMap model) {
		shopDAO.updateColumn("status", 3,new Integer(request.getParameter("id")));
		if (session.getAttribute("searchParam")!=null)
			session.setAttribute("shopList", shopDAO.search((SearchParameters)session.getAttribute("searchParam")));
		return "redirect:search";	
	}
	@RequestMapping(value="/makeinactive", method = {RequestMethod.GET})
	public String makeInactive(HttpServletRequest request, HttpSession session, ModelMap model) {
		shopDAO.updateColumn("status", 1,new Integer(request.getParameter("id")));
		if (session.getAttribute("searchParam")!=null)
			session.setAttribute("shopList", shopDAO.search((SearchParameters)session.getAttribute("searchParam")));
		return "redirect:search";
		
	}
	@RequestMapping(value="/deleteshop", method = {RequestMethod.GET})
	public String deleteShop(HttpServletRequest request, HttpSession session, ModelMap model) {
		shopDAO.delete(new Integer(request.getParameter("id")));
		if (session.getAttribute("searchParam")!=null)
			session.setAttribute("shopList", shopDAO.search((SearchParameters)session.getAttribute("searchParam")));
		return "redirect:search";
		
	}
	@RequestMapping(value="/checkshops", method = {RequestMethod.GET})
	public String checkShopsGet(HttpServletRequest request, HttpSession session, ModelMap model) {	
		List<Shop> shopList=shopDAO.findByStatus(2);
		model.addAttribute("shopList2", shopList);
		return "check_shops";
	}
	@RequestMapping(value="/updateshop", method = {RequestMethod.GET})
	public String updateShopGet(HttpServletRequest request, HttpSession session, ModelMap model) {
		String msg = request.getParameter("msg");
		Shop changeShop = (Shop)session.getAttribute("changeShop");
		Shop oldShop = shopDAO.get(new Integer(request.getParameter("id")));
		if (changeShop==null)
			changeShop = oldShop;
		if (msg!=null) {
			Map<String, String> map = (Map<String, String>)session.getAttribute("errors");
			model.addAttribute("errors", map);
			session.removeAttribute("errors");
			session.removeAttribute("changeShop");
		}
		changeShop.setTelephone(shopValidator.unformat(changeShop.getTelephone()));
		model.addAttribute("updateShopForm", changeShop);
		model.addAttribute("specList", specService.getSpecList());
		model.addAttribute("msg", msg);
		//session.setAttribute("changeShopId", changeShop.getId());
		session.setAttribute("oldShop", oldShop);
		return "update_shop";
	}
	@RequestMapping(value="/updateshop", method = {RequestMethod.POST})
	public String updateShopPost(@ModelAttribute("updateShopForm") Shop changeShop,Map<String,Object> model,HttpSession session) {
		//(Integer)session.getAttribute("changeShopId");
		Shop oldShop = (Shop)session.getAttribute("oldShop");
		int id = oldShop.getId();
		Map<String, String> errors = shopValidator.validate(changeShop);
		if (!errors.isEmpty()) {
			session.setAttribute("errors", errors);
			session.setAttribute("changeShop", changeShop);
			return "redirect:updateshop?id=" + id + "&msg=fail";
		}
		if (!oldShop.getName().equals(changeShop.getName()))
			shopDAO.updateColumn("name", changeShop.getName(),id);
		if (!oldShop.getSite().equals(changeShop.getSite()))
			shopDAO.updateColumn("site", changeShop.getSite(),id);
		if (!oldShop.getAddress().equals(changeShop.getAddress()))
			shopDAO.updateColumn("address", changeShop.getAddress(), id);
		if (!oldShop.getTelephone().equals(changeShop.getTelephone()))
			shopDAO.updateColumn("telephone", changeShop.getTelephone(), id);
		if (!oldShop.getSpec().equals(changeShop.getSpec()))
			shopDAO.updateColumn("spec", changeShop.getSpec(), id);
		if (oldShop.getLat()!=changeShop.getLat())
			shopDAO.updateColumn("lat", changeShop.getLat(), id);
		if (oldShop.getLng()!=changeShop.getLng())
			shopDAO.updateColumn("lng", changeShop.getLng(), id);
		if (session.getAttribute("searchParam")!=null)
			session.setAttribute("shopList", shopDAO.search((SearchParameters)session.getAttribute("searchParam")));
		return "redirect:/search";
	}
	@RequestMapping(value="/checkshop", method={RequestMethod.GET})
	public String checkShopGet(HttpServletRequest request, HttpSession session, ModelMap model) {
		Shop shop = shopDAO.get(new Integer(request.getParameter("id")).intValue());
		Comment comment = (Comment) session.getAttribute("comment");
		if (comment == null)
			comment = new Comment();
		model.addAttribute("shop",shop);
		model.addAttribute("comment",comment);
		return "check_shop";
		
	}
	@RequestMapping(value="/checkshop", method = {RequestMethod.POST})
	public String checkShopPost(@ModelAttribute("comment") Comment comment, HttpServletRequest request, HttpSession session, ModelMap model) {
		shopDAO.addComment(new Integer(request.getParameter("id")).intValue(),session.getId(), comment.getText());
		shopDAO.updateColumn("status", 2, new Integer(request.getParameter("id")).intValue());
		return "redirect:/search";
	}
	@RequestMapping(value="/watchcomments", method = {RequestMethod.GET})
	public String watchCommentsGet(HttpServletRequest request, HttpSession session, ModelMap model) {
		int shopid = new Integer(request.getParameter("id")).intValue();
		Shop shop = shopDAO.get(shopid);
		List<Comment> commentList = shopDAO.watchComments(shopid);
		logger.debug(shopid);
		for (Comment c: commentList) {
			logger.debug(c.getText());
		}
		model.addAttribute("shop",shop);
		model.addAttribute("commentList", commentList);
		return "watch_comments";
	}
}
