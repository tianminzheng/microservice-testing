package com.tianyalan.testing.orders;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.tianyalan.testing.orders.controller.OrderController;
import com.tianyalan.testing.orders.model.Order;
import com.tianyalan.testing.orders.service.OrderService;

import java.util.Collections;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(OrderController.class)
public class OrderControllerTests {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private OrderService orderService;

	@Test
	public void testGetUserOrders() throws Exception {
		String content = "[{\"username\": \"tianyalan\", \"orderNumber\": \"123456789\"}]";

		given(this.orderService.getUserOrders())
				.willReturn(Collections.singletonList(new Order("tianyalan", "123456789")));

		this.mvc.perform(get("/order/v1/orders").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().json(content));
	}
}
