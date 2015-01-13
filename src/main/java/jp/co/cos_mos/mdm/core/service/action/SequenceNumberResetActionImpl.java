package jp.co.cos_mos.mdm.core.service.action;

import jp.co.cos_mos.mdm.core.dao.entity.SequenceNumber;
import jp.co.cos_mos.mdm.core.dao.mapper.SequenceNumberMapper;
import jp.co.cos_mos.mdm.core.service.domain.SequenceNumberServiceResponse;
import jp.co.cos_mos.mdm.core.service.domain.entity.Control;
import jp.co.cos_mos.mdm.core.service.domain.entity.Result;
import jp.co.cos_mos.mdm.core.service.domain.entity.SequenceNumberCriteriaObj;
import jp.co.cos_mos.mdm.core.service.domain.entity.SequenceNumberObj;
import jp.co.cos_mos.mdm.core.service.domain.entity.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * シーケンス管理レコード初期値設定アクション実装クラス。
 * 
 * @author Cosmos Inc.
 */
@Service
public class SequenceNumberResetActionImpl implements SequenceNumberResetAction {

	@Autowired
	private SequenceNumberMapper sequenceNumberMapper;
	
	/**
	 * @see jp.co.cos_mos.mdm.core.service.action.SequenceNumberResetAction#perform(Control, SequenceNumberCriteriaObj)
	 */
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public SequenceNumberServiceResponse perform(Control control,
			SequenceNumberCriteriaObj criteria) {

		SequenceNumberServiceResponse response = 
				new SequenceNumberServiceResponse();
		response.setControl(control);

		Result result = validate(criteria);
		if (result.getStatus() != Status.SUCCESS) {
			response.setResult(result);
			return response;
		}
		
		Long sequenceNumberId = Long.parseLong(criteria.getId());
		SequenceNumber reset =
				sequenceNumberMapper.select(sequenceNumberId);
		
		// 初期値に更新する
		reset.setSeq(Long.valueOf(reset.getInitialValue()));
		reset.setLastUpdateTs(reset.getLastUpdateTs());

		int count = sequenceNumberMapper.update(reset);
		if (count == 0) {
			result.setStatus(Status.EXCEPTION_CONFLICT);
			response.setResult(result);
			return response;
		}
		
		// 戻り値を設定
		SequenceNumberObj outputObj = new SequenceNumberObj();
		SequenceNumber resetSeqIdObj =
				sequenceNumberMapper.select(sequenceNumberId);
		
		outputObj.setId(
				String.valueOf(resetSeqIdObj.getId()));
		outputObj.setName(resetSeqIdObj.getName());
		outputObj.setSeq(
				String.valueOf(resetSeqIdObj.getSeq()));
		outputObj.setName(
				String.valueOf(resetSeqIdObj.getName()));
		outputObj.setInitialValue(
				String.valueOf(resetSeqIdObj.getInitialValue()));
		outputObj.setIncrementValue(
				String.valueOf(resetSeqIdObj.getIncrementValue()));
		outputObj.setMaxValue(
				String.valueOf(resetSeqIdObj.getMaxValue()));
		outputObj.setLastUpdateTs(
				String.valueOf(resetSeqIdObj.getLastUpdateTs()));

		response.setResult(result);
		response.setOutput(outputObj);
		
		return response;
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
