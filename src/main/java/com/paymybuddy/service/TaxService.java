package com.paymybuddy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.dao.TaxDAO;
import com.paymybuddy.entity.Tax;

@Service
public class TaxService {

	@Autowired
	private TaxDAO taxDao;
	
	public Tax addTaxToDb (Tax taxToAdd) {
		return taxDao.save(taxToAdd);
	}

	public Tax getTax(int taxId) {
		return taxDao.findById(taxId).orElseThrow();
	}
}
