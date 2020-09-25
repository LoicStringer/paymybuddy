package com.paymybuddy.form;

import org.springframework.stereotype.Component;

@Component
public class FriendshipForm {

	private long myAccountId;
	private String friendEmail;
	
	public FriendshipForm() {
	}

	public FriendshipForm(long myAccountId, String friendEmail) {
		super();
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((friendEmail == null) ? 0 : friendEmail.hashCode());
		result = prime * result + (int) (myAccountId ^ (myAccountId >>> 32));
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
		FriendshipForm other = (FriendshipForm) obj;
		if (friendEmail == null) {
			if (other.friendEmail != null)
				return false;
		} else if (!friendEmail.equals(other.friendEmail))
			return false;
		if (myAccountId != other.myAccountId)
			return false;
		return true;
	}

	
}
