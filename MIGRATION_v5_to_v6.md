# Migration Guide: v5 → v6

This guide covers all breaking changes when upgrading from `stream-sdk-java` v5 to v6.

## Overview

v6 is a full OpenAPI-aligned release. The primary change is a **systematic type renaming**: types that appear in API responses now have a `Response` suffix, and input types have a `Request` suffix. There are no removed features — all functionality from v5 is available in v6. Additionally, v6 adds complete coverage of the **Feeds**, **Video**, and **Moderation** product APIs.

## Installation

Update your Maven `pom.xml`:

```xml
<dependency>
    <groupId>io.getstream</groupId>
    <artifactId>stream-sdk-java</artifactId>
    <version>6.0.0</version>
</dependency>
```

Or your Gradle `build.gradle`:

```groovy
implementation 'io.getstream:stream-sdk-java:6.0.0'
```

## Naming Conventions

All types use `PascalCase` and fields use `camelCase` (standard Java convention). The general rules:

- Types returned in API responses: `Foo` → `FooResponse`
- Types used as API inputs: `Foo` → `FooRequest`
- Some moderation action payloads: `FooRequest` → `FooRequestPayload`

## Breaking Changes

### Common / Shared Types

| v5 | v6 | Notes |
| --- | --- | --- |
| `ApplicationConfig` | `AppResponseFields` | App configuration in responses |
| `ChannelPushPreferences` | `ChannelPushPreferencesResponse` | Per-channel push settings |
| `Device` | `DeviceResponse` | Device data (push, voip) |
| `Event` | `WSEvent` | WebSocket event envelope |
| `FeedsPreferences` | `FeedsPreferencesResponse` | Feeds push preferences |
| `ImportV2Task` | `ImportV2TaskItem` | V2 import task |
| `OwnUser` | `OwnUserResponse` | Authenticated user data |
| `Pager` | `PagerResponse` | Now cursor-based (`next`/`prev`) |
| `PushPreferences` | `PushPreferencesResponse` | Push preferences |
| `PushTemplate` | `PushTemplateResponse` | Push template |
| `PrivacySettings` | `PrivacySettingsResponse` | Typing indicators, read receipts |
| `RateLimitInfo` | `LimitInfoResponse` | Rate limit info |
| `SortParam` | `SortParamRequest` | Sort parameter for queries |
| `User` | `UserResponse` | Full user in responses |
| `UserBlock` | `BlockedUserResponse` | Blocked user details |
| `UserCustomEvent` | `CustomEvent` | Custom user event |
| `UserMute` | `UserMuteResponse` | User mute details |

### Event System

| Before (v5) | After (v6) | Notes |
| --- | --- | --- |
| `BaseEvent` (field `T`) | `Event` (field `type`) | Base event type |
| `Event` (WS envelope) | `WSEvent` | WebSocket event wrapper |
| `*Preset` embeds | `Has*` composition types | e.g., `HasChannel`, `HasMessage` |
| — | `WHEvent` | New webhook envelope type |

New composition types: `HasOwnUser`, `HasUserCommonFields`, `HasUserPrivacyFields`, `HasOptionalUserCommonFields`, `HasChannel`, `HasOptionalChannel`, `HasMessage`, `HasOptionalMessage`, `HasThreadParticipants`, `HasChannelTypeAndID`.

### Chat Types

| v5 | v6 | Notes |
| --- | --- | --- |
| `Campaign` | `CampaignResponse` | |
| `CampaignStats` | `CampaignStatsResponse` | |
| `Channel` | `ChannelResponse` | |
| `ChannelConfigFields` | `ChannelConfigWithInfo` | Channel config + commands/grants |
| `ChannelMember` | `ChannelMemberResponse` | |
| `ChannelTypeConfigWithInfo` | `ChannelTypeConfig` | |
| `ConfigOverrides` | `ConfigOverridesRequest` | |
| `DraftMessage` / `DraftMessagePayload` | `DraftResponse` | Two types merged into one |
| `Message` | `MessageResponse` | |
| `MessageReminder` | `ReminderResponseData` | |
| `PendingMessage` | `PendingMessageResponse` | |
| `Poll` | `PollResponse` | |
| `PollOption` | `PollOptionResponse` | |
| `PollVote` | `PollVoteResponse` | |
| `Reaction` | `ReactionResponse` | |
| `ReadState` | `ReadStateResponse` | |
| `Thread` | `ThreadResponse` | |

