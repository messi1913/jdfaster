package com.jdfaster.jdfsample.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceAspect {
	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceAspect.class);
	
//	@Before("execution(* com.jdfaster.jdfsample.services.*.*Services.*(..))")
//	public void before(JoinPoint joinPoint){
//		LOGGER.debug("invoke before method by AOP : ");
//	}
//	
//	@After("execution(* com.jdfaster.jdfsample.services.*.*Services.*(..))")
//	public void after(JoinPoint joinPoint){
//		LOGGER.debug("invoke after method by AOP : ");
//	}
	
	@Around("execution(* com.jdfaster.jdfsample.services.*.*Services.*(..))")
	public void execute(ProceedingJoinPoint jointPoint){
		try {
			LOGGER.debug("do sth before invoke method by AOP : ");
			Object proceed = jointPoint.proceed(jointPoint.getArgs());
			LOGGER.debug("do sth after invoke method by AOP : ");
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
  