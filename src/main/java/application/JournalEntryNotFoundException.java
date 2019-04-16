package application;

public class JournalEntryNotFoundException extends RuntimeException {

	JournalEntryNotFoundException(Long id) {
		super("Could not find journal entry " + id);
	}
}