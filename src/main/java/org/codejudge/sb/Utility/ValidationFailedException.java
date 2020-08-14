package org.codejudge.sb.Utility;

public class ValidationFailedException extends RuntimeException {

	private static final long serialVersionUID = -8346440020134698545L;

	public ValidationFailedException(String message, Throwable cause) {
		super(message, cause);
	}

	public ValidationFailedException(String message) {
		super(message);
	}

	public ValidationFailedException(Throwable cause) {
		super(cause);
	}

	
}
