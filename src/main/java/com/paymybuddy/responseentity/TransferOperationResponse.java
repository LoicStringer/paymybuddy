package com.paymybuddy.responseentity;

import org.springframework.stereotype.Component;

@Component
public class TransferOperationResponse {

	private String message;
	
	public TransferOperationResponse() {

	}

	public TransferOperationResponse(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
