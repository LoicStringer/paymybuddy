package com.paymybuddy.responseentity;

import org.springframework.stereotype.Component;

@Component
public class TransferOperationInfo {

	private String message;
	
	public TransferOperationInfo() {

	}

	public TransferOperationInfo(String message) {
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
