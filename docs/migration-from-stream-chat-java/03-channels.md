# Channels

This guide shows how to migrate channel operations from `stream-chat-java` to `stream-sdk-java`.

## Create or Get a Channel

**Before (stream-chat-java):**

```java
import io.getstream.chat.java.models.Channel;
import io.getstream.chat.java.models.Channel.ChannelRequestObject;
import io.getstream.chat.java.models.User.UserRequestObject;

Channel.getOrCreate("messaging", "general")
    .data(ChannelRequestObject.builder()
        .createdBy(UserRequestObject.builder().id("admin").build())
        .additionalField("description", "General chat")
        .build())
    .request();
```

**After (stream-sdk-java):**

```java
import io.getstream.services.framework.StreamSDKClient;
import io.getstream.models.GetOrCreateChannelRequest;
import io.getstream.models.ChannelInput;
import java.util.Map;

StreamSDKClient client = new StreamSDKClient("your-api-key", "your-api-secret");

client.chat().getOrCreateChannel("messaging", "general",
    GetOrCreateChannelRequest.builder()
        .data(ChannelInput.builder()
            .createdByID("admin")
            .custom(Map.of("description", "General chat"))
            .build())
        .build())
    .execute();
```

**Key changes:**
- `Channel.getOrCreate(type, id)` becomes `client.chat().getOrCreateChannel(type, id, request)`
- `ChannelRequestObject` becomes `ChannelInput`
- `.createdBy(UserRequestObject)` becomes `.createdByID("admin")` (just the user ID)
- `.additionalField()` becomes `.custom(Map.of(...))`

## Create a Channel with Members

**Before (stream-chat-java):**

```java
import io.getstream.chat.java.models.Channel;
import io.getstream.chat.java.models.Channel.ChannelRequestObject;
import io.getstream.chat.java.models.Channel.ChannelMemberRequestObject;
import io.getstream.chat.java.models.User.UserRequestObject;
import java.util.Arrays;

Channel.getOrCreate("messaging", "team-chat")
    .data(ChannelRequestObject.builder()
        .createdBy(UserRequestObject.builder().id("admin").build())
        .members(Arrays.asList(
            ChannelMemberRequestObject.builder().userId("alice").build(),
            ChannelMemberRequestObject.builder().userId("bob").build()))
        .build())
    .request();
```

**After (stream-sdk-java):**

```java
import io.getstream.services.framework.StreamSDKClient;
import io.getstream.models.GetOrCreateChannelRequest;
import io.getstream.models.ChannelInput;
import io.getstream.models.ChannelMemberRequest;
import java.util.List;

StreamSDKClient client = new StreamSDKClient("your-api-key", "your-api-secret");

client.chat().getOrCreateChannel("messaging", "team-chat",
    GetOrCreateChannelRequest.builder()
        .data(ChannelInput.builder()
            .createdByID("admin")
            .members(List.of(
                ChannelMemberRequest.builder().userID("alice").build(),
                ChannelMemberRequest.builder().userID("bob").build()))
            .build())
        .build())
    .execute();
```

**Key changes:**
- `ChannelMemberRequestObject` becomes `ChannelMemberRequest`
- `.userId("alice")` becomes `.userID("alice")`

## Create a Distinct (1:1) Channel

**Before (stream-chat-java):**

```java
import io.getstream.chat.java.models.Channel;
import io.getstream.chat.java.models.Channel.ChannelRequestObject;
import io.getstream.chat.java.models.Channel.ChannelMemberRequestObject;
import java.util.Arrays;

Channel.getOrCreate("messaging")
    .data(ChannelRequestObject.builder()
        .members(Arrays.asList(
            ChannelMemberRequestObject.builder().userId("alice").build(),
            ChannelMemberRequestObject.builder().userId("bob").build()))
        .build())
    .request();
```

**After (stream-sdk-java):**

