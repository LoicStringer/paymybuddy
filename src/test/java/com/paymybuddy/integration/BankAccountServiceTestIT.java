package com.paymybuddy.integration;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.paymybuddy.dto.BankAccountDTO;
import com.paymybuddy.entity.Account;
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
	
	private static BankAccountDTO bankAccountDto;
	private static Account accountHolder;
	
	
	@BeforeAll
	static void setUp() throws ResourceNotFoundException {
		
		bankAccountDto = new BankAccountDTO();
		bankAccountDto.setAccountHolderId(2);
		bankAccountDto.setBankAccountHolderName("Montana");
		bankAccountDto.setBankAccountIban("FR1420041010050500013082606");
	}
	
	@Test
	void addABankAccountTest() throws UniqueConstraintViolationException, ResourceNotFoundException {

		accountHolder = accountService.getAccount(2);
		bankAccountService.addABankAccount(bankAccountDto);
		
		assertEquals(accountHolder.getOwnedBankAccounts().get(0).getBankAccountIban(),"FR1420041010050500013082606");
		assertEquals(bankAccountService.getBankAccountByIban("FR1420041010050500013082606").getHolderAccount().getAccountUserEmail(),"tony.montana@depalma.com");
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
	void isUniqueConstraintExceptionThrownWhenSavingBankAccountWithDuplicatedIban() throws UniqueConstraintViolationException, ResourceNotFoundException {
		
		bankAccountService.addABankAccount(bankAccountDto);
		
		Exception exception = assertThrows(UniqueConstraintViolationException.class,
				()-> bankAccountService.addABankAccount(bankAccountDto));
	
		assertEquals(exception.getMessage(),"This bank account already exists.");
	}
	
	@Test
	void isResourceNotFoundExceptionThrownWhenTryingToGetUnknownResource() {
		
		Exception exception = assertThrows(ResourceNotFoundException.class, 
				()-> bankAccountService.getBankAccount(100));
		
		assertEquals(exception.getMessage(), "Bank account not found.");
	}
}
