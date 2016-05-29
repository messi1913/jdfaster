package com.jdfaster.test;

import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.springframework.beans.factory.InitializingBean;

import com.jdfaster.service.ServiceAdapter;
import com.jdfaster.service.ServiceClassifier;

public class TestAspect implements InitializingBean {
	private Map<Long, Boolean> props = new ConcurrentHashMap<Long, Boolean>();

	private ServiceClassifier serviceClassifier;

	public void setServiceClassifier(ServiceClassifier serviceClassifier) {
		this.serviceClassifier = serviceClassifier;
	}

	public void afterPropertiesSet() throws Exception {
		if (serviceClassifier == null)
			throw new IllegalArgumentException("serviceClassier is required!");
	}

	public Object invoke(ProceedingJoinPoint point) throws Throwable {
		Signature signature = point.getSignature();
		Object[] args = point.getArgs();

		// public method가 아니거나, 서비스가 아닌 경우
		// 원래 메써드 실행
		if (!Modifier.isPublic(signature.getModifiers())
				|| !ProceedingJoinPoint.METHOD_EXECUTION.equals(point.getKind())
				|| !serviceClassifier.isCall(signature, args)) {
			return point.proceed();
		}

		// 이미 테스트 실행 중인지 여부 확인
		Long tid = Thread.currentThread().getId();
		boolean ongoing = props.containsKey(tid);

		// 실행하려는 클래스가 테스트 클래스인지 확인
		Object target = point.getTarget();
		Class<?> clazz = target.getClass();
		boolean testClass = clazz.getAnnotation(TestScenario.class) != null;

		// 테스트 실행 중이면서 테스트 클래스이거나, 테스트 실행 중이 아니면서 테스트 클래스가 아닌 경우
		// 원래 메써드 실행
		if ((ongoing && testClass) || (!ongoing && !testClass)) {
			return point.proceed();
		}

		// 이미 테스트 실행 중이면, 원격지로 호출
		if (ongoing) {
			ServiceAdapter adapter = serviceClassifier.getAdapter(signature, args);
			String id = serviceClassifier.getName(signature, args);
			// TODO 성능 측정
			// Total별 성능
			// Service별 성능
			try {
				return adapter.invoke(point);
			} catch (Throwable t) {
				throw t;
			}
		}

		// 테스트 시작
		props.put(tid, true);
		try {
			return point.proceed();
		} finally {
			props.remove(tid);
		}
	}
}
