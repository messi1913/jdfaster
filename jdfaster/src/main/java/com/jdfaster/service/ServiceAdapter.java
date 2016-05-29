package com.jdfaster.service;

import org.aspectj.lang.ProceedingJoinPoint;

public interface ServiceAdapter {
	Object invoke(ProceedingJoinPoint point) throws Throwable;
}
