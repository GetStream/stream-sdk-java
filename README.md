# Official Java SDK for [Stream](https://getstream.io/)

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
    implementation "io.getstream:stream-sdk-java:$stream_version"
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
import io.getstream.models.UserRequest;

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

client.common().UpdateUsers(updateUsersRequest).request();

// Create a JWT token for the user to connect client-side (e.g. browser/mobile app)
// token expires in 24 hours
String token = createToken(userId, 24*60*60);
```

// Token does not expire
String token = createToken(userId);
```

### Video API - Calls

To create a video call, use the `client.video.call` method:

```java
var testCall = new Call("default", UUID.randomUUID().toString());

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

## License

This project is licensed under the [MIT License](LICENSE).

## Contributing

Contributions are welcome! Please read the [contributing guidelines](CONTRIBUTING.md) to get started.
