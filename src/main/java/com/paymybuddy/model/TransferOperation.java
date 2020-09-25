package com.paymybuddy.model;

import org.springframework.stereotype.Component;

import com.paymybuddy.entity.Operation;
import com.paymybuddy.entity.Tax;
import com.paymybuddy.entity.Transfer;

@Component
public class TransferOperation {

	private Transfer transfer;
	private Operation operation;
	
	public TransferOperation() {
	}

	public TransferOperation(Transfer transfer, Operation operation) {
		super();
		this.transfer = transfer;
		this.operation = operation;
	}

	public Transfer getTransfer() {
		return transfer;
	}

	public void setTransfer(Transfer transfer) {
		this.transfer = transfer;
	}

	public Operation getOperation() {
		return operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}

	
	
}