```java
import io.getstream.services.framework.StreamSDKClient;
import io.getstream.models.GetOrCreateDistinctChannelRequest;
import io.getstream.models.ChannelInput;
import io.getstream.models.ChannelMemberRequest;
import java.util.List;

StreamSDKClient client = new StreamSDKClient("your-api-key", "your-api-secret");

client.chat().getOrCreateDistinctChannel("messaging",
    GetOrCreateDistinctChannelRequest.builder()
        .data(ChannelInput.builder()
            .members(List.of(
                ChannelMemberRequest.builder().userID("alice").build(),
                ChannelMemberRequest.builder().userID("bob").build()))
            .build())
        .build())
    .execute();
```

**Key changes:**
- `Channel.getOrCreate(type)` without an ID becomes `client.chat().getOrCreateDistinctChannel(type, request)`
- Separate method name makes the intent explicit

## Query Channels

**Before (stream-chat-java):**

```java
import io.getstream.chat.java.models.Channel;
import io.getstream.chat.java.models.Channel.ChannelListResponse;
import io.getstream.chat.java.models.User.UserRequestObject;
import io.getstream.chat.java.models.Sort;

ChannelListResponse response = Channel.list()
    .user(UserRequestObject.builder().id("admin").build())
    .filterCondition("type", "messaging")
    .sort(Sort.builder().field("last_message_at").direction(Sort.Direction.DESC).build())
    .limit(10)
    .request();
```

**After (stream-sdk-java):**

```java
import io.getstream.services.framework.StreamSDKClient;
import io.getstream.models.QueryChannelsRequest;
import io.getstream.models.SortParamRequest;
import java.util.List;
import java.util.Map;

StreamSDKClient client = new StreamSDKClient("your-api-key", "your-api-secret");

var response = client.chat().queryChannels(QueryChannelsRequest.builder()
    .userID("admin")
    .filterConditions(Map.of("type", "messaging"))
    .sort(List.of(SortParamRequest.builder()
        .field("last_message_at")
        .direction(-1)
        .build()))
    .limit(10)
    .build())
    .execute();

var channels = response.getData().getChannels();
```

**Key changes:**
- `Channel.list()` becomes `client.chat().queryChannels()`
- `.user(UserRequestObject)` becomes `.userID("admin")`
- Filters and sort are typed objects instead of chained builder calls
- Sort direction uses `-1` (descending) / `1` (ascending) instead of `Sort.Direction` enum

## Update a Channel

**Before (stream-chat-java):**

```java
import io.getstream.chat.java.models.Channel;
import io.getstream.chat.java.models.User.UserRequestObject;
import java.util.Arrays;

Channel.update("messaging", "general")
    .user(UserRequestObject.builder().id("admin").build())
    .addModerators(Arrays.asList("alice"))
    .removeModerators(Arrays.asList("bob"))
    .cooldown(30)
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
        .addModerators(List.of("alice"))
        .demoteModerators(List.of("bob"))
        .cooldown(30)
        .build())
    .execute();
```

**Key changes:**
- `Channel.update(type, id)` becomes `client.chat().updateChannel(type, id, request)`
- `.removeModerators()` becomes `.demoteModerators()`
- No user object required on the request

## Add and Remove Members

**Before (stream-chat-java):**

```java
import io.getstream.chat.java.models.Channel;
import java.util.Arrays;

Channel.update("messaging", "general")
    .addUserIds(Arrays.asList("charlie"))
    .removeUserIds(Arrays.asList("dave"))
    .request();
```

**After (stream-sdk-java):**

```java
import io.getstream.services.framework.StreamSDKClient;
import io.getstream.models.UpdateChannelRequest;
import io.getstream.models.ChannelMemberRequest;
import java.util.List;

StreamSDKClient client = new StreamSDKClient("your-api-key", "your-api-secret");

client.chat().updateChannel("messaging", "general",
    UpdateChannelRequest.builder()
        .addMembers(List.of(
            ChannelMemberRequest.builder().userID("charlie").build()))
        .removeMembers(List.of("dave"))
        .build())
    .execute();
```

**Key changes:**
- `.addUserIds()` becomes `.addMembers()` with `ChannelMemberRequest` objects
- `.removeUserIds()` becomes `.removeMembers()` with a list of user ID strings

## Partial Update a Channel

