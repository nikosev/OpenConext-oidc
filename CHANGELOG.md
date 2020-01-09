# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/)
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [v1.0.0]

### Added

- Add Manage Services
- Add options to define the max lifetime of a token
- Add options to define the default expiration time for each token type
- Add functionality for token exchange
- Add refeds_edu scope
- Add mappings for scopes-claims
- Add new file to overwrite the locale messages without need to change the original file
- Add options to define which format of the eduperson_* claim name will be displayed

### Changed

- Modify scripts to support PostgreSQL DB
- Change requesterID format

### Fixed

- Update all user attributes on login
- Add Json header in the response of well-known/openid-configuration endpoint