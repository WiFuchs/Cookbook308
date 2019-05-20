package littlechef.entities;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/*
 * Unused imports, here for reference or future use
 * 
 * import lombok.EqualsAndHashCode;
 * import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
 */


@Data
@Entity
public class Recipe {

	@Id
	@GeneratedValue
	private Long id;
	
	private String title;
	private String source;
	private int rating;
	private int difficulty;
	private int prepTime;
	private int cookTime;
	private boolean isPublic;
	
	private long userID;
	
	@OneToMany(mappedBy = "recipe", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<Ingredient> ingredients;
	
	@OneToMany(mappedBy = "recipe", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<Instruction> steps;
	
	public Recipe() {
		this.ingredients = new ArrayList<>();
		this.steps = new ArrayList<>();
	}

	public Recipe(String title, String source, int difficulty, int prepTime, int cookTime, boolean isPublic) {
		this.title = title;
		this.source = source;
		this.difficulty = difficulty;
		this.prepTime = prepTime;
		this.cookTime = cookTime;
		this.rating = 0;
		this.isPublic = isPublic;
		this.ingredients = new ArrayList<>();
		this.steps = new ArrayList<>();
	}
	
	@JsonCreator
	public Recipe(
			@JsonProperty("title") String title, 
			@JsonProperty("source") String source, 
			@JsonProperty("difficulty") int difficulty, 
			@JsonProperty("prepTime") int prepTime, 
			@JsonProperty("cookTime") int cookTime,
			@JsonProperty("isPublic") boolean isPublic, 
			@JsonProperty("ingredients") Ingredient[] ingredients, 
			@JsonProperty("steps") Instruction[] steps) {
		this.title = title;
		this.source = source;
		this.difficulty = difficulty;
		this.prepTime = prepTime;
		this.cookTime = cookTime;
		this.rating = 0;
		this.isPublic = isPublic;
		this.ingredients = Stream.of(ingredients).collect(Collectors.toList());
		this.ingredients.forEach(ing -> ing.setRecipe(this));
		this.steps = Stream.of(steps).collect(Collectors.toList());
		this.steps.forEach(step -> step.setRecipe(this));
		this.userID = 0;
	}
	
	public void addIngredient(Ingredient ing) {
		ingredients.add(ing);
		ing.setRecipe(this);
	}
	
	public void removeIngredient(Ingredient ing) {
		ingredients.remove(ing);
		ing.setRecipe(null);
	}
	
	public void addStep(Instruction step) {
		steps.add(step);
		step.setRecipe(this);
	}
	
	public void removeStep(Instruction step) {
		steps.remove(step);
		step.setRecipe(null);
	}
	
	public void setAnnotations(List<Annotation> annotations) {
	/*
	 * Commented out code
	 * 
	 * 	this.ingredients.setAnnotations(
	 *			annotations.stream()
	 * 			.filter(a -> a instanceof IngredientAnnotation)
	 *			.collect(Collectors.toList()));
	 *	this.steps.setAnnotations(
	 * 			annotations.stream()
	 *			.filter(a -> a instanceof InstructionAnnotation)
	 *			.collect(Collectors.toList()));
	 */
	}
}