package com.paymybuddy.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.paymybuddy.dto.TransferOperationDTO;
import com.paymybuddy.entity.Account;
import com.paymybuddy.exception.InsufficientBalanceException;
import com.paymybuddy.exception.NegativeAmountException;
import com.paymybuddy.exception.ResourceNotFoundException;
import com.paymybuddy.form.TransferOperationForm;

@ExtendWith(MockitoExtension.class)
class TransferOperationServiceTest {

	@Mock
	private AccountService accountService;

	@Mock
	private OperationService operationService;

	@Mock
	private TransferService transferservice;

	@Mock
	private TaxService taxService;

	@InjectMocks
	private TransferOperationService transferOperationService;

	private Account accountFrom;
	private Account accountTo;
	private TransferOperationForm form;

	private TransferOperationDTO dto;

	@BeforeEach
	void setUp() {

		accountFrom = new Account();
		accountFrom.setAccountId(1);
		accountFrom.setAccountBalance(527.50);

		accountTo = new Account();
		accountTo.setAccountId(2);
		accountTo.setAccountBalance(284.75);

		form = new TransferOperationForm();
		form.setAccountFromId(1);
		form.setAccountToId(2);
		form.setAmount(122.20);
		form.setTaxApplied(1);
	}

	@Nested
	@Tag("NominalCasesTests")
	@DisplayName("Nominal cases checking")
	class NominalCasesTests {

		@Test
		void convertFormToDtoTest() {

			dto = transferOperationService.convertTransferOperatioFormToTransferOperatioDto(form);

			assertEquals(form.getAmount(), dto.getAmount());
		}

		@Test
		void transferOperationTest()
				throws InsufficientBalanceException, ResourceNotFoundException, NegativeAmountException {

			dto = transferOperationService.convertTransferOperatioFormToTransferOperatioDto(form);

			when(accountService.getAccount(1)).thenReturn(accountFrom);
			when(accountService.getAccount(2)).thenReturn(accountTo);
			doCallRealMethod().when(accountService).removeMoneyFromAccount(accountFrom, form.getAmount());
			doCallRealMethod().when(accountService).addMoneyToAccount(accountTo, form.getAmount());

			transferOperationService.processTransferOperation(dto);

			assertEquals(accountFrom.getAccountBalance(), (527.50 - 122.20));
			assertEquals(accountTo.getAccountBalance(), (284.75 + 122.20));
		}
	}

	@Nested
	@Tag("ExceptionsTests")
	@DisplayName("Exceptions Checking")
	class ExceptionsTests {

		@Test
		void isTransferOperationAbortedWhenTryingToProcessWithNegativeAmount()
				throws ResourceNotFoundException, InsufficientBalanceException, NegativeAmountException {

			form.setAmount(-100);

			dto = transferOperationService.convertTransferOperatioFormToTransferOperatioDto(form);

			when(accountService.getAccount(1)).thenReturn(accountFrom);
			doCallRealMethod().when(accountService).removeMoneyFromAccount(accountFrom, form.getAmount());

			assertThrows(NegativeAmountException.class, () -> transferOperationService.processTransferOperation(dto));
		}

		@Test
		void isTransferOperationAbortedWhenTryingToProcessWithInsufficientBalance()
				throws ResourceNotFoundException, InsufficientBalanceException, NegativeAmountException {

			form.setAmount(1000);

			dto = transferOperationService.convertTransferOperatioFormToTransferOperatioDto(form);

			when(accountService.getAccount(1)).thenReturn(accountFrom);
			doCallRealMethod().when(accountService).removeMoneyFromAccount(accountFrom, form.getAmount());

			assertThrows(InsufficientBalanceException.class,
					() -> transferOperationService.processTransferOperation(dto));
		}

		@Test
		void isTransferOperationAbortedWhenAccountResourceNotFound()
				throws ResourceNotFoundException, InsufficientBalanceException, NegativeAmountException {

			dto = transferOperationService.convertTransferOperatioFormToTransferOperatioDto(form);

			when(accountService.getAccount(1)).thenThrow(ResourceNotFoundException.class);

			assertThrows(ResourceNotFoundException.class, () -> transferOperationService.processTransferOperation(dto));
		}
	}
}
