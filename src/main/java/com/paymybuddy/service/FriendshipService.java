package com.paymybuddy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.dao.FriendshipDAO;
import com.paymybuddy.dto.FriendshipDTO;
import com.paymybuddy.entity.Account;
import com.paymybuddy.entity.Friendship;
import com.paymybuddy.exception.ResourceNotFoundException;
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
	
	public List<Friendship> getMyFriends(Account myAccount){
		
		List<Friendship> myFriends = friendshipDao.findByMyAccount(myAccount);
		
		return myFriends;
	}
	
	public Friendship buildFriendship (FriendshipDTO friendshipDto) throws ResourceNotFoundException {
		
		Friendship buildedFriendship = new Friendship();
		
		buildedFriendship.setMyAccount(accountService.getAccount(friendshipDto.getMyAccountId()));
		buildedFriendship.setFriendWith(accountService.getAccountByEmail(friendshipDto.getFriendEmail()));
		
		return buildedFriendship;
	}
	
	public FriendshipDTO convertFriendshipFormToFriendshipDto(FriendshipForm friendshipForm) {
		
		FriendshipDTO friendshipDto = new FriendshipDTO();
		
		friendshipDto.setMyAccountId(friendshipForm.getMyAccountId());
		friendshipDto.setFriendEmail(friendshipForm.getFriendEmail());
		
		return friendshipDto;
	}
	
}
