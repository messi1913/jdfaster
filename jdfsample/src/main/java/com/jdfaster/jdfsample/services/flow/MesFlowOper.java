package com.jdfaster.jdfsample.services.flow;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name ="mes_flow_oper")
@IdClass(FlowCompositeKey.class)
public class MesFlowOper {
	@Id
	@Column (name = "flowCode", nullable = false, length = 50)
	private String flowCode;
	@Id
	@Column (name = "operCode", nullable = false, length = 50)
	private String operCode;
	@Column (name = "seqNo", length = 100)
	private Integer seqNo;
	@Column (name = "createUserId", length = 100)
	private String createUserId;
	@Column (name = "createTime")
	private Date createTime;
	@Column (name = "updateUserId", length = 100)
	private String updateUserId;
	@Column (name = "updateTime")
	private Date updateTime;

	public String getFlowCode() {
		return flowCode;
	}

	public void setFlowCode(String flowCode) {
		this.flowCode = flowCode;
	}

	public String getOperCode() {
		return operCode;
	}

	public void setOperCode(String operCode) {
		this.operCode = operCode;
	}

	public Integer getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(Integer seqNum) {
		this.seqNo = seqNum;
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

class FlowCompositeKey implements Serializable{
	private String flowCode;
	private String operCode;
}