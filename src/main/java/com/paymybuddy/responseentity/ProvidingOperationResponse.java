package com.paymybuddy.responseentity;

import org.springframework.stereotype.Component;

import com.paymybuddy.dto.ProvidingOperationDTO;

@Component
public class ProvidingOperationResponse {

	private String message;
	private ProvidingOperationDTO providingOperationDto;

	public ProvidingOperationResponse() {
	}

	public ProvidingOperationResponse(String message, ProvidingOperationDTO providingOperationDto) {
		super();
		this.message = message;
		this.providingOperationDto = providingOperationDto;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ProvidingOperationDTO getProvidingOperationDto() {
		return providingOperationDto;
	}

	public void setProvidingOperationDto(ProvidingOperationDTO providingOperationDto) {
		this.providingOperationDto = providingOperationDto;
	}
	
	
	
}
