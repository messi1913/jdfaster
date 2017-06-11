package com.jdfaster.jdfsample.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;

public class TestAspect extends com.jdfaster.test.TestAspect {

	@Around("execution(* com.jdfaster.jdfsample.services.*.*Services.*(..))")
	@Override
	public Object invoke(ProceedingJoinPoint point) throws Throwable {
		return super.invoke(point);
	}

}
