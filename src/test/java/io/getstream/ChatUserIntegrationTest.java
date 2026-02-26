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
}
