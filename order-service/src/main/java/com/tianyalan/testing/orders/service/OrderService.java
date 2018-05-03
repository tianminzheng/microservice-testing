package com.tianyalan.testing.orders.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianyalan.testing.orders.account.Account;
import com.tianyalan.testing.orders.account.AccountRestTemplateClient;
import com.tianyalan.testing.orders.model.Order;
import com.tianyalan.testing.orders.repository.OrderRepository;

import java.util.Collections;
import java.util.List;

@Service
public class OrderService {

	private final OrderRepository orderRepository;

	private final AccountRestTemplateClient accountRestTemplateClient;

	@Autowired
	public OrderService(OrderRepository orderRepository, 
			AccountRestTemplateClient accountRestTemplateClient) {
		this.orderRepository = orderRepository;
		this.accountRestTemplateClient = accountRestTemplateClient;
	}

	public List<Order> getUserOrders() {
		Account user = accountRestTemplateClient.getAuthenticatedAccount();
		if (user == null)
			return Collections.emptyList();

		List<Order> accounts = orderRepository.findOrdersByUsername(user.getUsername());
		return accounts;
	}
}
