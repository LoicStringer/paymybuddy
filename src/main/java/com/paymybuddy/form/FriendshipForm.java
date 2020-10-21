package com.paymybuddy.form;

import javax.validation.constraints.Email;

import org.springframework.stereotype.Component;

/**
 * <p>Test purpose class consisting in a simple form collecting {@link Friendship} data 
 * from front end in order to be persisted. To be improved with a front end collaboration.</p>
 * @author newbie
 *@see FriendshipDTO
 */
@Component
public class FriendshipForm {

	private long myAccountId;
	
	@Email(message="Invalid email")
	private String friendEmail;
	
	public FriendshipForm() {
	}

	public FriendshipForm(long myAccountId, String friendEmail) {
		this.myAccountId = myAccountId;
		this.friendEmail = friendEmail;
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
