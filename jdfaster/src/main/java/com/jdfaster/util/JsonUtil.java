package com.jdfaster.util;

import java.nio.charset.Charset;

import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.codehaus.jackson.map.ObjectMapper;

public class JsonUtil {
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
	public static <T> T request(String uri, MethodType type, Class<T> clazz) throws Exception {
		Request req;
		if (MethodType.POST.equals(type))
			req = Request.Post(uri);
		else if (MethodType.GET.equals(type))
			req = Request.Get(uri);
		else if (MethodType.DELETE.equals(type))
			req = Request.Delete(uri);
		else
			req = Request.Put(uri);
		Response resp = req.execute();
		String outStr;
		{
			Content content = resp.returnContent();
			outStr = content.asString(Charset.forName("UTF-8"));
		}
		return new ObjectMapper().readValue(outStr, clazz);
	}
}
