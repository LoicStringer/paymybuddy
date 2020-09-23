package com.paymybuddy.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paymybuddy.entity.Operation;

public interface OperationDAO extends JpaRepository<Operation, Long>{

}
