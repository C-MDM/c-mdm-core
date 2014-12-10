package jp.co.cos_mos.mdm.core.dao.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * SequenceNumberデータクラスです。
 * <p>
 * シーケンス番号を管理します。
 * ID単位にシーケンス番号を管理します。
 * 
 * @author Cosmos Inc.
 */
public class SequenceNumber implements Serializable {

	/** SequenceNumberId **/
	private Long id;
	
	/** 現在の採番値 **/
	private Long seq;
	
	/** 採番管理名 **/
	private String name;
	
	/** 採番初期値 **/
	private int initialValue;
	
	/** 採番増分値 **/
	private int incrementValue;
	
	/** 採番最大値 **/
	private int maxValue;
	
	/** 最終更新日時 **/
	private Timestamp lastUpdateTs;
	
	/** 現在日時 **/
	private Timestamp currentTs;

	/**
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id セットする id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return seq
	 */
	public Long getSeq() {
		return seq;
	}

	/**
	 * @param seq セットする seq
	 */
	public void setSeq(Long seq) {
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
	public int getInitialValue() {
		return initialValue;
	}

	/**
	 * @param initialValue セットする initialValue
	 */
	public void setInitialValue(int initialValue) {
		this.initialValue = initialValue;
	}

	/**
	 * @return incrementValue
	 */
	public int getIncrementValue() {
		return incrementValue;
	}

	/**
	 * @param incrementValue セットする incrementValue
	 */
	public void setIncrementValue(int incrementValue) {
		this.incrementValue = incrementValue;
	}

	/**
	 * @return maxValue
	 */
	public int getMaxValue() {
		return maxValue;
	}

	/**
	 * @param maxValue セットする maxValue
	 */
	public void setMaxValue(int maxValue) {
		this.maxValue = maxValue;
	}

	/**
	 * @return lastUpdateTs
	 */
	public Timestamp getLastUpdateTs() {
		return lastUpdateTs;
	}

	/**
	 * @param lastUpdateTs セットする lastUpdateTs
	 */
	public void setLastUpdateTs(Timestamp lastUpdateTs) {
		this.lastUpdateTs = lastUpdateTs;
	}

	/**
	 * @return currentTs
	 */
	public Timestamp getCurrentTs() {
		return currentTs;
	}

	/**
	 * @param currentTs セットする currentTs
	 */
	public void setCurrentTs(Timestamp currentTs) {
		this.currentTs = currentTs;
	}
	
}
