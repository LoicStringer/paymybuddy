package com.paymybuddy.entity;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TRANSACTION")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TRANSACTION_ID")
	private long transactionId;

	@Column(name = "TRANSACTION_DATE", columnDefinition = "timestamp")
	private Instant transactionDate;

	@Column(name = "TRANSACTION_AMOUNT", nullable = true)
	private double transactionAmount;

	@Column(name = "TRANSACTION_FEE", nullable = true)
	private double transactionFee;

	@OneToOne
	@JoinColumn(name = "TRANSACTION_TAX_ID", referencedColumnName = "TAX_ID")
	private Tax transactionTax;

	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Transaction(long transactionId, Instant transactionDate, double transactionAmount, double transactionFee,
			Tax transactionTax) {
		super();
		this.transactionId = transactionId;
		this.transactionDate = transactionDate;
		this.transactionAmount = transactionAmount;
		this.transactionFee = transactionFee;
		this.transactionTax = transactionTax;
	}

	public long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}

	public Instant getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Instant transactionDate) {
		this.transactionDate = transactionDate;
	}

	public double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public double getTransactionFee() {
		return transactionFee;
	}

	public void setTransactionFee(double transactionFee) {
		this.transactionFee = transactionFee;
	}

	public Tax getTransactionTax() {
		return transactionTax;
	}

	public void setTransactionTax(Tax transactionTax) {
		this.transactionTax = transactionTax;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(transactionAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((transactionDate == null) ? 0 : transactionDate.hashCode());
		temp = Double.doubleToLongBits(transactionFee);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (int) (transactionId ^ (transactionId >>> 32));
		result = prime * result + ((transactionTax == null) ? 0 : transactionTax.hashCode());
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
		Transaction other = (Transaction) obj;
		if (Double.doubleToLongBits(transactionAmount) != Double.doubleToLongBits(other.transactionAmount))
			return false;
		if (transactionDate == null) {
			if (other.transactionDate != null)
				return false;
		} else if (!transactionDate.equals(other.transactionDate))
			return false;
		if (Double.doubleToLongBits(transactionFee) != Double.doubleToLongBits(other.transactionFee))
			return false;
		if (transactionId != other.transactionId)
			return false;
		if (transactionTax == null) {
			if (other.transactionTax != null)
				return false;
		} else if (!transactionTax.equals(other.transactionTax))
			return false;
		return true;
	}

}
