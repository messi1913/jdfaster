package com.jdfaster.jdfsample.services.order;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="mes_order")
public class MesOrder {
	@Id
	@Column (name = "orderId", nullable = false, length = 100)
	private String orderId;
	@Column (name = "orderDesc", length = 1000)
	private String orderDesc;
	// CREATE, START, END
	@Column (name = "orderStatus", length = 50)
	private String orderStatus;
	@Column (name = "matCode", length = 50)
	private String matCode;
	@Column (name = "locCode", length = 50)
	private String locCode;
	@Column (name = "orderQty", length = 10)
	private Integer orderQty;
	@Column (name = "inputQty", length = 10)
	private Integer inputQty;
	@Column (name = "outputQty", length = 10)
	private Integer outputQty;
	@Column (name = "startTime")
	private Date startTime;
	@Column (name = "endTime")
	private Date endTime;
	@Column (name = "createUserId", length = 100)
	private String createUserId;
	@Column (name = "createTime")
	private Date createTime;
	@Column (name = "updateUserId", length = 100)
	private String updateUserId;
	@Column (name = "updateTime")
	private Date updateTime;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderDesc() {
		return orderDesc;
	}

	public void setOrderDesc(String orderDesc) {
		this.orderDesc = orderDesc;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getMatCode() {
		return matCode;
	}

	public void setMatCode(String matCode) {
		this.matCode = matCode;
	}

	public String getLocCode() {
		return locCode;
	}

	public void setLocCode(String locCode) {
		this.locCode = locCode;
	}

	public Integer getOrderQty() {
		return orderQty;
	}

	public void setOrderQty(Integer orderQty) {
		this.orderQty = orderQty;
	}

	public Integer getInputQty() {
		return inputQty;
	}

	public void setInputQty(Integer inputQty) {
		this.inputQty = inputQty;
	}

	public Integer getOutputQty() {
		return outputQty;
	}

	public void setOutputQty(Integer outputQty) {
		this.outputQty = outputQty;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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
