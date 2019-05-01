package exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class JournalEntryNotFoundAdvice {

	@ResponseBody
	@ExceptionHandler(JournalEntryNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String journalEntryNotFoundHandler(JournalEntryNotFoundException ex) {
		return ex.getMessage();
	}
}