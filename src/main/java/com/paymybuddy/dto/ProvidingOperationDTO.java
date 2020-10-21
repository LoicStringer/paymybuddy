package com.paymybuddy.dto;

import org.springframework.stereotype.Component;

/**
 * <p>This class is used to manipulate data (supplied by front end) required to process 
 * a providing from/to a bank account and build both {@link Providing} entity 
 * and {@link Operation} in order to persist them in database.</p> 
 * @author newbie
 *@see ProvidingOperationForm
 *@see ProvidingOperationService
 */
@Component
public class ProvidingOperationDTO {

	private long accountId;
	private long bankAccountId;
	private double amount;
	private int taxApplied;
	private String providingType;
	
	public ProvidingOperationDTO() {
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public long getBankAccountId() {
		return bankAccountId;
	}

	public void setBankAccountId(long bankAccountId) {
		this.bankAccountId = bankAccountId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getTaxApplied() {
		return taxApplied;
	}

	public void setTaxApplied(int taxApplied) {
		this.taxApplied = taxApplied;
	}

	public String getProvidingType() {
		return providingType;
	}

	public void setProvidingType(String providingType) {
		this.providingType = providingType;
	}


}
