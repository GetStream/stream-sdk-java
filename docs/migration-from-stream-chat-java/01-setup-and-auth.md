# Setup and Authentication

This guide shows how to migrate setup and authentication code from `stream-chat-java` to `stream-sdk-java`.

## Installation

**Before (stream-chat-java):**

Maven:
```xml
<dependency>
    <groupId>io.getstream</groupId>
    <artifactId>stream-chat-java</artifactId>
    <version>$streamVersion</version>
</dependency>
```

Gradle:
```groovy
implementation 'io.getstream:stream-chat-java:$streamVersion'
```

**After (stream-sdk-java):**

Maven:
```xml
<dependency>
    <groupId>io.getstream</groupId>
    <artifactId>stream-sdk-java</artifactId>
    <version>$streamVersion</version>
</dependency>
```

Gradle:
```groovy
implementation 'io.getstream:stream-sdk-java:$streamVersion'
```

**Key changes:**
- Artifact ID changes from `stream-chat-java` to `stream-sdk-java`

## Client Initialization

**Before (stream-chat-java):**

```java
import io.getstream.chat.java.services.framework.DefaultClient;
import java.util.Properties;

Properties props = new Properties();
props.put("io.getstream.chat.apiKey", "your-api-key");
props.put("io.getstream.chat.apiSecret", "your-api-secret");

DefaultClient client = new DefaultClient(props);
DefaultClient.setInstance(client);
```

**After (stream-sdk-java):**

```java
import io.getstream.services.framework.StreamSDKClient;

StreamSDKClient client = new StreamSDKClient("your-api-key", "your-api-secret");
```

**Key changes:**
- `DefaultClient` with singleton pattern becomes `StreamSDKClient` with direct instantiation
- Property names change from `io.getstream.chat.*` to `io.getstream.*`
- Environment variables change from `STREAM_KEY`/`STREAM_SECRET` to `STREAM_API_KEY`/`STREAM_API_SECRET`

## Client Initialization with Environment Variables

**Before (stream-chat-java):**

```java
import io.getstream.chat.java.services.framework.DefaultClient;

// Reads STREAM_KEY and STREAM_SECRET from environment
DefaultClient client = new DefaultClient();
DefaultClient.setInstance(client);
```

**After (stream-sdk-java):**

```java
import io.getstream.services.framework.StreamSDKClient;

// Reads STREAM_API_KEY and STREAM_API_SECRET from environment
StreamSDKClient client = new StreamSDKClient();
```

**Key changes:**
- Environment variable names change: `STREAM_KEY` to `STREAM_API_KEY`, `STREAM_SECRET` to `STREAM_API_SECRET`
- No singleton pattern required; use the client instance directly

## Token Generation

**Before (stream-chat-java):**

```java
import io.getstream.chat.java.models.User;
import java.util.Date;
import java.util.Calendar;

// Token with no expiry
String token = User.createToken("user-id", null, null);

// Token with expiry
Calendar cal = Calendar.getInstance();
cal.add(Calendar.HOUR, 24);
String tokenWithExpiry = User.createToken("user-id", cal.getTime(), null);
```

**After (stream-sdk-java):**

```java
import io.getstream.services.framework.StreamSDKClient;

StreamSDKClient client = new StreamSDKClient("your-api-key", "your-api-secret");

// Token with no expiry
String token = client.tokenBuilder().createToken("user-id");

// Token with expiry (validity in seconds)
String tokenWithExpiry = client.tokenBuilder().createToken("user-id", 24 * 60 * 60);
```

**Key changes:**
- Token creation moves from static `User.createToken()` to `client.tokenBuilder().createToken()`
- Expiry is specified as seconds (integer) instead of a `Date` object
- No need to pass `issuedAt` separately

## Sub-clients

**Before (stream-chat-java):**

```java
import io.getstream.chat.java.models.User;
import io.getstream.chat.java.models.Channel;
import io.getstream.chat.java.models.Message;

// All operations are static methods on model classes
User.upsert().user(...).request();
Channel.getOrCreate("messaging", "general").request();
Message.send("messaging", "general").message(...).request();
```

**After (stream-sdk-java):**

```java
import io.getstream.services.framework.StreamSDKClient;

StreamSDKClient client = new StreamSDKClient("your-api-key", "your-api-secret");

// Operations are on sub-client interfaces
client.updateUsers(...).execute();         // Common (user operations)
client.chat().getOrCreateChannel(...).execute();  // Chat
client.chat().sendMessage(...).execute();         // Chat
client.moderation().ban(...).execute();           // Moderation
client.video().getOrCreateCall(...).execute();    // Video
```

**Key changes:**
- Static methods on model classes (`User.upsert()`, `Channel.getOrCreate()`) become instance methods on sub-clients (`client.chat()`, `client.moderation()`, `client.video()`)
- User and device operations are on the root client (Common interface)
- `.request()` becomes `.execute()` to run the API call
