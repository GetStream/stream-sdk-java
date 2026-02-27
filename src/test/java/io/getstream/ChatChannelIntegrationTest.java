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

  @Test
  @Order(2)
  void testCreateChannelWithMembers() throws Exception {
    List<String> userIds = createTestUsers(3);
    createdUserIds.addAll(userIds);

    String channelId = createTestChannelWithMembers(userIds.get(0), userIds);
    createdChannelIds.add(channelId);

    // Query the channel back and verify member count >= 3
    var resp =
        chat.queryChannels(
                QueryChannelsRequest.builder()
                    .filterConditions(Map.of("id", channelId))
                    .build())
            .execute();

    assertNotNull(resp.getData());
    assertFalse(resp.getData().getChannels().isEmpty(), "QueryChannels should return the channel");
    var channelState = resp.getData().getChannels().get(0);
    assertNotNull(channelState.getMembers(), "Channel members should not be null");
    assertTrue(
        channelState.getMembers().size() >= 3,
        "Channel should have at least 3 members, got: " + channelState.getMembers().size());
  }

  @Test
  @Order(3)
  void testCreateDistinctChannel() throws Exception {
    List<String> userIds = createTestUsers(2);
    createdUserIds.addAll(userIds);
    String creatorId = userIds.get(0);
    String memberId = userIds.get(1);

    List<ChannelMemberRequest> members =
        List.of(
            ChannelMemberRequest.builder().userID(creatorId).build(),
            ChannelMemberRequest.builder().userID(memberId).build());

    // Create distinct channel (no explicit channel ID)
    var resp1 =
        chat.getOrCreateDistinctChannel(
                "messaging",
                GetOrCreateDistinctChannelRequest.builder()
                    .data(
                        ChannelInput.builder().createdByID(creatorId).members(members).build())
                    .build())
            .execute();

    assertNotNull(resp1.getData());
    assertNotNull(resp1.getData().getChannel(), "Channel should not be null");
    String cid1 = resp1.getData().getChannel().getCid();
    String channelId1 = resp1.getData().getChannel().getId();
    assertNotNull(cid1, "CID should not be null");
    createdChannelIds.add(channelId1);

    // Call again with same members - should return same channel
    var resp2 =
        chat.getOrCreateDistinctChannel(
                "messaging",
                GetOrCreateDistinctChannelRequest.builder()
                    .data(
                        ChannelInput.builder().createdByID(creatorId).members(members).build())
                    .build())
            .execute();

    assertNotNull(resp2.getData());
    assertNotNull(resp2.getData().getChannel(), "Channel should not be null on second call");
    String cid2 = resp2.getData().getChannel().getCid();
    assertEquals(cid1, cid2, "Same members should return the same channel CID");
  }

  @Test
  @Order(4)
  void testQueryChannels() throws Exception {
    List<String> userIds = createTestUsers(1);
    createdUserIds.addAll(userIds);
    String creatorId = userIds.get(0);

    String channelId = createTestChannel(creatorId);
    createdChannelIds.add(channelId);

    // Query by both type and id
    var resp =
        chat.queryChannels(
                QueryChannelsRequest.builder()
                    .filterConditions(Map.of("type", "messaging", "id", channelId))
                    .build())
            .execute();

    assertNotNull(resp.getData());
    assertFalse(resp.getData().getChannels().isEmpty(), "QueryChannels should return the channel");
    assertEquals(channelId, resp.getData().getChannels().get(0).getChannel().getId());
  }

  @Test
  @Order(5)
  void testUpdateChannel() throws Exception {
    List<String> userIds = createTestUsers(1);
    createdUserIds.addAll(userIds);
    String creatorId = userIds.get(0);

    String channelId = createTestChannel(creatorId);
    createdChannelIds.add(channelId);

    // Update channel with custom data and a system message
    var resp =
        chat.updateChannel(
                "messaging",
                channelId,
                UpdateChannelRequest.builder()
                    .data(
                        ChannelInputRequest.builder()
                            .custom(Map.of("color", "blue"))
                            .build())
                    .message(
                        MessageRequest.builder()
                            .text("Channel updated")
                            .userID(creatorId)
                            .build())
                    .build())
            .execute();

    assertNotNull(resp.getData(), "UpdateChannel response should not be null");
    assertNotNull(resp.getData().getChannel(), "Channel in response should not be null");
    var custom = resp.getData().getChannel().getCustom();
    assertNotNull(custom, "Channel custom data should not be null");
    assertEquals("blue", custom.get("color"), "Custom field 'color' should be 'blue'");
  }
}
