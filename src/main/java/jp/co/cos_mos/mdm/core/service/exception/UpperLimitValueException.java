package jp.co.cos_mos.mdm.core.service.exception;

import jp.co.cos_mos.mdm.core.service.domain.entity.Result;

public class UpperLimitValueException extends RuntimeException {
	private Result result;
	
	public UpperLimitValueException() {
		this.result = null;
	}
	
	public UpperLimitValueException(Result result) {
		this.result = result;
	}

	public Result getResult() {
		return result;
	}
}
