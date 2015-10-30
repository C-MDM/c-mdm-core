package jp.co.cos_mos.mdm.core.service.action;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.sql.Timestamp;
import java.util.UUID;

import jp.co.cos_mos.mdm.core.dao.entity.SequenceNumber;
import jp.co.cos_mos.mdm.core.dao.mapper.SequenceNumberMapper;
import jp.co.cos_mos.mdm.core.service.domain.SequenceNumberServiceResponse;
import jp.co.cos_mos.mdm.core.service.domain.entity.Control;
import jp.co.cos_mos.mdm.core.service.domain.entity.SequenceNumberCriteriaObj;
import jp.co.cos_mos.mdm.core.service.domain.entity.SequenceNumberObj;
import jp.co.cos_mos.mdm.core.service.domain.entity.Status;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class SequenceNumberGetActionTest {

	private Control control;
	private SequenceNumberCriteriaObj criteria;
	
	@InjectMocks
	private SequenceNumberGetAction target = new SequenceNumberGetActionImpl();
	
	@Mock
	private SequenceNumberMapper mapper;
	
	@Before
	public void setup() {
		control = new Control();
		control.setRequesterName("ut");
		control.setTransactionId(Math.abs(UUID.randomUUID().getLeastSignificantBits()));
		
		MockitoAnnotations.initMocks(this);
    }
	
	
	@Test
	public void testParform_SUCCESS001() {
		String id = "1";
		String lastUpdateTs = "2014-11-29 18:33:12.123456";
		
		criteria = new SequenceNumberCriteriaObj();
		criteria.setId(id);
		
		SequenceNumber selectedSequenceNumber = new SequenceNumber();
		selectedSequenceNumber.setId(Long.valueOf(id));
		selectedSequenceNumber.setSeq(Long.valueOf(5));
		selectedSequenceNumber.setName("test");
		
		selectedSequenceNumber.setIncrementValue(1);
		selectedSequenceNumber.setInitialValue(0);
		selectedSequenceNumber.setMaxValue(10);
		selectedSequenceNumber.setLastUpdateTs(Timestamp.valueOf(lastUpdateTs));
		
		when(mapper.select(Long.valueOf(id))).thenReturn(selectedSequenceNumber);
		
		SequenceNumberServiceResponse response = target.perform(control, criteria);
		
		assertTrue(this.control.equals(response.getControl()));
		assertTrue(response.getResult() != null);
		assertTrue(response.getResult().getStatus() == Status.SUCCESS);
		assertTrue(response.getOutput() != null);
		assertTrue(response.getOutput() instanceof SequenceNumberObj);
		
		SequenceNumberObj output = response.getOutput();
		
		assertTrue(output.getId().equals(id));
		assertTrue(output.getSeq().equals(selectedSequenceNumber.getSeq().toString()));
		assertTrue(output.getName().equals(selectedSequenceNumber.getName()));
		assertTrue(output.getIncrementValue().equals(
				String.valueOf(selectedSequenceNumber.getIncrementValue())));
		assertTrue(output.getInitialValue().equals(
				String.valueOf(selectedSequenceNumber.getInitialValue())));
		assertTrue(output.getMaxValue().equals(
				String.valueOf(selectedSequenceNumber.getMaxValue())));
		assertTrue(output.getLastUpdateTs().equals(
				selectedSequenceNumber.getLastUpdateTs().toString()));
	
	}
	
	@Test
	public void testParform_BAT_REQUEST_VALUE001() {
		SequenceNumberServiceResponse response = target.perform(control, null);
		assertTrue(response.getResult().getStatus().equals(Status.BAD_REQUEST_VALUE));
	}

	@Test
	public void testParform_BAT_REQUEST_VALUE002() {
		criteria = new SequenceNumberCriteriaObj();
		criteria.setId(null);
		
		SequenceNumberServiceResponse response = target.perform(control, criteria);
		assertTrue(response.getResult().getStatus().equals(Status.BAD_REQUEST_VALUE));
	}

	@Test
	public void testParform_BAT_REQUEST_VALUE003() {
		criteria = new SequenceNumberCriteriaObj();
		criteria.setId("abc001");
		
		SequenceNumberServiceResponse response = target.perform(control, criteria);
		assertTrue(response.getResult().getStatus().equals(Status.BAD_REQUEST_VALUE));
	}
	
	@Test
	public void testParform_DATA_NOT_FOUND001() {
		criteria = new SequenceNumberCriteriaObj();
		String ownerId = "1";
		criteria.setId(ownerId);
		
		SequenceNumber selectedSequenceNumber = new SequenceNumber();
		selectedSequenceNumber.setId(Long.valueOf(ownerId));
		
		when(mapper.select(Long.valueOf(ownerId))).thenReturn(null);
		
		SequenceNumberServiceResponse response = target.perform(control, criteria);
		assertTrue(response.getResult().getStatus().equals(Status.DATA_NOT_FOUND));
	}
	
	
}
