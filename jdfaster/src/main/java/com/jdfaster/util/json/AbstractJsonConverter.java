package com.jdfaster.util.json;

public abstract class AbstractJsonConverter {
	public abstract String toJsonString(Object target);

	public abstract <T> T toObject(String target, Class<T> returnType) throws Exception;
	
}
