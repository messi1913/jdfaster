package com.jdfaster.jdfsample.services.lot.get_list;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.jdfaster.jdfsample.services.lot.MesLot;
import com.jdfaster.jdfsample.utils.SvcUtils;

public class GetLotList {
	public GetLotListOut getList(GetLotListIn input) throws Exception {
		EntityManager em = SvcUtils.getEm();
		CriteriaBuilder cb = em.getCriteriaBuilder();

		List<MesLot> list;
		{
			CriteriaQuery<MesLot> cq = cb.createQuery(MesLot.class);
			Root<MesLot> root = cq.from(MesLot.class);
			List<Predicate> where = new ArrayList<Predicate>();
			if (input.getOrderId() != null && !input.getOrderId().trim().isEmpty())
				where.add(cb.equal(root.get("orderId"), input.getOrderId()));
			if (input.getOperCode() != null && !input.getOperCode().trim().isEmpty())
				where.add(cb.equal(root.get("operCode"), input.getOperCode()));
			if (input.getLotStatus() != null && !input.getLotStatus().trim().isEmpty())
				where.add(cb.equal(root.get("lotStatus"), input.getLotStatus()));
			if (input.getLotStatusIn() != null && !input.getLotStatusIn().isEmpty())
				where.add(root.get("lotStatus").in(input.getLotStatusIn()));
			cq.where(where.toArray(new Predicate[where.size()]));
			cq.orderBy(cb.asc(root.get("operEndTime")));
			TypedQuery<MesLot> query = em.createQuery(cq);
			list = query.getResultList();
		}

		GetLotListOut output = new GetLotListOut();
		output.setList(list);
		return output;
	}
}
