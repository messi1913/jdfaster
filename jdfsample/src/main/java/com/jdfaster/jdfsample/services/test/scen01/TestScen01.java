package com.jdfaster.jdfsample.services.test.scen01;

import java.util.ArrayList;
import java.util.List;

import com.jdfaster.jdfsample.services.lot.create.CreateLot;
import com.jdfaster.jdfsample.services.lot.create.CreateLotIn;
import com.jdfaster.jdfsample.services.lot.create.CreateLotOut;
import com.jdfaster.jdfsample.services.lot.end.EndLot;
import com.jdfaster.jdfsample.services.lot.end.EndLotIn;
import com.jdfaster.jdfsample.services.lot.pack.PackLot;
import com.jdfaster.jdfsample.services.lot.pack.PackLotIn;
import com.jdfaster.jdfsample.services.lot.ship.ShipLot;
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
		MesOrder order = MesTestUtils.getOrder(lineCode);

		String lotId;

		// 1. Assy
		{
			{
				CreateLotIn reqIn = new CreateLotIn();
				reqIn.setLocCode(order.getLocCode());
				reqIn.setOperCode("O-ASSY");
				reqIn.setOrderId(order.getOrderId());
				CreateLotOut reqOut = SvcUtils.getBean(CreateLot.class).create(reqIn);
				lotId = reqOut.getLotId();
			}

			{
				EndLotIn reqIn = new EndLotIn();
				reqIn.setLocCode(order.getLocCode());
				reqIn.setOperCode("O-ASSY");
				reqIn.setLotId(lotId);
				SvcUtils.getBean(EndLot.class).end(reqIn);
			}
		}

		// 2. Test
		{
			EndLotIn reqIn = new EndLotIn();
			reqIn.setLocCode(order.getLocCode());
			reqIn.setOperCode("O-TEST");
			reqIn.setLotId(lotId);
			SvcUtils.getBean(EndLot.class).end(reqIn);
		}

		// 3. Pack
		{
			// TODO check pack lot size
			int lotSize;
			{
				lotSize = 10;
			}

			if (lotSize >= 10) {
				List<String> lotIdList;
				{
					lotIdList = new ArrayList<String>();
				}

				PackLotIn reqIn = new PackLotIn();
				reqIn.setLocCode(order.getLocCode());
				reqIn.setOperCode("O-TEST");
				reqIn.setLotIdList(lotIdList);
				SvcUtils.getBean(PackLot.class).pack(reqIn);
			}
		}

		// 4. Ship
		{
			// TODO check ship lot size
			int lotSize;
			{
				lotSize = 100;
			}

			if (lotSize >= 100) {
				List<String> lotIdList;
				{
					lotIdList = new ArrayList<String>();
				}

				ShipLotIn reqIn = new ShipLotIn();
				reqIn.setLocCode(order.getLocCode());
				reqIn.setOperCode("O-TEST");
				reqIn.setLotIdList(lotIdList);
				SvcUtils.getBean(ShipLot.class).ship(reqIn);
			}
		}
	}
}
