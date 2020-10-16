package com.paymybuddy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(name = "PROVIDING")
@IdClass(ProvidingPK.class)
public class Providing {

	public enum ProvidingType {
		ACCOUNTTOBANKACCOUNT, BANKACCOUNTTOACCOUNT
	}

	@Id
	@MapsId("holderAccount")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACCOUNT_ID")
	private Account holderAccount;

	@Id
	@MapsId("bankAccountId")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BANK_ACCOUNT_ID")
	private BankAccount bankAccount;

	@Id
	@MapsId("operationId")
	@OneToOne
	@JoinColumn(name = "PROVIDING_OPERATION_ID", referencedColumnName = "OPERATION_ID")
	private Operation providingOperation;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "PROVIDING_TYPE", length = 20, nullable = false)
	private ProvidingType providingType;

	public Providing() {
	}

	public Account getHolderAccount() {
		return holderAccount;
	}

	public void setHolderAccount(Account holderAccount) {
		this.holderAccount = holderAccount;
	}

	public BankAccount getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(BankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}

	public Operation getProvidingOperation() {
		return providingOperation;
	}

	public void setProvidingOperation(Operation providingOperation) {
		this.providingOperation = providingOperation;
	}

	public ProvidingType getProvidingType() {
		return providingType;
	}

	public void setProvidingType(ProvidingType providingType) {
		this.providingType = providingType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bankAccount == null) ? 0 : bankAccount.hashCode());
		result = prime * result + ((holderAccount == null) ? 0 : holderAccount.hashCode());
		result = prime * result + ((providingOperation == null) ? 0 : providingOperation.hashCode());
		result = prime * result + ((providingType == null) ? 0 : providingType.hashCode());
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
		Providing other = (Providing) obj;
		if (bankAccount == null) {
			if (other.bankAccount != null)
				return false;
		} else if (!bankAccount.equals(other.bankAccount))
			return false;
		if (holderAccount == null) {
			if (other.holderAccount != null)
				return false;
		} else if (!holderAccount.equals(other.holderAccount))
			return false;
		if (providingOperation == null) {
			if (other.providingOperation != null)
				return false;
		} else if (!providingOperation.equals(other.providingOperation))
			return false;
		if (providingType != other.providingType)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Providing [holderAccount=" + holderAccount.getAccountUserName() + ", bankAccount=" + bankAccount.getBankAccountDescription() + ", providingOperation="
				+ providingOperation + ", providingType=" + providingType + "]";
	}

	

	
}
