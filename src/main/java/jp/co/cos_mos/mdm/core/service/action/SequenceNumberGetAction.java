/**
 * 
 */
package jp.co.cos_mos.mdm.core.service.action;

import jp.co.cos_mos.mdm.core.service.domain.SequenceNumberServiceResponse;
import jp.co.cos_mos.mdm.core.service.domain.entity.Control;
import jp.co.cos_mos.mdm.core.service.domain.entity.SequenceNumberCriteriaObj;

/**
 * シーケンスナンバー管理レコード取得アクションインターフェース。
 * 
 * @author Cosmos Inc.
 */
public interface SequenceNumberGetAction {

	/**
	 * シーケンスナンバー管理レコードを取得します。
	 * 
	 * @param control コントロールオブジェクト
	 * @param criteria パラメータオブジェクト
	 * @return 応答オブジェクト
	 */
	public abstract SequenceNumberServiceResponse perform(
			Control control, SequenceNumberCriteriaObj criteria);
	
}
