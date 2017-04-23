package com.jdfaster.jdfsample.services.test;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mes_test_status")
public class MesTestStatus {
	@Id
	@Column(name = "test_name", nullable = false, length = 100)
	private String testName;
	@Column(name = "locCode", length = 50)
	private String locCode;
	@Column(name = "orderId", length = 100)
	private String orderId;
	@Column(name = "leftQty", length = 10)
	private Integer leftQty;
	@Column(name = "createUserId", length = 100)
	private String createUserId;
	@Column(name = "createTime")
	private Date createTime;
	@Column(name = "updateUserId", length = 100)
	private String updateUserId;
	@Column(name = "updateTime")
	private Date updateTime;

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public String getLocCode() {
		return locCode;
	}

	public void setLocCode(String locCode) {
		this.locCode = locCode;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Integer getLeftQty() {
		return leftQty;
	}

	public void setLeftQty(Integer leftQty) {
		this.leftQty = leftQty;
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
