package jp.co.cos_mos.mdm.core.service.exception;

import jp.co.cos_mos.mdm.core.service.domain.entity.Result;

public class GetEntityNumberingIdException extends RuntimeException {

	private Result result;
	
	public GetEntityNumberingIdException() {
		this.result = null;
	}

	public GetEntityNumberingIdException(Result result) {
		this.result = result;
	}

	public Result getResult() {
		return result;
	}
	
}
