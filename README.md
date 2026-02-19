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
> Positional constructor usage is not supported and may break across SDK updates as parameter order is not guaranteed.

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
