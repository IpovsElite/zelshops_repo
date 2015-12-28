package zelshops;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ModelMap;

import com.ipovselite.ISpecService;
import com.ipovselite.SearchController;
import com.ipovselite.SearchParameters;
import com.ipovselite.Shop;
import com.ipovselite.ShopDAO;
import com.ipovselite.SpecLocal;
import static org.mockito.Mockito.*;
import junit.framework.TestCase;

public class SearchControllerTest extends TestCase {
	
	SearchParameters searchForm;
	Boolean isFirstVisit;
	ShopDAO shopDAO;
	ISpecService specService;
	List<String> specList;
	String currentUser;
	String currentAccess;
	List<Shop> shopList;
	ModelMap model;
	HttpSession session;
	SearchController searchController;
	
	@Before
	protected void setUp() {
		searchForm = new SearchParameters();
		shopDAO = mock(ShopDAO.class);
		specService = new SpecLocal();
		specList = specService.getSpecList();
		shopList = new ArrayList<Shop>();
		model = new ModelMap();
		session = new FakeSession();
		searchController = new SearchController(shopDAO, specService);
	}
	@Test
	public void testModelShouldTakeInputFromSession() {
		//GIVEN
		Shop s = new Shop();
		s.setName("test shop");
		shopList.add(s);
		session.setAttribute("shopList",shopList);
		currentUser = "Mikhail";
		currentAccess = "Властелин";
		isFirstVisit = false;
		session.setAttribute("currentUser",currentUser);
		session.setAttribute("currentAccess",currentAccess);
		session.setAttribute("isFirstVisit",isFirstVisit);
		//WHEN
		searchController.searchGet(model, session);
		//THEN
		List<?> newList = (List<?>)(model.get("shopList"));
		List<Shop> shopList1 = new ArrayList<Shop>();
		if (newList.size()>0) {
			for (int i=0;i<newList.size();i++) {
				Object o = newList.get(i);
				if (o instanceof Shop) {
					Shop s1 = (Shop)o;
					shopList1.add(s1);
				}
			}	   
		}
		String resUser = (String)(model.get("currentUser"));
		String resAccess = (String)(model.get("currentAccess"));
		Boolean resVisit = (Boolean)(model.get("isFirstVisit"));
		assertEquals(shopList,shopList1);
		assertEquals(currentUser, resUser);
		assertEquals(currentAccess, resAccess);
		assertEquals(isFirstVisit, resVisit);
	}
	@Test
	public void testModelShouldTakeShopsFromDAO() {
		//GIVEN
		Shop s1 = new Shop();
		Shop s2 = new Shop();
		shopList.add(s1);
		shopList.add(s2);
		when(shopDAO.search(searchForm)).thenReturn(shopList);
		//WHEN
		searchController.searchPost(searchForm, model, session);
		//THEN
		List<?> newList = (List<?>)(session.getAttribute("shopList"));
		List<Shop> shopList1 = new ArrayList<Shop>();
		if (newList.size()>0) {
			for (int i=0;i<newList.size();i++) {
				Object o = newList.get(i);
				if (o instanceof Shop) {
					Shop s = (Shop)o;
					shopList1.add(s);
				}
			}	   
		}
		assertEquals(shopList, shopList1);
	}
}
