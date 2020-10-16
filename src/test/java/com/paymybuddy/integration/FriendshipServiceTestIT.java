package com.paymybuddy.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.paymybuddy.dto.FriendshipDTO;
import com.paymybuddy.entity.Account;
import com.paymybuddy.entity.Friendship;
import com.paymybuddy.exception.ResourceNotFoundException;
import com.paymybuddy.exception.UniqueConstraintViolationException;
import com.paymybuddy.service.AccountService;
import com.paymybuddy.service.FriendshipService;

@ActiveProfiles("test")
@SpringBootTest
@Transactional
class FriendshipServiceTestIT {

	@Autowired
	private FriendshipService friendshipService;
	
	@Autowired 
	private AccountService accountService;
	
	private static FriendshipDTO friendshipDto;
	
	private Account myAccount;
	
	@BeforeAll
	static void setUp() {
		
		friendshipDto = new FriendshipDTO();
		friendshipDto.setMyAccountId(1);
		friendshipDto.setFriendEmail("tony.montana@depalma.com");
	}
	
	@BeforeEach
	void setUpForTests() throws ResourceNotFoundException {
		
		myAccount = accountService.getAccount(1);
	}
	
	@Test
	void addAFriendshipTest() throws ResourceNotFoundException, UniqueConstraintViolationException {
		
		friendshipService.addFriendship(friendshipDto);
		
		List<Friendship> friendships = friendshipService.getMyFriendships(myAccount);
		
		assertEquals(friendships.get(0).getMyFriend(),accountService.getAccount(2));
	}
	
	@Test
	void getMyFriendshipsTest() throws ResourceNotFoundException, UniqueConstraintViolationException {
		
		friendshipService.addFriendship(friendshipDto);
		
		List<Friendship> friendships = friendshipService.getMyFriendships(myAccount);
		
		assertEquals(friendships.get(0).getMyFriend().getAccountUserName(),"Montana");
	}
	
	@Test
	void isUniqueConstraintExceptionThrownWhenAddingAAlreadyRegisteredFriendshipTest() throws ResourceNotFoundException, UniqueConstraintViolationException{
		
		friendshipService.addFriendship(friendshipDto);
		
		Exception exception = assertThrows(UniqueConstraintViolationException.class,
				()-> friendshipService.addFriendship(friendshipDto));
	
		assertEquals(exception.getMessage(),"This friendship already exists.");
	}
	
	@Test
	void isResourceNotFoundExceptionThrownWhenAddingUnknownUserFriendshipTest() {
		
		FriendshipDTO unknownFriendDto = new FriendshipDTO();
		unknownFriendDto.setMyAccountId(myAccount.getAccountId());
		unknownFriendDto.setFriendEmail("michael.corleone@coppola.com");
		
		Exception exception = assertThrows(ResourceNotFoundException.class, 
				()-> friendshipService.addFriendship(unknownFriendDto));
		
		assertEquals(exception.getMessage(), "Account not found.");
	}
		
		
}
