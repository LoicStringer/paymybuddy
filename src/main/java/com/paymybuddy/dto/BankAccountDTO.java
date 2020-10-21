package com.paymybuddy.dto;

import org.springframework.stereotype.Component;

/**
 * <p>This class is used to manipulate data related to the {@link BankAccount} entity 
 * between front end and database.</p> 
 * @author newbie
 * @see paymybuddy.form.BankAccountForm
 *
 */
@Component
public class BankAccountDTO {
	
	private String bankAccountIban;
	private String bankAccountHolderName;
	private String bankAccountDescription;
	private long accountHolderId;
	
	public BankAccountDTO() {
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
