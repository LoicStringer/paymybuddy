package com.paymybuddy.dto;

import org.springframework.stereotype.Component;

/**
 * <p>This class is used to manipulate data (supplied by front end) required to process 
 * a transfer between accounts and build both {@link Transfer} entity 
 * and {@link Operation} in order to persist them in database.</p> 
 * @author newbie
 *@see TransferOperationForm
 *@see TransferOperationService
 */
@Component
public class TransferOperationDTO {

	private long accountFromId;
	private long accountToId;
	private double amount;
	private String transferDescription;
	private int taxApplied;
	
	public TransferOperationDTO() {
	}

	public long getAccountFromId() {
		return accountFromId;
	}

	public void setAccountFromId(long accountFromId) {
		this.accountFromId = accountFromId;
	}

	public long getAccountToId() {
		return accountToId;
	}

	public void setAccountToId(long accountToId) {
		this.accountToId = accountToId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getTransferDescription() {
		return transferDescription;
	}

	public void setTransferDescription(String transferDescription) {
		this.transferDescription = transferDescription;
	}

	public int getTaxApplied() {
		return taxApplied;
	}

	public void setTaxApplied(int taxApplied) {
		this.taxApplied = taxApplied;
	}

	
}
