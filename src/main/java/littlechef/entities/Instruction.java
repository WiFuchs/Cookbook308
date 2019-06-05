package littlechef.entities;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@Entity
public class Instruction {

	@Id
	@GeneratedValue
	private Long id;

	@Column(length = 500)
	private String step;

	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    @JsonIgnore
	private Recipe recipe;
	
	@JsonIgnoreProperties("steps")
	@OneToMany(mappedBy = "stepID", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<InstructionAnnotation> annotations = new ArrayList<>();
	
	public Instruction() {}

	@JsonCreator
	public Instruction(@JsonProperty("step")String step) {
		this.step = step;
		this.recipe = null;
	}
	
	public void addAnnotation(InstructionAnnotation annot) {
		annotations.add(annot);
		annot.setStepID(this.id);
	}
	
	public void removeAnnotation(InstructionAnnotation annot) {
		annotations.remove(annot);
		annot.setStepID(null);
	}
}
