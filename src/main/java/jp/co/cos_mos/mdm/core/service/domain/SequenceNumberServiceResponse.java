package jp.co.cos_mos.mdm.core.service.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import jp.co.cos_mos.mdm.core.service.domain.entity.Control;
import jp.co.cos_mos.mdm.core.service.domain.entity.Result;
import jp.co.cos_mos.mdm.core.service.domain.entity.SequenceNumberObj;

/**
 * シーケンスナンバーサービス応答データクラス。
 * 
 * @author Cosmos Inc.
 */
@XmlRootElement
public class SequenceNumberServiceResponse implements Serializable {

	/** コントロール **/
	private Control control;
	
	/** 実行結果 **/
	private Result result;

	/** 結果データ **/
	private SequenceNumberObj output;

	/**
	 * @return control
	 */
	public Control getControl() {
		return control;
	}

	/**
	 * @param control セットする control
	 */
	public void setControl(Control control) {
		this.control = control;
	}

	/**
	 * @return result
	 */
	public Result getResult() {
		return result;
	}

	/**
	 * @param result セットする result
	 */
	public void setResult(Result result) {
		this.result = result;
	}

	/**
	 * @return output
	 */
	public SequenceNumberObj getOutput() {
		return output;
	}

	/**
	 * @param output セットする output
	 */
	public void setOutput(SequenceNumberObj output) {
		this.output = output;
	}

}
