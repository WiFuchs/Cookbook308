
package entities;

import lombok.Data;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Entity
public abstract class Annotation {

    private @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) Long id;
    private String comment;
    private long userID;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn
    private JournalEntry journal;
    
    public Annotation() {}

    public Annotation(long userID, String comment) {
        this.comment = comment;
        this.userID = userID;
    }

}