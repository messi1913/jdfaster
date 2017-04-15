package com.jdfaster.jdfsample.services.lot;

import java.util.Date;

public class MesLot {
	private String lotId;
	// CREATE, OPERIN, OPERSTART, OPEREND, SHIP, STORE, USE, DELETE
	private String lotStatus;
	private String matCode;
	private Integer lotQty;
	private String locCode;
	private String flowCode;
	private String operCode;
	private Date operInTime;
	private Date operStartTime;
	private Date operEndTime;
	private String orderId;
	private String compOrderId;
	private String createUserId;
	private Date createTime;
	private String updateUserId;
	private Date updateTime;

	public String getLotId() {
		return lotId;
	}

	public void setLotId(String lotId) {
		this.lotId = lotId;
	}

	public String getLotStatus() {
		return lotStatus;
	}

	public void setLotStatus(String lotStatus) {
		this.lotStatus = lotStatus;
	}

	public String getMatCode() {
		return matCode;
	}

	public void setMatCode(String matCode) {
		this.matCode = matCode;
	}

	public Integer getLotQty() {
		return lotQty;
	}

	public void setLotQty(Integer lotQty) {
		this.lotQty = lotQty;
	}

	public String getLocCode() {
		return locCode;
	}

	public void setLocCode(String locCode) {
		this.locCode = locCode;
	}

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

	public Date getOperInTime() {
		return operInTime;
	}

	public void setOperInTime(Date operInTime) {
		this.operInTime = operInTime;
	}

	public Date getOperStartTime() {
		return operStartTime;
	}

	public void setOperStartTime(Date operStartTime) {
		this.operStartTime = operStartTime;
	}

	public Date getOperEndTime() {
		return operEndTime;
	}

	public void setOperEndTime(Date operEndTime) {
		this.operEndTime = operEndTime;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getCompOrderId() {
		return compOrderId;
	}

	public void setCompOrderId(String compOrderId) {
		this.compOrderId = compOrderId;
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
