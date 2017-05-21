package com.jdfaster.jdfsample.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DaoAspect {
	private static final Logger LOGGER = LoggerFactory.getLogger(DaoAspect.class);

	@Around("execution(* javax.persistence.EntityManager.*(..)) && !execution(* javax.persistence.EntityManager.get*(..))")
	public Object execute(ProceedingJoinPoint jointPoint) throws Throwable {
		return jointPoint.proceed(jointPoint.getArgs());
	}
}
