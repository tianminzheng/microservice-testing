package com.tianyalan.testing.orders;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

import com.tianyalan.testing.orders.model.Order;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@JsonTest
public class OrderTests {

	private Order order;

	@Autowired
	private JacksonTester<Order> orderJson;

	@Before
	public void setUp() throws Exception {
		Order order = new Order("tianyalan", "123456789");
		order.setId(0L);
		order.setCreatedAt(12345L);
		order.setLastModified(12346L);

		this.order = order;
	}

	@Test
	public void testSerializeJson() throws Exception {
		assertThat(this.orderJson.write(order)).isEqualTo("order.json");
		assertThat(this.orderJson.write(order)).isEqualToJson("order.json");
		assertThat(this.orderJson.write(order)).hasJsonPathStringValue("@.username");

		assertThat(this.orderJson.write(order)).extractingJsonPathStringValue("@.username").isEqualTo("tianyalan");

		assertThat(this.orderJson.write(order)).extractingJsonPathStringValue("@.orderNumber").isEqualTo("123456789");
	}

	@Test
	public void testDeserializeJson() throws Exception {
		String content = "{\"username\": \"tianyalan\", \"orderNumber\": \"123456789\"}";
		assertThat(this.orderJson.parse(content)).isEqualTo(new Order("tianyalan", "123456789"));
		assertThat(this.orderJson.parseObject(content).getUsername()).isEqualTo("tianyalan");
	}
}
