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
	private UserAccount accountFrom;

	@Id
	@ManyToOne
	@JoinColumn(name = "TRANSFER_ACCOUNT_TO")
	private UserAccount accountTo;

	@Id
	@OneToOne
	@MapsId
	@JoinColumn(name = "TRANSFER_TRANSACTION_ID",referencedColumnName = "TRANSACTION_ID")
	private Transaction transactionId;

	@Column(name = "TRANSFER_AMOUNT")
	private double transferAmount;

	@Column(name = "TRANSFER_DESCRIPTION")
	private String transferDescription;

	public Transfer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Transfer(UserAccount accountFrom, UserAccount accountTo, Transaction transactionId, double transferAmount,
			String transferDescription) {
		super();
		this.accountFrom = accountFrom;
		this.accountTo = accountTo;
		this.transactionId = transactionId;
		this.transferAmount = transferAmount;
		this.transferDescription = transferDescription;
	}

	public UserAccount getAccountFrom() {
		return accountFrom;
	}

	public void setAccountFrom(UserAccount accountFrom) {
		this.accountFrom = accountFrom;
	}

	public UserAccount getAccountTo() {
		return accountTo;
	}

	public void setAccountTo(UserAccount accountTo) {
		this.accountTo = accountTo;
	}

	public Transaction getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Transaction transactionId) {
		this.transactionId = transactionId;
	}

	public double getTransferAmount() {
		return transferAmount;
	}

	public void setTransferAmount(double transferAmount) {
		this.transferAmount = transferAmount;
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
		long temp;
		temp = Double.doubleToLongBits(transferAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((transferDescription == null) ? 0 : transferDescription.hashCode());
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
		if (Double.doubleToLongBits(transferAmount) != Double.doubleToLongBits(other.transferAmount))
			return false;
		if (transferDescription == null) {
			if (other.transferDescription != null)
				return false;
		} else if (!transferDescription.equals(other.transferDescription))
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
		private long transactionId;

		public TransferPK() {
			super();
			// TODO Auto-generated constructor stub
		}

		public TransferPK(long accountFrom, long accountTo, long transactionId) {
			super();
			this.accountFrom = accountFrom;
			this.accountTo = accountTo;
			this.transactionId = transactionId;
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

		public long getTransactionId() {
			return transactionId;
		}

		public void setTransactionId(long transactionId) {
			this.transactionId = transactionId;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getEnclosingInstance().hashCode();
			result = prime * result + (int) (accountFrom ^ (accountFrom >>> 32));
			result = prime * result + (int) (accountTo ^ (accountTo >>> 32));
			result = prime * result + (int) (transactionId ^ (transactionId >>> 32));
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
			if (transactionId != other.transactionId)
				return false;
			return true;
		}

		private Transfer getEnclosingInstance() {
			return Transfer.this;
		}

	}

}
