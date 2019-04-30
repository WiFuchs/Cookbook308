package entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@EqualsAndHashCode(callSuper=true)
@Entity
public class IngredientAnnotation extends Annotation {

    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties("annotations")
    private Ingredient ingredient;
    
    public IngredientAnnotation() {}

    public IngredientAnnotation(long userID, String comment) {
    	super(userID, comment);
    }
}
