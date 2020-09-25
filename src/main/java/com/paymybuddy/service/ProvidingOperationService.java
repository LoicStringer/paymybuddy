package com.paymybuddy.service;

import java.time.Instant;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.entity.Operation;
import com.paymybuddy.entity.Providing;
import com.paymybuddy.entity.Providing.ProvidingType;
import com.paymybuddy.entity.Tax;
import com.paymybuddy.form.ProvidingOperationForm;
import com.paymybuddy.model.ProvidingOperation;
import com.paymybuddy.model.TransferOperation;
import com.paymybuddy.responseentity.ProvidingOperationResponse;
import com.paymybuddy.responseentity.TransferOperationResponse;

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

	@Transactional
	public ProvidingOperationResponse processProvidingOperation(ProvidingOperationForm providingOperationForm) {

		ProvidingOperation providingOperationInProgress = buildProvidingOperation(providingOperationForm);

		if (providingOperationForm.getProvidingType() == ProvidingType.AccountToBankAccount)
			accountService.addMoneyToAccount(providingOperationInProgress.getProviding().getHolderAccountId(),
					-providingOperationInProgress.getOperation().getOperationAmount());

		accountService.addMoneyToAccount(providingOperationInProgress.getProviding().getHolderAccountId(),
				providingOperationInProgress.getOperation().getOperationAmount());

		ProvidingOperationResponse providingOperationCompletedInfo = new ProvidingOperationResponse();
		providingOperationCompletedInfo.setMessage("Providing operation has succed");
		
		
		return providingOperationCompletedInfo;
	}

	private ProvidingOperation buildProvidingOperation(ProvidingOperationForm providingOperationForm) {

		Tax taxApplied = taxService.getTax(providingOperationForm.getTaxApplied());

		Operation buildedOperation = new Operation();
		buildedOperation.setOperationAmount(providingOperationForm.getAmount());
		buildedOperation.setOperationDate(Instant.now());
		buildedOperation.setOperationFee(
				operationService.calculateOperationFee(taxApplied, providingOperationForm.getAmount()));
		buildedOperation.setOperationTax(taxApplied);
		buildedOperation = operationService.saveOperation(buildedOperation);

		Providing buildedProviding = new Providing();
		buildedProviding.setHolderAccountId(accountService.getAccount(providingOperationForm.getAccountId()));
		buildedProviding.setBankAccountId(bankAccountService.getBankAccount(providingOperationForm.getBankAccountId()));
		buildedProviding.setProvidingOperationId(buildedOperation);

		ProvidingOperation buildedProvidingOperation = new ProvidingOperation(buildedProviding, buildedOperation);

		return buildedProvidingOperation;
	}

}
