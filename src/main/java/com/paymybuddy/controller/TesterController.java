package com.paymybuddy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.paymybuddy.entity.Account;
import com.paymybuddy.entity.BankAccount;
import com.paymybuddy.entity.Friendship;
import com.paymybuddy.entity.Tax;
import com.paymybuddy.form.FriendshipForm;
import com.paymybuddy.form.TransferOperationForm;
import com.paymybuddy.responseentity.TransferOperationResponse;
import com.paymybuddy.service.AccountService;
import com.paymybuddy.service.BankAccountService;
import com.paymybuddy.service.FriendshipService;
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
	
	@PostMapping("/createAccount")
	public ResponseEntity<Account> createAccount(@RequestBody Account accountToCreate){
		return ResponseEntity.ok(accountService.createAccount(accountToCreate));
	}

	@PostMapping("/addFriend")
	public ResponseEntity<Friendship> addFriend (@RequestBody FriendshipForm frienshipForm){
		return ResponseEntity.ok(friendshipService.addFriendWithHisEmail(frienshipForm));
	}
	
	@PostMapping("/addBankAccount")
	public ResponseEntity<BankAccount> addBankAccount(@RequestBody BankAccount bankAccountToAdd){
		return ResponseEntity.ok(bankAccountService.addBankAccount(bankAccountToAdd));
	}
	
	@PostMapping("/addTax")
	public ResponseEntity<Tax> addTax (@RequestBody Tax taxToAdd){
		return ResponseEntity.ok(taxService.addTaxToDb(taxToAdd));
	}
	
	@PostMapping("/transferOperation")
	public ResponseEntity<TransferOperationResponse> processTransferOperation
	(@RequestBody TransferOperationForm transferOperationForm){
		TransferOperationResponse transferOperationInfo = transferOperationService.processTransferOperation(transferOperationForm);
		return ResponseEntity.ok(transferOperationInfo);
	}
}
