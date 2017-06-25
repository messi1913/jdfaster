package com.jdfaster.test;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.springframework.beans.factory.InitializingBean;

import com.jdfaster.service.ServiceAdapter;
import com.jdfaster.service.ServiceClassifier;

import net.sf.common.util.Closure;

public class TestAspect implements InitializingBean {
	private ServiceClassifier serviceClassifier;

	public void setServiceClassifier(ServiceClassifier serviceClassifier) {
		this.serviceClassifier = serviceClassifier;
	}

	public void afterPropertiesSet() throws Exception {
		if (serviceClassifier == null)
			throw new IllegalArgumentException("serviceClassifier is required!");
	}

	public Object invoke(final ProceedingJoinPoint point) throws Throwable {
		Signature signature = point.getSignature();
		Object[] args = point.getArgs();

		// 서비스가 아닌 경우, 원래 메써드 실행
		if (!Modifier.isPublic(signature.getModifiers())
				|| !ProceedingJoinPoint.METHOD_EXECUTION.equals(point.getKind())
				|| !serviceClassifier.isCall(signature, args)) {
			return point.proceed();
		}

		// 실행하려는 클래스/메써드가 테스트 인지 확인
		Object target = point.getTarget();
		Class<?> clazz = target.getClass();
		Method method;
		{
			Class<?>[] parameterTypes;
			if (args == null || args.length == 0) {
				parameterTypes = new Class<?>[0];
			} else {
				parameterTypes = new Class<?>[args.length];
				int i = 0;
				for (Object arg : args) {
					parameterTypes[i++] = arg.getClass();
				}
			}
			method = clazz.getMethod(signature.getName(), parameterTypes);
		}
		boolean testClass = method.getAnnotation(TestScenario.class) != null;

		// 이미 테스트 실행 중인지 여부 확인
		boolean ongoing = TestUtils.isOngoing();

		// 일반(테스트 중이 아닌) 서비스 실행인 경우
		if (!testClass && !ongoing) {
			return point.proceed();
		}

		// 테스트 클래스인 경우
		if (testClass) {
			if (ongoing)
				return point.proceed();

			// 테스트
			{
				Test test = new Test();
				test.setClazz(clazz);
				test.setMethod(method);
				if (method.getParameterTypes() != null)
					test.setParameterTypes(method.getParameterTypes());
				test.setArgs(args);

				Object result = TestUtils.run(test, new Closure<Object, Throwable>() {
					@Override
					public Object execute() throws Throwable {
						return point.proceed();
					}
				});
				return result;
			}
		}

		// 테스트 중에 서비스 호출인 경우, 원격지로 호출
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
}
