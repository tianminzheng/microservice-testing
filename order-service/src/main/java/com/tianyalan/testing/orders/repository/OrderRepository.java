package com.tianyalan.testing.orders.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.tianyalan.testing.orders.model.Order;
import com.tianyalan.testing.orders.model.OrderNumber;

import java.util.List;

public interface OrderRepository extends PagingAndSortingRepository<Order, Long> {

	List<Order> findOrdersByUsername(@Param("username") String username);

	Order findOrderByOrderNumber(@Param("orderNumber") OrderNumber orderNumber);
}
