package com.jdfaster.jdfsample.services.test.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.jdfaster.jdfsample.services.lot.LotServices;
import com.jdfaster.jdfsample.services.lot.instore.InstoreLotIn;
import com.jdfaster.jdfsample.services.lot.instore.InstoreLotOut;
import com.jdfaster.jdfsample.services.lot.send.SendLotIn;
import com.jdfaster.jdfsample.services.mat.MatServices;
import com.jdfaster.jdfsample.services.mat.MesMatComp;
import com.jdfaster.jdfsample.services.mat.get_comp_list.GetMatCompListIn;
import com.jdfaster.jdfsample.services.mat.get_comp_list.GetMatCompListOut;
import com.jdfaster.jdfsample.services.order.OrderServices;
import com.jdfaster.jdfsample.services.order.create_test.CreateOrderTestIn;
import com.jdfaster.jdfsample.services.order.pull_test.PullOrderTestIn;
import com.jdfaster.jdfsample.services.order.pull_test.PullOrderTestOut;
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

	public static String getOrderId(String testName, String lineCode, String matCode) throws Exception {
		// Pull Test Status
		{
			PullOrderTestIn reqIn = new PullOrderTestIn();
			reqIn.setTestName(testName);
			reqIn.setLocCode(lineCode);
			reqIn.setMatCode(matCode);
			PullOrderTestOut reqOut = SvcUtils.getBean(OrderServices.class).pullTest(reqIn);
			if (!SvcUtils.isEmpty(reqOut.getOrderId()))
				return reqOut.getOrderId();
		}

		// Create Order
		{
			CreateOrderTestIn reqIn = new CreateOrderTestIn();
			reqIn.setTestName(testName);
			reqIn.setMatCode(matCode);
			reqIn.setLocCode(lineCode);
			reqIn.setOrderQty(100);
			SvcUtils.getBean(OrderServices.class).createTest(reqIn);
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

		// Pull Test Status
		{
			PullOrderTestIn reqIn = new PullOrderTestIn();
			reqIn.setTestName(testName);
			reqIn.setLocCode(lineCode);
			reqIn.setMatCode(matCode);
			PullOrderTestOut reqOut = SvcUtils.getBean(OrderServices.class).pullTest(reqIn);
			return reqOut.getOrderId();
		}
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
