package com.jdfaster.jdfsample.services.lot;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "mes_lot_comp")
//@IdClass(LotCompositeKey.class)
public class MesLotComp implements Serializable {
	@Id
	@Column(name = "lotId", nullable = false, length = 100)
	private String lotId;
	@Id
	@Column(name = "compLotId", nullable = false, length = 100)
	private String compLotId;
	@Id
	@Column(name = "compMatSn", nullable = false, length = 100)
	private String compMatSn;
	@Column(name = "compMatQty", length = 10)
	private Integer compMatQty;
	@Column(name = "createUserId", length = 100)
	private String createUserId;
	@Column(name = "createTime")
	private Date createTime;
	@Column(name = "updateUserId", length = 100)
	private String updateUserId;
	@Column(name = "updateTime")
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

//class LotCompositeKey implements Serializable {
//	private String lotId;
//	private String compLotId;
//	private String compMatSn;
//}
