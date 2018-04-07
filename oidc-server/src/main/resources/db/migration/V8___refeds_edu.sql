--
-- Add eduperson_entitlement to UserInfo,
--

CREATE TABLE IF NOT EXISTS user_eduperson_entitlement (
	user_id BIGINT,
	eduperson_entitlement VARCHAR(255)
);
CREATE INDEX uepe_id_idx ON user_eduperson_entitlement(user_id);
ALTER TABLE user_eduperson_entitlement ADD id MEDIUMINT PRIMARY KEY AUTO_INCREMENT;

CREATE TABLE IF NOT EXISTS user_eduperson_scoped_affiliation (
	user_id BIGINT,
	eduperson_scoped_affiliation VARCHAR(255)
);
CREATE INDEX uepsa_id_idx ON user_eduperson_scoped_affiliation(user_id);
ALTER TABLE user_eduperson_scoped_affiliation ADD id MEDIUMINT PRIMARY KEY AUTO_INCREMENT;

ALTER TABLE user_info ADD eduperson_unique_id varchar(255) DEFAULT NULL;

ALTER TABLE user_info ADD eduperson_assurance varchar(255) DEFAULT NULL;