package com.jdfaster.test.services.run;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TestResultDVO {
	private StringProperty methodName;
	private StringProperty threadName;
	private StringProperty startTime;
	private StringProperty runTime;
	private StringProperty status;
	private StringProperty connectionTime;

	public TestResultDVO() {
		this.methodName = new SimpleStringProperty();
		this.threadName = new SimpleStringProperty();
		this.startTime = new SimpleStringProperty();
		this.runTime = new SimpleStringProperty();
		this.status = new SimpleStringProperty();
		this.connectionTime = new SimpleStringProperty();
	}

	public final StringProperty methodNameProperty() {
		return this.methodName;
	}

	public final String getMethodName() {
		return this.methodNameProperty().get();
	}

	public final void setMethodName(final String methodName) {
		this.methodNameProperty().set(methodName);
	}

	public final StringProperty threadNameProperty() {
		return this.threadName;
	}

	public final String getThreadName() {
		return this.threadNameProperty().get();
	}

	public final void setThreadName(final String threadName) {
		this.threadNameProperty().set(threadName);
	}

	public final StringProperty startTimeProperty() {
		return this.startTime;
	}

	public final String getStartTime() {
		return this.startTimeProperty().get();
	}

	public final void setStartTime(final String StartTime) {
		this.startTimeProperty().set(StartTime);
	}

	public final StringProperty runTimeProperty() {
		return this.runTime;
	}

	public final String getRunTime() {
		return this.runTimeProperty().get();
	}

	public final void setRunTime(final String runTime) {
		this.runTimeProperty().set(runTime);
	}

	public final StringProperty statusProperty() {
		return this.status;
	}

	public final String getStatus() {
		return this.statusProperty().get();
	}

	public final void setStatus(final String status) {
		this.statusProperty().set(status);
	}

	public final StringProperty connectionTimeProperty() {
		return this.connectionTime;
	}

	public final String getConnectionTime() {
		return this.connectionTimeProperty().get();
	}

	public final void setConnectionTime(final String connectionTime) {
		this.connectionTimeProperty().set(connectionTime);
	}
}
