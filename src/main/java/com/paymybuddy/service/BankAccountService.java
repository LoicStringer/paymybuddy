package com.paymybuddy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.dao.BankAccountDAO;
import com.paymybuddy.dto.BankAccountDTO;
import com.paymybuddy.entity.BankAccount;
import com.paymybuddy.exception.BankProcessFailedException;
import com.paymybuddy.exception.ResourceNotFoundException;
import com.paymybuddy.exception.UniqueConstraintViolationException;
import com.paymybuddy.form.BankAccountForm;

@Service
public class BankAccountService {

	@Autowired
	private BankAccountDAO bankAccountDao;
	
	@Autowired
	private AccountService accountService;
	
	public BankAccount getBankAccount (long bankAccountId) throws ResourceNotFoundException {
		return bankAccountDao.findById(bankAccountId).orElseThrow(()-> new ResourceNotFoundException("Bank account not found."));
	}
	
	public BankAccount updateBankAccount(BankAccount bankAccount) {
		return bankAccountDao.save(bankAccount);
	}
	
	public BankAccount getBankAccountByIban(String iban) throws ResourceNotFoundException {
		return bankAccountDao.findByBankAccountIbanEquals(iban).orElseThrow(()-> new ResourceNotFoundException("Bank account not found."));
	}
	
	public BankAccount addABankAccount(BankAccountDTO bankAccountDto) throws UniqueConstraintViolationException, ResourceNotFoundException {
		BankAccount bankAccountToAdd = buildBankAccount(bankAccountDto);
		checkIbanUnicity(bankAccountToAdd.getBankAccountIban());
		bankAccountToAdd.getHolderAccount().addBankAccount(bankAccountToAdd);
		accountService.updateAccount(bankAccountToAdd.getHolderAccount());
		return bankAccountToAdd;
	}
	
	public BankAccountDTO convertBankAccountFormToBankAccountDto(BankAccountForm bankAccountForm) {
		BankAccountDTO bankAccountDto = new BankAccountDTO();
		bankAccountDto.setBankAccountIban(bankAccountForm.getBankAccountIban());
		bankAccountDto.setBankAccountHolderName(bankAccountForm.getBankAccountHolderName());
		bankAccountDto.setBankAccountDescription(bankAccountForm.getBankAccountDescription());
		bankAccountDto.setAccountHolderId(bankAccountForm.getAccountHolderId());
		return bankAccountDto;
	}
	
	public void bankAccountWithdrawProcess() throws BankProcessFailedException {
		boolean success = false;
		//TODO implement the linked method to online payment external process
		success = true;
		if(success!=true)
			throw new BankProcessFailedException("Authorization denied");
	}
	
	public void bankAccountDepositProcess () throws BankProcessFailedException {
		boolean success = false;
		//TODO implement the linked method to online payment  external process
		success = true;
		if(success!=true)
			throw new BankProcessFailedException("Bank process failed");
	}
	

	private BankAccount buildBankAccount(BankAccountDTO bankAccountDto) throws ResourceNotFoundException {
		BankAccount buildedBankAccount = new BankAccount();
		buildedBankAccount.setBankAccountIban(bankAccountDto.getBankAccountIban());
		buildedBankAccount.setBankAccountHolderName(bankAccountDto.getBankAccountHolderName());
		buildedBankAccount.setBankAccountDescription(bankAccountDto.getBankAccountDescription());
		buildedBankAccount.setHolderAccount(accountService.getAccount(bankAccountDto.getAccountHolderId()));
		return buildedBankAccount;
	}
	
	
	private void checkIbanUnicity(String iban) throws UniqueConstraintViolationException {
		BankAccount bankAccountToCheck = bankAccountDao.findByBankAccountIbanEquals(iban).orElse(null);
		if (bankAccountToCheck != null)
			throw new UniqueConstraintViolationException("This bank account already exists.");
	}
}
