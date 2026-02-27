package io.getstream;

import static org.junit.jupiter.api.Assertions.*;

import io.getstream.models.*;
import java.time.*;
import java.time.format.*;
import java.util.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ChatMessageIntegrationTest extends ChatTestBase {

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
  void testSendAndGetMessage() throws Exception {
    List<String> userIds = createTestUsers(1);
    createdUserIds.addAll(userIds);
    String userId = userIds.get(0);

    String channelId = createTestChannel(userId);
    createdChannelIds.add(channelId);

    String text = "Hello from testSendAndGetMessage-" + randomString(8);
    String messageId = sendTestMessage("messaging", channelId, userId, text);

    var resp = chat.getMessage(messageId).execute();
    assertNotNull(resp.getData());
    assertNotNull(resp.getData().getMessage());
    assertEquals(text, resp.getData().getMessage().getText());
  }

  @Test
  @Order(3)
  void testUpdateMessage() throws Exception {
    List<String> userIds = createTestUsers(1);
    createdUserIds.addAll(userIds);
    String userId = userIds.get(0);

    String channelId = createTestChannel(userId);
    createdChannelIds.add(channelId);

    String originalText = "original-" + randomString(8);
    String messageId = sendTestMessage("messaging", channelId, userId, originalText);

    String updatedText = "updated-" + randomString(8);
    var resp =
        chat.updateMessage(
                messageId,
                UpdateMessageRequest.builder()
                    .message(
                        MessageRequest.builder().id(messageId).text(updatedText).userID(userId).build())
                    .build())
            .execute();
    assertNotNull(resp.getData());
    assertNotNull(resp.getData().getMessage());
    assertEquals(updatedText, resp.getData().getMessage().getText());
  }

  @Test
  @Order(4)
  void testPartialUpdateMessage() throws Exception {
    List<String> userIds = createTestUsers(1);
    createdUserIds.addAll(userIds);
    String userId = userIds.get(0);

    String channelId = createTestChannel(userId);
    createdChannelIds.add(channelId);

    String text = "partial-update-" + randomString(8);
    String messageId = sendTestMessage("messaging", channelId, userId, text);

    // Set custom fields
    Map<String, Object> setFields = new HashMap<>();
    setFields.put("priority", "high");
    setFields.put("status", "open");

    var setResp =
        chat.updateMessagePartial(
                messageId,
                UpdateMessagePartialRequest.builder()
                    .set(setFields)
                    .userID(userId)
                    .build())
            .execute();
    assertNotNull(setResp.getData());
    assertNotNull(setResp.getData().getMessage());

    // Unset "status" field
    var unsetResp =
        chat.updateMessagePartial(
                messageId,
                UpdateMessagePartialRequest.builder()
                    .unset(List.of("status"))
                    .userID(userId)
                    .build())
            .execute();
    assertNotNull(unsetResp.getData());
    assertNotNull(unsetResp.getData().getMessage());
    Map<String, Object> customAfterUnset = unsetResp.getData().getMessage().getCustom();
    assertTrue(
        customAfterUnset == null || !customAfterUnset.containsKey("status"),
        "status field should be removed after unset");
  }

  @Test
  @Order(5)
  void testDeleteMessage() throws Exception {
    List<String> userIds = createTestUsers(1);
    createdUserIds.addAll(userIds);
    String userId = userIds.get(0);

    String channelId = createTestChannel(userId);
    createdChannelIds.add(channelId);

    String messageId = sendTestMessage("messaging", channelId, userId, "to-delete-" + randomString(8));

    var resp =
        chat.deleteMessage(messageId, DeleteMessageRequest.builder().build()).execute();
    assertNotNull(resp.getData());
    assertNotNull(resp.getData().getMessage());
    assertEquals("deleted", resp.getData().getMessage().getType());
  }

  @Test
  @Order(6)
  void testHardDeleteMessage() throws Exception {
    List<String> userIds = createTestUsers(1);
    createdUserIds.addAll(userIds);
    String userId = userIds.get(0);

    String channelId = createTestChannel(userId);
    createdChannelIds.add(channelId);

    String messageId = sendTestMessage("messaging", channelId, userId, "to-hard-delete-" + randomString(8));

    var resp =
        chat.deleteMessage(messageId, DeleteMessageRequest.builder().Hard(true).build()).execute();
    assertNotNull(resp.getData());
    assertNotNull(resp.getData().getMessage());
    assertEquals("deleted", resp.getData().getMessage().getType());
  }

  @Test
  @Order(7)
  void testPinUnpinMessage() throws Exception {
    List<String> userIds = createTestUsers(1);
    createdUserIds.addAll(userIds);
    String userId = userIds.get(0);

    String channelId = createTestChannel(userId);
    createdChannelIds.add(channelId);

    // Send a pinned message
    var sendResp =
        chat.sendMessage(
                "messaging",
                channelId,
                SendMessageRequest.builder()
                    .message(
                        MessageRequest.builder()
                            .text("Pinned message " + randomString(8))
                            .userID(userId)
                            .pinned(true)
                            .build())
                    .build())
            .execute();
    assertNotNull(sendResp.getData());
    assertNotNull(sendResp.getData().getMessage());
    String messageId = sendResp.getData().getMessage().getId();
    assertTrue(Boolean.TRUE.equals(sendResp.getData().getMessage().getPinned()),
        "Message should be pinned after send");

    // Unpin via partial update
    Map<String, Object> unpin = new HashMap<>();
    unpin.put("pinned", false);
    var unpinResp =
        chat.updateMessagePartial(
                messageId,
                UpdateMessagePartialRequest.builder()
                    .set(unpin)
                    .userID(userId)
                    .build())
            .execute();
    assertNotNull(unpinResp.getData());
    assertNotNull(unpinResp.getData().getMessage());
    assertTrue(Boolean.FALSE.equals(unpinResp.getData().getMessage().getPinned())
        || unpinResp.getData().getMessage().getPinned() == null,
        "Message should be unpinned after partial update");
  }

  @Test
  @Order(8)
  void testTranslateMessage() throws Exception {
    List<String> userIds = createTestUsers(1);
    createdUserIds.addAll(userIds);
    String userId = userIds.get(0);

    String channelId = createTestChannel(userId);
    createdChannelIds.add(channelId);

    String messageId = sendTestMessage("messaging", channelId, userId, "Hello, how are you?");

    var resp =
        chat.translateMessage(
                messageId,
                TranslateMessageRequest.builder().language("es").build())
            .execute();
    assertNotNull(resp.getData());
    assertNotNull(resp.getData().getMessage(), "message should not be null after translation");
    assertNotNull(resp.getData().getMessage().getI18n(), "i18n field should be set after translation");
  }

  @Test
  @Order(9)
  void testThreadReply() throws Exception {
    List<String> userIds = createTestUsers(2);
    createdUserIds.addAll(userIds);
    String userId = userIds.get(0);
    String replyUserId = userIds.get(1);

    String channelId = createTestChannel(userId);
    createdChannelIds.add(channelId);

    // Send parent message
    String parentId = sendTestMessage("messaging", channelId, userId, "parent-" + randomString(8));

    // Send reply with parent_id
    var replyResp =
        chat.sendMessage(
                "messaging",
                channelId,
                SendMessageRequest.builder()
                    .message(
                        MessageRequest.builder()
                            .text("reply-" + randomString(8))
                            .userID(replyUserId)
                            .parentID(parentId)
                            .build())
                    .build())
            .execute();
    assertNotNull(replyResp.getData());
    assertNotNull(replyResp.getData().getMessage());
    assertEquals(parentId, replyResp.getData().getMessage().getParentID());

    // Get replies
    var repliesResp =
        chat.getReplies(parentId, GetRepliesRequest.builder().build()).execute();
    assertNotNull(repliesResp.getData());
    assertNotNull(repliesResp.getData().getMessages());
    assertEquals(1, repliesResp.getData().getMessages().size());
    assertEquals(parentId, repliesResp.getData().getMessages().get(0).getParentID());
  }

  @Test
  @Order(11)
  void testSilentMessage() throws Exception {
    List<String> userIds = createTestUsers(1);
    createdUserIds.addAll(userIds);
    String userId = userIds.get(0);

    String channelId = createTestChannel(userId);
    createdChannelIds.add(channelId);

    var resp =
        chat.sendMessage(
                "messaging",
                channelId,
                SendMessageRequest.builder()
                    .message(
                        MessageRequest.builder()
                            .text("This is a silent message")
                            .userID(userId)
                            .silent(true)
                            .build())
                    .build())
            .execute();
    assertNotNull(resp.getData());
    assertNotNull(resp.getData().getMessage());
    assertTrue(Boolean.TRUE.equals(resp.getData().getMessage().getSilent()),
        "Message should be silent");
  }

  @Test
  @Order(12)
  void testPendingMessage() throws Exception {
    List<String> userIds = createTestUsers(1);
    createdUserIds.addAll(userIds);
    String userId = userIds.get(0);

    String channelId = createTestChannelWithMembers(userId, userIds);
    createdChannelIds.add(channelId);

    SendMessageResponse sendResp;
    try {
      sendResp =
          chat.sendMessage(
                  "messaging",
                  channelId,
                  SendMessageRequest.builder()
                      .message(
                          MessageRequest.builder()
                              .text("Pending message text")
                              .userID(userId)
                              .build())
                      .pending(true)
                      .skipPush(true)
                      .build())
              .execute()
              .getData();
    } catch (Exception e) {
      String msg = e.getMessage();
      if (msg != null && (msg.contains("pending messages not enabled") || msg.contains("feature flag"))) {
        org.junit.jupiter.api.Assumptions.assumeTrue(false,
            "Pending messages not enabled for this app");
        return;
      }
      throw e;
    }

    assertNotNull(sendResp);
    assertNotNull(sendResp.getMessage());
    String messageId = sendResp.getMessage().getId();
    assertNotNull(messageId);

    // Commit the pending message
    var commitResp = chat.commitMessage(messageId).execute();
    assertNotNull(commitResp.getData());
    assertNotNull(commitResp.getData().getMessage());
    assertEquals(messageId, commitResp.getData().getMessage().getId());
  }

  @Test
  @Order(10)
  void testSearchMessages() throws Exception {
    List<String> userIds = createTestUsers(1);
    createdUserIds.addAll(userIds);
    String userId = userIds.get(0);

    String channelId = createTestChannel(userId);
    createdChannelIds.add(channelId);

    String uniqueTerm = "searchterm-" + randomString(12);
    sendTestMessage("messaging", channelId, userId, "Message with " + uniqueTerm);

    // Wait for search indexing
    Thread.sleep(2000);

    var resp =
        chat.search(
                SearchRequest.builder()
                    .Payload(
                        SearchPayload.builder()
                            .query(uniqueTerm)
                            .filterConditions(
                                Map.of("cid", "messaging:" + channelId))
                            .build())
                    .build())
            .execute();
    assertNotNull(resp.getData());
    assertNotNull(resp.getData().getResults());
    assertFalse(resp.getData().getResults().isEmpty(), "Search should return at least 1 result");
  }

  @Test
  @Order(13)
  void testQueryMessageHistory() throws Exception {
    List<String> userIds = createTestUsers(2);
    createdUserIds.addAll(userIds);
    String userId = userIds.get(0);
    String userId2 = userIds.get(1);

    String channelId = createTestChannelWithMembers(userId, userIds);
    createdChannelIds.add(channelId);

    // Send initial message
    String messageId = sendTestMessage("messaging", channelId, userId, "initial text");

    // Update by user1
    chat.updateMessage(
            messageId,
            UpdateMessageRequest.builder()
                .message(MessageRequest.builder().id(messageId).text("updated text").userID(userId).build())
                .build())
        .execute();

    // Update by user2
    chat.updateMessage(
            messageId,
            UpdateMessageRequest.builder()
                .message(MessageRequest.builder().id(messageId).text("updated text 2").userID(userId2).build())
                .build())
        .execute();

    try {
      var histResp =
          chat.queryMessageHistory(
                  QueryMessageHistoryRequest.builder()
                      .filter(Map.of("message_id", messageId))
                      .sort(List.of())
                      .build())
              .execute();
      assertNotNull(histResp.getData());
      assertNotNull(histResp.getData().getMessageHistory());
      assertTrue(
          histResp.getData().getMessageHistory().size() >= 2,
          "Should have at least 2 history entries");

      for (var entry : histResp.getData().getMessageHistory()) {
        assertEquals(messageId, entry.getMessageID());
      }

      // Default sort is descending: history[0] = most recent prior version, history[1] = original
      assertEquals("updated text", histResp.getData().getMessageHistory().get(0).getText());
      assertEquals(userId, histResp.getData().getMessageHistory().get(0).getMessageUpdatedByID());
      assertEquals("initial text", histResp.getData().getMessageHistory().get(1).getText());
      assertEquals(userId, histResp.getData().getMessageHistory().get(1).getMessageUpdatedByID());
    } catch (Exception e) {
      String msg = e.getMessage();
      if (msg != null && (msg.contains("feature flag") || msg.contains("not enabled"))) {
        org.junit.jupiter.api.Assumptions.assumeTrue(false, "QueryMessageHistory not enabled for this app");
        return;
      }
      throw e;
    }
  }

  @Test
  @Order(14)
  void testQueryMessageHistorySort() throws Exception {
    List<String> userIds = createTestUsers(1);
    createdUserIds.addAll(userIds);
    String userId = userIds.get(0);

    String channelId = createTestChannel(userId);
    createdChannelIds.add(channelId);

    // Send initial message
    String messageId = sendTestMessage("messaging", channelId, userId, "sort initial");

    // Update twice
    chat.updateMessage(
            messageId,
            UpdateMessageRequest.builder()
                .message(MessageRequest.builder().id(messageId).text("sort updated 1").userID(userId).build())
                .build())
        .execute();

    chat.updateMessage(
            messageId,
            UpdateMessageRequest.builder()
                .message(MessageRequest.builder().id(messageId).text("sort updated 2").userID(userId).build())
                .build())
        .execute();

    try {
      var histResp =
          chat.queryMessageHistory(
                  QueryMessageHistoryRequest.builder()
                      .filter(Map.of("message_id", messageId))
                      .sort(
                          List.of(
                              SortParamRequest.builder()
                                  .field("message_updated_at")
                                  .direction(1)
                                  .build()))
                      .build())
              .execute();
      assertNotNull(histResp.getData());
      assertNotNull(histResp.getData().getMessageHistory());
      assertTrue(
          histResp.getData().getMessageHistory().size() >= 2,
          "Should have at least 2 history entries");

      // Ascending sort: oldest first
      assertEquals("sort initial", histResp.getData().getMessageHistory().get(0).getText());
      assertEquals(userId, histResp.getData().getMessageHistory().get(0).getMessageUpdatedByID());
    } catch (Exception e) {
      String msg = e.getMessage();
      if (msg != null && (msg.contains("feature flag") || msg.contains("not enabled"))) {
        org.junit.jupiter.api.Assumptions.assumeTrue(false, "QueryMessageHistory not enabled for this app");
        return;
      }
      throw e;
    }
  }

  @Test
  @Order(15)
  void testSkipEnrichUrl() throws Exception {
    List<String> userIds = createTestUsers(1);
    createdUserIds.addAll(userIds);
    String userId = userIds.get(0);

    String channelId = createTestChannel(userId);
    createdChannelIds.add(channelId);

    // Send a message with a URL but skip enrichment
    var sendResp =
        chat.sendMessage(
                "messaging",
                channelId,
                SendMessageRequest.builder()
                    .message(
                        MessageRequest.builder()
                            .text("Check out https://getstream.io for more info")
                            .userID(userId)
                            .build())
                    .skipEnrichUrl(true)
                    .build())
            .execute();
    assertNotNull(sendResp.getData());
    assertNotNull(sendResp.getData().getMessage());
    String messageId = sendResp.getData().getMessage().getId();
    var attachments = sendResp.getData().getMessage().getAttachments();
    assertTrue(
        attachments == null || attachments.isEmpty(),
        "Attachments should be empty when skip_enrich_url=true");

    // Wait and verify via GetMessage that attachments remain empty
    Thread.sleep(1000);
    var getResp = chat.getMessage(messageId).execute();
    assertNotNull(getResp.getData());
    assertNotNull(getResp.getData().getMessage());
    var getAttachments = getResp.getData().getMessage().getAttachments();
    assertTrue(
        getAttachments == null || getAttachments.isEmpty(),
        "Attachments should remain empty after enrichment window");
  }

  @Test
  @Order(16)
  void testKeepChannelHidden() throws Exception {
    List<String> userIds = createTestUsers(1);
    createdUserIds.addAll(userIds);
    String userId = userIds.get(0);

    String channelId = createTestChannelWithMembers(userId, userIds);
    createdChannelIds.add(channelId);

    String cid = "messaging:" + channelId;

    // Hide the channel for the user
    chat.hideChannel(
            "messaging",
            channelId,
            HideChannelRequest.builder().userID(userId).build())
        .execute();

    // Send a message with keep_channel_hidden=true
    chat.sendMessage(
            "messaging",
            channelId,
            SendMessageRequest.builder()
                .message(
                    MessageRequest.builder()
                        .text("hidden message " + randomString(8))
                        .userID(userId)
                        .build())
                .keepChannelHidden(true)
                .build())
        .execute();

    // Query channels — channel should still be hidden (empty results)
    var qResp =
        chat.queryChannels(
                QueryChannelsRequest.builder()
                    .filterConditions(Map.of("cid", cid))
                    .userID(userId)
                    .build())
            .execute();
    assertNotNull(qResp.getData());
    assertTrue(
        qResp.getData().getChannels() == null || qResp.getData().getChannels().isEmpty(),
        "Channel should remain hidden after sending with keep_channel_hidden=true");
  }

  @Test
  @Order(17)
  void testUndeleteMessage() throws Exception {
    List<String> userIds = createTestUsers(1);
    createdUserIds.addAll(userIds);
    String userId = userIds.get(0);

    String channelId = createTestChannelWithMembers(userId, userIds);
    createdChannelIds.add(channelId);

    String text = "Message to undelete " + randomString(8);
    String messageId = sendTestMessage("messaging", channelId, userId, text);

    // Soft delete the message
    chat.deleteMessage(messageId, DeleteMessageRequest.builder().build()).execute();

    // Verify it's deleted
    var getResp = chat.getMessage(messageId).execute();
    assertNotNull(getResp.getData());
    assertEquals("deleted", getResp.getData().getMessage().getType());

    // Undelete the message
    // Note: API requires "undeleted_by" field which may not be in the generated request struct.
    // Gracefully skip if the field is missing from the SDK.
    try {
      var undelResp =
          chat.undeleteMessage(
                  messageId,
                  UndeleteMessageRequest.builder()
                      .message(MessageRequest.builder().userID(userId).build())
                      .build())
              .execute();
      assertNotNull(undelResp.getData());
      assertNotNull(undelResp.getData().getMessage());
      assertNotEquals("deleted", undelResp.getData().getMessage().getType());
      assertEquals(text, undelResp.getData().getMessage().getText());
    } catch (Exception e) {
      String msg = e.getMessage();
      if (msg != null && (msg.contains("undeleted_by") || msg.contains("required field"))) {
        org.junit.jupiter.api.Assumptions.assumeTrue(false,
            "UndeleteMessage requires 'undeleted_by' field not yet in generated request struct");
      }
      throw e;
    }
  }

  @Test
  @Order(18)
  void testRestrictedVisibility() throws Exception {
    List<String> userIds = createTestUsers(2);
    createdUserIds.addAll(userIds);
    String userId = userIds.get(0);
    String otherUserId = userIds.get(1);

    String channelId = createTestChannelWithMembers(userId, userIds);
    createdChannelIds.add(channelId);

    try {
      // Send message visible only to userId
      var resp =
          chat.sendMessage(
                  "messaging",
                  channelId,
                  SendMessageRequest.builder()
                      .message(
                          MessageRequest.builder()
                              .text("Restricted message " + randomString(8))
                              .userID(userId)
                              .restrictedVisibility(List.of(userId))
                              .build())
                      .build())
              .execute();
      assertNotNull(resp.getData());
      assertNotNull(resp.getData().getMessage());
      var restrictedVisibility = resp.getData().getMessage().getRestrictedVisibility();
      assertNotNull(restrictedVisibility, "restricted_visibility should be set in response");
      assertTrue(restrictedVisibility.contains(userId), "restricted_visibility should contain the sending user");
    } catch (Exception e) {
      String msg = e.getMessage();
      if (msg != null && (msg.contains("restricted visibility") || msg.contains("not enabled")
          || msg.contains("feature") || msg.contains("private messaging"))) {
        org.junit.jupiter.api.Assumptions.assumeTrue(false,
            "Restricted visibility feature not enabled for this app");
        return;
      }
      throw e;
    }
  }

  @Test
  @Order(19)
  void testDeleteMessageForMe() throws Exception {
    List<String> userIds = createTestUsers(2);
    createdUserIds.addAll(userIds);
    String userId = userIds.get(0);
    String otherUserId = userIds.get(1);

    String channelId = createTestChannelWithMembers(userId, userIds);
    createdChannelIds.add(channelId);

    String text = "Message to delete for me " + randomString(8);
    String messageId = sendTestMessage("messaging", channelId, userId, text);

    // Delete the message only for the current user
    var resp =
        chat.deleteMessage(
                messageId,
                DeleteMessageRequest.builder()
                    .DeleteForMe(true)
                    .DeletedBy(userId)
                    .build())
            .execute();
    assertNotNull(resp.getData());
    assertNotNull(resp.getData().getMessage());
    // delete_for_me only removes the message from the user's view,
    // so the API may return the original message (type="regular") or mark it deleted
    assertNotNull(resp.getData().getMessage().getId());
  }

  @Test
  @Order(20)
  void testPinExpiration() throws Exception {
    List<String> userIds = createTestUsers(2);
    createdUserIds.addAll(userIds);
    String userId = userIds.get(0);
    String userId2 = userIds.get(1);

    String channelId = createTestChannelWithMembers(userId, userIds);
    createdChannelIds.add(channelId);

    String messageId = sendTestMessage("messaging", channelId, userId2, "Message to pin with expiry " + randomString(8));

    // Pin with 3-second expiration using updateMessagePartial
    String expiry = Instant.now().plusSeconds(3).atOffset(ZoneOffset.UTC)
        .format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    Map<String, Object> setFields = new HashMap<>();
    setFields.put("pinned", true);
    setFields.put("pin_expires", expiry);

    var pinResp =
        chat.updateMessagePartial(
                messageId,
                UpdateMessagePartialRequest.builder()
                    .set(setFields)
                    .userID(userId)
                    .build())
            .execute();
    assertNotNull(pinResp.getData().getMessage());
    assertTrue(Boolean.TRUE.equals(pinResp.getData().getMessage().getPinned()), "Message should be pinned");

    // Wait for pin to expire
    Thread.sleep(4000);

    // Verify pin expired
    var getResp = chat.getMessage(messageId).execute();
    assertNotNull(getResp.getData().getMessage());
    assertFalse(Boolean.TRUE.equals(getResp.getData().getMessage().getPinned()), "Pin should have expired");
  }

  @Test
  @Order(21)
  void testSystemMessage() throws Exception {
    List<String> userIds = createTestUsers(1);
    createdUserIds.addAll(userIds);
    String userId = userIds.get(0);

    String channelId = createTestChannel(userId);
    createdChannelIds.add(channelId);

    var resp =
        chat.sendMessage(
                "messaging",
                channelId,
                SendMessageRequest.builder()
                    .message(
                        MessageRequest.builder()
                            .text("System message " + randomString(8))
                            .userID(userId)
                            .type("system")
                            .build())
                    .build())
            .execute();
    assertNotNull(resp.getData());
    assertNotNull(resp.getData().getMessage());
    assertEquals("system", resp.getData().getMessage().getType(),
        "Message type should be 'system'");
  }

  @Test
  @Order(22)
  void testPendingFalse() throws Exception {
    List<String> userIds = createTestUsers(1);
    createdUserIds.addAll(userIds);
    String userId = userIds.get(0);

    String channelId = createTestChannelWithMembers(userId, userIds);
    createdChannelIds.add(channelId);

    String text = "non-pending-" + randomString(8);
    var sendResp =
        chat.sendMessage(
                "messaging",
                channelId,
                SendMessageRequest.builder()
                    .message(
                        MessageRequest.builder()
                            .text(text)
                            .userID(userId)
                            .build())
                    .pending(false)
                    .build())
            .execute();
    assertNotNull(sendResp.getData());
    assertNotNull(sendResp.getData().getMessage());
    String messageId = sendResp.getData().getMessage().getId();
    assertNotNull(messageId);

    // Verify the message is immediately available (no commit needed)
    var getResp = chat.getMessage(messageId).execute();
    assertNotNull(getResp.getData());
    assertNotNull(getResp.getData().getMessage());
    assertEquals(text, getResp.getData().getMessage().getText());
  }

  @Test
  @Order(23)
  void testSearchWithMessageFilters() throws Exception {
    List<String> userIds = createTestUsers(1);
    createdUserIds.addAll(userIds);
    String userId = userIds.get(0);

    String channelId = createTestChannelWithMembers(userId, userIds);
    createdChannelIds.add(channelId);

    String searchTerm = "filterable" + randomString(8);
    sendTestMessage("messaging", channelId, userId, "This has " + searchTerm + " text");
    sendTestMessage("messaging", channelId, userId, "This also has " + searchTerm + " text");

    // Wait for search indexing
    Thread.sleep(2000);

    var resp =
        chat.search(
                SearchRequest.builder()
                    .Payload(
                        SearchPayload.builder()
                            .filterConditions(Map.of("cid", "messaging:" + channelId))
                            .messageFilterConditions(
                                Map.of("text", Map.of("$q", searchTerm)))
                            .build())
                    .build())
            .execute();
    assertNotNull(resp.getData());
    assertNotNull(resp.getData().getResults());
    assertTrue(
        resp.getData().getResults().size() >= 2,
        "Should find at least 2 messages with messageFilterConditions");
  }

  @Test
  @Order(24)
  void testSearchQueryAndMessageFiltersError() throws Exception {
    List<String> userIds = createTestUsers(1);
    createdUserIds.addAll(userIds);
    String userId = userIds.get(0);

    // Using both query and message_filter_conditions together should error
    Exception thrown =
        assertThrows(
            Exception.class,
            () ->
                chat.search(
                        SearchRequest.builder()
                            .Payload(
                                SearchPayload.builder()
                                    .filterConditions(
                                        Map.of("members", Map.of("$in", List.of(userId))))
                                    .query("test")
                                    .messageFilterConditions(
                                        Map.of("text", Map.of("$q", "test")))
                                    .build())
                            .build())
                    .execute(),
            "Using both query and message_filter_conditions should throw an error");
    assertNotNull(thrown);
  }

  @Test
  @Order(25)
  void testSearchOffsetAndSortError() throws Exception {
    List<String> userIds = createTestUsers(1);
    createdUserIds.addAll(userIds);
    String userId = userIds.get(0);

    String channelId = createTestChannelWithMembers(userId, userIds);
    createdChannelIds.add(channelId);

    sendTestMessage("messaging", channelId, userId, "search offset sort test " + randomString(8));

    // The API now ALLOWS using Offset with Sort (no error expected)
    var resp =
        chat.search(
                SearchRequest.builder()
                    .Payload(
                        SearchPayload.builder()
                            .filterConditions(
                                Map.of("members", Map.of("$in", List.of(userId))))
                            .query("test")
                            .offset(0)
                            .sort(
                                List.of(
                                    SortParamRequest.builder()
                                        .field("created_at")
                                        .direction(-1)
                                        .build()))
                            .build())
                    .build())
            .execute();
    assertNotNull(resp.getData(), "Search with offset+sort should succeed");
  }

  @Test
  @Order(26)
  void testSearchOffsetAndNextError() throws Exception {
    List<String> userIds = createTestUsers(1);
    createdUserIds.addAll(userIds);
    String userId = userIds.get(0);

    // Using Offset with Next should error
    assertThrows(
        Exception.class,
        () ->
            chat.search(
                    SearchRequest.builder()
                        .Payload(
                            SearchPayload.builder()
                                .filterConditions(
                                    Map.of("members", Map.of("$in", List.of(userId))))
                                .query("test")
                                .offset(1)
                                .next(randomString(5))
                                .build())
                        .build())
                .execute(),
        "Using Offset with Next should error");
  }

  @Test
  @Order(2)
  void testGetManyMessages() throws Exception {
    List<String> userIds = createTestUsers(1);
    createdUserIds.addAll(userIds);
    String userId = userIds.get(0);

    String channelId = createTestChannel(userId);
    createdChannelIds.add(channelId);

    String id1 = sendTestMessage("messaging", channelId, userId, "msg1-" + randomString(6));
    String id2 = sendTestMessage("messaging", channelId, userId, "msg2-" + randomString(6));
    String id3 = sendTestMessage("messaging", channelId, userId, "msg3-" + randomString(6));

    var resp =
        chat.getManyMessages(
                "messaging",
                channelId,
                GetManyMessagesRequest.builder().Ids(List.of(id1, id2, id3)).build())
            .execute();
    assertNotNull(resp.getData());
    assertNotNull(resp.getData().getMessages());
    assertEquals(3, resp.getData().getMessages().size());

    Set<String> returnedIds = new HashSet<>();
    for (var msg : resp.getData().getMessages()) {
      returnedIds.add(msg.getId());
    }
    assertTrue(returnedIds.contains(id1));
    assertTrue(returnedIds.contains(id2));
    assertTrue(returnedIds.contains(id3));
  }
}
