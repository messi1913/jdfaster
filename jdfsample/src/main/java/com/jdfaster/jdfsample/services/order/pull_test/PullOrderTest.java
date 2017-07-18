package com.jdfaster.jdfsample.services.order.pull_test;

import javax.persistence.EntityManager;

import com.jdfaster.jdfsample.services.test.MesTestStatus;
import com.jdfaster.jdfsample.utils.SvcUtils;

public class PullOrderTest {
	public PullOrderTestOut pullTest(PullOrderTestIn input) throws Exception {
		SvcUtils.checkNotEmpty("testName", input.getTestName());
		SvcUtils.checkNotEmpty("lineCode", input.getLocCode());
		SvcUtils.checkNotEmpty("matCode", input.getMatCode());

		EntityManager em = SvcUtils.getEm();

		MesTestStatus testStatus = new MesTestStatus();
		testStatus.setTestName(input.getTestName());
		testStatus.setLocCode(input.getLocCode());
		testStatus.setMatCode(input.getMatCode());
		testStatus = em.find(MesTestStatus.class, testStatus);

		PullOrderTestOut output = new PullOrderTestOut();

		if (testStatus == null || testStatus.getLeftQty() < 1)
			return output;

		testStatus.setLeftQty(testStatus.getLeftQty() - 1);
		em.merge(testStatus);

		// MesOrder order = em.find(MesOrder.class, testStatus.getOrderId());
		output.setOrderId(testStatus.getOrderId());
		return output;
	}
}
