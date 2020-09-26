package com.paymybuddy.entity;

import javax.persistence.Entity;
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
	@ManyToOne
	@JoinColumn(name = "FRIENDSHIP_ACCOUNT_ID")
	private Account friendWith;

	@Id
	@ManyToOne
	@JoinColumn(name = "FRIENDSHIP_FRIEND_ACCOUNT_TO")
	private Account friendOf;

	public Friendship() {
	}

	public Friendship(Account friendWith, Account friendOf) {
		super();
		this.friendWith = friendWith;
		this.friendOf = friendOf;
	}

	public Account getFriendWith() {
		return friendWith;
	}

	public void setFriendWith(Account friendWith) {
		this.friendWith = friendWith;
	}

	public Account getFriendOf() {
		return friendOf;
	}

	public void setFriendOf(Account friendOf) {
		this.friendOf = friendOf;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((friendOf == null) ? 0 : friendOf.hashCode());
		result = prime * result + ((friendWith == null) ? 0 : friendWith.hashCode());
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
		return true;
	}

	

}
