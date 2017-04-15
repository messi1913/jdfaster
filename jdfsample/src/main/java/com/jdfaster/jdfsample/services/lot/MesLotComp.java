package com.jdfaster.jdfsample.services.lot;

import java.util.Date;

public class MesLotComp {
	private String lotId;
	private String compLotId;
	private String compMatSn;
	private Integer compMatQty;
	private String createUserId;
	private Date createTime;
	private String updateUserId;
	private Date updateTime;
	public String getLotId() {
		return lotId;
	}
	public void setLotId(String lotId) {
		this.lotId = lotId;
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
