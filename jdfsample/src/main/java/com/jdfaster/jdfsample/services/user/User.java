package com.jdfaster.jdfsample.services.user;

import java.util.Date;

public class User {
	private String userId;
	private String userName;
	private String password;
	private String createUsername;
	private Date createTime;
	private String updateUsername;
	private Date updateTime;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String username) {
		this.userId = username;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userDesc) {
		this.userName = userDesc;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCreateUsername() {
		return createUsername;
	}

	public void setCreateUsername(String createUsername) {
		this.createUsername = createUsername;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUpdateUsername() {
		return updateUsername;
	}

	public void setUpdateUsername(String updateUsername) {
		this.updateUsername = updateUsername;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
