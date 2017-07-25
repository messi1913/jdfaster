package com.jdfaster.jdfsample.services.test.scen01;

import java.util.List;

import com.jdfaster.jdfsample.services.lot.LotServices;
import com.jdfaster.jdfsample.services.lot.create.CreateLotIn;
import com.jdfaster.jdfsample.services.lot.create.CreateLotOut;
import com.jdfaster.jdfsample.services.lot.end.EndLotIn;
import com.jdfaster.jdfsample.services.lot.get_id_list.GetLotIdListIn;
import com.jdfaster.jdfsample.services.lot.get_id_list.GetLotIdListOut;
import com.jdfaster.jdfsample.services.lot.pack.PackLotIn;
import com.jdfaster.jdfsample.services.lot.ship.ShipLotIn;
import com.jdfaster.jdfsample.services.test.TestServices;
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
		// MD 데이터 넣기.
//		SvcUtils.getBean(TestServices.class).md();
		
		String orderId = MesTestUtils.getOrderId(getClass().getSimpleName(), lineCode, "M-GS8");
		String lotId;

		// 1. Assy
		{
			{
				CreateLotIn reqIn = new CreateLotIn();
				reqIn.setLocCode(lineCode);
				reqIn.setOperCode("O-ASSY");
				reqIn.setOrderId(orderId);
				CreateLotOut reqOut = SvcUtils.getBean(LotServices.class).create(reqIn);
				lotId = reqOut.getLotId();
			}

			{
				EndLotIn reqIn = new EndLotIn();
				reqIn.setLocCode(lineCode);
				reqIn.setOperCode("O-ASSY");
				reqIn.setLotId(lotId);
				SvcUtils.getBean(LotServices.class).end(reqIn);
			}
		}

		// 2. Test
		{
			EndLotIn reqIn = new EndLotIn();
			reqIn.setLocCode(lineCode);
			reqIn.setOperCode("O-TEST");
			reqIn.setLotId(lotId);
			SvcUtils.getBean(LotServices.class).end(reqIn);
		}

		// 3. Pack
		{
			List<String> lotIdList;
			{
				GetLotIdListIn reqIn = new GetLotIdListIn();
				reqIn.setOrderId(orderId);
				reqIn.setOperCode("O-PACK");
				reqIn.setLotStatusIn(SvcUtils.newArrayList("OPERIN", "OPERSTART"));
				GetLotIdListOut reqOut = SvcUtils.getBean(LotServices.class).getIdList(reqIn);
				lotIdList = reqOut.getList();
			}

			if (!SvcUtils.isEmpty(lotIdList) && lotIdList.size() >= 10) {
				PackLotIn reqIn = new PackLotIn();
				reqIn.setLocCode(lineCode);
				reqIn.setOperCode("O-PACK");
				reqIn.setLotIdList(lotIdList);
				SvcUtils.getBean(LotServices.class).pack(reqIn);
			}
		}

		// 4. Ship
		{
			List<String> lotIdList;
			{
				GetLotIdListIn reqIn = new GetLotIdListIn();
				reqIn.setOrderId(orderId);
				reqIn.setOperCode("O-SHIP");
				reqIn.setLotStatusIn(SvcUtils.newArrayList("OPERIN", "OPERSTART"));
				GetLotIdListOut reqOut = SvcUtils.getBean(LotServices.class).getIdList(reqIn);
				lotIdList = reqOut.getList();
			}

			if (!SvcUtils.isEmpty(lotIdList) && lotIdList.size() >= 10) {
				ShipLotIn reqIn = new ShipLotIn();
				reqIn.setLocCode(lineCode);
				reqIn.setOperCode("O-SHIP");
				reqIn.setLotIdList(lotIdList);
				SvcUtils.getBean(LotServices.class).ship(reqIn);
			}
		}
	}
}
