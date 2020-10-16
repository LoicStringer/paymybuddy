package com.paymybuddy.form;

import javax.validation.constraints.Min;

import org.springframework.stereotype.Component;

@Component
public class ProvidingOperationForm {

	private long accountId;
	private long bankAccountId;
	
	@Min(value=0, message="Amount can't be negative !")
	private double amount;
	
	private int taxApplied;
	private String providingType;
	
	public ProvidingOperationForm() {
	}

	public ProvidingOperationForm(long accountId, long bankAccountId, double amount, int taxApplied,
			String providingType) {
		super();
		this.accountId = accountId;
		this.bankAccountId = bankAccountId;
		this.amount = amount;
		this.taxApplied = taxApplied;
		this.providingType = providingType;
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
