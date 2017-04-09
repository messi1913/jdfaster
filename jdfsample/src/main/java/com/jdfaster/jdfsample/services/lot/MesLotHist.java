package com.jdfaster.jdfsample.services.lot;

import java.util.Date;

public class MesLotHist {
	private String lotId;
	private Integer seqNo;
	private String tranCode;
	private String lotStatus;
	private String matCode;
	private Integer lotQty;
	private String locCode;
	private String operCode;
	private Date operInTime;
	private Date operStartTime;
	private Date operEndTime;
	private String orderId;
	private String compOrderId;
	private String createUsername;
	private Date createTime;
	private String updateUsername;
	private Date updateTime;
	public String getLotId() {
		return lotId;
	}
	public void setLotId(String lotId) {
		this.lotId = lotId;
	}
	public Integer getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}
	public String getTranCode() {
		return tranCode;
	}
	public void setTranCode(String tranCode) {
		this.tranCode = tranCode;
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
