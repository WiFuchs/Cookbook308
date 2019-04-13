package Models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Entity
class Ingredient {

	private @Id @GeneratedValue Long id;
	private int quantity;
	private String units;
	private String ingredient;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id")
	private Recipe recipe;
	
	Ingredient() {
	}

	Ingredient(int quantity, String units, String ingredient) {
		this.quantity = quantity;
		this.units = units;
		this.ingredient = ingredient;
		this.recipe = null;
	}
}