package com.paymybuddy.entity;

import java.io.Serializable;

public class ProvidingPK implements Serializable {

	private static final long serialVersionUID = 1L;
	private long holderAccountId;
	private long bankAccountId;
	private long providingOperationId;

	public ProvidingPK() {
	}

	public ProvidingPK(long holderAccountId, long bankAccountId, long providingOperationId) {
		this.holderAccountId = holderAccountId;
		this.bankAccountId = bankAccountId;
		this.providingOperationId = providingOperationId;
	}

	public long getHolderAccountId() {
		return holderAccountId;
	}

	public void setHolderAccountId(long holderAccountId) {
		this.holderAccountId = holderAccountId;
	}

	public long getBankAccountId() {
		return bankAccountId;
	}

	public void setBankAccountId(long bankAccountId) {
		this.bankAccountId = bankAccountId;
	}

	public long getProvidingOperationId() {
		return providingOperationId;
	}

	public void setProvidingOperationId(long providingOperationId) {
		this.providingOperationId = providingOperationId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (bankAccountId ^ (bankAccountId >>> 32));
		result = prime * result + (int) (holderAccountId ^ (holderAccountId >>> 32));
		result = prime * result + (int) (providingOperationId ^ (providingOperationId >>> 32));
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
		if (bankAccountId != other.bankAccountId)
			return false;
		if (holderAccountId != other.holderAccountId)
			return false;
		if (providingOperationId != other.providingOperationId)
			return false;
		return true;
	}

	
}