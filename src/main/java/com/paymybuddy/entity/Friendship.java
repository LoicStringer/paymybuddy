package com.paymybuddy.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "FRIENDSHIP")
@IdClass(Friendship.FriendshipPK.class)
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
			result = prime * result + getEnclosingInstance().hashCode();
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
			if (!getEnclosingInstance().equals(other.getEnclosingInstance()))
				return false;
			if (friendOf != other.friendOf)
				return false;
			if (friendWith != other.friendWith)
				return false;
			return true;
		}

		private Friendship getEnclosingInstance() {
			return Friendship.this;
		}

	}

}
