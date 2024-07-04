package io.getstream;

import static io.getstream.models.framework.User.createToken;

import io.getstream.exceptions.StreamException;
import io.getstream.models.*;
import io.getstream.services.Common;
import io.getstream.services.framework.DefaultClient;
import java.util.Properties;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CommonTest extends BasicTest {
  private String SnsStatus;
  private String SqsStatus;

  @DisplayName("Can generate a user token")
  @Test
  void whenGeneratingUserToken_thenNoException() {
    String userId = RandomStringUtils.randomAlphabetic(10);
    String token = createToken(userId, null, null);
    Assertions.assertEquals(184, token.length());
  }

  @DisplayName("App Get does not throw Exception")
  @Test
  void whenCallingGetApp_thenNoException() {
    Assertions.assertDoesNotThrow(() -> new Common.GetApp().request());
  }

  @Test
  @DisplayName("App get async does not throw Exception")
  void whenCallingGetAppAsync_thenNoException() {
    new Common.GetApp().requestAsync(Assertions::assertNotNull, Assertions::assertNull);
  }

  @DisplayName("App Settings update does not throw Exception")
  @Test
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

    Assertions.assertDoesNotThrow(() -> new Common.UpdateApp(data).request());
    Assertions.assertDoesNotThrow(
        () ->
            new Common.UpdateApp(
                    UpdateAppRequest.builder()
                        .disableAuthChecks(false)
                        .disablePermissionsChecks(false)
                        .build())
                .request());
  }

  @DisplayName("App Get fails with bad key")
  @Test
  void givenBadKey_whenGettingApp_thenException() {
    var properties = new Properties();
    properties.put(DefaultClient.API_KEY_PROP_NAME, "XXX");

    var client = new DefaultClient(properties);

    StreamException exception =
        Assertions.assertThrows(
            StreamException.class, () -> new Common.GetApp().withClient(client).request());
    Assertions.assertEquals(401, exception.getResponseData().getStatusCode());
  }

  @DisplayName("App Get fails with bad secret (after enabling auth)")
  @Test
  void givenBadSecret_whenEnableAuthAndGettingApp_thenException() {
    Assertions.assertDoesNotThrow(
        () ->
            new Common.UpdateApp(UpdateAppRequest.builder().disableAuthChecks(false).build())
                .request());
    var properties = new Properties();
    properties.put(
        DefaultClient.API_SECRET_PROP_NAME, "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");

    var client = new DefaultClient(properties);

    StreamException exception =
        Assertions.assertThrows(
            StreamException.class, () -> new Common.GetApp().withClient(client).request());
    Assertions.assertEquals(401, exception.getResponseData().getStatusCode());
  }

  @DisplayName("Get rate limits does not throw Exception")
  @Test
  void whenCallingGetRateLimits_thenNoException() {
    Assertions.assertDoesNotThrow(() -> new Common.GetRateLimits().request());
  }

  @DisplayName("Can check sqs")
  @Test
  void whenCheckingBadSqs_thenError() {
    CheckSQSResponse response =
        Assertions.assertDoesNotThrow(
            () ->
                new Common.CheckSQS(
                        CheckSQSRequest.builder()
                            .sqsKey("key")
                            .sqsSecret("secret")
                            .sqsUrl("https://foo.com/bar")
                            .build())
                    .request());
    Assertions.assertEquals("error", response.getStatus());
  }

  @DisplayName("Can check sns")
  @Test
  void whenCheckingBadSns_thenError() {
    CheckSNSResponse response =
        Assertions.assertDoesNotThrow(
            () ->
                new Common.CheckSNS(
                        CheckSNSRequest.builder()
                            .snsKey("key")
                            .snsSecret("secret")
                            .snsTopicArn("arn:aws:sns:us-east-1:123456789012:sns-topic")
                            .build())
                    .request());
    Assertions.assertEquals("error", response.getStatus());
  }

  //    @DisplayName("Can check push templates")
  //    @Test
  //    void whenCheckingPushTemplates_thenNoException() {
  //      String firstUserId = testUser.getId();
  //      String secondUserId = testUsers.get(1).getId();
  //      String text = "Hello @" + secondUserId;
  //      MessageRequest messageRequest =
  //          MessageRequest.builder().text(text).userId(firstUserId).build();
  //      MessageResponse message =
  //          Assertions.assertDoesNotThrow(
  //                  () ->
  //                          new ChatClient.SendMessage(testChannel.getType(), testChannel.getId(),
  // SendMessageRequest.builder().message(messageRequest).build())
  //                          .request())
  //              .getMessage();
  //      Assertions.assertDoesNotThrow(
  //          () ->
  //                  new Common.UpdateApp(UpdateAppRequest.builder()
  //                      .pushConfig(
  //                          PushConfig.builder()
  //                              .version("v2")
  //                              .offlineOnly(false)
  //                              .build())
  //                      .build())
  //                  .request());
  //      Assertions.assertDoesNotThrow(
  //          () ->
  //                  new Common.CheckPush(CheckPushRequest.builder()
  //                      .messageId(message.getId())
  //                      .skipDevices(true)
  //                      .userId(secondUserId)
  //                      .build())
  //                  .request());
  //    }
  //
  //    @DisplayName("Can revoke tokens")
  //    @Test
  //    void whenRevokingTokens_thenNoException() {
  //      Calendar calendar = new GregorianCalendar();
  //      calendar.add(Calendar.DAY_OF_MONTH, -1);
  //      Assertions.assertDoesNotThrow(() -> App.revokeTokens(calendar.getTime()).request());
  //    }
}
