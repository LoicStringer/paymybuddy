package com.paymybuddy.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "FRIENDSHIP")
@IdClass(FriendshipPK.class)
public class Friendship {

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FRIENDSHIP_ACCOUNT_ID")
	private Account myAccount;

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FRIENDSHIP_FRIEND_ACCOUNT_ID")
	private Account myFriend;

	public Friendship() {
	}

	public Account getMyAccount() {
		return myAccount;
	}

	public void setMyAccount(Account myAccount) {
		this.myAccount = myAccount;
	}

	public Account getMyFriend() {
		return myFriend;
	}

	public void setMyFriend(Account myFriend) {
		this.myFriend = myFriend;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((myAccount == null) ? 0 : myAccount.hashCode());
		result = prime * result + ((myFriend == null) ? 0 : myFriend.hashCode());
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
		Friendship other = (Friendship) obj;
		if (myAccount == null) {
			if (other.myAccount != null)
				return false;
		} else if (!myAccount.equals(other.myAccount))
			return false;
		if (myFriend == null) {
			if (other.myFriend != null)
				return false;
		} else if (!myFriend.equals(other.myFriend))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Friendship [myAccount=" + myAccount.getAccountId() + ", myFriend=" + myFriend.getAccountId() + "]";
	}

	
}
