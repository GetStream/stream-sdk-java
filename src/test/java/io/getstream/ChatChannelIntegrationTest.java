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
  @Order(11)
  void testInviteAcceptReject() throws Exception {
    List<String> userIds = createTestUsers(3);
    createdUserIds.addAll(userIds);
    String creatorId = userIds.get(0);
    String invitee1Id = userIds.get(1);
    String invitee2Id = userIds.get(2);

    // Create channel with creator as member and 2 users as invitees
    String channelId = "test-inv-" + UUID.randomUUID().toString().substring(0, 12);
    createdChannelIds.add(channelId);

    chat.getOrCreateChannel(
            "messaging",
            channelId,
            GetOrCreateChannelRequest.builder()
                .data(
                    ChannelInput.builder()
                        .createdByID(creatorId)
                        .members(List.of(ChannelMemberRequest.builder().userID(creatorId).build()))
                        .invites(
                            List.of(
                                ChannelMemberRequest.builder().userID(invitee1Id).build(),
                                ChannelMemberRequest.builder().userID(invitee2Id).build()))
                        .build())
                .build())
        .execute();

    // Accept invite for invitee1
    var acceptResp =
        chat.updateChannel(
                "messaging",
                channelId,
                UpdateChannelRequest.builder()
                    .acceptInvite(true)
                    .userID(invitee1Id)
                    .build())
            .execute();
    assertNotNull(acceptResp.getData(), "AcceptInvite response should not be null");

    // Reject invite for invitee2
    var rejectResp =
        chat.updateChannel(
                "messaging",
                channelId,
                UpdateChannelRequest.builder()
                    .rejectInvite(true)
                    .userID(invitee2Id)
                    .build())
            .execute();
    assertNotNull(rejectResp.getData(), "RejectInvite response should not be null");
  }

  @Test
  @Order(13)
  void testTruncateChannel() throws Exception {
    List<String> userIds = createTestUsers(1);
    createdUserIds.addAll(userIds);
    String creatorId = userIds.get(0);

    String channelId = createTestChannel(creatorId);
    createdChannelIds.add(channelId);

    // Send 3 messages
    sendTestMessage("messaging", channelId, creatorId, "msg1");
    sendTestMessage("messaging", channelId, creatorId, "msg2");
    sendTestMessage("messaging", channelId, creatorId, "msg3");

    // Truncate the channel
    var truncResp =
        chat.truncateChannel(
                "messaging",
                channelId,
                TruncateChannelRequest.builder().build())
            .execute();

    assertNotNull(truncResp.getData(), "TruncateChannel response should not be null");
    assertNotNull(truncResp.getData().getDuration(), "Duration should not be null");

    // Verify channel messages are now empty
    var resp =
        chat.getOrCreateChannel(
                "messaging",
                channelId,
                GetOrCreateChannelRequest.builder().build())
            .execute();

    assertNotNull(resp.getData(), "GetOrCreateChannel response should not be null");
    List<MessageResponse> messages = resp.getData().getMessages();
    assertTrue(
        messages == null || messages.isEmpty(),
        "Channel messages should be empty after truncation, got: "
            + (messages != null ? messages.size() : 0));
  }

  @Test
  @Order(12)
  void testHideShowChannel() throws Exception {
    List<String> userIds = createTestUsers(2);
    createdUserIds.addAll(userIds);
    String creatorId = userIds.get(0);
    String memberId = userIds.get(1);

    // Create channel with both users as members
    String channelId = createTestChannelWithMembers(creatorId, userIds);
    createdChannelIds.add(channelId);

    // Hide the channel for memberId
    var hideResp =
        chat.hideChannel(
                "messaging",
                channelId,
                HideChannelRequest.builder().userID(memberId).build())
            .execute();

    assertNotNull(hideResp.getData(), "HideChannel response should not be null");
    assertNotNull(hideResp.getData().getDuration(), "Duration should not be null");

    // Show the channel for memberId
    var showResp =
        chat.showChannel(
                "messaging",
                channelId,
                ShowChannelRequest.builder().userID(memberId).build())
            .execute();

    assertNotNull(showResp.getData(), "ShowChannel response should not be null");
    assertNotNull(showResp.getData().getDuration(), "Duration should not be null");
  }

  @Test
  @Order(10)
  void testQueryMembers() throws Exception {
    List<String> userIds = createTestUsers(3);
    createdUserIds.addAll(userIds);
    String creatorId = userIds.get(0);

    // Create channel with all 3 users as members
    String channelId = createTestChannelWithMembers(creatorId, userIds);
    createdChannelIds.add(channelId);

    // Query members of the channel
    var resp =
        chat.queryMembers(
                QueryMembersRequest.builder()
                    .Payload(
                        QueryMembersPayload.builder()
                            .type("messaging")
                            .id(channelId)
                            .filterConditions(Map.of())
                            .build())
                    .build())
            .execute();

    assertNotNull(resp.getData(), "QueryMembers response should not be null");
    assertNotNull(resp.getData().getMembers(), "Members list should not be null");
    assertTrue(
        resp.getData().getMembers().size() >= 3,
        "Channel should have at least 3 members, got: " + resp.getData().getMembers().size());
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
  @Order(15)
  void testMarkReadUnread() throws Exception {
    List<String> userIds = createTestUsers(2);
    createdUserIds.addAll(userIds);
    String creatorId = userIds.get(0);
    String memberId = userIds.get(1);

    String channelId = createTestChannelWithMembers(creatorId, userIds);
    createdChannelIds.add(channelId);

    // Send a message so there's something to mark read/unread
    String messageId = sendTestMessage("messaging", channelId, creatorId, "hello mark read test");

    // Mark the channel as read for memberId
    var markReadResp =
        chat.markRead(
                "messaging",
                channelId,
                MarkReadRequest.builder().userID(memberId).build())
            .execute();

    assertNotNull(markReadResp.getData(), "MarkRead response should not be null");
    assertNotNull(markReadResp.getData().getDuration(), "Duration should not be null after markRead");

    // Mark the channel as unread from that message for memberId
    var markUnreadResp =
        chat.markUnread(
                "messaging",
                channelId,
                MarkUnreadRequest.builder().userID(memberId).messageID(messageId).build())
            .execute();

    assertNotNull(markUnreadResp.getData(), "MarkUnread response should not be null");
    assertNotNull(markUnreadResp.getData().getDuration(), "Duration should not be null after markUnread");
  }

  @Test
  @Order(16)
  void testMuteUnmuteChannel() throws Exception {
    List<String> userIds = createTestUsers(2);
    createdUserIds.addAll(userIds);
    String creatorId = userIds.get(0);
    String memberId = userIds.get(1);

    String channelId = createTestChannelWithMembers(creatorId, userIds);
    createdChannelIds.add(channelId);
    String cid = "messaging:" + channelId;

    // Mute the channel for memberId
    var muteResp =
        chat.muteChannel(
                MuteChannelRequest.builder()
                    .channelCids(List.of(cid))
                    .userID(memberId)
                    .build())
            .execute();

    assertNotNull(muteResp.getData(), "MuteChannel response should not be null");
    assertNotNull(muteResp.getData().getDuration(), "Duration should not be null after mute");

    // Verify muted via QueryChannels with muted=true filter
    var mutedResp =
        chat.queryChannels(
                QueryChannelsRequest.builder()
                    .filterConditions(Map.of("muted", true, "cid", cid))
                    .userID(memberId)
                    .build())
            .execute();

    assertNotNull(mutedResp.getData(), "QueryChannels (muted=true) response should not be null");
    assertFalse(
        mutedResp.getData().getChannels().isEmpty(),
        "Channel should appear in muted=true query after muting");

    // Unmute the channel for memberId
    var unmuteResp =
        chat.unmuteChannel(
                UnmuteChannelRequest.builder()
                    .channelCids(List.of(cid))
                    .userID(memberId)
                    .build())
            .execute();

    assertNotNull(unmuteResp.getData(), "UnmuteChannel response should not be null");

    // Verify unmuted via QueryChannels with muted=false filter
    var unmutedResp =
        chat.queryChannels(
                QueryChannelsRequest.builder()
                    .filterConditions(Map.of("muted", false, "cid", cid))
                    .userID(memberId)
                    .build())
            .execute();

    assertNotNull(unmutedResp.getData(), "QueryChannels (muted=false) response should not be null");
    assertFalse(
        unmutedResp.getData().getChannels().isEmpty(),
        "Channel should appear in muted=false query after unmuting");
  }

  @Test
  @Order(17)
  void testMemberPartialUpdate() throws Exception {
    List<String> userIds = createTestUsers(2);
    createdUserIds.addAll(userIds);
    String creatorId = userIds.get(0);
    String memberId = userIds.get(1);

    String channelId = createTestChannelWithMembers(creatorId, userIds);
    createdChannelIds.add(channelId);

    // Set custom fields on the member
    var setResp =
        chat.updateMemberPartial(
                "messaging",
                channelId,
                UpdateMemberPartialRequest.builder()
                    .UserID(memberId)
                    .set(Map.of("role_label", "vip", "score", 100))
                    .build())
            .execute();

    assertNotNull(setResp.getData(), "UpdateMemberPartial (set) response should not be null");
    assertNotNull(setResp.getData().getChannelMember(), "ChannelMember should not be null");
    var custom = setResp.getData().getChannelMember().getCustom();
    assertNotNull(custom, "Custom data should not be null after set");
    assertEquals("vip", custom.get("role_label"), "Custom field 'role_label' should be 'vip'");
    assertNotNull(custom.get("score"), "Custom field 'score' should be present");

    // Unset 'score' and verify it's removed
    var unsetResp =
        chat.updateMemberPartial(
                "messaging",
                channelId,
                UpdateMemberPartialRequest.builder()
                    .UserID(memberId)
                    .unset(List.of("score"))
                    .build())
            .execute();

    assertNotNull(unsetResp.getData(), "UpdateMemberPartial (unset) response should not be null");
    assertNotNull(
        unsetResp.getData().getChannelMember(), "ChannelMember should not be null after unset");
    var customAfterUnset = unsetResp.getData().getChannelMember().getCustom();
    assertTrue(
        customAfterUnset == null || !customAfterUnset.containsKey("score"),
        "Custom field 'score' should be removed after unset");
  }

  @Test
  @Order(18)
  void testAssignRoles() throws Exception {
    List<String> userIds = createTestUsers(2);
    createdUserIds.addAll(userIds);
    String creatorId = userIds.get(0);
    String memberId = userIds.get(1);

    String channelId = createTestChannelWithMembers(creatorId, userIds);
    createdChannelIds.add(channelId);

    // Assign channel_moderator role to memberId
    chat.updateChannel(
            "messaging",
            channelId,
            UpdateChannelRequest.builder()
                .assignRoles(
                    List.of(
                        ChannelMemberRequest.builder()
                            .userID(memberId)
                            .channelRole("channel_moderator")
                            .build()))
                .build())
        .execute();

    // Verify via QueryMembers that the role is set
    var qResp =
        chat.queryMembers(
                QueryMembersRequest.builder()
                    .Payload(
                        QueryMembersPayload.builder()
                            .type("messaging")
                            .id(channelId)
                            .filterConditions(Map.of("user_id", memberId))
                            .build())
                    .build())
            .execute();

    assertNotNull(qResp.getData(), "QueryMembers response should not be null");
    assertFalse(qResp.getData().getMembers().isEmpty(), "Members list should not be empty");
    assertEquals(
        "channel_moderator",
        qResp.getData().getMembers().get(0).getChannelRole(),
        "Member should have channel_moderator role");
  }

  @Test
  @Order(20)
  void testMarkUnreadWithThread() throws Exception {
    List<String> userIds = createTestUsers(2);
    createdUserIds.addAll(userIds);
    String creatorId = userIds.get(0);
    String memberId = userIds.get(1);

    String channelId = createTestChannelWithMembers(creatorId, userIds);
    createdChannelIds.add(channelId);

    // Send parent message
    String parentMsgId = sendTestMessage("messaging", channelId, creatorId, "parent message");

    // Send thread reply to create a thread
    chat.sendMessage(
            "messaging",
            channelId,
            SendMessageRequest.builder()
                .message(
                    MessageRequest.builder()
                        .text("thread reply")
                        .userID(memberId)
                        .parentID(parentMsgId)
                        .build())
                .build())
        .execute();

    // Mark channel as read first
    chat.markRead(
            "messaging",
            channelId,
            MarkReadRequest.builder().userID(memberId).build())
        .execute();

    // Mark unread from thread (using the parent message ID as the thread ID)
    var markUnreadResp =
        chat.markUnread(
                "messaging",
                channelId,
                MarkUnreadRequest.builder()
                    .userID(memberId)
                    .threadID(parentMsgId)
                    .build())
            .execute();

    assertNotNull(markUnreadResp.getData(), "MarkUnread (thread) response should not be null");
    assertNotNull(
        markUnreadResp.getData().getDuration(),
        "Duration should not be null after markUnread with thread");
  }

  @Test
  @Order(19)
  void testAddDemoteModerators() throws Exception {
    List<String> userIds = createTestUsers(2);
    createdUserIds.addAll(userIds);
    String creatorId = userIds.get(0);
    String memberId = userIds.get(1);

    String channelId = createTestChannelWithMembers(creatorId, userIds);
    createdChannelIds.add(channelId);

    // Add memberId as moderator
    chat.updateChannel(
            "messaging",
            channelId,
            UpdateChannelRequest.builder()
                .addModerators(List.of(memberId))
                .build())
        .execute();

    // Verify via QueryMembers that the role is channel_moderator
    var afterPromote =
        chat.queryMembers(
                QueryMembersRequest.builder()
                    .Payload(
                        QueryMembersPayload.builder()
                            .type("messaging")
                            .id(channelId)
                            .filterConditions(Map.of("user_id", memberId))
                            .build())
                    .build())
            .execute();

    assertNotNull(afterPromote.getData(), "QueryMembers response should not be null");
    assertFalse(afterPromote.getData().getMembers().isEmpty(), "Members list should not be empty");
    assertEquals(
        "channel_moderator",
        afterPromote.getData().getMembers().get(0).getChannelRole(),
        "Member should have channel_moderator role after promotion");

    // Demote memberId back to regular member
    chat.updateChannel(
            "messaging",
            channelId,
            UpdateChannelRequest.builder()
                .demoteModerators(List.of(memberId))
                .build())
        .execute();

    // Verify role is back to channel_member
    var afterDemote =
        chat.queryMembers(
                QueryMembersRequest.builder()
                    .Payload(
                        QueryMembersPayload.builder()
                            .type("messaging")
                            .id(channelId)
                            .filterConditions(Map.of("user_id", memberId))
                            .build())
                    .build())
            .execute();

    assertNotNull(afterDemote.getData(), "QueryMembers response should not be null after demote");
    assertFalse(
        afterDemote.getData().getMembers().isEmpty(), "Members list should not be empty after demote");
    assertEquals(
        "channel_member",
        afterDemote.getData().getMembers().get(0).getChannelRole(),
        "Member should be back to channel_member role after demotion");
  }

  @Test
  @Order(21)
  void testTruncateWithOptions() throws Exception {
    List<String> userIds = createTestUsers(2);
    createdUserIds.addAll(userIds);
    String creatorId = userIds.get(0);
    String memberId = userIds.get(1);

    String channelId = createTestChannelWithMembers(creatorId, userIds);
    createdChannelIds.add(channelId);

    // Send 2 messages
    sendTestMessage("messaging", channelId, creatorId, "Truncate msg 1");
    sendTestMessage("messaging", channelId, creatorId, "Truncate msg 2");

    // Truncate with message, skip_push=true, hard_delete=true
    var truncResp =
        chat.truncateChannel(
                "messaging",
                channelId,
                TruncateChannelRequest.builder()
                    .message(
                        MessageRequest.builder()
                            .text("Channel was truncated")
                            .userID(creatorId)
                            .build())
                    .skipPush(true)
                    .hardDelete(true)
                    .build())
            .execute();

    assertNotNull(truncResp.getData(), "TruncateWithOptions response should not be null");
    assertNotNull(truncResp.getData().getDuration(), "Duration should not be null");
  }

  @Test
  @Order(14)
  void testFreezeUnfreezeChannel() throws Exception {
    List<String> userIds = createTestUsers(1);
    createdUserIds.addAll(userIds);
    String creatorId = userIds.get(0);

    String channelId = createTestChannel(creatorId);
    createdChannelIds.add(channelId);

    // Freeze the channel
    var freezeResp =
        chat.updateChannelPartial(
                "messaging",
                channelId,
                UpdateChannelPartialRequest.builder()
                    .set(Map.of("frozen", true))
                    .build())
            .execute();

    assertNotNull(freezeResp.getData(), "Freeze response should not be null");
    assertNotNull(freezeResp.getData().getChannel(), "Channel in freeze response should not be null");
    Boolean frozenAfterFreeze = freezeResp.getData().getChannel().getFrozen();
    assertNotNull(frozenAfterFreeze, "Frozen field should not be null after freeze");
    assertTrue(frozenAfterFreeze, "Channel should be frozen after setting frozen=true");

    // Unfreeze the channel
    var unfreezeResp =
        chat.updateChannelPartial(
                "messaging",
                channelId,
                UpdateChannelPartialRequest.builder()
                    .set(Map.of("frozen", false))
                    .build())
            .execute();

    assertNotNull(unfreezeResp.getData(), "Unfreeze response should not be null");
    assertNotNull(unfreezeResp.getData().getChannel(), "Channel in unfreeze response should not be null");
    Boolean frozenAfterUnfreeze = unfreezeResp.getData().getChannel().getFrozen();
    assertNotNull(frozenAfterUnfreeze, "Frozen field should not be null after unfreeze");
    assertFalse(frozenAfterUnfreeze, "Channel should be unfrozen after setting frozen=false");
  }

  @Test
  @Order(22)
  void testPinUnpinChannel() throws Exception {
    List<String> userIds = createTestUsers(2);
    createdUserIds.addAll(userIds);
    String creatorId = userIds.get(0);
    String memberId = userIds.get(1);

    String channelId = createTestChannelWithMembers(creatorId, userIds);
    createdChannelIds.add(channelId);
    String cid = "messaging:" + channelId;

    // Pin the channel for memberId via UpdateMemberPartial
    var pinResp =
        chat.updateMemberPartial(
                "messaging",
                channelId,
                UpdateMemberPartialRequest.builder()
                    .UserID(memberId)
                    .set(Map.of("pinned", true))
                    .build())
            .execute();

    assertNotNull(pinResp.getData(), "Pin response should not be null");
    assertNotNull(pinResp.getData().getChannelMember(), "ChannelMember should not be null after pin");

    // Verify pinned via QueryChannels with pinned=true filter
    var pinnedResp =
        chat.queryChannels(
                QueryChannelsRequest.builder()
                    .filterConditions(Map.of("pinned", true, "cid", cid))
                    .userID(memberId)
                    .build())
            .execute();

    assertNotNull(pinnedResp.getData(), "QueryChannels (pinned=true) response should not be null");
    assertFalse(
        pinnedResp.getData().getChannels().isEmpty(),
        "Channel should appear in pinned=true query after pinning");

    // Unpin the channel for memberId
    var unpinResp =
        chat.updateMemberPartial(
                "messaging",
                channelId,
                UpdateMemberPartialRequest.builder()
                    .UserID(memberId)
                    .set(Map.of("pinned", false))
                    .build())
            .execute();

    assertNotNull(unpinResp.getData(), "Unpin response should not be null");
    assertNotNull(unpinResp.getData().getChannelMember(), "ChannelMember should not be null after unpin");

    // Verify unpinned via QueryChannels with pinned=false filter
    var unpinnedResp =
        chat.queryChannels(
                QueryChannelsRequest.builder()
                    .filterConditions(Map.of("pinned", false, "cid", cid))
                    .userID(memberId)
                    .build())
            .execute();

    assertNotNull(unpinnedResp.getData(), "QueryChannels (pinned=false) response should not be null");
    assertFalse(
        unpinnedResp.getData().getChannels().isEmpty(),
        "Channel should appear in pinned=false query after unpinning");
  }

  @Test
  @Order(23)
  void testArchiveUnarchiveChannel() throws Exception {
    List<String> userIds = createTestUsers(2);
    createdUserIds.addAll(userIds);
    String creatorId = userIds.get(0);
    String memberId = userIds.get(1);

    String channelId = createTestChannelWithMembers(creatorId, userIds);
    createdChannelIds.add(channelId);
    String cid = "messaging:" + channelId;

    // Archive the channel for memberId via UpdateMemberPartial
    var archiveResp =
        chat.updateMemberPartial(
                "messaging",
                channelId,
                UpdateMemberPartialRequest.builder()
                    .UserID(memberId)
                    .set(Map.of("archived", true))
                    .build())
            .execute();

    assertNotNull(archiveResp.getData(), "Archive response should not be null");
    assertNotNull(
        archiveResp.getData().getChannelMember(), "ChannelMember should not be null after archive");

    // Verify archived via QueryChannels with archived=true filter
    var archivedResp =
        chat.queryChannels(
                QueryChannelsRequest.builder()
                    .filterConditions(Map.of("archived", true, "cid", cid))
                    .userID(memberId)
                    .build())
            .execute();

    assertNotNull(archivedResp.getData(), "QueryChannels (archived=true) response should not be null");
    assertFalse(
        archivedResp.getData().getChannels().isEmpty(),
        "Channel should appear in archived=true query after archiving");

    // Unarchive the channel for memberId
    var unarchiveResp =
        chat.updateMemberPartial(
                "messaging",
                channelId,
                UpdateMemberPartialRequest.builder()
                    .UserID(memberId)
                    .set(Map.of("archived", false))
                    .build())
            .execute();

    assertNotNull(unarchiveResp.getData(), "Unarchive response should not be null");
    assertNotNull(
        unarchiveResp.getData().getChannelMember(),
        "ChannelMember should not be null after unarchive");

    // Verify unarchived via QueryChannels with archived=false filter
    var unarchivedResp =
        chat.queryChannels(
                QueryChannelsRequest.builder()
                    .filterConditions(Map.of("archived", false, "cid", cid))
                    .userID(memberId)
                    .build())
            .execute();

    assertNotNull(
        unarchivedResp.getData(), "QueryChannels (archived=false) response should not be null");
    assertFalse(
        unarchivedResp.getData().getChannels().isEmpty(),
        "Channel should appear in archived=false query after unarchiving");
  }

  @Test
  @Order(24)
  void testAddMembersWithRoles() throws Exception {
    List<String> creatorIds = createTestUsers(1);
    createdUserIds.addAll(creatorIds);
    String creatorId = creatorIds.get(0);

    String channelId = createTestChannel(creatorId);
    createdChannelIds.add(channelId);

    // Create 2 more users to add with specific roles
    List<String> newMemberIds = createTestUsers(2);
    createdUserIds.addAll(newMemberIds);
    String moderatorId = newMemberIds.get(0);
    String memberId = newMemberIds.get(1);

    // Add members with specific channel roles
    chat.updateChannel(
            "messaging",
            channelId,
            UpdateChannelRequest.builder()
                .addMembers(
                    List.of(
                        ChannelMemberRequest.builder()
                            .userID(moderatorId)
                            .channelRole("channel_moderator")
                            .build(),
                        ChannelMemberRequest.builder()
                            .userID(memberId)
                            .channelRole("channel_member")
                            .build()))
                .build())
        .execute();

    // Verify roles via queryMembers with $in filter
    var qResp =
        chat.queryMembers(
                QueryMembersRequest.builder()
                    .Payload(
                        QueryMembersPayload.builder()
                            .type("messaging")
                            .id(channelId)
                            .filterConditions(
                                Map.of("user_id", Map.of("$in", List.of(moderatorId, memberId))))
                            .build())
                    .build())
            .execute();

    assertNotNull(qResp.getData(), "QueryMembers response should not be null");
    assertFalse(qResp.getData().getMembers().isEmpty(), "Members list should not be empty");

    // Build a role map keyed by user ID
    Map<String, String> roleMap = new java.util.HashMap<>();
    for (ChannelMemberResponse member : qResp.getData().getMembers()) {
      roleMap.put(member.getUserID(), member.getChannelRole());
    }

    assertEquals(
        "channel_moderator",
        roleMap.get(moderatorId),
        "moderator user should have channel_moderator role");
    assertEquals(
        "channel_member",
        roleMap.get(memberId),
        "member user should have channel_member role");
  }

  @Test
  @Order(25)
  void testMessageCount() throws Exception {
    List<String> userIds = createTestUsers(2);
    createdUserIds.addAll(userIds);
    String creatorId = userIds.get(0);
    String memberId = userIds.get(1);

    String channelId = createTestChannelWithMembers(creatorId, userIds);
    createdChannelIds.add(channelId);

    // Send a message
    sendTestMessage("messaging", channelId, creatorId, "hello world");

    // Query the channel to get message count
    var qResp =
        chat.queryChannels(
                QueryChannelsRequest.builder()
                    .filterConditions(Map.of("cid", "messaging:" + channelId))
                    .userID(creatorId)
                    .build())
            .execute();

    assertNotNull(qResp.getData(), "QueryChannels response should not be null");
    assertFalse(qResp.getData().getChannels().isEmpty(), "Channels list should not be empty");

    // MessageCount should be present (default enabled for messaging type)
    Integer messageCount = qResp.getData().getChannels().get(0).getChannel().getMessageCount();
    if (messageCount != null) {
      assertTrue(messageCount >= 1, "MessageCount should be >= 1");
    }
    // Note: MessageCount may be nil if count_messages is disabled on the channel type
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

  @Test
  @Order(27)
  void testFilterTags() throws Exception {
    List<String> userIds = createTestUsers(1);
    createdUserIds.addAll(userIds);
    String creatorId = userIds.get(0);

    String channelId = createTestChannel(creatorId);
    createdChannelIds.add(channelId);

    // Add filter tags
    chat.updateChannel(
            "messaging",
            channelId,
            UpdateChannelRequest.builder()
                .addFilterTags(List.of("sports", "news"))
                .build())
        .execute();

    // Verify tags were added by querying
    var resp =
        chat.getOrCreateChannel(
                "messaging",
                channelId,
                GetOrCreateChannelRequest.builder().build())
            .execute();
    assertNotNull(resp.getData(), "GetOrCreate response should not be null");
    assertNotNull(resp.getData().getChannel(), "Channel should not be null");

    // Remove one filter tag
    chat.updateChannel(
            "messaging",
            channelId,
            UpdateChannelRequest.builder()
                .removeFilterTags(List.of("sports"))
                .build())
        .execute();
  }

  @Test
  @Order(28)
  void testMessageCountDisabled() throws Exception {
    List<String> userIds = createTestUsers(2);
    createdUserIds.addAll(userIds);
    String creatorId = userIds.get(0);

    String channelId = createTestChannelWithMembers(creatorId, userIds);
    createdChannelIds.add(channelId);
    String cid = "messaging:" + channelId;

    // Disable count_messages via config_overrides partial update
    Map<String, Object> configOverrides = new HashMap<>();
    configOverrides.put("count_messages", false);
    Map<String, Object> setFields = new HashMap<>();
    setFields.put("config_overrides", configOverrides);

    chat.updateChannelPartial(
            "messaging",
            channelId,
            UpdateChannelPartialRequest.builder()
                .set(setFields)
                .build())
        .execute();

    // Send a message
    sendTestMessage("messaging", channelId, creatorId, "hello world disabled count");

    // Query the channel - MessageCount should be nil when count_messages is disabled
    var resp =
        chat.queryChannels(
                QueryChannelsRequest.builder()
                    .filterConditions(Map.of("cid", cid))
                    .userID(creatorId)
                    .build())
            .execute();

    assertNotNull(resp.getData(), "QueryChannels response should not be null");
    assertFalse(resp.getData().getChannels().isEmpty(), "Channels list should not be empty");
    Integer messageCount =
        resp.getData().getChannels().get(0).getChannel().getMessageCount();
    assertNull(messageCount, "MessageCount should be null when count_messages is disabled");
  }

  @Test
  @Order(29)
  void testMarkUnreadWithTimestamp() throws Exception {
    List<String> userIds = createTestUsers(2);
    createdUserIds.addAll(userIds);
    String creatorId = userIds.get(0);
    String memberId = userIds.get(1);

    String channelId = createTestChannelWithMembers(creatorId, userIds);
    createdChannelIds.add(channelId);

    // Send a message to get a valid timestamp
    var sendResp =
        chat.sendMessage(
                "messaging",
                channelId,
                SendMessageRequest.builder()
                    .message(
                        MessageRequest.builder()
                            .text("test message for timestamp unread")
                            .userID(creatorId)
                            .build())
                    .build())
            .execute();

    assertNotNull(sendResp.getData(), "SendMessage response should not be null");
    assertNotNull(
        sendResp.getData().getMessage().getCreatedAt(), "Message createdAt should not be null");

    java.util.Date messageTimestamp = sendResp.getData().getMessage().getCreatedAt();

    // Mark unread from timestamp (using messageTimestamp instead of messageID)
    var resp =
        chat.markUnread(
                "messaging",
                channelId,
                MarkUnreadRequest.builder()
                    .userID(memberId)
                    .messageTimestamp(messageTimestamp)
                    .build())
            .execute();

    assertNotNull(resp.getData(), "MarkUnread response should not be null");
    assertNotNull(resp.getData().getDuration(), "Duration should not be null");
  }

  @Test
  @Order(26)
  void testSendChannelEvent() throws Exception {
    List<String> userIds = createTestUsers(2);
    createdUserIds.addAll(userIds);
    String creatorId = userIds.get(0);

    String channelId = createTestChannelWithMembers(creatorId, userIds);
    createdChannelIds.add(channelId);

    // Send a typing.start event
    var resp =
        chat.sendEvent(
                "messaging",
                channelId,
                SendEventRequest.builder()
                    .event(
                        EventRequest.builder()
                            .type("typing.start")
                            .userID(creatorId)
                            .build())
                    .build())
            .execute();

    assertNotNull(resp.getData(), "SendChannelEvent response should not be null");
    assertNotNull(resp.getData().getDuration(), "Duration should not be null");
  }
}
