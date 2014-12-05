package jp.co.cos_mos.mdm.core.service.domain.entity;

import java.io.Serializable;

public class SequenceNumberObj implements Serializable {

	private String id;
	private String seq;
	private String name;
	private String initialValue;
	private String incrementValue;
	private String maxValue;
	private String lastUpdateTs;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getInitialValue() {
		return initialValue;
	}
	public void setInitialValue(String initialValue) {
		this.initialValue = initialValue;
	}
	public String getIncrementValue() {
		return incrementValue;
	}
	public void setIncrementValue(String incrementValue) {
		this.incrementValue = incrementValue;
	}
	public String getMaxValue() {
		return maxValue;
	}
	public void setMaxValue(String maxValue) {
		this.maxValue = maxValue;
	}
	public String getLastUpdateTs() {
		return lastUpdateTs;
	}
	public void setLastUpdateTs(String lastUpdateTs) {
		this.lastUpdateTs = lastUpdateTs;
	}
	

}
