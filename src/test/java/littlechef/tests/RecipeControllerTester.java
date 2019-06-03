package littlechef.tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;


import littlechef.entities.Ingredient;
import littlechef.entities.Instruction;
import littlechef.entities.Recipe;
import littlechef.repositories.RecipeRepository;

import java.util.Arrays;
 
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContext.class, WebApplicationContext.class})
@WebAppConfiguration
public class RecipeControllerTester {
 
    private MockMvc mockMvc;
 
    private RecipeRepository recRepo;
    
    private Recipe testRec1;
 
    //Add WebApplicationContext field here.
 
    @Before
    public void setUp() throws Exception {
    	// create first test recipe
    	Ingredient[] ingreds = new Ingredient[1];
    	ingreds[0] = new Ingredient(2, "Cups", "Hot Mess");
    	
    	Instruction[] instrs = new Instruction[1];
    	instrs[0] = new Instruction("Pour into nearest cauldron");
    	
        testRec1 = new Recipe("Yummyness", "www.UrMom.com", 5, 10, 10, true, ingreds, instrs, "Vegan");
        testRec1.setUserID(1);
        
    }
 
    @Test
    public void findAll_Recipe() throws Exception {


        when(recRepo.findByUserID(1)).thenReturn(Arrays.asList(testRec1));
 
        mockMvc.perform(get("/recipes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].title", is("Yummyness")))
                .andExpect(jsonPath("$[0].cookTime", is(10)))
                .andExpect(jsonPath("$[0].prepTime", is(10))) 
                .andExpect(jsonPath("$[0].difficulty", is(5)))
                .andExpect(jsonPath("$[0].ingredients[0].quantity", is(2)))
                .andExpect(jsonPath("$[0].ingredients[0].unit", is("Cups")))
                .andExpect(jsonPath("$[0].ingredients[0].ingredient", is("Hot Mess")))
                .andExpect(jsonPath("$[0].isPublic", is(true)))
                .andExpect(jsonPath("$[0].rating", is(0)))
                .andExpect(jsonPath("$[0].source", is("www.UrMom.com")))
                .andExpect(jsonPath("$[0].steps[0].step", is("Pour into nearest cauldron")));
 
        verify(recRepo, times(1)).findByUserID(1);
        verifyNoMoreInteractions(recRepo);
    }
}
