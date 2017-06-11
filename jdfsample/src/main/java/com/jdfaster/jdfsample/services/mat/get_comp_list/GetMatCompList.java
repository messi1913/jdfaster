package com.jdfaster.jdfsample.services.mat.get_comp_list;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.transaction.annotation.Transactional;

import com.jdfaster.jdfsample.services.mat.MesMatComp;
import com.jdfaster.jdfsample.utils.SvcUtils;

public class GetMatCompList {
	public GetMatCompListOut getCompList(GetMatCompListIn input) throws Exception {
		EntityManager em = SvcUtils.getEm();
		CriteriaBuilder cb = em.getCriteriaBuilder();

		List<MesMatComp> list;
		{
			CriteriaQuery<MesMatComp> cq = cb.createQuery(MesMatComp.class);
			Root<MesMatComp> root = cq.from(MesMatComp.class);
			List<Predicate> where = new ArrayList<Predicate>();
			if (input.getMatCode() != null && !input.getMatCode().trim().isEmpty())
				where.add(cb.equal(root.get("matCode"), input.getMatCode()));
			if (input.getOperCode() != null && !input.getOperCode().trim().isEmpty())
				where.add(cb.equal(root.get("operCode"), input.getOperCode()));
			cq.where(where.toArray(new Predicate[where.size()]));
			cq.orderBy(cb.asc(root.get("matCode")));
			TypedQuery<MesMatComp> query = em.createQuery(cq);
			query.setFirstResult(0);
			query.setMaxResults(1);
			list = query.getResultList();
		}

		GetMatCompListOut output = new GetMatCompListOut();
		output.setList(list);
		return output;
	}
}
