package com.paymybuddy.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.paymybuddy.dao.FriendshipDAO;
import com.paymybuddy.entity.Account;
import com.paymybuddy.entity.Friendship;

@ExtendWith(MockitoExtension.class)
class FriendshipServiceTest {

	@InjectMocks
	private FriendshipService friendshipService;

	@Mock
	private FriendshipDAO friendshipDao;
	
	@Mock
	private AccountService accountservice;
	
	private static Account myAccount;
	private static Account friend;
	private static Account friendTwo;
	private static Friendship friendship;
	private static Friendship friendshipTwo;
	private static List<Friendship> friends;

	@BeforeAll
	static void setUp() {
		myAccount = new Account();
		friend = new Account();
		friendTwo = new Account();
		friendship = new Friendship();
		friendshipTwo = new Friendship();
		friends = new ArrayList<Friendship>();

		myAccount.setAccountId(1);
		friend.setAccountId(2);
		friendTwo.setAccountId(3);

		friendship.setMyAccount(myAccount);
		friendship.setFriendWith(friend);

		friendshipTwo.setMyAccount(myAccount);
		friendshipTwo.setFriendWith(friendTwo);

		friends.add(friendship);
		friends.add(friendshipTwo);
	}

	@Test
	void getFriendsTest() {
		
		when(friendshipDao.findByMyAccount(any(Account.class))).thenReturn(friends);
		
		assertEquals(friendshipService.getMyFriends(myAccount),friends);
	}

	@Test
	void addFriendTest() {
		
		when(friendshipDao.save(any(Friendship.class))).thenReturn(friendship);
		
		assertEquals(friendshipService.addFriendship(friendship), friendship);
	}
	
	
}
