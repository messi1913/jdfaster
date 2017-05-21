package com.jdfaster.jdfsample.services.mat;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "mes_mat_comp")
@IdClass(MatCompositeKey.class)
public class MesMatComp implements Serializable {
	@Id
	@Column(name = "matCode", nullable = false, length = 50)
	private String matCode;
	@Column(name = "compMatCode", length = 50)
	private String compMatCode;
	@Column(name = "compMatQty", length = 10)
	private Integer compMatQty;
	@Column(name = "operCode", length = 100)
	private String operCode;
	@Column(name = "createUserId", length = 100)
	private String createUserId;
	@Column(name = "createTime")
	private Date createTime;
	@Column(name = "updateUserId", length = 100)
	private String updateUserId;
	@Column(name = "updateTime")
	private Date updateTime;

	public String getMatCode() {
		return matCode;
	}

	public void setMatCode(String matCode) {
		this.matCode = matCode;
	}

	public String getCompMatCode() {
		return compMatCode;
	}

	public void setCompMatCode(String compMatCode) {
		this.compMatCode = compMatCode;
	}

	public Integer getCompMatQty() {
		return compMatQty;
	}

	public void setCompMatQty(Integer compMatQty) {
		this.compMatQty = compMatQty;
	}

	public String getOperCode() {
		return operCode;
	}

	public void setOperCode(String operCode) {
		this.operCode = operCode;
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

class MatCompositeKey implements Serializable {
	private String matCode;
	private String compMatCode;
}