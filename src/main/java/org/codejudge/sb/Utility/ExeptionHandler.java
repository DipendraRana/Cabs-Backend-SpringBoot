package org.codejudge.sb.Utility;

import org.codejudge.sb.model.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExeptionHandler {
	
	@ExceptionHandler
	public ResponseEntity<Message> handleValidationException(ValidationFailedException exc) {
		Message message = new Message(StatusCode.FAILURE, exc.getMessage());
		return new ResponseEntity<Message>(message, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public ResponseEntity<Message> handleException(Exception exc) {
		Message message = new Message(StatusCode.FAILURE, "Exception Occured While registering the Driver!!!! Knidly try Again");
		return new ResponseEntity<Message>(message, HttpStatus.BAD_REQUEST);
	}
	
}
