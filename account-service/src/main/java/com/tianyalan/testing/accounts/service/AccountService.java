package com.tianyalan.testing.accounts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianyalan.testing.accounts.model.Account;
import com.tianyalan.testing.accounts.repository.AccountRepository;

@Service
public class AccountService {

	private final AccountRepository accountRepository;

	@Autowired
	public AccountService(AccountRepository userRepository) {
		this.accountRepository = userRepository;
	}

	public Account getAccountByUsername(String username) {
		return accountRepository.findAccountByUsername(username);
	}
}
