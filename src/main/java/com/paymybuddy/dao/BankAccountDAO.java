package com.paymybuddy.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paymybuddy.entity.BankAccount;

public interface BankAccountDAO extends JpaRepository<BankAccount, Long> {
	
	Optional<BankAccount> findByBankAccountIbanEquals(String iban);

}
