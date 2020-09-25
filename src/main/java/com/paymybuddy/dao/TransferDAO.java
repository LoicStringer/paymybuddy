package com.paymybuddy.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paymybuddy.entity.Transfer;
import com.paymybuddy.entity.TransferPK;

public interface TransferDAO extends JpaRepository<Transfer, TransferPK> {

	
	
}
