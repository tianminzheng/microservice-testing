package com.tianyalan.testing.orders.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class OrderNumberAttributeConverter implements AttributeConverter<OrderNumber, String> {

	@Override
	public String convertToDatabaseColumn(OrderNumber attribute) {
		return attribute.toString();
	}

	@Override
	public OrderNumber convertToEntityAttribute(String dbData) {
		return new OrderNumber(dbData);
	}

}
