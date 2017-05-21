package com.jdfaster.jdfsample.services.mat;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "mes_mat")
public class MesMat implements Serializable {
	@Id
	@Column(name = "matCode", nullable = false, length = 50)
	private String matCode;
	@Column(name = "matName", length = 500)
	private String matName;
	@Column(name = "matType", length = 50)
	private String matType;
	@Column(name = "flowCode", length = 50)
	private String flowCode;
	@Column(name = "createUserId", length = 100)
	private String createUserId;
	@Column(name = "createTime")
	private Date createTime;
	@Column(name = "updateUserId", length = 100)
	private String updateUserId;
	@Column(name = "updateTime")
	private Date updateTime;

	public String getMatCode() {
		return matCode;
	}

	public void setMatCode(String matCode) {
		this.matCode = matCode;
	}

	public String getMatName() {
		return matName;
	}

	public void setMatName(String matName) {
		this.matName = matName;
	}

	public String getMatType() {
		return matType;
	}

	public void setMatType(String matType) {
		this.matType = matType;
	}

	public String getFlowCode() {
		return flowCode;
	}

	public void setFlowCode(String flowCode) {
		this.flowCode = flowCode;
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
