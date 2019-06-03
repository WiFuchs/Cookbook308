package littlechef.entities;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@Entity
public class Ingredient {

	@Id 
	@GeneratedValue
	private long id;
	
	private int quantity;
	private String units;
	private String typeOfIngredient;

	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    @JsonIgnore
	private Recipe recipe;

	@JsonIgnoreProperties("ingredients")
	@OneToMany(mappedBy = "ingredientID", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<IngredientAnnotation> annotations = new ArrayList<>();

	public Ingredient() {
	}

	@JsonCreator
	public Ingredient(
			@JsonProperty("quantity") int quantity, 
			@JsonProperty("units") String units, 
			@JsonProperty("ingredient") String typeOfIngredient) {
		this.quantity = quantity;
		this.units = units;
		this.typeOfIngredient = typeOfIngredient;
		this.recipe = null;
	}
	
	public void addAnnotation(IngredientAnnotation annot) {
		annotations.add(annot);
		annot.setIngredientID(this.id);
	}
	
	public void removeAnnotation(IngredientAnnotation annot) {
		annotations.remove(annot);
		annot.setIngredientID(null);
	}
}
