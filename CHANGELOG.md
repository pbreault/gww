# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/).

## [Unreleased]
### Fixed
- Allow `/` without splitting commands in system props (`-Dargs`) and projects props (`-Pargs`). Fixed by [@EricKuck](https://github.com/EricKuck)

## [0.1.0] - 2022-01-16
Initial Version
### Added
- Navigate up to find the first `gradlew` if it isn't found in the current directory.
- Convert `:` to `/` in project names to take advantage of the shell's autocomplete
  - e.g. `gww features/myfeature:clean`
- Split commands on the same project using `//`
  - e.g. `gww features/myfeature:clean//assemble`

[Unreleased]: https://github.com/pbreault/gww/compare/0.1.0..HEAD
[0.1.0]: https://github.com/pbreault/gww/releases/tag/0.1.0