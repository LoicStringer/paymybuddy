package com.paymybuddy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.dao.FriendshipDAO;
import com.paymybuddy.dto.FriendshipDTO;
import com.paymybuddy.entity.Account;
import com.paymybuddy.entity.Friendship;
import com.paymybuddy.entity.FriendshipPK;
import com.paymybuddy.exception.ResourceNotFoundException;
import com.paymybuddy.exception.UniqueConstraintViolationException;
import com.paymybuddy.form.FriendshipForm;

@Service
public class FriendshipService {

	@Autowired
	private FriendshipDAO friendshipDao;

	@Autowired
	private AccountService accountService;

	public Friendship addFriendship(FriendshipDTO friendshipDto) throws ResourceNotFoundException, UniqueConstraintViolationException {
		
		Friendship friendshipToAdd = buildFriendship(friendshipDto);
		
		checkForFriendshipPKUnicity(getFriendshipPk(friendshipToAdd));
		friendshipToAdd.getMyAccount().addFriendship(friendshipToAdd);
		accountService.updateAccount(friendshipToAdd.getMyAccount());
		return friendshipToAdd;
	}

	public List<Friendship> getMyFriendships(Account myAccount) {
		List<Friendship> friendships = friendshipDao.findByMyAccount(myAccount);
		return friendships;
	}

	public FriendshipDTO convertFriendshipFormToFriendshipDto(FriendshipForm friendshipForm) {

		FriendshipDTO friendshipDto = new FriendshipDTO();

		friendshipDto.setMyAccountId(friendshipForm.getMyAccountId());
		friendshipDto.setFriendEmail(friendshipForm.getFriendEmail());

		return friendshipDto;
	}

	private Friendship buildFriendship(FriendshipDTO friendshipDto) throws ResourceNotFoundException {

		Friendship buildedFriendship = new Friendship();

		buildedFriendship.setMyAccount(accountService.getAccount(friendshipDto.getMyAccountId()));
		buildedFriendship.setMyFriend(accountService.getAccountByEmail(friendshipDto.getFriendEmail()));

		return buildedFriendship;
	}
	
	private FriendshipPK getFriendshipPk (Friendship friendship) {
		
		FriendshipPK friendshipPkToGet = new FriendshipPK();
		friendshipPkToGet.setMyAccount(friendship.getMyAccount().getAccountId());
		friendshipPkToGet.setMyFriend(friendship.getMyFriend().getAccountId());
		return friendshipPkToGet;
	}
	
	private void checkForFriendshipPKUnicity(FriendshipPK friendshipPk) throws UniqueConstraintViolationException {
		Friendship friendshipToCheck = friendshipDao.findById(friendshipPk).orElse(null);
		if(friendshipToCheck != null)
			throw new UniqueConstraintViolationException("This friendship already exists.");
	}
}
