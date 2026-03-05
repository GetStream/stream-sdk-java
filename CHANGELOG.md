# Changelog

All notable changes to this project will be documented in this file. See [standard-version](https://github.com/conventional-changelog/standard-version) for commit guidelines.

## [6.0.0](https://github.com/GetStream/stream-sdk-java/compare/5.0.1...6.0.0) (2026-03-05)

### Breaking Changes

- Type names across all products now follow the OpenAPI spec naming convention: response types are suffixed with `Response`, input types with `Request`. See [MIGRATION_v5_to_v6.md](./MIGRATION_v5_to_v6.md) for the complete rename mapping.
- `Event` (WebSocket envelope type) renamed to `WSEvent`. Base event type renamed from `BaseEvent` to `Event` (with field `type` instead of `T`).
- Event composition changed from monolithic `*Preset` embeds to modular `Has*` types.
- `Pager` renamed to `PagerResponse` and migrated from offset-based to cursor-based pagination (`next`/`prev` tokens).

### Added

- Full product coverage: Chat, Video, Moderation, and Feeds APIs are all supported in a single SDK.
- **Feeds**: activities, feeds, feed groups, follows, comments, reactions, collections, bookmarks, membership levels, feed views, and more.
- **Video**: calls, recordings, transcription, closed captions, SFU, call statistics, user feedback analytics, and more.
- **Moderation**: flags, review queue, moderation rules, config, appeals, moderation logs, and more.
- Push notification types, preferences, and templates.
- Webhook support: `WHEvent` envelope class for receiving webhook payloads, utility methods for decoding and verifying webhook signatures, and a full set of individual typed event classes for every event across all products (Chat, Video, Moderation, Feeds) usable as discriminated event types.
- Cursor-based pagination across all list endpoints.

### [5.0.1](https://github.com/GetStream/stream-sdk-java/compare/5.0.0...5.0.1) (2026-02-11)

## [5.0.0](https://github.com/GetStream/stream-sdk-java/compare/4.1.1...5.0.0) (2026-02-03)

### [4.1.1](https://github.com/GetStream/stream-sdk-java/compare/4.0.0...4.1.1) (2026-01-06)

## [4.1.0](https://github.com/GetStream/stream-sdk-java/compare/4.0.0...4.1.0) (2026-01-06)

## [4.0.0](https://github.com/GetStream/stream-sdk-java/compare/3.0.3...4.0.0) (2025-09-30)

### [3.0.3-feeds](https://github.com/GetStream/stream-sdk-java/compare/3.0.1...3.0.3-feeds) (2025-08-22)

### [3.0.2-feeds](https://github.com/GetStream/stream-sdk-java/compare/2.0.0...3.0.2-feeds) (2025-08-22)

### [3.0.1-feeds](https://github.com/GetStream/stream-sdk-java/compare/2.0.0...3.0.1-feeds) (2025-08-22)

## [3.0.0-feeds](https://github.com/GetStream/stream-sdk-java/compare/2.0.0...3.0.0-feeds) (2025-08-22)

## [2.0.0](https://github.com/GetStream/stream-sdk-java/compare/1.0.0...2.0.0) (2025-04-30)

## 1.0.0 (2025-02-18)

### 0.1.5 (2025-02-12)

### 0.1.4 (2025-02-12)

### 0.1.3 (2025-02-12)

### 0.1.2 (2025-02-12)

### 0.1.1 (2025-02-12)


## 0.1.0 (2025-02-12)
