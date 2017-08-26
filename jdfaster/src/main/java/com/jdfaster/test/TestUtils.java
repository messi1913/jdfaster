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
	
	public static Map<Long, Test> getAllTest() {
		return props;
	}
	
	
	public static void removeAllTest() {
		props.clear();
	}
}
