package littlechef.testingsuite.hannah;

import littlechef.entities.ApplicationUser;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TestApplicationUser3 {
	
	@Test
	public void testApplicationUserGetLName() {
		ApplicationUser appUser1 = new ApplicationUser();
		
		assertEquals(true, appUser1.getlName() == null);
	}
	
	@Test
	public void testApplicationUserSetEmail() {
		ApplicationUser appUser2 = new ApplicationUser();
		appUser2.setEmail("hannah@awesomesauce.com");
		
		assertEquals("hannah@awesomesauce.com", appUser2.getEmail());
	}
}
