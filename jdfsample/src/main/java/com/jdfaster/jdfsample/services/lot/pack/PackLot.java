package com.jdfaster.jdfsample.services.lot.pack;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;

import com.jdfaster.jdfsample.services.lot.MesLot;
import com.jdfaster.jdfsample.utils.SvcUtils;

public class PackLot {
	public PackLotOut pack(PackLotIn input) throws Exception {
		SvcUtils.checkNotEmpty("locCode", input.getLocCode());
		SvcUtils.checkNotEmpty("operCode", input.getOperCode());
		SvcUtils.checkNotEmpty("lotIdList", input.getLotIdList());
		
		EntityManager em = SvcUtils.getEm();

		int totalQty = 0;
		List<MesLot> lotList = new ArrayList<MesLot>(input.getLotIdList().size());
		for (String lotId : input.getLotIdList()) {
			// TODO
			MesLot lot = new MesLot();
			lot.setLotId(lotId);
			lot = em.find(MesLot.class, lot);
			totalQty += lot.getLotQty();
			lotList.add(lot);
		}

		if (SvcUtils.isEmpty(input.getLotId())) {
			input.setLotId(UUID.randomUUID().toString());
		}

		// TODO
		MesLot packLot = null;
		if (packLot != null)
			throw new Exception("This Pack Already Exist: " + input.getLotId());

		packLot = new MesLot();
		// @Column(name = "lotStatus", length = 50)
		// private String lotStatus;
		// @Column(name = "matCode", length = 50)
		// private String matCode;
		// @Column(name = "lotQty", length = 10)
		// private Integer lotQty;
		// @Column(name = "locCode", length = 50)
		// private String locCode;
		// @Column(name = "flowCode", length = 50)
		// private String flowCode;
		// @Column(name = "operCode", length = 50)
		// private String operCode;
		// @Column(name = "operInTime")
		// private Date operInTime;
		// @Column(name = "operStartTime")
		// private Date operStartTime;
		// @Column(name = "operEndTime")
		// private Date operEndTime;
		// @Column(name = "orderId", length = 100)
		// private String orderId;
		// @Column(name = "compOrderId", length = 100)
		// private String compOrderId;
		// @Column(name = "packedFlag", length = 1)
		// private String packedFlag;

		PackLotOut output = new PackLotOut();
		return output;
	}
}
