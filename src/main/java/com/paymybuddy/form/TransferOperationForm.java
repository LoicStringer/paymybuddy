package com.paymybuddy.form;

import javax.validation.constraints.Min;

import org.springframework.stereotype.Component;

/**
 * <p>Test purpose class consisting in a simple form collecting {@link TransferOperationDTO} data 
 * from front end in order to process a transfer operation. 
 * To be improved with a front end collaboration.</p>
 * @author newbie
 *@see TransferOperationService
 */
@Component
public class TransferOperationForm {
	
	private long accountFromId;
	private long accountToId;
	
	@Min(value=0,message="Amount can't be negative !")
	private double amount;
	
	private String transferDescription;
	private int taxApplied;
	
	public TransferOperationForm() {
	}

	public TransferOperationForm(long accountFromId, long accountToId, double amount, String transferDescription,
			int taxApplied) {
		super();
		this.accountFromId = accountFromId;
		this.accountToId = accountToId;
		this.amount = amount;
		this.transferDescription = transferDescription;
		this.taxApplied = taxApplied;
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
