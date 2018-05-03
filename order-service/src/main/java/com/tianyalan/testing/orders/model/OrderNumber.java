package com.tianyalan.testing.orders.model;

import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.util.Assert;

public class OrderNumber {

	private String orderNumber;

	public OrderNumber(String orderNumber) {
		Assert.notNull(orderNumber, "Order Number must not be null");
		Assert.isTrue(orderNumber.length() == 9, "Order Number must be exactly 9 characters");
		this.orderNumber = orderNumber;
	}

	@JsonValue
	public String getOrderNumber() {
		return orderNumber;
	}

	@Override
	public String toString() {
		return orderNumber;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof OrderNumber))
			return false;

		OrderNumber that = (OrderNumber) o;

		return getOrderNumber() != null ? getOrderNumber().equals(that.getOrderNumber())
				: that.getOrderNumber() == null;
	}

	@Override
	public int hashCode() {
		return getOrderNumber() != null ? getOrderNumber().hashCode() : 0;
	}
}
