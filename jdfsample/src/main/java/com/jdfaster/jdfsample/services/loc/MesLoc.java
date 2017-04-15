package com.jdfaster.jdfsample.services.loc;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="mes_flow_oper")
public class MesLoc {
	@Id
	@Column (name = "locCode", nullable = false, length = 100)
	private String locCode;
	@Column (name = "locName", length = 500)
	private String locName;
	// STORE/LINE
	@Column (name = "locType", length = 50)
	private String locType;
	@Column (name = "createUserId", length = 100)
	private String createUserId;
	@Column (name = "createTime", length = 16)
	private Date createTime;
	@Column (name = "updateUserId", length = 100)
	private String updateUserId;
	@Column (name = "updateTime", length = 16)
	private Date updateTime;

	public String getLocCode() {
		return locCode;
	}

	public void setLocCode(String locCode) {
		this.locCode = locCode;
	}

	public String getLocName() {
		return locName;
	}

	public void setLocName(String locName) {
		this.locName = locName;
	}

	public String getLocType() {
		return locType;
	}

	public void setLocType(String locType) {
		this.locType = locType;
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
