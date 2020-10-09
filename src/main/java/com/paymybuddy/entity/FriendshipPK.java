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
	private long friendWith;

	public FriendshipPK() {
	}

	public FriendshipPK(long myAccount, long friendWith) {
		super();
		this.myAccount = myAccount;
		this.friendWith = friendWith;
	}

	public long getMyAccount() {
		return myAccount;
	}

	public void setMyAccount(long myAccount) {
		this.myAccount = myAccount;
	}

	public long getFriendWith() {
		return friendWith;
	}

	public void setFriendWith(long friendWith) {
		this.friendWith = friendWith;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (friendWith ^ (friendWith >>> 32));
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
		if (friendWith != other.friendWith)
			return false;
		if (myAccount != other.myAccount)
			return false;
		return true;
	}

	
	

}
