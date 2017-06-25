package com.jdfaster.test.services.get_list;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;

import com.jdfaster.test.Test;
import com.jdfaster.test.TestConfigs;
import com.jdfaster.test.TestScenario;

import net.sf.common.util.ReflectionUtils;

public class GetTestList {
	public GetTestListOut getList(ServletContext context, GetTestListIn input) throws Exception {
		String contextName = context.getContextPath();

		TestConfigs configs = TestConfigs.getInstance(contextName);
		if (configs.getBasePackages().isEmpty())
			return new GetTestListOut();

		List<Test> list = new ArrayList<>();
		for (String bp : configs.getBasePackages()) {
			StringBuffer buf = new StringBuffer("classpath*:").append(StringUtils.replace(bp, ".", "/"));
			if (!buf.toString().endsWith("/"))
				buf.append("/");
			buf.append("*.class");
			String path = buf.toString();
			List<Class<?>> classList = ReflectionUtils.getClassList(path, null);
			for (Class<?> clazz : classList) {
				{
					RestController ctrl = clazz.getAnnotation(RestController.class);
					if (ctrl == null)
						continue;
				}

				// String prefix;
				// {
				// RequestMapping rm =
				// clazz.getAnnotation(RequestMapping.class);
				// prefix = rm == null || rm.value().length == 0 ? "/" :
				// rm.value()[0];
				// if (!prefix.endsWith("/"))
				// prefix += "/";
				// }

				for (Method method : clazz.getMethods()) {
					if (method.getAnnotation(TestScenario.class) == null)
						continue;
					Test test = new Test();
					test.setClazz(clazz);
					test.setMethod(method);
					if (method.getParameterTypes() != null)
						test.setParameterTypes(method.getParameterTypes());
					list.add(test);
				}
			}
		}

		GetTestListOut output = new GetTestListOut();
		output.setList(list);
		return output;
	}
}
