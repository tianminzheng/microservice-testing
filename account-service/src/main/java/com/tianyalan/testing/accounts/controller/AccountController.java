package com.tianyalan.testing.accounts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tianyalan.testing.accounts.model.Account;
import com.tianyalan.testing.accounts.service.AccountService;

@RestController
@RequestMapping(path = "/account/v1")
public class AccountController {

	private AccountService accountService;

	@Autowired
	public AccountController(AccountService userService) {
		this.accountService = userService;
	}

	@RequestMapping(path = "/{username}")
	public ResponseEntity<Account> me(@PathVariable String username) throws Exception {
		return ResponseEntity.ok().body(accountService.getAccountByUsername(username));
	}
}
