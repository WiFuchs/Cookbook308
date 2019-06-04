package littlechef.testingsuite.will;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import littlechef.entities.Ingredient;
import littlechef.entities.Instruction;
import littlechef.entities.Recipe;

public class TestRecipe3 {

	@Test
	public void testRecipeUpdateSteps() {
		Recipe oldRecipe = new Recipe("Pasta", "online", 3, 10, 20, true, 
				new Ingredient[]{new Ingredient(1, "pound", "gluten-free pasta"), new Ingredient(0.5, "pound", "tomato sauce")}, 
				new Instruction[]{new Instruction("boil water"), new Instruction("put pasta in water"), new Instruction("add sauce")}, 
				"gluten-free");
		
		Recipe newRecipe = new Recipe("Pasta", "online", 3, 10, 20, false, 
				new Ingredient[]{new Ingredient(3, "pounds", "pasta"), new Ingredient(1.25, "pounds", "pesto"), new Ingredient(2.5, "pounds", "sausage")}, 
				new Instruction[]{new Instruction("heat pan"), new Instruction("fry pasta on pan with sauce")}, 
				"not gluten-free");
		
		oldRecipe.update(newRecipe);
		
		assertEquals(2, oldRecipe.getSteps().size());
	}
	
	@Test
	public void testRecipeUpdateIsPublic() {
		Recipe oldRecipe = new Recipe("Pasta", "online", 3, 10, 20, true, 
				new Ingredient[]{new Ingredient(1, "pound", "gluten-free pasta"), new Ingredient(0.5, "pound", "tomato sauce")}, 
				new Instruction[]{new Instruction("boil water"), new Instruction("put pasta in water"), new Instruction("add sauce")}, 
				"gluten-free");
		
		Recipe newRecipe = new Recipe("Pasta", "online", 3, 10, 20, false, 
				new Ingredient[]{new Ingredient(3, "pounds", "pasta"), new Ingredient(1.25, "pounds", "pesto"), new Ingredient(2.5, "pounds", "sausage")}, 
				new Instruction[]{new Instruction("heat pan"), new Instruction("fry pasta on pan with sauce")}, 
				"not gluten-free");
		
		oldRecipe.update(newRecipe);
		
		assertEquals(newRecipe.isPublic(), oldRecipe.isPublic());
	}
}
