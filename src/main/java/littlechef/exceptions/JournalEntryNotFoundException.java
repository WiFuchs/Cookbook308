package littlechef.exceptions;

import java.util.Date;

public class JournalEntryNotFoundException extends RuntimeException {

	public JournalEntryNotFoundException(Long id) {
		super("Could not find journal entry " + id);
	}
	
	public JournalEntryNotFoundException(Date timestamp) {
		super("Could not find journal entry " + timestamp);
	}
}