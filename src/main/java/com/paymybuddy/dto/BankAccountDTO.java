package com.paymybuddy.dto;

import org.springframework.stereotype.Component;

@Component
public class BankAccountDTO {
	
	private String bankAccountIban;
	private String bankAccountHolderName;
	private String bankAccountDescription;
	private long accountHolderId;
	
	public BankAccountDTO() {
	}

	public BankAccountDTO(String bankAccountIban, String bankAccountHolderName, String bankAccountDescription,
			long accountHolderId) {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (accountHolderId ^ (accountHolderId >>> 32));
		result = prime * result + ((bankAccountDescription == null) ? 0 : bankAccountDescription.hashCode());
		result = prime * result + ((bankAccountHolderName == null) ? 0 : bankAccountHolderName.hashCode());
		result = prime * result + ((bankAccountIban == null) ? 0 : bankAccountIban.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BankAccountDTO other = (BankAccountDTO) obj;
		if (accountHolderId != other.accountHolderId)
			return false;
		if (bankAccountDescription == null) {
			if (other.bankAccountDescription != null)
				return false;
		} else if (!bankAccountDescription.equals(other.bankAccountDescription))
			return false;
		if (bankAccountHolderName == null) {
			if (other.bankAccountHolderName != null)
				return false;
		} else if (!bankAccountHolderName.equals(other.bankAccountHolderName))
			return false;
		if (bankAccountIban == null) {
			if (other.bankAccountIban != null)
				return false;
		} else if (!bankAccountIban.equals(other.bankAccountIban))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BankAccountDTO [bankAccountIban=" + bankAccountIban + ", bankAccountHolderName=" + bankAccountHolderName
				+ ", bankAccountDescription=" + bankAccountDescription + ", accountHolderId=" + accountHolderId + "]";
	}
	
	
	
}
