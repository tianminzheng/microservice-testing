package com.tianyalan.testing.orders.model;

import javax.persistence.*;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
public class Order {

	private Long id;

	private String username;

	private OrderNumber orderNumber;

	@CreatedDate
	private Long createdAt;

	@LastModifiedDate
	private Long lastModified;

	public Order() {
	}

	public Order(String username, OrderNumber orderNumber) {
		this();
		this.username = username;
		this.orderNumber = orderNumber;
	}

	public Order(String username, String orderNumber) {
		this();
		this.username = username;
		this.orderNumber = new OrderNumber(orderNumber);
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public OrderNumber getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(OrderNumber orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Long getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}

	public Long getLastModified() {
		return lastModified;
	}

	public void setLastModified(Long lastModified) {
		this.lastModified = lastModified;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Order that = (Order) o;

		if (createdAt != null ? !createdAt.equals(that.createdAt) : that.createdAt != null)
			return false;
		return lastModified != null ? lastModified.equals(that.lastModified) : that.lastModified == null;

	}

	@Override
	public int hashCode() {
		int result = createdAt != null ? createdAt.hashCode() : 0;
		result = 31 * result + (lastModified != null ? lastModified.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Order{" + "id=" + id + ", username='" + username + '\'' + ", orderNumber=" + orderNumber + "} "
				+ super.toString();
	}
}
