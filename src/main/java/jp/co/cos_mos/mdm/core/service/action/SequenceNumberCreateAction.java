package jp.co.cos_mos.mdm.core.service.action;

import jp.co.cos_mos.mdm.core.service.domain.SequenceNumberServiceResponse;
import jp.co.cos_mos.mdm.core.service.domain.entity.Control;
import jp.co.cos_mos.mdm.core.service.domain.entity.SequenceNumberObj;

/**
 * シーケンスナンバー管理レコード登録アクションインターフェース。
 * 
 * @author Cosmos Inc.
 */
public interface SequenceNumberCreateAction {

	/**
	 * シーケンスナンバー管理レコードを登録します。
	 * 
	 * @param control コントロールオブジェクト
	 * @param input 入力データオブジェクト
	 * @return サービス応答オブジェクト
	 */
	public abstract SequenceNumberServiceResponse perform(Control control,
			SequenceNumberObj input);
	
}
