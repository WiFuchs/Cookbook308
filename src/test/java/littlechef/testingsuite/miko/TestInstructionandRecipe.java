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
	public void testRecipeUpdateSource() {
		Recipe oldRecipe = new Recipe("Pasta", "online", 3, 10, 20, true, 
				new Ingredient[]{new Ingredient(1, "pound", "gluten-free pasta"), new Ingredient(0.5, "kilogram", "tomato sauce")}, 
				new Instruction[]{new Instruction("boil water"), new Instruction("put pasta in water"), new Instruction("add sauce")}, 
				"gluten-free");
		
		Recipe newRecipe = new Recipe("Pasta", "old family recipe", 3, 10, 20, false, 
				new Ingredient[]{new Ingredient(3, "kilograms", "pasta"), new Ingredient(1.25, "ounces", "garlic pesto"), new Ingredient(2.5, "pounds", "sausage")}, 
				new Instruction[]{new Instruction("heat pan"), new Instruction("fry pasta in pan and add sauce")}, 
				"not gluten-free");
		
		oldRecipe.update(newRecipe);
		
		assertEquals(newRecipe.getSource(), oldRecipe.getSource());
	}
}
