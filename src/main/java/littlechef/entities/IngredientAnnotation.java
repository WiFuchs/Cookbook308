package littlechef.entities;

import lombok.Data;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@Entity
public class IngredientAnnotation extends Annotation {

	//@ManyToOne(cascade = CascadeType.ALL)
    //@JoinColumn
    //@JsonIgnoreProperties("ingredientannotations")
	
	public Long ingredientID;
	
    public IngredientAnnotation() {}

    @JsonCreator
    public IngredientAnnotation(
    		@JsonProperty("comment") String comment, 
    		@JsonProperty("ingid") Long ingredientID) {
    	super(comment);
    	this.ingredientID = ingredientID;
    }
}
