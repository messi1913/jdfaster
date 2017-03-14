package com.jdfaster.service;

import org.aspectj.lang.Signature;

public class DefaultServiceClassifier implements ServiceClassifier {

	@Override
	public boolean isCall(Signature signature, Object... args) {
		return true;
	}

	// 호출 된 클래스의 Full Path 정보를 리턴 
	@Override
	public String getName(Signature signature, Object... args) {
		return signature.getDeclaringTypeName() + "." + signature.getName();
	}

	// Service Advisor 부모 클래스 형태로 DefaultServiceAdapter 생.
	private ServiceAdapter serviceAdapter = new DefaultServiceAdapter();

	public void setServiceAdapter(ServiceAdapter serviceAdapter) {
		this.serviceAdapter = serviceAdapter;
	}

	@Override
	public ServiceAdapter getAdapter(Signature signature, Object... args) {
		return serviceAdapter;
	}

}
