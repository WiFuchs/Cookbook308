package littlechef.exceptions;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

public class RecipeNotAuthenticatedException extends RuntimeException {
	
	public RecipeNotAuthenticatedException(@AuthenticationPrincipal String user) {
		super("The recipe " + user + " has not been authenticated");
		
		//TODO: check if exception is correct
	}

}
