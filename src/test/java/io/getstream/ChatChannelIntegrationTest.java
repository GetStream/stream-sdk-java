package io.getstream;

import static org.junit.jupiter.api.Assertions.*;

import io.getstream.models.*;
import java.util.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ChatChannelIntegrationTest extends ChatTestBase {

  private final List<String> createdUserIds = new ArrayList<>();
  private final List<String> createdChannelIds = new ArrayList<>();

  @AfterAll
  void cleanup() {
    for (String channelId : createdChannelIds) {
      try {
        chat.deleteChannel(
                "messaging",
                channelId,
                DeleteChannelRequest.builder().HardDelete(true).build())
            .execute();
      } catch (Exception ignored) {
      }
    }
    if (!createdUserIds.isEmpty()) {
      deleteUsersWithRetry(createdUserIds);
    }
  }

  @Test
  @Order(1)
  void testCreateChannelWithID() throws Exception {
    List<String> userIds = createTestUsers(1);
    createdUserIds.addAll(userIds);
    String creatorId = userIds.get(0);

    String channelId = createTestChannel(creatorId);
    createdChannelIds.add(channelId);

    // Verify channel exists by querying
    var resp =
        chat.queryChannels(
                QueryChannelsRequest.builder()
                    .filterConditions(Map.of("id", channelId))
                    .build())
            .execute();

    assertNotNull(resp.getData());
    assertFalse(resp.getData().getChannels().isEmpty(), "QueryChannels should return the channel");
    assertEquals(channelId, resp.getData().getChannels().get(0).getChannel().getId());
    assertEquals("messaging", resp.getData().getChannels().get(0).getChannel().getType());
  }
}
