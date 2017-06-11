package com.jdfaster.jdfsample.services.order.pull_test;

public class PullOrderTestIn {
	private String testName;
	private String locCode;
	private String matCode;
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public String getLocCode() {
		return locCode;
	}
	public void setLocCode(String lineCode) {
		this.locCode = lineCode;
	}
	public String getMatCode() {
		return matCode;
	}
	public void setMatCode(String matCode) {
		this.matCode = matCode;
	}
}
