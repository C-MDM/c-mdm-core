package jp.co.cos_mos.mdm.core.service.action;

import jp.co.cos_mos.mdm.core.domain.SequenceNumberServiceResponse;
import jp.co.cos_mos.mdm.core.service.domain.entity.Control;
import jp.co.cos_mos.mdm.core.service.domain.entity.SequenceNumberCriteriaObj;
import jp.co.cos_mos.mdm.core.service.exception.GetEntityNumberingIdException;

public interface SequenceNumberNumberingAction {

	/**
	 * 
	 * 
	 * @param request
	 * @return
	 */
	public abstract SequenceNumberServiceResponse perform(Control control,
			SequenceNumberCriteriaObj criteria);

	/**
	 * 採番されたEntityのID値を取得します。
	 * 採番に失敗した場合はnullを返却します。
	 * 
	 * @param classname Entityクラスの完全修飾名
	 * @return ID値
	 * @throws GetEntityNumberingIdException
	 */
	public abstract Long getEntityNumberingId(String classname);

}