package jp.co.cos_mos.mdm.core.dao.mapper;

import jp.co.cos_mos.mdm.core.dao.entity.SequenceIdentifier;
import jp.co.cos_mos.mdm.core.dao.entity.SequenceNumber;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:serviceContext.xml")
public class SequenceIdentifierMapperTest {

	@Autowired
	SequenceIdentifierMapper mapper;
	
	@Test
	public void testSelect001() {
		
		System.out.println(SequenceNumber.class.getName());
		SequenceIdentifier result = 
				mapper.select(SequenceNumber.class.getName());
		System.out.println(result.getClassName());
		
	}

}
