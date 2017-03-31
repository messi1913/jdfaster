package com.jdfaster.jdfsample.qm.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "shipment")
public class Shipment {
	@Id
	@Column (name = "lotNo", nullable = false, length = 100)
	private String lotNo;
	@Column (name = "lotStatus", length = 20)
	private String lotStatus;
	@Column (name = "succYn", length = 1)
	private String succYn;
	
	
	public String getLotNo() {
		return lotNo;
	}
	public void setLotNo(String lotNo) {
		this.lotNo = lotNo;
	}
	public String getLotStatus() {
		return lotStatus;
	}
	public void setLotStatus(String lotStatus) {
		this.lotStatus = lotStatus;
	}
	public String getSuccYn() {
		return succYn;
	}
	public void setSuccYn(String succYn) {
		this.succYn = succYn;
	}
	@Override
	public String toString() {
		return "Shipment [lotNo=" + lotNo + ", lotStatus=" + lotStatus + ", succYn=" + succYn + "]";
	}
	
	
	
}
