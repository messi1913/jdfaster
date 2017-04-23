package com.jdfaster.jdfsample.utils;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.ObjectMapper;

import com.jdfaster.jdfsample.services.lot.MesLot;
import com.jdfaster.jdfsample.services.lot.MesLotHist;

import net.sf.common.util.BeanUtils;

public class SvcUtils {

	public static <T> T getBean(Class<T> type) throws Exception {
		return BeanUtils.getInstance("jdfsample").get(type);
	}

	public static boolean isEmpty(Object value) throws Exception {
		if (value == null)
			return true;
		if (value instanceof String) {
			String str = (String) value;
			return str.trim().isEmpty();
		} else if (value instanceof Integer) {
			Integer num = (Integer) value;
			return num > 0;
		} else if (value instanceof Collection) {
			Collection<?> col = (Collection<?>) value;
			return col.isEmpty();
		}

		String str = value.toString();
		return str.trim().isEmpty();
	}

	public static void checkNotEmpty(String paramName, Object value) throws Exception {
		if (SvcUtils.isEmpty(value))
			throw new Exception("Required param: " + paramName);
	}

	public static void endLot(MesLot lot, int histSeqNo, String tranCode) throws Exception {
		if (!"OPERIN".equals(lot.getLotStatus()))
			throw new Exception("This Lot Status is Not OPERIN: " + lot.getLotStatus());
		if (lot.getOperStartTime() == null || lot.getOperStartTime().getTime() < lot.getOperInTime().getTime())
			lot.setOperStartTime(lot.getOperInTime());
		lot.setLotStatus("OPEREND");
		lot.setOperEndTime(new Date());
		SvcUtils.updateLot(lot, histSeqNo, tranCode);
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

	public static <T> T toInVo(HttpServletRequest request) throws Exception {
		// TODO SvcUtils.toInvo
		return null;
	}

	private static final ObjectMapper om;
	static {
		om = new ObjectMapper();
	}

	public static String toOutStr(Object output) throws Exception {
		return om.writeValueAsString(output);
	}

}
