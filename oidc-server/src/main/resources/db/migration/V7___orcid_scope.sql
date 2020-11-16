ALTER TABLE user_info ADD edu_person_orcid varchar(256) DEFAULT NULL;

INSERT INTO system_scope (scope, description, icon, restricted, default_scope, structured, structured_param_description) VALUES
  ('orcid', 'read your ORCID iD', 'user', true, false, false, null);
