package com.jdfaster.jdfsample.services.lot.pack;

import java.util.List;

public class PackLotIn {
	private String lotId;
	private String locCode;
	private String operCode;
	private List<String> lotIdList;

	public String getLotId() {
		return lotId;
	}

	public void setLotId(String lotId) {
		this.lotId = lotId;
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

	public List<String> getLotIdList() {
		return lotIdList;
	}

	public void setLotIdList(List<String> lotIdList) {
		this.lotIdList = lotIdList;
	}
}
