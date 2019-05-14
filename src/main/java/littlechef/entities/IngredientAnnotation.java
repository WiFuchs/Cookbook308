package littlechef.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@Entity
public class IngredientAnnotation extends Annotation {

    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties("annotations")
    private Ingredient ingredient;
    
    public IngredientAnnotation() {}

    @JsonCreator
    public IngredientAnnotation(
    		@JsonProperty("comment") String comment, 
    		@JsonProperty("ingredient") Ingredient ingredient) {
    	super(comment);
    	this.ingredient = ingredient;
    }
}
