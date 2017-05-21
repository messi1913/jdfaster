package com.jdfaster.jdfsample.services.lot.get_size;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.jdfaster.jdfsample.services.mat.MesMatComp;
import com.jdfaster.jdfsample.utils.SvcUtils;

public class GetLotSize {
	public GetLotSizeOut getSize(GetLotSizeIn input) throws Exception {
		EntityManager em = SvcUtils.getEm();
		CriteriaBuilder cb = em.getCriteriaBuilder();

		long size;
		{
			CriteriaQuery<Long> cq = cb.createQuery(Long.class);
			Root<MesMatComp> root = cq.from(MesMatComp.class);
			cq.select(cb.count(root));
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
			cq.orderBy(cb.asc(root.get("matCode")));
			TypedQuery<Long> query = em.createQuery(cq);
			size = query.getSingleResult();
		}

		GetLotSizeOut output = new GetLotSizeOut();
		output.setSize(size);
		return output;
	}
}
