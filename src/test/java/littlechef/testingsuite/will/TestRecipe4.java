package littlechef.testingsuite.will;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import littlechef.entities.Ingredient;
import littlechef.entities.Instruction;
import littlechef.entities.Recipe;

public class TestRecipe4 {

	@Test
	public void testRecipeUpdateTags() {
		Recipe[] recipes = {new Recipe(), new Recipe(), new Recipe(),
				new Recipe("Pasta", "online", 3, 10, 20, true, 
				new Ingredient[]{new Ingredient(1, "pound", "gluten-free pasta"), new Ingredient(0.5, "kilogram", "tomato sauce")}, 
				new Instruction[]{new Instruction("boil water"), new Instruction("put pasta in water"), new Instruction("add sauce")}, 
				"gluten-free"), 
				new Recipe("Pasta", "old family recipe", 3, 10, 20, false, 
						new Ingredient[]{new Ingredient(3, "kilograms", "pasta"), new Ingredient(1.25, "ounces", "garlic pesto"), new Ingredient(2.5, "pounds", "sausage")}, 
						new Instruction[]{new Instruction("heat pan"), new Instruction("fry pasta in pan and add sauce")}, 
						"not gluten-free")};
		
		recipes[3].update(recipes[4]);
		
		assertEquals(false, recipes[3].getTags().contentEquals(recipes[4].getTags()));
	}
	
	@Test
	public void testRecipeAddIngredient() {
		Recipe recipe = new Recipe();
		Ingredient ing1 = new Ingredient();
		Ingredient ing2 = new Ingredient();
		recipe.addIngredient(ing1);
		recipe.addIngredient(ing2);
		
		assertEquals(2, recipe.getIngredients().size());
	}

}
