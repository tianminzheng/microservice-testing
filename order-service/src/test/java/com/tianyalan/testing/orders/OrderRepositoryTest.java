package com.tianyalan.testing.orders;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.tianyalan.testing.orders.model.Order;
import com.tianyalan.testing.orders.model.OrderNumber;
import com.tianyalan.testing.orders.repository.OrderRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class OrderRepositoryTest {

	private static final OrderNumber ORDER_NUMBER = new OrderNumber("098765432");

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private OrderRepository orderRepository;

	@Test
	public void testFndOrdersByUsername() throws Exception {
		this.entityManager.persist(new Order("tianyalan2", ORDER_NUMBER));
		List<Order> Order = this.orderRepository.findOrdersByUsername("tianyalan2");
		assertThat(Order).size().isEqualTo(1);
		Order actual = Order.get(0);
		assertThat(actual.getOrderNumber()).isEqualTo(ORDER_NUMBER);
		assertThat(actual.getUsername()).isEqualTo("tianyalan2");
	}

	@Test
	public void testFindOrderByOrderNumber() throws Exception {
		this.entityManager.persist(new Order("tianyalan3", ORDER_NUMBER));
		Order Order = this.orderRepository.findOrderByOrderNumber(ORDER_NUMBER);
		assertThat(Order).isNotNull();
		assertThat(Order.getOrderNumber()).isEqualTo(ORDER_NUMBER);
	}

	@Test
	public void testFindOrderByNonExistedOrderNumber() throws Exception {
		this.entityManager.persist(new Order("tianyalan2", ORDER_NUMBER));
		Order Order = this.orderRepository.findOrderByOrderNumber(new OrderNumber("000000000"));
		assertThat(Order).isNull();
	}
}
