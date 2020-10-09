package com.paymybuddy.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doThrow;
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

import com.paymybuddy.dto.ProvidingOperationDTO;
import com.paymybuddy.entity.Account;
import com.paymybuddy.exception.BankProcessFailedException;
import com.paymybuddy.exception.InsufficientBalanceException;
import com.paymybuddy.exception.NegativeAmountException;
import com.paymybuddy.exception.ResourceNotFoundException;
import com.paymybuddy.form.ProvidingOperationForm;

@ExtendWith(MockitoExtension.class)
class ProvidingOperationServiceTest {

	@InjectMocks
	private ProvidingOperationService providingOperationService;

	@Mock
	private AccountService accountService;

	@Mock
	private TaxService taxService;

	@Mock
	private OperationService operationService;

	@Mock
	private BankAccountService bankAccountService;

	@Mock
	private TransferService transferservice;

	@Mock
	private ProvidingService providingService;

	private ProvidingOperationForm form;
	private Account account;

	private ProvidingOperationDTO dto;

	@BeforeEach
	void setUp() {

		form = new ProvidingOperationForm();
		form.setAccountId(1);
		form.setAmount(100);
		form.setBankAccountId(1);
		form.setTaxApplied(1);

		account = new Account();
		account.setAccountId(1);
		account.setAccountBalance(500);
	}

	@Nested
	@Tag("NominalCasesTests")
	@DisplayName("Nominal cases checking")
	class NominalCasesTests {

		@Test
		void convertFormToDtoTest() {

			dto = providingOperationService.convertProvidingFormToProvidingOperationDto(form);

			assertEquals(dto.getAmount(), form.getAmount());
		}

		@Test
		void isMoneyRemovedWhenProcessingProvidingOperationFromAccountToBankAccountTest()
				throws InsufficientBalanceException, BankProcessFailedException, ResourceNotFoundException,
				NegativeAmountException {

			form.setProvidingType("ACCOUNTTOBANKACCOUNT");
			dto = providingOperationService.convertProvidingFormToProvidingOperationDto(form);

			when(accountService.getAccount(1)).thenReturn(account);
			doCallRealMethod().when(accountService).removeMoneyFromAccount(account, form.getAmount());

			providingOperationService.processProvidingOperation(dto);

			assertEquals(account.getAccountBalance(), 400);
		}

		@Test
		void isMoneyAddedWhenProcessingProvidingOperationFromBankAccountToAccountTest()
				throws InsufficientBalanceException, ResourceNotFoundException, NegativeAmountException,
				BankProcessFailedException {

			form.setProvidingType("BANKACCOUNTTOACCOUNT");
			dto = providingOperationService.convertProvidingFormToProvidingOperationDto(form);

			when(accountService.getAccount(1)).thenReturn(account);
			doCallRealMethod().when(accountService).addMoneyToAccount(account, form.getAmount());

			providingOperationService.processProvidingOperation(dto);

			assertEquals(account.getAccountBalance(), 600);
		}
	}

	@Nested
	@Tag("ExceptionsTests")
	@DisplayName("Exceptions Checking")
	class ExceptionsTests {

		@Test
		void isProvidingOperationAbortedWhenTryingToProcessWithNegativeAmount()
				throws ResourceNotFoundException, InsufficientBalanceException, NegativeAmountException {

			form.setProvidingType("ACCOUNTTOBANKACCOUNT");
			form.setAmount(-200);
			dto = providingOperationService.convertProvidingFormToProvidingOperationDto(form);

			when(accountService.getAccount(1)).thenReturn(account);
			doCallRealMethod().when(accountService).removeMoneyFromAccount(account, form.getAmount());

			assertThrows(NegativeAmountException.class, () -> providingOperationService.processProvidingOperation(dto));
			assertEquals(account.getAccountBalance(), 500);
		}

		@Test
		void isProvidingOperationAbortedWhenTryingToProcessWithInsufficientBalance()
				throws ResourceNotFoundException, InsufficientBalanceException, NegativeAmountException {

			form.setProvidingType("ACCOUNTTOBANKACCOUNT");
			form.setAmount(600);
			dto = providingOperationService.convertProvidingFormToProvidingOperationDto(form);

			when(accountService.getAccount(1)).thenReturn(account);
			doCallRealMethod().when(accountService).removeMoneyFromAccount(account, form.getAmount());

			assertThrows(InsufficientBalanceException.class,
					() -> providingOperationService.processProvidingOperation(dto));
			assertEquals(account.getAccountBalance(), 500);
		}

		@Test
		void isProvidingOperationAbortedWhenTryingToProcessWithUnknownAccount()
				throws ResourceNotFoundException, InsufficientBalanceException, NegativeAmountException {

			form.setProvidingType("ACCOUNTTOBANKACCOUNT");
			dto = providingOperationService.convertProvidingFormToProvidingOperationDto(form);

			when(accountService.getAccount(1)).thenThrow(ResourceNotFoundException.class);

			assertThrows(ResourceNotFoundException.class,
					() -> providingOperationService.processProvidingOperation(dto));
		}

		@Test
		void isProvidingOperationAbortedWhenBankProcessFails() throws BankProcessFailedException {

			form.setProvidingType("BANKACCOUNTTOACCOUNT");
			dto = providingOperationService.convertProvidingFormToProvidingOperationDto(form);

			doThrow(BankProcessFailedException.class).when(bankAccountService).bankAccountWithdrawProcess();

			assertThrows(BankProcessFailedException.class,
					() -> providingOperationService.processProvidingOperation(dto));
		}
	}
}
