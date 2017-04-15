package com.jdfaster.jdfsample.services.lot.end;

import java.util.Date;

public class EndLotIn {
	private String lotId;
	private String lotStatus;
	private Date operStartTime;
	private Date operEndTime;
	private String compOrderId;
	private String compLotId;
	private String compMatSn;
	private Integer compMatQty;
	
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
	
	
}
