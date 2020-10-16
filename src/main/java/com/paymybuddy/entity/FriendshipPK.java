package com.paymybuddy.entity;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class FriendshipPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long myAccount ;
	private long myFriend;

	public FriendshipPK() {
	}

	public FriendshipPK(long myAccount, long myFriend) {
		super();
		this.myAccount = myAccount;
		this.myFriend = myFriend;
	}

	public long getMyAccount() {
		return myAccount;
	}

	public void setMyAccount(long myAccount) {
		this.myAccount = myAccount;
	}

	public long getMyFriend() {
		return myFriend;
	}

	public void setMyFriend(long myFriend) {
		this.myFriend = myFriend;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (myFriend ^ (myFriend >>> 32));
		result = prime * result + (int) (myAccount ^ (myAccount >>> 32));
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
		FriendshipPK other = (FriendshipPK) obj;
		if (myFriend != other.myFriend)
			return false;
		if (myAccount != other.myAccount)
			return false;
		return true;
	}
	
}
