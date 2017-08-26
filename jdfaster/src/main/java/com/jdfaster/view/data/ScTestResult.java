package com.jdfaster.view.data;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ScTestResult implements Serializable {
	private String threadNo;
	private String serviceName;
	private String avgTime;
	private String maxTime;
	private String minTime;
	private String status;
	
	public String getThreadNo() {
		return threadNo;
	}
	public void setThreadNo(String threadNo) {
		this.threadNo = threadNo;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getAvgTime() {
		return avgTime;
	}
	public void setAvgTime(String avgTime) {
		this.avgTime = avgTime;
	}
	public String getMaxTime() {
		return maxTime;
	}
	public void setMaxTime(String maxTime) {
		this.maxTime = maxTime;
	}
	public String getMinTime() {
		return minTime;
	}
	public void setMinTime(String minTime) {
		this.minTime = minTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
