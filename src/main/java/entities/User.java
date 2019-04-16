package entities;

import java.util.List;

import javax.persistence.*;

import lombok.Data;


@Data
@Entity
public class User {
	@Id
	@GeneratedValue
	private long id;
	
	private String username;
	
	@OneToMany
	private List<Recipe> recipes;
	
	//private List<JournalEntry> entries;
}
