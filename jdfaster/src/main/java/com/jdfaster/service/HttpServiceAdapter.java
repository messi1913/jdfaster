package com.jdfaster.service;

import org.aspectj.lang.ProceedingJoinPoint;

public class HttpServiceAdapter implements ServiceAdapter {

	@Override
	public Object invoke(ProceedingJoinPoint point) throws Throwable {
		// TODO Invoke HTTP Method
		return point.proceed();
	}

}
