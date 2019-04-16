package entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper=true)
@Entity
public class IngredientAnnotation extends Annotation {

    @ManyToOne
    @JoinColumn
    private Ingredient ingredient;

    public IngredientAnnotation() {}
}
