package jp.co.cos_mos.mdm.core.service.exception;

import jp.co.cos_mos.mdm.core.service.domain.entity.Result;

/**
 * データ更新衝突例外クラスです。
 * <p>
 * 排他キー条件で更新が失敗した場合にスローします。
 * 
 * @author Cosmos Inc.
 */
public class ConflictRequestException extends RuntimeException {
	
	/** 処理結果オブジェクト **/
	private Result result;
	
	/**
	 * コンストラクタです。
	 */
	public ConflictRequestException() {
		this.result = null;
	}
	
	/**
	 * コンストラクタです。
	 * 
	 * @param result 処理結果オブジェクト
	 */
	public ConflictRequestException(Result result) {
		this.result = result;
	}

	/**
	 * @return result
	 */
	public Result getResult() {
		return result;
	}

}
