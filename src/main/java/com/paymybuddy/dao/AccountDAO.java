package com.paymybuddy.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paymybuddy.entity.Account;

public interface AccountDAO extends JpaRepository<Account, Long>{

	Optional<Account> findByAccountUserEmailEquals(String email);

	
}
