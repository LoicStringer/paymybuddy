package com.paymybuddy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.dao.AccountDAO;
import com.paymybuddy.entity.Account;
import com.paymybuddy.exception.InsufficientBalanceException;
import com.paymybuddy.exception.NegativeAmountException;
import com.paymybuddy.exception.ResourceNotFoundException;
import com.paymybuddy.exception.UniqueConstraintViolationException;

@Service
public class AccountService {
	
	@Autowired
	private AccountDAO accountDao;
	
	public Account getAccount (long accountId) throws ResourceNotFoundException {
		return accountDao.findById(accountId).orElseThrow(()-> new ResourceNotFoundException("Account not found."));
	}
	
	public Account getAccountByEmail(String email) throws ResourceNotFoundException {
		return accountDao.findByAccountUserEmailEquals(email).orElseThrow(()-> new ResourceNotFoundException("Account not found."));
	}
	
	public Account createAccount(Account createdAccount) throws UniqueConstraintViolationException {
		checkForEmailUnicity(createdAccount.getAccountUserEmail());
		return accountDao.save(createdAccount);
	}
	
	public Account updateAccount(Account account) {
		return accountDao.save(account);
	}

	public void addMoneyToAccount(Account account, double amount) throws NegativeAmountException {
		checkForNegativeAmount(amount);
		double newBalance = account.getAccountBalance()+amount;
		account.setAccountBalance(newBalance);
	}
	
	public void removeMoneyFromAccount(Account account, double amount) throws InsufficientBalanceException, NegativeAmountException {
		checkForNegativeAmount(amount);
		if(account.getAccountBalance()<=0|amount>account.getAccountBalance())
			throw new InsufficientBalanceException("Insufficient balance");
		
		double newBalance = account.getAccountBalance()-amount;
		account.setAccountBalance(newBalance);
	}
	
	private void checkForEmailUnicity(String email) throws UniqueConstraintViolationException {
		Account accountToCheck = accountDao.findByAccountUserEmailEquals(email).orElse(null);
		if(accountToCheck != null)
			throw new UniqueConstraintViolationException("This email already exists.");
	}

	private void checkForNegativeAmount(double amount) throws NegativeAmountException {
		if(amount<0)
			throw new NegativeAmountException("Amount can't be negative!");
	}

}
