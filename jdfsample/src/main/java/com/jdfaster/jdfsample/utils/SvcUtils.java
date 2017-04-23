package com.jdfaster.jdfsample.utils;

import java.util.Collection;

import javax.persistence.EntityManager;

import com.jdfaster.jdfsample.controller.CommonController;

public class SvcUtils {

	public static EntityManager getEm() throws Exception {
		EntityManager em = getBean(CommonController.class).getEm();
		return em;
	}

	public static <T> T getBean(Class<T> type) throws Exception {
		return CommonController.getApplicationContext().getBean(type);
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

	// public static <T> T toInVo(HttpServletRequest request) throws Exception {
	// // TODO SvcUtils.toInvo
	// return null;
	// }
	//
	// private static final ObjectMapper om;
	// static {
	// om = new ObjectMapper();
	// }
	//
	// public static String toOutStr(Object output) throws Exception {
	// return om.writeValueAsString(output);
	// }

}
