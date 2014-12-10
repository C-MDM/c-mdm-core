package jp.co.cos_mos.mdm.core.service.exception;

import jp.co.cos_mos.mdm.core.service.domain.entity.Result;

/**
 * EntityID採番失敗例外クラスです。
 * <p>
 * 採番処理が失敗した場合などにスローされる非検査例外です。
 * @author Cosmos Inc.
 */
public class GetEntityNumberingIdException extends RuntimeException {

	/** 処理結果オブジェクト **/
	private Result result;
	
	/**
	 * コンストラクタです。
	 */
	public GetEntityNumberingIdException() {
		this.result = null;
	}

	/**
	 * コンストラクタです。
	 * 
	 * @param result 処理結果オブジェクト
	 */
	public GetEntityNumberingIdException(Result result) {
		this.result = result;
	}

	/**
	 * @return result
	 */
	public Result getResult() {
		return result;
	}
	
}
