package com.tianyalan.testing.accounts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.tianyalan.testing.accounts.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

	Account findAccountByUsername(@Param("username") String username);
}
