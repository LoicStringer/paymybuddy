package com.paymybuddy.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paymybuddy.entity.Account;

public interface AccountDAO extends JpaRepository<Account, Long>{


}
