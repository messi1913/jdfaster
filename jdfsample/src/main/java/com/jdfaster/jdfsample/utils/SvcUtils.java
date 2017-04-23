package com.jdfaster.jdfsample.utils;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.ObjectMapper;

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
		}

		String str = value.toString();
		return str.trim().isEmpty();
	}

	public static void checkNotEmpty(String paramName, Object value) throws Exception {
		if (SvcUtils.isEmpty(value))
			throw new Exception("Required param: " + paramName);
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
