package application;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import entities.JournalEntry;
import entities.JournalEntryRepository;


@RestController
class JournalEntryController {

	private final JournalEntryRepository repository;

	JournalEntryController(JournalEntryRepository repository) {
		this.repository = repository;
	}

	// Aggregate root

	@GetMapping("/journalentry")
	List<JournalEntry> all() {
		return repository.findAll();
	}

	@PostMapping("/journalentry")
	JournalEntry newJournalEntry(@RequestBody JournalEntry newJournalEntry) {
		return repository.save(newJournalEntry);
	}

	// Single item

	@GetMapping("/journalentry/{id}")
	JournalEntry one(@PathVariable Long id) {

		return repository.findById(id).orElseThrow(() -> new JournalEntryNotFoundException(id));
	}

	@PutMapping("/journalentry/{id}")
	JournalEntry replaceJournalEntry(@RequestBody JournalEntry newJournalEntry, @PathVariable Long id) {

//		return repository.findById(id)
//			.map(recipe -> {
//				recipe.setName(newRecipe.getName());
//				recipe.setRole(newRecipe.getRole());
//				return repository.save(employee);
//			})
//			.orElseGet(() -> {
//				newEmployee.setId(id);
//				return repository.save(newEmployee);
//			});
		
		
		//TODO: implement put
		
		return repository.findById(id).orElseThrow(() -> new JournalEntryNotFoundException(id));
	}

	@DeleteMapping("/journalentry/{id}")
	void deleteEmployee(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
