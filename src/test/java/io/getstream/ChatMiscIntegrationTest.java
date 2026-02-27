package io.getstream;

import static org.junit.jupiter.api.Assertions.*;

import io.getstream.models.*;
import io.getstream.services.ModerationImpl;
import java.util.*;
import java.util.Map;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ChatMiscIntegrationTest extends ChatTestBase {

  private final List<String> createdUserIds = new ArrayList<>();

  @AfterAll
  void cleanup() {
    if (!createdUserIds.isEmpty()) {
      deleteUsersWithRetry(createdUserIds);
    }
  }

  @Test
  @Order(4)
  void testCreateUpdateDeleteChannelType() throws Exception {
    // Channel type names must be lowercase alphanumeric
    String typeName = "testtype" + randomString(6).toLowerCase();

    try {
      // Create channel type
      var createResp =
          client
              .chat()
              .createChannelType(
                  CreateChannelTypeRequest.builder()
                      .name(typeName)
                      .automod("disabled")
                      .automodBehavior("flag")
                      .maxMessageLength(1000)
                      .build())
              .execute();
      assertNotNull(createResp.getData().getDuration());
      assertEquals(typeName, createResp.getData().getName());

      // CRITICAL: 6-second delay for eventual consistency
      Thread.sleep(6000);

      // Get channel type with retry
      GetChannelTypeResponse getResp = null;
      for (int i = 0; i < 5; i++) {
        try {
          getResp = client.chat().getChannelType(typeName).execute().getData();
          break;
        } catch (Exception e) {
          if (i < 4) {
            Thread.sleep(1000);
          } else {
            throw e;
          }
        }
      }
      assertNotNull(getResp);
      assertEquals(typeName, getResp.getName());

      // Update channel type: set typingEvents=false
      var updateResp =
          client
              .chat()
              .updateChannelType(
                  typeName,
                  UpdateChannelTypeRequest.builder().typingEvents(false).build())
              .execute();
      assertNotNull(updateResp.getData());
      assertEquals(Boolean.FALSE, updateResp.getData().getTypingEvents());

      // List channel types, verify typeName is present
      var listResp = client.chat().listChannelTypes().execute();
      assertNotNull(listResp.getData().getChannelTypes());
      assertTrue(
          listResp.getData().getChannelTypes().containsKey(typeName),
          "Created channel type should appear in list");

    } finally {
      // Delete with retry for eventual consistency
      Exception deleteErr = null;
      for (int i = 0; i < 5; i++) {
        try {
          client.chat().deleteChannelType(typeName).execute();
          deleteErr = null;
          break;
        } catch (Exception e) {
          deleteErr = e;
          Thread.sleep(1000);
        }
      }
      if (deleteErr != null) {
        System.err.println("Warning: failed to delete channel type " + typeName + ": " + deleteErr.getMessage());
      }
    }
  }

  @Test
  @Order(5)
  void testListChannelTypes() throws Exception {
    var listResp = client.chat().listChannelTypes().execute();
    assertNotNull(listResp.getData().getChannelTypes());
    assertTrue(listResp.getData().getChannelTypes().size() > 0, "Should have at least one channel type");
    // Default channel types should be present
    assertTrue(
        listResp.getData().getChannelTypes().containsKey("messaging"),
        "Default 'messaging' channel type should be present");
  }

  @Test
  @Order(3)
  void testCreateListDeleteCommand() throws Exception {
    String cmdName = "testcmd" + randomString(6);

    try {
      // Create command
      var createResp =
          client
              .chat()
              .createCommand(
                  CreateCommandRequest.builder()
                      .name(cmdName)
                      .description("A test command")
                      .build())
              .execute();
      assertNotNull(createResp.getData().getCommand());
      assertEquals(cmdName, createResp.getData().getCommand().getName());

      // Wait for eventual consistency
      Thread.sleep(500);

      // List commands and verify found
      var listResp = client.chat().listCommands().execute();
      assertNotNull(listResp.getData().getCommands());
      boolean found = false;
      for (Command cmd : listResp.getData().getCommands()) {
        if (cmdName.equals(cmd.getName())) {
          found = true;
        }
      }
      assertTrue(found, "Created command should appear in list");

      // Delete command with retry
      Exception deleteErr = null;
      for (int i = 0; i < 5; i++) {
        try {
          client.chat().deleteCommand(cmdName).execute();
          deleteErr = null;
          break;
        } catch (Exception e) {
          deleteErr = e;
          Thread.sleep(1000);
        }
      }
      if (deleteErr != null) {
        throw deleteErr;
      }

    } catch (Exception e) {
      // Cleanup on failure
      try {
        client.chat().deleteCommand(cmdName).execute();
      } catch (Exception ignored) {
      }
      throw e;
    }
  }

  @Test
  @Order(2)
  void testCreateListDeleteBlocklist() throws Exception {
    String blocklistName = "test-blocklist-" + randomString(8);

    try {
      // Create blocklist
      var createResp =
          client
              .createBlockList(
                  CreateBlockListRequest.builder()
                      .name(blocklistName)
                      .words(List.of("badword1", "badword2", "badword3"))
                      .build())
              .execute();
      assertNotNull(createResp.getData());

      // Wait for eventual consistency
      Thread.sleep(500);

      // List blocklists and verify found
      var listResp = client.listBlockLists().execute();
      assertNotNull(listResp.getData().getBlocklists());
      boolean found = false;
      for (BlockListResponse bl : listResp.getData().getBlocklists()) {
        if (blocklistName.equals(bl.getName())) {
          found = true;
        }
      }
      assertTrue(found, "Created blocklist should appear in list");

      // Delete blocklist
      client.deleteBlockList(blocklistName).execute();

      // Verify deleted - list again and check it's gone
      var listAfter = client.listBlockLists().execute();
      if (listAfter.getData().getBlocklists() != null) {
        for (BlockListResponse bl : listAfter.getData().getBlocklists()) {
          assertNotEquals(blocklistName, bl.getName(), "Blocklist should be deleted");
        }
      }
    } catch (Exception e) {
      // Cleanup on failure
      try {
        client.deleteBlockList(blocklistName).execute();
      } catch (Exception ignored) {
      }
      throw e;
    }
  }

  @Test
  @Order(1)
  void testCreateListDeleteDevice() throws Exception {
    List<String> userIds = createTestUsers(1);
    createdUserIds.addAll(userIds);
    String userId = userIds.get(0);

    String deviceId = "integration-test-device-" + randomString(12);

    try {
      // Create device
      client
          .createDevice(
              CreateDeviceRequest.builder()
                  .id(deviceId)
                  .pushProvider("firebase")
                  .userID(userId)
                  .build())
          .execute();
    } catch (Exception e) {
      String msg = e.getMessage();
      if (msg != null
          && (msg.contains("no push providers configured")
              || msg.contains("push provider")
              || msg.contains("push notifications"))) {
        Assumptions.assumeTrue(false, "Push providers not configured for this app");
      }
      throw e;
    }

    // List devices and verify found
    var listResp =
        client
            .listDevices(ListDevicesRequest.builder().UserID(userId).build())
            .execute();

    assertNotNull(listResp.getData().getDevices());
    boolean found = false;
    for (DeviceResponse d : listResp.getData().getDevices()) {
      if (deviceId.equals(d.getId())) {
        found = true;
        assertEquals("firebase", d.getPushProvider());
        assertEquals(userId, d.getUserID());
      }
    }
    assertTrue(found, "Created device should appear in list");

    // Delete device
    client
        .deleteDevice(DeleteDeviceRequest.builder().ID(deviceId).UserID(userId).build())
        .execute();

    // Verify deleted
    var listAfterDelete =
        client
            .listDevices(ListDevicesRequest.builder().UserID(userId).build())
            .execute();

    if (listAfterDelete.getData().getDevices() != null) {
      for (DeviceResponse d : listAfterDelete.getData().getDevices()) {
        assertNotEquals(deviceId, d.getId(), "Device should be deleted");
      }
    }
  }

  @Test
  @Order(6)
  void testListPermissions() throws Exception {
    var resp = client.listPermissions().execute();
    assertNotNull(resp.getData().getPermissions(), "Permissions list should not be null");
    assertFalse(resp.getData().getPermissions().isEmpty(), "Should have at least one permission");
  }

  @Test
  @Order(7)
  void testCreatePermission() throws Exception {
    // CreatePermission is marked Ignore: true in the backend spec and is therefore
    // not available in the generated Java SDK. Per the getstream-go reference:
    // "CreatePermission is hidden from the generated spec (Ignore: true in backend)."
    // This test documents that the endpoint is not supported in this SDK version.
    Assumptions.assumeTrue(
        false,
        "CreatePermission is not available in the generated SDK (Ignore: true in backend spec)");
  }

  @Test
  @Order(8)
  void testGetPermission() throws Exception {
    // Get a specific well-known permission by ID
    var resp = client.getPermission("create-channel").execute();
    assertNotNull(resp.getData(), "Response data should not be null");
    assertNotNull(resp.getData().getPermission(), "Permission should not be null");
    assertEquals("create-channel", resp.getData().getPermission().getId(), "Permission ID should match");
    assertNotNull(resp.getData().getPermission().getAction(), "Permission action should not be null");
    assertFalse(
        resp.getData().getPermission().getAction().isEmpty(),
        "Permission action should not be empty");
  }

  @Test
  @Order(9)
  void testQueryBannedUsers() throws Exception {
    List<String> userIds = createTestUsers(2);
    createdUserIds.addAll(userIds);
    String adminId = userIds.get(0);
    String targetId = userIds.get(1);

    ModerationImpl moderation = new ModerationImpl(client.getHttpClient());

    // Create a channel with both users as members
    String channelId = createTestChannelWithMembers(adminId, userIds);
    String cid = "messaging:" + channelId;

    try {
      // Ban target user from channel with a reason
      moderation
          .ban(
              BanRequest.builder()
                  .targetUserID(targetId)
                  .bannedByID(adminId)
                  .channelCid(cid)
                  .reason("test ban reason")
                  .build())
          .execute();

      // Query banned users for this channel
      var queryResp =
          client
              .chat()
              .queryBannedUsers(
                  QueryBannedUsersRequest.builder()
                      .Payload(
                          QueryBannedUsersPayload.builder()
                              .filterConditions(Map.of("channel_cid", Map.of("$eq", cid)))
                              .build())
                      .build())
              .execute();

      assertNotNull(queryResp.getData().getBans());
      assertFalse(queryResp.getData().getBans().isEmpty(), "Should find the banned user");

      // Verify ban details
      BanResponse ban = queryResp.getData().getBans().get(0);
      assertNotNull(ban.getUser(), "Banned user should be included in response");
      assertEquals(targetId, ban.getUser().getId(), "Banned user ID should match");
      assertEquals("test ban reason", ban.getReason(), "Ban reason should match");

      // Unban the user from the channel
      moderation
          .unban(UnbanRequest.builder().TargetUserID(targetId).ChannelCid(cid).build())
          .execute();

      // Verify ban is gone after unban
      var queryAfter =
          client
              .chat()
              .queryBannedUsers(
                  QueryBannedUsersRequest.builder()
                      .Payload(
                          QueryBannedUsersPayload.builder()
                              .filterConditions(Map.of("channel_cid", Map.of("$eq", cid)))
                              .build())
                      .build())
              .execute();

      assertTrue(
          queryAfter.getData().getBans() == null || queryAfter.getData().getBans().isEmpty(),
          "Bans should be empty after unban");

    } finally {
      // Clean up channel
      try {
        client
            .chat()
            .deleteChannel(
                "messaging", channelId, DeleteChannelRequest.builder().HardDelete(true).build())
            .execute();
      } catch (Exception ignored) {
      }
    }
  }

  @Test
  @Order(12)
  void testExportChannels() throws Exception {
    List<String> userIds = createTestUsers(1);
    createdUserIds.addAll(userIds);
    String userId = userIds.get(0);

    // Create channel with a message to export
    String channelId = createTestChannelWithMembers(userId, userIds);
    sendTestMessage("messaging", channelId, userId, "export test message");

    try {
      // Export the channel
      var exportResp =
          client
              .chat()
              .exportChannels(
                  ExportChannelsRequest.builder()
                      .channels(
                          List.of(
                              ChannelExport.builder()
                                  .type("messaging")
                                  .id(channelId)
                                  .build()))
                      .build())
              .execute();

      assertNotNull(exportResp.getData(), "Export response data should not be null");
      assertNotNull(exportResp.getData().getTaskID(), "Task ID should not be null");
      assertFalse(exportResp.getData().getTaskID().isEmpty(), "Task ID should not be empty");

      // Poll task until completed
      String taskId = exportResp.getData().getTaskID();
      waitForTask(taskId);

    } finally {
      try {
        client
            .chat()
            .deleteChannel(
                "messaging", channelId, DeleteChannelRequest.builder().HardDelete(true).build())
            .execute();
      } catch (Exception ignored) {
      }
    }
  }

  @Test
  @Order(11)
  void testGetAppSettings() throws Exception {
    var resp = client.getApp().execute();
    assertNotNull(resp.getData(), "Response data should not be null");
    assertNotNull(resp.getData().getDuration(), "Duration should not be null");
    assertNotNull(resp.getData().getApp(), "App settings should not be null");
  }

  @Test
  @Order(10)
  void testMuteUnmuteUser() throws Exception {
    List<String> userIds = createTestUsers(2);
    createdUserIds.addAll(userIds);
    String muterId = userIds.get(0);
    String targetId = userIds.get(1);

    ModerationImpl moderation = new ModerationImpl(client.getHttpClient());

    // Mute target user as muter
    var muteResp =
        moderation
            .mute(
                MuteRequest.builder()
                    .targetIds(List.of(targetId))
                    .userID(muterId)
                    .build())
            .execute();

    assertNotNull(muteResp.getData(), "Mute response data should not be null");
    // Verify mutes list includes the target user
    assertNotNull(muteResp.getData().getMutes(), "Mutes list should not be null");
    boolean found = false;
    for (UserMuteResponse mute : muteResp.getData().getMutes()) {
      if (mute.getTarget() != null && targetId.equals(mute.getTarget().getId())) {
        found = true;
      }
    }
    assertTrue(found, "Target user should be in mutes list");

    // Unmute the target user
    var unmuteResp =
        moderation
            .unmute(
                UnmuteRequest.builder()
                    .targetIds(List.of(targetId))
                    .userID(muterId)
                    .build())
            .execute();

    assertNotNull(unmuteResp.getData(), "Unmute response data should not be null");
  }

  @Test
  @Order(13)
  void testThreads() throws Exception {
    List<String> userIds = createTestUsers(2);
    createdUserIds.addAll(userIds);
    String userId1 = userIds.get(0);
    String userId2 = userIds.get(1);

    // Create a channel with both members
    String channelId = createTestChannelWithMembers(userId1, userIds);
    String channelCid = "messaging:" + channelId;

    try {
      // Send parent message
      String parentId = sendTestMessage("messaging", channelId, userId1, "Thread parent message");

      // Send replies to create a thread
      SendMessageRequest reply1Req = new SendMessageRequest();
      MessageRequest reply1Msg = new MessageRequest();
      reply1Msg.setText("First reply in thread");
      reply1Msg.setUserID(userId2);
      reply1Msg.setParentID(parentId);
      reply1Req.setMessage(reply1Msg);
      client.chat().sendMessage("messaging", channelId, reply1Req).execute();

      SendMessageRequest reply2Req = new SendMessageRequest();
      MessageRequest reply2Msg = new MessageRequest();
      reply2Msg.setText("Second reply in thread");
      reply2Msg.setUserID(userId1);
      reply2Msg.setParentID(parentId);
      reply2Req.setMessage(reply2Msg);
      client.chat().sendMessage("messaging", channelId, reply2Req).execute();

      // Small delay for eventual consistency
      Thread.sleep(500);

      // Query threads filtering by channel_cid
      var queryResp =
          client
              .chat()
              .queryThreads(
                  QueryThreadsRequest.builder()
                      .userID(userId1)
                      .filter(
                          Map.of(
                              "channel_cid", Map.of("$eq", channelCid)))
                      .build())
              .execute();

      assertNotNull(queryResp.getData(), "QueryThreads response should not be null");
      assertNotNull(queryResp.getData().getThreads(), "Threads list should not be null");
      assertFalse(queryResp.getData().getThreads().isEmpty(), "Should have at least one thread");

      // Verify the parent thread appears in results
      boolean found = false;
      for (ThreadStateResponse thread : queryResp.getData().getThreads()) {
        if (parentId.equals(thread.getParentMessageID())) {
          found = true;
          break;
        }
      }
      assertTrue(found, "Parent message thread should appear in query results");

      // GetThread: verify replies are included
      var getResp =
          client
              .chat()
              .getThread(
                  parentId,
                  GetThreadRequest.builder().ReplyLimit(10).build())
              .execute();

      assertNotNull(getResp.getData(), "GetThread response should not be null");
      assertNotNull(getResp.getData().getThread(), "Thread should not be null");
      assertEquals(
          parentId,
          getResp.getData().getThread().getParentMessageID(),
          "Thread parent message ID should match");
      assertNotNull(
          getResp.getData().getThread().getLatestReplies(),
          "Latest replies should not be null");
      assertTrue(
          getResp.getData().getThread().getLatestReplies().size() >= 2,
          "Thread should have at least 2 replies");

    } finally {
      try {
        client
            .chat()
            .deleteChannel(
                "messaging", channelId, DeleteChannelRequest.builder().HardDelete(true).build())
            .execute();
      } catch (Exception ignored) {
      }
    }
  }

  @Test
  @Order(14)
  void testGetUnreadCounts() throws Exception {
    List<String> userIds = createTestUsers(2);
    createdUserIds.addAll(userIds);
    String senderId = userIds.get(0);
    String readerId = userIds.get(1);

    // Create channel with both members
    String channelId = createTestChannelWithMembers(senderId, userIds);

    try {
      // Sender sends a message (reader has not read it)
      sendTestMessage("messaging", channelId, senderId, "Unread count test message");

      // Small delay for eventual consistency
      Thread.sleep(500);

      // Get unread counts for reader (who hasn't read the message)
      var resp =
          client
              .chat()
              .unreadCounts(UnreadCountsRequest.builder().UserID(readerId).build())
              .execute();

      assertNotNull(resp.getData(), "Unread counts response should not be null");
      assertNotNull(
          resp.getData().getTotalUnreadCount(), "Total unread count should not be null");
      assertTrue(
          resp.getData().getTotalUnreadCount() >= 1,
          "Reader should have at least 1 unread message");

    } finally {
      try {
        client
            .chat()
            .deleteChannel(
                "messaging", channelId, DeleteChannelRequest.builder().HardDelete(true).build())
            .execute();
      } catch (Exception ignored) {
      }
    }
  }
}
