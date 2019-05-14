package littlechef.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateUserException extends RuntimeException {

	public DuplicateUserException(String uname) {
		super("The username " + uname + " is already taken");
	}
}