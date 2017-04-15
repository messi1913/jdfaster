package com.jdfaster.jdfsample.services.mat;

import java.util.Date;

public class MesMatComp {
	private String matCode;
	private String compMatCode;
	private Integer compMatQty;
	private String operCode;
	private String createUserId;
	private Date createTime;
	private String updateUserId;
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

	public String getOperCode() {
		return operCode;
	}

	public void setOperCode(String operCode) {
		this.operCode = operCode;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
