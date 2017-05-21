package com.jdfaster.jdfsample.services.lot.get_size;

import java.util.List;

public class GetLotSizeIn {
	private String orderId;
	private String operCode;
	private String lotStatus;
	private List<String> lotStatusIn;
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getOperCode() {
		return operCode;
	}
	public void setOperCode(String operCode) {
		this.operCode = operCode;
	}
	public String getLotStatus() {
		return lotStatus;
	}
	public void setLotStatus(String lotStatus) {
		this.lotStatus = lotStatus;
	}
	public List<String> getLotStatusIn() {
		return lotStatusIn;
	}
	public void setLotStatusIn(List<String> lotStatusIn) {
		this.lotStatusIn = lotStatusIn;
	}
}
