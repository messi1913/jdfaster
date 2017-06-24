package com.jdfaster.util;

import com.jdfaster.util.json.JsonFactory;

public class JsonUtil {
	public static String toJsonString(Object target) {
		return JsonFactory.toJsonString(target);
	}
	
	public static <T> T toObject(String target, Class<T> returnType) throws Exception {
		return JsonFactory.toObject(target, returnType);
	}
	
	
	
}
