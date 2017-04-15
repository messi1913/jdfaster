package com.jdfaster.jdfsample.services.lot.instore;

import java.util.Date;

public class InstoreLotOut {
	private String lotId;
	private String lotStatus;
	private String compOrderId;
	private String compLotId;
	private String compMatSn;
	private Integer compMatQty;
	private Date operStartTime;
	private Date operEndTime;
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
	public String getCompOrderId() {
		return compOrderId;
	}
	public void setCompOrderId(String compOrderId) {
		this.compOrderId = compOrderId;
	}
	public String getCompLotId() {
		return compLotId;
	}
	public void setCompLotId(String compLotId) {
		this.compLotId = compLotId;
	}
	public String getCompMatSn() {
		return compMatSn;
	}
	public void setCompMatSn(String compMatSn) {
		this.compMatSn = compMatSn;
	}
	public Integer getCompMatQty() {
		return compMatQty;
	}
	public void setCompMatQty(Integer compMatQty) {
		this.compMatQty = compMatQty;
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
	
	
}
