package com.paymybuddy.entity;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class FriendshipPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long friendWith;
	private long friendOf;

	public FriendshipPK() {
	}

	public FriendshipPK(long friendWith, long friendOf) {
		this.friendWith = friendWith;
		this.friendOf = friendOf;
	}

	public long getFriendWith() {
		return friendWith;
	}

	public void setFriendWith(long friendWith) {
		this.friendWith = friendWith;
	}

	public long getFriendOf() {
		return friendOf;
	}

	public void setFriendOf(long friendOf) {
		this.friendOf = friendOf;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (friendOf ^ (friendOf >>> 32));
		result = prime * result + (int) (friendWith ^ (friendWith >>> 32));
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
		if (friendOf != other.friendOf)
			return false;
		if (friendWith != other.friendWith)
			return false;
		return true;
	}

	
	

}
