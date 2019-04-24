package application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import entities.IngredientAnnotation;
import entities.JournalEntryRepository;
import entities.Recipe;
import entities.RecipeRepository;


@RestController
class RecipeController {

	private final RecipeRepository repository;
	private final JournalEntryRepository journals;

	RecipeController(RecipeRepository repository, JournalEntryRepository journals) {
		this.repository = repository;
		this.journals = journals;
	}

	// Aggregate root

	@GetMapping("/recipes")
	List<Recipe> all() {
		return repository.findAll();
	}

	@PostMapping("/recipes")
	Recipe newRecipe(@RequestBody Recipe newRecipe) {
		return repository.save(newRecipe);
	}

	// Single item

	@GetMapping("/recipes/{id}")
	Recipe one(@PathVariable Long id) {

		Recipe rec = repository.findById(id).orElseThrow(() -> new RecipeNotFoundException(id));
		
		rec.getIngredients().forEach((ing) -> ing.setAnnotations(
				ing.getAnnotations().stream().filter((a) -> a.getJournal() != null).collect(Collectors.toList())));
				//new ArrayList<IngredientAnnotation>(Arrays.asList(new IngredientAnnotation()))));
		
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
}