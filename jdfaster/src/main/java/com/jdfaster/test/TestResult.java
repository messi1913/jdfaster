package com.jdfaster.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

public class TestResult {
	private String name;
	private long minRunTime;
	private long maxRunTime;
	private long avgRunTime;
	private long totalRunSize;
	private long totalRunTime;
	public Map<String, TestResult> results = new ConcurrentHashMap<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getMinRunTime() {
		return minRunTime;
	}

	public void setMinRunTime(long minRunTime) {
		this.minRunTime = minRunTime;
	}

	public long getMaxRunTime() {
		return maxRunTime;
	}

	public void setMaxRunTime(long maxRunTime) {
		this.maxRunTime = maxRunTime;
	}

	public long getAvgRunTime() {
		return avgRunTime;
	}

	public void setAvgRunTime(long avgRunTime) {
		this.avgRunTime = avgRunTime;
	}

	public long getTotalRunSize() {
		return totalRunSize;
	}

	public void setTotalRunSize(long totalRunSize) {
		this.totalRunSize = totalRunSize;
	}

	public long getTotalRunTime() {
		return totalRunTime;
	}

	public void setTotalRunTime(long totalRunTime) {
		this.totalRunTime = totalRunTime;
	}

	public List<TestResult> getList() {
		Map<String, TestResult> results = new TreeMap<>(this.results);
		List<TestResult> list = new ArrayList<>(results.values());
		return list;
	}
}
