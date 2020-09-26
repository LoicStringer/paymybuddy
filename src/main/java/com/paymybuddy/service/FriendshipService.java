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
	
	@Autowired
	private AccountService accountService;
	
	public Friendship addFriendship(Friendship friendshipToAdd) {
		return friendshipDao.save(friendshipToAdd);
	}
	
	public Friendship addFriendWithHisEmail(FriendshipForm friendshipForm) {
		Friendship addedFriendship = new Friendship();
		
		addedFriendship.setFriendOf(accountService.getAccount(friendshipForm.getMyAccountId()));
		addedFriendship.setFriendWith(accountService.getAccountByEmail(friendshipForm.getFriendEmail()));
		friendshipDao.save(addedFriendship);
		return addedFriendship;
	}
}
