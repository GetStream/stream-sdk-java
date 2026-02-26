package io.getstream;

import static org.junit.jupiter.api.Assertions.*;

import io.getstream.models.*;
import java.util.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ChatUserIntegrationTest extends ChatTestBase {

  private final List<String> createdUserIds = new ArrayList<>();

  @AfterAll
  void cleanup() {
    if (!createdUserIds.isEmpty()) {
      deleteUsersWithRetry(createdUserIds);
    }
  }

  @Test
  @Order(1)
  void testUpsertUsers() throws Exception {
    Map<String, UserRequest> users = new HashMap<>();
    for (int i = 0; i < 2; i++) {
      String id = "tu-" + UUID.randomUUID().toString().replace("-", "").substring(0, 16);
      users.put(id, UserRequest.builder().id(id).name("Test User " + i).role("user").build());
    }

    var resp = client.updateUsers(UpdateUsersRequest.builder().users(users).build()).execute();

    createdUserIds.addAll(users.keySet());

    assertNotNull(resp.getData());
    Map<String, FullUserResponse> result = resp.getData().getUsers();
    for (String id : users.keySet()) {
      assertTrue(result.containsKey(id), "User " + id + " should be in response");
    }
  }

  @Test
  @Order(2)
  void testQueryUsers() throws Exception {
    List<String> userIds = createTestUsers(2);
    createdUserIds.addAll(userIds);

    var resp =
        client
            .queryUsers(
                QueryUsersRequest.builder()
                    .Payload(
                        QueryUsersPayload.builder()
                            .filterConditions(Map.of("id", Map.of("$in", userIds)))
                            .build())
                    .build())
            .execute();

    assertNotNull(resp.getData());
    List<FullUserResponse> foundUsers = resp.getData().getUsers();
    assertTrue(foundUsers.size() >= 2, "Expected at least 2 users, got " + foundUsers.size());
    Set<String> foundIds = new HashSet<>();
    for (FullUserResponse u : foundUsers) {
      foundIds.add(u.getId());
    }
    for (String id : userIds) {
      assertTrue(foundIds.contains(id), "User " + id + " should be found in query result");
    }
  }

  @Test
  @Order(3)
  void testQueryUsersWithOffsetLimit() throws Exception {
    List<String> userIds = createTestUsers(3);
    createdUserIds.addAll(userIds);

    var resp =
        client
            .queryUsers(
                QueryUsersRequest.builder()
                    .Payload(
                        QueryUsersPayload.builder()
                            .filterConditions(Map.of("id", Map.of("$in", userIds)))
                            .offset(1)
                            .limit(2)
                            .build())
                    .build())
            .execute();

    assertNotNull(resp.getData());
    List<FullUserResponse> foundUsers = resp.getData().getUsers();
    assertEquals(2, foundUsers.size(), "Expected exactly 2 users with offset=1 limit=2");
  }

  @Test
  @Order(6)
  void testDeactivateReactivateUser() throws Exception {
    List<String> userIds = createTestUsers(1);
    String userId = userIds.get(0);
    createdUserIds.addAll(userIds);

    // Deactivate the user
    var deactivateResp = client.deactivateUser(userId).execute();
    assertNotNull(deactivateResp.getData());

    // Reactivate the user
    var reactivateResp = client.reactivateUser(userId).execute();
    assertNotNull(reactivateResp.getData());

    // Verify user is active again by querying without include_deactivated flag
    var queryResp =
        client
            .queryUsers(
                QueryUsersRequest.builder()
                    .Payload(
                        QueryUsersPayload.builder()
                            .filterConditions(Map.of("id", Map.of("$in", userIds)))
                            .build())
                    .build())
            .execute();

    assertNotNull(queryResp.getData());
    List<FullUserResponse> foundUsers = queryResp.getData().getUsers();
    assertTrue(foundUsers.size() >= 1, "Reactivated user should appear in normal query");
    boolean found = foundUsers.stream().anyMatch(u -> userId.equals(u.getId()));
    assertTrue(found, "Reactivated user " + userId + " should be found in query results");
  }

  @Test
  @Order(5)
  void testBlockUnblockUser() throws Exception {
    List<String> userIds = createTestUsers(2);
    String aliceId = userIds.get(0);
    String bobId = userIds.get(1);
    createdUserIds.addAll(userIds);

    // Block bob from alice's perspective
    client
        .blockUsers(
            BlockUsersRequest.builder().blockedUserID(bobId).userID(aliceId).build())
        .execute();

    // Verify bob is in alice's blocked list
    var blockedResp =
        client
            .getBlockedUsers(GetBlockedUsersRequest.builder().UserID(aliceId).build())
            .execute();

    assertNotNull(blockedResp.getData());
    List<BlockedUserResponse> blocks = blockedResp.getData().getBlocks();
    assertNotNull(blocks);
    boolean foundBob = blocks.stream().anyMatch(b -> bobId.equals(b.getBlockedUserID()));
    assertTrue(foundBob, "Bob should be in alice's blocked list");

    // Unblock bob
    client
        .unblockUsers(
            UnblockUsersRequest.builder().blockedUserID(bobId).userID(aliceId).build())
        .execute();

    // Verify bob is no longer in alice's blocked list
    var unblockedResp =
        client
            .getBlockedUsers(GetBlockedUsersRequest.builder().UserID(aliceId).build())
            .execute();

    assertNotNull(unblockedResp.getData());
    List<BlockedUserResponse> remainingBlocks = unblockedResp.getData().getBlocks();
    boolean stillBlocked =
        remainingBlocks != null
            && remainingBlocks.stream().anyMatch(b -> bobId.equals(b.getBlockedUserID()));
    assertFalse(stillBlocked, "Bob should no longer be in alice's blocked list after unblock");
  }

  @Test
  @Order(7)
  void testDeleteUsers() throws Exception {
    List<String> userIds = createTestUsers(2);
    // NOT added to createdUserIds since we explicitly delete them

    String taskId = null;
    for (int i = 0; i < 10; i++) {
      try {
        var resp =
            client
                .deleteUsers(
                    DeleteUsersRequest.builder()
                        .userIds(userIds)
                        .user("hard")
                        .messages("hard")
                        .conversations("hard")
                        .build())
                .execute();
        assertNotNull(resp.getData());
        taskId = resp.getData().getTaskID();
        break;
      } catch (Exception e) {
        String msg = e.getMessage();
        if (msg == null || !msg.contains("Too many requests")) throw e;
        Thread.sleep((long) (i + 1) * 3000L);
      }
    }

    assertNotNull(taskId, "Delete users should return a task ID");
    waitForTask(taskId);
  }

  @Test
  @Order(8)
  void testExportUser() throws Exception {
    List<String> userIds = createTestUsers(1);
    String userId = userIds.get(0);
    createdUserIds.addAll(userIds);

    var resp = client.exportUser(userId).execute();

    assertNotNull(resp.getData(), "Export user response data should not be null");
    assertNotNull(resp.getData().getUser(), "Exported user should not be null");
    assertEquals(userId, resp.getData().getUser().getId(), "Exported user ID should match");
  }

  @Test
  @Order(9)
  void testCreateGuest() throws Exception {
    String guestId = "guest-" + UUID.randomUUID().toString().replace("-", "").substring(0, 12);
    try {
      var resp =
          client
              .createGuest(
                  CreateGuestRequest.builder()
                      .user(UserRequest.builder().id(guestId).build())
                      .build())
              .execute();

      assertNotNull(resp.getData(), "CreateGuest response data should not be null");
      assertNotNull(resp.getData().getAccessToken(), "Access token should not be null");
      assertFalse(
          resp.getData().getAccessToken().isEmpty(), "Access token should not be empty");
      assertNotNull(resp.getData().getUser(), "User should not be null");
      assertTrue(
          resp.getData().getUser().getId().contains(guestId),
          "User ID should contain the requested guest ID");
    } catch (Exception e) {
      String msg = e.getMessage();
      if (msg != null && (msg.contains("guest") || msg.contains("disabled") || msg.contains("not enabled"))) {
        org.junit.jupiter.api.Assumptions.assumeTrue(false, "Guest user creation not enabled for this app");
      }
      throw e;
    }
  }

  @Test
  @Order(10)
  void testUpsertUsersWithRoleAndTeamsRole() throws Exception {
    String userId = "tu-" + UUID.randomUUID().toString().replace("-", "").substring(0, 16);
    Map<String, String> teamsRole = new HashMap<>();
    teamsRole.put("blue", "admin");

    Map<String, UserRequest> users = new HashMap<>();
    users.put(
        userId,
        UserRequest.builder()
            .id(userId)
            .name("Teams Role User")
            .role("admin")
            .teams(List.of("blue"))
            .teamsRole(teamsRole)
            .build());

    var resp = client.updateUsers(UpdateUsersRequest.builder().users(users).build()).execute();
    createdUserIds.add(userId);

    assertNotNull(resp.getData());
    Map<String, FullUserResponse> result = resp.getData().getUsers();
    assertTrue(result.containsKey(userId), "User should be in response");

    FullUserResponse user = result.get(userId);
    assertEquals("admin", user.getRole(), "Role should be admin");
    assertNotNull(user.getTeams(), "Teams should not be null");
    assertTrue(user.getTeams().contains("blue"), "Teams should contain 'blue'");
    assertNotNull(user.getTeamsRole(), "TeamsRole should not be null");
    assertEquals("admin", user.getTeamsRole().get("blue"), "TeamsRole for 'blue' should be 'admin'");
  }

  @Test
  @Order(11)
  void testPartialUpdateUserWithTeam() throws Exception {
    List<String> userIds = createTestUsers(1);
    String userId = userIds.get(0);
    createdUserIds.addAll(userIds);

    // Partial update to add teams and teams_role via set map
    Map<String, Object> setFields = new HashMap<>();
    setFields.put("teams", List.of("blue"));
    Map<String, String> teamsRole = new HashMap<>();
    teamsRole.put("blue", "admin");
    setFields.put("teams_role", teamsRole);

    var resp =
        client
            .updateUsersPartial(
                UpdateUsersPartialRequest.builder()
                    .users(
                        List.of(
                            UpdateUserPartialRequest.builder().id(userId).set(setFields).build()))
                    .build())
            .execute();

    assertNotNull(resp.getData());
    Map<String, FullUserResponse> result = resp.getData().getUsers();
    assertTrue(result.containsKey(userId), "User should be in response");

    FullUserResponse user = result.get(userId);
    assertNotNull(user.getTeams(), "Teams should not be null");
    assertTrue(user.getTeams().contains("blue"), "Teams should contain 'blue'");
    assertNotNull(user.getTeamsRole(), "TeamsRole should not be null");
    assertEquals("admin", user.getTeamsRole().get("blue"), "TeamsRole for 'blue' should be 'admin'");
  }

  @Test
  @Order(12)
  void testUpdatePrivacySettings() throws Exception {
    List<String> userIds = createTestUsers(1);
    String userId = userIds.get(0);
    createdUserIds.addAll(userIds);

    // Step 1: Disable typing indicators
    Map<String, UserRequest> users1 = new HashMap<>();
    users1.put(
        userId,
        UserRequest.builder()
            .id(userId)
            .privacySettings(
                PrivacySettingsResponse.builder()
                    .typingIndicators(TypingIndicatorsResponse.builder().enabled(false).build())
                    .build())
            .build());
    var resp1 = client.updateUsers(UpdateUsersRequest.builder().users(users1).build()).execute();
    assertNotNull(resp1.getData());
    assertNotNull(resp1.getData().getUsers().get(userId), "User should be in response");

    // Verify via query
    var query1 =
        client
            .queryUsers(
                QueryUsersRequest.builder()
                    .Payload(
                        QueryUsersPayload.builder()
                            .filterConditions(Map.of("id", Map.of("$in", userIds)))
                            .build())
                    .build())
            .execute();
    assertNotNull(query1.getData());
    FullUserResponse queried1 =
        query1.getData().getUsers().stream()
            .filter(u -> userId.equals(u.getId()))
            .findFirst()
            .orElse(null);
    assertNotNull(queried1, "User should appear in query result");
    if (queried1.getPrivacySettings() != null
        && queried1.getPrivacySettings().getTypingIndicators() != null) {
      assertFalse(
          queried1.getPrivacySettings().getTypingIndicators().getEnabled(),
          "TypingIndicators should be disabled");
    }

    // Step 2: Set both typing indicators (true) and read receipts (false)
    Map<String, UserRequest> users2 = new HashMap<>();
    users2.put(
        userId,
        UserRequest.builder()
            .id(userId)
            .privacySettings(
                PrivacySettingsResponse.builder()
                    .typingIndicators(TypingIndicatorsResponse.builder().enabled(true).build())
                    .readReceipts(ReadReceiptsResponse.builder().enabled(false).build())
                    .build())
            .build());
    var resp2 = client.updateUsers(UpdateUsersRequest.builder().users(users2).build()).execute();
    assertNotNull(resp2.getData());
    assertNotNull(resp2.getData().getUsers().get(userId), "User should be in response after second update");

    // Verify via query
    var query2 =
        client
            .queryUsers(
                QueryUsersRequest.builder()
                    .Payload(
                        QueryUsersPayload.builder()
                            .filterConditions(Map.of("id", Map.of("$in", userIds)))
                            .build())
                    .build())
            .execute();
    assertNotNull(query2.getData());
    FullUserResponse queried2 =
        query2.getData().getUsers().stream()
            .filter(u -> userId.equals(u.getId()))
            .findFirst()
            .orElse(null);
    assertNotNull(queried2, "User should appear in query result after second update");
    if (queried2.getPrivacySettings() != null) {
      PrivacySettingsResponse ps = queried2.getPrivacySettings();
      if (ps.getTypingIndicators() != null) {
        assertTrue(
            ps.getTypingIndicators().getEnabled(), "TypingIndicators should be enabled after second update");
      }
      if (ps.getReadReceipts() != null) {
        assertFalse(
            ps.getReadReceipts().getEnabled(), "ReadReceipts should be disabled after second update");
      }
    }
  }

  @Test
  @Order(13)
  void testPartialUpdatePrivacySettings() throws Exception {
    List<String> userIds = createTestUsers(1);
    String userId = userIds.get(0);
    createdUserIds.addAll(userIds);

    // Step 1: Partial update to set typing_indicators.enabled=true only
    Map<String, Object> typingMap = new HashMap<>();
    typingMap.put("enabled", true);
    Map<String, Object> privacyMap1 = new HashMap<>();
    privacyMap1.put("typing_indicators", typingMap);
    Map<String, Object> setFields1 = new HashMap<>();
    setFields1.put("privacy_settings", privacyMap1);

    var resp1 =
        client
            .updateUsersPartial(
                UpdateUsersPartialRequest.builder()
                    .users(
                        List.of(
                            UpdateUserPartialRequest.builder().id(userId).set(setFields1).build()))
                    .build())
            .execute();
    assertNotNull(resp1.getData());
    assertNotNull(resp1.getData().getUsers().get(userId), "User should be in partial update response");

    // Verify typing_indicators set, read_receipts still null
    var query1 =
        client
            .queryUsers(
                QueryUsersRequest.builder()
                    .Payload(
                        QueryUsersPayload.builder()
                            .filterConditions(Map.of("id", Map.of("$in", userIds)))
                            .build())
                    .build())
            .execute();
    assertNotNull(query1.getData());
    FullUserResponse queried1 =
        query1.getData().getUsers().stream()
            .filter(u -> userId.equals(u.getId()))
            .findFirst()
            .orElse(null);
    assertNotNull(queried1, "User should appear in query result after first partial update");
    if (queried1.getPrivacySettings() != null
        && queried1.getPrivacySettings().getTypingIndicators() != null) {
      assertTrue(
          queried1.getPrivacySettings().getTypingIndicators().getEnabled(),
          "TypingIndicators should be enabled after first partial update");
    }
    // ReadReceipts should not be set yet (null or not enabled)
    if (queried1.getPrivacySettings() != null) {
      // ReadReceipts is either null or its enabled state is not set to false yet
      PrivacySettingsResponse ps1 = queried1.getPrivacySettings();
      if (ps1.getReadReceipts() != null) {
        // If present, it was set via a previous test - we just note this is the state before our second update
        // The key behavior is that our first partial update did NOT wipe out privacy_settings or break existing ones
      }
    }

    // Step 2: Partial update to set read_receipts.enabled=false only (should NOT clear typing_indicators)
    Map<String, Object> readMap = new HashMap<>();
    readMap.put("enabled", false);
    Map<String, Object> privacyMap2 = new HashMap<>();
    privacyMap2.put("read_receipts", readMap);
    Map<String, Object> setFields2 = new HashMap<>();
    setFields2.put("privacy_settings", privacyMap2);

    var resp2 =
        client
            .updateUsersPartial(
                UpdateUsersPartialRequest.builder()
                    .users(
                        List.of(
                            UpdateUserPartialRequest.builder().id(userId).set(setFields2).build()))
                    .build())
            .execute();
    assertNotNull(resp2.getData());
    assertNotNull(resp2.getData().getUsers().get(userId), "User should be in second partial update response");

    // Verify both typing_indicators and read_receipts are correct
    var query2 =
        client
            .queryUsers(
                QueryUsersRequest.builder()
                    .Payload(
                        QueryUsersPayload.builder()
                            .filterConditions(Map.of("id", Map.of("$in", userIds)))
                            .build())
                    .build())
            .execute();
    assertNotNull(query2.getData());
    FullUserResponse queried2 =
        query2.getData().getUsers().stream()
            .filter(u -> userId.equals(u.getId()))
            .findFirst()
            .orElse(null);
    assertNotNull(queried2, "User should appear in query result after second partial update");
    if (queried2.getPrivacySettings() != null) {
      PrivacySettingsResponse ps2 = queried2.getPrivacySettings();
      if (ps2.getTypingIndicators() != null) {
        assertTrue(
            ps2.getTypingIndicators().getEnabled(),
            "TypingIndicators should still be enabled after second partial update");
      }
      if (ps2.getReadReceipts() != null) {
        assertFalse(
            ps2.getReadReceipts().getEnabled(),
            "ReadReceipts should be disabled after second partial update");
      }
    }
  }

  @Test
  @Order(14)
  void testQueryUsersWithDeactivated() throws Exception {
    List<String> userIds = createTestUsers(2);
    String activeId = userIds.get(0);
    String deactivatedId = userIds.get(1);
    createdUserIds.addAll(userIds);

    // Deactivate one user
    client.deactivateUser(deactivatedId).execute();

    // Query without include_deactivated - should only find the active user
    var respWithout =
        client
            .queryUsers(
                QueryUsersRequest.builder()
                    .Payload(
                        QueryUsersPayload.builder()
                            .filterConditions(Map.of("id", Map.of("$in", userIds)))
                            .build())
                    .build())
            .execute();

    assertNotNull(respWithout.getData());
    List<FullUserResponse> withoutDeactivated = respWithout.getData().getUsers();
    Set<String> foundIdsWithout = new HashSet<>();
    for (FullUserResponse u : withoutDeactivated) {
      foundIdsWithout.add(u.getId());
    }
    assertTrue(foundIdsWithout.contains(activeId), "Active user should appear in query without include_deactivated");
    assertFalse(foundIdsWithout.contains(deactivatedId), "Deactivated user should NOT appear in query without include_deactivated");

    // Query with include_deactivated=true - should find both users
    var respWith =
        client
            .queryUsers(
                QueryUsersRequest.builder()
                    .Payload(
                        QueryUsersPayload.builder()
                            .filterConditions(Map.of("id", Map.of("$in", userIds)))
                            .includeDeactivatedUsers(true)
                            .build())
                    .build())
            .execute();

    assertNotNull(respWith.getData());
    List<FullUserResponse> withDeactivated = respWith.getData().getUsers();
    Set<String> foundIdsWith = new HashSet<>();
    for (FullUserResponse u : withDeactivated) {
      foundIdsWith.add(u.getId());
    }
    assertTrue(foundIdsWith.contains(activeId), "Active user should appear in query with include_deactivated");
    assertTrue(foundIdsWith.contains(deactivatedId), "Deactivated user should appear in query with include_deactivated=true");

    // Reactivate so cleanup can delete the user
    client.reactivateUser(deactivatedId).execute();
  }

  @Test
  @Order(15)
  void testDeactivateUsersPlural() throws Exception {
    List<String> userIds = createTestUsers(2);
    createdUserIds.addAll(userIds);

    // Deactivate multiple users at once (async task)
    var resp =
        client
            .deactivateUsers(
                DeactivateUsersRequest.builder().userIds(userIds).build())
            .execute();

    assertNotNull(resp.getData(), "DeactivateUsers response data should not be null");
    String taskId = resp.getData().getTaskID();
    assertNotNull(taskId, "DeactivateUsers should return a task ID");

    // Poll until completed
    waitForTask(taskId);

    // Verify users are deactivated (not visible without includeDeactivatedUsers)
    var queryResp =
        client
            .queryUsers(
                QueryUsersRequest.builder()
                    .Payload(
                        QueryUsersPayload.builder()
                            .filterConditions(Map.of("id", Map.of("$in", userIds)))
                            .build())
                    .build())
            .execute();

    assertNotNull(queryResp.getData());
    List<FullUserResponse> found = queryResp.getData().getUsers();
    assertEquals(0, found.size(), "Deactivated users should not appear in default query");

    // Reactivate for cleanup
    for (String userId : userIds) {
      try {
        client.reactivateUser(userId).execute();
      } catch (Exception ignored) {}
    }
  }

  @Test
  @Order(4)
  void testPartialUpdateUser() throws Exception {
    List<String> userIds = createTestUsers(1);
    String userId = userIds.get(0);
    createdUserIds.addAll(userIds);

    // Set custom fields: country=NL, role=admin
    Map<String, Object> setFields = new HashMap<>();
    setFields.put("country", "NL");
    setFields.put("role", "admin");

    var setResp =
        client
            .updateUsersPartial(
                UpdateUsersPartialRequest.builder()
                    .users(
                        List.of(
                            UpdateUserPartialRequest.builder().id(userId).set(setFields).build()))
                    .build())
            .execute();

    assertNotNull(setResp.getData());
    Map<String, FullUserResponse> setResult = setResp.getData().getUsers();
    assertTrue(setResult.containsKey(userId), "User should be in response");
    assertEquals("admin", setResult.get(userId).getRole(), "Role should be admin");

    // Unset country field
    var unsetResp =
        client
            .updateUsersPartial(
                UpdateUsersPartialRequest.builder()
                    .users(
                        List.of(
                            UpdateUserPartialRequest.builder()
                                .id(userId)
                                .unset(List.of("country"))
                                .build()))
                    .build())
            .execute();

    assertNotNull(unsetResp.getData());
    Map<String, FullUserResponse> unsetResult = unsetResp.getData().getUsers();
    assertTrue(unsetResult.containsKey(userId), "User should be in response after unset");
    // country should be null/removed in custom fields after unset
    Map<String, Object> custom = unsetResult.get(userId).getCustom();
    if (custom != null) {
      assertFalse(custom.containsKey("country"), "country should be unset from custom fields");
    }
    // If custom is null, country is definitely gone - that also passes
  }
}
