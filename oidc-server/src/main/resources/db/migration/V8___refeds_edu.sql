--
-- Add eduperson_entitlement to UserInfo,
--

CREATE TABLE IF NOT EXISTS user_eduperson_entitlement (
	user_id BIGINT,
	eduperson_entitlement VARCHAR(256)
);
CREATE INDEX unepe_id_idx ON user_eduperson_entitlement(user_id);

CREATE TABLE IF NOT EXISTS user_eduperson_scoped_affiliation (
	user_id BIGINT,
	eduperson_scoped_affiliation VARCHAR(256)
);
CREATE INDEX unepsa_id_idx ON user_eduperson_scoped_affiliation(user_id);

CREATE TABLE IF NOT EXISTS user_eduperson_assurance (
	user_id BIGINT,
	eduperson_assurance VARCHAR(256)
);
CREATE INDEX uepa_id_idx ON user_eduperson_assurance(user_id);

ALTER TABLE user_info ADD eduperson_unique_id varchar(256) DEFAULT NULL;