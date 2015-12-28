package zelshops;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ModelMap;

import com.ipovselite.Access;
import com.ipovselite.LoginController;
import com.ipovselite.User;
import com.ipovselite.UserDAO;

import junit.framework.TestCase;
import static org.mockito.Mockito.*;

public class LoginControllerTest extends TestCase {
	LoginController loginController;
	UserDAO userDAO;
	ModelMap model;
	Map<String, Object> map;
	HttpSession session;
	HttpServletRequest request;
	@Before
	protected void setUp() {
		userDAO = mock(UserDAO.class);
		loginController = new LoginController(userDAO);
		model = new ModelMap();
		map = new HashMap<String, Object>();
		session = new FakeSession();
		request = mock(HttpServletRequest.class);
	}
	@Test
	public void testModelShouldBeEmptyUserInGet() {
		//GIVEN
		when(request.getParameter("msg")).thenReturn(null);
		//WHEN
		loginController.loginGet(request, session, model);
		//THEN
		User user1 = (User)(model.get("loginForm"));
		assertNotNull(user1);
	}
	@Test
	public void testModelShouldTakeUserFromIsValid() {
		//GIVEN
		User user = new User();
		user.setUsername("Vasiliy");
		user.setPassword("vasya1");
		User user1 = new User();
		user1.setId(1);
		user1.setUsername("Vasiliy");
		user1.setPassword("vasya1");
		user1.setAccess(Access.Admin);
		when(userDAO.isValid(user.getUsername(), user.getPassword())).thenReturn(user1);
		//WHEN
		loginController.loginPost(user, model, session);
		//THEN
		User res = (User)(session.getAttribute("currentUser"));
		assertNotSame(user, res);
		assertEquals(user1, res);
	}
	@Test
	public void testModelShouldReturnFailWhenUserIsInvalid() {
		//GIVEN
		User user = new User();
		user.setUsername("Vasiliy");
		user.setPassword("vasya1");
		when(userDAO.isValid(user.getUsername(), user.getPassword())).thenReturn(null);
		//WHEN
		String res = loginController.loginPost(user, model, session);
		//THEN
		assertEquals(res, "redirect:login?msg=fail");
	}
	@Test
	public void testModelShouldSetCurrentUserNullWhenLogout() {
		//GIVEN
		User user1 = new User();
		user1.setId(1);
		user1.setUsername("Vasiliy");
		user1.setPassword("vasya1");
		user1.setAccess(Access.Admin);
		session.setAttribute("currentUser", user1);
		//WHEN
		loginController.logoutGet(map, session);
		loginController.logoutPost(map);
		//THEN
		assertEquals(session.getAttribute("currentUser"), null);
		assertEquals(session.getAttribute("currentAccess"), null);
	}
}
