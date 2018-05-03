package com.tianyalan.testing.accounts;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.tianyalan.testing.accounts.controller.AccountController;
import com.tianyalan.testing.accounts.model.Account;
import com.tianyalan.testing.accounts.service.AccountService;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AccountController.class)
public class AccountControllerTests {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private AccountService accountService;

	@Test
	public void testGetAccountByUsername() throws Exception {
		String content = "{\"username\": \"tianyalan\", \"firstName\": \"tianmin\", \"lastName\": \"zheng\", \"email\": \"tianyalan@gmail.com\"}";

		given(this.accountService.getAccountByUsername("tianyalan"))
				.willReturn(new Account("tianyalan", "tianmin", "zheng", "tianyalan@gmail.com"));

		this.mvc.perform(get("/account/v1/tianyalan").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().json(content));
	}
}
