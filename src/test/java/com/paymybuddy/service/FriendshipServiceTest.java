package com.paymybuddy.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.paymybuddy.dao.FriendshipDAO;
import com.paymybuddy.dto.FriendshipDTO;
import com.paymybuddy.entity.Account;
import com.paymybuddy.entity.Friendship;
import com.paymybuddy.entity.FriendshipPK;
import com.paymybuddy.exception.ResourceNotFoundException;
import com.paymybuddy.exception.UniqueConstraintViolationException;
import com.paymybuddy.form.FriendshipForm;

@ExtendWith(MockitoExtension.class)
class FriendshipServiceTest {

	@InjectMocks
	private FriendshipService friendshipService;

	@Mock
	private FriendshipDAO friendshipDao;

	@Mock
	private AccountService accountService;

	private static FriendshipForm friendshipForm;
	private static Account myAccount;
	private static Account myFriendAccount;

	private FriendshipDTO friendshipDto;
	private Friendship friendship;

	@BeforeAll
	static void setUp() {
		myAccount = new Account();
		myAccount.setAccountId(1);
		myAccount.setAccountUserName("Serpico");

		myFriendAccount = new Account();
		myFriendAccount.setAccountId(2);
		myFriendAccount.setAccountUserEmail("tony.montana@depalma.com");
		myFriendAccount.setAccountUserName("Montana");

		friendshipForm = new FriendshipForm();
		friendshipForm.setMyAccountId(1);
		friendshipForm.setFriendEmail(myFriendAccount.getAccountUserEmail());
	}

	@BeforeEach
	void setUpForTests() {

		friendshipDto = friendshipService.convertFriendshipFormToFriendshipDto(friendshipForm);

		friendship = new Friendship();
		friendship.setMyAccount(myAccount);
		friendship.setMyFriend(myFriendAccount);
	}

	@Nested
	@Tag("NominalCasesTests")
	@DisplayName("Nominal cases checking")
	class NominalCasesTests {

		@Test
		void convertFormToDtoTest() {

			assertEquals(friendshipDto.getFriendEmail(), friendshipForm.getFriendEmail());
		}

		@Test
		void getFriendshipsTest() {

			List<Friendship> myFriendships = new ArrayList<Friendship>();
			myFriendships.add(friendship);

			when(friendshipDao.findByMyAccount(myAccount)).thenReturn(myFriendships);

			assertEquals(friendshipService.getMyFriendships(myAccount).get(0), friendship);
			
		}

		@Test
		void addFriendTest() throws ResourceNotFoundException, UniqueConstraintViolationException {

			when(accountService.getAccount(1)).thenReturn(myAccount);
			when(accountService.getAccountByEmail("tony.montana@depalma.com")).thenReturn(myFriendAccount);

			assertEquals(friendshipService.addFriendship(friendshipDto), friendship);
			assertEquals(myAccount.getFriendships().get(0), friendship);
		}
	}
	
	@Nested
	@Tag("ExceptionsTests")
	@DisplayName("Exceptions Checking")
	class ExceptionsTests {
		
		@Test
		void isExpectedExceptionThrownWhenFriendAccountNotFoundTest() throws ResourceNotFoundException {
			
			when(accountService.getAccountByEmail(any(String.class))).thenThrow(ResourceNotFoundException.class);
			
			assertThrows(ResourceNotFoundException.class,()-> friendshipService.addFriendship(friendshipDto));		
		}
		
		@Test
		void isExpectedExceptionThrownWhenAddingAlreadyRegisteredFriendshipTest() throws ResourceNotFoundException {
			
			when(friendshipDao.findById(any(FriendshipPK.class))).thenReturn(Optional.of(friendship));

			when(accountService.getAccount(1)).thenReturn(myAccount);
			when(accountService.getAccountByEmail("tony.montana@depalma.com")).thenReturn(myFriendAccount);
			
			assertThrows(UniqueConstraintViolationException.class, ()-> friendshipService.addFriendship(friendshipDto));
		}
	}
}
