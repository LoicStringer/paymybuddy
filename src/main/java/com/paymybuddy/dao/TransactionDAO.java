package com.paymybuddy.dao;

import org.springframework.data.repository.CrudRepository;

import com.paymybuddy.entity.Transaction;

public interface TransactionDAO extends CrudRepository<Transaction, Long>{

}
