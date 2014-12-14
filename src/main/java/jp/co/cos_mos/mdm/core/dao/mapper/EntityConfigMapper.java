package jp.co.cos_mos.mdm.core.dao.mapper;

import jp.co.cos_mos.mdm.core.dao.entity.EntityConfig;

/**
 * EntityConfigデータアクセスマッパー。
 * 
 * @author Cosmos Inc.
 */
public interface EntityConfigMapper {

	/**
	 * Entityクラス名を指定しEntityConfigを取得します。
	 * <pre>
	 * SELECT
	 *     class_name AS className,
	 *     sequence_id AS sequenceId,
	 *     inactive_ts AS inactiveTs,
	 *     last_update_ts AS lastUpdateTs,
	 *     last_update_user AS lastUpdateUser,
	 *     last_update_tx_id AS lastUpdateTxId
	 * FROM
	 *     _entity_config
	 * WHERE
	 *         class_name = #{className}
	 * </pre>
	 * @param classname Entity クラス名（完全名）
	 * @return EntityConfigオブジェクト
	 */
	public EntityConfig select(String classname);

	/**
	 * 引数に指定されたEntityConfigを追加します。
	 * 
	 * @param config EntityConfigエンティティオブジェクト
	 * @return 登録件数
	 */
	public int insert(EntityConfig config);

	/**
	 * 引数に指定されたEntityConfigを削除します。
	 * <p>
	 * 削除キーとして以下を指定してください。
	 * <ul>
	 * <li>name Entityクラス名
	 * <li>lastUpdateTs 最終更新日時
	 * </ul>
	 * 
	 * @param config EntityConfigエンティティオブジェクト
	 * @return 削除件数
	 */
	public int delete(EntityConfig config);

}
