package com.paymybuddy.service;



import java.time.Instant;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.dto.TransferOperationDTO;
import com.paymybuddy.entity.Operation;
import com.paymybuddy.entity.Tax;
import com.paymybuddy.entity.Transfer;
import com.paymybuddy.exception.InsufficientBalanceException;
import com.paymybuddy.exception.NegativeAmountException;
import com.paymybuddy.exception.ResourceNotFoundException;
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
	
	/**
	 * <p>Method used to process a transfer operation between registered accounts
	 * and persist the related entities {@link Transfer} and {@link Operation}.</p>
	 * @param transferOperationDto
	 * @return TransferOperationResponse
	 * @throws InsufficientBalanceException
	 * @throws ResourceNotFoundException
	 * @throws NegativeAmountException
	 */
	@Transactional(rollbackOn = Exception.class)
	public TransferOperationResponse processTransferOperation (TransferOperationDTO transferOperationDto) throws InsufficientBalanceException, ResourceNotFoundException, NegativeAmountException {
					
		Operation operationInProgress = buildOperationFromTransferOperationDto(transferOperationDto);
		Transfer transferInProgress = buildTransferFromTransferOperationDto(transferOperationDto);
	
		accountService.removeMoneyFromAccount(transferInProgress.getAccountFrom(), operationInProgress.getOperationAmount());
		accountService.addMoneyToAccount(transferInProgress.getAccountTo(), operationInProgress.getOperationAmount());
		
		transferInProgress.setTransferOperationId(operationService.saveOperation(operationInProgress));
		transferInProgress.getAccountFrom().addTransfer(transferInProgress);
		
		accountService.updateAccount(transferInProgress.getAccountFrom());
		accountService.updateAccount(transferInProgress.getAccountTo());
		
		TransferOperationResponse transferOperationCompletedInfo = new TransferOperationResponse();
		transferOperationCompletedInfo.setMessage("Transfer operation has succeed.");
		transferOperationCompletedInfo.setTransferOperationDto(transferOperationDto);
		
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
	
	private Transfer buildTransferFromTransferOperationDto(TransferOperationDTO transferOperationDTO) throws ResourceNotFoundException {
	
		Transfer buildedTransfer = new Transfer();
		buildedTransfer.setAccountFrom(accountService.getAccount(transferOperationDTO.getAccountFromId()));
		buildedTransfer.setAccountTo(accountService.getAccount(transferOperationDTO.getAccountToId()));
		buildedTransfer.setTransferDescription(transferOperationDTO.getTransferDescription());
	
		return buildedTransfer;
	}
		
	private Operation buildOperationFromTransferOperationDto(TransferOperationDTO transferOperationDTO) throws NegativeAmountException, ResourceNotFoundException {
		
		Tax taxApplied = taxService.getTax(transferOperationDTO.getTaxApplied());
		Operation buildedOperation = new Operation();
		
		buildedOperation.setOperationAmount(transferOperationDTO.getAmount());
		buildedOperation.setOperationDate(Instant.now());
		buildedOperation.setOperationFee(operationService.calculateOperationFee(taxApplied, transferOperationDTO.getAmount()));
		buildedOperation.setOperationTax(taxApplied);
	
		return buildedOperation;
	}
	
	
	 
	
	
	
}
