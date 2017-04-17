package com.jdfaster.jdfsample.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceAspect {
	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceAspect.class);
	
	@Before("execution(* com.jdfaster.jdfsample.services.*.*Services.*(..))")
	public void before(JoinPoint joinPoint){
		LOGGER.debug("invoke before method by AOP : ");
	}
	
	@After("execution(* com.jdfaster.jdfsample.services.*.*Services.*(..))")
	public void after(JoinPoint joinPoint){
		LOGGER.debug("invoke after method by AOP : ");
	}
}
  