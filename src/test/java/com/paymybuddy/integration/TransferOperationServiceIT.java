package com.paymybuddy.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.paymybuddy.dto.TransferOperationDTO;
import com.paymybuddy.entity.Operation;
import com.paymybuddy.entity.Transfer;
import com.paymybuddy.exception.InsufficientBalanceException;
import com.paymybuddy.exception.NegativeAmountException;
import com.paymybuddy.exception.ResourceNotFoundException;
import com.paymybuddy.form.TransferOperationForm;
import com.paymybuddy.service.AccountService;
import com.paymybuddy.service.OperationService;
import com.paymybuddy.service.TransferOperationService;
import com.paymybuddy.service.TransferService;

@ActiveProfiles("test")
@SpringBootTest
@Transactional
class TransferOperationServiceIT {

	@Autowired
	private TransferOperationService transferOperationService;

	@Autowired
	private AccountService accountService;

	@Autowired
	private OperationService operationService;
	
	@Autowired
	private TransferService TransferService;
	
	private TransferOperationDTO dto;
	private TransferOperationForm form;

	@BeforeEach
	void setUp() {

		form = new TransferOperationForm();
		form.setAccountFromId(1);
		form.setAccountToId(2);
		form.setAmount(100);
		form.setTaxApplied(1);
		form.setTransferDescription("Loan");
	}

	@Test
	void convertFormToDtoTest() {
		
		dto = transferOperationService.convertTransferOperatioFormToTransferOperatioDto(form);
		
		assertEquals(form.getTransferDescription(),dto.getTransferDescription());
	}
	
	@Test
	void transferOperationTest()
			throws InsufficientBalanceException, ResourceNotFoundException, NegativeAmountException {

		dto = transferOperationService.convertTransferOperatioFormToTransferOperatioDto(form);
		transferOperationService.processTransferOperation(dto);

		List<Transfer> transfers = TransferService.getTransfersByAccount(accountService.getAccount(1));
		Operation operation = operationService.getOperation(transfers.get(0).getTransferOperationId().getOperationId());
		
		assertEquals(operation.getOperationAmount(),100);
		assertEquals(transfers.get(0).getAccountTo().getAccountUserName(),"Montana");
		assertEquals(accountService.getAccount(1).getAccountBalance(), 400);
		assertEquals(accountService.getAccount(2).getAccountBalance(), 600);
		assertEquals(accountService.getAccount(1).getTransfers().get(0).getTransferDescription(),"Loan");
	}

	@Test
	void isExpectedExceptionThrownWhenProcessingWithNegativeAmountTest() {

		form.setAmount(-100);

		dto = transferOperationService.convertTransferOperatioFormToTransferOperatioDto(form);

		Exception exception = assertThrows(NegativeAmountException.class,
				() -> transferOperationService.processTransferOperation(dto));

		assertEquals(exception.getMessage(), "Amount can't be negative!");
	}

	@Test
	void isExpectedExceptionThrownWhenBalanceIsUnsifficientTest() {

		form.setAmount(1000);

		dto = transferOperationService.convertTransferOperatioFormToTransferOperatioDto(form);

		Exception exception = assertThrows(InsufficientBalanceException.class,
				() -> transferOperationService.processTransferOperation(dto));

		assertEquals(exception.getMessage(), "Insufficient balance");
	}
	
	@Test
	void isExpectedExceptionThrownWhenResourceNotFoundTest() {

		form.setAccountFromId(50);;

		dto = transferOperationService.convertTransferOperatioFormToTransferOperatioDto(form);

		Exception exception = assertThrows(ResourceNotFoundException.class,
				() -> transferOperationService.processTransferOperation(dto));

		assertEquals(exception.getMessage(), "Account not found.");
	}
}
