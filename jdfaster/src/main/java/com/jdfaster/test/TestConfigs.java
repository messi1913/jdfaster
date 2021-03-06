package com.jdfaster.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TestConfigs {
	private static Map<String, TestConfigs> configsMap = new ConcurrentHashMap<String, TestConfigs>();

	public static TestConfigs getInstance(String contextName) throws Exception {
		if (configsMap.containsKey(contextName))
			return configsMap.get(contextName);
		TestConfigs configs = new TestConfigs();
		configsMap.put(contextName, configs);
		return configs;
	}

	private List<String> basePackages = new ArrayList<>();

	public synchronized List<String> getBasePackages() {
		return new ArrayList<>(basePackages);
	}

	public synchronized void addBasePackage(String... basePackage) {
		for (String bp : basePackage) {
			if (basePackages.contains(bp))
				continue;
			basePackages.add(bp);
		}
	}

	private String targetUrl;
	private long connectionTimeout;
	private long responseTimeout;
	private int numberOfThreads;

	public String getTargetUrl() {
		return targetUrl;
	}

	public void setTargetUrl(String targetUrl) {
		this.targetUrl = targetUrl;
	}

	public long getConnectionTimeout() {
		return connectionTimeout;
	}

	public void setConnectionTimeout(long connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}

	public long getResponseTimeout() {
		return responseTimeout;
	}

	public void setResponseTimeout(long responseTimeout) {
		this.responseTimeout = responseTimeout;
	}

	public int getNumberOfThreads() {
		return numberOfThreads;
	}

	public void setNumberOfThreads(int numberOfThreads) {
		this.numberOfThreads = numberOfThreads;
	}

}
