package jp.co.cos_mos.mdm.core.service.domain.entity;

import java.io.Serializable;

public enum Status implements Serializable {
	SUCCESS,
	FATAL,
	DATA_NOT_FOUND,
	BAD_REQUEST_VALUE,
	EXCEPTION_CONFLICT,
	EXCEPTION_GET_ENTITY_NUMBERING_ID,
	EXCEPTION_UPPER_LIMIT_VALUE
}
