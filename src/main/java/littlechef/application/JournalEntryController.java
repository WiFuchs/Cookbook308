package littlechef.application;

import java.util.List;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import littlechef.entities.JournalEntry;
import littlechef.entities.Recipe;
import littlechef.exceptions.JournalEntryNotAuthenticatedException;
//import littlechef.exceptions.AccessDeniedException;
import littlechef.exceptions.JournalEntryNotFoundException;
import littlechef.exceptions.RecipeNotAuthenticatedException;
import littlechef.repositories.ApplicationUserRepository;
import littlechef.repositories.JournalEntryRepository;


@RestController
class JournalEntryController {

	private final JournalEntryRepository repository;
	private final ApplicationUserRepository users;

	JournalEntryController(JournalEntryRepository repository, ApplicationUserRepository users) {
		this.repository = repository;
		this.users = users;
	}

	// Aggregate root

	@GetMapping("/journalentries")
	List<JournalEntry> all(@AuthenticationPrincipal String user) {
		return repository.findByUserID(users.findByUsername(user).getId());
	}

	@PostMapping("/journalentries")
	JournalEntry newJournalEntry(@AuthenticationPrincipal String user, @RequestBody JournalEntry newJournalEntry) {
		newJournalEntry.setUserID(users.findByUsername(user).getId());
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
	JournalEntry replaceJournalEntry(@AuthenticationPrincipal String user, @RequestBody JournalEntry newJournalEntry, 
			@PathVariable Long id) {

		JournalEntry updatedJournalEntry = repository.findById(id)
				.orElseGet(() -> {
					newJournalEntry.setId(id);
					return repository.save(newJournalEntry);
				});
		
		if(updatedJournalEntry.getUserID() == users.findByUsername(user).getId()) {
			updatedJournalEntry.setRecipe(newJournalEntry.getRecipe());
			updatedJournalEntry.setTimestamp(newJournalEntry.getTimestamp());
			updatedJournalEntry.setTags(newJournalEntry.getTags());
			updatedJournalEntry.setPrepTime(newJournalEntry.getPrepTime());
			updatedJournalEntry.setCookTime(newJournalEntry.getCookTime());
			updatedJournalEntry.setDifficultyRating(newJournalEntry.getDifficultyRating());
			updatedJournalEntry.setRating(newJournalEntry.getRating());
			updatedJournalEntry.setComment(newJournalEntry.getComment());
			updatedJournalEntry.setStepAnnotations(newJournalEntry.getStepAnnotations());
			updatedJournalEntry.setIngredientAnnotations(newJournalEntry.getIngredientAnnotations());
			return repository.save(updatedJournalEntry);
		}
		else
			throw new JournalEntryNotAuthenticatedException(user);
	}

	@DeleteMapping("/journalentries/{id}")
	void deleteJournalEntry(@AuthenticationPrincipal String user, @PathVariable Long id) {
		JournalEntry journal = repository.findById(id).orElseThrow(() -> new JournalEntryNotFoundException(id));
		if (journal.getUserID() == users.findByUsername(user).getId()) {
			repository.deleteById(id);
		} else {
			throw new AccessDeniedException(user);
		}
	}
	
	@GetMapping("/journalentries/first")
	JournalEntry first1ByOrderByTimeDesc(@AuthenticationPrincipal String user) {
		
		long id = users.findByUsername(user).getId();
		
		return repository.findFirst1ByUserIDOrderByTimestampDesc(id).orElseThrow(
						() -> new JournalEntryNotFoundException(id));
	}
}
