package littlechef.application;

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
import littlechef.entities.Ingredient;
import littlechef.entities.Instruction;
import littlechef.entities.Recipe;
import littlechef.exceptions.AccessDeniedException;
import littlechef.exceptions.RecipeNotFoundException;
import littlechef.repositories.ApplicationUserRepository;
import littlechef.repositories.JournalEntryRepository;
import littlechef.repositories.RecipeRepository;

@RestController
class RecipeController {

	private final RecipeRepository repository;
	private final JournalEntryRepository journals;
	private final ApplicationUserRepository users;
	
	private static final int NUM_REQUESTS = 50;

	RecipeController(RecipeRepository repository, JournalEntryRepository journals, ApplicationUserRepository users) {
		this.repository = repository;
		this.journals = journals;
		this.users = users;
	}

	// Aggregate root
	@GetMapping("/recipes")
	List<Recipe> all(@AuthenticationPrincipal String user) {
		List<Recipe> recs = repository.findByUserID(users.findByUsername(user).getId());
		recs.forEach(r -> journals.findFirst1ByRecipeOrderByTimestampDesc(r.getId())
				.ifPresent(j -> r.setAnnotations(j.getId())));
		return recs;
	}
	
	@PostMapping("/recipes")
	Recipe newRecipe(@AuthenticationPrincipal String user, @RequestBody Recipe newRecipe) {
		newRecipe.setUserID(users.findByUsername(user).getId());
		newRecipe.setUsername(user);
		return repository.save(newRecipe);
	}

	// Single item
	@GetMapping("recipes/{rid}")
	Recipe one(@AuthenticationPrincipal String user, @PathVariable("rid") Long rid) {
		long id = users.findByUsername(user).getId();
		Recipe rec = repository.findByUserIDAndId(id, rid).orElseThrow(() -> new RecipeNotFoundException(rid));
		journals.findFirst1ByRecipeOrderByTimestampDesc(rid).ifPresent(journ ->
			rec.setAnnotations(journ.getId()));
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
	
	//Return only public recipes for other users	
	@GetMapping("/recipes/user/{id}") 
	List<Recipe> byUserId(@PathVariable Long id) {
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
				
				// get ingredients
				JSONObject json = resp.getJSONArray("recipes").getJSONObject(i);
				Ingredient[] ingredients = null;
				Instruction[] instructions = null;
				if(json.has("extendedIngredients")) {
					JSONArray arr = json.getJSONArray("extendedIngredients");
					ingredients = new Ingredient[arr.length()];
					for(int j = 0; j < arr.length(); j++) {
						JSONObject jthIngr = arr.getJSONObject(j);
						ingredients[j] = new Ingredient(jthIngr.getDouble("amount"), jthIngr.getString("unit"), jthIngr.getString("name"));
					}
				}
				
				// get instructions
				if(json.has("instructions")) {
					String instr = json.getString("instructions");
					instructions = parseInstr(instr);
				}
				
				//get tags
				String tags = getTags(json);
				String title = checkTitle(json);
				String src = checkSource(json);
				int prepTime = checkPrepTime(json);
				int cookTime = checkCookTime(json);
				Recipe newRecipe = new Recipe(title, src, -1, prepTime, cookTime, true, ingredients, instructions, tags);
				newRecipe.setUserID(uid);
				newRecipe.setUsername(user);	
				repository.save(newRecipe);
			}
		}
	    catch (UnirestException e) {
			e.printStackTrace();
		} 
	}
	
	private String checkTitle(JSONObject json) {
		String title = "";
		if(json.has("title")) {
			title = json.getString("title");
		}
		return title;
	}
	
	private String checkSource(JSONObject json) {
		String source = "";
		if(json.has("sourceUrl")) {
			source = json.getString("sourceUrl");
		}
		return source;
	}
	
	private int checkPrepTime(JSONObject json) {
		int prepTime = 0;
		if(json.has("preparationMinutes")) {
			prepTime = json.getInt("preparationMinutes");
		}
		return prepTime;
	}
	
	private int checkCookTime(JSONObject json) {
		int cookTime = 0;
		if(json.has("cookingMinutes")) {
			cookTime = json.getInt("cookingMinutes");
		}
		return cookTime;
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
			if(json.has(tagsArray[i]) && json.getBoolean(tagsArray[i])) {
				tags.append(tagsArray[i] + "|");
			}
		}
		return tags.toString();
	}
}