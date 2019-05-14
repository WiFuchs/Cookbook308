package littlechef.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@Entity
public class IngredientAnnotation extends Annotation {

    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties("annotations")
    private Ingredient ingredient;
    
    public IngredientAnnotation() {}

    public IngredientAnnotation(String comment, Ingredient ingredient) {
    	super(comment);
    	this.ingredient = ingredient;
    }
}
