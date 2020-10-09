package com.paymybuddy.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.paymybuddy.dto.BankAccountDTO;
import com.paymybuddy.dto.FriendshipDTO;
import com.paymybuddy.dto.ProvidingOperationDTO;
import com.paymybuddy.dto.TransferOperationDTO;
import com.paymybuddy.entity.Account;
import com.paymybuddy.entity.BankAccount;
import com.paymybuddy.entity.Friendship;
import com.paymybuddy.entity.Tax;
import com.paymybuddy.exception.BankProcessFailedException;
import com.paymybuddy.exception.InsufficientBalanceException;
import com.paymybuddy.exception.NegativeAmountException;
import com.paymybuddy.exception.ResourceNotFoundException;
import com.paymybuddy.exception.UniqueConstraintViolationException;
import com.paymybuddy.form.BankAccountForm;
import com.paymybuddy.form.FriendshipForm;
import com.paymybuddy.form.ProvidingOperationForm;
import com.paymybuddy.form.TransferOperationForm;
import com.paymybuddy.responseentity.ProvidingOperationResponse;
import com.paymybuddy.responseentity.TransferOperationResponse;
import com.paymybuddy.service.AccountService;
import com.paymybuddy.service.BankAccountService;
import com.paymybuddy.service.FriendshipService;
import com.paymybuddy.service.ProvidingOperationService;
import com.paymybuddy.service.TaxService;
import com.paymybuddy.service.TransferOperationService;

@RestController
public class TesterController {

	@Autowired
	private AccountService accountService;

	@Autowired
	private FriendshipService friendshipService;

	@Autowired
	private BankAccountService bankAccountService;

	@Autowired
	private TaxService taxService;

	@Autowired
	private TransferOperationService transferOperationService;
	
	@Autowired
	private ProvidingOperationService providingOperationService;
	
	@GetMapping("/accounts")
	public ResponseEntity<Account> getAccount(@RequestParam("id")Long id) throws ResourceNotFoundException{
		return ResponseEntity.ok(accountService.getAccount(id));
	}

	@PostMapping("/accounts")
	public ResponseEntity<Account> createAccount(@Valid @RequestBody Account accountToCreate) throws UniqueConstraintViolationException {
		return ResponseEntity.ok(accountService.createAccount(accountToCreate));
	}

	@PostMapping("/friends")
	public ResponseEntity<Friendship> addFriend(@Valid @RequestBody FriendshipForm friendshipForm) throws ResourceNotFoundException {
		
		FriendshipDTO friendshipDto = friendshipService.convertFriendshipFormToFriendshipDto(friendshipForm);
		
		Friendship friendshipToAdd = friendshipService.buildFriendship(friendshipDto);
		
		return ResponseEntity.ok(friendshipService.addFriendship(friendshipToAdd));
	}

	@PostMapping("/bankAccounts")
	public ResponseEntity<BankAccount> addBankAccount(@Valid @RequestBody BankAccountForm bankAccountForm) throws UniqueConstraintViolationException, ResourceNotFoundException {
		
		BankAccountDTO bankAccountDto = bankAccountService.convertBankAccountFormToBankAccountDto(bankAccountForm);
		
		BankAccount bankAccountToSave = bankAccountService.buildBankAccount(bankAccountDto);
		
		return ResponseEntity.ok(bankAccountService.saveBankAccount(bankAccountToSave));
	}

	@PostMapping("/taxes")
	public ResponseEntity<Tax> addTax(@Valid @RequestBody Tax taxToAdd) {
		return ResponseEntity.ok(taxService.addTaxToDb(taxToAdd));
	}

	@PostMapping("/transferOperations")
	public ResponseEntity<TransferOperationResponse> processTransferOperation(
			@Valid @RequestBody TransferOperationForm transferOperationForm) throws InsufficientBalanceException, ResourceNotFoundException, NegativeAmountException {
		
		TransferOperationDTO transferOperationInProgress = 
				transferOperationService.convertTransferOperatioFormToTransferOperatioDto(transferOperationForm);
		
		TransferOperationResponse transferOperationInfo = transferOperationService
				.processTransferOperation(transferOperationInProgress);
		
		return ResponseEntity.ok(transferOperationInfo);
	}

	@PostMapping("/providingOperations")
	public ResponseEntity<ProvidingOperationResponse> processProvidingOperation(
			@Valid @RequestBody ProvidingOperationForm providingOperationForm) throws InsufficientBalanceException, BankProcessFailedException, ResourceNotFoundException, NegativeAmountException {
		
		ProvidingOperationDTO providingOperationInProgress = 
				providingOperationService.convertProvidingFormToProvidingOperationDto(providingOperationForm);
		
		ProvidingOperationResponse providingOperationInfo = 
				providingOperationService.processProvidingOperation(providingOperationInProgress);
		
		return ResponseEntity.ok(providingOperationInfo);
	}
}
