package com.paymybuddy.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PROVIDING")
@IdClass(Providing.ProvidingPK.class)
public class Providing {

	@Id
	@ManyToOne
	@JoinColumn(name = "ACCOUNT_ID")
	private Account holderAccountId;

	@Id
	@ManyToOne
	@JoinColumn(name = "BANK_ACCOUNT_ID")
	private BankAccount bankAccountId;

	@Id
	@OneToOne
	@MapsId
	@JoinColumn(name = "PROVIDING_OPERATION_ID", referencedColumnName = "OPERATION_ID")
	private Operation providingOperationId;

	public Providing() {

	}

	public Providing(Account holderAccountId, BankAccount bankAccountId, Operation providingOperationId) {
		super();
		this.holderAccountId = holderAccountId;
		this.bankAccountId = bankAccountId;
		this.providingOperationId = providingOperationId;
	}

	public Account getHolderAccountId() {
		return holderAccountId;
	}

	public void setHolderAccountId(Account holderAccountId) {
		this.holderAccountId = holderAccountId;
	}

	public BankAccount getBankAccountId() {
		return bankAccountId;
	}

	public void setBankAccountId(BankAccount bankAccountId) {
		this.bankAccountId = bankAccountId;
	}

	public Operation getProvidingOperationId() {
		return providingOperationId;
	}

	public void setProvidingOperationId(Operation providingOperationId) {
		this.providingOperationId = providingOperationId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bankAccountId == null) ? 0 : bankAccountId.hashCode());
		result = prime * result + ((holderAccountId == null) ? 0 : holderAccountId.hashCode());
		result = prime * result + ((providingOperationId == null) ? 0 : providingOperationId.hashCode());
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
		if (bankAccountId == null) {
			if (other.bankAccountId != null)
				return false;
		} else if (!bankAccountId.equals(other.bankAccountId))
			return false;
		if (holderAccountId == null) {
			if (other.holderAccountId != null)
				return false;
		} else if (!holderAccountId.equals(other.holderAccountId))
			return false;
		if (providingOperationId == null) {
			if (other.providingOperationId != null)
				return false;
		} else if (!providingOperationId.equals(other.providingOperationId))
			return false;
		return true;
	}

	public class ProvidingPK implements Serializable {

		private static final long serialVersionUID = 1L;
		private long holderAccountId;
		private long bankAccountId;
		private long providingOperationId;

		public ProvidingPK() {
		}

		public ProvidingPK(long holderAccountId, long bankAccountId, long providingOperationId) {
			super();
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
			result = prime * result + getEnclosingInstance().hashCode();
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
			if (!getEnclosingInstance().equals(other.getEnclosingInstance()))
				return false;
			if (bankAccountId != other.bankAccountId)
				return false;
			if (holderAccountId != other.holderAccountId)
				return false;
			if (providingOperationId != other.providingOperationId)
				return false;
			return true;
		}

		private Providing getEnclosingInstance() {
			return Providing.this;
		}

	}
}
