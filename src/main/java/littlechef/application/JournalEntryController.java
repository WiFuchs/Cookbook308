package littlechef.application;

import java.util.List;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import littlechef.entities.JournalEntry;
import littlechef.exceptions.JournalEntryNotFoundException;
import littlechef.repositories.JournalEntryRepository;


@RestController
class JournalEntryController {

	private final JournalEntryRepository repository;

	JournalEntryController(JournalEntryRepository repository) {
		this.repository = repository;
	}

	// Aggregate root

	@GetMapping("/user/{id}/journalentries")
	List<JournalEntry> all() {
		return repository.findAll();
	}

	@PostMapping("/journalentries")
	JournalEntry newJournalEntry(@RequestBody JournalEntry newJournalEntry) {
		return repository.save(newJournalEntry);
	}

	// Single item

	@GetMapping("/journalentries/{id}")
	JournalEntry one(@PathVariable Long id) {
		
		return repository.findById(id).orElseThrow(() -> new JournalEntryNotFoundException(id));
	}
	
	@GetMapping("/user/{id}/journalentries/{timestamp}")
	JournalEntry byIdAndTimeStamp(@PathVariable("id") Long id, 
			@PathVariable("timestamp") @DateTimeFormat(pattern = "yyyy-MM-dd") Date timestamp) {
	
		return repository.findByUserIDAndTimestamp(id, timestamp).orElseThrow(() -> new JournalEntryNotFoundException(timestamp));
	}

	@PutMapping("/journalentries/{id}")
	JournalEntry replaceJournalEntry(@RequestBody JournalEntry newJournalEntry, @PathVariable Long id) {

	/*
	 * Commented out code, not sure if we will use it in the future
	 * 
 	 *	return repository.findById(id)
 	 *		.map(recipe -> {
	 *			recipe.setName(newRecipe.getName());
     *				recipe.setRole(newRecipe.getRole());
     *			return repository.save(employee);
     *		})
     *			.orElseGet(() -> {
     *				newEmployee.setId(id);
     *				return repository.save(newEmployee);
     *			});
	 */	
		
		//TODO: implement put
		
		return repository.findById(id).orElseThrow(() -> new JournalEntryNotFoundException(id));
	}

	@DeleteMapping("/journalentries/{id}")
	void deleteJournalEntry(@PathVariable Long id) {
		repository.deleteById(id);
	}
	
	@GetMapping("/journalentries/user/{id}")
	JournalEntry first1ByOrderByTimeDesc(@PathVariable Long id) {
		
		return repository.findFirst1ByUserIDOrderByTimestampDesc(id).orElseThrow(() -> new JournalEntryNotFoundException(id));
	}
}
