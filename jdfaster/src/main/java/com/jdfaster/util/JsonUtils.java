package com.jdfaster.util;

import java.nio.charset.Charset;

import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.entity.ContentType;
import org.codehaus.jackson.map.ObjectMapper;

public class JsonUtils {
	public enum MethodType {
		POST("POST"), GET("GET"), DELETE("DELETE"), PUT("PUT");
		private String span;

		MethodType(String type) {
			span = type;
		}

		public String getTYpe() {
			return span;
		}
	}

	/**
	 *
	 * @param uri
	 * @param type
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	
	public static <T> T request(String uri, MethodType type, Class<T> requiredDVO) throws Exception {
		return request(uri, type, "", requiredDVO);
	}
	
	public static <T> T request(String uri, MethodType type, String inputString, Class<T> requiredDVO) throws Exception {
		Request req;

		switch (type) {
		case GET:
			req = Request.Get(uri);
			break;
		case POST:
			req = Request.Post(uri).bodyString(inputString,	ContentType.create("application/json"));
			break;
		default :
			req = Request.Post(uri).bodyString(inputString,	ContentType.create("application/json"));
			break;
		}
		Response resp = req.execute();
		String outStr;
		{
			Content content = resp.returnContent();
			outStr = content.asString(Charset.forName("UTF-8"));
		}
		return new ObjectMapper().readValue(outStr, requiredDVO);
	}
}
