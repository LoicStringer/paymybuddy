package com.paymybuddy.dto;

import org.springframework.stereotype.Component;

/**
 * <p>This class is used to manipulate data related to the {@link Friendship} entity 
 * between front end and database.</p> 
 * @author newbie
 *@see FriendshipForm
 */
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
