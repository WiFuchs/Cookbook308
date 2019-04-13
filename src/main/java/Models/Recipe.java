package Models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Data
@Entity
public class Recipe {

	private @Id @GeneratedValue Long id;
	private String title;
	private String source;
	private int rating;
	private int difficulty;
	private int time;
	
	@OneToMany(mappedBy = "recipe", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<Ingredient> ingredients = new ArrayList<Ingredient>();
	
	Recipe() {
	}

	Recipe(String title, String source, int difficulty, int time, List<Ingredient> ingredients) {
		this.title = title;
		this.source = source;
		this.difficulty = difficulty;
		this.time = time;
		this.rating = 0;
		
		for (Ingredient ing: ingredients) {
			this.ingredients.add(ing);
			ing.setRecipe(this);
		}
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