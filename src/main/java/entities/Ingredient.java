package entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@Entity
public class Ingredient {

	private @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) Long id;
	private int quantity;
	private String units;
	private String ingredient;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    @JsonIgnoreProperties("ingredients")
	private Recipe recipe;
	
	public Ingredient() {
	}

	public Ingredient(int quantity, String units, String ingredient) {
		this.quantity = quantity;
		this.units = units;
		this.ingredient = ingredient;
		this.recipe = null;
	}
}