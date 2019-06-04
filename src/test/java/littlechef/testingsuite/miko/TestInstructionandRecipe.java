package littlechef.testingsuite.miko;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import littlechef.entities.Ingredient;
import littlechef.entities.Instruction;
import littlechef.entities.InstructionAnnotation;
import littlechef.entities.Recipe;

public class TestInstructionandRecipe {

	@Test
	public void testIngredientRemoveAnnotation() {
		Instruction inst1 = new Instruction();
		InstructionAnnotation ia1 = new InstructionAnnotation();
		InstructionAnnotation ia2 = new InstructionAnnotation();
		inst1.addAnnotation(ia1);
		inst1.addAnnotation(ia2);
		inst1.removeAnnotation(ia2);
		
		assertEquals(1, inst1.getAnnotations().size());
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
