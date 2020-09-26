package com.paymybuddy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.dao.ProvidingDAO;
import com.paymybuddy.entity.Providing;

@Service
public class ProvidingService {

	@Autowired
	private ProvidingDAO providingDao;
	
	public Providing saveProviding(Providing providing) {
		return providingDao.save(providing);
	}
	
}
