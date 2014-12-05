package jp.co.cos_mos.mdm.core.service.domain.entity;

import java.io.Serializable;

public class Result implements Serializable  {

	private Status status;
	private Message message;
	
	public Result() {
		this.status = Status.SUCCESS;
		this.message = null;
	}
	
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}

}
