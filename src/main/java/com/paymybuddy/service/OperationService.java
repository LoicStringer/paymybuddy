package com.paymybuddy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.dao.OperationDAO;
import com.paymybuddy.entity.Operation;
import com.paymybuddy.entity.Tax;

@Service
public class OperationService {

	@Autowired
	private OperationDAO operationDao;
	
	
	
	public Operation saveOperation(Operation operationToSave) {
		return operationDao.save(operationToSave);
	}
	
	public double calculateOperationFee(Tax taxApplied,double amount) {
		return (taxApplied.getTaxRate()*amount);
	}
	
}
