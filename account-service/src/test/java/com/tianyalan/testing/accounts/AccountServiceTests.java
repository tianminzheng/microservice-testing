package com.tianyalan.testing.accounts;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.tianyalan.testing.accounts.model.Account;
import com.tianyalan.testing.accounts.repository.AccountRepository;
import com.tianyalan.testing.accounts.service.AccountService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class AccountServiceTests {

	private AccountService accountService;

	@MockBean
	private AccountRepository accountRepository;
	
	@Before
	public void before() {
		accountService = new AccountService(accountRepository);
	}

	@Test
	public void testGetAccountByUsername() throws Exception {
		given(this.accountRepository.findAccountByUsername("tianyalan"))
				.willReturn(new Account("tianyalan", "tianmin", "zheng", "tianyalan@gmail.com"));
		
		Account actual = accountService.getAccountByUsername("tianyalan");

		assertThat(actual.getUsername()).isEqualTo("tianyalan");
		assertThat(actual.getEmail()).isEqualTo("tianyalan@gmail.com");
	}
}


