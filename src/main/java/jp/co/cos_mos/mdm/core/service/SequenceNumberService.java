package jp.co.cos_mos.mdm.core.service;

import jp.co.cos_mos.mdm.core.service.domain.SequenceNumberServiceRequest;
import jp.co.cos_mos.mdm.core.service.domain.SequenceNumberServiceResponse;

/**
 * SequenceNumber サービスインターフェース。
 * 
 * @author Cosmos Inc.
 */
public interface SequenceNumberService {

	/**
	 * 指定されたシーケンスナンバー管理レコードを取得します。
	 * 
	 * @param request 要求オブジェクト
	 * @return 応答オブジェクト
	 */
	public SequenceNumberServiceResponse get(
			SequenceNumberServiceRequest request);
	
	/**
	 * シーケンスナンバー管理レコードを登録します。
	 * 
	 * @param request 要求オブジェクト
	 * @return 応答オブジェクト
	 */
	public SequenceNumberServiceResponse createCategory(
			SequenceNumberServiceRequest request);

	/**
	 * 指定されたシーケンスナンバー管理レコードの採番を行います。
	 * 
	 * @param request 要求オブジェクト
	 * @return 応答オブジェクト
	 */
	public SequenceNumberServiceResponse numbering(
			SequenceNumberServiceRequest request);

	/**
	 * 指定されたシーケンスナンバー管理レコードを初期値に更新します。
	 * 
	 * @param request 要求オブジェクト
	 * @return 応答オブジェクト
	 */
	public SequenceNumberServiceResponse reset(
			SequenceNumberServiceRequest request);

}