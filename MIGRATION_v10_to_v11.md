# Migrating from v10 to v11

v11 regenerates the client from protocol tag `openapi-v239.93.2` (chat **v239.93.2**, FLU-1105). **HTTP JSON is unchanged.** Most request and response fields stay `String`. The compile breaks are the ones below.

## Installation

```xml
<dependency>
    <groupId>io.getstream</groupId>
    <artifactId>stream-sdk-java</artifactId>
    <version>11.0.0</version>
</dependency>
```

```groovy
implementation 'io.getstream:stream-sdk-java:11.0.0'
```

## Named enum constants

New types such as `PushLevel`, `VotingVisibility`, `BlocklistBehavior`, and `ChannelModerationBehavior` are classes of `public static final String` constants. Fields that use them are still `String`, so existing string assignments keep compiling.

```java
request.setPushLevel(PushLevel.ALL);
request.setAutomodBehavior(ChannelModerationBehavior.FLAG);
```

## Own capabilities

`OwnCapability`, `ChannelOwnCapability`, and `FeedOwnCapability` are no longer wrapper objects. They are `String` constants, and the list fields are `List<String>`.

```java
// v10
List<OwnCapability> caps = channel.getOwnCapabilities();
if (caps.contains(OwnCapability.JOIN_CALL)) { }

// v11
List<String> caps = channel.getOwnCapabilities();
if (caps.contains(OwnCapability.JOIN_CALL)) { }
```

The same change applies to channel and feed capability lists, and to call responses that returned `List<OwnCapability>` (`GetCallResponse`, `GetOrCreateCallResponse`, `UpdateCallResponse`, `QueryCallParticipantsResponse`, `CallStateResponseFields`, `UpdatedCallPermissionsEvent`).

`OwnCapability.JOIN_CALL.toString()` is no longer valid. The constant is already a `String`.

## Campaigns

`updateCampaign` returns `UpdateCampaignResponse`. `stopCampaign` returns `StopCampaignResponse`. The campaign itself is nested.

```java
// v10
CampaignResponse campaign = client.chat().updateCampaign(id, request).execute().getData();

// v11
CampaignResponse campaign =
    client.chat().updateCampaign(id, request).execute().getData().getCampaign();
```

`stopCampaign` is the same shape: `StopCampaignResponse.getCampaign()`.

## Feeds retention policies

`GetFeedsRetentionPolicyResponse.getPolicies()` is `List<FeedsRetentionPolicy>`. `SetFeedsRetentionPolicyResponse.getPolicy()` is `FeedsRetentionPolicy`. Both were `RetentionPolicy`.

## Moderation check response

`CheckResponse` no longer has `intentMatchedTopic`, `intentScore`, `intentScoreThreshold`, or `intentWouldFire`. Scores are `List<IntentScoreResponse> getIntentScores()`.

## Reaction timestamps

`EnrichedReaction.getCreatedAt()` and `getUpdatedAt()` are `String`. They were `Time`. `Time` is removed.

## Removed types

These classes are gone:

- `Time`
- `IntentBufferedItem`, `IntentDetectedEvent`
- `VideoEndCallRequestPayload`, `VideoKickUserRequestPayload`

`moderation.intent_detected` is no longer a known webhook type. It arrives as `UnknownEvent`.

## HTTP JSON

The server still sends and accepts the same strings (`"join-call"`, `"all"`, `"flag"`). Stay on **10.5.0** until you are ready to fix the compile errors above.
