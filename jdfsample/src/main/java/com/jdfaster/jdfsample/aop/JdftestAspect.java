package com.jdfaster.jdfsample.aop;

import javax.servlet.ServletContext;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jdfaster.test.TestConfigs;

@Aspect
@Component
public class JdftestAspect {
	@Autowired
	private ServletContext context;

	@Around("execution(* com.jdfaster.test.services.*Services.*(..))")
	public Object invoke(ProceedingJoinPoint point) throws Throwable {
		TestConfigs configs = TestConfigs.getInstance(context.getContextPath());
		if (configs.getBasePackages().isEmpty()) {
			synchronized (configs) {
				configs.addBasePackage("com.jdfaster.jdfsample.services.test");
			}
		}

		Object result;
		try {
			result = point.proceed(point.getArgs());
		} catch (Throwable t) {
			throw t;
		}
		return result;
	}

}
