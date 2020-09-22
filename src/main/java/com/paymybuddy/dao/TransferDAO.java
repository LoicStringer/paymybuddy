package com.paymybuddy.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.paymybuddy.entity.Transfer;

public interface TransferDAO extends JpaRepository<Transfer, Transfer.TransferPK> {

	
	@Procedure(procedureName = "money_transfer")
	public int moneyTransfer(@Param("account_from")long accountFromId,@Param("account_to")long accountToId,
			@Param("transfer_description")String transferDescription);
			
}
