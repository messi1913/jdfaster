package com.jdfaster.jdfsample.services.lot.create;

import java.util.Date;
import java.util.UUID;

import com.jdfaster.jdfsample.services.flow.MesFlowOper;
import com.jdfaster.jdfsample.services.lot.MesLot;
import com.jdfaster.jdfsample.services.lot.utils.LotUtils;
import com.jdfaster.jdfsample.services.mat.MesMat;
import com.jdfaster.jdfsample.services.order.MesOrder;
import com.jdfaster.jdfsample.utils.SvcUtils;

public class CreateLot {
	public CreateLotOut create(CreateLotIn input) throws Exception {
		SvcUtils.checkNotEmpty("locCode", input.getLocCode());
		SvcUtils.checkNotEmpty("operCode", input.getOperCode());
		SvcUtils.checkNotEmpty("orderId", input.getOrderId());

		// TODO
		MesOrder order = null;
		if ("END".equals(order.getOrderStatus()))
			throw new Exception("This Order Already Ended: " + input.getOrderId());

		// TODO
		MesMat prod = null;

		// TODO
		MesFlowOper oper = null;
		if (!input.getOperCode().equals(oper.getOperCode()))
			throw new Exception("This Oper is Not the Starting Oper: " + input.getOperCode());

		if (SvcUtils.isEmpty(input.getLotId())) {
			input.setLotId(UUID.randomUUID().toString());
		}

		MesLot lot = new MesLot();
		lot.setLotId(input.getLotId());
		lot.setMatCode(prod.getMatCode());
		lot.setLotQty(1);
		lot.setLocCode(input.getLocCode());
		lot.setFlowCode(prod.getFlowCode());
		lot.setOrderId(order.getOrderId());

		lot.setLotStatus("OPERIN");
		lot.setOperCode(input.getOperCode());
		lot.setOperInTime(new Date());
		lot.setpLotId(" ");
		// TODO
		// em.persist(lot);
		LotUtils.updateLot(lot, 1, "OPERIN");

		if (!"START".equals(order.getOrderStatus())) {
			order.setOrderStatus("START");
			if (order.getStartTime() == null)
				order.setStartTime(new Date());
		}
		order.setInputQty(order.getInputQty() + 1);
		// TODO
		// em.persist(order);

		CreateLotOut output = new CreateLotOut();
		return output;
	}
}
