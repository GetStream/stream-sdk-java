# Messages and Reactions

This guide shows how to migrate message and reaction operations from `stream-chat-java` to `stream-sdk-java`.

## Send a Message

**Before (stream-chat-java):**

```java
import io.getstream.chat.java.models.Message;
import io.getstream.chat.java.models.Message.MessageRequestObject;

Message.send("messaging", "general")
    .message(MessageRequestObject.builder()
        .text("Hello, world!")
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
            .text("Hello, world!")
            .userID("john")
            .build())
        .build())
    .execute();
```

**Key changes:**
- `Message.send(type, id)` becomes `client.chat().sendMessage(type, id, request)`
- `MessageRequestObject` becomes `MessageRequest`
- `.userId()` becomes `.userID()`

## Send a Message with Options

**Before (stream-chat-java):**

```java
import io.getstream.chat.java.models.Message;
import io.getstream.chat.java.models.Message.MessageRequestObject;

Message.send("messaging", "general")
    .message(MessageRequestObject.builder()
        .text("Silent notification")
        .userId("john")
        .build())
    .skipPush(true)
    .skipEnrichUrl(true)
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
            .text("Silent notification")
            .userID("john")
            .build())
        .skipPush(true)
        .skipEnrichUrl(true)
        .build())
    .execute();
```

**Key changes:**
- Options like `skipPush` and `skipEnrichUrl` go on `SendMessageRequest` (the outer wrapper), not chained on the builder

## Send a Thread Reply

**Before (stream-chat-java):**

```java
import io.getstream.chat.java.models.Message;
import io.getstream.chat.java.models.Message.MessageRequestObject;

Message.send("messaging", "general")
    .message(MessageRequestObject.builder()
        .text("This is a reply")
        .userId("john")
        .parentId("parent-message-id")
        .showInChannel(true)
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
            .text("This is a reply")
            .userID("john")
            .parentID("parent-message-id")
            .showInChannel(true)
            .build())
        .build())
    .execute();
```

**Key changes:**
- `.parentId()` becomes `.parentID()`

## Get a Message

**Before (stream-chat-java):**

```java
import io.getstream.chat.java.models.Message;

var response = Message.get("message-id")
    .showDeletedMessages(true)
    .request();
```

**After (stream-sdk-java):**

```java
import io.getstream.services.framework.StreamSDKClient;
import io.getstream.models.GetMessageRequest;

StreamSDKClient client = new StreamSDKClient("your-api-key", "your-api-secret");

var response = client.chat().getMessage("message-id",
    GetMessageRequest.builder()
        .showDeletedMessage(true)
        .build())
    .execute();

var message = response.getData().getMessage();
```

**Key changes:**
- `Message.get(id)` becomes `client.chat().getMessage(id, request)`
- Response accessed via `.getData().getMessage()`

## Update a Message

**Before (stream-chat-java):**

```java
import io.getstream.chat.java.models.Message;
import io.getstream.chat.java.models.Message.MessageRequestObject;

Message.update("message-id")
    .message(MessageRequestObject.builder()
        .text("Updated text")
        .userId("john")
        .build())
    .request();
```

**After (stream-sdk-java):**

```java
import io.getstream.services.framework.StreamSDKClient;
import io.getstream.models.UpdateMessageRequest;
import io.getstream.models.MessageRequest;

StreamSDKClient client = new StreamSDKClient("your-api-key", "your-api-secret");

client.chat().updateMessage("message-id",
    UpdateMessageRequest.builder()
        .message(MessageRequest.builder()
            .text("Updated text")
            .userID("john")
            .build())
        .build())
    .execute();
```

**Key changes:**
- `Message.update(id)` becomes `client.chat().updateMessage(id, request)`

## Partial Update a Message

**Before (stream-chat-java):**

```java
import io.getstream.chat.java.models.Message;

Message.partialUpdate("message-id")
    .setValue("custom_field", "new value")
    .unsetValue("old_field")
    .request();
```

**After (stream-sdk-java):**

```java
import io.getstream.services.framework.StreamSDKClient;
import io.getstream.models.UpdateMessagePartialRequest;
import java.util.List;
import java.util.Map;

StreamSDKClient client = new StreamSDKClient("your-api-key", "your-api-secret");

client.chat().updateMessagePartial("message-id",
    UpdateMessagePartialRequest.builder()
        .set(Map.of("custom_field", "new value"))
        .unset(List.of("old_field"))
        .build())
    .execute();
```

**Key changes:**
- `Message.partialUpdate(id)` becomes `client.chat().updateMessagePartial(id, request)`
- `.setValue()`/`.unsetValue()` becomes `.set(Map)`/`.unset(List)`

