package com.paymybuddy.dao;

import org.springframework.data.repository.CrudRepository;

import com.paymybuddy.entity.Transfer;

public interface TransferDAO extends CrudRepository<Transfer, Transfer.TransferPK> {

}
