package jp.co.cos_mos.mdm.core.service.domain.entity;

import java.io.Serializable;

/**
 * サービスメソッド結果データクラスです。
 * <p>
 * クライアントに応答する処理の結果情報を保有するデータクラスです。
 * 
 * @author Cosmos Inc.
 */
public class Result implements Serializable  {

	/** ステータス **/
	private Status status;
	
	/** メッセージ **/
	private Message message;
	
	/**
	 * コンストラクタです。
	 */
	public Result() {
		this.status = Status.SUCCESS;
		this.message = null;
	}

	/**
	 * @return status
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * @param status セットする status
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	/**
	 * @return message
	 */
	public Message getMessage() {
		return message;
	}

	/**
	 * @param message セットする message
	 */
	public void setMessage(Message message) {
		this.message = message;
	}

}
