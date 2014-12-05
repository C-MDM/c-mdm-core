package jp.co.cos_mos.mdm.core.service;

import static org.junit.Assert.*;
import jp.co.cos_mos.mdm.core.domain.SequenceNumberServiceRequest;
import jp.co.cos_mos.mdm.core.domain.SequenceNumberServiceResponse;
import jp.co.cos_mos.mdm.core.service.domain.entity.SequenceNumberObj;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:serviceContext.xml")
public class SequenceNumberServiceTest {

	@Autowired
	private SequenceNumberService service;
	
	@Test
	public void testCreate001() {
		
		SequenceNumberObj inputObj = new SequenceNumberObj();
		inputObj.setSeq("0");
		inputObj.setName("test");
		inputObj.setInitialValue("0");
		inputObj.setIncrementValue("0");
		inputObj.setMaxValue("100");
		
		SequenceNumberServiceRequest request = 
				new SequenceNumberServiceRequest();
		request.setInput(inputObj);
		
		SequenceNumberServiceResponse response = 
				service.createCategory(request);
		
		assertNotNull(response.getOutput().getId());
		assertNotNull(response.getOutput().getSeq());
		assertEquals(
				request.getInput().getName(), 
				response.getOutput().getName());
	}

	@Test
	public void testNumbering001() {
		SequenceNumberObj inputObj = new SequenceNumberObj();
		inputObj.setId("0");
		SequenceNumberServiceRequest request = 
				new SequenceNumberServiceRequest();
		request.setInput(inputObj);
		
		SequenceNumberServiceResponse response = 
				service.numbering(request);

		assertNotNull(response.getOutput().getSeq());
	}
	
	@Test
	public void testNumbering002() {
		SequenceNumberObj inputObj = new SequenceNumberObj();
		inputObj.setId("0");
		SequenceNumberServiceRequest request = 
				new SequenceNumberServiceRequest();
		request.setInput(inputObj);
		
		SequenceNumberServiceResponse response1 = 
				service.numbering(request);
		assertNotNull(response1.getOutput().getSeq());
		Long first = Long.valueOf(response1.getOutput().getSeq());
		
		SequenceNumberServiceResponse response2 = 
				service.numbering(request);
		Long second = Long.valueOf(response2.getOutput().getSeq());

		assertTrue(
				Long.valueOf(second) == (first + 1));
		
	}
}
