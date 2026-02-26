package io.getstream;

import static org.junit.jupiter.api.Assertions.*;

import io.getstream.models.*;
import java.util.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ChatUserIntegrationTest extends ChatTestBase {

  private final List<String> createdUserIds = new ArrayList<>();

  @AfterAll
  void cleanup() {
    if (!createdUserIds.isEmpty()) {
      deleteUsersWithRetry(createdUserIds);
    }
  }

  @Test
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
}
