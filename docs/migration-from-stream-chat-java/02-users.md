# Users

This guide shows how to migrate user operations from `stream-chat-java` to `stream-sdk-java`.

## Upsert User

**Before (stream-chat-java):**

```java
import io.getstream.chat.java.models.User;
import io.getstream.chat.java.models.User.UserRequestObject;

User.upsert()
    .user(UserRequestObject.builder()
        .id("john")
        .name("John Doe")
        .role("admin")
        .additionalField("country", "US")
        .build())
    .request();
```

**After (stream-sdk-java):**

```java
import io.getstream.services.framework.StreamSDKClient;
import io.getstream.models.UpdateUsersRequest;
import io.getstream.models.UserRequest;
import java.util.Map;

StreamSDKClient client = new StreamSDKClient("your-api-key", "your-api-secret");

client.updateUsers(UpdateUsersRequest.builder()
    .users(Map.of("john", UserRequest.builder()
        .id("john")
        .name("John Doe")
        .role("admin")
        .custom(Map.of("country", "US"))
        .build()))
    .build())
    .execute();
```

**Key changes:**
- `User.upsert().user(...)` becomes `client.updateUsers(UpdateUsersRequest)` with a map of users keyed by ID
- `UserRequestObject` becomes `UserRequest`
- `additionalField()` becomes `custom(Map.of(...))`
- `.request()` becomes `.execute()`

## Batch Upsert Users

**Before (stream-chat-java):**

```java
import io.getstream.chat.java.models.User;
import io.getstream.chat.java.models.User.UserRequestObject;

User.upsert()
    .user(UserRequestObject.builder().id("alice").name("Alice").build())
    .user(UserRequestObject.builder().id("bob").name("Bob").build())
    .request();
```

**After (stream-sdk-java):**

```java
import io.getstream.services.framework.StreamSDKClient;
import io.getstream.models.UpdateUsersRequest;
import io.getstream.models.UserRequest;
import java.util.Map;

StreamSDKClient client = new StreamSDKClient("your-api-key", "your-api-secret");

client.updateUsers(UpdateUsersRequest.builder()
    .users(Map.of(
        "alice", UserRequest.builder().id("alice").name("Alice").build(),
        "bob", UserRequest.builder().id("bob").name("Bob").build()))
    .build())
    .execute();
```

**Key changes:**
- Multiple `.user()` calls become a single map passed to `UpdateUsersRequest`

## Query Users

**Before (stream-chat-java):**

```java
import io.getstream.chat.java.models.User;
import io.getstream.chat.java.models.User.UserListResponse;

UserListResponse response = User.list()
    .filterCondition("id", "john")
    .limit(10)
    .offset(0)
    .request();
```

**After (stream-sdk-java):**

```java
import io.getstream.services.framework.StreamSDKClient;
import io.getstream.models.QueryUsersRequest;
import io.getstream.models.QueryUsersPayload;
import io.getstream.models.QueryUsersResponse;
import java.util.Map;

StreamSDKClient client = new StreamSDKClient("your-api-key", "your-api-secret");

var response = client.queryUsers(QueryUsersRequest.builder()
    .payload(QueryUsersPayload.builder()
        .filterConditions(Map.of("id", "john"))
        .limit(10)
        .offset(0)
        .build())
    .build())
    .execute();

var users = response.getData().getUsers();
```

**Key changes:**
- `User.list()` with chained filter methods becomes `client.queryUsers()` with `QueryUsersRequest`
- Filters are a `Map<String, Object>` instead of chained `.filterCondition()` calls
- Response data is accessed via `.getData().getUsers()` instead of direct response fields

## Partial Update User

**Before (stream-chat-java):**

```java
import io.getstream.chat.java.models.User;
import io.getstream.chat.java.models.User.UserPartialUpdateRequestObject;

User.partialUpdate()
    .user(UserPartialUpdateRequestObject.builder()
        .id("john")
        .setValue("role", "admin")
        .unsetValue("obsolete_field")
        .build())
    .request();
```

**After (stream-sdk-java):**

```java
import io.getstream.services.framework.StreamSDKClient;
import io.getstream.models.UpdateUsersPartialRequest;
import io.getstream.models.UpdateUserPartialRequest;
import java.util.List;
import java.util.Map;

StreamSDKClient client = new StreamSDKClient("your-api-key", "your-api-secret");

client.updateUsersPartial(UpdateUsersPartialRequest.builder()
    .users(List.of(UpdateUserPartialRequest.builder()
        .id("john")
        .set(Map.of("role", "admin"))
        .unset(List.of("obsolete_field"))
        .build()))
    .build())
    .execute();
```

**Key changes:**
- `User.partialUpdate().user(...)` becomes `client.updateUsersPartial()` with a list of `UpdateUserPartialRequest`
- `.setValue(key, value)` becomes `.set(Map.of(key, value))`
- `.unsetValue(key)` becomes `.unset(List.of(key))`

## Deactivate User

**Before (stream-chat-java):**

```java
import io.getstream.chat.java.models.User;

User.deactivate("john")
    .createdById("admin-user")
    .markMessagesDeleted(true)
    .request();
```

**After (stream-sdk-java):**

```java
import io.getstream.services.framework.StreamSDKClient;
import io.getstream.models.DeactivateUserRequest;

StreamSDKClient client = new StreamSDKClient("your-api-key", "your-api-secret");

client.deactivateUser("john", DeactivateUserRequest.builder()
    .createdByID("admin-user")
    .markMessagesDeleted(true)
    .build())
    .execute();
```

**Key changes:**
- `User.deactivate(userId)` becomes `client.deactivateUser(userId, request)`
- Builder options become fields on `DeactivateUserRequest`

## Reactivate User

**Before (stream-chat-java):**

```java
import io.getstream.chat.java.models.User;

User.reactivate("john")
    .createdById("admin-user")
    .request();
```

**After (stream-sdk-java):**

```java
import io.getstream.services.framework.StreamSDKClient;
import io.getstream.models.ReactivateUserRequest;

StreamSDKClient client = new StreamSDKClient("your-api-key", "your-api-secret");

client.reactivateUser("john", ReactivateUserRequest.builder()
    .createdByID("admin-user")
    .build())
    .execute();
```

**Key changes:**
- `User.reactivate(userId)` becomes `client.reactivateUser(userId, request)`

## Delete Users

**Before (stream-chat-java):**

```java
import io.getstream.chat.java.models.User;
import io.getstream.chat.java.models.User.DeleteStrategy;
import java.util.Arrays;

// Single user delete
User.delete("john")
    .markMessagesDeleted(true)
    .hardDelete(false)
    .request();

// Batch delete
User.deleteMany(Arrays.asList("alice", "bob"))
    .setDeleteStrategy(DeleteStrategy.HARD)
    .request();
```

**After (stream-sdk-java):**

```java
import io.getstream.services.framework.StreamSDKClient;
import io.getstream.models.DeleteUsersRequest;
import java.util.List;

StreamSDKClient client = new StreamSDKClient("your-api-key", "your-api-secret");

// Single or batch delete (same API)
client.deleteUsers(DeleteUsersRequest.builder()
    .userIds(List.of("john"))
    .user("hard")
    .messages("hard")
    .conversations("hard")
    .build())
    .execute();
```

**Key changes:**
- `User.delete()` and `User.deleteMany()` are unified into `client.deleteUsers()`
- Delete strategy is specified per resource type: `user`, `messages`, `conversations` (each accepts `"hard"` or `"soft"`)
- Always uses a list of user IDs, even for a single user
