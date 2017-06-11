package com.jdfaster.jdfsample.utils;

import org.aspectj.lang.Signature;

import com.jdfaster.service.DefaultServiceClassifier;
import com.jdfaster.service.HttpServiceAdapter;
import com.jdfaster.service.ServiceAdapter;
import com.jdfaster.service.ServiceClassifier;

public class SvcClassifier extends DefaultServiceClassifier implements ServiceClassifier {

	private ServiceAdapter serviceAdapter = new HttpServiceAdapter();

	public SvcClassifier() {
		super.setServiceAdapter(serviceAdapter);
	}

	@Override
	public boolean isCall(Signature signature, Object... args) {
		try {
			return SvcUtils.getSvc(signature, args) != null;
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			return false;
		}
	}

}
