package com.paymybuddy.responseentity;

import org.springframework.stereotype.Component;

import com.paymybuddy.dto.ProvidingOperationDTO;
import com.paymybuddy.service.ProvidingOperationService;

/**
 * <p>Detailed information message returned when a providing operation 
 * from account/bank account to account/bank account has succeed.</p>
 * @author newbie
 * @see ProvidingOperationService
 *
 */
@Component
public class ProvidingOperationResponse {

	private String message;
	private ProvidingOperationDTO providingOperationDto;

	public ProvidingOperationResponse() {
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