### Video Types

| v5 | v6 | Notes |
| --- | --- | --- |
| `AudioSettings` | `AudioSettingsResponse` | |
| `BackstageSettings` | `BackstageSettingsResponse` | |
| `BroadcastSettings` | `BroadcastSettingsResponse` | |
| `Call` | `CallResponse` | |
| `CallEgress` | `EgressResponse` | |
| `CallMember` | `MemberResponse` | Note: not `CallMemberResponse` |
| `CallParticipant` | `CallParticipantResponse` | |
| `CallParticipantFeedback` | *(removed)* | Use `CollectUserFeedbackRequest` |
| `CallSession` | `CallSessionResponse` | |
| `CallSettings` | `CallSettingsResponse` | |
| `CallType` | `CallTypeResponse` | |
| `EventNotificationSettings` | `EventNotificationSettingsResponse` | |
| `FrameRecordSettings` | `FrameRecordingSettingsResponse` | `Recording` inserted in name |
| `GeofenceSettings` | `GeofenceSettingsResponse` | |
| `HLSSettings` | `HLSSettingsResponse` | |
| `IndividualRecordSettings` | `IndividualRecordingSettingsResponse` | `Recording` inserted in name |
| `IngressSettings` | `IngressSettingsResponse` | |
| `IngressSource` | `IngressSourceResponse` | |
| `IngressAudioEncodingOptions` | `IngressAudioEncodingResponse` | Shortened name |
| `IngressVideoEncodingOptions` | `IngressVideoEncodingResponse` | Shortened name |
| `IngressVideoLayer` | `IngressVideoLayerResponse` | |
| `LimitsSettings` | `LimitsSettingsResponse` | |
| `NotificationSettings` | `NotificationSettingsResponse` | |
| `RawRecordSettings` | `RawRecordingSettingsResponse` | `Recording` inserted in name |
| `RecordSettings` | `RecordSettingsResponse` | |
| `RingSettings` | `RingSettingsResponse` | |
| `RTMPSettings` | `RTMPSettingsResponse` | |
| `ScreensharingSettings` | `ScreensharingSettingsResponse` | |
| `SessionSettings` | `SessionSettingsResponse` | |
| `SIPCallConfigs` | `SIPCallConfigsResponse` | |
| `SIPCallerConfigs` | `SIPCallerConfigsResponse` | |
| `SIPDirectRoutingRuleCallConfigs` | `SIPDirectRoutingRuleCallConfigsResponse` | |
| `SIPInboundRoutingRules` | `SIPInboundRoutingRuleResponse` | Plural → singular |
| `SIPPinProtectionConfigs` | `SIPPinProtectionConfigsResponse` | |
| `SIPTrunk` | `SIPTrunkResponse` | |
| `ThumbnailsSettings` | `ThumbnailsSettingsResponse` | |
| `TranscriptionSettings` | `TranscriptionSettingsResponse` | |
| `VideoSettings` | `VideoSettingsResponse` | |

### Moderation Types

