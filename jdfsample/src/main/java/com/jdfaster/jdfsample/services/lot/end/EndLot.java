package com.jdfaster.jdfsample.services.lot.end;

import java.util.Date;

import javax.persistence.EntityManager;

import com.jdfaster.jdfsample.services.flow.MesFlowOper;
import com.jdfaster.jdfsample.services.lot.MesLot;
import com.jdfaster.jdfsample.services.lot.utils.LotUtils;
import com.jdfaster.jdfsample.utils.SvcUtils;

public class EndLot {
	public EndLotOut end(EndLotIn input) throws Exception {
		SvcUtils.checkNotEmpty("lotId", input.getLotId());
		SvcUtils.checkNotEmpty("locCode", input.getLocCode());
		SvcUtils.checkNotEmpty("operCode", input.getOperCode());
		
		EntityManager em = SvcUtils.getEm();

		MesLot lot = new MesLot();
		lot = em.find(MesLot.class, input.getLotId());
		if (!input.getLocCode().equals(lot.getLocCode()))
			throw new Exception("The Line of This Lot is not This Line: " + input.getLocCode());
		if (!input.getOperCode().equals(lot.getOperCode()))
			throw new Exception("The Oper Location of This Lot is not This Oper: " + input.getOperCode());

		int lastHistSeq = LotUtils.getCurrentSeqNo( input.getLotId());

		LotUtils.endLot(lot, ++lastHistSeq, "OPEREND");

		MesFlowOper currFlowOper = LotUtils.getByOperCode(lot.getOperCode());
		MesFlowOper nextFlowOper = LotUtils.getNextOper(currFlowOper.getFlowCode(), currFlowOper.getSeqNo());
		lot.setLotStatus("OPERIN");
		lot.setOperCode(nextFlowOper.getOperCode());
		lot.setOperInTime(new Date());
		LotUtils.updateLot(lot, ++lastHistSeq, "OPERIN");

		EndLotOut output = new EndLotOut();
		return output;
	}
}
