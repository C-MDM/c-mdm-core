package jp.co.cos_mos.mdm.core.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import jp.co.cos_mos.mdm.core.service.domain.entity.Control;
import jp.co.cos_mos.mdm.core.service.domain.entity.Result;
import jp.co.cos_mos.mdm.core.service.domain.entity.SequenceNumberObj;

@XmlRootElement
public class SequenceNumberServiceResponse implements Serializable {

	private Control control;
	private Result result;
	private SequenceNumberObj output;
	
	public Control getControl() {
		return control;
	}
	public void setControl(Control control) {
		this.control = control;
	}
	public Result getResult() {
		return result;
	}
	public void setResult(Result result) {
		this.result = result;
	}
	public SequenceNumberObj getOutput() {
		return output;
	}
	public void setOutput(SequenceNumberObj output) {
		this.output = output;
	}

}
