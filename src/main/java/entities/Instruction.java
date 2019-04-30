package entities;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@Entity
public class Instruction {

	private @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) Long id;

	private String step;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    @JsonIgnore
	private Recipe recipe;
	
	@JsonIgnoreProperties("steps")
	@OneToMany(mappedBy = "instruction", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<InstructionAnnotation> annotations = new ArrayList<InstructionAnnotation>();
	

	public Instruction() {
	}

	public Instruction(String step) {
		this.step = step;
		this.recipe = null;
	}
	
	public void addAnnotation(InstructionAnnotation annot) {
		annotations.add(annot);
		annot.setInstruction(this);
	}
	
	public void removeAnnotation(InstructionAnnotation annot) {
		annotations.remove(annot);
		annot.setInstruction(null);
	}
}
