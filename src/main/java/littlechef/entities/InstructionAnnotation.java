package littlechef.entities;

import lombok.Data;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@Entity
public class InstructionAnnotation extends Annotation {

    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties("annotations")
    private Instruction instruction;

    public InstructionAnnotation() {}
    
    @JsonCreator
    public InstructionAnnotation(
    		@JsonProperty("comment") String comment, 
    		@JsonProperty("instruction") Instruction instruction) {
    	super(comment);
    	this.instruction = instruction;
    }
}
