package jp.co.cos_mos.mdm.core.dao.mapper;

import jp.co.cos_mos.mdm.core.dao.entity.SequenceNumber;

/**
 * SequenceNumberデータアクセスマッパー。
 * 
 * @author Cosmos Inc.
 */
public interface SequenceNumberMapper {
	
	/**
	 * SequenceNumberの主キーを指定しSequenceNumberを取得します。
	 * 
	 * @param id SequenceNumberId
	 * @return SequenceNumber SequenceNumberエンティティオブジェクト
	 */
	public SequenceNumber select(Long id);
	
	/**
	 * 引数に指定されたSequenceNumberを追加します。
	 * 
	 * @param sequence SequenceNumberエンティティオブジェクト
	 * @return 登録件数
	 */
	public int insert(SequenceNumber sequence);
	
	/**
	 * 引数に指定されたSequenceNumberを更新します。
	 * <p>
	 * 更新キー・更新値として以下を指定してください。
	 * <ul>
	 * <li>id SequenceNumberId
	 * <li>lastUpdateTs 最終更新日時
	 * </ul>
	 * 
	 * @param sequence SequenceNumberエンティティオブジェクト
	 * @return 更新件数
	 */
	public int update(SequenceNumber sequence);
	
	/**
	 * 引数に指定されたSequenceIdentifierを削除します。
	 * <p>
	 * 削除キーとして以下を指定してください。
	 * <ul>
	 * <li>id SequenceNumberId
	 * <li>lastUpdateTs 最終更新日時
	 * </ul>
	 * 
	 * @param sequence SequenceNumberエンティティオブジェクト
	 * @return 削除件数
	 */
	public int delete(SequenceNumber sequence);
	
	/**
	 * 引数に指定されたSequenceNumberの採番値を更新します。
	 * <p>
	 * 更新キー・更新値として以下を指定してください。
	 * <ul>
	 * <li>id SequenceNumberId
	 * <li>lastUpdateTs 最終更新日時
	 * <li>currentTs 現在日時
	 * </ul>
	 * 
	 * @param sequence SequenceNumberエンティティオブジェクト
	 * @return 更新件数
	 */
	public int updateNumbering(SequenceNumber sequence);
	
}
