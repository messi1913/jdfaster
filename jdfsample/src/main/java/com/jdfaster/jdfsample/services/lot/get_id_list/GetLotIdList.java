package com.jdfaster.jdfsample.services.lot.get_id_list;

import java.util.ArrayList;
import java.util.List;

import com.jdfaster.jdfsample.services.lot.LotServices;
import com.jdfaster.jdfsample.services.lot.MesLot;
import com.jdfaster.jdfsample.services.lot.get_list.GetLotListIn;
import com.jdfaster.jdfsample.services.lot.get_list.GetLotListOut;
import com.jdfaster.jdfsample.utils.SvcUtils;

public class GetLotIdList {
	public GetLotIdListOut getIdList(GetLotIdListIn input) throws Exception {
		List<String> list = new ArrayList<String>();
		{
			List<MesLot> lotList;
			{
				GetLotListIn reqIn = new GetLotListIn();
				reqIn.setOrderId(input.getOrderId());
				reqIn.setOperCode(input.getOperCode());
				reqIn.setLotStatus(input.getLotStatus());
				reqIn.setLotStatusIn(input.getLotStatusIn());
				GetLotListOut reqOut = SvcUtils.getBean(LotServices.class).getList(reqIn);
				lotList = reqOut.getList();
			}

			for (int i = 0; i < 10; i++)
				list.add(lotList.get(i).getLotId());
		}

		GetLotIdListOut output = new GetLotIdListOut();
		output.setList(list);
		return output;
	}
}
