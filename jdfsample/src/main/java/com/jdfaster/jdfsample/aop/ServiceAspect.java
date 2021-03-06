package com.jdfaster.jdfsample.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceAspect {
	// private static final Logger LOGGER =
	// LoggerFactory.getLogger(ServiceAspect.class);

	@Around("execution(* com.jdfaster.jdfsample.services.*.*Services.*(..))")
	public Object invoke(ProceedingJoinPoint jointPoint) throws Throwable {
		Object result;
		try {
			result = jointPoint.proceed(jointPoint.getArgs());
		} catch (Throwable t) {
			throw t;
		}
		return result;
	}

}
