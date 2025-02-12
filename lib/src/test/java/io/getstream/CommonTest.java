package io.getstream;

import io.getstream.exceptions.StreamException;
import io.getstream.models.*;
import io.getstream.services.framework.StreamHTTPClient;
import io.getstream.services.framework.StreamSDKClient;
import java.util.Properties;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CommonTest extends BasicTest {
  private String SnsStatus;
  private String SqsStatus;

  @DisplayName("Can generate a user token")
  @Test
  void whenGeneratingUserToken_thenNoException() {
    String userId = RandomStringUtils.randomAlphabetic(10);
    String token = client.tokenBuilder().createToken(userId, 24 * 60 * 60);
    Assertions.assertNotEquals(0, token.length());
  }

  @DisplayName("App Get does not throw Exception")
  @Test
  void whenCallingGetApp_thenNoException() {
    Assertions.assertDoesNotThrow(() -> client.getApp(null).execute());
  }

  @DisplayName("App Settings update does not throw Exception")
  @Test
  @Disabled
  void whenUpdatingAppSettings_thenNoException() {
    var data =
        UpdateAppRequest.builder()
            .disableAuthChecks(true)
            .disablePermissionsChecks(true)
            .asyncModerationConfig(
                AsyncModerationConfiguration.builder()
                    .callback(
                        AsyncModerationCallbackConfig.builder()
                            .mode("CALLBACK_MODE_REST")
                            .serverUrl("http://localhost.com")
                            .build())
                    .timeoutMs(3000)
                    .build())
            .build();

    Assertions.assertDoesNotThrow(() -> client.updateApp(data).execute());
    Assertions.assertDoesNotThrow(
        () ->
            client
                .updateApp(
                    UpdateAppRequest.builder()
                        .disableAuthChecks(false)
                        .disablePermissionsChecks(false)
                        .build())
                .execute());
  }

  @DisplayName("App Get fails with bad key")
  @Test
  @Disabled
  void givenBadKey_whenGettingApp_thenException() {
    var properties = new Properties();
    properties.put(StreamHTTPClient.API_KEY_PROP_NAME, "XXX");

    var client = new StreamSDKClient(properties);

    StreamException exception =
        Assertions.assertThrows(StreamException.class, () -> client.getApp(null).execute());
    Assertions.assertEquals(401, exception.getResponseData().getStatusCode());
  }

  @DisplayName("App Get fails with bad secret (after enabling auth)")
  @Test
  @Disabled
  void givenBadSecret_whenEnableAuthAndGettingApp_thenException() {
    Assertions.assertDoesNotThrow(
        () ->
            client
                .updateApp(UpdateAppRequest.builder().disableAuthChecks(false).build())
                .execute());
    var properties = new Properties();
    properties.put(
        StreamHTTPClient.API_SECRET_PROP_NAME, "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");

    var client = new StreamSDKClient(properties);

    StreamException exception =
        Assertions.assertThrows(StreamException.class, () -> client.getApp(null).execute());
    Assertions.assertEquals(401, exception.getResponseData().getStatusCode());
  }

  @DisplayName("Can check sqs")
  @Test
  void whenCheckingBadSqs_thenError() {
    CheckSQSResponse response =
        Assertions.assertDoesNotThrow(
            () ->
                client
                    .checkSQS(
                        CheckSQSRequest.builder()
                            .sqsKey("key")
                            .sqsSecret("secret")
                            .sqsUrl("https://foo.com/bar")
                            .build())
                    .execute()
                    .getData());
    Assertions.assertEquals("error", response.getStatus());
  }

  @DisplayName("Can check sns")
  @Test
  void whenCheckingBadSns_thenError() {
    CheckSNSResponse response =
        Assertions.assertDoesNotThrow(
            () ->
                client
                    .checkSNS(
                        CheckSNSRequest.builder()
                            .snsKey("key")
                            .snsSecret("secret")
                            .snsTopicArn("arn:aws:sns:us-east-1:123456789012:sns-topic")
                            .build())
                    .execute()
                    .getData());
    Assertions.assertEquals("error", response.getStatus());
  }
}
