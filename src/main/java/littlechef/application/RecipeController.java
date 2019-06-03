package littlechef.application;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

import littlechef.entities.Annotation;
import littlechef.entities.Ingredient;
import littlechef.entities.Instruction;
import littlechef.entities.JournalEntry;
import littlechef.entities.Recipe;
import littlechef.exceptions.AccessDeniedException;
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
	
	private static final int NUM_REQUESTS = 1;

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
	

	@PostMapping("/recipes")
	Recipe newRecipe(@AuthenticationPrincipal String user, @RequestBody Recipe newRecipe) {
		newRecipe.setUserID(users.findByUsername(user).getId());
		return repository.save(newRecipe);
	}

	// Single item

	@GetMapping("recipes/{rid}")
	Recipe one(@AuthenticationPrincipal String user, @PathVariable("rid") Long rid) {
		long id = users.findByUsername(user).getId();

		Recipe rec = repository.findByUserIDAndId(id, rid).orElseThrow(() -> new RecipeNotFoundException(rid));

		//Definitely works!
		JournalEntry journ = journals.findFirst1ByUserIDOrderByTimestampDesc(id).orElseThrow(() -> new JournalEntryNotFoundException(id));
		
		rec.setAnnotations(journ.getId());
		
		return rec;
	}

	@PutMapping("/recipes/{id}")
	Recipe replaceRecipe(@AuthenticationPrincipal String user, @RequestBody Recipe newRecipe, @PathVariable Long id) {
		long uid = users.findByUsername(user).getId();
		
		Recipe rec = repository.findById(id).orElseGet(() -> {
			newRecipe.setId(id);
			newRecipe.setUserID(uid);
			return repository.save(newRecipe);
		});
		
		if (rec.getUserID() != uid) {
			throw new AccessDeniedException();
		}
		
		rec.update(newRecipe);
		return repository.save(rec);
	}
	
	@GetMapping("/recipes/user/{id}") 
	List<Recipe> byUserId(@PathVariable Long id) {
		//Return only public recipes for other users
		return repository.findByUserID(id).stream().filter(Recipe::isPublic).collect(Collectors.toList());
		
	}

	@DeleteMapping("/recipes/{id}")
	void deleteEmployee(@AuthenticationPrincipal String user, @PathVariable Long id) {
		long uid = users.findByUsername(user).getId();
		Recipe rec = repository.findById(id).orElseThrow(() -> new RecipeNotFoundException(id));
		if (rec.getUserID() != uid) {
			throw new AccessDeniedException();
		}
		repository.deleteById(id);
	}
	
	@PostMapping("/recipes/populate")
	void populate(@AuthenticationPrincipal String user) {
		long uid = users.findByUsername(user).getId();
		
		try {
			HttpResponse<JsonNode> response = Unirest.get("https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/random?number=" + NUM_REQUESTS)
					.header("X-RapidAPI-Host", "spoonacular-recipe-food-nutrition-v1.p.rapidapi.com")
					.header("X-RapidAPI-Key", "ffd8925755msh676e8efd7c5ba06p17b57bjsn1be328b133cf")
					.asJson();
			
			JSONObject resp = response.getBody().getObject();
			
			for(int i = 0; i < NUM_REQUESTS; i++) {
				
				JSONObject json = resp.getJSONArray("recipes").getJSONObject(i);
			
				System.out.println(json.toString());
				// get ingredients
				JSONArray arr = json.getJSONArray("extendedIngredients");
				Ingredient[] ingredients = new Ingredient[arr.length()];
				for(int j = 0; j < arr.length(); j++) {
					JSONObject jthIngr = arr.getJSONObject(j);
					ingredients[j] = new Ingredient(jthIngr.getInt("amount"), jthIngr.getString("unit"), jthIngr.getString("name"));
				}
				
				// get instructions
				String instr = json.getString("instructions");
				Instruction[] instructions = parseInstr(instr);
				
				System.out.println(instr);
				//get tags
				String tags = getTags(json);
			
				Recipe newRecipe = new Recipe(json.getString("title"), json.getString("sourceUrl"), -1, json.getInt("preparationMinutes"), json.getInt("cookingMinutes"), true, ingredients, instructions, tags, json.getString("image"));
				newRecipe.setUserID(uid);
				
				repository.save(newRecipe);
					    
			}
		}
	    catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	private Instruction[] parseInstr(String instr) {
		String[] strInst = instr.split("\\.");
		Instruction[] instructions = new Instruction[strInst.length];
		for(int i = 0; i < strInst.length; i++) {
			instructions[i] = new Instruction(strInst[i]);
		}
		return instructions;
		
	}
	
	private String getTags(JSONObject json) {
		
		StringBuilder tags = new StringBuilder();
		String[] tagsArray = {
				"vegetarian",
				"vegan",
				"glutenFree",
				"dairyFree",
				"veryHealthy",
				"cheap",
				"veryPopular",
				"sustainable",
				"lowFodmap",
				"ketogenic",
				"whole30" 
			};
		for(int i = 0; i < tagsArray.length; i++) {
			if(json.getBoolean(tagsArray[i])) {
				tags.append(tagsArray[i] + "|");
			}
		}
		return tags.toString();
	}
}