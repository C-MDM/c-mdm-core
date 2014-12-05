package jp.co.cos_mos.mdm.core.dao.mapper;

import jp.co.cos_mos.mdm.core.dao.entity.SequenceIdentifier;

public interface SequenceIdentifierMapper {

	public SequenceIdentifier select(String classname);
	
	public int insert(SequenceIdentifier seqident);
	
	public int update(SequenceIdentifier seqident);
	
	public int delete(String classname);
}
