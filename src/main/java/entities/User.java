package entities;

import java.util.List;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;


@Data
@Entity
public class User {
	@Id
	@GeneratedValue
	private long id;
	
	private String username;
	
	@OneToMany
	@JsonIgnore
	private List<Recipe> recipes;
	
	@OneToMany
	@JsonIgnore
	private List<JournalEntry> entries;
	
	
	public User() {
		
	}
	
	public User(String username, List<Recipe> recipes, List<JournalEntry> entries) {
		this.username = username;
		this.recipes = recipes;
		this.entries = entries;
	}
}
