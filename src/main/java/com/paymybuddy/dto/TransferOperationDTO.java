package com.paymybuddy.dto;

import org.springframework.stereotype.Component;

import com.paymybuddy.entity.Operation;
import com.paymybuddy.entity.Tax;
import com.paymybuddy.entity.Transfer;

@Component
public class TransferOperationDTO {

	private long accountFromId;
	private long accountToId;
	private double amount;
	private String transferDescription;
	private int taxApplied;
	
	public TransferOperationDTO() {
	}

	public TransferOperationDTO(long accountFromId, long accountToId, double amount, String transferDescription,
			int taxApplied) {
		super();
		this.accountFromId = accountFromId;
		this.accountToId = accountToId;
		this.amount = amount;
		this.transferDescription = transferDescription;
		this.taxApplied = taxApplied;
	}

	public long getAccountFromId() {
		return accountFromId;
	}

	public void setAccountFromId(long accountFromId) {
		this.accountFromId = accountFromId;
	}

	public long getAccountToId() {
		return accountToId;
	}

	public void setAccountToId(long accountToId) {
		this.accountToId = accountToId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getTransferDescription() {
		return transferDescription;
	}

	public void setTransferDescription(String transferDescription) {
		this.transferDescription = transferDescription;
	}

	public int getTaxApplied() {
		return taxApplied;
	}

	public void setTaxApplied(int taxApplied) {
		this.taxApplied = taxApplied;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (accountFromId ^ (accountFromId >>> 32));
		result = prime * result + (int) (accountToId ^ (accountToId >>> 32));
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + taxApplied;
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
		TransferOperationDTO other = (TransferOperationDTO) obj;
		if (accountFromId != other.accountFromId)
			return false;
		if (accountToId != other.accountToId)
			return false;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (taxApplied != other.taxApplied)
			return false;
		if (transferDescription == null) {
			if (other.transferDescription != null)
				return false;
		} else if (!transferDescription.equals(other.transferDescription))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TransferOperationDTO [accountFromId=" + accountFromId + ", accountToId=" + accountToId + ", amount="
				+ amount + ", transferDescription=" + transferDescription + ", taxApplied=" + taxApplied + "]";
	}

		
}
