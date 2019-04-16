package entities;

import javax.persistence.*;
import java.util.Date;

import lombok.Data;

@Data
@Entity
public class JournalEntry {
	
	private @Id @GeneratedValue Long id;
	private int prepTime;
	private int cookTime;
	private int difficultyRating;
	private int rating;
	private String tags;
	
	@JoinColumn
	@OneToOne
	private Recipe recipe;
	
	/* Date format will be mm/dd/yyyy, hh:mm am/pm */
	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	private Date utilTimestamp;
	
	/* @OneToMany(mappedBy = "journalEntry", orphanRemoval = true, cascade = CascadeType.ALL) 
	private List<Annotation> annotations; */
	
	/* default constructor */
	public JournalEntry() {
	}

	public JournalEntry(Recipe recipe, Date timeStamp, String tags, int prepTime, 
			int cookTime, int difficultyRating, int rating, Annotation... annotations) {
		this.recipe = recipe;
		this.utilTimestamp = timeStamp;
		this.tags = tags;
		this.prepTime = prepTime;
		this.cookTime = cookTime;
		this.difficultyRating = difficultyRating;
		this.rating = rating;
		//this.annotations = new ArrayList<Annotation>();
	}
}
