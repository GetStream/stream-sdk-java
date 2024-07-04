# Official Java SDK for [Stream](https://getstream.io/)

> [!NOTE]
> Not to be confused with the [Feeds SDK](https://github.com/GetStream/stream-java) or [Chat SDK](https://github.com/GetStream/stream-chat-java).

## Features

- Video call creation and management
- Token generation for user authentication

## Installation using gradle

```gradle
dependencies {
    implementation "io.getstream:stream-sdk-java:$stream_version"
}
```

## ✨ Getting started

### Configuration

To configure the SDK you need to provide required properties.

| Property               | ENV                 | Default                        | Required |
| ---------------------- | ------------------- | ------------------------------ | -------- |
| io.getstream.apiKey    | STREAM_KEY          | -                              | Yes      |
| io.getstream.apiSecret | STREAM_SECRET       | -                              | Yes      |
| io.getstream.timeout   | STREAM_CHAT_TIMEOUT | 10000                          | No       |
| io.getstream.url       | STREAM_CHAT_URL     | https://chat.stream-io-api.com | No       |

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

new Common.UpdateUsers(updateUsersRequest).request();

// Create a JWT token for the user to connect client-side (e.g. browser/mobile app)
String userId = RandomStringUtils.randomAlphabetic(10);
GregorianCalendar expiryTime = new GregorianCalendar();
// Token expires in 2 weeks
expiryTime.add(GregorianCalendar.WEEK_OF_YEAR, 2);
String token = createToken(userId, expiryTime.getTime(), null);

// Token does not expire
String token = createToken(userId, null, null);
```

### Video API - Calls

To create a video call, use the `client.video.call` method:

```java
var callRequest =
    GetOrCreateCallRequest.builder()
        .data(
            CallRequest.builder()
                .createdById("tommaso-id")
                .members(
                    List.of(
                        MemberRequest.builder().userId("thierry-id").build(),
                        MemberRequest.builder().userId("tommaso-id").build()))
                .build())
        .build();

// generate a random call ID
String callID = "call-" + UUID.randomUUID();

// use request classes on Video
new Video.GetOrCreateCall("default", callID, callRequest).request();

// or use the Call convenience class
Call testCall = new Call("default", callID);
testCall.GetOrCreate(callRequest);
```

### App configuration

### Chat API - Channels

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

To regenerate the source from the OpenAPI specification, just run the `./generate.sh` script from this repo.

> [!NOTE]
> Code generation currently relies on tooling that is not publicly available, only Stream devs can regenerate SDK source code from the OpenAPI spec.

## License

This project is licensed under the [MIT License](LICENSE).

## Contributing

Contributions are welcome! Please read the [contributing guidelines](CONTRIBUTING.md) to get started.
