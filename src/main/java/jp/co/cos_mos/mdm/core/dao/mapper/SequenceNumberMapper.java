package jp.co.cos_mos.mdm.core.dao.mapper;

import jp.co.cos_mos.mdm.core.dao.entity.SequenceNumber;

public interface SequenceNumberMapper {
	
	public SequenceNumber select(Long id);
	
	public int insert(SequenceNumber sequence);
	
	public int update(SequenceNumber sequence);
	
	public int delete(Long id);
	
	public int updateNumbering(SequenceNumber sequence);
	
}
