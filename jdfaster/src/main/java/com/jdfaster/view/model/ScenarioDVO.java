package com.jdfaster.view.model;

public class ScenarioDVO {
	private String serverUrl;
	private String scenario;
	private int connectTimeout;
	private int responseTimeout;
	private int sizeOfThread;
	private String methodType;
	private String contentType;
	public String getServerUrl() {
		return serverUrl;
	}
	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}
	public String getScenario() {
		return scenario;
	}
	public void setScenario(String scenario) {
		this.scenario = scenario;
	}
	public int getConnectTimeout() {
		return connectTimeout;
	}
	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}
	public int getResponseTimeout() {
		return responseTimeout;
	}
	public void setResponseTimeout(int responseTimeout) {
		this.responseTimeout = responseTimeout;
	}
	public int getSizeOfThread() {
		return sizeOfThread;
	}
	public void setSizeOfThread(int sizeOfThread) {
		this.sizeOfThread = sizeOfThread;
	}
	public String getMethodType() {
		return methodType;
	}
	public void setMethodType(String methodType) {
		this.methodType = methodType;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	
	
}
