package com.paymybuddy.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.paymybuddy.dao.BankAccountDAO;
import com.paymybuddy.dto.BankAccountDTO;
import com.paymybuddy.entity.Account;
import com.paymybuddy.entity.BankAccount;
import com.paymybuddy.exception.ResourceNotFoundException;
import com.paymybuddy.exception.UniqueConstraintViolationException;
import com.paymybuddy.form.BankAccountForm;

@ExtendWith(MockitoExtension.class)
class BankAccountServiceTest {

	@InjectMocks
	private BankAccountService bankAccountService;

	@Mock
	private BankAccountDAO bankAccountDao;

	@Mock
	private AccountService accountService;

	private static BankAccountForm bankAccountForm;
	private static Account account;
	private BankAccountDTO bankAccountDto;
	private BankAccount bankAccount;

	@BeforeAll
	static void setUp() {
		account = new Account();
		bankAccountForm = new BankAccountForm();

		bankAccountForm.setAccountHolderId(1);
		bankAccountForm.setBankAccountDescription("Bets");
		bankAccountForm.setBankAccountHolderName("Serpico");
		bankAccountForm.setBankAccountIban("FR7630004000031234567890143");

	}

	@BeforeEach
	void setUpForTests() throws ResourceNotFoundException {
		when(accountService.getAccount(any(Long.class))).thenReturn(account);
		bankAccountDto = bankAccountService.convertBankAccountFormToBankAccountDto(bankAccountForm);
		bankAccount = bankAccountService.buildBankAccount(bankAccountDto);
	}

	@Nested
	@Tag("NominalCasesTests")
	@DisplayName("Nominal cases checking")
	class NominalCasesTests {

		@Test
		void convertFormToDtoTest() {

			assertEquals(bankAccountDto.getBankAccountIban(), bankAccountForm.getBankAccountIban());
		}

		@Test
		void convertDtoToEntityTest() throws ResourceNotFoundException {

			assertEquals(bankAccount.getBankAccountDescription(), bankAccountDto.getBankAccountDescription());
		}

		@Test
		void getBankAccountTest() throws ResourceNotFoundException {

			when(bankAccountDao.findById((long) 1)).thenReturn(Optional.of(bankAccount));

			assertEquals(bankAccountService.getBankAccount(1), bankAccount);
		}

		@Test
		void getBankAccountByIbanTest() throws ResourceNotFoundException {

			when(bankAccountDao.findByBankAccountIbanEquals(any(String.class))).thenReturn(Optional.of(bankAccount));

			assertEquals(bankAccountService.getBankAccountByIban("FR7630004000031234567890143"), bankAccount);
		}

		//TODO Implement tests for bankAccountDepositProcess() and bankAccountWithdrawprocess() methods.
		
	}

	@Nested
	@Tag("ExceptionsTests")
	@DisplayName("Exceptions Checking")
	class ExceptionsTests {

		@Test
		void expectedExceptionIsThrownWhenCreatingAccountWithAnIbanAlreadyRegistered() {

			when(bankAccountDao.findByBankAccountIbanEquals(any(String.class))).thenReturn(Optional.of(bankAccount));

			assertThrows(UniqueConstraintViolationException.class,
					() -> bankAccountService.saveBankAccount(bankAccount));
		}

		@Test
		void expectedExceptionIsThrownWhenResourceNotFound() {

			when(bankAccountDao.findById((long) 1)).thenReturn(Optional.empty());
			
			assertThrows(ResourceNotFoundException.class, ()-> bankAccountService.getBankAccount(1));
			
		}

		@Test
		void expectedExceptionIsThrownWhenResourceNotFoundByIban() {
			
			when(bankAccountDao.findByBankAccountIbanEquals(any(String.class))).thenReturn(Optional.empty());

			assertThrows(ResourceNotFoundException.class, ()-> bankAccountService.getBankAccountByIban("FR7630004000031234567890143"));
		}
		
		//TODO Implement tests to check exceptions around bankAccountDepositProcess() and bankAccountWithdrawprocess() methods.
	}

}
