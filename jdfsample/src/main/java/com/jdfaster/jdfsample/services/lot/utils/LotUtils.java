package com.jdfaster.jdfsample.services.lot.utils;

import java.util.Date;

import com.jdfaster.jdfsample.services.lot.MesLot;
import com.jdfaster.jdfsample.services.lot.MesLotHist;

public class LotUtils {

	public static void endLot(MesLot lot, int histSeqNo, String tranCode) throws Exception {
		if (!"OPERIN".equals(lot.getLotStatus()))
			throw new Exception("This Lot Status is Not OPERIN: " + lot.getLotStatus());
		if (lot.getOperStartTime() == null || lot.getOperStartTime().getTime() < lot.getOperInTime().getTime())
			lot.setOperStartTime(lot.getOperInTime());
		lot.setLotStatus("OPEREND");
		lot.setOperEndTime(new Date());
		LotUtils.updateLot(lot, histSeqNo, tranCode);
	}

	public static void updateLot(MesLot lot, int histSeqNo, String tranCode) throws Exception {
		MesLotHist hist = new MesLotHist();
		hist.setLotId(lot.getLotId());
		hist.setSeqNo(histSeqNo);
		hist.setTranCode(tranCode);
		hist.setLotStatus(lot.getLotStatus());
		hist.setMatCode(lot.getMatCode());
		hist.setLotQty(lot.getLotQty());
		hist.setLocCode(lot.getLocCode());
		hist.setFlowCode(lot.getFlowCode());
		hist.setOperCode(lot.getOperCode());
		hist.setOperInTime(lot.getOperInTime());
		hist.setOperStartTime(lot.getOperStartTime());
		hist.setOperEndTime(lot.getOperEndTime());
		hist.setOrderId(lot.getOrderId());
		hist.setCompOrderId(lot.getCompOrderId());
		// TODO
		// em.merge(lot);
		// em.persist(hist);
	}

}
