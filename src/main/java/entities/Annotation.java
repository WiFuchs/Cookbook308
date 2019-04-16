
package entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public abstract class Annotation {

    private @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) Long id;
    private String comment;
    private boolean pinned;
    
    public Annotation() {}

    public Annotation(String comment) {
        this.comment = comment;
        this.pinned = false;
    }

}
