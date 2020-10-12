package com.paymybuddy.service;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
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
import com.paymybuddy.entity.Providing;

@ExtendWith(MockitoExtension.class)
class ProvidingServiceTest {

	@InjectMocks
	private ProvidingService providingService;
	
	@Mock
	private ProvidingDAO providingDao;
	
	private static Providing providing;
	private static Account account;
	
	@BeforeAll
	static void setUp() {
		
		account = new Account();
		account.setAccountId(1);
		
		providing = new Providing();
		providing.setHolderAccountId(account);
	}
	
	@Test
	void getProvidingsByAccountHolderTest() {
		
		List<Providing> providings = new ArrayList<Providing>();
		providings.add(providing);
		
		when(providingDao.findByHolderAccountId(account)).thenReturn(providings);
		
		assertEquals(providingService.getProvidingsByAccount(account).get(0),providing);
	}
	
	@Test
	void saveProvidingTest() {
		
		when(providingDao.save(any(Providing.class))).thenReturn(providing);
		
		assertEquals(providingService.saveProviding(providing).getHolderAccountId().getAccountId(), 1);
	}

}
