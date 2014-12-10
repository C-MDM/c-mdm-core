package jp.co.cos_mos.mdm.core.service.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import jp.co.cos_mos.mdm.core.service.domain.entity.Control;
import jp.co.cos_mos.mdm.core.service.domain.entity.SequenceNumberCriteriaObj;
import jp.co.cos_mos.mdm.core.service.domain.entity.SequenceNumberObj;

/**
 * シーケンスナンバーサービス要求データクラス。
 * 
 * @author Cosmos Inc.
 */
@XmlRootElement
public class SequenceNumberServiceRequest implements Serializable {

	/** コントロール **/
	private Control control;
	
	/** 入力データ **/
	private SequenceNumberObj input;
	
	/** 入力パラメータ **/
	private SequenceNumberCriteriaObj criteria;

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
	 * @return input
	 */
	public SequenceNumberObj getInput() {
		return input;
	}

	/**
	 * @param input セットする input
	 */
	public void setInput(SequenceNumberObj input) {
		this.input = input;
	}

	/**
	 * @return criteria
	 */
	public SequenceNumberCriteriaObj getCriteria() {
		return criteria;
	}

	/**
	 * @param criteria セットする criteria
	 */
	public void setCriteria(SequenceNumberCriteriaObj criteria) {
		this.criteria = criteria;
	}

}
