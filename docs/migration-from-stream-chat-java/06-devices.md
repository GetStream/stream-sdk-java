# Devices

This guide shows how to migrate device management operations from `stream-chat-java` to `stream-sdk-java`.

## Add a Device (APN)

**Before (stream-chat-java):**

```java
import io.getstream.chat.java.models.Device;
import io.getstream.chat.java.models.Device.PushProviderType;
import io.getstream.chat.java.models.User.UserRequestObject;

Device.create()
    .id("device-token-123")
    .user(UserRequestObject.builder().id("john").build())
    .pushProvider(PushProviderType.Apn)
    .request();
```

**After (stream-sdk-java):**

```java
import io.getstream.services.framework.StreamSDKClient;
import io.getstream.models.CreateDeviceRequest;

StreamSDKClient client = new StreamSDKClient("your-api-key", "your-api-secret");

client.createDevice(CreateDeviceRequest.builder()
    .id("device-token-123")
    .userID("john")
    .pushProvider("apn")
    .build())
    .execute();
```

**Key changes:**
- `Device.create()` becomes `client.createDevice()`
- `.user(UserRequestObject)` becomes `.userID("john")`
- `PushProviderType.Apn` enum becomes the string `"apn"`

## Add a Device (Firebase)

**Before (stream-chat-java):**

```java
import io.getstream.chat.java.models.Device;
import io.getstream.chat.java.models.Device.PushProviderType;
import io.getstream.chat.java.models.User.UserRequestObject;

Device.create()
    .id("fcm-token-456")
    .user(UserRequestObject.builder().id("john").build())
    .pushProvider(PushProviderType.Firebase)
    .request();
```

**After (stream-sdk-java):**

```java
import io.getstream.services.framework.StreamSDKClient;
import io.getstream.models.CreateDeviceRequest;

StreamSDKClient client = new StreamSDKClient("your-api-key", "your-api-secret");

client.createDevice(CreateDeviceRequest.builder()
    .id("fcm-token-456")
    .userID("john")
    .pushProvider("firebase")
    .build())
    .execute();
```

**Key changes:**
- `PushProviderType.Firebase` becomes the string `"firebase"`

## Add a VoIP Device

**Before (stream-chat-java):**

```java
import io.getstream.chat.java.models.Device;
import io.getstream.chat.java.models.Device.PushProviderType;
import io.getstream.chat.java.models.User.UserRequestObject;

// No dedicated VoIP support; same as regular APN device
Device.create()
    .id("voip-token-789")
    .user(UserRequestObject.builder().id("john").build())
    .pushProvider(PushProviderType.Apn)
    .request();
```

**After (stream-sdk-java):**

```java
import io.getstream.services.framework.StreamSDKClient;
import io.getstream.models.CreateDeviceRequest;

StreamSDKClient client = new StreamSDKClient("your-api-key", "your-api-secret");

client.createDevice(CreateDeviceRequest.builder()
    .id("voip-token-789")
    .userID("john")
    .pushProvider("apn")
    .voipToken(true)
    .build())
    .execute();
```

**Key changes:**
- New SDK adds `.voipToken(true)` for Apple VoIP push tokens

## List Devices

**Before (stream-chat-java):**

```java
import io.getstream.chat.java.models.Device;

var response = Device.list("john").request();
```

**After (stream-sdk-java):**

```java
import io.getstream.services.framework.StreamSDKClient;
import io.getstream.models.ListDevicesRequest;

StreamSDKClient client = new StreamSDKClient("your-api-key", "your-api-secret");

var response = client.listDevices(ListDevicesRequest.builder()
    .userID("john")
    .build())
    .execute();

var devices = response.getData().getDevices();
```

**Key changes:**
- `Device.list(userId)` becomes `client.listDevices(ListDevicesRequest)`
- User ID moves from a positional argument to a field on the request object

## Delete a Device

**Before (stream-chat-java):**

```java
import io.getstream.chat.java.models.Device;

Device.delete("device-token-123", "john").request();
```

**After (stream-sdk-java):**

```java
import io.getstream.services.framework.StreamSDKClient;
import io.getstream.models.DeleteDeviceRequest;

StreamSDKClient client = new StreamSDKClient("your-api-key", "your-api-secret");

client.deleteDevice(DeleteDeviceRequest.builder()
    .id("device-token-123")
    .userID("john")
    .build())
    .execute();
```

**Key changes:**
- `Device.delete(deviceId, userId)` becomes `client.deleteDevice(DeleteDeviceRequest)`
- Positional arguments become named fields on the request object

## Method Mapping Summary

| Operation | stream-chat-java | stream-sdk-java |
|-----------|-----------------|-----------------|
| Add device | `Device.create()` | `client.createDevice()` |
| List devices | `Device.list(userId)` | `client.listDevices()` |
| Delete device | `Device.delete(deviceId, userId)` | `client.deleteDevice()` |
