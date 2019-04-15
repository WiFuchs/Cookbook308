package entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Data
@EqualsAndHashCode(exclude = "ingredients")
@Entity
public class Recipe {

	private @Id @GeneratedValue Long id;
	private String title;
	private String source;
	private int rating;
	private int difficulty;
	private int time;
	
	@OneToMany(mappedBy = "recipe", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<Ingredient> ingredients;
	
	public Recipe() {
		this.ingredients = new ArrayList<Ingredient>();
	}

	public Recipe(String title, String source, int difficulty, int time) {
		this.title = title;
		this.source = source;
		this.difficulty = difficulty;
		this.time = time;
		this.rating = 0;
		this.ingredients = new ArrayList<Ingredient>();
	}
	
	public Recipe(String title, String source, int difficulty, int time, Ingredient... ingredients) {
		this.title = title;
		this.source = source;
		this.difficulty = difficulty;
		this.time = time;
		this.rating = 0;
		this.ingredients = Stream.of(ingredients).collect(Collectors.toList());
		this.ingredients.forEach(ing -> ing.setRecipe(this));
	}
	
	public void addIngredient(Ingredient ing) {
		ingredients.add(ing);
		ing.setRecipe(this);
	}
	
	public void removeIngredient(Ingredient ing) {
		ingredients.remove(ing);
		ing.setRecipe(null);
	}
}