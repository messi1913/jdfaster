package com.jdfaster.jdfsample.services.lot.send;

public class SendLotIn {
	private String lotId;
	private String lotStatus;
	private Integer lotQty;
	private String locCode;
	private String flowCode;
	private String operCode;
	
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
	
	
}
