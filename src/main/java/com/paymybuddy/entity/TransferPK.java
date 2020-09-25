package com.paymybuddy.entity;

import java.io.Serializable;



public class TransferPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long accountFrom;
	private long accountTo;
	private long transferOperationId;

	
	public TransferPK() {
	}


	public TransferPK(long accountFrom, long accountTo, long transferOperationId) {
		super();
		this.accountFrom = accountFrom;
		this.accountTo = accountTo;
		this.transferOperationId = transferOperationId;
	}


	public long getAccountFrom() {
		return accountFrom;
	}


	public void setAccountFrom(long accountFrom) {
		this.accountFrom = accountFrom;
	}


	public long getAccountTo() {
		return accountTo;
	}


	public void setAccountTo(long accountTo) {
		this.accountTo = accountTo;
	}


	public long getTransferOperationId() {
		return transferOperationId;
	}


	public void setTransferOperationId(long transferOperationId) {
		this.transferOperationId = transferOperationId;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (accountFrom ^ (accountFrom >>> 32));
		result = prime * result + (int) (accountTo ^ (accountTo >>> 32));
		result = prime * result + (int) (transferOperationId ^ (transferOperationId >>> 32));
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
		TransferPK other = (TransferPK) obj;
		if (accountFrom != other.accountFrom)
			return false;
		if (accountTo != other.accountTo)
			return false;
		if (transferOperationId != other.transferOperationId)
			return false;
		return true;
	}

	
}