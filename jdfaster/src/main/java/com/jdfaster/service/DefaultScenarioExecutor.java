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

// ApplicationContextAware :Application Context 를 객체로 받아서 사용가능한 인터페이스 
public class DefaultScenarioExecutor implements ApplicationContextAware, ScenarioExecutor {

	private static Map<String, BeanInfo> beanInfos = new ConcurrentHashMap<String, BeanInfo>();

	// ScenarioExecutor 의 오버라이드.
	@Override
	public void execute(final String name) throws Exception {
		// BeanInfo 객체를 SyncCtrlUtils 통해서 갖고옴.(싱글톤 & 동기방식) 
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
				// 클래스 유틸을 통한 클래스 로딩
				clazz = ClassUtils.resolveClassName(className, ClassUtils.getDefaultClassLoader());
				// 메서드명 확인.
				for (Method m : clazz.getMethods()) {
					if (!m.getName().equals(methodName))
						continue;
					method = m;
				}

				if (method == null)
					throw new NoSuchMethodError(name);
				// 메서드의 파라미터 유형 클래스를 담음.
				List<Object> argList = new ArrayList<Object>();
				for (Class<?> item : method.getParameterTypes()) {
					argList.add(item.newInstance());
				}
				args = argList.toArray(new Object[argList.size()]);
				// 클래스명 을 통해서 bean 을 갖고옴.
				String beanName = StringUtils.uncapitalize(clazz.getSimpleName());
				try {
					bean = applicationContext.getBean(beanName);
				} catch (NoSuchBeanDefinitionException e) {
					beanName = null;
					bean = applicationContext.getBean(clazz);
				}
				// Class Bean, method, parameter 를 BeanInfo 클래스에 저장.
				BeanInfo beanInfo = new BeanInfo();
				beanInfo.setBean(bean);
				beanInfo.setMethod(method);
				beanInfo.setArgs(args);
				return beanInfo;
			}
		});
		// 호출 고
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

	@Override // Application 을 객체로 갖고 옴.
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

}
