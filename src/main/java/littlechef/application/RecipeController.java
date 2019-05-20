package littlechef.application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.json.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mashape.unirest.http.*;
import com.mashape.unirest.http.exceptions.UnirestException;

import littlechef.entities.ApplicationUser;
import littlechef.entities.Ingredient;
import littlechef.entities.IngredientAnnotation;
import littlechef.entities.Instruction;
import littlechef.entities.JournalEntry;
import littlechef.entities.Recipe;
import littlechef.exceptions.JournalEntryNotFoundException;
import littlechef.exceptions.RecipeNotFoundException;
import littlechef.repositories.ApplicationUserRepository;
import littlechef.repositories.JournalEntryRepository;
import littlechef.repositories.RecipeRepository;



@RestController
class RecipeController {

	private final RecipeRepository repository;
	private final JournalEntryRepository journals;
	private final ApplicationUserRepository users;

	RecipeController(RecipeRepository repository, JournalEntryRepository journals, ApplicationUserRepository users) {
		this.repository = repository;
		this.journals = journals;
		this.users = users;
	}

	// Aggregate root

	@GetMapping("/recipes")
	List<Recipe> all(@AuthenticationPrincipal String user) {
		return repository.findByUserID(users.findByUsername(user).getId());
	}
	
	@GetMapping("user/{id}/recipes")
	List<Recipe> all(@AuthenticationPrincipal String user, @PathVariable Long id) {
		return repository.findByUserID(id);
	}

	@PostMapping("/recipes")
	Recipe newRecipe(@AuthenticationPrincipal String user, @RequestBody Recipe newRecipe) {
		newRecipe.setUserID(users.findByUsername(user).getId());
		return repository.save(newRecipe);
	}

	// Single item

	@GetMapping("user/{id}/recipes/{rid}")
	Recipe one(@PathVariable("id") Long id, @PathVariable("rid") Long rid) {

		Recipe rec = repository.findByUserIDAndId(id, rid).orElseThrow(() -> new RecipeNotFoundException(rid));

		JournalEntry journ = journals.findFirst1ByUserIDOrderByTimestampDesc(id).orElseThrow(() -> new JournalEntryNotFoundException(id));
		
		rec.setAnnotations(journ.getAnnotations());
		
//		rec.getIngredients().forEach((ing) -> ing.setAnnotations(
//				ing.getAnnotations().stream().filter((a) -> a.getUserID() == id).collect(Collectors.toList())));
//				//new ArrayList<IngredientAnnotation>(Arrays.asList(new IngredientAnnotation()))));
		
		return rec;
	}

	@PutMapping("/recipes/{id}")
	Recipe replaceRecipe(@RequestBody Recipe newRecipe, @PathVariable Long id) {

//		return repository.findById(id)
//			.map(recipe -> {
//				recipe.setName(newRecipe.getName());
//				recipe.setRole(newRecipe.getRole());
//				return repository.save(employee);
//			})
//			.orElseGet(() -> {
//				newEmployee.setId(id);
//				return repository.save(newEmployee);
//			});
		
		
		//TODO: implement put
		
		return repository.findById(id).orElseThrow(() -> new RecipeNotFoundException(id));
	}
	
	@GetMapping("/recipes/user/{id}") 
	List<Recipe> byUserId(@PathVariable Long id) {
		
		return repository.findByUserID(id);
		
	}

	@DeleteMapping("/recipes/{id}")
	void deleteEmployee(@PathVariable Long id) {
		repository.deleteById(id);
	}
	
	@PostMapping("/recipes/populate")
	void populate() {
		
		try {
			HttpResponse<JsonNode> response = Unirest.get("https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/random?number=1")
					.header("X-RapidAPI-Host", "spoonacular-recipe-food-nutrition-v1.p.rapidapi.com")
					.header("X-RapidAPI-Key", "ffd8925755msh676e8efd7c5ba06p17b57bjsn1be328b133cf")
					.asJson();
			
			JSONObject json = response.getBody().getObject();
					
			// get ingredients
			JSONArray arr = json.getJSONArray("extendedIngredients");
			Ingredient[] ingredients = new Ingredient[arr.length()];
			for(int i = 0; i < arr.length(); i++) {
				JSONObject ithIngr = arr.getJSONObject(i);
				ingredients[i] = new Ingredient(ithIngr.getInt("amount"), ithIngr.getString("cup"), ithIngr.getString("name"));
			}
			
			// get instructions
			String instr = json.getString("instructions");
			Instruction[] instructions = parseInstr(instr);
			
			Recipe newRecipe = new Recipe(json.getString("title"), json.getString("sourceUrl"), -1, json.getInt("preparationMinutes"), json.getInt("cookingMinutes"), true, ingredients, instructions);
			repository.save(newRecipe);
			
		}
	    catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	private Instruction[] parseInstr(String instr) {
		String[] strInst = instr.split(".");
		Instruction[] instructions = new Instruction[strInst.length];
		for(int i = 0; i < strInst.length; i++) {
			instructions[i] = new Instruction(strInst[i]);
		}
		return instructions;
		
	}
}