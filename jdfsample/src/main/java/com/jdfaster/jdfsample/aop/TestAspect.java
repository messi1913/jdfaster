package com.jdfaster.jdfsample.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TestAspect extends com.jdfaster.test.TestAspect {

	@Around("execution(* com.jdfaster.jdfsample.services.*.*Services.*(..))")
	@Override
	public Object invoke(ProceedingJoinPoint point) throws Throwable {
		return super.invoke(point);
	}

}
