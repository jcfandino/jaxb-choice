package com.example;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.junit.Test;

import com.example.order.CreditType;
import com.example.order.Order;

public class OrderTest {

	@Test
	public void test() throws Exception {
		Order order = new Order();
		CreditType credit = new CreditType();
		credit.setNumber("4111111111111111");
		order.getCreditOrGiftCardOrAny().add(credit);

		JAXBContext jaxbContext = JAXBContext.newInstance(Order.class);
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.marshal(order, System.out);
	}

}
