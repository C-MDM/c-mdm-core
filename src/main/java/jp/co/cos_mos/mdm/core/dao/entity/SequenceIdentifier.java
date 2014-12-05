package jp.co.cos_mos.mdm.core.dao.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class SequenceIdentifier implements Serializable {
	
	private String className;
	private Long sequenceId;
	private Timestamp inactiveTs;;
	private Timestamp lastUpdateTs;
	private String lastUpdateUser;
	private Long lastUpdateTxId;

	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public Long getSequenceId() {
		return sequenceId;
	}
	public void setSequenceId(Long sequenceId) {
		this.sequenceId = sequenceId;
	}
	public Timestamp getInactiveTs() {
		return inactiveTs;
	}
	public void setInactiveTs(Timestamp inactiveTs) {
		this.inactiveTs = inactiveTs;
	}
	public Timestamp getLastUpdateTs() {
		return lastUpdateTs;
	}
	public void setLastUpdateTs(Timestamp lastUpdateTs) {
		this.lastUpdateTs = lastUpdateTs;
	}
	public String getLastUpdateUser() {
		return lastUpdateUser;
	}
	public void setLastUpdateUser(String lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser;
	}
	public Long getLastUpdateTxId() {
		return lastUpdateTxId;
	}
	public void setLastUpdateTxId(Long lastUpdateTxId) {
		this.lastUpdateTxId = lastUpdateTxId;
	}
	
	
}
