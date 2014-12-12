CREATE TABLE IF NOT EXISTS _sequence_number
(
  id BIGINT NOT NULL,
  seq BIGINT NOT NULL,
  name VARCHAR(32),
  initial_value INT NOT NULL DEFAULT 0,
  increment_value INT NOT NULL DEFAULT 1,
  max_value INT NOT NULL,
  last_update_ts TIMESTAMP(6) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS _entity_config
(
  class_name VARCHAR(255) NOT NULL,
  sequence_id BIGINT NOT NULL,
  inactive_ts TIMESTAMP(6) NULL default NULL,
  last_update_ts TIMESTAMP(6) NOT NULL,
  last_update_user VARCHAR(32) NOT NULL,
  last_update_tx_id BIGINT NOT NULL,
  PRIMARY KEY (class_name)
);

CREATE TABLE IF NOT EXISTS owner
(
  id BIGINT NOT NULL,
  name VARCHAR(255) NOT NULL,
  inactive_ts TIMESTAMP(6) NULL default NULL,
  last_update_ts TIMESTAMP(6) NOT NULL,
  last_update_user VARCHAR(32) NOT NULL,
  last_update_tx_id BIGINT NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS code_category
(
  id BIGINT NOT NULL,
  owner_id BIGINT NOT NULL,
  name VARCHAR(255) NOT NULL,
  inactive_ts TIMESTAMP(6) NULL default NULL,
  last_update_ts TIMESTAMP(6) NOT NULL,
  last_update_user VARCHAR(32) NOT NULL,
  last_update_tx_id BIGINT NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS code
(
  id BIGINT NOT NULL,
  owner_id BIGINT NOT NULL,
  category_id BIGINT NOT NULL,
  code VARCHAR(50) NOT NULL,
  name VARCHAR(255) NOT NULL,
  start_date CHAR(8) NOT NULL,
  end_date CHAR(8) NOT NULL,
  last_update_ts TIMESTAMP(6) NOT NULL,
  last_update_user VARCHAR(32) NOT NULL,
  last_update_tx_id BIGINT NOT NULL,
  PRIMARY KEY (id)
);

INSERT INTO _entity_config (`class_name`, `sequence_id`, `last_update_ts`, `last_update_user`, `last_update_tx_id`) VALUES ('jp.co.cos_mos.mdm.core.dao.entity.SequenceNumber', '0', CURRENT_TIMESTAMP, 'c-mdm', '0');
INSERT INTO _entity_config (`class_name`, `sequence_id`, `last_update_ts`, `last_update_user`, `last_update_tx_id`) VALUES ('jp.co.cos_mos.mdm.v1.dao.entity.Owner', '1', CURRENT_TIMESTAMP, 'c-mdm', '0');
INSERT INTO _entity_config (`class_name`, `sequence_id`, `last_update_ts`, `last_update_user`, `last_update_tx_id`) VALUES ('jp.co.cos_mos.mdm.v1.dao.entity.CodeCategory', '2', CURRENT_TIMESTAMP, 'c-mdm', '0');
INSERT INTO _entity_config (`class_name`, `sequence_id`, `last_update_ts`, `last_update_user`, `last_update_tx_id`) VALUES ('jp.co.cos_mos.mdm.v1.dao.entity.Code', '3', CURRENT_TIMESTAMP, 'c-mdm', '0');

