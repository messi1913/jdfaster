package com.jdfaster.jdfsample.services.order.create;

import java.util.UUID;

import javax.persistence.EntityManager;

import org.springframework.transaction.annotation.Transactional;

import com.jdfaster.jdfsample.services.order.MesOrder;
import com.jdfaster.jdfsample.utils.SvcUtils;

public class CreateOrder {
	public CreateOrderOut create(CreateOrderIn input) throws Exception {
		SvcUtils.checkNotEmpty("matCode", input.getMatCode());
		SvcUtils.checkNotEmpty("locCode", input.getLocCode());
		SvcUtils.checkNotEmpty("orderQty", input.getOrderQty());

		EntityManager em = SvcUtils.getEm();

		if (SvcUtils.isEmpty(input.getOrderId())) {
			input.setOrderId(UUID.randomUUID().toString());
		}

		MesOrder order = em.find(MesOrder.class, input.getOrderId());

		if (order != null)
			throw new Exception("This Order Already Exist");

		order = new MesOrder();
		order.setOrderId(input.getOrderId());
		order.setOrderDesc(input.getOrderDesc());
		order.setOrderStatus("CREATE");
		order.setMatCode(input.getMatCode());
		order.setLocCode(input.getLocCode());
		order.setOrderQty(input.getOrderQty());
		order.setInputQty(0);
		order.setOutputQty(0);
		em.persist(order);

		CreateOrderOut output = new CreateOrderOut();
		output.setOrderId(input.getOrderId());
		return output;
	}
}
