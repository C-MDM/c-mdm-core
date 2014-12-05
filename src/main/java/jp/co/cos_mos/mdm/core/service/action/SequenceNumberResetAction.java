package jp.co.cos_mos.mdm.core.service.action;

import jp.co.cos_mos.mdm.core.domain.SequenceNumberServiceResponse;
import jp.co.cos_mos.mdm.core.service.domain.entity.Control;
import jp.co.cos_mos.mdm.core.service.domain.entity.SequenceNumberCriteriaObj;

public interface SequenceNumberResetAction {

	/**
	 * 
	 * 
	 * @param request
	 * @return
	 */
	public abstract SequenceNumberServiceResponse perform(Control control,
			SequenceNumberCriteriaObj criteria);
}
