
package littlechef.entities;

import lombok.Data;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

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

    @JsonCreator
    public Annotation(@JsonProperty("comment") String comment) {
        this.comment = comment;
    }

}