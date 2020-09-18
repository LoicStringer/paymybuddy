package com.paymybuddy.dao;

import org.springframework.data.repository.CrudRepository;

import com.paymybuddy.entity.UserAccount;

public interface UserAccountDAO extends CrudRepository<UserAccount, Long>{

}
