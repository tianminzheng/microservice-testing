package com.tianyalan.testing.orders.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tianyalan.testing.orders.model.Order;
import com.tianyalan.testing.orders.service.OrderService;

import java.util.List;

@RestController
@RequestMapping(path = "order/v1")
public class OrderController {

	private OrderService orderService;

	public OrderController(OrderService accountService) {
		this.orderService = accountService;
	}

	@RequestMapping(path = "/orders")
	public ResponseEntity<List<Order>> getUserAccount() throws Exception {
		List<Order> accounts = orderService.getUserOrders();
		return new ResponseEntity<List<Order>>(accounts, HttpStatus.OK);
	}
}
