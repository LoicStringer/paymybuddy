package com.paymybuddy.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paymybuddy.entity.Account;
import com.paymybuddy.entity.BankAccount;
import com.paymybuddy.entity.Providing;
import com.paymybuddy.entity.ProvidingPK;

public interface ProvidingDAO extends JpaRepository<Providing, ProvidingPK>{
	
	List<Providing> findByHolderAccount(Account holderAccount);
	List<Providing> findByBankAccount(BankAccount bankAccount);

	

}
