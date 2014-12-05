package jp.co.cos_mos.mdm.core.service;

import jp.co.cos_mos.mdm.core.domain.SequenceNumberServiceRequest;
import jp.co.cos_mos.mdm.core.domain.SequenceNumberServiceResponse;

public interface SequenceNumberService {

	public SequenceNumberServiceResponse createCategory(
			SequenceNumberServiceRequest request);

	public SequenceNumberServiceResponse numbering(
			SequenceNumberServiceRequest request);

	public SequenceNumberServiceResponse reset(
			SequenceNumberServiceRequest request);

}