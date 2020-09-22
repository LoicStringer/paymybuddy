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
@Table(name = "OPERATION")
public class Operation {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "OPERATION_ID")
	private long operationId;

	@Column(name = "OPERATION_DATE")
	private Instant operationDate;

	@Column(name = "OPERATION_AMOUNT", nullable = false)
	private double operationAmount;

	@Column(name = "OPERATION_FEE", nullable = true)
	private double operationFee;

	@OneToOne
	@JoinColumn(name = "OPERATION_TAX_ID", referencedColumnName = "TAX_ID")
	private Tax operationTax;

	public Operation() {
	}

	public Operation(long operationId, Instant operationDate, double operationAmount, double operationFee,
			Tax operationTax) {
		super();
		this.operationId = operationId;
		this.operationDate = operationDate;
		this.operationAmount = operationAmount;
		this.operationFee = operationFee;
		this.operationTax = operationTax;
	}

	public long getOperationId() {
		return operationId;
	}

	public void setOperationId(long operationId) {
		this.operationId = operationId;
	}

	public Instant getOperationDate() {
		return operationDate;
	}

	public void setOperationDate(Instant operationDate) {
		this.operationDate = operationDate;
	}

	public double getOperationAmount() {
		return operationAmount;
	}

	public void setOperationAmount(double operationAmount) {
		this.operationAmount = operationAmount;
	}

	public double getOperationFee() {
		return operationFee;
	}

	public void setOperationFee(double operationFee) {
		this.operationFee = operationFee;
	}

	public Tax getOperationTax() {
		return operationTax;
	}

	public void setOperationTax(Tax operationTax) {
		this.operationTax = operationTax;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(operationAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((operationDate == null) ? 0 : operationDate.hashCode());
		temp = Double.doubleToLongBits(operationFee);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (int) (operationId ^ (operationId >>> 32));
		result = prime * result + ((operationTax == null) ? 0 : operationTax.hashCode());
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
		Operation other = (Operation) obj;
		if (Double.doubleToLongBits(operationAmount) != Double.doubleToLongBits(other.operationAmount))
			return false;
		if (operationDate == null) {
			if (other.operationDate != null)
				return false;
		} else if (!operationDate.equals(other.operationDate))
			return false;
		if (Double.doubleToLongBits(operationFee) != Double.doubleToLongBits(other.operationFee))
			return false;
		if (operationId != other.operationId)
			return false;
		if (operationTax == null) {
			if (other.operationTax != null)
				return false;
		} else if (!operationTax.equals(other.operationTax))
			return false;
		return true;
	}

}
