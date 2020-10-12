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
	private Account myAccount;

	@Id
	@ManyToOne
	@JoinColumn(name = "FRIENDSHIP_FRIEND_ACCOUNT_ID")
	private Account friendWith;

	public Friendship() {
	}

	public Friendship(Account myAccount, Account friendWith) {
		super();
		this.myAccount = myAccount;
		this.friendWith = friendWith;
	}

	public Account getMyAccount() {
		return myAccount;
	}

	public void setMyAccount(Account myAccount) {
		this.myAccount = myAccount;
	}

	public Account getFriendWith() {
		return friendWith;
	}

	public void setFriendWith(Account friendWith) {
		this.friendWith = friendWith;
	}

	

}
