package littlechef.testingsuite.tanner;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import littlechef.entities.Instruction;
import littlechef.entities.Ingredient;
import littlechef.entities.Recipe;

public class TestRecipe2 {

	@Test
	public void testRecipeRemoveStep() {
		Recipe recipe = new Recipe();
		Instruction inst1 = new Instruction();
		Instruction inst2 = new Instruction();
		Instruction inst3 = new Instruction();
		recipe.addStep(inst1);
		recipe.addStep(inst2);
		recipe.removeStep(inst1);
		recipe.addStep(inst3);
		
		assertEquals(2, recipe.getSteps().size());
	}
	
	@Test
	public void testRecipeUpdateIngredients() {
		Recipe oldRecipe = new Recipe("Pasta", "online", 3, 10, 20, true, 
				new Ingredient[]{new Ingredient(1, "pound", "gluten-free pasta"), new Ingredient(0.5, "kilogram", "tomato sauce")}, 
				new Instruction[]{new Instruction("boil water"), new Instruction("put pasta in water"), new Instruction("add sauce")}, 
				"gluten-free");
		
		Recipe newRecipe = new Recipe("Pasta", "online", 3, 10, 20, true, 
				new Ingredient[]{new Ingredient(3, "pounds", "pasta"), new Ingredient(1.25, "ounces", "pesto"), new Ingredient(2.5, "kilograms", "sausage")}, 
				new Instruction[]{new Instruction("heat pan"), new Instruction("fry pasta on pan with sauce")}, 
				"not gluten-free");
		
		oldRecipe.update(newRecipe);
		
		assertEquals(3, oldRecipe.getIngredients().size());
	}
}
