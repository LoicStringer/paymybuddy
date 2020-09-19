package com.paymybuddy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TAX")
public class Tax {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TAX_ID")
	private int taxId;

	@Column(name = "TAX_RATE")
	private float taxRate;

	@Column(name = "TAX_DESCRIPTION")
	private String taxDescription;

	public Tax() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Tax(int taxId, float taxRate, String taxDescription) {
		super();
		this.taxId = taxId;
		this.taxRate = taxRate;
		this.taxDescription = taxDescription;
	}

	public int getTaxId() {
		return taxId;
	}

	public void setTaxId(int taxId) {
		this.taxId = taxId;
	}

	public float getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(float taxRate) {
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
		result = prime * result + Float.floatToIntBits(taxRate);
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
		if (Float.floatToIntBits(taxRate) != Float.floatToIntBits(other.taxRate))
			return false;
		return true;
	}

}
