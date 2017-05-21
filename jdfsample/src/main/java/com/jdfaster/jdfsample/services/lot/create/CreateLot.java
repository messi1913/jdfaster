package com.jdfaster.jdfsample.services.lot.create;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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

		EntityManager em = SvcUtils.getEm();
		CriteriaBuilder cb = em.getCriteriaBuilder();

		MesOrder order = new MesOrder();
		order.setOrderId(input.getOrderId());
		order = em.find(MesOrder.class, order);

		if ("END".equals(order.getOrderStatus()))
			throw new Exception("This Order Already Ended: " + input.getOrderId());

		SvcUtils.checkNotEmpty("matCode", order.getMatCode());
		MesMat prod = new MesMat();
		prod.setMatCode(order.getMatCode());
		prod = em.find(MesMat.class, prod);

		SvcUtils.checkNotEmpty("flowCode", prod.getFlowCode());
		{
			MesFlowOper firstOper;
			{
				CriteriaQuery<MesFlowOper> cq = cb.createQuery(MesFlowOper.class);
				Root<MesFlowOper> root = cq.from(MesFlowOper.class);
				List<Predicate> where = new ArrayList<Predicate>();
				where.add(cb.equal(root.get("flowCode"), prod.getFlowCode()));
				where.add(cb.equal(root.get("seqNo"), 1));
				cq.where(where.toArray(new Predicate[where.size()]));
				TypedQuery<MesFlowOper> query = em.createQuery(cq);
				firstOper = query.getSingleResult();
			}
			if (!input.getOperCode().equals(firstOper.getOperCode()))
				throw new Exception("This Oper is Not the Starting Oper: " + input.getOperCode());
		}

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

		em.persist(lot);
		LotUtils.updateLot(lot, 1, "OPERIN");

		if (!"START".equals(order.getOrderStatus())) {
			order.setOrderStatus("START");
			if (order.getStartTime() == null)
				order.setStartTime(new Date());
		}
		order.setInputQty(order.getInputQty() + 1);

		em.persist(order);

		CreateLotOut output = new CreateLotOut();
		return output;
	}
}
