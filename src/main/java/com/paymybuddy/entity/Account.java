package com.paymybuddy.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import org.hibernate.annotations.NaturalId;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

@SequenceGenerator(name="seq")
@Entity
@Table(name = "ACCOUNT")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
	@Column(name = "ACCOUNT_ID")
	private long accountId;

	@NotNull
	@Column(name = "ACCOUNT_NAME", length = 20, nullable = false)
	private String accountUserName;

	@NotNull
	@Column(name = "ACCOUNT_EMAIL", length = 50, unique = true, nullable = false)
	@Email(message="Invalid email")
	@NaturalId
	private String accountUserEmail;

	@NotNull
	@Column(name = "ACCOUNT_PASSWORD", length = 20, nullable = false)
	private String accountUserPassword;

	@Column(name = "ACCOUNT_BALANCE", columnDefinition = " double default 0")
	private double accountBalance;

	@JsonIgnore
	@OneToMany(mappedBy = "accountFrom",orphanRemoval = true,cascade = CascadeType.ALL)
	private List<Transfer> transfers = new ArrayList<Transfer>();

	@JsonIgnore
	@OneToMany(mappedBy = "myFriend", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<Friendship> friendships = new ArrayList<Friendship>();

	
	@JsonIgnore
	@OneToMany(mappedBy = "holderAccount",orphanRemoval = true, cascade = CascadeType.ALL)
	private List<Providing> providingsToBankAccount = new ArrayList<Providing>();
	
	
	@JsonIgnore
	@OneToMany(mappedBy = "holderAccount",orphanRemoval = true, cascade = CascadeType.ALL)
	private List<BankAccount> ownedBankAccounts = new ArrayList<BankAccount>();

	public Account() {
	}

	public void addFriendship(Friendship friendship) {
		this.friendships.add(friendship);
		friendship.setMyAccount(this);
	}
	
	public void addProviding(Providing providing) {
		this.providingsToBankAccount.add(providing);
		providing.setHolderAccount(this);
	}
		
	public void addBankAccount(BankAccount bankAccount) {
		this.ownedBankAccounts.add(bankAccount);
		bankAccount.setHolderAccount(this);
	}
	
	public void addTransfer(Transfer transfer) {
		this.transfers.add(transfer);
		transfer.setAccountFrom(this);
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

	public List<Transfer> getTransfers() {
		return transfers;
	}

	public void setTransfers(List<Transfer> transfers) {
		this.transfers = transfers;
	}

	public List<Friendship> getFriendships() {
		return friendships;
	}

	public void setFriendships(List<Friendship> friendships) {
		this.friendships = friendships;
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
		return Objects.hashCode(accountUserEmail);
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
		return Objects.equals(accountUserEmail, other.getAccountUserEmail());
	}

	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", accountUserName=" + accountUserName + ", accountUserEmail="
				+ accountUserEmail + ", accountUserPassword=" + accountUserPassword + ", accountBalance="
				+ accountBalance + ", transfers=" + transfers + ", friendships=" + friendships
				+ ", providingsToBankAccount=" + providingsToBankAccount + ", ownedBankAccounts=" + ownedBankAccounts
				+ "]";
	}

}
