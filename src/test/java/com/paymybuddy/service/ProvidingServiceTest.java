package com.paymybuddy.service;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.paymybuddy.dao.ProvidingDAO;
import com.paymybuddy.entity.Account;
import com.paymybuddy.entity.BankAccount;
import com.paymybuddy.entity.Providing;

@ExtendWith(MockitoExtension.class)
class ProvidingServiceTest {

	@InjectMocks
	private ProvidingService providingService;
	
	@Mock
	private ProvidingDAO providingDao;
	
	private static Providing providing;
	private static Account account;
	private static BankAccount bankAccount;
	
	@BeforeAll
	static void setUp() {
		
		account = new Account();
		account.setAccountId(1);
		account.setProvidingsToBankAccount(null);
		
		bankAccount = new BankAccount();
		bankAccount.setBankAccountId(1);
		bankAccount.setProvidingsToAccount(null);
		
		providing = new Providing();
		providing.setHolderAccount(account);
		providing.setBankAccount(bankAccount);
		
	}
	
	@Test
	void getProvidingsByAccountHolderTest() {
		
		List<Providing> providings = new ArrayList<Providing>();
		providings.add(providing);
		
		when(providingDao.findByHolderAccount(account)).thenReturn(providings);
		
		assertEquals(providingService.getProvidingsByAccount(account).get(0),providing);
	}
	
	@Test
	void getProvidingsByBankAccountTest() {
		
		List<Providing> providings = new ArrayList<Providing>();
		providings.add(providing);
		
		when(providingDao.findByBankAccount(bankAccount)).thenReturn(providings);
		
		assertEquals(providingService.getProvidingsByBankAccount(bankAccount).get(0),providing);
	}
	

}
