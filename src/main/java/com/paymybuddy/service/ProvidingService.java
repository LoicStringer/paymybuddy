package com.paymybuddy.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.dao.ProvidingDAO;
import com.paymybuddy.entity.Account;
import com.paymybuddy.entity.Providing;

@Service
public class ProvidingService {

	@Autowired
	private ProvidingDAO providingDao;
	
	public List<Providing> getProvidingsByAccount(Account holderAccount){
		List<Providing> providings = new ArrayList<Providing>();
		providings = providingDao.findByHolderAccountId(holderAccount);
		return providings;
	}
	
	public Providing saveProviding(Providing providing) {
		return providingDao.save(providing);
	}
	
}
