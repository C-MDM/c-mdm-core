package jp.co.cos_mos.mdm.core.service.action;

import jp.co.cos_mos.mdm.core.dao.entity.SequenceNumber;
import jp.co.cos_mos.mdm.core.dao.mapper.SequenceNumberMapper;
import jp.co.cos_mos.mdm.core.service.domain.SequenceNumberServiceResponse;
import jp.co.cos_mos.mdm.core.service.domain.entity.Control;
import jp.co.cos_mos.mdm.core.service.domain.entity.Result;
import jp.co.cos_mos.mdm.core.service.domain.entity.SequenceNumberObj;
import jp.co.cos_mos.mdm.core.service.domain.entity.Status;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * シーケンスナンバー登録アクション実装クラス。
 * 
 * @author Cosmos Inc.
 */
public class SequenceNumberCreateActionImpl implements
		SequenceNumberCreateAction {

	@Autowired
	private SequenceNumberMapper sequenceNumberMapper;
	@Autowired
	private SequenceNumberNumberingAction numberingAction;
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public SequenceNumberServiceResponse perform(Control control,
			SequenceNumberObj input) {
		
		SequenceNumberServiceResponse response = 
				new SequenceNumberServiceResponse();
		response.setControl(control);

		// 省略値設定
		if (StringUtils.isEmpty(input.getSeq())) {
			input.setSeq("0");
		}
		if (StringUtils.isEmpty(input.getInitialValue())) {
			input.setInitialValue("0");
		}
		if (StringUtils.isEmpty(input.getIncrementValue())) {
			input.setIncrementValue("1");
		}
		if (StringUtils.isEmpty(input.getMaxValue())) {
			input.setMaxValue("0");
		}
		
		Result result = validate(input);
		if (result.getStatus() != Status.SUCCESS) {
			response.setResult(result);
			return response;
		}
		
		// 新しいシーケンスレコードIDを採番する
		Long sequenceNumberId = 
				numberingAction.getEntityNumberingId(SequenceNumber.class.getName());
		
		// 挿入レコードを生成
		SequenceNumber newSeqNumber = new SequenceNumber();
		newSeqNumber.setId(sequenceNumberId);
		newSeqNumber.setSeq(
				Long.valueOf(input.getSeq()));
		newSeqNumber.setName(input.getName());
		newSeqNumber.setInitialValue(
				Integer.valueOf(input.getInitialValue()));
		newSeqNumber.setIncrementValue(
				Integer.valueOf(input.getIncrementValue()));
		newSeqNumber.setMaxValue(
				Integer.valueOf(input.getMaxValue()));
		
		// 新しい採番レコードを挿入
		int count = sequenceNumberMapper.insert(newSeqNumber);
		if (count != 1) {
			result.setStatus(Status.FATAL);
			return response;
		}

		// 戻り値を設定
		SequenceNumberObj outputObj = new SequenceNumberObj();
		SequenceNumber inserted =
				sequenceNumberMapper.select(sequenceNumberId);
		
		outputObj.setId(
				String.valueOf(inserted.getId()));
		outputObj.setName(inserted.getName());
		outputObj.setSeq(
				String.valueOf(inserted.getSeq()));
		outputObj.setName(
				String.valueOf(inserted.getName()));
		outputObj.setInitialValue(
				String.valueOf(inserted.getInitialValue()));
		outputObj.setIncrementValue(
				String.valueOf(inserted.getIncrementValue()));
		outputObj.setMaxValue(
				String.valueOf(inserted.getMaxValue()));
		outputObj.setLastUpdateTs(
				inserted.getLastUpdateTs().toString());
		
		response.setResult(result);
		response.setOutput(outputObj);
		
		return response;
	}
	

	/**
	 * 入力データの妥当性をチェックします。
	 * <p>
	 * 次の妥当性をチェックしエラーの場合、ステータスStatus#BAD_REQUEST_VALUEを返却します
	 * <ul>
	 * <li>seq の数値チェック
	 * <li>name の必須チェック
	 * <li>initialValue の数値チェック
	 * <li>IncrementValue の数値チェック
	 * <li>IncrementValue の数値範囲（０より大きい）チェック
	 * <li>MaxValueの数値チェック
	 * <li>MaxValueの数値妥当性チェック
	 * </ul>
	 * @param input 入力データ
	 * @return 妥当性チェック結果
	 */
	protected Result validate(SequenceNumberObj input) {
		Result result = new Result();
		
		if (!StringUtils.isNumeric(input.getSeq())) {
			result.setStatus(Status.BAD_REQUEST_VALUE);
			return result;
		}

		if (StringUtils.isEmpty(input.getName())) {
			result.setStatus(Status.BAD_REQUEST_VALUE);
			return result;
		}
		
		if (!StringUtils.isNumeric(input.getInitialValue())) {
			result.setStatus(Status.BAD_REQUEST_VALUE);
			return result;
		}
		
		if (!StringUtils.isNumeric(input.getIncrementValue())) {
			result.setStatus(Status.BAD_REQUEST_VALUE);
			return result;
		}
		
		if (Integer.valueOf(input.getIncrementValue()) <=0) {
			result.setStatus(Status.BAD_REQUEST_VALUE);
			return result;
		}
		
		if (!StringUtils.isNumeric(input.getMaxValue())) {
			result.setStatus(Status.BAD_REQUEST_VALUE);
			return result;
		}
		
		Long seqId = Long.valueOf(input.getSeq());
		int maxValue = Integer.valueOf(input.getMaxValue());
		int initialValue = Integer.valueOf(input.getInitialValue());
		int incrementValue = Integer.valueOf(input.getIncrementValue());
		
		if (maxValue > 0) {
			if (seqId >= maxValue) {
				result.setStatus(Status.BAD_REQUEST_VALUE);
				return result;
			}
			if (initialValue >= maxValue) {
				result.setStatus(Status.BAD_REQUEST_VALUE);
				return result;
			}
			if ((initialValue + incrementValue) >= maxValue) {
				result.setStatus(Status.BAD_REQUEST_VALUE);
				return result;
			}
		}
		
		return result;
	}

}
