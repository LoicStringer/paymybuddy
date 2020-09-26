package com.paymybuddy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.dao.BankAccountDAO;
import com.paymybuddy.entity.Account;
import com.paymybuddy.entity.BankAccount;
import com.paymybuddy.form.BankAccountForm;

@Service
public class BankAccountService {

	@Autowired
	private BankAccountDAO bankAccountDao;
	
	@Autowired
	private AccountService accountService;
	
	public BankAccount getBankAccount (long bankAccountId) {
		return bankAccountDao.findById(bankAccountId).orElseThrow();
	}
	
	public BankAccount addBankAccount(BankAccountForm bankAccountForm) {
		
		
		BankAccount bankAccountToAdd = new BankAccount();
		
		Account bankAccountHolder = accountService.getAccount(bankAccountForm.getAccountHolderId());
		bankAccountToAdd.setBankAccountIban(bankAccountForm.getBankAccountIban());
		bankAccountToAdd.setBankAccountHolderName(bankAccountForm.getBankAccountHolderName());
		bankAccountToAdd.setBankAccountDescription(bankAccountForm.getBankAccountDescription());
		bankAccountToAdd.setAccountHolderId(bankAccountHolder);
		
		return bankAccountDao.save(bankAccountToAdd);
	}
	
	public boolean askAuthorisationToTheBank() {
		//TODO linked method to online payment
		return true;
	}
	
}
