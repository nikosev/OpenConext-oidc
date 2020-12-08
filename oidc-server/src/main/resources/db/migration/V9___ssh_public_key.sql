CREATE TABLE IF NOT EXISTS user_ssh_public_key (
	user_id BIGINT,
	ssh_public_key TEXT
);

CREATE INDEX uspk_ui_idx ON user_ssh_public_key(user_id);

INSERT INTO system_scope (scope, description, icon, restricted, default_scope, structured, structured_param_description) VALUES
	('ssh_public_key', 'read you SSH public keys', 'certificate', true, false, false, null);
