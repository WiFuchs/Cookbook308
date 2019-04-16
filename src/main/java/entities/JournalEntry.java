package entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import lombok.Data;

@Data
@Entity
public class JournalEntry {

	private int prepTime;
	private int cookTime;
	private int difficultyRating;
	private int rating;
	private List<String> tags;
	
	@OneToOne(mappedBy = "journalEntry", orphanRemoval = false)
	private Recipe recipe;
	
	/* Date format will be mm/dd/yyyy, hh:mm am/pm */
	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	private Date utilTimestamp;
	
	@OneToMany(mappedBy = "journalEntry", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<Annotation> annotations;
	
	/* default constructor */
	JournalEntry() {
	}

	JournalEntry(Recipe recipe, Date timeStamp, List<String> tags, int prepTime, 
			int cookTime, int difficultyRating, int rating, Annotation... annotations) {
		this.recipe = recipe;
		this.utilTimestamp = timeStamp;
		this.tags = new ArrayList<String>();
		this.prepTime = prepTime;
		this.cookTime = cookTime;
		this.difficultyRating = difficultyRating;
		this.rating = rating;
		this.annotations = new ArrayList<Annotation>();
	}
}
