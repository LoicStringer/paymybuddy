package com.paymybuddy.responseentity;

import org.springframework.stereotype.Component;

@Component
public class ProvidingOperationResponse {

	private String message;

	public ProvidingOperationResponse() {
	}

	public ProvidingOperationResponse(String message) {
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
