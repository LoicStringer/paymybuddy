package com.paymybuddy.service;

import java.time.Instant;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.dto.ProvidingOperationDTO;
import com.paymybuddy.entity.Operation;
import com.paymybuddy.entity.Providing;
import com.paymybuddy.entity.Providing.ProvidingType;
import com.paymybuddy.entity.Tax;
import com.paymybuddy.exception.BankProcessFailedException;
import com.paymybuddy.exception.InsufficientBalanceException;
import com.paymybuddy.exception.NegativeAmountException;
import com.paymybuddy.exception.ResourceNotFoundException;
import com.paymybuddy.form.ProvidingOperationForm;
import com.paymybuddy.responseentity.ProvidingOperationResponse;

@Service
public class ProvidingOperationService {
	
	@Autowired
	private AccountService accountService;

	@Autowired
	private TaxService taxService;

	@Autowired
	private OperationService operationService;

	@Autowired
	private BankAccountService bankAccountService;

	@Autowired
	private ProvidingService providingService;

	@Transactional(rollbackOn = Exception.class)
	public ProvidingOperationResponse processProvidingOperation(ProvidingOperationDTO providingOperationDto) throws InsufficientBalanceException, BankProcessFailedException, ResourceNotFoundException, NegativeAmountException {

		ProvidingOperationResponse providingOperationCompletedInfo = new ProvidingOperationResponse();
		
		Operation operationInProgress = buildOperationInProgressFromProvidingOperatioDto(providingOperationDto);
		Providing providingInProgress = buildProvidingInProgressFromProvidingOperatioDto(providingOperationDto);
		
		switch (providingInProgress.getProvidingType()) {
		case ACCOUNTTOBANKACCOUNT:
			bankAccountService.bankAccountDepositProcess();
			accountService.removeMoneyFromAccount(providingInProgress.getHolderAccountId(),
					operationInProgress.getOperationAmount());
			accountService.updateAccount(providingInProgress.getHolderAccountId());
			providingInProgress.setProvidingOperationId(operationService.saveOperation(operationInProgress));
			providingService.saveProviding(providingInProgress);
			break;
			
		case BANKACCOUNTTOACCOUNT:
			bankAccountService.bankAccountWithdrawProcess();
			accountService.addMoneyToAccount(providingInProgress.getHolderAccountId(),
					operationInProgress.getOperationAmount());
			accountService.updateAccount(providingInProgress.getHolderAccountId());
			providingInProgress.setProvidingOperationId(operationService.saveOperation(operationInProgress));
			providingService.saveProviding(providingInProgress);
			break;
		}
		
		providingOperationCompletedInfo.setMessage("Providing operation has succed");
		providingOperationCompletedInfo.setProvidingOperationDto(providingOperationDto);

		return providingOperationCompletedInfo;
	}

	public ProvidingOperationDTO convertProvidingFormToProvidingOperationDto(
			ProvidingOperationForm providingOperationForm) {

		ProvidingOperationDTO providingOperationInProgress = new ProvidingOperationDTO();

		providingOperationInProgress.setAccountId(providingOperationForm.getAccountId());
		providingOperationInProgress.setBankAccountId(providingOperationForm.getBankAccountId());
		providingOperationInProgress.setAmount(providingOperationForm.getAmount());
		providingOperationInProgress.setProvidingType(providingOperationForm.getProvidingType());
		providingOperationInProgress.setTaxApplied(providingOperationForm.getTaxApplied());

		return providingOperationInProgress;
	}

	private Operation buildOperationInProgressFromProvidingOperatioDto(ProvidingOperationDTO providingOperationDto) throws NegativeAmountException, ResourceNotFoundException {

		Tax taxApplied = taxService.getTax(providingOperationDto.getTaxApplied());

		Operation buildedOperation = new Operation();
		buildedOperation.setOperationAmount(providingOperationDto.getAmount());
		buildedOperation.setOperationDate(Instant.now());
		buildedOperation
				.setOperationFee(operationService.calculateOperationFee(taxApplied, providingOperationDto.getAmount()));
		buildedOperation.setOperationTax(taxApplied);

		return buildedOperation;
	}

	private Providing buildProvidingInProgressFromProvidingOperatioDto(ProvidingOperationDTO providingOperationDto) throws ResourceNotFoundException {

		Providing buildedProviding = new Providing();

		buildedProviding.setHolderAccountId(accountService.getAccount(providingOperationDto.getAccountId()));
		buildedProviding.setBankAccountId(bankAccountService.getBankAccount(providingOperationDto.getBankAccountId()));
		buildedProviding.setProvidingType(ProvidingType.valueOf(providingOperationDto.getProvidingType()));

		return buildedProviding;
	}

}
