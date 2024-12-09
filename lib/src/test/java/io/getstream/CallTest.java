package io.getstream;

import io.getstream.exceptions.StreamException;
import io.getstream.models.*;
import io.getstream.services.Call;
import io.getstream.services.Video;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CallTest extends BasicTest {
  String callType = "default";
  private static String callTypeName;

  @BeforeAll
  static void createCall() throws InterruptedException {
    callTypeName = "calltype-" + RandomStringUtils.randomAlphanumeric(10);

    // Assuming context setup is not required as it's often implicit in Java methods
    CallSettingsRequest callSettings =
        CallSettingsRequest.builder()
            .audio(
                AudioSettingsRequest.builder().defaultDevice("speaker").micDefaultOn(true).build())
            .screensharing(
                ScreensharingSettingsRequest.builder()
                    .accessRequestEnabled(false)
                    .enabled(true)
                    .build())
            .build();

    NotificationSettings notificationSettings =
        new NotificationSettings(
            true,
            new EventNotificationSettings(
                true, new APNS("{{ user.display_name }} invites you to a call", "")),
            new EventNotificationSettings(
                false, new APNS("", "{{ user.display_name }} invites you to a call")),
            new EventNotificationSettings(
                false, new APNS("", "{{ user.display_name }} invites you to a call")),
            new EventNotificationSettings(
                false, new APNS("", "{{ user.display_name }} invites you to a call")),
            new EventNotificationSettings(
                false, new APNS("", "{{ user.display_name }} invites you to a call")));

    Map<String, List<String>> grants =
        Map.of(
            "admin", List.of("send-audio", "send-video", "mute-users"),
            "user", List.of("send-audio", "send-video"));

    Assertions.assertDoesNotThrow(
        () ->
            new Video.createCallType(
                    CreateCallTypeRequest.builder()
                        .grants(grants)
                        .name(callTypeName)
                        .settings(callSettings)
                        .notificationSettings(notificationSettings)
                        .build())
                .request());

    java.lang.Thread.sleep(3000);
  }

  @AfterAll
  static void tearDown() throws StreamException {
    new Video.deleteCallType(callTypeName).request();
  }

  @Test
  void testUpdateCallTypeSettings() {
    Map<String, List<String>> grants = Map.of("host", List.of("join-backstage"));

    var response =
        Assertions.assertDoesNotThrow(
            () ->
                new Video.updateCallType(
                        callTypeName,
                        UpdateCallTypeRequest.builder()
                            .settings(
                                CallSettingsRequest.builder()
                                    .audio(
                                        AudioSettingsRequest.builder()
                                            .defaultDevice("earpiece")
                                            .micDefaultOn(false)
                                            .build())
                                    .recording(
                                        RecordSettingsRequest.builder().mode("disabled").build())
                                    .backstage(
                                        BackstageSettingsRequest.builder().enabled(true).build())
                                    .build())
                            .grants(grants)
                            .build())
                    .request());

    Assertions.assertFalse(response.getSettings().getAudio().getMicDefaultOn());
    Assertions.assertEquals("earpiece", response.getSettings().getAudio().getDefaultDevice());
    Assertions.assertEquals("disabled", response.getSettings().getRecording().getMode());
    Assertions.assertTrue(response.getSettings().getBackstage().getEnabled());
    Assertions.assertEquals(List.of("join-backstage"), response.getGrants().get("host"));
  }

  @Test
  public void updateLayoutOptions() {
    Map<String, Object> layoutOptions = new HashMap<>();
    layoutOptions.put(
        "logo.image_url",
        "https://theme.zdassets.com/theme_assets/9442057/efc3820e436f9150bc8cf34267fff4df052a1f9c.png");
    layoutOptions.put("logo.horizontal_position", "center");
    layoutOptions.put("title.text", "Building Stream Video Q&A");
    layoutOptions.put("title.horizontal_position", "center");
    layoutOptions.put("title.color", "black");
    layoutOptions.put("participant_label.border_radius", "0px");
    layoutOptions.put("participant.border_radius", "0px");
    layoutOptions.put("layout.spotlight.participants_bar_position", "top");
    layoutOptions.put("layout.background_color", "#f2f2f2");
    layoutOptions.put("participant.placeholder_background_color", "#1f1f1f");
    layoutOptions.put("layout.single-participant.padding_inline", "20%");
    layoutOptions.put("participant_label.background_color", "transparent");

    Assertions.assertDoesNotThrow(
        () ->
            new Video.updateCallType(
                callTypeName,
                UpdateCallTypeRequest.builder()
                    .settings(
                        CallSettingsRequest.builder()
                            .recording(
                                RecordSettingsRequest.builder()
                                    .mode("available")
                                    .audioOnly(false)
                                    .quality("1080p")
                                    .layout(
                                        LayoutSettingsRequest.builder()
                                            .name("spotlight")
                                            .options(layoutOptions)
                                            .build())
                                    .build())
                            .build())
                    .build()));
  }

  @Test
  public void testUpdateCustomRecordingStyle() {
    Assertions.assertDoesNotThrow(
        () ->
            new Video.updateCallType(
                callTypeName,
                UpdateCallTypeRequest.builder()
                    .settings(
                        CallSettingsRequest.builder()
                            .recording(
                                RecordSettingsRequest.builder()
                                    .mode("available")
                                    .audioOnly(false)
                                    .quality("1080p")
                                    .layout(
                                        LayoutSettingsRequest.builder()
                                            .name("spotlight")
                                            .externalCssUrl("https://path/to/custom.css")
                                            .build())
                                    .build())
                            .build())
                    .build()));
  }

  @Test
  public void testUpdateCustomRecordingWebsite() {
    Assertions.assertDoesNotThrow(
        () ->
            new Video.updateCallType(
                callTypeName,
                UpdateCallTypeRequest.builder()
                    .settings(
                        CallSettingsRequest.builder()
                            .recording(
                                RecordSettingsRequest.builder()
                                    .mode("available")
                                    .audioOnly(false)
                                    .quality("1080p")
                                    .layout(
                                        LayoutSettingsRequest.builder()
                                            .name("custom")
                                            .externalAppUrl("https://path/to/layout/app")
                                            .build())
                                    .build())
                            .build())
                    .build()));
  }

  @Test
  public void testReadCallType() {
    var response =
        Assertions.assertDoesNotThrow(() -> new Video.getCallType(callTypeName).request());
    Assertions.assertEquals(callTypeName, response.getName());
  }

  @Test
  public void testCreateCall() {
    var callRequest =
        GetOrCreateCallRequest.builder()
            .data(
                CallRequest.builder()
                    .createdByID(testUser.getId())
                    .settingsOverride(
                        CallSettingsRequest.builder()
                            .geofencing(
                                GeofenceSettingsRequest.builder().names(List.of("canada")).build())
                            .screensharing(
                                ScreensharingSettingsRequest.builder().enabled(false).build())
                            .build())
                    .build())
            .build();

    String callID = "call-" + RandomStringUtils.randomAlphanumeric(10);
    Call testCall = new Call(callType, callID);
    var response = Assertions.assertDoesNotThrow(() -> testCall.getOrCreate(callRequest));
    Assertions.assertEquals(testUser.getId(), response.getCall().getCreatedBy().getId());
    Assertions.assertFalse(response.getCall().getSettings().getScreensharing().getEnabled());
  }

  @Test
  public void testUpdateCall() {
    var callRequest =
        GetOrCreateCallRequest.builder()
            .data(CallRequest.builder().createdByID(testUser.getId()).build())
            .build();

    var callId = "call-" + RandomStringUtils.randomAlphabetic(10);
    Assertions.assertDoesNotThrow(
        () -> new Video.getOrCreateCall("default", callId, callRequest).request());

    Call call = new Call(callType, callId);

    var updateRequest =
        UpdateCallRequest.builder()
            .settingsOverride(
                CallSettingsRequest.builder()
                    .audio(
                        AudioSettingsRequest.builder()
                            .micDefaultOn(true)
                            .defaultDevice("speaker")
                            .build())
                    .build())
            .build();

    var updatedResponse = Assertions.assertDoesNotThrow(() -> call.update(updateRequest));
    Assertions.assertTrue(updatedResponse.getCall().getSettings().getAudio().getMicDefaultOn());
  }

  @Test
  void testSendCustomEvent() {
    Assertions.assertNotNull(testUser, "User should not be null");
    String callID = "call-" + RandomStringUtils.randomAlphanumeric(10);
    Call testCall = new Call(callType, callID);

    GetOrCreateCallRequest callRequest =
        GetOrCreateCallRequest.builder()
            .data(CallRequest.builder().createdByID(testUser.getId()).build())
            .build();

    Assertions.assertDoesNotThrow(() -> testCall.getOrCreate(callRequest));

    Map<String, Object> customEvent = Map.of("bananas", "good");

    SendCallEventRequest sendEventRequest =
        SendCallEventRequest.builder().userID(testUser.getId()).custom(customEvent).build();
    Assertions.assertDoesNotThrow(() -> testCall.sendCallEvent(sendEventRequest));
  }
}
