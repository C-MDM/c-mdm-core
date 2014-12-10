package jp.co.cos_mos.mdm.core.dao.mapper;

import jp.co.cos_mos.mdm.core.dao.entity.SequenceIdentifier;

/**
 * SequenceIdentifierデータアクセスマッパー。
 * 
 * @author Cosmos Inc.
 */
public interface SequenceIdentifierMapper {

	/**
	 * SequenceIdentifierの主キーを指定しSequenceIdentifierを取得します。
	 * <pre>
	 * SELECT
	 *     class_name AS className,
	 *     sequence_id AS sequenceId,
	 *     inactive_ts AS inactiveTs,
	 *     last_update_ts AS lastUpdateTs,
	 *     last_update_user AS lastUpdateUser,
	 *     last_update_tx_id AS lastUpdateTxId
	 * FROM
	 *     sequence_identifier
	 * WHERE
	 *         class_name = #{className}
	 * </pre>
	 * @param classname Entity クラス名（完全名）
	 * @return SequenceIdentifierエンティティオブジェクト
	 */
	public SequenceIdentifier select(String classname);

}
