package jp.co.cos_mos.mdm.core.service.exception;

import jp.co.cos_mos.mdm.core.service.domain.entity.Result;

/**
 * 最大値達成例外クラスです。
 * <p>
 * EntityId採番処理で最大採番値に達した場合にスローされます。
 * @author Cosmos Inc.
 */
public class UpperLimitValueException extends RuntimeException {

	/** 処理結果オブジェクト **/
	private Result result;
	
	/**
	 * コンストラクタです。
	 */
	public UpperLimitValueException() {
		this.result = null;
	}
	
	/**
	 * コンストラクタです。
	 * 
	 * @param result 処理結果オブジェクト
	 */
	public UpperLimitValueException(Result result) {
		this.result = result;
	}

	/**
	 * @return result
	 */
	public Result getResult() {
		return result;
	}

}
