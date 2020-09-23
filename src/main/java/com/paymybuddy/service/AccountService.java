package com.paymybuddy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.dao.AccountDAO;
import com.paymybuddy.entity.Account;

@Service
public class AccountService {
	
	@Autowired
	private AccountDAO accountDao;
	
	public Account createAccount(Account createdAccount) {
		return accountDao.save(createdAccount);
	}

}
