package littlechef.testingsuite.hannah;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import littlechef.entities.ApplicationUser;

public class TestApplicationUser4 {

	@Test
	public void testApplicationUserGetEmail() {
		ApplicationUser appUser1 = new ApplicationUser();
		
		assertEquals(true, appUser1.getEmail() == null);
	}
	
	@Test
	public void testApplicationUserSetUsername() {
		ApplicationUser appUser2 = new ApplicationUser();
		appUser2.setUsername("hannahk");
		
		assertEquals("hannahk", appUser2.getUsername());
	}
}
