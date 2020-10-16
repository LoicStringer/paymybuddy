package com.paymybuddy.entity;

import java.io.Serializable;

public class ProvidingPK implements Serializable {

	private static final long serialVersionUID = 1L;
	private long holderAccount;
	private long bankAccount;
	private long providingOperation;

	public ProvidingPK() {
	}

	public ProvidingPK(long holderAccount, long bankAccount, long providingOperation) {
		this.holderAccount = holderAccount;
		this.bankAccount = bankAccount;
		this.providingOperation = providingOperation;
	}

	public long getHolderAccount() {
		return holderAccount;
	}

	public void setHolderAccount(long holderAccount) {
		this.holderAccount = holderAccount;
	}

	public long getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(long bankAccount) {
		this.bankAccount = bankAccount;
	}

	public long getProvidingOperation() {
		return providingOperation;
	}

	public void setProvidingOperation(long providingOperation) {
		this.providingOperation = providingOperation;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (bankAccount ^ (bankAccount >>> 32));
		result = prime * result + (int) (holderAccount ^ (holderAccount >>> 32));
		result = prime * result + (int) (providingOperation ^ (providingOperation >>> 32));
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
		ProvidingPK other = (ProvidingPK) obj;
		if (bankAccount != other.bankAccount)
			return false;
		if (holderAccount != other.holderAccount)
			return false;
		if (providingOperation != other.providingOperation)
			return false;
		return true;
	}

	
}