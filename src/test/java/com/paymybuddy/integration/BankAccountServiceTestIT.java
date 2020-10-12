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
import com.paymybuddy.entity.BankAccount;
import com.paymybuddy.exception.ResourceNotFoundException;
import com.paymybuddy.exception.UniqueConstraintViolationException;
import com.paymybuddy.service.AccountService;
import com.paymybuddy.service.BankAccountService;

@ActiveProfiles("test")
@SpringBootTest
@Transactional
class BankAccountServiceTestIT {

	@Autowired
	private BankAccountService bankAccountService;
	
	@Autowired 
	private AccountService accountService;
	
	private static BankAccount bankAccount;
	private static Account accountHolder;
	
	@BeforeAll
	static void setUp() throws ResourceNotFoundException {
		
		bankAccount = new BankAccount();
		bankAccount.setAccountHolderId(accountHolder);
		bankAccount.setBankAccountHolderName("Montana");
		bankAccount.setBankAccountIban("FR1420041010050500013082606");
	}
	
	@Test
	void createBankAccountTest() throws UniqueConstraintViolationException, ResourceNotFoundException {
		
		accountHolder = accountService.getAccount(2);
		bankAccount.setAccountHolderId(accountHolder);
		assertEquals(bankAccountService.saveBankAccount(bankAccount).getBankAccountHolderName(),"Montana");
	}

	@Test
	void getBankAccountByIdTest() throws ResourceNotFoundException {
		
		assertEquals(bankAccountService.getBankAccount(1).getBankAccountHolderName(),"Brigante");
	}
	
	@Test
	void getBankAccountByIbanTest() throws ResourceNotFoundException {
		
		assertEquals(bankAccountService.getBankAccountByIban("FR7611808009101234567890147").getBankAccountHolderName(),"Brigante");
	}
	
	@Test
	void isUniqueConstraintExceptionThrownWhenSavingBankAccountWithDuplicatedIban() {
		
		Exception exception = assertThrows(UniqueConstraintViolationException.class,
				()-> bankAccountService.saveBankAccount(bankAccountService.getBankAccount(1)));
	
		assertEquals(exception.getMessage(),"This bank account already exists.");
	}
	
	@Test
	void isResourceNotFoundExceptionThrownWhenTryingToGetUnknownResource() {
		
		Exception exception = assertThrows(ResourceNotFoundException.class, 
				()-> bankAccountService.getBankAccount(100));
		
		assertEquals(exception.getMessage(), "Bank account not found.");
	}
}
