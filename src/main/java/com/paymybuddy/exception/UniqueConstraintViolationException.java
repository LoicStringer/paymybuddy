package com.paymybuddy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UniqueConstraintViolationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UniqueConstraintViolationException(String message, Throwable cause) {
		super(message, cause);
	}

	public UniqueConstraintViolationException(String message) {
		super(message);
	}

	
	
}
