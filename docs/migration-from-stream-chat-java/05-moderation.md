# Moderation

This guide shows how to migrate moderation operations from `stream-chat-java` to `stream-sdk-java`.

## Add Moderators

**Before (stream-chat-java):**

```java
import io.getstream.chat.java.models.Channel;
import java.util.Arrays;

Channel.update("messaging", "general")
    .addModerators(Arrays.asList("alice", "bob"))
    .request();
```

**After (stream-sdk-java):**

```java
import io.getstream.services.framework.StreamSDKClient;
import io.getstream.models.UpdateChannelRequest;
import java.util.List;

StreamSDKClient client = new StreamSDKClient("your-api-key", "your-api-secret");

client.chat().updateChannel("messaging", "general",
    UpdateChannelRequest.builder()
        .addModerators(List.of("alice", "bob"))
        .build())
    .execute();
```

**Key changes:**
- `Channel.update()` becomes `client.chat().updateChannel()`
- Same field name `.addModerators()` on `UpdateChannelRequest`

## Remove Moderators

**Before (stream-chat-java):**

```java
import io.getstream.chat.java.models.Channel;
import java.util.Arrays;

Channel.update("messaging", "general")
    .removeModerators(Arrays.asList("alice"))
    .request();
```

**After (stream-sdk-java):**

```java
import io.getstream.services.framework.StreamSDKClient;
import io.getstream.models.UpdateChannelRequest;
import java.util.List;

StreamSDKClient client = new StreamSDKClient("your-api-key", "your-api-secret");

client.chat().updateChannel("messaging", "general",
    UpdateChannelRequest.builder()
        .demoteModerators(List.of("alice"))
        .build())
    .execute();
```

**Key changes:**
- `.removeModerators()` becomes `.demoteModerators()`

## Ban a User (App-level)

**Before (stream-chat-java):**

```java
import io.getstream.chat.java.models.User;

User.ban()
    .userId("admin")
    .targetUserId("spammer")
    .reason("Spamming")
    .timeout(3600)
    .request();
```

**After (stream-sdk-java):**

```java
import io.getstream.services.framework.StreamSDKClient;
import io.getstream.models.BanRequest;

StreamSDKClient client = new StreamSDKClient("your-api-key", "your-api-secret");

client.moderation().ban(BanRequest.builder()
    .targetUserID("spammer")
    .bannedByID("admin")
    .reason("Spamming")
    .timeout(3600)
    .build())
    .execute();
```

**Key changes:**
- `User.ban()` becomes `client.moderation().ban()`
- `.userId()` (the banner) becomes `.bannedByID()`
- `.targetUserId()` becomes `.targetUserID()`

## Ban a User (Channel-level)

**Before (stream-chat-java):**

```java
import io.getstream.chat.java.models.User;

User.ban()
    .userId("admin")
    .targetUserId("spammer")
    .type("messaging")
    .id("general")
    .reason("Off-topic")
    .request();
```

**After (stream-sdk-java):**

```java
import io.getstream.services.framework.StreamSDKClient;
import io.getstream.models.BanRequest;

StreamSDKClient client = new StreamSDKClient("your-api-key", "your-api-secret");

client.moderation().ban(BanRequest.builder()
    .targetUserID("spammer")
    .bannedByID("admin")
    .channelCid("messaging:general")
    .reason("Off-topic")
    .build())
    .execute();
```

**Key changes:**
- `.type("messaging").id("general")` becomes `.channelCid("messaging:general")` (combined CID format)

## Shadow Ban

**Before (stream-chat-java):**

```java
import io.getstream.chat.java.models.User;

User.shadowBan()
    .userId("admin")
    .targetUserId("troll")
    .request();
```

**After (stream-sdk-java):**

```java
import io.getstream.services.framework.StreamSDKClient;
import io.getstream.models.BanRequest;

StreamSDKClient client = new StreamSDKClient("your-api-key", "your-api-secret");

client.moderation().ban(BanRequest.builder()
    .targetUserID("troll")
    .bannedByID("admin")
    .shadow(true)
    .build())
    .execute();
```

**Key changes:**
- `User.shadowBan()` is no longer a separate method; use `client.moderation().ban()` with `.shadow(true)`

## Unban a User

**Before (stream-chat-java):**

