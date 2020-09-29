package com.paymybuddy.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

@Entity
@Table(name = "BANK_ACCOUNT")
public class BankAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BANK_ACCOUNT_ID")
	private long bankAccountId;

	@NotNull
	@Column(name = "BANK_ACCOUNT_IBAN", length = 35, unique = true, nullable = false)
	private String bankAccountIban;

	@NotNull
	@Column(name = "BANK_ACCOUNT_HOLDER_NAME", length = 30, nullable = false)
	private String bankAccountHolderName;

	@Column(name = "BANK_ACCOUNT_DESCRIPTION", length = 20)
	private String bankAccountDescription;

	@ManyToOne
	@NotNull
	@JoinColumn(name = "ACCOUNT_HOLDER_ID")
	private Account accountHolderId;

	@JsonIgnore
	@OneToMany(mappedBy = "bankAccountId")
	private List<Providing> providingsToAccount;

	public BankAccount() {
	}

	public BankAccount(long bankAccountId, String bankAccountIban, String bankAccountHolderName,
			String bankAccountDescription, Account accountHolderId, List<Providing> providingsToAccount) {
		super();
		this.bankAccountId = bankAccountId;
		this.bankAccountIban = bankAccountIban;
		this.bankAccountHolderName = bankAccountHolderName;
		this.bankAccountDescription = bankAccountDescription;
		this.accountHolderId = accountHolderId;
		this.providingsToAccount = providingsToAccount;
	}

	public long getBankAccountId() {
		return bankAccountId;
	}

	public void setBankAccountId(long bankAccountId) {
		this.bankAccountId = bankAccountId;
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

	public Account getAccountHolderId() {
		return accountHolderId;
	}

	public void setAccountHolderId(Account accountHolderId) {
		this.accountHolderId = accountHolderId;
	}

	public List<Providing> getProvidingsToAccount() {
		return providingsToAccount;
	}

	public void setProvidingsToAccount(List<Providing> providingsToAccount) {
		this.providingsToAccount = providingsToAccount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountHolderId == null) ? 0 : accountHolderId.hashCode());
		result = prime * result + ((bankAccountDescription == null) ? 0 : bankAccountDescription.hashCode());
		result = prime * result + ((bankAccountHolderName == null) ? 0 : bankAccountHolderName.hashCode());
		result = prime * result + ((bankAccountIban == null) ? 0 : bankAccountIban.hashCode());
		result = prime * result + (int) (bankAccountId ^ (bankAccountId >>> 32));
		result = prime * result + ((providingsToAccount == null) ? 0 : providingsToAccount.hashCode());
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
		BankAccount other = (BankAccount) obj;
		if (accountHolderId == null) {
			if (other.accountHolderId != null)
				return false;
		} else if (!accountHolderId.equals(other.accountHolderId))
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
		if (bankAccountId != other.bankAccountId)
			return false;
		if (providingsToAccount == null) {
			if (other.providingsToAccount != null)
				return false;
		} else if (!providingsToAccount.equals(other.providingsToAccount))
			return false;
		return true;
	}

}
