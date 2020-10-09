
package com.paymybuddy.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.paymybuddy.dao.AccountDAO;
import com.paymybuddy.entity.Account;
import com.paymybuddy.exception.InsufficientBalanceException;
import com.paymybuddy.exception.NegativeAmountException;
import com.paymybuddy.exception.ResourceNotFoundException;
import com.paymybuddy.exception.UniqueConstraintViolationException;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

	@InjectMocks
	private AccountService accountService;

	@Mock
	private AccountDAO accountDao;

	private Account account;

	@BeforeEach
	void setUp() {

		account = new Account();
		account.setAccountId(1);
		account.setAccountBalance(275.50);
		account.setAccountUserEmail("tonymontana@scarface.com");
	}

	@Nested
	@Tag("NominalCasesTests")
	@DisplayName("Nominal cases checking")
	class NominalCasesTests {

		@Test
		void getAccountByIdTest() throws ResourceNotFoundException {

			when(accountDao.findById((long) 1)).thenReturn(Optional.of(account));

			assertEquals(accountService.getAccount(1), account);
		}

		@Test
		void getAccountByEmailTest() throws ResourceNotFoundException {

			when(accountDao.findByAccountUserEmailEquals(any(String.class))).thenReturn(Optional.of(account));

			assertEquals(accountService.getAccountByEmail("tonymontana@scarface.com"), account);
		}

		@Test
		void createAccountTest() throws UniqueConstraintViolationException {

			when(accountDao.save(any(Account.class))).thenReturn(account);

			assertEquals(accountService.createAccount(account), account);
		}

		@Test
		void updateAccountTest() {

			when(accountDao.save(any(Account.class))).thenReturn(account);

			assertEquals(accountService.updateAccount(account), account);
		}

		@Test
		void addMoneyToAccountTest() throws NegativeAmountException {

			accountService.addMoneyToAccount(account, 150);

			assertEquals((275.50 + 150), account.getAccountBalance());
		}

		@Test
		void removeMoneyFromAccountTest() throws InsufficientBalanceException, NegativeAmountException {

			accountService.removeMoneyFromAccount(account, 150);

			assertEquals((275.50 - 150), account.getAccountBalance());
		}

	}

	@Nested
	@Tag("ExceptionsTests")
	@DisplayName("Exceptions Checking")
	class ExceptionsTests {

		@Test
		void expectedExceptionIsThrownWhenAddingNegativeAmount() {
			assertThrows(NegativeAmountException.class, () -> accountService.addMoneyToAccount(account, -250));
		}

		@Test
		void expectedExceptionIsThrownWhenRemovingNegativeAmount() {
			assertThrows(NegativeAmountException.class, () -> accountService.removeMoneyFromAccount(account, -250));
		}

		@Test
		void expectedExceptionIsThrownWhenResourceIsNotFound() {

			when(accountDao.findById(any(Long.class))).thenReturn(Optional.empty());

			assertThrows(ResourceNotFoundException.class, () -> accountService.getAccount(any(Long.class)));
		}

		@Test
		void expectedExceptionIsThrownWhenResourceIsNotFoundByEmail() {
			
			when(accountDao.findByAccountUserEmailEquals(any(String.class))).thenReturn(Optional.empty());

			assertThrows(ResourceNotFoundException.class, () -> accountService.getAccountByEmail("tonymontana@scarface.com"));

		}

		@Test
		void expectedExceptionIsThrownWhenAccountBalanceIsInsufficient() {
			
			assertThrows(InsufficientBalanceException.class, () -> accountService.removeMoneyFromAccount(account, 1000));
		}

		@Test
		void expectedExceptionIsThrownWhenCreatingAccountWithAnEmailAlreadyRegistered() {
			
			when(accountDao.findByAccountUserEmailEquals(any(String.class))).thenReturn(Optional.of(account));
			
			assertThrows(UniqueConstraintViolationException.class, ()-> accountService.createAccount(account));
		}
	}

}
