package com.jdfaster.jdfsample.services.order.create_test;

import javax.persistence.EntityManager;

import com.jdfaster.jdfsample.services.order.OrderServices;
import com.jdfaster.jdfsample.services.order.create.CreateOrderIn;
import com.jdfaster.jdfsample.services.order.create.CreateOrderOut;
import com.jdfaster.jdfsample.services.test.MesTestStatus;
import com.jdfaster.jdfsample.utils.SvcUtils;

public class CreateOrderTest {
	public CreateOrderTestOut createTest(CreateOrderTestIn input) throws Exception {
		SvcUtils.checkNotEmpty("testName", input.getTestName());
		SvcUtils.checkNotEmpty("locCode", input.getLocCode());
		SvcUtils.checkNotEmpty("matCode", input.getMatCode());
		SvcUtils.checkNotEmpty("orderQty", input.getOrderQty());

		String orderId;

		{
			CreateOrderIn reqIn = new CreateOrderIn();
			reqIn.setOrderDesc(input.getTestName() + " Order");
			reqIn.setMatCode(input.getMatCode());
			reqIn.setLocCode(input.getLocCode());
			reqIn.setOrderQty(input.getOrderQty());
			CreateOrderOut reqOut = SvcUtils.getBean(OrderServices.class).create(reqIn);
			orderId = reqOut.getOrderId();
		}

		EntityManager em = SvcUtils.getEm();

		{
			MesTestStatus testStatus = new MesTestStatus();
			testStatus.setTestName(input.getTestName());
			testStatus.setLocCode(input.getLocCode());
			testStatus.setMatCode(input.getMatCode());
			testStatus = em.find(MesTestStatus.class, testStatus);

			boolean insert = testStatus == null;
			if (insert)
				testStatus = new MesTestStatus();
			testStatus.setTestName(input.getTestName());
			testStatus.setLocCode(input.getLocCode());
			testStatus.setMatCode(input.getMatCode());
			testStatus.setOrderId(orderId);
			testStatus.setLeftQty(input.getOrderQty());
			if (insert) {
				em.persist(testStatus);
			} else {
				em.merge(testStatus);
			}
		}

		CreateOrderTestOut output = new CreateOrderTestOut();
		output.setOrderId(orderId);
		return output;
	}
}
