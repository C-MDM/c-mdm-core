package jp.co.cos_mos.mdm.core.service.domain.entity;

import java.io.Serializable;

/**
 * 応答メッセージクラスです。
 * <p>
 * クライアントに応答するメッセージを保有するデータクラスです。
 * 
 * @author Cosmos Inc.
 */
public class Message implements Serializable {
	
	/** メッセージ */
	private String message;

	/**
	 * @return message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message セットする message
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
}
