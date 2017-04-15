package com.jdfaster.jdfsample.services.lot.end;

import java.util.Date;

public class EndLotOut {
	private String lotId;
	private String lotStatus;
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
