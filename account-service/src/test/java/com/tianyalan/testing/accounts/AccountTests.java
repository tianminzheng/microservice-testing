package com.tianyalan.testing.accounts;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

import com.tianyalan.testing.accounts.model.Account;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@JsonTest
public class AccountTests {

	private Account account;

	@Autowired
	private JacksonTester<Account> json;

	@Before
	public void setUp() throws Exception {
		Account user = new Account("tianyalan", "tianmin", "zheng", "tianyalan@gmail.com");
		user.setId(0L);
		user.setCreatedAt(12345L);
		user.setLastModified(12346L);

		this.account = user;
	}

	@Test
	public void testSerializeJson() throws Exception {
		assertThat(this.json.write(account)).isEqualTo("account.json");
		assertThat(this.json.write(account)).isEqualToJson("account.json");
		assertThat(this.json.write(account)).hasJsonPathStringValue("@.username");

		assertJsonPropertyEquals("@.username", "tianyalan");
		assertJsonPropertyEquals("@.firstName", "tianmin");
		assertJsonPropertyEquals("@.lastName", "zheng");
		assertJsonPropertyEquals("@.email", "tianyalan@gmail.com");
	}

	@Test
	public void testDeserializeJson() throws Exception {
		String content = "{\"username\": \"tianyalan\", \"firstName\": \"tianmin\", "
				+ "\"lastName\": \"zheng\", \"email\": \"tianyalan@gmail.com\"}";

		assertThat(this.json.parse(content))
				.isEqualTo(new Account("tianyalan", "tianmin", "zheng", "tianyalan@gmail.com"));
		assertThat(this.json.parseObject(content).getUsername()).isEqualTo("tianyalan");
	}

	private void assertJsonPropertyEquals(String key, String value) throws java.io.IOException {
		assertThat(this.json.write(account)).extractingJsonPathStringValue(key).isEqualTo(value);
	}
}
