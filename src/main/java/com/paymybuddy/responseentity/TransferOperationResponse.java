package com.paymybuddy.responseentity;

import org.springframework.stereotype.Component;

import com.paymybuddy.dto.TransferOperationDTO;

@Component
public class TransferOperationResponse {

	private String message;
	private TransferOperationDTO transferOperationDto;
	
	public TransferOperationResponse() {
	}

	public TransferOperationResponse(String message, TransferOperationDTO transferOperationDto) {
		this.message = message;
		this.transferOperationDto = transferOperationDto;
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
