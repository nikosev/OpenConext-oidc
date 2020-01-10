CREATE TABLE IF NOT EXISTS user_cert_entitlement (
	user_id BIGINT,
	cert_entitlement VARCHAR(1024)
);

CREATE INDEX uce_ui_idx ON user_cert_entitlement(user_id);

INSERT INTO system_scope (scope, description, icon, restricted, default_scope, structured, structured_param_description) VALUES
  ('cert_entitlement', 'read your certificate subject DN', 'certificate', true, false, false, null);