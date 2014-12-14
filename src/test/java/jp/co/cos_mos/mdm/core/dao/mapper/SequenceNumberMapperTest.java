package jp.co.cos_mos.mdm.core.dao.mapper;

import static org.junit.Assert.*;

import java.util.UUID;

import jp.co.cos_mos.mdm.core.dao.entity.SequenceNumber;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:serviceContext.xml")
public class SequenceNumberMapperTest {

	@Autowired
	SequenceNumberMapper sequenceNumerMapper;
	
	SequenceNumber testSequenceNumber1;
	
	@Before
	public void setup() {
		// 準備
		testSequenceNumber1 = new SequenceNumber();
		testSequenceNumber1.setId(Math.abs(UUID.randomUUID().getLeastSignificantBits()));
		testSequenceNumber1.setName("sequence1");
		testSequenceNumber1.setSeq(0L);
		testSequenceNumber1.setInitialValue(0);
		testSequenceNumber1.setIncrementValue(1);
		testSequenceNumber1.setMaxValue(10);
		sequenceNumerMapper.insert(testSequenceNumber1);

	}
	
	@After
	public void doAfter() {
		// 後始末
		sequenceNumerMapper.delete(testSequenceNumber1);
	}
	
	/**
	 * Mpperメソッドの正常動作検証ケース
	 * <ul>
	 * <li>select
	 * <li>update
	 * <li>updateNumbering
	 * </ul>
	 */
	@Test
	public void testCase001() {
		
		// testSequenceNumber1のidで取得できること。異なるidで取得できないこと。
		assertTrue(sequenceNumerMapper.select(testSequenceNumber1.getId() + 1) == null);

		SequenceNumber selectSequence = sequenceNumerMapper.select(testSequenceNumber1.getId());
		assertTrue(selectSequence != null);
		assertTrue(selectSequence.getId().equals(testSequenceNumber1.getId()));
		assertTrue(selectSequence.getName().equals(testSequenceNumber1.getName()));
		assertTrue(selectSequence.getSeq() == testSequenceNumber1.getSeq());
		assertTrue(selectSequence.getInitialValue() == testSequenceNumber1.getInitialValue());
		assertTrue(selectSequence.getIncrementValue() == testSequenceNumber1.getIncrementValue());
		assertTrue(selectSequence.getMaxValue() == testSequenceNumber1.getMaxValue());
		assertTrue(selectSequence.getLastUpdateTs() != null);
		
		// testSequenceNumber1はlastUpdateTsが入力されていないためUpdateは失敗すること。
		assertTrue(testSequenceNumber1.getLastUpdateTs() == null);
		assertTrue(sequenceNumerMapper.update(testSequenceNumber1) == 0);
		
		// selectSequenceはlastUpdateTsが入力されているためUpdateは成功すること。
		selectSequence.setName("UpdatedSequence");
		selectSequence.setSeq(100L);
		selectSequence.setInitialValue(10);
		selectSequence.setIncrementValue(100);
		selectSequence.setMaxValue(10000);
		assertTrue(sequenceNumerMapper.update(selectSequence) == 1);
		
		// 設定した値が更新されていること。
		SequenceNumber updatedSequence = sequenceNumerMapper.select(testSequenceNumber1.getId());
		assertTrue(updatedSequence != null);
		assertTrue(updatedSequence.getId().equals(selectSequence.getId()));
		assertTrue(updatedSequence.getName().equals(selectSequence.getName()));
		assertTrue(updatedSequence.getSeq() == selectSequence.getSeq());
		assertTrue(updatedSequence.getInitialValue() == selectSequence.getInitialValue());
		assertTrue(updatedSequence.getIncrementValue() == selectSequence.getIncrementValue());
		assertTrue(updatedSequence.getMaxValue() == selectSequence.getMaxValue());
		assertTrue(updatedSequence.getLastUpdateTs() != null);
		assertFalse(updatedSequence.getLastUpdateTs().equals(selectSequence.getLastUpdateTs()));
		
	}
}
