package littlechef.testingsuite.miko;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestIngredientandInstruction.class, TestInstructionandRecipe.class })
public class AllTests {

}
