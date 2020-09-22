package com.paymybuddy.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TRANSFER")
@IdClass(Transfer.TransferPK.class)
public class Transfer {

	@Id
	@ManyToOne
	@JoinColumn(name = "TRANSFER_ACCOUNT_FROM")
	private Account accountFrom;

	@Id
	@ManyToOne
	@JoinColumn(name = "TRANSFER_ACCOUNT_TO")
	private Account accountTo;

	@Id
	@OneToOne
	@MapsId
	@JoinColumn(name = "TRANSFER_OPERATION_ID", referencedColumnName = "OPERATION_ID")
	private Operation transferOperationId;

	@Column(name = "TRANSFER_DESCRIPTION", length = 20, nullable = true)
	private String transferDescription;

	public Transfer() {
	}

	public Transfer(Account accountFrom, Account accountTo, Operation transferOperationId, String transferDescription) {
		super();
		this.accountFrom = accountFrom;
		this.accountTo = accountTo;
		this.transferOperationId = transferOperationId;
		this.transferDescription = transferDescription;
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

	public class TransferPK implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private long accountFrom;
		private long accountTo;
		private long transferOperationId;

		public TransferPK() {
			super();
			// TODO Auto-generated constructor stub
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
			result = prime * result + getEnclosingInstance().hashCode();
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
			if (!getEnclosingInstance().equals(other.getEnclosingInstance()))
				return false;
			if (accountFrom != other.accountFrom)
				return false;
			if (accountTo != other.accountTo)
				return false;
			if (transferOperationId != other.transferOperationId)
				return false;
			return true;
		}

		private Transfer getEnclosingInstance() {
			return Transfer.this;
		}

	}

}
