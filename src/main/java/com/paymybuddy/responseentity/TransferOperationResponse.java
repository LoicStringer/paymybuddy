package com.paymybuddy.responseentity;

import org.springframework.stereotype.Component;

import com.paymybuddy.dto.TransferOperationDTO;
import com.paymybuddy.service.TransferOperationService;

/**
 * <p>Detailed information message returned when a transfer operation has succeed.</p>
 * @author newbie
 *@see TransferOperationService
 */
@Component
public class TransferOperationResponse {

	private String message;
	private TransferOperationDTO transferOperationDto;
	
	public TransferOperationResponse() {
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public TransferOperationDTO getTransferOperationDto() {
		return transferOperationDto;
	}

	public void setTransferOperationDto(TransferOperationDTO transferOperationDto) {
		this.transferOperationDto = transferOperationDto;
	}
	
	
	
}
