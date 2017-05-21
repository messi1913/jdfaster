package com.jdfaster.jdfsample.services.test.scen01;

import java.util.ArrayList;
import java.util.List;

import com.jdfaster.jdfsample.services.lot.LotServices;
import com.jdfaster.jdfsample.services.lot.MesLot;
import com.jdfaster.jdfsample.services.lot.create.CreateLotIn;
import com.jdfaster.jdfsample.services.lot.create.CreateLotOut;
import com.jdfaster.jdfsample.services.lot.end.EndLotIn;
import com.jdfaster.jdfsample.services.lot.get_list.GetLotListIn;
import com.jdfaster.jdfsample.services.lot.get_list.GetLotListOut;
import com.jdfaster.jdfsample.services.lot.pack.PackLotIn;
import com.jdfaster.jdfsample.services.lot.ship.ShipLotIn;
import com.jdfaster.jdfsample.services.order.MesOrder;
import com.jdfaster.jdfsample.services.test.utils.MesTestUtils;
import com.jdfaster.jdfsample.utils.SvcUtils;

public class TestScen01 {

	public TestScen01Out scen01(TestScen01In input) throws Exception {
		String lineCode = MesTestUtils.getLineCode();
		try {
			test(lineCode);
		} finally {
			MesTestUtils.releaseLineCode(lineCode);
		}

		TestScen01Out output = new TestScen01Out();
		return output;
	}

	private void test(String lineCode) throws Exception {
		MesOrder order = MesTestUtils.getOrder(getClass().getSimpleName(), lineCode, "M-GS8");

		String lotId;

		// 1. Assy
		{
			{
				CreateLotIn reqIn = new CreateLotIn();
				reqIn.setLocCode(order.getLocCode());
				reqIn.setOperCode("O-ASSY");
				reqIn.setOrderId(order.getOrderId());
				CreateLotOut reqOut = SvcUtils.getBean(LotServices.class).create(reqIn);
				lotId = reqOut.getLotId();
			}

			{
				EndLotIn reqIn = new EndLotIn();
				reqIn.setLocCode(order.getLocCode());
				reqIn.setOperCode("O-ASSY");
				reqIn.setLotId(lotId);
				SvcUtils.getBean(LotServices.class).end(reqIn);
			}
		}

		// 2. Test
		{
			EndLotIn reqIn = new EndLotIn();
			reqIn.setLocCode(order.getLocCode());
			reqIn.setOperCode("O-TEST");
			reqIn.setLotId(lotId);
			SvcUtils.getBean(LotServices.class).end(reqIn);
		}

		// 3. Pack
		{
			List<String> lotIdList = new ArrayList<String>();
			{
				List<MesLot> list;
				{
					GetLotListIn reqIn = new GetLotListIn();
					reqIn.setOrderId(order.getOrderId());
					reqIn.setOperCode("O-PACK");
					reqIn.setLotStatusIn(newArrayList("OPERIN", "OPERSTART", ""));
					GetLotListOut reqOut = SvcUtils.getBean(LotServices.class).getList(reqIn);
					list = reqOut.getList();
				}

				if (list.size() >= 10) {
					for (int i = 0; i < 10; i++)
						lotIdList.add(list.get(i).getLotId());
				}
			}

			if (!lotIdList.isEmpty()) {
				PackLotIn reqIn = new PackLotIn();
				reqIn.setLocCode(order.getLocCode());
				reqIn.setOperCode("O-PACK");
				reqIn.setLotIdList(lotIdList);
				SvcUtils.getBean(LotServices.class).pack(reqIn);
			}
		}

		// 4. Ship
		{
			List<String> lotIdList = new ArrayList<String>();
			{
				List<MesLot> list;
				{
					GetLotListIn reqIn = new GetLotListIn();
					reqIn.setOrderId(order.getOrderId());
					reqIn.setOperCode("O-PACK");
					reqIn.setLotStatusIn(newArrayList("OPERIN", "OPERSTART", ""));
					GetLotListOut reqOut = SvcUtils.getBean(LotServices.class).getList(reqIn);
					list = reqOut.getList();
				}

				if (list.size() >= 10) {
					for (int i = 0; i < 10; i++)
						lotIdList.add(list.get(i).getLotId());
				}
			}

			if (!lotIdList.isEmpty()) {
				ShipLotIn reqIn = new ShipLotIn();
				reqIn.setLocCode(order.getLocCode());
				reqIn.setOperCode("O-SHIP");
				reqIn.setLotIdList(lotIdList);
				SvcUtils.getBean(LotServices.class).ship(reqIn);
			}
		}
	}

	@SafeVarargs
	public static <T> ArrayList<T> newArrayList(T... elements) {
		if (elements == null) {
			return null;
		}
		ArrayList<T> list = new ArrayList<T>();
		java.util.Collections.addAll(list, elements);
		return list;
	}
}
