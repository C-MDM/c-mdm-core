package jp.co.cos_mos.mdm.core.service.action;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.sql.Timestamp;
import java.util.UUID;

import jp.co.cos_mos.mdm.core.dao.entity.SequenceNumber;
import jp.co.cos_mos.mdm.core.dao.mapper.SequenceNumberMapper;
import jp.co.cos_mos.mdm.core.service.domain.SequenceNumberServiceResponse;
import jp.co.cos_mos.mdm.core.service.domain.entity.Control;
import jp.co.cos_mos.mdm.core.service.domain.entity.SequenceNumberCriteriaObj;
import jp.co.cos_mos.mdm.core.service.domain.entity.Status;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class SequenceNumberResetActionTest {

	private Control control;
	private SequenceNumberCriteriaObj criteria;
	
	@InjectMocks
	private SequenceNumberResetAction target = new SequenceNumberResetActionImpl();
	
	@Mock
	private SequenceNumberMapper sequenceNumberMapper;	
    
	@Before
    public void setup() {
		control = new Control();
		control.setRequesterName("ut");
		control.setTransactionId(Math.abs(UUID.randomUUID().getLeastSignificantBits()));
		
		MockitoAnnotations.initMocks(this);
    }
    
	@Test
	public void testParform_SUCCESS001() {
		criteria = new SequenceNumberCriteriaObj();
		criteria.setId("1");

		Long sequenceNumberId = 1L;

		SequenceNumber resetedSequenceNumber = new SequenceNumber();
		resetedSequenceNumber.setId(sequenceNumberId);
		resetedSequenceNumber.setSeq(1000L);
		resetedSequenceNumber.setName("resetSeqNumber");
		resetedSequenceNumber.setInitialValue(0);
		resetedSequenceNumber.setIncrementValue(1);
		resetedSequenceNumber.setMaxValue(0);
		resetedSequenceNumber.setLastUpdateTs(new Timestamp(System.currentTimeMillis()));

		when(sequenceNumberMapper.select(sequenceNumberId)).thenReturn(resetedSequenceNumber);
		when(sequenceNumberMapper.update(any(SequenceNumber.class))).thenReturn(1);

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		resetedSequenceNumber.setSeq(
				Long.valueOf(resetedSequenceNumber.getInitialValue()));
		resetedSequenceNumber.setLastUpdateTs(timestamp);
		
		when(sequenceNumberMapper.select(sequenceNumberId)).thenReturn(resetedSequenceNumber);
		
		SequenceNumberServiceResponse response = target.perform(control, criteria);

		assertTrue(this.control.equals(response.getControl()));
		assertTrue(response.getResult() != null);
		assertTrue(response.getResult().getStatus() == Status.SUCCESS);
		assertTrue(response.getOutput() != null);
		assertTrue(response.getOutput().getId().equals(
				String.valueOf(sequenceNumberId)));
		assertTrue(response.getOutput().getSeq().equals(
				resetedSequenceNumber.getSeq().toString()));
		assertTrue(response.getOutput().getName().equals(
				resetedSequenceNumber.getName()));
		assertTrue(response.getOutput().getInitialValue().equals(
				String.valueOf(resetedSequenceNumber.getInitialValue())));
		assertTrue(response.getOutput().getIncrementValue().equals(
				String.valueOf(resetedSequenceNumber.getIncrementValue())));
		assertTrue(response.getOutput().getMaxValue().equals(
				String.valueOf(resetedSequenceNumber.getMaxValue())));
		assertTrue(response.getOutput().getLastUpdateTs().equals(timestamp.toString()));
		
	}
	
	/**
	 * id なし
	 */
	@Test
	public void testParform_BAD_REQUEST_VALUE001() {
		criteria = new SequenceNumberCriteriaObj();
		criteria.setId("");

		SequenceNumberServiceResponse response = target.perform(control, criteria);

		assertTrue(this.control.equals(response.getControl()));
		assertTrue(response.getResult() != null);
		assertTrue(response.getResult().getStatus() == Status.BAD_REQUEST_VALUE);
		assertTrue(response.getOutput() == null);
	}
	
	/**
	 * トランザクション衝突例外
	 */
	@Test
	public void testParform_EXCEPTION_CONFLICT001() {
		criteria = new SequenceNumberCriteriaObj();
		criteria.setId("1");

		Long sequenceNumberId = 1L;

		SequenceNumber resetedSequenceNumber = new SequenceNumber();
		resetedSequenceNumber.setId(sequenceNumberId);
		resetedSequenceNumber.setSeq(1000L);
		resetedSequenceNumber.setName("resetSeqNumber");
		resetedSequenceNumber.setInitialValue(0);
		resetedSequenceNumber.setIncrementValue(1);
		resetedSequenceNumber.setMaxValue(0);
		resetedSequenceNumber.setLastUpdateTs(new Timestamp(System.currentTimeMillis()));

		when(sequenceNumberMapper.select(sequenceNumberId)).thenReturn(resetedSequenceNumber);
		when(sequenceNumberMapper.update(any(SequenceNumber.class))).thenReturn(0);

		SequenceNumberServiceResponse response = target.perform(control, criteria);

		assertTrue(this.control.equals(response.getControl()));
		assertTrue(response.getResult() != null);
		assertTrue(response.getResult().getStatus() == Status.EXCEPTION_CONFLICT);
		assertTrue(response.getOutput() == null);
		
	}

}
