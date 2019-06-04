package littlechef.testingsuite.joy;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import littlechef.entities.ApplicationUser;
import littlechef.entities.Ingredient;
import littlechef.entities.IngredientAnnotation;

public class TestApplicationUserAndIngredient {
	
	@Test
	public void testApplicationUserGetPassword() {
		ApplicationUser appUser1 = new ApplicationUser();
		
		assertEquals(true, appUser1.getPassword() == null);
	}
	
	@Test
	public void testIngredientAddAnnotation() {
		Ingredient ing1 = new Ingredient();
		IngredientAnnotation ia1 = new IngredientAnnotation();
		ing1.addAnnotation(ia1);
		
		assertEquals(1, ing1.getAnnotations().size());
	}
}
