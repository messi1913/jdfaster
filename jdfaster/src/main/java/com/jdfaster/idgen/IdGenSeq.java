package com.jdfaster.idgen;

import java.util.Date;

public class IdGenSeq {
	private String idRuleCode;
	private String idPattern;
	private String lastSeqs;
	private Date createdAt;
	private Date updatedAt;
	public String getIdRuleCode() {
		return idRuleCode;
	}
	public void setIdRuleCode(String idRuleCode) {
		this.idRuleCode = idRuleCode;
	}
	public String getIdPattern() {
		return idPattern;
	}
	public void setIdPattern(String idPattern) {
		this.idPattern = idPattern;
	}
	public String getLastSeqs() {
		return lastSeqs;
	}
	public void setLastSeqs(String lastSeqs) {
		this.lastSeqs = lastSeqs;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
}
