ALTER TABLE client_details ADD COLUMN device_code_validity_seconds BIGINT;

CREATE TABLE IF NOT EXISTS device_code (
	id SERIAL PRIMARY KEY,
	device_code VARCHAR(1024),
	user_code VARCHAR(1024),
	expiration TIMESTAMP,
	client_id VARCHAR(256),
	approved BOOLEAN,
	auth_holder_id BIGINT	
);

CREATE TABLE IF NOT EXISTS device_code_scope (
	owner_id BIGINT NOT NULL,
	scope VARCHAR(256) NOT NULL
);

CREATE TABLE IF NOT EXISTS device_code_request_parameter (
	owner_id BIGINT,
	param VARCHAR(2048),
	val VARCHAR(2048)
);