**Before (stream-chat-java):**

```java
import io.getstream.chat.java.models.Channel;

Channel.partialUpdate("messaging", "general")
    .setValue("topic", "New Topic")
    .unsetValue("old_field")
    .request();
```

**After (stream-sdk-java):**

```java
import io.getstream.services.framework.StreamSDKClient;
import io.getstream.models.UpdateChannelPartialRequest;
import java.util.List;
import java.util.Map;

StreamSDKClient client = new StreamSDKClient("your-api-key", "your-api-secret");

client.chat().updateChannelPartial("messaging", "general",
    UpdateChannelPartialRequest.builder()
        .set(Map.of("topic", "New Topic"))
        .unset(List.of("old_field"))
        .build())
    .execute();
```

**Key changes:**
- `Channel.partialUpdate()` becomes `client.chat().updateChannelPartial()`
- `.setValue(key, val)` becomes `.set(Map.of(key, val))`
- `.unsetValue(key)` becomes `.unset(List.of(key))`

## Query Members

**Before (stream-chat-java):**

```java
import io.getstream.chat.java.models.Channel;
import io.getstream.chat.java.models.Sort;

Channel.queryMembers()
    .type("messaging")
    .id("general")
    .filterCondition("notifications_muted", true)
    .sort(Sort.builder().field("created_at").build())
    .request();
```

**After (stream-sdk-java):**

```java
import io.getstream.services.framework.StreamSDKClient;
import io.getstream.models.QueryMembersRequest;
import io.getstream.models.QueryMembersPayload;
import io.getstream.models.SortParamRequest;
import java.util.List;
import java.util.Map;

StreamSDKClient client = new StreamSDKClient("your-api-key", "your-api-secret");

client.chat().queryMembers(QueryMembersRequest.builder()
    .payload(QueryMembersPayload.builder()
        .type("messaging")
        .id("general")
        .filterConditions(Map.of("notifications_muted", true))
        .sort(List.of(SortParamRequest.builder().field("created_at").build()))
        .build())
    .build())
    .execute();
```

**Key changes:**
- `Channel.queryMembers()` becomes `client.chat().queryMembers()`
- Filter conditions are a `Map` instead of chained calls

## Delete a Channel

**Before (stream-chat-java):**

```java
import io.getstream.chat.java.models.Channel;

Channel.delete("messaging", "general").request();
```

**After (stream-sdk-java):**

```java
import io.getstream.services.framework.StreamSDKClient;
import io.getstream.models.DeleteChannelRequest;

StreamSDKClient client = new StreamSDKClient("your-api-key", "your-api-secret");

// Soft delete
client.chat().deleteChannel("messaging", "general").execute();

// Hard delete
client.chat().deleteChannel("messaging", "general",
    DeleteChannelRequest.builder()
        .hardDelete(true)
        .build())
    .execute();
```

**Key changes:**
- `Channel.delete(type, id)` becomes `client.chat().deleteChannel(type, id)`
- Hard delete is specified via `DeleteChannelRequest` with `.hardDelete(true)`

## Truncate a Channel

**Before (stream-chat-java):**

```java
import io.getstream.chat.java.models.Channel;
import io.getstream.chat.java.models.Message.MessageRequestObject;

Channel.truncate("messaging", "general")
    .skipPush(true)
    .message(MessageRequestObject.builder()
        .text("Channel truncated")
        .userId("admin")
        .build())
    .request();
```

**After (stream-sdk-java):**

```java
import io.getstream.services.framework.StreamSDKClient;
import io.getstream.models.TruncateChannelRequest;
import io.getstream.models.MessageRequest;

StreamSDKClient client = new StreamSDKClient("your-api-key", "your-api-secret");

client.chat().truncateChannel("messaging", "general",
    TruncateChannelRequest.builder()
        .skipPush(true)
        .message(MessageRequest.builder()
            .text("Channel truncated")
            .userID("admin")
            .build())
        .build())
    .execute();
```

**Key changes:**
- `Channel.truncate()` becomes `client.chat().truncateChannel()`
- `MessageRequestObject` becomes `MessageRequest`
