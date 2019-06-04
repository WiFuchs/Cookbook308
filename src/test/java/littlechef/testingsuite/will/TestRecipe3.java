package littlechef.testingsuite.will;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import littlechef.entities.Ingredient;
import littlechef.entities.Instruction;
import littlechef.entities.Recipe;

public class TestRecipe3 {

	@Test
	public void testRecipeUpdateSteps() {
		Recipe[] recipes = {new Recipe("Pasta", "online", 3, 10, 20, true, 
				new Ingredient[]{new Ingredient(1, "pound", "gluten-free pasta"), new Ingredient(0.5, "kilogram", "tomato sauce")}, 
				new Instruction[]{new Instruction("boil water"), new Instruction("put pasta in water"), new Instruction("add sauce")}, 
				"gluten-free"), 
				new Recipe("Pasta", "old family recipe", 3, 10, 20, false, 
						new Ingredient[]{new Ingredient(3, "kilograms", "pasta"), new Ingredient(1.25, "ounces", "garlic pesto"), new Ingredient(2.5, "pounds", "sausage")}, 
						new Instruction[]{new Instruction("heat pan"), new Instruction("fry pasta in pan and add sauce")}, 
						"not gluten-free"),
				new Recipe()};
		
		recipes[0].update(recipes[1]);
		
		assertEquals(2, recipes[0].getSteps().size());
	}
	
	@Test
	public void testRecipeAddStep() {
		Recipe recipe = new Recipe();
		Instruction inst1 = new Instruction();
		Instruction inst2 = new Instruction();
		recipe.addStep(inst1);
		recipe.addStep(inst2);
		
		assertEquals(2, recipe.getSteps().size());
	}
}
