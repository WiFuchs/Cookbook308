package littlechef.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
	private String comment;
	private Long user;
	
	@JoinColumn
	@OneToOne
	private Recipe recipe;
	
	/* Date format will be mm/dd/yyyy, hh:mm am/pm */
	@Basic
	@Temporal(TemporalType.DATE)
	private Date timestamp;
	
	@OneToMany(mappedBy = "journal", orphanRemoval = true, cascade = CascadeType.ALL) 
	private List<Annotation> annotations;
	
	/* default constructor */
	public JournalEntry() {
	}

	public JournalEntry(Recipe recipe, String tags, int prepTime, 
			int cookTime, int difficultyRating, int rating, String comment, Annotation... annotations) {
		this.recipe = recipe;
		this.timestamp = new Date();
		this.tags = tags;
		this.prepTime = prepTime;
		this.cookTime = cookTime;
		this.difficultyRating = difficultyRating;
		this.rating = rating;
		this.comment = comment;
		this.annotations = Stream.of(annotations).collect(Collectors.toList());
		this.annotations.forEach(step -> step.setJournal(this));
		user = (long) 0;
	}
}
