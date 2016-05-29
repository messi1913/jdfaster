package com.jdfaster.service;

import org.aspectj.lang.Signature;

public interface ServiceClassifier {

	boolean isCall(Signature signature, Object... args);

	String getName(Signature signature, Object... args);

	ServiceAdapter getAdapter(Signature signature, Object... args);

}
