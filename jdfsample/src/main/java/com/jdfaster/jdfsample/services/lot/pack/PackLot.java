package com.jdfaster.jdfsample.services.lot.pack;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;

import org.springframework.transaction.annotation.Transactional;

import com.jdfaster.jdfsample.services.lot.LotServices;
import com.jdfaster.jdfsample.services.lot.MesLot;
import com.jdfaster.jdfsample.services.lot.end.EndLotIn;
import com.jdfaster.jdfsample.utils.SvcUtils;

public class PackLot {
	public PackLotOut pack(PackLotIn input) throws Exception {
		SvcUtils.checkNotEmpty("locCode", input.getLocCode());
		SvcUtils.checkNotEmpty("operCode", input.getOperCode());
		SvcUtils.checkNotEmpty("lotIdList", input.getLotIdList());

		EntityManager em = SvcUtils.getEm();

		if (SvcUtils.isEmpty(input.getLotId())) {
			input.setLotId(UUID.randomUUID().toString());
		}

		MesLot firstLot = null;

		int totalQty = 0;
		List<MesLot> lotList = new ArrayList<MesLot>(input.getLotIdList().size());
		for (String lotId : input.getLotIdList()) {
			// TODO
			MesLot lot = new MesLot();
			lot.setLotId(lotId);
			lot = em.find(MesLot.class, lot);
			totalQty += lot.getLotQty();
			lotList.add(lot);
			lot.setpLotId(input.getLotId());
			lot.setLotStatus("PACK");
			if (firstLot == null)
				firstLot = lot;
		}

		// TODO
		MesLot packLot = null;
		// if (packLot != null)
		// throw new Exception("This Pack Already Exist: " + input.getLotId());

		packLot = new MesLot();
		packLot.setLotId(input.getLotId());
		packLot.setLotStatus("OPERIN");
		packLot.setMatCode(firstLot.getMatCode());
		packLot.setLotQty(totalQty);
		packLot.setLocCode(input.getLocCode());
		packLot.setFlowCode(firstLot.getFlowCode());
		packLot.setOperCode(firstLot.getOperCode());
		packLot.setOperInTime(new Date());
		packLot.setpLotId(" ");
		packLot.setOrderId(firstLot.getOrderId());
		em.persist(packLot);

		{
			EndLotIn reqIn = new EndLotIn();
			reqIn.setLotId(packLot.getLotId());
			reqIn.setLocCode(packLot.getLocCode());
			reqIn.setOperCode(packLot.getOperCode());
			SvcUtils.getBean(LotServices.class).end(reqIn);
		}

		PackLotOut output = new PackLotOut();
		output.setOrderId(firstLot.getOrderId());
		return output;
	}
}
