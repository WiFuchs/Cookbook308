package littlechef.testingsuite.alex;

import littlechef.entities.ApplicationUser;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TestApplicationUser2 {

	@Test
	public void testApplicationUserGetFName() {
		ApplicationUser appUser1 = new ApplicationUser();
		
		assertEquals(true, appUser1.getfName() == null);
	}
	
	@Test
	public void testApplicationUserSetLName() {
		ApplicationUser appUser2 = new ApplicationUser();
		appUser2.setlName("Pinto");
		
		assertEquals("Pinto", appUser2.getlName());
	}
}
