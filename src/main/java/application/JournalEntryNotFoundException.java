package application;

import java.util.Date;

public class JournalEntryNotFoundException extends RuntimeException {

	JournalEntryNotFoundException(Long id) {
		super("Could not find journal entry " + id);
	}
	
	JournalEntryNotFoundException(Date timestamp) {
		super("Could not find journal entry " + timestamp);
	}
}