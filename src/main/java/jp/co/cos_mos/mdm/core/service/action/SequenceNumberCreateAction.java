package jp.co.cos_mos.mdm.core.service.action;

import jp.co.cos_mos.mdm.core.domain.SequenceNumberServiceResponse;
import jp.co.cos_mos.mdm.core.service.domain.entity.Control;
import jp.co.cos_mos.mdm.core.service.domain.entity.SequenceNumberObj;

public interface SequenceNumberCreateAction {

	/**
	 * 
	 * 
	 * @param control
	 * @param input
	 * @return
	 */
	public abstract SequenceNumberServiceResponse perform(Control control,
			SequenceNumberObj input);
	
}
