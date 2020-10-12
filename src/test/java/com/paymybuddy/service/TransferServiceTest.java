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

import com.paymybuddy.dao.TransferDAO;
import com.paymybuddy.entity.Account;
import com.paymybuddy.entity.Transfer;

@ExtendWith(MockitoExtension.class)
class TransferServiceTest {

	@InjectMocks
	private TransferService transferService;
	
	@Mock
	private TransferDAO transferDao;
	
	private static Account account;
	private static Transfer transfer;
	
	@BeforeAll
	static void setUp() {
		
		account = new Account();
		account.setAccountId(1);
		
		transfer = new Transfer();
		transfer.setAccountFrom(account);
	}
	
	@Test
	void getTransfersByAccountTest() {
		
		List<Transfer> transfers = new ArrayList<Transfer>();
		transfers.add(transfer);
		
		when(transferDao.findByAccountFrom(account)).thenReturn(transfers);
		
		assertEquals(transferService.getTransfersByAccount(account).get(0),transfer);
	}
	
	
	@Test
	void saveTransferTest() {
		
		when(transferDao.save(any(Transfer.class))).thenReturn(transfer);
		
		assertEquals(transferService.saveTransfer(transfer).getAccountFrom(), account);
	}

}
