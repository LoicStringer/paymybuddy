package com.paymybuddy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.dao.OperationDAO;
import com.paymybuddy.entity.Operation;
import com.paymybuddy.entity.Tax;
import com.paymybuddy.exception.NegativeAmountException;

@Service
public class OperationService {

	@Autowired
	private OperationDAO operationDao;
	
	public Operation getOperation(long id) {
		return(operationDao.findById(id)).orElse(null);
	}
	
	public Operation saveOperation(Operation operationToSave) {
		return operationDao.save(operationToSave);
	}
	
	public double calculateOperationFee(Tax taxApplied,double amount) throws NegativeAmountException {
		checkForNegativeAmount(amount);
		return (taxApplied.getTaxRate()*amount);
	}
	
	private void checkForNegativeAmount(double amount) throws NegativeAmountException {
		if(amount<0)
			throw new NegativeAmountException("Amount can't be negative!");
	}
}
