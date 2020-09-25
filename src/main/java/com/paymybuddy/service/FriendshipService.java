package com.paymybuddy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.dao.FriendshipDAO;
import com.paymybuddy.entity.Friendship;
import com.paymybuddy.form.FriendshipForm;

@Service
public class FriendshipService {

	@Autowired
	private FriendshipDAO friendshipDao;
	
	
	public Friendship addFriendship(Friendship friendshipToAdd) {
		return friendshipDao.save(friendshipToAdd);
	}
	
	public Friendship addFriendWithHisEmail(FriendshipForm friendshipForm) {
		Friendship addedFriendship = new Friendship();
		AccountService accountService = new AccountService();
		addedFriendship.setFriendOf(accountService.getAccount(friendshipForm.getMyAccountId()));
		addedFriendship.setFriendWith(accountService.getAccountByEmail(friendshipForm.getFriendEmail()));
		friendshipDao.save(addedFriendship);
		return addedFriendship;
	}
}
