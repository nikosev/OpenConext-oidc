CREATE TABLE IF NOT EXISTS user_voperson_verified_email (
	user_id BIGINT,
	voperson_verified_email VARCHAR(256)
);

CREATE INDEX uvpve_ui_idx ON user_voperson_verified_email(user_id);