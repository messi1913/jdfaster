package com.jdfaster.jdfsample.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DaoAspect {
	// private static final Logger LOGGER =
	// LoggerFactory.getLogger(DaoAspect.class);

	@Around("execution(* javax.persistence.EntityManager.*(..))")
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
