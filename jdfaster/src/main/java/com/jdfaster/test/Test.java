package com.jdfaster.test;

import java.lang.reflect.Method;

public class Test {
	private Class<?> clazz;
	private Method method;
	private Object[] args = new Object[0];
	private TestResult result;

	public Class<?> getClazz() {
		return clazz;
	}

	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}

	public Method getMethod() {
		return method;
	}

	public void setMethod(Method method) {
		this.method = method;
	}

	public Object[] getArgs() {
		return args;
	}

	public void setArgs(Object[] args) {
		this.args = args;
	}

	public TestResult getResult() {
		if (result == null) {
			synchronized (this) {
				if (result == null) {
					result = new TestResult();
					result.setName(clazz.getSimpleName() + "." + method.getName());
				}
			}
		}
		return result;
	}

	public void setResult(TestResult result) {
		this.result = result;
	}
}
