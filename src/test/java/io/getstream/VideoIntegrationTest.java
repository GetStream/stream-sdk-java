package io.getstream;

import static org.junit.jupiter.api.Assertions.*;

import io.getstream.models.*;
import java.util.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.*;

/**
 * Video integration tests for stream-sdk-java, ported from the getstream-go reference
 * implementation.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class VideoIntegrationTest extends BasicTest {

  private static String callTypeName;
  private static final List<String> createdCallIds = new ArrayList<>();

  @BeforeAll
  static void setupCallType() throws Exception {
    // Create a unique call type for all tests in this class
    callTypeName = "calltype-" + RandomStringUtils.randomAlphabetic(8).toLowerCase();

    Map<String, List<String>> grants =
        Map.of(
            "admin", List.of("send-audio", "send-video", "mute-users"),
            "user", List.of("send-audio", "send-video"));

    video
        .createCallType(
            CreateCallTypeRequest.builder()
                .name(callTypeName)
                .grants(grants)
                .settings(
                    CallSettingsRequest.builder()
                        .audio(
                            AudioSettingsRequest.builder()
                                .defaultDevice("speaker")
                                .micDefaultOn(true)
                                .build())
                        .screensharing(
                            ScreensharingSettingsRequest.builder()
                                .accessRequestEnabled(false)
                                .enabled(true)
                                .build())
                        .build())
                .build())
        .execute();

    // Wait for eventual consistency after creating call type
    Thread.sleep(6000);
  }

  @AfterAll
  static void tearDownCallType() {
    // Clean up created calls
    for (String callId : createdCallIds) {
      try {
        video.deleteCall("default", callId).execute();
      } catch (Exception ignored) {
      }
    }
    // Clean up the call type
    if (callTypeName != null) {
      try {
        video.deleteCallType(callTypeName).execute();
      } catch (Exception ignored) {
      }
    }
  }

  @Test
  @Order(1)
  void testCRUDCallTypeOperations() throws Exception {
    // --- Update call type settings ---
    Map<String, List<String>> updatedGrants = Map.of("host", List.of("join-backstage"));

    var updateResp =
        video
            .updateCallType(
                callTypeName,
                UpdateCallTypeRequest.builder()
                    .settings(
                        CallSettingsRequest.builder()
                            .audio(
                                AudioSettingsRequest.builder()
                                    .defaultDevice("earpiece")
                                    .micDefaultOn(false)
                                    .build())
                            .recording(RecordSettingsRequest.builder().mode("disabled").build())
                            .backstage(BackstageSettingsRequest.builder().enabled(true).build())
                            .build())
                    .grants(updatedGrants)
                    .build())
            .execute();

    assertNotNull(updateResp.getData());
    assertFalse(updateResp.getData().getSettings().getAudio().getMicDefaultOn());
    assertEquals("earpiece", updateResp.getData().getSettings().getAudio().getDefaultDevice());
    assertEquals("disabled", updateResp.getData().getSettings().getRecording().getMode());
    assertTrue(updateResp.getData().getSettings().getBackstage().getEnabled());
    assertEquals(
        List.of("join-backstage"), updateResp.getData().getGrants().get("host"));

    // --- Update layout options ---
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

    var layoutResp =
        video
            .updateCallType(
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
                    .build())
            .execute();
    assertNotNull(layoutResp.getData());

    // --- Update custom recording CSS ---
    var cssResp =
        video
            .updateCallType(
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
                    .build())
            .execute();
    assertNotNull(cssResp.getData());

    // --- Update custom recording website ---
    var webResp =
        video
            .updateCallType(
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
                    .build())
            .execute();
    assertNotNull(webResp.getData());

    // --- Read call type and verify name ---
    var readResp = video.getCallType(callTypeName).execute();
    assertNotNull(readResp.getData());
    assertEquals(callTypeName, readResp.getData().getName());
  }

  @Test
  @Order(2)
  void testCreateCallWithMembers() throws Exception {
    // Create a call with members using existing test users
    String callId = "test-call-" + RandomStringUtils.randomAlphabetic(8).toLowerCase();
    createdCallIds.add(callId);

    // Build member list from test users
    List<MemberRequest> members = new ArrayList<>();
    members.add(MemberRequest.builder().userID(testUsers.get(0).getId()).build());
    members.add(MemberRequest.builder().userID(testUsers.get(1).getId()).build());

    var resp =
        video
            .getOrCreateCall(
                "default",
                callId,
                GetOrCreateCallRequest.builder()
                    .data(
                        CallRequest.builder()
                            .createdByID(testUsers.get(0).getId())
                            .members(members)
                            .build())
                    .build())
            .execute();

    assertNotNull(resp.getData());
    assertNotNull(resp.getData().getCall());
    assertEquals(callId, resp.getData().getCall().getId());
    assertNotNull(resp.getData().getMembers());
    assertTrue(resp.getData().getMembers().size() >= 2);
  }

  @Test
  @Order(5)
  void testMuteAll() throws Exception {
    // Create a call
    String callId = "test-call-" + RandomStringUtils.randomAlphabetic(8).toLowerCase();
    createdCallIds.add(callId);

    String userId = testUsers.get(0).getId();

    video
        .getOrCreateCall(
            "default",
            callId,
            GetOrCreateCallRequest.builder()
                .data(CallRequest.builder().createdByID(userId).build())
                .build())
        .execute();

    // Mute all users with audio
    var resp =
        video
            .muteUsers(
                "default",
                callId,
                MuteUsersRequest.builder()
                    .muteAllUsers(true)
                    .audio(true)
                    .mutedByID(userId)
                    .build())
            .execute();

    assertNotNull(resp.getData());
    assertNotNull(resp.getData().getDuration());
  }

  @Test
  @Order(6)
  void testMuteSomeUsers() throws Exception {
    // Create a call
    String callId = "test-call-" + RandomStringUtils.randomAlphabetic(8).toLowerCase();
    createdCallIds.add(callId);

    String creatorId = testUsers.get(0).getId();
    String aliceId = testUsers.get(1).getId();
    String bobId = testUsers.get(2).getId();

    video
        .getOrCreateCall(
            "default",
            callId,
            GetOrCreateCallRequest.builder()
                .data(CallRequest.builder().createdByID(creatorId).build())
                .build())
        .execute();

    // Mute specific users with audio, video, screenshare
    var resp =
        video
            .muteUsers(
                "default",
                callId,
                MuteUsersRequest.builder()
                    .mutedByID(creatorId)
                    .userIds(List.of(aliceId, bobId))
                    .audio(true)
                    .video(true)
                    .screenshare(true)
                    .screenshareAudio(true)
                    .build())
            .execute();

    assertNotNull(resp.getData());
    assertNotNull(resp.getData().getDuration());
  }

  @Test
  @Order(4)
  void testSendCustomEvent() throws Exception {
    // Create a call
    String callId = "test-call-" + RandomStringUtils.randomAlphabetic(8).toLowerCase();
    createdCallIds.add(callId);

    video
        .getOrCreateCall(
            "default",
            callId,
            GetOrCreateCallRequest.builder()
                .data(
                    CallRequest.builder()
                        .createdByID(testUsers.get(0).getId())
                        .build())
                .build())
        .execute();

    // Send a custom event with custom data
    Map<String, Object> customData = new HashMap<>();
    customData.put("bananas", "good");

    var resp =
        video
            .sendCallEvent(
                "default",
                callId,
                SendCallEventRequest.builder()
                    .userID(testUsers.get(0).getId())
                    .custom(customData)
                    .build())
            .execute();

    assertNotNull(resp.getData());
    assertNotNull(resp.getData().getDuration());
  }

  @Test
  @Order(3)
  void testBlockUnblockUserFromCalls() throws Exception {
    // Create a call
    String callId = "test-call-" + RandomStringUtils.randomAlphabetic(8).toLowerCase();
    createdCallIds.add(callId);

    video
        .getOrCreateCall(
            "default",
            callId,
            GetOrCreateCallRequest.builder()
                .data(
                    CallRequest.builder()
                        .createdByID(testUsers.get(0).getId())
                        .build())
                .build())
        .execute();

    // Use a second test user as the "bad" user to block
    String badUserId = testUsers.get(1).getId();

    // Block the user from the call
    var blockResp =
        video
            .blockUser("default", callId, BlockUserRequest.builder().userID(badUserId).build())
            .execute();
    assertNotNull(blockResp.getData());

    // Verify the user is in the blocked list
    var getResp = video.getCall("default", callId).execute();
    assertNotNull(getResp.getData());
    assertNotNull(getResp.getData().getCall());
    assertNotNull(getResp.getData().getCall().getBlockedUserIds());
    assertTrue(getResp.getData().getCall().getBlockedUserIds().contains(badUserId));

    // Unblock the user
    var unblockResp =
        video
            .unblockUser("default", callId, UnblockUserRequest.builder().userID(badUserId).build())
            .execute();
    assertNotNull(unblockResp.getData());

    // Verify the user is no longer in the blocked list
    var getResp2 = video.getCall("default", callId).execute();
    assertNotNull(getResp2.getData());
    assertNotNull(getResp2.getData().getCall());
    List<String> blockedIds = getResp2.getData().getCall().getBlockedUserIds();
    assertTrue(blockedIds == null || !blockedIds.contains(badUserId));
  }
}
