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
	@Autowired
	private ShopDAO shopDAO;
	@Autowired
	private ISpecService specService;
	private static final Logger logger = Logger.getLogger(AdminController.class);
	
	@RequestMapping(value="/addshop", method = {RequestMethod.GET})
	public String addShopGet(HttpServletRequest request, HttpSession session, ModelMap model) {
		Shop shop = new Shop();
		String msg = request.getParameter("msg");
		model.addAttribute("specList", specService.getSpecList());
		model.addAttribute("msg", msg);
		model.addAttribute("shopForm", shop);
		return "add_shop";
	}
	@RequestMapping(value="/addshop", method = {RequestMethod.POST})
	public String addShopPost(@ModelAttribute("shopForm") Shop shop,Map<String,Object> model,HttpSession session) {
		if (shop.getName().equals("") || shop.getSite().equals("") || shop.getSite().equals("") || shop.getSite().equals(""))
			return "redirect:addshop?msg=fail";
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
		Shop shop = shopDAO.get(new Integer(request.getParameter("id")));
		String msg = request.getParameter("msg");
		model.addAttribute("updateShopForm", shop);
		model.addAttribute("specList", specService.getSpecList());
		model.addAttribute("msg", msg);
		session.setAttribute("changeShopId", shop.getId());
		return "update_shop";
	}
	@RequestMapping(value="/updateshop", method = {RequestMethod.POST})
	public String updateShopPost(@ModelAttribute("updateShopForm") Shop shop,Map<String,Object> model,HttpSession session) {
		Integer id= (Integer)session.getAttribute("changeShopId");
		if (shop.getName().equals("") || shop.getSite().equals("") || shop.getAddress().equals("") || shop.getTelephone().equals("") )
			return "redirect:updateshop?id="+id.intValue()+"&msg=fail";
		if (!shop.getName().equals(""))
			shopDAO.updateColumn("name", shop.getName(),id.intValue());
		if (!shop.getSite().equals("") )
			shopDAO.updateColumn("site", shop.getSite(),id.intValue());
		if (!shop.getAddress().equals("") )
			shopDAO.updateColumn("address", shop.getAddress(), id.intValue());
		if (!shop.getTelephone().equals("") )
			shopDAO.updateColumn("telephone", shop.getTelephone(), id.intValue());
		shopDAO.updateColumn("spec", shop.getSpec(), id.intValue());
		shopDAO.updateColumn("lat", shop.getLat(), id.intValue());
		shopDAO.updateColumn("lng", shop.getLng(), id.intValue());
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
