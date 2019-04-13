package API;

import Models.Recipe;
import Models.RecipeRepository;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
class RecipeController {

	private final RecipeRepository repository;

	RecipeController(RecipeRepository repository) {
		this.repository = repository;
	}

	// Aggregate root

	@GetMapping("/recipe")
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

		return repository.findById(id).orElseThrow(() -> new RecipeNotFoundException(id));
	}

	@PutMapping("/employees/{id}")
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

	@DeleteMapping("/employees/{id}")
	void deleteEmployee(@PathVariable Long id) {
		repository.deleteById(id);
	}
}