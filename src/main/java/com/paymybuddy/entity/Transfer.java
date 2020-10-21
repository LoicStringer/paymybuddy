package com.paymybuddy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "TRANSFER")
@IdClass(TransferPK.class)
public class Transfer {

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TRANSFER_ACCOUNT_FROM")
	private Account accountFrom;

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TRANSFER_ACCOUNT_TO")
	private Account accountTo;

	@Id
	@OneToOne
	@MapsId("operationId")
	@JoinColumn(name = "TRANSFER_OPERATION_ID", referencedColumnName = "OPERATION_ID")
	private Operation transferOperationId;

	@Column(name = "TRANSFER_DESCRIPTION", length = 20)
	private String transferDescription;

	public Transfer() {
	}

	public Account getAccountFrom() {
		return accountFrom;
	}

	public void setAccountFrom(Account accountFrom) {
		this.accountFrom = accountFrom;
	}

	public Account getAccountTo() {
		return accountTo;
	}

	public void setAccountTo(Account accountTo) {
		this.accountTo = accountTo;
	}

	public Operation getTransferOperationId() {
		return transferOperationId;
	}

	public void setTransferOperationId(Operation transferOperationId) {
		this.transferOperationId = transferOperationId;
	}

	public String getTransferDescription() {
		return transferDescription;
	}

	public void setTransferDescription(String transferDescription) {
		this.transferDescription = transferDescription;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountFrom == null) ? 0 : accountFrom.hashCode());
		result = prime * result + ((accountTo == null) ? 0 : accountTo.hashCode());
		result = prime * result + ((transferDescription == null) ? 0 : transferDescription.hashCode());
		result = prime * result + ((transferOperationId == null) ? 0 : transferOperationId.hashCode());
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
		Transfer other = (Transfer) obj;
		if (accountFrom == null) {
			if (other.accountFrom != null)
				return false;
		} else if (!accountFrom.equals(other.accountFrom))
			return false;
		if (accountTo == null) {
			if (other.accountTo != null)
				return false;
		} else if (!accountTo.equals(other.accountTo))
			return false;
		if (transferDescription == null) {
			if (other.transferDescription != null)
				return false;
		} else if (!transferDescription.equals(other.transferDescription))
			return false;
		if (transferOperationId == null) {
			if (other.transferOperationId != null)
				return false;
		} else if (!transferOperationId.equals(other.transferOperationId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Transfer [accountFrom=" + accountFrom.getAccountUserName() + ", accountTo=" + accountTo.getAccountUserName() + ", transferOperationId="
				+ transferOperationId + ", transferDescription=" + transferDescription + "]";
	}

	
	

}
