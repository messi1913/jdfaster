
package com.jdfaster.jdfsample.utils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.persistence.EntityManager;

import org.aspectj.lang.Signature;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jdfaster.jdfsample.controller.CommonController;
import com.jdfaster.service.LoadController;

public class SvcUtils {
	@Transactional
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
			return num <= 0;
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

	@SafeVarargs
	public static <T> ArrayList<T> newArrayList(T... elements) {
		if (elements == null) {
			return null;
		}
		ArrayList<T> list = new ArrayList<T>();
		java.util.Collections.addAll(list, elements);
		return list;
	}

	private static Map<String, Svc> svcMap = new ConcurrentHashMap<String, Svc>();

	public static Svc getSvc(Signature signature, Object... args) throws Exception {
		if (args == null || args.length != 1)
			return null;

		Class<?> clazz = signature.getDeclaringType();
		String methodName = signature.getName();
		String key = clazz.getName() + "." + methodName;
		if (svcMap.containsKey(key)) {
			Svc svc = svcMap.get(key);
			if (svc.getUrl() == null)
				return null;
		}

		Svc svc = new Svc();

		try {
			StringBuffer buf = new StringBuffer();
			{
				RequestMapping mapping = clazz.getAnnotation(RequestMapping.class);
				if (mapping == null)
					return null;
				String[] strs = mapping.path();
				if (strs == null || strs.length == 0)
					strs = mapping.value();
				if (strs == null || strs.length == 0)
					return null;
				String str = strs[0];
				if (!str.startsWith("/"))
					buf.append("/");
				buf.append(str);
				if (!str.endsWith("/"))
					buf.append("/");
			}

			Method method = clazz.getMethod(methodName, args[0].getClass());

			{
				RequestMapping mapping = method.getAnnotation(RequestMapping.class);
				if (mapping == null)
					return null;
				String[] strs = mapping.path();
				if (strs == null || strs.length == 0)
					strs = mapping.value();
				if (strs == null || strs.length == 0)
					return null;
				String str = strs[0];
				if (str.startsWith("/"))
					str = str.substring(1);
				buf.append(str);
				if (!str.endsWith("/"))
					buf.append("/");

				RequestMethod[] methods = mapping.method();
				if (methods == null || methods.length == 0)
					return null;

				svc.setMethod(methods[0]);
			}

			svc.setUrl(buf.toString());
			svc.setInputType(args[0].getClass());
			svc.setOutputType(method.getReturnType());

			return svc;
		} finally {
			svcMap.put(key, svc);
		}
	}

	public static class Svc {
		private String url;
		private RequestMethod method;
		private Class<?> inputType;
		private Class<?> outputType;

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public RequestMethod getMethod() {
			return method;
		}

		public void setMethod(RequestMethod method) {
			this.method = method;
		}

		public Class<?> getInputType() {
			return inputType;
		}

		public void setInputType(Class<?> inputType) {
			this.inputType = inputType;
		}

		public Class<?> getOutputType() {
			return outputType;
		}

		public void setOutputType(Class<?> outputType) {
			this.outputType = outputType;
		}
	}
}
