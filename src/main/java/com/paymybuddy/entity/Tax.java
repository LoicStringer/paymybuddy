package com.paymybuddy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(name = "TAX")
public class Tax {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TAX_ID")
	private int taxId;

	@NotNull
	@Column(name = "TAX_RATE", length = 4, nullable = false)
	private double taxRate;

	@NotNull
	@Column(name = "TAX_DESCRIPTION", length = 25, nullable = false)
	private String taxDescription;

	public Tax() {
	}

	public int getTaxId() {
		return taxId;
	}

	public void setTaxId(int taxId) {
		this.taxId = taxId;
	}

	public double getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(double taxRate) {
		this.taxRate = taxRate;
	}

	public String getTaxDescription() {
		return taxDescription;
	}

	public void setTaxDescription(String taxDescription) {
		this.taxDescription = taxDescription;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((taxDescription == null) ? 0 : taxDescription.hashCode());
		result = prime * result + taxId;
		long temp;
		temp = Double.doubleToLongBits(taxRate);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Tax other = (Tax) obj;
		if (taxDescription == null) {
			if (other.taxDescription != null)
				return false;
		} else if (!taxDescription.equals(other.taxDescription))
			return false;
		if (taxId != other.taxId)
			return false;
		if (Double.doubleToLongBits(taxRate) != Double.doubleToLongBits(other.taxRate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Tax [taxId=" + taxId + ", taxRate=" + taxRate + ", taxDescription=" + taxDescription + "]";
	}

	
	
}
