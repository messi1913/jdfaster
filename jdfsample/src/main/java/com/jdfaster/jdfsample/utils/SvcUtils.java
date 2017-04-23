package com.jdfaster.jdfsample.utils;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.ObjectMapper;

import net.sf.common.util.BeanUtils;

public class SvcUtils {

	public static <T> T getBean(Class<T> type) throws Exception {
		return BeanUtils.getInstance("jdfsample").get(type);
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
