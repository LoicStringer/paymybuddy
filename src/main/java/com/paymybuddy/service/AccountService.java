package com.paymybuddy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.dao.AccountDAO;
import com.paymybuddy.entity.Account;

@Service
public class AccountService {
	
	@Autowired
	private AccountDAO accountDao;
	
	public Account getAccount (long accountId) {
		return accountDao.findById(accountId).orElseThrow();
	}
	
	public Account getAccountByEmail(String email) {
		return accountDao.findByAccountEmailEquals(email).orElseThrow();
	}
	
	
	public Account createAccount(Account createdAccount) {
		return accountDao.save(createdAccount);
	}

	public Account addMoneyToAccount(Account account, double amount) {
		double newBalance = account.getAccountBalance()+amount;
		account.setAccountBalance(newBalance);
		return accountDao.save(account);
	}
	
}
