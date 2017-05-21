package com.jdfaster.jdfsample.services.flow;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "mes_flow")
public class MesFlow implements Serializable {
	@Id
	@Column(name = "flowCode", nullable = false, length = 100)
	private String flowCode;
	@Column(name = "flowName", length = 500)
	private String flowName;
	@Column(name = "createUserId", length = 100)
	private String createUserId;
	@Column(name = "createTime")
	private Date createTime;
	@Column(name = "updateUserId", length = 100)
	private String updateUserId;
	@Column(name = "updateTime")
	private Date updateTime;

	public String getFlowCode() {
		return flowCode;
	}

	public void setFlowCode(String flowCode) {
		this.flowCode = flowCode;
	}

	public String getFlowName() {
		return flowName;
	}

	public void setFlowName(String flowName) {
		this.flowName = flowName;
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
