package littlechef.testingsuite.miko;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import littlechef.entities.Ingredient;
import littlechef.entities.IngredientAnnotation;
import littlechef.entities.Instruction;
import littlechef.entities.InstructionAnnotation;

public class TestIngredientandInstruction {

	@Test
	public void testIngredientRemoveAnnotation() {
		Ingredient ing1 = new Ingredient();
		IngredientAnnotation ia1 = new IngredientAnnotation();
		IngredientAnnotation ia2 = new IngredientAnnotation();
		ing1.addAnnotation(ia1);
		ing1.addAnnotation(ia2);
		ing1.removeAnnotation(ia2);
		
		assertEquals(1, ing1.getAnnotations().size());
	}
	
	@Test
	public void testInstructionAddAnnotation() {
		Instruction inst1 = new Instruction();
		InstructionAnnotation ia1 = new InstructionAnnotation();
		inst1.addAnnotation(ia1);
		
		assertEquals(1, inst1.getAnnotations().size());
	}
}
