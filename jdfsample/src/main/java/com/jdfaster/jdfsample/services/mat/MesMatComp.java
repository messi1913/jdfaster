package com.jdfaster.jdfsample.services.mat;

import java.util.Date;

public class MesMatComp {
	private String matCode;
	private String compMatCode;
	private Integer compMatQty;
	private String createUsername;
	private Date createTime;
	private String updateUsername;
	private Date updateTime;
	public String getMatCode() {
		return matCode;
	}
	public void setMatCode(String matCode) {
		this.matCode = matCode;
	}
	public String getCompMatCode() {
		return compMatCode;
	}
	public void setCompMatCode(String compMatCode) {
		this.compMatCode = compMatCode;
	}
	public Integer getCompMatQty() {
		return compMatQty;
	}
	public void setCompMatQty(Integer compMatQty) {
		this.compMatQty = compMatQty;
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
