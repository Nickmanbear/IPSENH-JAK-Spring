package nl.cherement.jak.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RecordNotFoundException extends Exception {

	private static final String DEFAULT_MESSAGE = "No record exist for given id";

	private static final long serialVersionUID = 1L;

	public RecordNotFoundException() {
		super(DEFAULT_MESSAGE);
	}
	
	public RecordNotFoundException(String message) {
		super(message);
	}
	
	public RecordNotFoundException(String message, Throwable t) {
		super(message, t);
	}
}
