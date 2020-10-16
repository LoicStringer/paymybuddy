package com.paymybuddy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.dao.ProvidingDAO;
import com.paymybuddy.entity.Account;
import com.paymybuddy.entity.BankAccount;
import com.paymybuddy.entity.Providing;

@Service
public class ProvidingService {

	@Autowired
	private ProvidingDAO providingDao;

	public List<Providing> getProvidingsByAccount(Account holderAccount) {
		return providingDao.findByHolderAccount(holderAccount);
	}

	public List<Providing> getProvidingsByBankAccount(BankAccount bankAccount) {
		return providingDao.findByBankAccount(bankAccount);
	}

}
