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
import jp.co.cos_mos.mdm.core.service.domain.entity.SequenceNumberObj;
import jp.co.cos_mos.mdm.core.service.domain.entity.Status;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class SequenceNumberCreateActionTest {

	private Control control;
	private SequenceNumberObj input;
	
	@InjectMocks
	private SequenceNumberCreateAction target = new SequenceNumberCreateActionImpl();
	
	@Mock
	private SequenceNumberMapper sequenceNumberMapper;	
	@Mock
	private SequenceNumberNumberingAction numberingAction;
    
	@Before
    public void setup() {
		control = new Control();
		control.setRequesterName("ut");
		control.setTransactionId(Math.abs(UUID.randomUUID().getLeastSignificantBits()));
		
		MockitoAnnotations.initMocks(this);
    }
    
	@Test
	public void testParform_SUCCESS001() {
		input = new SequenceNumberObj();
		input.setId(null);
		input.setName("SequenceNumberName");

		Long sequenceNumberId = 1L;

		when(numberingAction.getEntityNumberingId(
				SequenceNumber.class.getName())).thenReturn(sequenceNumberId);
		
		when(sequenceNumberMapper.insert(anyObject())).thenReturn(1);
		
		SequenceNumber insertedSequenceNumber = new SequenceNumber();
		insertedSequenceNumber.setId(sequenceNumberId);
		insertedSequenceNumber.setSeq(0L);
		insertedSequenceNumber.setName(input.getName());
		insertedSequenceNumber.setInitialValue(0);
		insertedSequenceNumber.setIncrementValue(1);
		insertedSequenceNumber.setMaxValue(0);
		insertedSequenceNumber.setLastUpdateTs(new Timestamp(System.currentTimeMillis()));

		when(sequenceNumberMapper.select(sequenceNumberId)).thenReturn(insertedSequenceNumber);
		
		SequenceNumberServiceResponse response = target.perform(control, input);

		assertTrue(this.control.equals(response.getControl()));
		assertTrue(response.getResult() != null);
		assertTrue(response.getResult().getStatus() == Status.SUCCESS);
		assertTrue(response.getOutput() != null);
		assertTrue(response.getOutput().getId().equals(String.valueOf(sequenceNumberId)));
		assertTrue(response.getOutput().getSeq().equals(input.getSeq()));
		assertTrue(response.getOutput().getName().equals(input.getName()));
		assertTrue(response.getOutput().getInitialValue().equals("0"));
		assertTrue(response.getOutput().getIncrementValue().equals("1"));
		assertTrue(response.getOutput().getMaxValue().equals("0"));
		assertTrue(response.getOutput().getLastUpdateTs() != null);
		
	}
	
	@Test
	public void testParform_SUCCESS002() {
		input = new SequenceNumberObj();
		input.setId(null);
		input.setName("SequenceNumberName");
		input.setSeq("100");
		input.setInitialValue("1000");
		input.setIncrementValue("1");
		input.setMaxValue("10000");

		Long sequenceNumberId = 1L;

		when(numberingAction.getEntityNumberingId(
				SequenceNumber.class.getName())).thenReturn(sequenceNumberId);
		
		when(sequenceNumberMapper.insert(anyObject())).thenReturn(1);
		
		SequenceNumber insertedSequenceNumber = new SequenceNumber();
		insertedSequenceNumber.setId(sequenceNumberId);
		insertedSequenceNumber.setSeq(100L);
		insertedSequenceNumber.setName(input.getName());
		insertedSequenceNumber.setInitialValue(1000);
		insertedSequenceNumber.setIncrementValue(1);
		insertedSequenceNumber.setMaxValue(10000);
		insertedSequenceNumber.setLastUpdateTs(new Timestamp(System.currentTimeMillis()));

		when(sequenceNumberMapper.select(sequenceNumberId)).thenReturn(insertedSequenceNumber);
		
		SequenceNumberServiceResponse response = target.perform(control, input);

		assertTrue(this.control.equals(response.getControl()));
		assertTrue(response.getResult() != null);
		assertTrue(response.getResult().getStatus() == Status.SUCCESS);
		assertTrue(response.getOutput() != null);
		assertTrue(response.getOutput().getId().equals(
				String.valueOf(sequenceNumberId)));
		assertTrue(response.getOutput().getSeq().equals(input.getSeq()));
		assertTrue(response.getOutput().getName().equals(input.getName()));
		assertTrue(response.getOutput().getInitialValue().equals(input.getInitialValue()));
		assertTrue(response.getOutput().getIncrementValue().equals(input.getIncrementValue()));
		assertTrue(response.getOutput().getMaxValue().equals(input.getMaxValue()));
		assertTrue(response.getOutput().getLastUpdateTs() != null);
		
	}
}
