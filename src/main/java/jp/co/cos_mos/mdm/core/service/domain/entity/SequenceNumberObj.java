package jp.co.cos_mos.mdm.core.service.domain.entity;

import java.io.Serializable;

/**
 * SequenceNumber入力データクラス。
 * <p>
 * クライアントの入力データ値を管理します。
 * 
 * @author Cosmos Inc.
 */
public class SequenceNumberObj implements Serializable {

	/** SequenceNumberId **/
	private String id;
	
	/** 現在の採番値 **/
	private String seq;
	
	/** 採番管理名 **/
	private String name;
	
	/** 採番初期値 **/
	private String initialValue;
	
	/** 採番増分値 **/
	private String incrementValue;
	
	/** 採番最大値 **/
	private String maxValue;
	
	/** 最終更新日時 **/
	private String lastUpdateTs;
	/**
	 * @return id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id セットする id
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return seq
	 */
	public String getSeq() {
		return seq;
	}
	/**
	 * @param seq セットする seq
	 */
	public void setSeq(String seq) {
		this.seq = seq;
	}
	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name セットする name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return initialValue
	 */
	public String getInitialValue() {
		return initialValue;
	}
	/**
	 * @param initialValue セットする initialValue
	 */
	public void setInitialValue(String initialValue) {
		this.initialValue = initialValue;
	}
	/**
	 * @return incrementValue
	 */
	public String getIncrementValue() {
		return incrementValue;
	}
	/**
	 * @param incrementValue セットする incrementValue
	 */
	public void setIncrementValue(String incrementValue) {
		this.incrementValue = incrementValue;
	}
	/**
	 * @return maxValue
	 */
	public String getMaxValue() {
		return maxValue;
	}
	/**
	 * @param maxValue セットする maxValue
	 */
	public void setMaxValue(String maxValue) {
		this.maxValue = maxValue;
	}
	/**
	 * @return lastUpdateTs
	 */
	public String getLastUpdateTs() {
		return lastUpdateTs;
	}
	/**
	 * @param lastUpdateTs セットする lastUpdateTs
	 */
	public void setLastUpdateTs(String lastUpdateTs) {
		this.lastUpdateTs = lastUpdateTs;
	}

}
