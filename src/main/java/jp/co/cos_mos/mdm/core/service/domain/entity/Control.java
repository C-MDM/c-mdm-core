package jp.co.cos_mos.mdm.core.service.domain.entity;

import java.io.Serializable;

/**
 * コントロールデータクラスです。
 * <p>
 * トランザクションコントロール情報を保有するデータクラスです。
 * <p>
 * 最終更新日、最終更新者、追跡ログ情報に利用されます。
 * 
 * @author Cosmos Inc.
 */
public class Control implements Serializable {

	/** クライアント要求者名 **/
	private String requesterName;
	
	/** クライアント要求ID **/
	private Long requestId;
	
	/** サーバー要求ID **/
	private Long transactionId;

	/**
	 * @return requesterName
	 */
	public String getRequesterName() {
		return requesterName;
	}

	/**
	 * @param requesterName セットする requesterName
	 */
	public void setRequesterName(String requesterName) {
		this.requesterName = requesterName;
	}

	/**
	 * @return requestId
	 */
	public Long getRequestId() {
		return requestId;
	}

	/**
	 * @param requestId セットする requestId
	 */
	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}

	/**
	 * @return transactionId
	 */
	public Long getTransactionId() {
		return transactionId;
	}

	/**
	 * @param transactionId セットする transactionId
	 */
	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}
	
}
