package littlechef.testingsuite.tanner;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import littlechef.entities.Ingredient;
import littlechef.entities.Instruction;
import littlechef.entities.Recipe;

public class TestRecipe1 {

	@Test
	public void testRecipeRemoveIngredient() {
		Recipe recipe = new Recipe();
		Ingredient ing1 = new Ingredient();
		Ingredient ing2 = new Ingredient();
		Ingredient ing3 = new Ingredient();
		recipe.addIngredient(ing1);
		recipe.addIngredient(ing2);
		recipe.removeIngredient(ing1);
		recipe.addIngredient(ing3);
		
		assertEquals(2, recipe.getIngredients().size());
	}
	
	@Test
	public void testRecipeUpdateIsPublic() {
		Recipe oldRecipe = new Recipe("Pasta", "online", 3, 10, 20, true, 
				new Ingredient[]{new Ingredient(1, "pound", "gluten-free pasta"), new Ingredient(0.5, "kilogram", "tomato and basil sauce")}, 
				new Instruction[]{new Instruction("boil water"), new Instruction("put pasta in water"), new Instruction("add sauce")}, 
				"gluten-free");
		
		Recipe newRecipe = new Recipe("Pasta", "online", 3, 10, 20, false, 
				new Ingredient[]{new Ingredient(3, "pounds", "pasta"), new Ingredient(1.25, "ounces", "pesto"), new Ingredient(2.5, "kilograms", "sausage")}, 
				new Instruction[]{new Instruction("heat pan"), new Instruction("fry pasta in pan and add sauce")}, 
				"not gluten-free");
		
		oldRecipe.update(newRecipe);
		
		assertEquals(newRecipe.isPublic(), oldRecipe.isPublic());
	}
}
