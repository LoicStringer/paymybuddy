package com.paymybuddy.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paymybuddy.entity.Transfer;

public interface TransferDAO extends JpaRepository<Transfer, Transfer.TransferPK> {

	
	
}
