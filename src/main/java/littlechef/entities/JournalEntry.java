package littlechef.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.lang.Long;
import lombok.Data;

@Data
@Entity
public class JournalEntry {
	
	@Id
	@GeneratedValue 
	private Long id;
	
	private int prepTime;
	private int cookTime;
	private int difficultyRating;
	private int rating;
	private String tags;
	private String comment;
	
	
	private Long userID;
	
	//@JoinColumn
	//@OneToOne
	private Long recipe;

	/* TODO: remove this, will be provided by the front end */
	/* Date format will be mm/dd/yyyy, hh:mm am/pm */
	@Basic
	@Temporal(TemporalType.DATE)
	private Date timestamp;
	
	@OneToMany(mappedBy = "journal", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<InstructionAnnotation> stepAnnotations;
	@OneToMany(mappedBy = "journal", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<IngredientAnnotation> ingredientAnnotations;
	
	
	/* default constructor */
	public JournalEntry() {}

	@JsonCreator
	public JournalEntry(
			@JsonProperty("recipe") Long recipeID, 
			@JsonProperty("tags") String tags, 
			@JsonProperty("prepTime") int prepTime, 
			@JsonProperty("cookTime") int cookTime, 
			@JsonProperty("difficulty") int difficultyRating, 
			@JsonProperty("rating") int rating, 
			@JsonProperty("comment") String comment, 
			@JsonProperty("stepannotations") InstructionAnnotation[] stepAnnotations,
			@JsonProperty("ingredientannotations") IngredientAnnotation[] ingredientAnnotations) {
		this.recipe = recipeID;
		this.timestamp = new Date();
		this.tags = tags;
		this.prepTime = prepTime;
		this.cookTime = cookTime;
		this.difficultyRating = difficultyRating;
		this.rating = rating;
		this.comment = comment;
		this.ingredientAnnotations = Stream.of(ingredientAnnotations).collect(Collectors.toList());
		this.ingredientAnnotations.forEach(ingredient -> ingredient.setJournal(this));
		this.stepAnnotations = Stream.of(stepAnnotations).collect(Collectors.toList());
		this.stepAnnotations.forEach(instruction -> instruction.setJournal(this));
		this.userID = (long) 0;
	}
}
