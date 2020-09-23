package com.paymybuddy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.paymybuddy.entity.Account;
import com.paymybuddy.entity.Friendship;
import com.paymybuddy.service.AccountService;
import com.paymybuddy.service.FriendshipService;

@RestController
public class TesterController {
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private FriendshipService friendshipService;
	
	@PostMapping("/createAccount")
	public ResponseEntity<Account> createAccount(@RequestBody Account accountToCreate){
		return ResponseEntity.ok(accountService.createAccount(accountToCreate));
	}

	@PostMapping("/addFriend")
	public ResponseEntity<Friendship> addFriend (@RequestBody Friendship friendshipToAdd){
		return ResponseEntity.ok(friendshipService.addFriendship(friendshipToAdd));
	}
}
