package com.tianyalan.testing.accounts;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.tianyalan.testing.accounts.model.Account;
import com.tianyalan.testing.accounts.repository.AccountRepository;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountRepositoryTests {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private TestEntityManager entityManager;

	@Before
	public void setUp() {
		this.entityManager.persist(new Account("tianyalan", "tianmin", "zheng", "tianyalan@gmail.com"));
	}

	@Test
	public void testFindAccountByUsername() throws Exception {
		Account actual = this.accountRepository.findAccountByUsername("tianyalan");
		assertThat(actual).isNotNull();
		assertThat(actual.getUsername()).isEqualTo("tianyalan");
		assertThat(actual.getFirstName()).isEqualTo("tianmin");
		assertThat(actual.getLastName()).isEqualTo("zheng");
		assertThat(actual.getEmail()).isEqualTo("tianyalan@gmail.com");
	}

	@Test
	public void testFindAccountByNonExistedUsername() throws Exception {
		Account user = this.accountRepository.findAccountByUsername("tianyalan2");
		assertThat(user).isNull();
	}
}
