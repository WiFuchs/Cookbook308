package littlechef.exceptions;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

public class JournalEntryNotAuthenticatedException extends RuntimeException {

	public JournalEntryNotAuthenticatedException(@AuthenticationPrincipal String user) {
		super("The journal entry " + user + " has not been authenticated");
	}
	
}
