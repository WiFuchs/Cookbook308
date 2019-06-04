package littlechef.testingsuite.joy;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import littlechef.entities.ApplicationUser;

public class TestApplicationUser5 {

	@Test
	public void testApplicationUserGetUsername() {
		ApplicationUser appUser1 = new ApplicationUser();
		
		assertEquals(true, appUser1.getUsername() == null);
	}
	
	@Test
	public void testApplicationUserSetPassword() {
		ApplicationUser appUser2 = new ApplicationUser();
		appUser2.setPassword("littlechef");
		
		assertEquals("littlechef", appUser2.getPassword());
	}
}
