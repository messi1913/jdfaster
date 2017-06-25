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

}
