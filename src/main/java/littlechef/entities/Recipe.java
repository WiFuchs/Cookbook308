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
	private String tags;
	//TODO add tags
	
	private long userID;
	private String username;
	
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
		this.username = "";
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
			@JsonProperty("steps") Instruction[] steps,
			@JsonProperty("tags") String tags) {
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
		this.tags = tags;
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
//		this.ingredients.setAnnotations(
//				annotations.stream()
//				.filter(a -> a instanceof IngredientAnnotation)
//				.collect(Collectors.toList()));
//		this.steps.setAnnotations(
//				annotations.stream()
//				.filter(a -> a instanceof InstructionAnnotation)
//				.collect(Collectors.toList()));
	}
	
	public void update(Recipe rec) {
		this.title = rec.title;
		this.cookTime = rec.cookTime;
		this.difficulty = rec.difficulty;
		this.isPublic = rec.isPublic;
		this.prepTime = rec.prepTime;
		this.rating = rec.rating;
		this.source = rec.source;
		
		
//		this.steps.forEach(stp -> {
//			if(!rec.steps.stream().map(s->s.getStep()).collect(Collectors.toList()).contains(stp.getStep())) {
//				this.removeStep(stp);
//			}
//		});
		
		for (int i = this.ingredients.size()-1; i >= 0; i--) {
			removeIngredient(this.ingredients.get(i));
		}
		for (Ingredient newIng : rec.ingredients) {
			this.addIngredient(newIng);
		}
		for (int i = this.steps.size()-1; i >= 0; i--) {
			removeStep(this.steps.get(i));
		}
		for (Instruction newStp : rec.steps) {
			this.addStep(newStp);
		}
	}
}