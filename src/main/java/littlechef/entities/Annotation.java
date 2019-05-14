
package littlechef.entities;

import lombok.Data;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Entity
public abstract class Annotation {

	@Id 
	@GeneratedValue
    private Long id;
	
    private String comment;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn
    private JournalEntry journal;
    
    public Annotation() {}

    public Annotation(String comment) {
        this.comment = comment;
    }

}