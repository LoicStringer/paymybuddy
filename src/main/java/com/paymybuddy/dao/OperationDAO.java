package com.paymybuddy.dao;

import org.springframework.data.repository.CrudRepository;

import com.paymybuddy.entity.Operation;

public interface OperationDAO extends CrudRepository<Operation, Long>{

}
