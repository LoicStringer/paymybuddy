package com.paymybuddy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.dao.FriendshipDAO;
import com.paymybuddy.entity.Friendship;

@Service
public class FriendshipService {

	@Autowired
	private FriendshipDAO friendshipDao;
	
	public Friendship addFriendship(Friendship friendshipToAdd) {
		return friendshipDao.save(friendshipToAdd);
	}
	
	
}
