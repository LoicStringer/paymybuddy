package com.paymybuddy.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.paymybuddy.dao.OperationDAO;
import com.paymybuddy.entity.Operation;
import com.paymybuddy.entity.Tax;
import com.paymybuddy.exception.NegativeAmountException;

@ExtendWith(MockitoExtension.class)
class OperationServiceTest {
	
	@InjectMocks
	private OperationService operationService;

	@Mock
	private OperationDAO operationDao;
	
	private static Tax tax;
	private static Operation operation;
	
	@BeforeAll
	static void setUp() {
		tax = new Tax(1, 0.5, "Huge tax");
		
		operation = new Operation();
		operation.setOperationId(1);
		operation.setOperationDate(Instant.now());
		operation.setOperationAmount(500);
	}
	
	@Test
	void getOperationTest() {
		
		when(operationDao.findById((long) 1)).thenReturn(Optional.of(operation));
		
		assertEquals(operationService.getOperation(1),operation);
	}
	
	@Test
	void saveOperationtest() {
		
		when(operationDao.save(any(Operation.class))).thenReturn(operation);
		
		assertEquals(operationService.saveOperation(operation).getOperationAmount(),500);
	}
	
	@Test
	void calculateFeeTest() throws NegativeAmountException {
		
		assertEquals(operationService.calculateOperationFee(tax, 10),5);
	}

	@Test
	void expectedExceptionIsThrownWhenCalculatingFeeWithNegativeAmount() {
		
		assertThrows(NegativeAmountException.class,()-> operationService.calculateOperationFee(tax,-10));
	}
	
}
