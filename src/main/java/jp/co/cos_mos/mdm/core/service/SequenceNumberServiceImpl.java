package jp.co.cos_mos.mdm.core.service;

import jp.co.cos_mos.mdm.core.domain.SequenceNumberServiceRequest;
import jp.co.cos_mos.mdm.core.domain.SequenceNumberServiceResponse;
import jp.co.cos_mos.mdm.core.service.action.SequenceNumberCreateAction;
import jp.co.cos_mos.mdm.core.service.action.SequenceNumberNumberingAction;
import jp.co.cos_mos.mdm.core.service.action.SequenceNumberResetAction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SequenceNumberServiceImpl implements SequenceNumberService {
	
	@Autowired
	private SequenceNumberCreateAction createAction;
	@Autowired
	private SequenceNumberNumberingAction numberingAction;
	@Autowired
	private SequenceNumberResetAction resetAction;
	
	
	/* (非 Javadoc)
	 * @see jp.co.cos_mos.mdm.core.service.SequenceNumberService#create(jp.co.cos_mos.mdm.core.domain.SequenceNumberServiceRequest)
	 */
	@Transactional
	public SequenceNumberServiceResponse createCategory(
			SequenceNumberServiceRequest request) {
		return createAction.perform(request.getControl(), request.getInput());
	}

	/* (非 Javadoc)
	 * @see jp.co.cos_mos.mdm.core.service.SequenceNumberService#numbering(java.lang.String)
	 */
	@Transactional
	public SequenceNumberServiceResponse numbering(
			SequenceNumberServiceRequest request) {
		return numberingAction.perform(request.getControl(), request.getCriteria());
	}

	/* (非 Javadoc)
	 * @see jp.co.cos_mos.mdm.core.service.SequenceNumberService#reset(java.lang.String)
	 */
	@Transactional
	public SequenceNumberServiceResponse reset(
			SequenceNumberServiceRequest request) {
		return resetAction.perform(request.getControl(), request.getCriteria());
	}
	
}
