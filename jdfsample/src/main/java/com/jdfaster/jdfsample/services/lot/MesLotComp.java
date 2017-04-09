package com.jdfaster.jdfsample.services.lot;

import java.util.Date;

public class MesLotComp {
	private String lotId;
	private String compLotId;
	private String compMatSn;
	private Integer compMatQty;
	private String createUsername;
	private Date createTime;
	private String updateUsername;
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
	public String getCreateUsername() {
		return createUsername;
	}
	public void setCreateUsername(String createUsername) {
		this.createUsername = createUsername;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getUpdateUsername() {
		return updateUsername;
	}
	public void setUpdateUsername(String updateUsername) {
		this.updateUsername = updateUsername;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
