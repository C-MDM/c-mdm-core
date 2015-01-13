package jp.co.cos_mos.mdm.core.service.action;

import jp.co.cos_mos.mdm.core.dao.entity.EntityConfig;
import jp.co.cos_mos.mdm.core.dao.entity.SequenceNumber;
import jp.co.cos_mos.mdm.core.dao.mapper.EntityConfigMapper;
import jp.co.cos_mos.mdm.core.dao.mapper.SequenceNumberMapper;
import jp.co.cos_mos.mdm.core.service.domain.SequenceNumberServiceResponse;
import jp.co.cos_mos.mdm.core.service.domain.entity.Control;
import jp.co.cos_mos.mdm.core.service.domain.entity.Message;
import jp.co.cos_mos.mdm.core.service.domain.entity.Result;
import jp.co.cos_mos.mdm.core.service.domain.entity.SequenceNumberCriteriaObj;
import jp.co.cos_mos.mdm.core.service.domain.entity.SequenceNumberObj;
import jp.co.cos_mos.mdm.core.service.domain.entity.Status;
import jp.co.cos_mos.mdm.core.service.exception.ConflictRequestException;
import jp.co.cos_mos.mdm.core.service.exception.GetEntityNumberingIdException;
import jp.co.cos_mos.mdm.core.service.exception.UpperLimitValueException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * シーケンス番号採番アクション実装クラス。
 * 
 * @author Cosmos Inc.
 */
@Service
public class SequenceNumberNumberingActionImpl implements SequenceNumberNumberingAction  {

	@Autowired
	private SequenceNumberMapper sequenceNumberMapper;
	@Autowired
	private EntityConfigMapper entityConfigMapper;
	
	/* (非 Javadoc)
	 * @see jp.co.cos_mos.mdm.core.service.action.SequenceNumberNumberingAction#perform(jp.co.cos_mos.mdm.core.service.domain.entity.Control, jp.co.cos_mos.mdm.core.service.domain.entity.SequenceNumberCriteriaObj)
	 */
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public SequenceNumberServiceResponse perform(Control control, SequenceNumberCriteriaObj criteria) {
		SequenceNumberServiceResponse response = 
				new SequenceNumberServiceResponse();
		response.setControl(control);
		
		Result result = validate(criteria);
		if (result.getStatus() != Status.SUCCESS) {
			response.setResult(result);
			return response;
		}

		// 新しいシーケンスレコードIDを採番する
		Long sequenceNumberId = Long.parseLong(criteria.getId());
		
		SequenceNumber updatedSequenceNumber;
		try {
			updatedSequenceNumber = numbering(sequenceNumberId);
		
		} catch (UpperLimitValueException e) {
			result.setStatus(Status.EXCEPTION_UPPER_LIMIT_VALUE);
			response.setResult(result);
			return response;
		} catch (ConflictRequestException e) {
			result.setStatus(Status.EXCEPTION_CONFLICT);
			response.setResult(result);
			return response;
		}
		
		// 戻り値を設定
		SequenceNumberObj outputObj = new SequenceNumberObj();
		outputObj.setId(
				String.valueOf(updatedSequenceNumber.getId()));
		outputObj.setName(updatedSequenceNumber.getName());
		outputObj.setSeq(
				String.valueOf(updatedSequenceNumber.getSeq()));
		outputObj.setName(
				String.valueOf(updatedSequenceNumber.getName()));
		outputObj.setInitialValue(
				String.valueOf(updatedSequenceNumber.getInitialValue()));
		outputObj.setIncrementValue(
				String.valueOf(updatedSequenceNumber.getIncrementValue()));
		outputObj.setMaxValue(
				String.valueOf(updatedSequenceNumber.getMaxValue()));
		outputObj.setLastUpdateTs(
				String.valueOf(updatedSequenceNumber.getLastUpdateTs()));

		response.setResult(result);
		response.setOutput(outputObj);
		
		return response;
	}
	
	/* (非 Javadoc)
	 * @see jp.co.cos_mos.mdm.core.service.action.SequenceNumberNumberingAction#getEntityNumberingSeqId(java.lang.String)
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class)
	public Long getEntityNumberingId(String classname) {
		Result result = new Result();
		
		EntityConfig config = 
				entityConfigMapper.select(classname);
		
		if (config == null) {
			Message message = new Message();
			message.setMessage(classname + " not found fail.");

			result.setStatus(Status.EXCEPTION_GET_ENTITY_NUMBERING_ID);
			result.setMessage(message);
			throw new GetEntityNumberingIdException(result);
		}
		
		SequenceNumber updatedSequenceNumber;
		try {
			updatedSequenceNumber = numbering(config.getSequenceId());

		} catch (UpperLimitValueException e) {
			Message message = new Message();
			message.setMessage(classname + " upper limit fail.");

			result.setStatus(Status.EXCEPTION_GET_ENTITY_NUMBERING_ID);
			result.setMessage(message);
			throw new GetEntityNumberingIdException(result);

		} catch (ConflictRequestException e) {
			Message message = new Message();
			message.setMessage(classname + " updateNumbering conflict fail.");

			result.setStatus(Status.EXCEPTION_GET_ENTITY_NUMBERING_ID);
			result.setMessage(message);
			throw new GetEntityNumberingIdException(result);
		}
		
		return updatedSequenceNumber.getSeq();
	}
	
	/**
	 * シーケンス番号を採番します。
	 * <p>
	 * 採番（更新）に失敗した場合 null を返却します。
	 * 
	 * @param id sequenceNumberId
	 * @return 採番済 SequenceNumber オブジェクト
	 * 
	 * @see UpperLimitValueException 採番値が上限に達成した場合にスロー
	 * @see ConflictRequestException 採番値の更新で衝突が発生した場合にスロー
	 */
	synchronized protected SequenceNumber numbering(Long id) {
		
		SequenceNumber updateSequenceNumber = sequenceNumberMapper.select(id);
		if (updateSequenceNumber == null) {
			// TODO:selectの結果なければnull
			System.err.println();
		}

		// 採番
		updateSequenceNumber.setSeq(
				updateSequenceNumber.getSeq() + updateSequenceNumber.getIncrementValue());

		if (updateSequenceNumber.getMaxValue() > 0) {
			if (updateSequenceNumber.getSeq() > updateSequenceNumber.getMaxValue()) {
				throw new UpperLimitValueException();
			}
		}

		int count = sequenceNumberMapper.update(updateSequenceNumber);
		if (count == 0) {
			throw new ConflictRequestException();
		}

		return sequenceNumberMapper.select(id);
	}
	
	/**
	 * 入力パラメータの妥当性をチェックします。
	 * <p>
	 * 次の妥当性をチェックしエラーの場合、ステータスStatus#BAD_REQUEST_VALUEを返却します
	 * <ul>
	 * <li>id の必須チェック
	 * </ul>
	 * @param criteria 入力パラメータ
	 * @return 妥当性チェック結果
	 */
	protected Result validate(SequenceNumberCriteriaObj criteria) {
		Result result = new Result();

		if (StringUtils.isEmpty(criteria.getId())) {
			result.setStatus(Status.BAD_REQUEST_VALUE);
			return result;
		}
		
		return result;
	}
	
}
