package com.paymybuddy.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ACCOUNT")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ACCOUNT_ID")
	private long accountId;

	@Column(name = "ACCOUNT_NAME", length = 20, nullable = false)
	private String accountUserName;

	@Column(name = "ACCOUNT_EMAIL", length = 50, nullable = false, unique = true)
	private String accountUserEmail;

	@Column(name = "ACCOUNT_PASSWORD", length = 20, nullable = false)
	private String accountUserPassword;

	@Column(name = "ACCOUNT_BALANCE", nullable = false, columnDefinition = " double default 0")
	private double accountBalance;

	@OneToMany(mappedBy = "accountFrom")
	private List<Transfer> transfersFrom;

	@OneToMany(mappedBy = "accountTo")
	private List<Transfer> transfersTo;

	@OneToMany(mappedBy = "friendWith")
	private List<Friendship> friendWith;

	@OneToMany(mappedBy = "friendOf",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Friendship> friendOf;

	@OneToMany(mappedBy = "holderAccountId",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Providing> providingsToBankAccount;

	@OneToMany(mappedBy = "accountHolderId",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<BankAccount> ownedBankAccounts;

	public Account() {
	}

	public Account(long accountId, String accountUserName, String accountUserEmail, String accountUserPassword,
			double accountBalance, List<Transfer> transfersFrom, List<Transfer> transfersTo,
			List<Friendship> friendWith, List<Friendship> friendOf, List<Providing> providingsToBankAccount,
			List<BankAccount> ownedBankAccounts) {
		super();
		this.accountId = accountId;
		this.accountUserName = accountUserName;
		this.accountUserEmail = accountUserEmail;
		this.accountUserPassword = accountUserPassword;
		this.accountBalance = accountBalance;
		this.transfersFrom = transfersFrom;
		this.transfersTo = transfersTo;
		this.friendWith = friendWith;
		this.friendOf = friendOf;
		this.providingsToBankAccount = providingsToBankAccount;
		this.ownedBankAccounts = ownedBankAccounts;
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

	public double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
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

	public List<Friendship> getFriendWith() {
		return friendWith;
	}

	public void setFriendWith(List<Friendship> friendWith) {
		this.friendWith = friendWith;
	}

	public List<Friendship> getFriendOf() {
		return friendOf;
	}

	public void setFriendOf(List<Friendship> friendOf) {
		this.friendOf = friendOf;
	}

	public List<Providing> getProvidingsToBankAccount() {
		return providingsToBankAccount;
	}

	public void setProvidingsToBankAccount(List<Providing> providingsToBankAccount) {
		this.providingsToBankAccount = providingsToBankAccount;
	}

	public List<BankAccount> getOwnedBankAccounts() {
		return ownedBankAccounts;
	}

	public void setOwnedBankAccounts(List<BankAccount> ownedBankAccounts) {
		this.ownedBankAccounts = ownedBankAccounts;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(accountBalance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (int) (accountId ^ (accountId >>> 32));
		result = prime * result + ((accountUserEmail == null) ? 0 : accountUserEmail.hashCode());
		result = prime * result + ((accountUserName == null) ? 0 : accountUserName.hashCode());
		result = prime * result + ((accountUserPassword == null) ? 0 : accountUserPassword.hashCode());
		result = prime * result + ((friendOf == null) ? 0 : friendOf.hashCode());
		result = prime * result + ((friendWith == null) ? 0 : friendWith.hashCode());
		result = prime * result + ((ownedBankAccounts == null) ? 0 : ownedBankAccounts.hashCode());
		result = prime * result + ((providingsToBankAccount == null) ? 0 : providingsToBankAccount.hashCode());
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
		if (Double.doubleToLongBits(accountBalance) != Double.doubleToLongBits(other.accountBalance))
			return false;
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
		if (friendOf == null) {
			if (other.friendOf != null)
				return false;
		} else if (!friendOf.equals(other.friendOf))
			return false;
		if (friendWith == null) {
			if (other.friendWith != null)
				return false;
		} else if (!friendWith.equals(other.friendWith))
			return false;
		if (ownedBankAccounts == null) {
			if (other.ownedBankAccounts != null)
				return false;
		} else if (!ownedBankAccounts.equals(other.ownedBankAccounts))
			return false;
		if (providingsToBankAccount == null) {
			if (other.providingsToBankAccount != null)
				return false;
		} else if (!providingsToBankAccount.equals(other.providingsToBankAccount))
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
