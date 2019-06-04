package littlechef.entities;

import lombok.Data;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@Entity
public class InstructionAnnotation extends Annotation {

    private Long stepID;

    public InstructionAnnotation() {}
    
    @JsonCreator
    public InstructionAnnotation(
    		@JsonProperty("comment") String comment, 
    		@JsonProperty("stepid") Long stepID) {
    	super(comment);
    	this.stepID = stepID;
    }
}
