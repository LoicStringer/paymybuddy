package com.paymybuddy.form;

import org.springframework.stereotype.Component;

@Component
public class BankAccountForm {
	
	private String bankAccountIban;
	private String bankAccountHolderName;
	private String bankAccountDescription;
	private long accountHolderId;
	
	public BankAccountForm() {
	}

	public BankAccountForm(String bankAccountIban, String bankAccountHolderName, String bankAccountDescription,
			long accountHolderId) {
		super();
		this.bankAccountIban = bankAccountIban;
		this.bankAccountHolderName = bankAccountHolderName;
		this.bankAccountDescription = bankAccountDescription;
		this.accountHolderId = accountHolderId;
	}

	public String getBankAccountIban() {
		return bankAccountIban;
	}

	public void setBankAccountIban(String bankAccountIban) {
		this.bankAccountIban = bankAccountIban;
	}

	public String getBankAccountHolderName() {
		return bankAccountHolderName;
	}

	public void setBankAccountHolderName(String bankAccountHolderName) {
		this.bankAccountHolderName = bankAccountHolderName;
	}

	public String getBankAccountDescription() {
		return bankAccountDescription;
	}

	public void setBankAccountDescription(String bankAccountDescription) {
		this.bankAccountDescription = bankAccountDescription;
	}

	public long getAccountHolderId() {
		return accountHolderId;
	}

	public void setAccountHolderId(long accountHolderId) {
		this.accountHolderId = accountHolderId;
	}


}
