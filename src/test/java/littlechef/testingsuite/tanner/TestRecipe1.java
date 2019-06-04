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
	public void testRecipeAddStep() {
		Recipe recipe = new Recipe();
		Instruction inst1 = new Instruction();
		Instruction inst2 = new Instruction();
		recipe.addStep(inst1);
		recipe.addStep(inst2);
		
		assertEquals(2, recipe.getSteps().size());
	}
}
