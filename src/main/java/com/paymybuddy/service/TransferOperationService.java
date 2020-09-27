package com.paymybuddy.service;



import java.time.Instant;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.dto.TransferOperationDTO;
import com.paymybuddy.entity.Operation;
import com.paymybuddy.entity.Tax;
import com.paymybuddy.entity.Transfer;
import com.paymybuddy.form.TransferOperationForm;
import com.paymybuddy.responseentity.TransferOperationResponse;

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
	public TransferOperationResponse processTransferOperation (TransferOperationDTO transferOperationDto) {
		
		TransferOperationResponse transferOperationCompletedInfo = new TransferOperationResponse();
		
		if (accountService.getAccount(transferOperationDto.getAccountFromId()).getAccountBalance()<=0) {
			//throw exception
			transferOperationCompletedInfo.setMessage("Failed");
		}
			
		Operation operationInProgress = buildOperationFromTransferOperationDto(transferOperationDto);
		Transfer transferInProgress = buildTransferFromTransferOperationDto(transferOperationDto);
		
		transferInProgress.setTransferOperationId(operationService.saveOperation(operationInProgress));
		
		accountService.addMoneyToAccount(transferInProgress.getAccountFrom(), - operationInProgress.getOperationAmount());
		accountService.addMoneyToAccount(transferInProgress.getAccountTo(), operationInProgress.getOperationAmount());
		transferService.saveTransfer(transferInProgress);
		
		transferOperationCompletedInfo.setMessage("Transfer operation has succeed.");
		
		return transferOperationCompletedInfo;
	}
	
	public TransferOperationDTO convertTransferOperatioFormToTransferOperatioDto(TransferOperationForm transferOperationForm) {
		
		TransferOperationDTO transferOperationInProgress = new TransferOperationDTO();
		
		transferOperationInProgress.setAccountFromId(transferOperationForm.getAccountFromId());
		transferOperationInProgress.setAccountToId(transferOperationForm.getAccountToId());
		transferOperationInProgress.setAmount(transferOperationForm.getAmount());
		transferOperationInProgress.setTaxApplied(transferOperationForm.getTaxApplied());
		transferOperationInProgress.setTransferDescription(transferOperationForm.getTransferDescription());
		
		return transferOperationInProgress;
	}
	
	private Transfer buildTransferFromTransferOperationDto(TransferOperationDTO transferOperationDTO) {
	
		Transfer buildedTransfer = new Transfer();
		buildedTransfer.setAccountFrom(accountService.getAccount(transferOperationDTO.getAccountFromId()));
		buildedTransfer.setAccountTo(accountService.getAccount(transferOperationDTO.getAccountToId()));
		buildedTransfer.setTransferDescription(transferOperationDTO.getTransferDescription());
	
		return buildedTransfer;
	}
		
	private Operation buildOperationFromTransferOperationDto(TransferOperationDTO transferOperationDTO) {
		
		Tax taxApplied = taxService.getTax(transferOperationDTO.getTaxApplied());
		Operation buildedOperation = new Operation();
		
		buildedOperation.setOperationAmount(transferOperationDTO.getAmount());
		buildedOperation.setOperationDate(Instant.now());
		buildedOperation.setOperationFee(operationService.calculateOperationFee(taxApplied, transferOperationDTO.getAmount()));
		buildedOperation.setOperationTax(taxApplied);
	
		return buildedOperation;
	}
	
	
	 
	
	
	
}
