package littlechef.entities;

import lombok.Data;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@Entity
public class InstructionAnnotation extends Annotation {

    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties("annotations")
    private Instruction instruction;

    public InstructionAnnotation() {}
    
    public InstructionAnnotation(String comment, Instruction instruction) {
    	super(comment);
    	this.instruction = instruction;
    }
}
