package com.jdfaster.util.json;

import com.google.gson.GsonBuilder;

public class JsonConverter extends AbstractJsonConverter{
	
	private static GsonBuilder gsonBuilder;
	
	private static GsonBuilder getBuilder(){
		if(gsonBuilder == null) gsonBuilder = new GsonBuilder();
		return gsonBuilder;
	}

	@Override
	public String toJsonString(Object target) {
		return getBuilder().create().toJson(target);
	}

	@Override
	public <T> T toObject(String target, Class<T> returnType) throws Exception {
		return getBuilder().create().fromJson(target, returnType);
	}

	static JsonConverter instance; 
	
	public static AbstractJsonConverter getInstance() {
		if (instance == null) { 
			instance = new JsonConverter(); 
			} 
			return instance; 
	}

}
