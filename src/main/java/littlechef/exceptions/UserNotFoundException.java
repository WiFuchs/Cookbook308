package littlechef.exceptions;

import java.io.IOException;

public class UserNotFoundException extends RuntimeException {

	public UserNotFoundException(IOException e) {}

}