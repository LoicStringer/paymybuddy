package com.paymybuddy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.dao.TransferDAO;
import com.paymybuddy.entity.Transfer;

@Service
public class TransferService {

	@Autowired
	private TransferDAO transferDao;
	
	public Transfer saveTransfer(Transfer transferToSave) {
		return transferDao.save(transferToSave);
	}
	
}
