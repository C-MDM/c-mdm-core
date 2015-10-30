/**
 * 
 */
package jp.co.cos_mos.mdm.core.service.action;

import jp.co.cos_mos.mdm.core.dao.entity.SequenceNumber;
import jp.co.cos_mos.mdm.core.dao.mapper.SequenceNumberMapper;
import jp.co.cos_mos.mdm.core.service.domain.SequenceNumberServiceResponse;
import jp.co.cos_mos.mdm.core.service.domain.entity.Control;
import jp.co.cos_mos.mdm.core.service.domain.entity.Result;
import jp.co.cos_mos.mdm.core.service.domain.entity.SequenceNumberCriteriaObj;
import jp.co.cos_mos.mdm.core.service.domain.entity.SequenceNumberObj;
import jp.co.cos_mos.mdm.core.service.domain.entity.Status;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SequenceNumberGetActionImpl implements SequenceNumberGetAction {

	@Autowired
	private SequenceNumberMapper sequenceNumberMapper;
	
	
	/* (非 Javadoc)
	 * @see jp.co.cos_mos.mdm.core.service.action.SequenceNumberGetAction#perform(jp.co.cos_mos.mdm.core.service.domain.entity.Control, jp.co.cos_mos.mdm.core.service.domain.entity.SequenceNumberCriteriaObj)
	 */
	@Transactional(readOnly=true)
	public SequenceNumberServiceResponse perform(
			Control control,
			SequenceNumberCriteriaObj criteria) {
		
		SequenceNumberServiceResponse response 
			= new SequenceNumberServiceResponse();
		response.setControl(control);
		
		Result result = validate(criteria);
		if (result.getStatus() != Status.SUCCESS) {
			response.setResult(result);
			return response;
		}
		
		Long seqId = Long.valueOf(criteria.getId());
		SequenceNumber selectSequenceNumber = sequenceNumberMapper.select(seqId);
		
		if (selectSequenceNumber == null) {
			result.setStatus(Status.DATA_NOT_FOUND);
			response.setResult(result);
			return response;
		}
		
		SequenceNumberObj output = new SequenceNumberObj();
		output.setId(
				selectSequenceNumber.getId().toString());
		output.setSeq(
				selectSequenceNumber.getSeq().toString());
		output.setName(
				selectSequenceNumber.getName());
		output.setIncrementValue(
				String.valueOf(selectSequenceNumber.getIncrementValue()));
		output.setInitialValue(
				String.valueOf(selectSequenceNumber.getInitialValue()));
		output.setLastUpdateTs(
				selectSequenceNumber.getLastUpdateTs().toString());
		output.setMaxValue(
				String.valueOf(selectSequenceNumber.getMaxValue()));
		
		response.setOutput(output);
		response.setResult(result);

		return response;
	}

	/**
	 * 入力パラメータを検査する。
	 * 
	 * @param criteria 入力パラメータ
	 * @return 検査結果オブジェクト
	 */
	private Result validate(SequenceNumberCriteriaObj criteria) {
		Result result = new Result();
		
		if (criteria == null) {
			result.setStatus(Status.BAD_REQUEST_VALUE);
			return result;
		}
		if (StringUtils.isEmpty(criteria.getId()) || !StringUtils.isNumeric(criteria.getId())) {
			result.setStatus(Status.BAD_REQUEST_VALUE);
			return result;
		}
		
		return result;
	}
	
}
