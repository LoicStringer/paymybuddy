package com.paymybuddy.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paymybuddy.entity.BankAccount;

public interface BankAccountDAO extends JpaRepository<BankAccount, Long> {

}
