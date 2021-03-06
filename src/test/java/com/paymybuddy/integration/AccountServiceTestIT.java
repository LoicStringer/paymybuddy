package com.paymybuddy.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.paymybuddy.entity.Account;
import com.paymybuddy.exception.ResourceNotFoundException;
import com.paymybuddy.exception.UniqueConstraintViolationException;
import com.paymybuddy.exception.WrongPasswordException;
import com.paymybuddy.form.ConnectionForm;
import com.paymybuddy.service.AccountService;

@ActiveProfiles("test")
@SpringBootTest
@Transactional
class AccountServiceTestIT {
	
	@Autowired
	private AccountService accountService;
	
	private static Account accountToCreate;

	@BeforeAll
	static void setUpAccountToCreateForTest() {
		
		accountToCreate = new Account();
		accountToCreate.setAccountUserName("Serpico");
		accountToCreate.setAccountUserEmail("franck.serpico@lumet.com");
		accountToCreate.setAccountUserPassword("AlPacinoIsTheGOAT");
		
	}
	
	@Test
	void createAccountTest() throws UniqueConstraintViolationException, ResourceNotFoundException {
		
		 assertEquals(accountService.createAccount(accountToCreate).getAccountUserName(),"Serpico");
	}
	
	@Test
	void getAccountByIdTest() throws ResourceNotFoundException {
		
		assertEquals(accountService.getAccount(1).getAccountUserName(),"Brigante");
	}
	
	@Test
	void getAccountByEmailTest() throws ResourceNotFoundException {
		
		assertEquals(accountService.getAccountByEmail("carlito.brigante@depalma.com").getAccountUserName(),"Brigante");
	}
	
	@Test
	void connectToMyAccountTest() throws ResourceNotFoundException, WrongPasswordException {
		
		ConnectionForm connectionForm = new ConnectionForm();
		connectionForm.setEmail("carlito.brigante@depalma.com");
		connectionForm.setPassword("AlPacinoIsTheGOAT");
		
		Account myAccount = accountService.getAccountByEmail("carlito.brigante@depalma.com");
		
		assertEquals(accountService.connectToMyAccount(connectionForm),myAccount);
	}
	
	@Test
	void isUniqueConstraintViolationExceptionThrownWhenCrreatingAccountWithDuplicatedEmail() throws UniqueConstraintViolationException  {
		
		Exception exception = assertThrows(UniqueConstraintViolationException.class, 
				()-> accountService.createAccount(accountService.getAccount(1)));
		
		assertEquals(exception.getMessage(),"This email already exists.");
	}
	
	@Test
	void isResourceNotFoundExceptionThrownWhenTryingToGetUnknownResource() {
		
		Exception exception = assertThrows(ResourceNotFoundException.class, 
				()-> accountService.getAccount(100));
		
		assertEquals(exception.getMessage(),"Account not found.");
	}
	
	@Test
	void isWrongPasswordExceptionThrownWhenConnectingWithWrongPasswordTest() {
		
		ConnectionForm connectionForm = new ConnectionForm();
		connectionForm.setEmail("carlito.brigante@depalma.com");
		connectionForm.setPassword("wrongPassword");
		
		Exception exception = assertThrows(WrongPasswordException.class,
				()-> accountService.connectToMyAccount(connectionForm));
		
		assertEquals(exception.getMessage(), "Wrong password.");
	}
}
