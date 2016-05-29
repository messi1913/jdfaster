package com.jdfaster.service;

import org.aspectj.lang.Signature;

public class DefaultServiceClassifier implements ServiceClassifier {

	@Override
	public boolean isCall(Signature signature, Object... args) {
		return true;
	}

	@Override
	public String getName(Signature signature, Object... args) {
		return signature.getDeclaringTypeName() + "." + signature.getName();
	}

	private ServiceAdapter serviceAdapter = new DefaultServiceAdapter();

	public void setServiceAdapter(ServiceAdapter serviceAdapter) {
		this.serviceAdapter = serviceAdapter;
	}

	@Override
	public ServiceAdapter getAdapter(Signature signature, Object... args) {
		return serviceAdapter;
	}

}
