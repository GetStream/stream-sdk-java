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
  @Order(7)
  void testUpdateUserPermissions() throws Exception {
    // Create a call
    String callId = "test-call-" + RandomStringUtils.randomAlphabetic(8).toLowerCase();
    createdCallIds.add(callId);

    String creatorId = testUsers.get(0).getId();
    String aliceId = testUsers.get(1).getId();

    video
        .getOrCreateCall(
            "default",
            callId,
            GetOrCreateCallRequest.builder()
                .data(CallRequest.builder().createdByID(creatorId).build())
                .build())
        .execute();

    // Revoke "send-audio" permission from alice
    var revokeResp =
        video
            .updateUserPermissions(
                "default",
                callId,
                UpdateUserPermissionsRequest.builder()
                    .userID(aliceId)
                    .revokePermissions(List.of("send-audio"))
                    .build())
            .execute();

    assertNotNull(revokeResp.getData());
    assertNotNull(revokeResp.getData().getDuration());

    // Grant "send-audio" permission back to alice
    var grantResp =
        video
            .updateUserPermissions(
                "default",
                callId,
                UpdateUserPermissionsRequest.builder()
                    .userID(aliceId)
                    .grantPermissions(List.of("send-audio"))
                    .build())
            .execute();

    assertNotNull(grantResp.getData());
    assertNotNull(grantResp.getData().getDuration());
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

  @Test
  @Order(9)
  void testCreateCallWithSessionTimer() throws Exception {
    // Create a call with max_duration_seconds = 3600 via settingsOverride
    String callId = "test-call-" + RandomStringUtils.randomAlphabetic(8).toLowerCase();
    createdCallIds.add(callId);

    var createResp =
        video
            .getOrCreateCall(
                "default",
                callId,
                GetOrCreateCallRequest.builder()
                    .data(
                        CallRequest.builder()
                            .createdByID(testUsers.get(0).getId())
                            .settingsOverride(
                                CallSettingsRequest.builder()
                                    .limits(
                                        LimitsSettingsRequest.builder()
                                            .maxDurationSeconds(3600)
                                            .build())
                                    .build())
                            .build())
                    .build())
            .execute();

    assertNotNull(createResp.getData());
    assertNotNull(createResp.getData().getCall());
    assertNotNull(createResp.getData().getCall().getSettings());
    assertNotNull(createResp.getData().getCall().getSettings().getLimits());
    assertEquals(3600, createResp.getData().getCall().getSettings().getLimits().getMaxDurationSeconds());

    // Update the call to max_duration_seconds = 7200
    var updateResp =
        video
            .updateCall(
                "default",
                callId,
                UpdateCallRequest.builder()
                    .settingsOverride(
                        CallSettingsRequest.builder()
                            .limits(
                                LimitsSettingsRequest.builder()
                                    .maxDurationSeconds(7200)
                                    .build())
                            .build())
                    .build())
            .execute();

    assertNotNull(updateResp.getData());
    assertNotNull(updateResp.getData().getCall().getSettings().getLimits());
    assertEquals(7200, updateResp.getData().getCall().getSettings().getLimits().getMaxDurationSeconds());

    // Update the call to max_duration_seconds = 0 (disable)
    var updateResp2 =
        video
            .updateCall(
                "default",
                callId,
                UpdateCallRequest.builder()
                    .settingsOverride(
                        CallSettingsRequest.builder()
                            .limits(
                                LimitsSettingsRequest.builder()
                                    .maxDurationSeconds(0)
                                    .build())
                            .build())
                    .build())
            .execute();

    assertNotNull(updateResp2.getData());
    assertEquals(0, updateResp2.getData().getCall().getSettings().getLimits().getMaxDurationSeconds());
  }

  @Test
  @Order(10)
  void testUserBlocking() throws Exception {
    // Create two fresh users: alice (blocker) and bob (blocked)
    String aliceId = "vid-alice-blk-" + RandomStringUtils.randomAlphabetic(8).toLowerCase();
    String bobId = "vid-bob-blk-" + RandomStringUtils.randomAlphabetic(8).toLowerCase();

    Map<String, UserRequest> usersMap = new HashMap<>();
    usersMap.put(aliceId, UserRequest.builder().id(aliceId).name("Alice Blocker").build());
    usersMap.put(bobId, UserRequest.builder().id(bobId).name("Bob Blocked").build());
    client.updateUsers(UpdateUsersRequest.builder().users(usersMap).build()).execute();

    // Block bob from alice's perspective (app-level user block, not call-level)
    var blockResp =
        client
            .blockUsers(
                BlockUsersRequest.builder().blockedUserID(bobId).userID(aliceId).build())
            .execute();
    assertNotNull(blockResp.getData());

    // Verify bob is in alice's blocked list
    var getBlockedResp =
        client
            .getBlockedUsers(GetBlockedUsersRequest.builder().UserID(aliceId).build())
            .execute();
    assertNotNull(getBlockedResp.getData());
    assertNotNull(getBlockedResp.getData().getBlocks());

    boolean foundBob =
        getBlockedResp.getData().getBlocks().stream()
            .anyMatch(b -> bobId.equals(b.getBlockedUserID()));
    assertTrue(foundBob, "Bob should be in alice's blocked list");

    // Unblock bob
    client
        .unblockUsers(
            UnblockUsersRequest.builder().blockedUserID(bobId).userID(aliceId).build())
        .execute();

    // Verify bob is no longer in alice's blocked list
    var getBlockedResp2 =
        client
            .getBlockedUsers(GetBlockedUsersRequest.builder().UserID(aliceId).build())
            .execute();
    assertNotNull(getBlockedResp2.getData());

    boolean stillFoundBob =
        getBlockedResp2.getData().getBlocks() != null
            && getBlockedResp2.getData().getBlocks().stream()
                .anyMatch(b -> bobId.equals(b.getBlockedUserID()));
    assertFalse(stillFoundBob, "Bob should no longer be in alice's blocked list");
  }

  @Test
  @Order(8)
  void testDeactivateUser() throws Exception {
    // Create two fresh users for this test (alice and bob)
    String aliceId = "vid-alice-" + RandomStringUtils.randomAlphabetic(8).toLowerCase();
    String bobId = "vid-bob-" + RandomStringUtils.randomAlphabetic(8).toLowerCase();

    Map<String, UserRequest> usersMap = new HashMap<>();
    usersMap.put(aliceId, UserRequest.builder().id(aliceId).name("Alice Video").build());
    usersMap.put(bobId, UserRequest.builder().id(bobId).name("Bob Video").build());
    client.updateUsers(UpdateUsersRequest.builder().users(usersMap).build()).execute();

    // Deactivate alice
    var deactivateResp =
        client.deactivateUser(aliceId, DeactivateUserRequest.builder().build()).execute();
    assertNotNull(deactivateResp.getData());
    assertNotNull(deactivateResp.getData().getDuration());

    // Reactivate alice
    var reactivateResp =
        client.reactivateUser(aliceId, ReactivateUserRequest.builder().build()).execute();
    assertNotNull(reactivateResp.getData());
    assertNotNull(reactivateResp.getData().getDuration());

    // Batch deactivate alice and bob, wait for task
    var batchResp =
        client
            .deactivateUsers(
                DeactivateUsersRequest.builder().userIds(List.of(aliceId, bobId)).build())
            .execute();
    assertNotNull(batchResp.getData());
    String taskId = batchResp.getData().getTaskID();
    assertNotNull(taskId);
    waitForAsyncTask(taskId);
  }
}
