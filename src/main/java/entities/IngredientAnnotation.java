package entities;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
public class IngredientAnnotation extends Annotation {

    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    public IngredientAnnotation() {}
}
