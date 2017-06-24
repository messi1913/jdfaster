package com.jdfaster.util.json;

public class JsonFactory {
	
	public static AbstractJsonConverter getConverter() {
		
		return JsonConverter.getInstance();
	}
	
	public static String toJsonString(Object target) {
		return getConverter().toJsonString(target);
	}

	public static <T> T toObject(String target, Class<T> returnType) throws Exception {
		return getConverter().toObject(target, returnType);
	}

}
