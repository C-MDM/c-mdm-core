package jp.co.cos_mos.mdm.core.service.action;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.sql.Timestamp;
import java.util.UUID;

import jp.co.cos_mos.mdm.core.dao.entity.EntityConfig;
import jp.co.cos_mos.mdm.core.dao.entity.SequenceNumber;
import jp.co.cos_mos.mdm.core.dao.mapper.EntityConfigMapper;
import jp.co.cos_mos.mdm.core.dao.mapper.SequenceNumberMapper;
import jp.co.cos_mos.mdm.core.service.domain.SequenceNumberServiceResponse;
import jp.co.cos_mos.mdm.core.service.domain.entity.Control;
import jp.co.cos_mos.mdm.core.service.domain.entity.Result;
import jp.co.cos_mos.mdm.core.service.domain.entity.SequenceNumberCriteriaObj;
import jp.co.cos_mos.mdm.core.service.domain.entity.Status;
import jp.co.cos_mos.mdm.core.service.exception.GetEntityNumberingIdException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class SequenceNumberNumberingActionTest {

	private Control control;
	private SequenceNumberCriteriaObj criteria;
	
	@InjectMocks
	private SequenceNumberNumberingAction target = 
		new SequenceNumberNumberingActionImpl();
	
	@Mock
	private SequenceNumberMapper sequenceNumberMapper;	
    @Mock
	private EntityConfigMapper entityConfigMapper;
	
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

		Timestamp beforeTs = new Timestamp(System.currentTimeMillis());

		SequenceNumber numberingSequenceNumber = new SequenceNumber();
		numberingSequenceNumber.setId(sequenceNumberId);
		numberingSequenceNumber.setSeq(5L);
		numberingSequenceNumber.setName("resetSeqNumber");
		numberingSequenceNumber.setInitialValue(0);
		numberingSequenceNumber.setIncrementValue(1);
		numberingSequenceNumber.setMaxValue(0);
		numberingSequenceNumber.setLastUpdateTs(beforeTs);
		
		when(sequenceNumberMapper.select(sequenceNumberId)).thenReturn(numberingSequenceNumber);
		when(sequenceNumberMapper.update(any(SequenceNumber.class))).thenReturn(1);
		
		Timestamp afterTs = new Timestamp(System.currentTimeMillis());

		SequenceNumber updatedSequenceNumber = new SequenceNumber();
		updatedSequenceNumber.setId(sequenceNumberId);
		updatedSequenceNumber.setSeq(
				numberingSequenceNumber.getSeq() + numberingSequenceNumber.getIncrementValue());
		updatedSequenceNumber.setName(numberingSequenceNumber.getName());
		updatedSequenceNumber.setInitialValue(
				numberingSequenceNumber.getInitialValue());
		updatedSequenceNumber.setIncrementValue(
				numberingSequenceNumber.getIncrementValue());
		updatedSequenceNumber.setMaxValue(
				numberingSequenceNumber.getInitialValue());
		updatedSequenceNumber.setLastUpdateTs(afterTs);
		when(sequenceNumberMapper.select(sequenceNumberId)).thenReturn(updatedSequenceNumber);

		SequenceNumberServiceResponse response = target.perform(control, criteria);

		assertTrue(this.control.equals(response.getControl()));
		assertTrue(response.getResult() != null);
		assertTrue(response.getResult().getStatus() == Status.SUCCESS);
		assertTrue(response.getOutput() != null);
		assertTrue(response.getOutput().getId().equals(
				String.valueOf(numberingSequenceNumber.getId())));
		assertFalse(response.getOutput().getSeq().equals(
				String.valueOf(numberingSequenceNumber.getSeq())));
		assertTrue(response.getOutput().getName().equals(
				numberingSequenceNumber.getName()));
		assertTrue(response.getOutput().getInitialValue().equals(
				String.valueOf(numberingSequenceNumber.getInitialValue())));
		assertTrue(response.getOutput().getIncrementValue().equals(
				String.valueOf(numberingSequenceNumber.getIncrementValue())));
		assertTrue(response.getOutput().getMaxValue().equals(
				String.valueOf(numberingSequenceNumber.getMaxValue())));
		assertFalse(response.getOutput().getLastUpdateTs().equals(beforeTs));
		
	}
	
	/**
	 * 最大値超過例外
	 */
	@Test
	public void testParform_EXCEPTION_UPPER_LIMIT_VALUE001() {
		criteria = new SequenceNumberCriteriaObj();
		criteria.setId("1");
		Long sequenceNumberId = 1L;

		Timestamp beforeTs = new Timestamp(System.currentTimeMillis());

		SequenceNumber numberingSequenceNumber = new SequenceNumber();
		numberingSequenceNumber.setId(sequenceNumberId);
		numberingSequenceNumber.setSeq(5L);
		numberingSequenceNumber.setName("resetSeqNumber");
		numberingSequenceNumber.setInitialValue(0);
		numberingSequenceNumber.setIncrementValue(1);
		numberingSequenceNumber.setMaxValue(5);
		numberingSequenceNumber.setLastUpdateTs(beforeTs);
		
		when(sequenceNumberMapper.select(sequenceNumberId)).thenReturn(numberingSequenceNumber);

		SequenceNumberServiceResponse response = new SequenceNumberServiceResponse();
		
		response = target.perform(control, criteria);

		assertTrue(this.control.equals(response.getControl()));
		assertTrue(response.getResult() != null);
		assertTrue(response.getResult().getStatus() == Status.EXCEPTION_UPPER_LIMIT_VALUE);
		assertTrue(response.getOutput() == null);
	}

	/**
	 * 衝突例外
	 */
	@Test
	public void testParform_EXCEPTION_CONFLICT001() {
		criteria = new SequenceNumberCriteriaObj();
		criteria.setId("1");
		Long sequenceNumberId = 1L;

		Timestamp beforeTs = new Timestamp(System.currentTimeMillis());

		SequenceNumber numberingSequenceNumber = new SequenceNumber();
		numberingSequenceNumber.setId(sequenceNumberId);
		numberingSequenceNumber.setSeq(5L);
		numberingSequenceNumber.setName("resetSeqNumber");
		numberingSequenceNumber.setInitialValue(0);
		numberingSequenceNumber.setIncrementValue(1);
		numberingSequenceNumber.setMaxValue(6);
		numberingSequenceNumber.setLastUpdateTs(beforeTs);
		
		when(sequenceNumberMapper.select(sequenceNumberId)).thenReturn(numberingSequenceNumber);
		when(sequenceNumberMapper.update(any(SequenceNumber.class))).thenReturn(0);

		SequenceNumberServiceResponse response = target.perform(control, criteria);

		assertTrue(this.control.equals(response.getControl()));
		assertTrue(response.getResult() != null);
		assertTrue(response.getResult().getStatus() == Status.EXCEPTION_CONFLICT);
		assertTrue(response.getOutput() == null);
	}
	
	/**
	 * id なし
	 */
	@Test
	public void testParform_EXCEPTION_BAD_REQUEST_VALUE001() {
		criteria = new SequenceNumberCriteriaObj();
		criteria.setId("");

		SequenceNumberServiceResponse response = target.perform(control, criteria);

		assertTrue(this.control.equals(response.getControl()));
		assertTrue(response.getResult() != null);
		assertTrue(response.getResult().getStatus() == Status.BAD_REQUEST_VALUE);
		assertTrue(response.getOutput() == null);
	}
	
	
	@Test
	public void testGetEntityNumberingId_SUCCESS001() {
		
		EntityConfig config = new EntityConfig();
		String className = this.getClass().getName();
		Long sequenceNumberId = 10L;
		
		config.setClassName(className);
		config.setSequenceId(sequenceNumberId);
		
		when(entityConfigMapper.select(className)).thenReturn(config);
		
		SequenceNumber numberingSequenceNumber = new SequenceNumber();
		numberingSequenceNumber.setId(sequenceNumberId);
		numberingSequenceNumber.setSeq(5L);
		numberingSequenceNumber.setIncrementValue(1);
		
		when(sequenceNumberMapper.select(sequenceNumberId)).thenReturn(numberingSequenceNumber);
		when(sequenceNumberMapper.update(any(SequenceNumber.class))).thenReturn(1);
		
		SequenceNumber updatedSequenceNumber = new SequenceNumber();
		updatedSequenceNumber.setId(sequenceNumberId);
		updatedSequenceNumber.setSeq(
				numberingSequenceNumber.getSeq() + numberingSequenceNumber.getIncrementValue());
		when(sequenceNumberMapper.select(sequenceNumberId)).thenReturn(updatedSequenceNumber);

		Long getid = 
				target.getEntityNumberingId(this.getClass().getName());

		assertTrue(getid == updatedSequenceNumber.getSeq());
		
	}
	
	/**
	 * EntityConfigなし
	 */
	@Test(expected=GetEntityNumberingIdException.class)
	public void testGetEntityNumberingId_EXCEPTION_GET_ENTITY_NUMBERING_ID001() {
		
		EntityConfig config = new EntityConfig();
		String className = this.getClass().getName();
		Long sequenceNumberId = 10L;
		
		config.setClassName(className);
		config.setSequenceId(sequenceNumberId);
		
		when(entityConfigMapper.select(className)).thenReturn(null);
		
		Long getid = null;
		try {
			getid = 
					target.getEntityNumberingId(this.getClass().getName());
			fail();
		} catch (GetEntityNumberingIdException e) {
			Result result = e.getResult();
			assertTrue(getid == null);
			assertTrue(result != null);
			assertTrue(result.getStatus().equals(Status.EXCEPTION_GET_ENTITY_NUMBERING_ID));
			throw e;
		}

	}
	
	/**
	 * 最大値超過例外
	 */
	@Test(expected=GetEntityNumberingIdException.class)
	public void testGetEntityNumberingId_EXCEPTION_GET_ENTITY_NUMBERING_ID002() {
		
		EntityConfig config = new EntityConfig();
		String className = this.getClass().getName();
		Long sequenceNumberId = 10L;
		
		config.setClassName(className);
		config.setSequenceId(sequenceNumberId);
		
		when(entityConfigMapper.select(className)).thenReturn(config);
		
		SequenceNumber numberingSequenceNumber = new SequenceNumber();
		numberingSequenceNumber.setId(sequenceNumberId);
		numberingSequenceNumber.setSeq(5L);
		numberingSequenceNumber.setIncrementValue(1);
		numberingSequenceNumber.setMaxValue(5);
		
		when(sequenceNumberMapper.select(sequenceNumberId)).thenReturn(numberingSequenceNumber);
		
		Long getid = null;
		try {
			getid = 
					target.getEntityNumberingId(this.getClass().getName());
			fail();
		} catch (GetEntityNumberingIdException e) {
			Result result = e.getResult();
			assertTrue(getid == null);
			assertTrue(result != null);
			assertTrue(result.getStatus().equals(Status.EXCEPTION_GET_ENTITY_NUMBERING_ID));
			throw e;
		}

	}

	/**
	 * 衝突例外
	 */
	@Test(expected=GetEntityNumberingIdException.class)
	public void testGetEntityNumberingId_EXCEPTION_GET_ENTITY_NUMBERING_ID003() {
		
		EntityConfig config = new EntityConfig();
		String className = this.getClass().getName();
		Long sequenceNumberId = 10L;
		
		config.setClassName(className);
		config.setSequenceId(sequenceNumberId);
		
		when(entityConfigMapper.select(className)).thenReturn(config);
		
		SequenceNumber numberingSequenceNumber = new SequenceNumber();
		numberingSequenceNumber.setId(sequenceNumberId);
		numberingSequenceNumber.setSeq(5L);
		numberingSequenceNumber.setIncrementValue(1);
		
		when(sequenceNumberMapper.select(sequenceNumberId)).thenReturn(numberingSequenceNumber);
		when(sequenceNumberMapper.update(any(SequenceNumber.class))).thenReturn(0);
		
		Long getid = null;
		try {
			getid = 
					target.getEntityNumberingId(this.getClass().getName());
			fail();
		} catch (GetEntityNumberingIdException e) {
			Result result = e.getResult();
			assertTrue(getid == null);
			assertTrue(result != null);
			assertTrue(result.getStatus().equals(Status.EXCEPTION_GET_ENTITY_NUMBERING_ID));
			throw e;
		}

	}
}
