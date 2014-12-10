package jp.co.cos_mos.mdm.core.service.action;

import jp.co.cos_mos.mdm.core.service.domain.SequenceNumberServiceResponse;
import jp.co.cos_mos.mdm.core.service.domain.entity.Control;
import jp.co.cos_mos.mdm.core.service.domain.entity.SequenceNumberCriteriaObj;
import jp.co.cos_mos.mdm.core.service.exception.GetEntityNumberingIdException;

/**
 * シーケンス番号採番アクションインターフェース。
 * 
 * @author Cosmos Inc.
 */
public interface SequenceNumberNumberingAction {

	/**
	 * 指定されたシーケンス管理レコードの採番処理を行います。
	 * 
	 * @param control コントロールオブジェクト
	 * @param criteria パラメータオブジェクト
	 * @return 応答オブジェクト
	 */
	public abstract SequenceNumberServiceResponse perform(Control control,
			SequenceNumberCriteriaObj criteria);

	/**
	 * 指定されたEntityの採番されたID値を取得します。
	 * 
	 * @param classname Entityクラスの完全修飾名
	 * @return ID値
	 * @throws GetEntityNumberingIdException 採番に失敗した場合にスロー
	 */
	public abstract Long getEntityNumberingId(String classname);

}