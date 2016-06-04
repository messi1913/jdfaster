package com.jdfaster.jdfsample.utils;

import javax.servlet.http.HttpServletRequest;

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
		if (args == null || args.length > 1 || args[0] instanceof HttpServletRequest)
			return false;
		return true;
	}

}
