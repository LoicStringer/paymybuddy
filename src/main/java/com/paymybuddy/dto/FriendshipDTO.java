package com.paymybuddy.dto;

import org.springframework.stereotype.Component;

@Component
public class FriendshipDTO {

	private long myAccountId;
	private String friendEmail;
	
	public FriendshipDTO() {
	}

	public long getMyAccountId() {
		return myAccountId;
	}

	public void setMyAccountId(long myAccountId) {
		this.myAccountId = myAccountId;
	}

	public String getFriendEmail() {
		return friendEmail;
	}

	public void setFriendEmail(String friendEmail) {
		this.friendEmail = friendEmail;
	}

}