| v5 | v6 | Notes |
| --- | --- | --- |
| `ActionLog` | `ActionLogResponse` | |
| `Appeal` | `AppealItemResponse` | |
| `AutomodDetails` | `AutomodDetailsResponse` | |
| `Ban` | `BanInfoResponse` | |
| `BanOptions` | *(removed)* | Merged into `BanActionRequestPayload` |
| `BanActionRequest` | `BanActionRequestPayload` | |
| `BlockActionRequest` | `BlockActionRequestPayload` | |
| `BlockedMessage` | *(removed)* | Internal only |
| `CustomActionRequest` | `CustomActionRequestPayload` | |
| `DeleteMessageRequest` | `DeleteMessageRequestPayload` | |
| `DeleteUserRequest` | `DeleteUserRequestPayload` | |
| `EntityCreator` | `EntityCreatorResponse` | |
| `Evaluation` | `EvaluationResponse` | |
| `FeedsModerationTemplate` | `QueryFeedModerationTemplate` | No `Response` suffix |
| `FeedsModerationTemplateConfig` | `FeedsModerationTemplateConfigPayload` | |
| `Flag` | *(removed)* | Use `ModerationFlagResponse` |
| `Flag2` | `ModerationFlagResponse` | |
| `FlagDetails` | `FlagDetailsResponse` | |
| `FlagFeedback` | `FlagFeedbackResponse` | |
| `FlagMessageDetails` | `FlagMessageDetailsResponse` | |
| `FlagReport` | *(removed)* | Internal only |
| `FutureChannelBan` | `FutureChannelBanResponse` | |
| `MarkReviewedRequest` | `MarkReviewedRequestPayload` | |
| `Match` | `MatchResponse` | |
| `ModerationActionConfig` | `ModerationActionConfigResponse` | |
| `ModerationBulkSubmitActionRequest` | `BulkSubmitActionRequest` | `Moderation` prefix dropped |
| `ModerationConfig` | `ConfigResponse` | |
| `ModerationFlags` | *(removed)* | Use `List<ModerationFlagResponse>` |
| `ModerationLog` | *(removed)* | Use `ActionLogResponse` |
| `ModerationLogResponse` | *(removed)* | Use `QueryModerationLogsResponse` |
| `ModerationUsageStats` | `ModerationUsageStatsResponse` | |
| `RestoreActionRequest` | `RestoreActionRequestPayload` | |
| `ReviewQueueItem` | `ReviewQueueItemResponse` | |
| `Rule` | `RuleResponse` | |
| `ShadowBlockActionRequest` | `ShadowBlockActionRequestPayload` | |
| `Task` | `TaskResponse` | |
| `Trigger` | `TriggerResponse` | |
| `UnbanActionRequest` | `UnbanActionRequestPayload` | |
| `UnblockActionRequest` | `UnblockActionRequestPayload` | |
| `VideoEndCallRequest` | `VideoEndCallRequestPayload` | |
| `VideoKickUserRequest` | `VideoKickUserRequestPayload` | |

### Feeds Types

| v5 | v6 | Notes |
| --- | --- | --- |
| `Activity` | `ActivityResponse` | |
| `ActivityFeedback` | `ActivityFeedbackRequest` | Request-only (no `Response` suffix) |
| `ActivityMark` | `MarkActivityRequest` | |
| `ActivityPin` | `ActivityPinResponse` | |
| `AggregatedActivity` | `AggregatedActivityResponse` | |
| `Bookmark` | `BookmarkResponse` | |
| `BookmarkFolder` | `BookmarkFolderResponse` | |
| `Collection` | `CollectionResponse` | |
| `Comment` | `CommentResponse` | |
| `CommentMedia` | *(removed)* | Embedded inline in `CommentResponse` |
| `CommentMention` | *(removed)* | Embedded inline in `CommentResponse` |
| `DenormalizedFeedsReaction` | *(removed)* | Internal only |
| `Feed` | `FeedResponse` | |
| `FeedGroup` | `FeedGroupResponse` | |
| `FeedMember` | `FeedMemberResponse` | |
| `FeedsReaction` | `FeedsReactionResponse` | |
| `FeedsReactionGroup` | `FeedsReactionGroupResponse` | |
| `FeedSuggestion` | `FeedSuggestionResponse` | |
| `FeedView` | `FeedViewResponse` | |
| `FeedVisibilityInfo` | `FeedVisibilityResponse` | |
| `Follow` | `FollowResponse` | |
| `MembershipLevel` | `MembershipLevelResponse` | |
| `ThreadedComment` | `ThreadedCommentResponse` | |

## JSON Serialization of Optional Fields

Optional (nullable) fields in request objects are now omitted from the JSON body when not set, instead of being sent as explicit `null`. Previously, every unset field was serialized as `null`, which caused the backend to zero out existing values on partial updates.

**Before:**
```java
UpdateAppRequest request = UpdateAppRequest.builder()
    .enforceUniqueUsernames("no")
    .build();
// Wire: {"enforce_unique_usernames":"no","webhook_url":null,"multi_tenant_enabled":null,...}
// Backend: sets enforce_unique_usernames="no", but ALSO resets webhook_url="", multi_tenant_enabled=false, etc.
```

**After:**
```java
UpdateAppRequest request = UpdateAppRequest.builder()
    .enforceUniqueUsernames("no")
    .build();
// Wire: {"enforce_unique_usernames":"no"}
// Backend: sets enforce_unique_usernames="no", all other fields preserved
```

Collection fields (lists, maps) are still serialized when set (including as empty `[]`/`{}`), so you can continue to send an empty list to clear a list field. Unset collection fields (`null`) are now also omitted.

## Getting Help

- [Stream documentation](https://getstream.io/docs/)
- [GitHub Issues](https://github.com/GetStream/stream-sdk-java/issues)
- [Stream support](https://getstream.io/contact/support/)
