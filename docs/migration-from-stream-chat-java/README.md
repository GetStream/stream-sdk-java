# Migrating from stream-chat-java to stream-sdk-java

## Why Migrate?

- `stream-sdk-java` is the actively developed, long-term-supported SDK
- Covers Chat, Video, Moderation, and Feeds in a single package
- Strongly typed models generated from the official OpenAPI spec
- `stream-chat-java` will enter maintenance mode (critical fixes only)

## Key Differences

| Aspect | stream-chat-java | stream-sdk-java |
|--------|-----------------|-----------------|
| Package | `io.getstream:stream-chat-java` | `io.getstream:stream-sdk-java` |
| Client class | `DefaultClient` (singleton) | `StreamSDKClient` (instance) |
| API pattern | Static methods on model classes (`User.upsert()`, `Channel.getOrCreate()`) | Instance methods on sub-clients (`client.chat()`, `client.moderation()`) |
| Models | Builder classes nested in model files (`UserRequestObject`, `ChannelRequestObject`) | Standalone generated request classes (`UserRequest`, `ChannelInput`) |
| Custom fields | `.additionalField(key, value)` | `.custom(Map.of(key, value))` |
| Execution | `.request()` | `.execute()` |
| Tokens | `User.createToken(userId, expiry, issuedAt)` | `client.tokenBuilder().createToken(userId, validitySeconds)` |
| Env vars | `STREAM_KEY`, `STREAM_SECRET` | `STREAM_API_KEY`, `STREAM_API_SECRET` |

## Quick Example

**Before (stream-chat-java):**

```java
import io.getstream.chat.java.services.framework.DefaultClient;
import io.getstream.chat.java.models.Message;
import io.getstream.chat.java.models.Message.MessageRequestObject;
import java.util.Properties;

Properties props = new Properties();
props.put("io.getstream.chat.apiKey", "your-api-key");
props.put("io.getstream.chat.apiSecret", "your-api-secret");
DefaultClient client = new DefaultClient(props);
DefaultClient.setInstance(client);

Message.send("messaging", "general")
    .message(MessageRequestObject.builder()
        .text("Hello!")
        .userId("john")
        .build())
    .request();
```

**After (stream-sdk-java):**

```java
import io.getstream.services.framework.StreamSDKClient;
import io.getstream.models.SendMessageRequest;
import io.getstream.models.MessageRequest;

StreamSDKClient client = new StreamSDKClient("your-api-key", "your-api-secret");

client.chat().sendMessage("messaging", "general",
    SendMessageRequest.builder()
        .message(MessageRequest.builder()
            .text("Hello!")
            .userID("john")
            .build())
        .build())
    .execute();
```

## Migration Guides by Topic

| # | Topic | File |
|---|-------|------|
| 1 | [Setup and Authentication](01-setup-and-auth.md) | Client init, tokens |
| 2 | [Users](02-users.md) | Upsert, query, update, delete |
| 3 | [Channels](03-channels.md) | Create, query, members, update |
| 4 | [Messages and Reactions](04-messages-and-reactions.md) | Send, reply, react |
| 5 | [Moderation](05-moderation.md) | Ban, mute, moderators |
| 6 | [Devices](06-devices.md) | Push device management |

## Notes

- `stream-chat-java` is not going away. Your existing integration will keep working.
- The new SDK uses Lombok builders for all request model creation.
- All API calls return `StreamRequest<T>` objects; call `.execute()` to run them.
- If you find a use case missing from this guide, please open an issue.
