package com.jdfaster.jdfsample.utils;

import java.nio.charset.Charset;

import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.entity.ContentType;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.codehaus.jackson.map.ObjectMapper;

import com.jdfaster.jdfsample.utils.SvcUtils.Svc;
import com.jdfaster.service.DefaultServiceAdapter;
import com.jdfaster.service.ServiceAdapter;
import com.jdfaster.test.Test;
import com.jdfaster.test.TestUtils;

public class SvcAdapter extends DefaultServiceAdapter implements ServiceAdapter {

	private static final ObjectMapper om;
	static {
		om = new ObjectMapper();
	}

	@Override
	public Object invoke(ProceedingJoinPoint point) throws Throwable {
		Test test = TestUtils.getCurrentTest();
		if (test == null)
			throw new IllegalArgumentException("Cannot find Current Test!");

		Signature signature = point.getSignature();
		Object[] args = point.getArgs();
		Svc svc = SvcUtils.getSvc(signature, args);
		if (svc == null)
			throw new IllegalArgumentException("Cannot find Service!");

		String uri;
		{
			TestIn testIn = (TestIn) test.getArgs()[0];
			StringBuffer buf = new StringBuffer();
			{
				buf = buf.append(testIn.getTargetUrl());
				String id = svc.getId();
				if (buf.toString().endsWith("/")) {
					if (id.startsWith("/"))
						id = id.substring(1);
				} else {
					if (!id.startsWith("/"))
						buf.append("/");
				}
				buf.append(id);
			}
			uri = buf.toString();
		}

		{
			Object input = args[0];
			String inStr;
			{
				inStr = om.writeValueAsString(input);
			}

			Request req;
			{
				switch (svc.getMethod()) {
				case GET:
					req = Request.Get(uri);
					break;
				case POST:
					req = Request.Post(uri);
					break;
				case PUT:
					req = Request.Put(uri);
					break;
				case DELETE:
					req = Request.Delete(uri);
					break;
				default:
					throw new IllegalArgumentException("Unsupported HTTP Method for Test");
				}
			}

			req.bodyString(inStr, ContentType.APPLICATION_JSON);
			Response resp = req.execute();

			String outStr;
			{
				Content content = resp.returnContent();
				outStr = content.asString(Charset.forName("UTF-8"));
			}

			Object output = new ObjectMapper().readValue(outStr, svc.getOutputType());
			return output;
		}
	}

}
