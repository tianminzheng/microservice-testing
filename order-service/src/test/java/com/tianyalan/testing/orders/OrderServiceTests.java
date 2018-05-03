package com.tianyalan.testing.orders;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.tianyalan.testing.orders.account.Account;
import com.tianyalan.testing.orders.account.AccountRestTemplateClient;
import com.tianyalan.testing.orders.model.Order;
import com.tianyalan.testing.orders.model.OrderNumber;
import com.tianyalan.testing.orders.repository.OrderRepository;
import com.tianyalan.testing.orders.service.OrderService;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class OrderServiceTests {

	@MockBean
	private AccountRestTemplateClient accountRestTemplateClient;

	@MockBean
	private OrderRepository orderRepository;

	private OrderService orderService;

	@Before
	public void before() {
		orderService = new OrderService(orderRepository, accountRestTemplateClient);
	}

	@Test
	public void testGetUserOrders() throws Exception {
		given(this.orderRepository.findOrdersByUsername("tianyalan"))
				.willReturn(Collections.singletonList(new Order("user", new OrderNumber("123456789"))));
		given(this.accountRestTemplateClient.getAuthenticatedAccount()).willReturn(new Account(0L, "user", "John", "Doe"));

		List<Order> actual = orderService.getUserOrders();

		assertThat(actual).size().isEqualTo(1);
		assertThat(actual.get(0).getUsername()).isEqualTo("user");
		assertThat(actual.get(0).getOrderNumber()).isEqualTo(new OrderNumber("123456789"));
	}
}
