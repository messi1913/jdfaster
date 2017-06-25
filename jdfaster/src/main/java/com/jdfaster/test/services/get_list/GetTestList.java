package com.jdfaster.test.services.get_list;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdfaster.test.TestConfigs;
import com.jdfaster.test.TestScenario;
import com.jdfaster.test.services.get_list.GetTestListOut.TestInfo;

import net.sf.common.util.ReflectionUtils;

public class GetTestList {
	public GetTestListOut getList(ServletContext context, GetTestListIn input) throws Exception {
		String contextName = context.getContextPath();

		TestConfigs configs = TestConfigs.getInstance(contextName);
		if (configs.getBasePackages().isEmpty())
			return new GetTestListOut();

		List<TestInfo> list = new ArrayList<>();
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

				String prefix = getPrefix(clazz.getAnnotation(RequestMapping.class));

				for (Method method : clazz.getMethods()) {
					if (method.getAnnotation(TestScenario.class) == null)
						continue;

					RequestMapping rm = method.getAnnotation(RequestMapping.class);
					if (rm == null)
						continue;

					String suffix = getSuffix(rm);

					TestInfo test = new TestInfo();
					test.setName(clazz.getSimpleName() + "." + method.getName());
					test.setUrl(prefix + suffix);
					test.setClassName(clazz.getName());
					test.setMethodName(method.getName());
					list.add(test);
				}
			}
		}

		GetTestListOut output = new GetTestListOut();
		output.setList(list);
		return output;
	}

	private static String getPrefix(RequestMapping rm) {
		if (rm == null) {
			return "/";
		}

		String prefix;
		if (rm.path().length != 0) {
			prefix = rm.path()[0];
		} else if (rm.value().length != 0) {
			prefix = rm.value()[0];
		} else {
			return "/";
		}

		if (!prefix.endsWith("/"))
			prefix += "/";

		return prefix;
	}

	private static String getSuffix(RequestMapping rm) {
		if (rm == null) {
			return "/";
		}

		String suffix;
		if (rm.path().length != 0) {
			suffix = rm.path()[0];
		} else if (rm.value().length != 0) {
			suffix = rm.value()[0];
		} else {
			return "/";
		}

		if (suffix.startsWith("/"))
			suffix = suffix.substring(1);

		return suffix;
	}
}
