package jp.co.cos_mos.mdm.core.service.domain.entity;

import java.io.Serializable;

public class Control implements Serializable {
	private String requesterName;
	private Long requestId;
	private Long transactionId;
	
	public String getRequesterName() {
		return requesterName;
	}
	public void setRequesterName(String requesterName) {
		this.requesterName = requesterName;
	}
	public Long getRequestId() {
		return requestId;
	}
	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}
	public Long getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}
	
}