## Delete a Message

**Before (stream-chat-java):**

```java
import io.getstream.chat.java.models.Message;

// Soft delete
Message.delete("message-id").request();

// Hard delete
Message.hardDelete("message-id").request();

// Delete by specific user
Message.delete("message-id")
    .deletedBy("admin")
    .request();
```

**After (stream-sdk-java):**

```java
import io.getstream.services.framework.StreamSDKClient;
import io.getstream.models.DeleteMessageRequest;

StreamSDKClient client = new StreamSDKClient("your-api-key", "your-api-secret");

// Soft delete
client.chat().deleteMessage("message-id").execute();

// Hard delete
client.chat().deleteMessage("message-id",
    DeleteMessageRequest.builder()
        .hard(true)
        .build())
    .execute();

// Delete by specific user
client.chat().deleteMessage("message-id",
    DeleteMessageRequest.builder()
        .deletedBy("admin")
        .build())
    .execute();
```

**Key changes:**
- `Message.delete()`/`Message.hardDelete()` are unified into `client.chat().deleteMessage()` with optional `DeleteMessageRequest`
- Hard delete is a boolean flag instead of a separate method

## Get Replies

**Before (stream-chat-java):**

```java
import io.getstream.chat.java.models.Message;

var response = Message.getReplies("parent-message-id")
    .limit(25)
    .request();
```

**After (stream-sdk-java):**

```java
import io.getstream.services.framework.StreamSDKClient;
import io.getstream.models.GetRepliesRequest;

StreamSDKClient client = new StreamSDKClient("your-api-key", "your-api-secret");

var response = client.chat().getReplies("parent-message-id",
    GetRepliesRequest.builder()
        .limit(25)
        .build())
    .execute();

var replies = response.getData().getMessages();
```

**Key changes:**
- `Message.getReplies(parentId)` becomes `client.chat().getReplies(parentId, request)`

## Send a Reaction

**Before (stream-chat-java):**

```java
import io.getstream.chat.java.models.Reaction;
import io.getstream.chat.java.models.Reaction.ReactionRequestObject;
import io.getstream.chat.java.models.User.UserRequestObject;

Reaction.send("message-id")
    .reaction(ReactionRequestObject.builder()
        .type("like")
        .user(UserRequestObject.builder().id("john").build())
        .build())
    .enforceUnique(true)
    .request();
```

**After (stream-sdk-java):**

```java
import io.getstream.services.framework.StreamSDKClient;
import io.getstream.models.SendReactionRequest;
import io.getstream.models.ReactionRequest;

StreamSDKClient client = new StreamSDKClient("your-api-key", "your-api-secret");

client.chat().sendReaction("message-id",
    SendReactionRequest.builder()
        .reaction(ReactionRequest.builder()
            .type("like")
            .userID("john")
            .build())
        .enforceUnique(true)
        .build())
    .execute();
```

**Key changes:**
- `Reaction.send(messageId)` becomes `client.chat().sendReaction(messageId, request)`
- `.user(UserRequestObject)` becomes `.userID("john")` on `ReactionRequest`
- `ReactionRequestObject` becomes `ReactionRequest`

## List Reactions

**Before (stream-chat-java):**

```java
import io.getstream.chat.java.models.Reaction;

var response = Reaction.list("message-id")
    .limit(25)
    .offset(0)
    .request();
```

**After (stream-sdk-java):**

```java
import io.getstream.services.framework.StreamSDKClient;
import io.getstream.models.GetReactionsRequest;

StreamSDKClient client = new StreamSDKClient("your-api-key", "your-api-secret");

var response = client.chat().getReactions("message-id",
    GetReactionsRequest.builder()
        .limit(25)
        .offset(0)
        .build())
    .execute();

var reactions = response.getData().getReactions();
```

**Key changes:**
- `Reaction.list(messageId)` becomes `client.chat().getReactions(messageId, request)`

## Delete a Reaction

**Before (stream-chat-java):**

```java
import io.getstream.chat.java.models.Reaction;

Reaction.delete("message-id", "like")
    .userId("john")
    .request();
```

**After (stream-sdk-java):**

```java
import io.getstream.services.framework.StreamSDKClient;
import io.getstream.models.DeleteReactionRequest;

StreamSDKClient client = new StreamSDKClient("your-api-key", "your-api-secret");

client.chat().deleteReaction("message-id", "like",
    DeleteReactionRequest.builder()
        .userID("john")
        .build())
    .execute();
```

**Key changes:**
- `Reaction.delete(messageId, type)` becomes `client.chat().deleteReaction(messageId, type, request)`
- `.userId()` moves into `DeleteReactionRequest` as `.userID()`
