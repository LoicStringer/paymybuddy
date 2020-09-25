package com.paymybuddy.service;



import java.time.Instant;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.entity.Operation;
import com.paymybuddy.entity.Tax;
import com.paymybuddy.entity.Transfer;
import com.paymybuddy.form.TransferOperationForm;
import com.paymybuddy.model.TransferOperation;
import com.paymybuddy.responseentity.TransferOperationInfo;

@Service
public class TransferOperationService {
	
	@Autowired 
	private AccountService accountService;
	
	@Autowired
	private TaxService taxService;
	
	@Autowired
	private OperationService operationService;
	
	@Autowired
	private TransferService transferService;
	
	
	@Transactional
	public TransferOperationInfo processTransferOperation (TransferOperation transferOperationInProgress) {
		
		TransferOperationInfo transferOperationCompletedInfo = new TransferOperationInfo();
		
		accountService.addMoneyToAccount(transferOperationInProgress.getTransfer().getAccountFrom(), - transferOperationInProgress.getOperation().getOperationAmount());
		accountService.addMoneyToAccount(transferOperationInProgress.getTransfer().getAccountTo(), transferOperationInProgress.getOperation().getOperationAmount());
		transferService.saveTransfer(transferOperationInProgress.getTransfer());
		
		transferOperationCompletedInfo.setMessage("Transfer operation has succeed.");
		
		return transferOperationCompletedInfo;
	}
	
	
	public TransferOperation buildTransferOperation(TransferOperationForm transferOperatioForm) {
		
		Tax taxApplied = taxService.getTax(transferOperatioForm.getTaxApplied());
		
		Operation buildedOperation = new Operation();
		buildedOperation.setOperationAmount(transferOperatioForm.getAmount());
		buildedOperation.setOperationDate(Instant.now());
		buildedOperation.setOperationFee(operationService.calculateOperationFee(taxApplied, transferOperatioForm.getAmount()));
		buildedOperation.setOperationTax(taxApplied);
		buildedOperation = operationService.saveOperation(buildedOperation);
		
		Transfer buildedTransfer = new Transfer();
		buildedTransfer.setAccountFrom(accountService.getAccount(transferOperatioForm.getAccountFromId()));
		buildedTransfer.setAccountTo(accountService.getAccount(transferOperatioForm.getAccountToId()));
		buildedTransfer.setTransferDescription(transferOperatioForm.getTransferDescription());
		buildedTransfer.setTransferOperationId(buildedOperation);
		
		TransferOperation buildedTransferOperation = new TransferOperation(buildedTransfer,buildedOperation);
		
		return buildedTransferOperation;
	}
	
	
	 
	
	
	
}