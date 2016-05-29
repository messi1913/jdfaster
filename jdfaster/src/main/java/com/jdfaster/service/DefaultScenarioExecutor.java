package com.jdfaster.service;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.ClassUtils;

import net.sf.common.util.Closure;
import net.sf.common.util.SyncCtrlUtils;

public class DefaultScenarioExecutor implements ApplicationContextAware, ScenarioExecutor {

	private static Map<String, BeanInfo> beanInfos = new ConcurrentHashMap<String, BeanInfo>();

	@Override
	public void execute(final String name) throws Exception {
		BeanInfo beanInfo = SyncCtrlUtils.wrap(name, beanInfos, name, new Closure<BeanInfo, Exception>() {
			@Override
			public BeanInfo execute() throws Exception {
				Object bean;
				Class<?> clazz;
				Method method = null;
				Object[] args;

				int index = name.lastIndexOf('.');
				if (index < 1)
					throw new IllegalArgumentException("name pattern must be 'className.methodName'!");
				String className = name.substring(0, index);
				String methodName = name.substring(index + 1);
				clazz = ClassUtils.resolveClassName(className, ClassUtils.getDefaultClassLoader());
				for (Method m : clazz.getMethods()) {
					if (!m.getName().equals(methodName))
						continue;
					method = m;
				}

				if (method == null)
					throw new NoSuchMethodError(name);

				List<Object> argList = new ArrayList<Object>();
				for (Class<?> item : method.getParameterTypes()) {
					argList.add(item.newInstance());
				}
				args = argList.toArray(new Object[argList.size()]);

				String beanName = StringUtils.uncapitalize(clazz.getSimpleName());
				try {
					bean = applicationContext.getBean(beanName);
				} catch (NoSuchBeanDefinitionException e) {
					beanName = null;
					bean = applicationContext.getBean(clazz);
				}

				BeanInfo beanInfo = new BeanInfo();
				beanInfo.setBean(bean);
				beanInfo.setMethod(method);
				beanInfo.setArgs(args);
				return beanInfo;
			}
		});

		beanInfo.getMethod().invoke(beanInfo.getBean(), beanInfo.getArgs());
	}

	private static class BeanInfo {
		private Object bean;
		private Method method;
		private Object[] args;

		public void setBean(Object bean) {
			this.bean = bean;
		}

		public Object getBean() {
			return bean;
		}

		public Method getMethod() {
			return method;
		}

		public void setMethod(Method method) {
			this.method = method;
		}

		public Object[] getArgs() {
			return args;
		}

		public void setArgs(Object[] args) {
			this.args = args;
		}
	}

	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

}
