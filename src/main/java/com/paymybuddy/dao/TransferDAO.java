package com.paymybuddy.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paymybuddy.entity.Account;
import com.paymybuddy.entity.Transfer;
import com.paymybuddy.entity.TransferPK;

public interface TransferDAO extends JpaRepository<Transfer, TransferPK> {

	List<Transfer> findByAccountFrom(Account accountFrom);
	
	
	
}
