package com.jdfaster.service;

import org.aspectj.lang.ProceedingJoinPoint;

public class DefaultServiceAdapter implements ServiceAdapter {

	@Override
	public Object invoke(ProceedingJoinPoint point) throws Throwable {
		return point.proceed();
	}

}
