package zelshops;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.ipovselite.Shop;
import com.ipovselite.ShopValidator;

import junit.framework.TestCase;

public class ShopValidatorTest extends TestCase {
	ShopValidator shopValidator;
	Map<String, String> errors;
	@Before
	protected void setUp() {
		shopValidator = new ShopValidator();
		errors = new HashMap<String, String>();
	}
	@Test
	public void testModelShouldDenyNotFormatedUrl() {
		//GIVEN
		Shop s = new Shop();
		s.setSite("lalala");
		String errMsg = "Неверный формат URL.";
		String resMsg = "";
		//WHEN
		errors = shopValidator.validate(s);
		//THEN
		if (errors != null)
		if (!errors.isEmpty()) {
			resMsg = errors.get("site");
			assertEquals(resMsg, errMsg);
		}
		else
			System.out.println("IN ShopValidatorTest (1): errors is empty.");
		
	}
	@Test
	public void testModelShouldAcceptFormatedUrl() {
		//GIVEN
		Shop s = new Shop();
		s.setSite("yandex.com");
		//WHEN
		errors = shopValidator.validate(s);
		//THEN
		if (errors != null)
			assertEquals(errors.get("site"), null);
		
	}
	@Test
	public void testModelShouldAcceptFormattedTelephone() {
		//GIVEN
		List<Shop> shops = new ArrayList<Shop>();
		List<String> errs = new ArrayList<String>();
		String[] numbers = {"1234567897",
				"111 444 44 4 4",
				};
		for (String s: numbers) {
			Shop sh = new Shop();
			sh.setTelephone(s);
			shops.add(sh);
		}
		//WHEN
		for (int i = 0;i < shops.size();i++) {
			errors = shopValidator.validate(shops.get(i));
			System.out.println("Telephone #" + i + ": " + shops.get(i).getTelephone());
			errs.add(errors.get("telephone") + i);
		}
		//THEN
		String[] results = new String[shops.size()];
		for (int i = 0;i<shops.size();i++)
			results[i] = shops.get(i).getTelephone();
		
		assertEquals(results[0], "+7(123)4567897");
		assertEquals(results[1], "+7(111)4444444");
	}
	@Test
	public void testModelShouldDenyNotFormattedTelephone() {
		//GIVEN
		List<Shop> shops = new ArrayList<Shop>();
		List<String> errs = new ArrayList<String>();
		String[] numbers = {"12345678974311111",
				"111 444 44 4 4 3 4 2 1111111",
				"+7(495)444 44 44",
				"au12345891",
				"333"
				};
		for (String s: numbers) {
			Shop sh = new Shop();
			sh.setTelephone(s);
			shops.add(sh);
		}
		//WHEN
		for (int i = 0;i < shops.size();i++) {
			errors = shopValidator.validate(shops.get(i));
			System.out.println("Telephone #" + i + ": " + shops.get(i).getTelephone());
			String errBuf = errors.get("telephone");
			if (errBuf != null)
				errs.add(errors.get("telephone") + i);
		}
		//THEN
		String[] results = new String[shops.size()];
		for (int i = 0;i<shops.size();i++)
			results[i] = shops.get(i).getTelephone();
		for (String s: errs) {
			System.out.println(s);
		}
		assertEquals(errs.size(), numbers.length);	
	}
}
