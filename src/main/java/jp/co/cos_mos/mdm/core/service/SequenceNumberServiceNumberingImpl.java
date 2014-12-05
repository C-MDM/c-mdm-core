package jp.co.cos_mos.mdm.core.service;

import java.sql.Timestamp;

import jp.co.cos_mos.mdm.core.dao.entity.SequenceNumber;
import jp.co.cos_mos.mdm.core.dao.mapper.SequenceNumberMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Deprecated
public class SequenceNumberServiceNumberingImpl implements
		SequenceNumberServiceNumbering {

	@Autowired
	private SequenceNumberMapper sequenceNumberMapper;
	
	@Deprecated
	public Long perform(Long seqid) {
		SequenceNumber numbering = sequenceNumberMapper.select(seqid);
		numbering.setCurrentTs(new Timestamp(System.currentTimeMillis()));
		sequenceNumberMapper.updateNumbering(numbering);
		
		return numbering.getSeq();

	}

}
