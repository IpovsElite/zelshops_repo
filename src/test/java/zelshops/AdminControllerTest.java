package zelshops;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;

import com.ipovselite.AdminController;
import com.ipovselite.ISpecService;
import com.ipovselite.Shop;
import com.ipovselite.ShopDAO;
import com.ipovselite.ShopValidator;
import com.ipovselite.SpecLocal;

import junit.framework.TestCase;
import static org.mockito.Mockito.*;

public class AdminControllerTest extends TestCase {
	AdminController adminController;
	ShopDAO shopDAO;
	ISpecService specService;
	Map<String, Object> model;
	Map<String, String> errors;
	ShopValidator shopValidator;
	HttpSession session;
	
	@Before
	protected void setUp() {
		shopDAO = mock(ShopDAO.class);
		specService = new SpecLocal();
		shopValidator = new ShopValidator();
		adminController = new AdminController(shopDAO, specService, shopValidator);
		model = new HashMap<String, Object>();
		errors = new HashMap<String, String>();
		session = new FakeSession();
	}
	@Test
	public void testModelShouldDenyEmptyFields() {
		//GIVEN
		Shop shop = new Shop();
		shop.setTelephone("4957777777");
		
		//WHEN
		adminController.addShopPost(shop, model, session);
		//THEN
		errors = (HashMap<String, String>)model.get("errors");
		assertEquals(errors.get("name"), "Поле не заполнено.");
		assertEquals(errors.get("site"), "Поле не заполнено.");
		assertEquals(errors.get("telephone"), null);
	}
	@Test
	public void testModelShouldAcceptGoodFields() {
		//GIVEN
		Shop shop = new Shop();
		shop.setTelephone("495 777 77 77");
		shop.setName("magaz");
		shop.setAddress("CoolStreet");
		shop.setSite("xyz.ru");
		
		//WHEN
		adminController.addShopPost(shop, model, session);
		
		//THEN
		errors = (HashMap<String, String>)model.get("errors");
		assertEquals(errors, null);
	}
	@Test
	public void testModelShouldDenyBadFields() {
		//GIVEN
		Shop shop = new Shop();
		shop.setTelephone("495");
		shop.setName("magaz");
		shop.setAddress("CoolStreet");
		shop.setSite("xyz .ru");
		
		//WHEN
		adminController.addShopPost(shop, model, session);
		
		//THEN
		errors = (HashMap<String, String>)model.get("errors");
		assertNotNull(errors);
		assertEquals(errors.get("name"), null);
		assertEquals(errors.get("address"), null);
		assertEquals(errors.get("site"), "Неверный формат URL.");
		assertEquals(errors.get("telephone"), "Неверный формат номера телефона.");
	}
}
