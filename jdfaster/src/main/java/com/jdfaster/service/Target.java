package com.jdfaster.service;

import java.util.List;

public class Target {
	private String name;
	private List<Object> args;
	private int loadFactor = 1;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Object> getArgs() {
		return args;
	}

	public void setArgs(List<Object> args) {
		this.args = args;
	}

	public int getLoadFactor() {
		return loadFactor;
	}

	public void setLoadFactor(int loadFactor) {
		this.loadFactor = loadFactor;
	}
}