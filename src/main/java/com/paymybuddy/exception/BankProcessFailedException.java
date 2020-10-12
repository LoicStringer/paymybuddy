package com.paymybuddy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PAYMENT_REQUIRED)
public class BankProcessFailedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BankProcessFailedException(String message) {
		super(message);
	}

	
	
}
