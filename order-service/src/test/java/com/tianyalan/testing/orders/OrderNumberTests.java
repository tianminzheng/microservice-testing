package com.tianyalan.testing.orders;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.tianyalan.testing.orders.model.OrderNumber;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderNumberTests {

	private static final String ORDER_NUMBER = "123456789";

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void testOrderNumberIsNull() throws Exception {
		this.thrown.expect(IllegalArgumentException.class);
		this.thrown.expectMessage("Order Number must not be null");
		new OrderNumber(null);
	}

	@Test
	public void testOrderNumberIsMoreThan9Chars() throws Exception {
		this.thrown.expect(IllegalArgumentException.class);
		this.thrown.expectMessage("Order Number must be exactly 9 characters");
		new OrderNumber("1234567890");
	}

	@Test
	public void testOrderNumberIsLessThan9Chars() throws Exception {
		this.thrown.expect(IllegalArgumentException.class);
		this.thrown.expectMessage("Order Number must be exactly 9 characters");
		new OrderNumber("12345678");
	}

	@Test
	public void testOrderNumberIsExactly9Chars() throws Exception {
		OrderNumber OrderNumber = new OrderNumber(ORDER_NUMBER);
		assertThat(OrderNumber.toString()).isEqualTo(ORDER_NUMBER);
	}

	@Test
	public void testEqualsAndHashCodeOnOrderNumber() throws Exception {
		OrderNumber OrderNumber1 = new OrderNumber(ORDER_NUMBER);
		OrderNumber OrderNumber2 = new OrderNumber(ORDER_NUMBER);
		OrderNumber OrderNumber3 = new OrderNumber("000000000");
		assertThat(OrderNumber1.hashCode()).isEqualTo(OrderNumber2.hashCode());
		assertThat(OrderNumber1).isEqualTo(OrderNumber1).isEqualTo(OrderNumber2).isNotEqualTo(OrderNumber3);
	}
}
