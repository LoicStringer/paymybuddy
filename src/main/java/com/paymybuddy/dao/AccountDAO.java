package com.paymybuddy.dao;

import org.springframework.data.repository.CrudRepository;

import com.paymybuddy.entity.Account;

public interface AccountDAO extends CrudRepository<Account, Long>{

}
