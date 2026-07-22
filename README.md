# Official Java SDK for [Stream](https://getstream.io/)

[![Build](https://github.com/GetStream/stream-sdk-java/actions/workflows/ci.yml/badge.svg)](https://github.com/GetStream/stream-sdk-java/actions/workflows/ci.yml) ![Maven Central Version](https://img.shields.io/maven-central/v/io.getstream/stream-sdk-java) ![Java Version](https://img.shields.io/badge/Java-17%2B-orange)


Check out our:

- ⭐ [Chat API](https://getstream.io/chat/)
- 📱 [Video API](https://getstream.io/video/)
- 🔔 [Activity Feeds](https://getstream.io/activity-feeds/)

## Features

- Video call creation and management
- Chat session creation and management
- Token generation for user authentication

## Installation

```gradle
dependencies {
    implementation("io.getstream:stream-sdk-java:$streamVersion")
}
```

## Migrating from stream-chat-java?

If you are currently using [`stream-chat-java`](https://github.com/GetStream/stream-chat-java), we have a detailed migration guide with side-by-side code examples for common Chat use cases. See the [Migration Guide](docs/migration-from-stream-chat-java/README.md).

## ✨ Getting started

### Configuration

To configure the SDK you need to provide required properties.

| Property               | ENV                | Default                      | Required |
| ---------------------- |--------------------|------------------------------| -------- |
| io.getstream.apiKey    | STREAM_API_KEY     | -                            | Yes      |
| io.getstream.apiSecret | STREAM_API_SECRET  | -                            | Yes      |
| io.getstream.timeout   | STREAM_API_TIMEOUT | 10000                          | No       |

### Users and Authentication

```java
import io.getstream.services.frameworks.StreamSDKClient;
import io.getstream.models.UserRequest;

var client = new StreamSDKClient("apiKey", "apiSecret");

// sync two users using the UpdateUsers method, both users will get inserted or updated
List<UserRequest> userRequests =
    List.of(
        UserRequest.builder()
            .id("tommaso-id")
            .name("tommaso")
            .role("admin")
            .custom(Map.of("country", "NL"))
            .build(),
        UserRequest.builder()
            .id("thierry-id")
            .name("thierry")
            .custom(Map.of("country", "US"))
            .build());

UpdateUsersRequest updateUsersRequest =
    UpdateUsersRequest.builder()
        .users(userRequests.stream().collect(Collectors.toMap(UserRequest::getId, x -> x)))
        .build();

client.updateUsers(updateUsersRequest).execute();

// Create a JWT token for the user to connect client-side (e.g. browser/mobile app)
// token expires in 24 hours
client.tokenBuilder().createToken("john", 24 * 60 * 60);
```


### Video API - Calls

To create a video call, use the `client.video.call` method:

```java
var testCall = client.video().call("default", UUID.randomUUID().toString());

// create call if it doesn't exist or get the existing one
call.getOrCreate(
    GetOrCreateCallRequest.builder()
        .data(
            CallRequest.builder()
                .createdByID("sacha")
                .members(members)
                .custom(Map.of("color", "blue"))
                .build())
        .build());
```

> **Note:** When constructing models, always use the **builder pattern** (e.g. `UserRequest.builder().id("id").build()`).
> While some generated models expose positional constructors (for example via Lombok's `@AllArgsConstructor`), their parameter order is not part of the public API and may change between releases; using positional constructors is therefore strongly discouraged and may break across SDK updates.

### Logging

The SDK emits structured log events through [SLF4J](https://www.slf4j.org/) (dependency `org.slf4j:slf4j-api`). Inject your own SLF4J `Logger` via `StreamClientOptions.setLogger(...)`. When no logger is injected the SDK logs to a no-op logger, so nothing is emitted unless you opt in. The SDK never changes the logger's level; that stays entirely under your control through your SLF4J binding.

```java
org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger("io.getstream");
var options = new StreamClientOptions().setLogger(logger);
var client = new StreamSDKClient("apiKey", "apiSecret", options);
```

Four events are emitted:

| Event | Level | When |
| ----- | ----- | ---- |
| `client.initialized` | INFO | once, at client construction (SDK name/version and the effective client config) |
| `http.request.sent` | DEBUG | before each request (method, path, query) |
| `http.response.received` | DEBUG | after any response, including 4xx/5xx (status code, body size, duration) |
| `http.request.failed` | ERROR | transport failure only, when no HTTP response was received (error type, message, duration) |

Redaction is mandatory and cannot be disabled: query values for `api_key`, `api_secret` and `token` are replaced with `<redacted>`, and the top-level JSON body keys `api_secret`, `token` and `password` are redacted. The events never log request/response headers.

Request and response bodies are **not** logged by default. Call `StreamClientOptions.setLogBodies(true)` to opt in (secret body keys are still redacted); doing so emits a one-time warning at construction. Do not enable body logging in production unless you accept the risk of logging sensitive payloads.

> **Deprecated:** the older `HttpLoggingInterceptor` is deprecated in favour of these SLF4J events. It is kept for backward compatibility and now redacts secret headers and secret body keys in its own output.

## Development

To run tests, create the `local.properties` file using the `local.properties.example` and adjust it to have valid API credentials:

```sh
cp local.properties.example local.properties
```

Then run the tests:

```sh
 ./gradlew test
```

Format the code:

```sh
./gradlew spotlessApply
```

### Generate code from spec

To regenerate the Java source from OpenAPI, just run the `./generate.sh` script from this repo.

> [!NOTE]
> Code generation currently relies on tooling that is not publicly available, only Stream devs can regenerate SDK source code from the OpenAPI spec.

## Contributing

Contributions are welcome! Please read the [contributing guidelines](CONTRIBUTING.md) to get started.
