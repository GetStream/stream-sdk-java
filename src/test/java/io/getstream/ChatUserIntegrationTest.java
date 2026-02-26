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
