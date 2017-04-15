package com.jdfaster.jdfsample.services.lot.ship;

import java.util.List;

public class ShipLotIn {
	private String locCode;
	private String operCode;
	private String toLocCode;
	private List<String> lotIdList;

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

	public String getToLocCode() {
		return toLocCode;
	}

	public void setToLocCode(String toLocCode) {
		this.toLocCode = toLocCode;
	}

	public List<String> getLotIdList() {
		return lotIdList;
	}

	public void setLotIdList(List<String> lotIdList) {
		this.lotIdList = lotIdList;
	}
}
