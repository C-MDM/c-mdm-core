package jp.co.cos_mos.mdm.core.service.domain.entity;

import java.io.Serializable;

/**
 * SequenceNumberパラメータクラス。
 * <p>
 * クライアントのパラメータ値を管理します。
 * 
 * @author Cosmos Inc.
 */
public class SequenceNumberCriteriaObj implements Serializable {

	/** SequenceNumberId条件値 **/
	private String id;

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
	
}
