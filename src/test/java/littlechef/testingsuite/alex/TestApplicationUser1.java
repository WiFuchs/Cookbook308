package littlechef.testingsuite.alex;

import littlechef.entities.ApplicationUser;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TestApplicationUser1 {
	
	@Test
	public void testApplicationUserGetID() {
		ApplicationUser appUser1 = new ApplicationUser();
	
		assertEquals(true, ((Long)appUser1.getId() != null));
	}
	
	@Test
	public void testApplicationUserSetFName() {
		ApplicationUser appUser2 = new ApplicationUser();
		appUser2.setfName("Alex");
		
		assertEquals("Alex", appUser2.getfName());
	}
}
