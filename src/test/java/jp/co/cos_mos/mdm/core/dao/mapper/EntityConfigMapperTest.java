package jp.co.cos_mos.mdm.core.dao.mapper;

import static org.junit.Assert.*;

import java.util.UUID;

import jp.co.cos_mos.mdm.core.dao.entity.EntityConfig;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:serviceContext.xml")
public class EntityConfigMapperTest {

	@Autowired
	EntityConfigMapper mapper;
	
	EntityConfig testEntityConfig;
	
	@Before
	public void setup() {
		// 準備
		testEntityConfig = new EntityConfig();
		testEntityConfig.setClassName(this.getClass().getName());
		testEntityConfig.setSequenceId(0L);
		testEntityConfig.setInactiveTs(null);
		testEntityConfig.setLastUpdateTxId(Math.abs(UUID.randomUUID().getLeastSignificantBits()));
		testEntityConfig.setLastUpdateUser("ut");
		mapper.insert(testEntityConfig);
	}
	
	@After
	public void doAfter() {
		// 後始末
		testEntityConfig = mapper.select(this.getClass().getName());
		mapper.delete(testEntityConfig);
	}
	
	
	/**
	 * Mpperメソッドの正常動作検証ケース
	 * <ul>
	 * <li>select
	 * </ul>
	 */
	@Test
	public void testCase001() {
		EntityConfig result = 
				mapper.select(this.getClass().getName());
		assertTrue(result.getClassName().equals(testEntityConfig.getClassName()));
		assertTrue(result.getSequenceId().equals(testEntityConfig.getSequenceId()));
	}

}
