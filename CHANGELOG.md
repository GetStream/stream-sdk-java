# Changelog

All notable changes to this project will be documented in this file. See [standard-version](https://github.com/conventional-changelog/standard-version) for commit guidelines.

## [Unreleased]

### Added

- Typed error hierarchy for the Stream API per [CHA-2958](https://linear.app/stream/issue/CHA-2958). New checked subclasses of the existing `StreamException`:
    * `StreamApiException` — HTTP 4xx/5xx with the `APIError` envelope parsed. Getters: `getStatusCode`, `getCode`, `getMessage`, `getExceptionFields`, `isUnrecoverable`, `getRawResponseBody`, `getMoreInfo`, `getDetails`. The `unrecoverable` and `details` fields were previously dropped on the floor.
    * `StreamRateLimitException` — HTTP 429. Subclass of `StreamApiException` with `Duration getRetryAfter()` populated from `Retry-After` (RFC 7231 §7.1.3 integer seconds + HTTP-date). `null` when header absent or unparseable.
    * `StreamTransportException` — connection reset, timeout, DNS, TLS, etc. Carries `getErrorType()` matching the logging spec enum (`connection_reset` / `timeout` / `dns_failure` / `tls_handshake_failed` / `unknown`). Cause chain preserved.
    * `StreamTaskException` — async task observed with `status: "failed"`. Carries `getTaskId`, `getErrorType`, `getDescription`, `getStackTraceText`, `getVersion`. (`getStackTraceText` rather than `getStackTrace` to avoid colliding with `Throwable.getStackTrace()`.)
- `StreamSDKClient.waitForTask(taskId)` — main-source helper that polls the task endpoint to a terminal state. Returns `GetTaskResponse` on `completed`, throws `StreamTaskException` on `failed`, throws `StreamTransportException(errorType=timeout)` if the wait elapses (defaults: 1s poll, 60s timeout; overloaded with explicit `Duration`s). Replaces the test-only `ChatTestBase.waitForTask` (removed).
- Webhook handling spec helpers (CHA-2961): `UnknownEvent` class for forward-compat;
  `gunzipPayload`, `decodeSqsPayload`, `decodeSnsPayload` primitives;
  `verifyAndParseWebhook` HTTP composite; `parseSqs` / `parseSns`
  queue composites (no HMAC signature on the payload: queue transports rely on
  AWS IAM for authentication). Transparent gzip via magic-byte detection.
- New instance methods on `StreamSDKClient`: `verifySignature(body, signature)`
  and `verifyAndParseWebhook(body, signature)` that drop the api_secret parameter
  in favor of the client's stored secret. Dual API: static `Webhook.*` methods
  remain available.
- New instance methods on `StreamSDKClient`: `parseSqs(String)`, `parseSns(String)`
  (no signature; AWS IAM).
- New exception class: `Webhook.InvalidWebhookException` (unified, covering both
  signature mismatch and malformed payloads).
- Conformance fixture suite under `src/test/resources/fixtures/webhooks/`.
- Explicit HTTP connection pool configuration ([CHA-2956](https://linear.app/stream/issue/CHA-2956/connection-pooling)). New `StreamClientOptions` POJO with fluent setters:
    * `setMaxConnsPerHost(int)`: default `5`
    * `setIdleTimeout(Duration)`: default `55s`
    * `setConnectTimeout(Duration)`: default `10s`
    * `setRequestTimeout(Duration)`: default `30s` (was `10s`; see Behavior changes)
    * `setHttpClient(OkHttpClient)`: escape hatch; bypasses the four knobs above
  Pass via the new constructor: `new StreamSDKClient(apiKey, secret, options)`.
- Per-call `RequestTimeout` override on `StreamRequest`: `request.callTimeout(Duration.ofSeconds(5)).execute()`.
- INFO log on client construction lists the effective pool config. Uses `java.util.logging.Logger` (no new dependency).
- Regenerated from the latest chat OpenAPI spec. New endpoints: `Moderation.analyze`, `Moderation.bulkActionAppeals`, `Moderation.getSetupSession`, `Moderation.upsertSetupSession`; `Feeds.getOrCreateFollow`, `Feeds.getOrCreateUnfollow`, `Feeds.getUserInterests`; `Chat.createSegment`, `Chat.updateSegment`, `Chat.addSegmentTargets`; `Common.cancelImportV2Task`; `Video.reportClientCallEvent`, together with the request/response model classes backing them.
- New webhook event types `moderation.image_analysis.complete` and `moderation.text_analysis.complete`, with `ModerationImageAnalysisCompleteEvent` and `ModerationTextAnalysisCompleteEvent` model classes.

### Changed

- Exceptions remain **checked** (CHA-2958 §9.3). All new subclasses extend the existing checked `StreamException`, so `throws StreamException` declarations continue to compile and `catch (StreamException)` continues to handle every SDK error.
- `StreamRequest` now throws `StreamApiException` (or `StreamRateLimitException` for 429) for HTTP-response errors, and `StreamTransportException` for IO failures. The static type on declarations is still `StreamException` — these are subclasses. The static `StreamException.build(Throwable)` factory continues to wrap into a base `StreamException` (the request path classifies transport failures directly so this factory is no longer auto-routed). `StreamException.getResponseData()` is still populated on API exceptions for back-compat.
- The `APIError` envelope parser (formerly `StreamException.ResponseData`) gained the previously-dropped `details` and `unrecoverable` fields.
- **Default per-call `RequestTimeout` is now `30s` (was `10s`).** Aligns with CHA-2956 cross-SDK contract. The previous `10s` came from the hardcoded `timeout = 10000` ms in `StreamHTTPClient`. To keep the old ceiling, pass `new StreamClientOptions().setRequestTimeout(Duration.ofSeconds(10))`.
- Default idle-connection lifetime now `55s` (was `59s` via the `STREAM_API_CONNECTION_MAX_AGE` env var path). 55s sits 5s below the typical 60s LB idle timeout for safer eviction. `MaxConnsPerHost` default is unchanged at `5`.
- The client constructors are unchanged. Existing `StreamSDKClient(apiKey, secret)`, `StreamSDKClient(apiKey, secret, OkHttpClient)`, and `StreamSDKClient(Properties)` are preserved.

### Breaking

These come from regenerating against the latest chat OpenAPI spec. They are source- and binary-incompatible for Java consumers (changed return types and removed/retyped getters), so this release is a major version bump.

- `Moderation.flag(...)` now returns `StreamRequest<FlagItemResponse>` (was `StreamRequest<FlagResponse>`). The moderation flag-action acknowledgement, which carries `itemID` and `duration`, moved to the new `FlagItemResponse`; `FlagResponse` now models the full flag record (`createdAt`, `updatedAt`, `targetMessage`, `targetUser`, `user`, `reason`, `details`, `custom`, and related fields). This resolves an upstream OpenAPI name collision where the acknowledgement was shadowing the rich record. The `/api/v2/moderation/flag` wire response is unchanged; call sites typed on `FlagResponse` must switch to `FlagItemResponse`.
- Removed getters: `FlagResponse.getItemID()`, `FlagResponse.getDuration()`, `FlagDetails.getExtra()`.
- Changed getter return types: `ChannelInput.getConfigOverrides()` and `ChannelDataUpdate.getConfigOverrides()` now return `ChannelConfigOverrides` (was `ChannelConfig`); `FlagDetails.getAutomod()` now returns `AutomodDetailsResponse` (was `AutomodDetails`); `ChatMessageResponse.getAttachments()` now returns `List<Attachment>` (was `List<Object>`); `ChatMessageResponse.getOwnReactions()` and `getLatestReactions()` now return `List<ChatReactionResponse>` (was `List<Object>`).

## [7.2.0](https://github.com/GetStream/stream-sdk-java/compare/7.1.0...7.2.0) (2026-04-30)

## [7.1.0](https://github.com/GetStream/stream-sdk-java/compare/7.0.0...7.1.0) (2026-04-10)


### Features

* allow users to provide a custom OkHttpClient ([780443e](https://github.com/GetStream/stream-sdk-java/commit/780443e0bfb0f658d5f769e43dafa69eba33acad))

## [7.0.0](https://github.com/GetStream/stream-sdk-java/compare/6.1.0...7.0.0) (2026-03-31)


### Features

* regenerate from latest OpenAPI spec, keep only retention runs test ([2c7e910](https://github.com/GetStream/stream-sdk-java/commit/2c7e910f7761a49c9b7199ea5ed862cc4294b9f3))


### Bug Fixes

* apply spotless formatting to generated code ([e593fce](https://github.com/GetStream/stream-sdk-java/commit/e593fce0c9e3965d0db71dd07db4f2ea0f96dfc9))
* remove trailing blank line for spotless ([6b300f7](https://github.com/GetStream/stream-sdk-java/commit/6b300f783f56b0b85c373eef74cd3fecf089313c))
* use lowercase builder method for limit field ([841e0cb](https://github.com/GetStream/stream-sdk-java/commit/841e0cbf125280fd8599d8c970b34e55f63739a5))

## [6.1.0](https://github.com/GetStream/stream-sdk-java/compare/6.0.1...6.1.0) (2026-03-20)

### [6.0.1](https://github.com/GetStream/stream-sdk-java/compare/6.0.0...6.0.1) (2026-03-19)

## [6.0.0](https://github.com/GetStream/stream-sdk-java/compare/5.0.1...6.0.0) (2026-03-05)

### Breaking Changes

- Type names across all products now follow the OpenAPI spec naming convention: response types are suffixed with `Response`, input types with `Request`. See [MIGRATION_v5_to_v6.md](./MIGRATION_v5_to_v6.md) for the complete rename mapping.
- `Event` (WebSocket envelope type) renamed to `WSEvent`. Base event type renamed from `BaseEvent` to `Event` (with field `type` instead of `T`).
- Event composition changed from monolithic `*Preset` embeds to modular `Has*` types.
- `Pager` renamed to `PagerResponse` and migrated from offset-based to cursor-based pagination (`next`/`prev` tokens).

### Added

- Full product coverage: Chat, Video, Moderation, and Feeds APIs are all supported in a single SDK.
- **Feeds**: activities, feeds, feed groups, follows, comments, reactions, collections, bookmarks, membership levels, feed views and more.
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
