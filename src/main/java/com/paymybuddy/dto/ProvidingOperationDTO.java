package com.paymybuddy.dto;

import org.springframework.stereotype.Component;

@Component
public class ProvidingOperationDTO {

	private long accountId;
	private long bankAccountId;
	private double amount;
	private int taxApplied;
	private String providingType;
	
	public ProvidingOperationDTO() {
	}

	public ProvidingOperationDTO(long accountId, long bankAccountId, double amount, int taxApplied,
			String providingType) {
		super();
		this.accountId = accountId;
		this.bankAccountId = bankAccountId;
		this.amount = amount;
		this.taxApplied = taxApplied;
		this.providingType = providingType;
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public long getBankAccountId() {
		return bankAccountId;
	}

	public void setBankAccountId(long bankAccountId) {
		this.bankAccountId = bankAccountId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getTaxApplied() {
		return taxApplied;
	}

	public void setTaxApplied(int taxApplied) {
		this.taxApplied = taxApplied;
	}

	public String getProvidingType() {
		return providingType;
	}

	public void setProvidingType(String providingType) {
		this.providingType = providingType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (accountId ^ (accountId >>> 32));
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (int) (bankAccountId ^ (bankAccountId >>> 32));
		result = prime * result + ((providingType == null) ? 0 : providingType.hashCode());
		result = prime * result + taxApplied;
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
		ProvidingOperationDTO other = (ProvidingOperationDTO) obj;
		if (accountId != other.accountId)
			return false;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (bankAccountId != other.bankAccountId)
			return false;
		if (providingType == null) {
			if (other.providingType != null)
				return false;
		} else if (!providingType.equals(other.providingType))
			return false;
		if (taxApplied != other.taxApplied)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ProvidingOperationDTO [accountId=" + accountId + ", bankAccountId=" + bankAccountId + ", amount="
				+ amount + ", taxApplied=" + taxApplied + ", providingType=" + providingType + "]";
	}
	
	
	
}
