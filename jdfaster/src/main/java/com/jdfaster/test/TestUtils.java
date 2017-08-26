package com.jdfaster.test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.sf.common.util.Closure;

public class TestUtils {
	private static Map<Long, Test> props = new ConcurrentHashMap<Long, Test>();

	public static boolean isOngoing() {
		Long tid = Thread.currentThread().getId();
		return props.containsKey(tid);
	}

	public static <T extends Throwable> Object run(Test test, Closure<Object, T> closure) throws T {
		Long tid = Thread.currentThread().getId();
		props.put(tid, test);
		try {
			return closure.execute();
		} finally {
			props.remove(tid);
		}
	}

	public static Test getCurrentTest() {
		Long tid = Thread.currentThread().getId();
		return props.get(tid);
	}

	private static TestResult result = new TestResult();

	public static TestResult getResult() {
		return result;
	}

	public static TestResult getClonedResult() {
		synchronized (result) {
			TestResult data = new TestResult();

			data.setName(result.getName());
			data.setMinRunTime(result.getMinRunTime());
			data.setMaxRunTime(result.getMaxRunTime());
			data.setAvgRunTime(result.getAvgRunTime());
			data.setTotalRunSize(result.getTotalRunSize());
			data.setTotalRunTime(result.getTotalRunTime());

			for (TestResult item : result.getList()) {
				TestResult svcResult = new TestResult();
				svcResult.setName(item.getName());
				svcResult.setMinRunTime(item.getMinRunTime());
				svcResult.setMaxRunTime(item.getMaxRunTime());
				svcResult.setAvgRunTime(item.getAvgRunTime());
				svcResult.setTotalRunSize(item.getTotalRunSize());
				svcResult.setTotalRunTime(item.getTotalRunTime());
				data.results.put(svcResult.getName(), svcResult);
			}
			return result;
		}
	}

	public static Map<Long, Test> getAllTest() {
		return props;
	}

	public static void removeAllTest() {
		props.clear();
	}
}
