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
  @Order(6)
  void testPartialUpdateChannel() throws Exception {
    List<String> userIds = createTestUsers(1);
    createdUserIds.addAll(userIds);
    String creatorId = userIds.get(0);

    String channelId = createTestChannel(creatorId);
    createdChannelIds.add(channelId);

    // Set custom fields: color=red, description=A test channel
    var setResp =
        chat.updateChannelPartial(
                "messaging",
                channelId,
                UpdateChannelPartialRequest.builder()
                    .set(Map.of("color", "red", "description", "A test channel"))
                    .build())
            .execute();

    assertNotNull(setResp.getData(), "PartialUpdate (set) response should not be null");
    assertNotNull(setResp.getData().getChannel(), "Channel in response should not be null");
    var custom = setResp.getData().getChannel().getCustom();
    assertNotNull(custom, "Custom data should not be null after set");
    assertEquals("red", custom.get("color"), "Custom field 'color' should be 'red'");

    // Unset 'color' and verify it's removed
    var unsetResp =
        chat.updateChannelPartial(
                "messaging",
                channelId,
                UpdateChannelPartialRequest.builder()
                    .unset(List.of("color"))
                    .build())
            .execute();

    assertNotNull(unsetResp.getData(), "PartialUpdate (unset) response should not be null");
    assertNotNull(unsetResp.getData().getChannel(), "Channel in response should not be null");
    var customAfterUnset = unsetResp.getData().getChannel().getCustom();
    assertTrue(
        customAfterUnset == null || !customAfterUnset.containsKey("color"),
        "Custom field 'color' should be removed after unset");
  }

  @Test
  @Order(9)
  void testAddRemoveMembers() throws Exception {
    List<String> userIds = createTestUsers(4);
    createdUserIds.addAll(userIds);
    String creatorId = userIds.get(0);
    String memberId1 = userIds.get(1);
    String memberId2 = userIds.get(2);
    String memberId3 = userIds.get(3);

    // Create channel with creator + member1
    String channelId = createTestChannelWithMembers(creatorId, List.of(creatorId, memberId1));
    createdChannelIds.add(channelId);

    // Add member2 and member3
    chat.updateChannel(
            "messaging",
            channelId,
            UpdateChannelRequest.builder()
                .addMembers(
                    List.of(
                        ChannelMemberRequest.builder().userID(memberId2).build(),
                        ChannelMemberRequest.builder().userID(memberId3).build()))
                .build())
        .execute();

    // Verify members added (should have at least 4 members)
    var resp1 =
        chat.getOrCreateChannel(
                "messaging",
                channelId,
                GetOrCreateChannelRequest.builder().build())
            .execute();
    assertNotNull(resp1.getData());
    assertTrue(
        resp1.getData().getMembers().size() >= 4,
        "Channel should have at least 4 members after add, got: "
            + resp1.getData().getMembers().size());

    // Remove member3
    chat.updateChannel(
            "messaging",
            channelId,
            UpdateChannelRequest.builder()
                .removeMembers(List.of(memberId3))
                .build())
        .execute();

    // Verify member3 is removed
    var resp2 =
        chat.getOrCreateChannel(
                "messaging",
                channelId,
                GetOrCreateChannelRequest.builder().build())
            .execute();
    assertNotNull(resp2.getData());
    boolean memberFound =
        resp2.getData().getMembers().stream()
            .anyMatch(m -> memberId3.equals(m.getUserID()));
    assertFalse(memberFound, "member3 should have been removed from the channel");
  }

  @Test
  @Order(8)
  void testHardDeleteChannels() throws Exception {
    List<String> userIds = createTestUsers(1);
    createdUserIds.addAll(userIds);
    String creatorId = userIds.get(0);

    // Create 2 channels but do NOT track them in createdChannelIds (we delete them explicitly)
    String channelId1 = createTestChannel(creatorId);
    String channelId2 = createTestChannel(creatorId);

    List<String> cids =
        List.of("messaging:" + channelId1, "messaging:" + channelId2);

    // Hard delete both channels via batch endpoint with retry for rate limiting
    String taskId = null;
    for (int i = 0; i < 10; i++) {
      try {
        var resp =
            chat.deleteChannels(
                    DeleteChannelsRequest.builder()
                        .cids(cids)
                        .hardDelete(true)
                        .build())
                .execute();
        assertNotNull(resp.getData(), "DeleteChannels response should not be null");
        taskId = resp.getData().getTaskID();
        break;
      } catch (Exception e) {
        if (!e.getMessage().contains("Too many requests")) throw e;
        Thread.sleep((i + 1) * 3000L);
      }
    }

    assertNotNull(taskId, "TaskID should not be null for hard delete");
    assertFalse(taskId.isEmpty(), "TaskID should not be empty");

    // Poll task until completed
    waitForTask(taskId);
  }

  @Test
  @Order(7)
  void testDeleteChannel() throws Exception {
    List<String> userIds = createTestUsers(1);
    createdUserIds.addAll(userIds);
    String creatorId = userIds.get(0);

    // Create channel but do NOT track it in createdChannelIds (we delete it explicitly)
    String channelId = createTestChannel(creatorId);

    // Soft delete the channel
    var resp =
        chat.deleteChannel(
                "messaging",
                channelId,
                DeleteChannelRequest.builder().build())
            .execute();

    assertNotNull(resp.getData(), "DeleteChannel response should not be null");
    assertNotNull(resp.getData().getDuration(), "Duration should not be null");
    assertFalse(resp.getData().getDuration().isEmpty(), "Duration should not be empty");
    assertNotNull(resp.getData().getChannel(), "Channel should not be null on soft delete");
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
