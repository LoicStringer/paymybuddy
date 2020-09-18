package com.paymybuddy.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "USER_ACCOUNT")
public class UserAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ACCOUNT_ID")
	private long userAccountId;

	@Column(name = "USER_ACCOUNT_BALANCE")
	private double balance;

	@ManyToOne
	@JoinColumn(name = "USER_ACCOUNT_OWNER")
	private UserProfile userProfile;

	@OneToMany(mappedBy="accountFrom")
	private List<Transfer> transfersFrom;
	
	@OneToMany(mappedBy="accountTo")
	private List<Transfer> transfersTo;
	
	
	public UserAccount() {
		super();
		// TODO Auto-generated constructor stub
	}


	public UserAccount(long userAccountId, double balance, UserProfile userProfile, List<Transfer> transfersFrom,
			List<Transfer> transfersTo) {
		super();
		this.userAccountId = userAccountId;
		this.balance = balance;
		this.userProfile = userProfile;
		this.transfersFrom = transfersFrom;
		this.transfersTo = transfersTo;
	}


	public long getUserAccountId() {
		return userAccountId;
	}


	public void setUserAccountId(long userAccountid) {
		this.userAccountId = userAccountid;
	}


	public double getBalance() {
		return balance;
	}


	public void setBalance(double balance) {
		this.balance = balance;
	}


	public UserProfile getUserProfile() {
		return userProfile;
	}


	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
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
		long temp;
		temp = Double.doubleToLongBits(balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((transfersFrom == null) ? 0 : transfersFrom.hashCode());
		result = prime * result + ((transfersTo == null) ? 0 : transfersTo.hashCode());
		result = prime * result + (int) (userAccountId ^ (userAccountId >>> 32));
		result = prime * result + ((userProfile == null) ? 0 : userProfile.hashCode());
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
		UserAccount other = (UserAccount) obj;
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
		if (userAccountId != other.userAccountId)
			return false;
		if (userProfile == null) {
			if (other.userProfile != null)
				return false;
		} else if (!userProfile.equals(other.userProfile))
			return false;
		return true;
	}

	
		
}
