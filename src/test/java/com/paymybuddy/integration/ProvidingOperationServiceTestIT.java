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

import com.paymybuddy.dto.ProvidingOperationDTO;
import com.paymybuddy.entity.Operation;
import com.paymybuddy.entity.Providing;
import com.paymybuddy.exception.BankProcessFailedException;
import com.paymybuddy.exception.InsufficientBalanceException;
import com.paymybuddy.exception.NegativeAmountException;
import com.paymybuddy.exception.ResourceNotFoundException;
import com.paymybuddy.form.ProvidingOperationForm;
import com.paymybuddy.service.AccountService;
import com.paymybuddy.service.BankAccountService;
import com.paymybuddy.service.OperationService;
import com.paymybuddy.service.ProvidingOperationService;
import com.paymybuddy.service.ProvidingService;

@ActiveProfiles("test")
@SpringBootTest
@Transactional
class ProvidingOperationServiceTestIT {

	@Autowired 
	private ProvidingOperationService providingOperationService;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private OperationService operationService;
	
	@Autowired
	private ProvidingService providingService;
	
	@Autowired
	private BankAccountService bankAccountService;
	
	private ProvidingOperationForm form;
	private ProvidingOperationDTO dto;
	
	@BeforeEach
	void setUp() {
		
		form = new ProvidingOperationForm();
		form.setAccountId(1);
		form.setBankAccountId(1);
		form.setAmount(100);
		form.setTaxApplied(1);
	}
	
	@Test
	void convertFormToDtoTest() {
		
		dto = providingOperationService.convertProvidingFormToProvidingOperationDto(form);
		
		assertEquals(form.getAmount(),dto.getAmount());
	}
	
	@Test
	void providingOperationFromAccountToBankAccountTest() throws InsufficientBalanceException, BankProcessFailedException, ResourceNotFoundException, NegativeAmountException {
		
		form.setProvidingType("ACCOUNTTOBANKACCOUNT");
		
		dto = providingOperationService.convertProvidingFormToProvidingOperationDto(form);
		providingOperationService.processProvidingOperation(dto);
		
		List<Providing> providings = providingService.getProvidingsByAccount(accountService.getAccount(1));
		Operation operation = operationService.getOperation(providings.get(0).getProvidingOperation().getOperationId());
		
		assertEquals(operation.getOperationAmount(),100);
		assertEquals(accountService.getAccount(1).getAccountBalance(),400);
		assertEquals(providings.get(0).getHolderAccount().getAccountUserName(),"Brigante");
		assertEquals(accountService.getAccount(1).getProvidingsToBankAccount().get(0).getProvidingType().toString(),"ACCOUNTTOBANKACCOUNT"); 	
	}

	@Test
	void providingOperationFromBankAccountToAccountTest() throws InsufficientBalanceException, BankProcessFailedException, ResourceNotFoundException, NegativeAmountException {
		
		form.setAccountId(2);
		form.setProvidingType("BANKACCOUNTTOACCOUNT");
		
		dto = providingOperationService.convertProvidingFormToProvidingOperationDto(form);
		providingOperationService.processProvidingOperation(dto);
		
		List<Providing> providings = providingService.getProvidingsByBankAccount(bankAccountService.getBankAccount(1));
		Operation operation = operationService.getOperation(providings.get(0).getProvidingOperation().getOperationId());
		
		assertEquals(operation.getOperationAmount(),100);
		assertEquals(accountService.getAccount(2).getAccountBalance(),600);
		assertEquals(providings.get(0).getHolderAccount().getAccountUserName(),"Montana");
		assertEquals(bankAccountService.getBankAccount(1).getProvidingsToAccount().get(0).getHolderAccount(),accountService.getAccount(2));
		
	}
	
	@Test
	void isExpectedExceptionThrownWhenBalanceIsUnsifficientTest() {
		
		form.setProvidingType("ACCOUNTTOBANKACCOUNT");
		form.setAmount(1000);
		
		dto = providingOperationService.convertProvidingFormToProvidingOperationDto(form);
		
		Exception exception = assertThrows(InsufficientBalanceException.class,()-> providingOperationService.processProvidingOperation(dto));
		
		assertEquals(exception.getMessage(), "Insufficient balance");
	}

	@Test
	void isExpectedExceptionThrownWhenProcessingWithNegativeAmountTest() {

		form.setProvidingType("ACCOUNTTOBANKACCOUNT");
		form.setAmount(-100);

		dto = providingOperationService.convertProvidingFormToProvidingOperationDto(form);

		Exception exception = assertThrows(NegativeAmountException.class,
				() -> providingOperationService.processProvidingOperation(dto));

		assertEquals(exception.getMessage(), "Amount can't be negative!");
	}
	
	@Test
	void isExpectedExceptionThrownWhenResourceNotFoundTest() {
		
		form.setProvidingType("ACCOUNTTOBANKACCOUNT");
		form.setAccountId(50);;

		dto = providingOperationService.convertProvidingFormToProvidingOperationDto(form);

		Exception exception = assertThrows(ResourceNotFoundException.class,
				() -> providingOperationService.processProvidingOperation(dto));

		assertEquals(exception.getMessage(), "Account not found.");
	}
	
	//TODO Implement tests to check exceptions around BankProcessFailedException related to external online payment process
	
}
