package jp.co.cos_mos.mdm.core.service.exception;

import jp.co.cos_mos.mdm.core.service.domain.entity.Result;

public class ConflictRequestException extends RuntimeException {
	private Result result;
	
	public ConflictRequestException() {
		this.result = null;
	}
	
	public ConflictRequestException(Result result) {
		this.result = result;
	}

	public Result getResult() {
		return result;
	}

}
