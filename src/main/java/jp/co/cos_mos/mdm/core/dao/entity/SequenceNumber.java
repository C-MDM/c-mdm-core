package jp.co.cos_mos.mdm.core.dao.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class SequenceNumber implements Serializable {

	private Long id;
	private Long seq;
	private String name;
	private int initialValue;
	private int incrementValue;
	private int maxValue;
	private Timestamp lastUpdateTs;
	
	private Timestamp currentTs;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getSeq() {
		return seq;
	}
	public void setSeq(Long seq) {
		this.seq = seq;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getInitialValue() {
		return initialValue;
	}
	public void setInitialValue(int initialValue) {
		this.initialValue = initialValue;
	}
	public int getIncrementValue() {
		return incrementValue;
	}
	public void setIncrementValue(int incrementValue) {
		this.incrementValue = incrementValue;
	}
	public int getMaxValue() {
		return maxValue;
	}
	public void setMaxValue(int maxValue) {
		this.maxValue = maxValue;
	}
	public Timestamp getLastUpdateTs() {
		return lastUpdateTs;
	}
	public void setLastUpdateTs(Timestamp lastUpdateTs) {
		this.lastUpdateTs = lastUpdateTs;
	}
	public Timestamp getCurrentTs() {
		return currentTs;
	}
	public void setCurrentTs(Timestamp currentTs) {
		this.currentTs = currentTs;
	}

	
}
