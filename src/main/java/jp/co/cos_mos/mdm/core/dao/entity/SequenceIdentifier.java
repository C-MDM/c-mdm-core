package jp.co.cos_mos.mdm.core.dao.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * SequenceIdentifieデータクラスです。
 * <p>
 * SequenceNumberIdに予約されたエンティティのIDを管理します。
 * 各エンティティクラスのID採番時にアクセスされます。
 * 
 * @author Cosmos Inc.
 */
public class SequenceIdentifier implements Serializable {
	
	/** Entity クラス名 **/
	private String className;
	
	/** SequenceNumber EntityId **/
	private Long sequenceId;
	
	/** データ無効日時 **/
	private Timestamp inactiveTs;
	
	/** 最終更新日時 **/
	private Timestamp lastUpdateTs;
	
	/** 最終更新者 **/
	private String lastUpdateUser;
	
	/** 最終更新トランザクションID **/
	private Long lastUpdateTxId;
	
	/**
	 * @return className
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * @param className セットする className
	 */
	public void setClassName(String className) {
		this.className = className;
	}

	/**
	 * @return sequenceId
	 */
	public Long getSequenceId() {
		return sequenceId;
	}

	/**
	 * @param sequenceId セットする sequenceId
	 */
	public void setSequenceId(Long sequenceId) {
		this.sequenceId = sequenceId;
	}

	/**
	 * @return inactiveTs
	 */
	public Timestamp getInactiveTs() {
		return inactiveTs;
	}

	/**
	 * @param inactiveTs セットする inactiveTs
	 */
	public void setInactiveTs(Timestamp inactiveTs) {
		this.inactiveTs = inactiveTs;
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
	 * @return lastUpdateUser
	 */
	public String getLastUpdateUser() {
		return lastUpdateUser;
	}

	/**
	 * @param lastUpdateUser セットする lastUpdateUser
	 */
	public void setLastUpdateUser(String lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser;
	}

	/**
	 * @return lastUpdateTxId
	 */
	public Long getLastUpdateTxId() {
		return lastUpdateTxId;
	}

	/**
	 * @param lastUpdateTxId セットする lastUpdateTxId
	 */
	public void setLastUpdateTxId(Long lastUpdateTxId) {
		this.lastUpdateTxId = lastUpdateTxId;
	}
	
}