```java
import io.getstream.chat.java.models.User;

User.unban("spammer")
    .createdBy("admin")
    .request();

// Channel-level unban
User.unban("spammer")
    .type("messaging")
    .id("general")
    .createdBy("admin")
    .request();
```

**After (stream-sdk-java):**

```java
import io.getstream.services.framework.StreamSDKClient;
import io.getstream.models.UnbanRequest;

StreamSDKClient client = new StreamSDKClient("your-api-key", "your-api-secret");

// App-level unban
client.moderation().unban(UnbanRequest.builder()
    .targetUserID("spammer")
    .build())
    .execute();

// Channel-level unban
client.moderation().unban(UnbanRequest.builder()
    .targetUserID("spammer")
    .channelCid("messaging:general")
    .build())
    .execute();
```

**Key changes:**
- `User.unban(targetId)` becomes `client.moderation().unban(UnbanRequest)`
- `.type().id()` becomes `.channelCid("type:id")`

## Query Banned Users

**Before (stream-chat-java):**

```java
import io.getstream.chat.java.models.User;

var response = User.queryBanned()
    .filterCondition("username", "john")
    .limit(25)
    .offset(0)
    .request();
```

**After (stream-sdk-java):**

```java
import io.getstream.services.framework.StreamSDKClient;
import io.getstream.models.QueryBannedUsersRequest;
import io.getstream.models.QueryBannedUsersPayload;
import java.util.Map;

StreamSDKClient client = new StreamSDKClient("your-api-key", "your-api-secret");

var response = client.moderation().queryBannedUsers(QueryBannedUsersRequest.builder()
    .payload(QueryBannedUsersPayload.builder()
        .filterConditions(Map.of("username", "john"))
        .limit(25)
        .offset(0)
        .build())
    .build())
    .execute();
```

**Key changes:**
- `User.queryBanned()` becomes `client.moderation().queryBannedUsers()`

## Mute a User

**Before (stream-chat-java):**

```java
import io.getstream.chat.java.models.User;

User.mute()
    .userId("john")
    .targetUserId("noisy-user")
    .timeout(60)
    .request();
```

**After (stream-sdk-java):**

```java
import io.getstream.services.framework.StreamSDKClient;
import io.getstream.models.MuteRequest;
import java.util.List;

StreamSDKClient client = new StreamSDKClient("your-api-key", "your-api-secret");

client.moderation().mute(MuteRequest.builder()
    .userID("john")
    .targetIds(List.of("noisy-user"))
    .timeout(60)
    .build())
    .execute();
```

**Key changes:**
- `User.mute()` becomes `client.moderation().mute()`
- `.targetUserId()` becomes `.targetIds(List.of(...))`, allowing batch muting

## Unmute a User

**Before (stream-chat-java):**

```java
import io.getstream.chat.java.models.User;

User.unmute()
    .userId("john")
    .targetUserId("noisy-user")
    .request();
```

**After (stream-sdk-java):**

```java
import io.getstream.services.framework.StreamSDKClient;
import io.getstream.models.UnmuteRequest;
import java.util.List;

StreamSDKClient client = new StreamSDKClient("your-api-key", "your-api-secret");

client.moderation().unmute(UnmuteRequest.builder()
    .userID("john")
    .targetIds(List.of("noisy-user"))
    .build())
    .execute();
```

**Key changes:**
- `User.unmute()` becomes `client.moderation().unmute()`
- `.targetUserId()` becomes `.targetIds(List.of(...))`

## Method Mapping Summary

| Operation | stream-chat-java | stream-sdk-java |
|-----------|-----------------|-----------------|
| Add moderators | `Channel.update().addModerators()` | `client.chat().updateChannel()` with `.addModerators()` |
| Remove moderators | `Channel.update().removeModerators()` | `client.chat().updateChannel()` with `.demoteModerators()` |
| Ban (app-level) | `User.ban()` | `client.moderation().ban()` |
| Ban (channel-level) | `User.ban().type().id()` | `client.moderation().ban()` with `.channelCid()` |
| Shadow ban | `User.shadowBan()` | `client.moderation().ban()` with `.shadow(true)` |
| Unban | `User.unban()` | `client.moderation().unban()` |
| Mute | `User.mute()` | `client.moderation().mute()` |
| Unmute | `User.unmute()` | `client.moderation().unmute()` |
| Query banned | `User.queryBanned()` | `client.moderation().queryBannedUsers()` |
