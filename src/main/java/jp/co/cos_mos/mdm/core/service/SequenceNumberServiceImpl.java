package jp.co.cos_mos.mdm.core.service;

import jp.co.cos_mos.mdm.core.service.action.SequenceNumberCreateAction;
import jp.co.cos_mos.mdm.core.service.action.SequenceNumberGetAction;
import jp.co.cos_mos.mdm.core.service.action.SequenceNumberNumberingAction;
import jp.co.cos_mos.mdm.core.service.action.SequenceNumberResetAction;
import jp.co.cos_mos.mdm.core.service.domain.SequenceNumberServiceRequest;
import jp.co.cos_mos.mdm.core.service.domain.SequenceNumberServiceResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * SequenceNumber サービス実装クラス。
 * 
 * @author Cosmos Inc.
 */
@Service
public class SequenceNumberServiceImpl implements SequenceNumberService {
	
	@Autowired
	private SequenceNumberGetAction getAction;
	@Autowired
	private SequenceNumberCreateAction createAction;
	@Autowired
	private SequenceNumberNumberingAction numberingAction;
	@Autowired
	private SequenceNumberResetAction resetAction;
	
	
	/*
	 * (非 Javadoc)
	 * @see jp.co.cos_mos.mdm.core.service.SequenceNumberService#get(jp.co.cos_mos.mdm.core.service.domain.SequenceNumberServiceRequest)
	 */
	public SequenceNumberServiceResponse get(
			SequenceNumberServiceRequest request) {
		return getAction.perform(request.getControl(), request.getCriteria());
	}

	/* (非 Javadoc)
	 * @see jp.co.cos_mos.mdm.core.service.SequenceNumberService#create(jp.co.cos_mos.mdm.core.domain.SequenceNumberServiceRequest)
	 */
	public SequenceNumberServiceResponse createCategory(
			SequenceNumberServiceRequest request) {
		return createAction.perform(request.getControl(), request.getInput());
	}

	/* (非 Javadoc)
	 * @see jp.co.cos_mos.mdm.core.service.SequenceNumberService#numbering(java.lang.String)
	 */
	public SequenceNumberServiceResponse numbering(
			SequenceNumberServiceRequest request) {
		return numberingAction.perform(request.getControl(), request.getCriteria());
	}

	/* (非 Javadoc)
	 * @see jp.co.cos_mos.mdm.core.service.SequenceNumberService#reset(java.lang.String)
	 */
	public SequenceNumberServiceResponse reset(
			SequenceNumberServiceRequest request) {
		return resetAction.perform(request.getControl(), request.getCriteria());
	}
	
}
