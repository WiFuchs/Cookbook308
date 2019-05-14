package littlechef.entities;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
/* import javax.persistence.OneToOne; */

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@Entity
public class Ingredient {

	@Id 
	@GeneratedValue
	private Long id;
	
	private int quantity;
	private String units;
	private String ingredient;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    @JsonIgnore
	private Recipe recipe;

	@JsonIgnoreProperties("ingredients")
	@OneToMany(mappedBy = "ingredient", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<IngredientAnnotation> annotations = new ArrayList<>();

	public Ingredient() {
	}

	@JsonCreator
	public Ingredient(
			@JsonProperty("quantity") int quantity, 
			@JsonProperty("units") String units, 
			@JsonProperty("ingredient") String ingredient) {
		this.quantity = quantity;
		this.units = units;
		this.ingredient = ingredient;
		this.recipe = null;
	}
	
	public void addAnnotation(IngredientAnnotation annot) {
		annotations.add(annot);
		annot.setIngredient(this);
	}
	
	public void removeAnnotation(IngredientAnnotation annot) {
		annotations.remove(annot);
		annot.setIngredient(null);
	}
}
