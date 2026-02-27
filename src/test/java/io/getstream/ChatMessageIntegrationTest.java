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
