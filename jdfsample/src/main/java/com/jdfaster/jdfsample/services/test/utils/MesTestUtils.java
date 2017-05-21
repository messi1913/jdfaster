package com.jdfaster.jdfsample.services.test.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.persistence.EntityManager;

import com.jdfaster.jdfsample.services.lot.LotServices;
import com.jdfaster.jdfsample.services.lot.instore.InstoreLotIn;
import com.jdfaster.jdfsample.services.lot.instore.InstoreLotOut;
import com.jdfaster.jdfsample.services.lot.send.SendLotIn;
import com.jdfaster.jdfsample.services.mat.MatServices;
import com.jdfaster.jdfsample.services.mat.MesMatComp;
import com.jdfaster.jdfsample.services.mat.get_comp_list.GetMatCompListIn;
import com.jdfaster.jdfsample.services.mat.get_comp_list.GetMatCompListOut;
import com.jdfaster.jdfsample.services.order.MesOrder;
import com.jdfaster.jdfsample.services.order.OrderServices;
import com.jdfaster.jdfsample.services.order.create.CreateOrderIn;
import com.jdfaster.jdfsample.services.order.create.CreateOrderOut;
import com.jdfaster.jdfsample.services.test.MesTestStatus;
import com.jdfaster.jdfsample.utils.SvcUtils;

public class MesTestUtils {
	private static int lineSize = 200;
	private static List<String> LINES_ALL = new ArrayList<String>();
	private static List<String> LINES_RUNNING = new ArrayList<String>(lineSize);

	static {
		for (int i = 1; i <= lineSize; i++) {
			String index;
			{
				if (i < 10)
					index = "00" + i;
				else if (i < 100)
					index = "0" + i;
				else
					index = "" + i;
			}
			LINES_ALL.add("L-" + index);
		}
	}

	public static List<String> getAllLineCodes() {
		return LINES_ALL;
	}

	public static MesOrder getOrder(String testName, String lineCode, String matCode) throws Exception {
		EntityManager em = SvcUtils.getEm();

		// Check Test Status
		MesTestStatus testStatus = new MesTestStatus();
		testStatus.setTestName(testName);
		testStatus.setLocCode(lineCode);
		testStatus.setMatCode(matCode);
		testStatus = em.find(MesTestStatus.class, testStatus);
		if (testStatus != null && testStatus.getLeftQty() > 0) {
			testStatus.setLeftQty(testStatus.getLeftQty() - 1);
			MesOrder order = em.find(MesOrder.class, testStatus.getOrderId());
			return order;
		}

		String orderId;

		// Create Order
		{
			CreateOrderIn reqIn = new CreateOrderIn(); 
			reqIn.setOrderDesc(testName + " Order");
			reqIn.setMatCode(matCode);
			reqIn.setLocCode(lineCode);
			reqIn.setOrderQty(100);
			CreateOrderOut reqOut = SvcUtils.getBean(OrderServices.class).create(reqIn);
			orderId = reqOut.getOrderId();
		}

		// View Comp List
		List<MesMatComp> compList;
		{
			GetMatCompListIn reqIn = new GetMatCompListIn();
			reqIn.setMatCode(matCode);
			GetMatCompListOut reqOut = SvcUtils.getBean(MatServices.class).getCompList(reqIn);
			compList = reqOut.getList();
		}

		List<String> compLotIdList = new ArrayList<String>(compList.size());

		// Instore Component
		{
			for (MesMatComp item : compList) {
				InstoreLotIn reqIn = new InstoreLotIn();
				reqIn.setLocCode("S-RM");
				reqIn.setMatCode(item.getCompMatCode());
				reqIn.setLotQty(100);
				InstoreLotOut reqOut = SvcUtils.getBean(LotServices.class).instore(reqIn);
				compLotIdList.add(reqOut.getLotId());
			}
		}

		// Send Component
		{
			for (String compLotId : compLotIdList) {
				SendLotIn reqIn = new SendLotIn();
				reqIn.setLotId(compLotId);
				reqIn.setLocCode(lineCode);
				SvcUtils.getBean(LotServices.class).send(reqIn);
			}
		}

		MesOrder order = em.find(MesOrder.class, orderId);
		{
			boolean insert = testStatus == null;
			if (insert)
				testStatus = new MesTestStatus();
			testStatus.setTestName(testName);
			testStatus.setLocCode(lineCode);
			testStatus.setMatCode(matCode);
			testStatus.setOrderId(orderId);
			testStatus.setLeftQty(order.getOrderQty() - 1);
			if (insert) {
				em.persist(testStatus);
			} else {
				em.merge(testStatus);
			}
		}
		return order;
	}

	public static String getLineCode() {
		synchronized (LINES_ALL) {
			List<String> linesWaiting = new ArrayList<String>(LINES_ALL);
			linesWaiting.removeAll(LINES_RUNNING);
			if (linesWaiting.isEmpty())
				throw new IllegalStateException("Line Master Data is not enough for Testing");
			int index = ThreadLocalRandom.current().nextInt(linesWaiting.size());
			String lineCode = linesWaiting.get(index);
			LINES_RUNNING.add(lineCode);
			return lineCode;
		}
	}

	public static void releaseLineCode(String lineCode) {
		synchronized (LINES_ALL) {
			LINES_RUNNING.remove(lineCode);
		}
	}
}
