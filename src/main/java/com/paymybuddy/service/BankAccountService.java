package com.paymybuddy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.dao.BankAccountDAO;
import com.paymybuddy.entity.BankAccount;

@Service
public class BankAccountService {

	@Autowired
	private BankAccountDAO bankAccountDao;
	
	public BankAccount addBankAccount(BankAccount bankAccountToAdd) {
		return bankAccountDao.save(bankAccountToAdd);
	}
	
	
	
}
