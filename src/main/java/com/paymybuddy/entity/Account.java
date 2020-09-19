package com.paymybuddy.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "USER_ACCOUNT")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ACCOUNT_ID")
	private long accountId;

	@Column(name = "ACCOUNT_USER_NAME", length = 20, nullable = false)
	private String accountUserName;

	@Column(name = "ACCOUNT_USER_EMAIL", length = 50, nullable = false, unique = true)
	private String accountUserEmail;

	@Column(name = "ACCOUNT_USER_PASSWORD", length = 20, nullable = false)
	private String accountUserPassword;

	@Column(name = "ACCOUNT_BALANCE", columnDefinition = "double default 0")
	private double balance;

	@OneToMany(mappedBy = "accountFrom")
	private List<Transfer> transfersFrom;

	@OneToMany(mappedBy = "accountTo")
	private List<Transfer> transfersTo;

	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Account(long accountId, String accountUserName, String accountUserEmail, String accountUserPassword,
			double balance, List<Transfer> transfersFrom, List<Transfer> transfersTo) {
		super();
		this.accountId = accountId;
		this.accountUserName = accountUserName;
		this.accountUserEmail = accountUserEmail;
		this.accountUserPassword = accountUserPassword;
		this.balance = balance;
		this.transfersFrom = transfersFrom;
		this.transfersTo = transfersTo;
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public String getAccountUserName() {
		return accountUserName;
	}

	public void setAccountUserName(String accountUserName) {
		this.accountUserName = accountUserName;
	}

	public String getAccountUserEmail() {
		return accountUserEmail;
	}

	public void setAccountUserEmail(String accountUserEmail) {
		this.accountUserEmail = accountUserEmail;
	}

	public String getAccountUserPassword() {
		return accountUserPassword;
	}

	public void setAccountUserPassword(String accountUserPassword) {
		this.accountUserPassword = accountUserPassword;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public List<Transfer> getTransfersFrom() {
		return transfersFrom;
	}

	public void setTransfersFrom(List<Transfer> transfersFrom) {
		this.transfersFrom = transfersFrom;
	}

	public List<Transfer> getTransfersTo() {
		return transfersTo;
	}

	public void setTransfersTo(List<Transfer> transfersTo) {
		this.transfersTo = transfersTo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (accountId ^ (accountId >>> 32));
		result = prime * result + ((accountUserEmail == null) ? 0 : accountUserEmail.hashCode());
		result = prime * result + ((accountUserName == null) ? 0 : accountUserName.hashCode());
		result = prime * result + ((accountUserPassword == null) ? 0 : accountUserPassword.hashCode());
		long temp;
		temp = Double.doubleToLongBits(balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((transfersFrom == null) ? 0 : transfersFrom.hashCode());
		result = prime * result + ((transfersTo == null) ? 0 : transfersTo.hashCode());
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
		Account other = (Account) obj;
		if (accountId != other.accountId)
			return false;
		if (accountUserEmail == null) {
			if (other.accountUserEmail != null)
				return false;
		} else if (!accountUserEmail.equals(other.accountUserEmail))
			return false;
		if (accountUserName == null) {
			if (other.accountUserName != null)
				return false;
		} else if (!accountUserName.equals(other.accountUserName))
			return false;
		if (accountUserPassword == null) {
			if (other.accountUserPassword != null)
				return false;
		} else if (!accountUserPassword.equals(other.accountUserPassword))
			return false;
		if (Double.doubleToLongBits(balance) != Double.doubleToLongBits(other.balance))
			return false;
		if (transfersFrom == null) {
			if (other.transfersFrom != null)
				return false;
		} else if (!transfersFrom.equals(other.transfersFrom))
			return false;
		if (transfersTo == null) {
			if (other.transfersTo != null)
				return false;
		} else if (!transfersTo.equals(other.transfersTo))
			return false;
		return true;
	}

}
