# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/)
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

### Added

- Add `ssh_public_key` scope

### Fixed

- Fix layout of approve device page of device code grant
- Remove whitespace after `eduperson_unique_id` in /.well-known/openid-configuration
- Add option to enable/disable claims to be displayed on introspection response

### Security

- Patch autobind vulnerability

## [v2.2.0]

### Added

- Add support for `cert_entitlement` scope
- Include email claim in introspection endpoint response
- Add ORCID scope
- Add support for "voPersonVerifiedEmail" SAML attribute

### Fixed

- Increase ProxyCount to 10
- Remove the last "/" from the request URI if exists
- Display error message properly when creating a new client with duplicate client id
- Add ContentType in generate-oidc-keystore response
- Remove empty claims from instrospection endpoint response

## [v2.1.0]

### Added

- Add support for PKCE

## [v2.0.0]

### Added

- Add support for device code flow
- Add "refresh_token" to "grant_types_supported"

### Fixed

- Add missing quotes in FederatedUserInfo class

### Removed

- Disable HTML escaping on json response

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
