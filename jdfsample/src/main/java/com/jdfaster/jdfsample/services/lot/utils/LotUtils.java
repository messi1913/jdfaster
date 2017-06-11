package com.jdfaster.jdfsample.services.lot.utils;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.transaction.annotation.Transactional;

import com.jdfaster.jdfsample.services.flow.MesFlowOper;
import com.jdfaster.jdfsample.services.lot.MesLot;
import com.jdfaster.jdfsample.services.lot.MesLotHist;
import com.jdfaster.jdfsample.utils.SvcUtils;

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
		EntityManager em = SvcUtils.getEm();
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
		em.merge(lot);
		em.persist(hist);
	}

	public static int getCurrentSeqNo(String lotId) throws Exception {
		EntityManager em = SvcUtils.getEm();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		List<MesLotHist> list;
		{
			CriteriaQuery<MesLotHist> cq = cb.createQuery(MesLotHist.class);
			Root<MesLotHist> root = cq.from(MesLotHist.class);
			Predicate predicate = cb.equal(root.get("lotId"), lotId);
			cq.where(predicate);
			cq.orderBy(cb.desc(root.get("seqNo")));
			TypedQuery<MesLotHist> query = em.createQuery(cq);
			query.setFirstResult(0);
			query.setMaxResults(1);
			list = query.getResultList();
		}

		return SvcUtils.isEmpty(list) ? 0 : list.get(0).getSeqNo();

	}

	public static MesFlowOper getByOperCode(String operCode) throws Exception {
		EntityManager em = SvcUtils.getEm();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		List<MesFlowOper> list;
		{
			CriteriaQuery<MesFlowOper> cq = cb.createQuery(MesFlowOper.class);
			Root<MesFlowOper> root = cq.from(MesFlowOper.class);
			Predicate predicate = cb.equal(root.get("operCode"), operCode);
			cq.where(predicate);
			TypedQuery<MesFlowOper> query = em.createQuery(cq);
			query.setFirstResult(0);
			query.setMaxResults(1);
			list = query.getResultList();
		}
		return list.get(0);
	}

	public static MesFlowOper getNextOper(String flowCode, int seqNo) throws Exception {
		EntityManager em = SvcUtils.getEm();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		List<MesFlowOper> list;
		{
			CriteriaQuery<MesFlowOper> cq = cb.createQuery(MesFlowOper.class);
			Root<MesFlowOper> root = cq.from(MesFlowOper.class);
			Predicate preFlowCode = cb.equal(root.get("flowCode"), flowCode);
			Predicate preSeqNo = cb.equal(root.get("seqNo"), ++seqNo);
			cq.where(preFlowCode, preSeqNo);
			TypedQuery<MesFlowOper> query = em.createQuery(cq);
			query.setFirstResult(0);
			query.setMaxResults(1);
			list = query.getResultList();
		}
		return list.get(0);
	}

}
