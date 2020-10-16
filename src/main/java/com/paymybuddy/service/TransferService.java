package com.paymybuddy.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.dao.TransferDAO;
import com.paymybuddy.entity.Account;
import com.paymybuddy.entity.Transfer;

@Service
public class TransferService {

	@Autowired
	private TransferDAO transferDao;
	
	public List<Transfer> getTransfersByAccount(Account accountFrom){
		
		List<Transfer> transfers = new ArrayList<Transfer>();
		transfers = transferDao.findByAccountFrom(accountFrom);
		
		return transfers;
	}
	
}
