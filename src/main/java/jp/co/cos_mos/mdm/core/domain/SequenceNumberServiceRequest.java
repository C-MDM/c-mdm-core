package jp.co.cos_mos.mdm.core.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import jp.co.cos_mos.mdm.core.service.domain.entity.Control;
import jp.co.cos_mos.mdm.core.service.domain.entity.SequenceNumberCriteriaObj;
import jp.co.cos_mos.mdm.core.service.domain.entity.SequenceNumberObj;

@XmlRootElement
public class SequenceNumberServiceRequest implements Serializable {

	private Control control;
	private SequenceNumberObj input;
	private SequenceNumberCriteriaObj criteria;
	
	public Control getControl() {
		return control;
	}
	public void setControl(Control control) {
		this.control = control;
	}
	public SequenceNumberObj getInput() {
		return input;
	}
	public void setInput(SequenceNumberObj input) {
		this.input = input;
	}
	public SequenceNumberCriteriaObj getCriteria() {
		return criteria;
	}
	public void setCriteria(SequenceNumberCriteriaObj criteria) {
		this.criteria = criteria;
	}

}
