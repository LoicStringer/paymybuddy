package com.paymybuddy.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.paymybuddy.dao.AccountDAO;
import com.paymybuddy.dao.OperationDAO;
import com.paymybuddy.dao.TaxDAO;
import com.paymybuddy.dao.TransferDAO;

@ExtendWith(MockitoExtension.class)
class TransferOperationServiceTest {

	@Mock
	private TransferDAO transferDao;
	
	@Mock
	private OperationDAO operationDao;
	
	@Mock
	private TaxDAO taxDao;
	
	@Mock
	private AccountDAO accountDao;
	
	@InjectMocks
	private TransferOperationService transferOperationService;
	
	

}
