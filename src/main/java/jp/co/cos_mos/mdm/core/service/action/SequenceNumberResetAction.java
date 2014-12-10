package jp.co.cos_mos.mdm.core.service.action;

import jp.co.cos_mos.mdm.core.service.domain.SequenceNumberServiceResponse;
import jp.co.cos_mos.mdm.core.service.domain.entity.Control;
import jp.co.cos_mos.mdm.core.service.domain.entity.SequenceNumberCriteriaObj;

/**
 * シーケンス管理レコード初期値設定アクションインターフェース。
 * 
 * @author Cosmos Inc.
 */
public interface SequenceNumberResetAction {

	/**
	 * 指定されたシーケンス管理レコードのシーケンス番号を初期値に更新します。
	 * 
	 * @param control コントロールオブジェクト
	 * @param criteria パラメータオブジェクト
	 * @return 応答オブジェクト
	 */
	public abstract SequenceNumberServiceResponse perform(Control control,
			SequenceNumberCriteriaObj criteria);
}
