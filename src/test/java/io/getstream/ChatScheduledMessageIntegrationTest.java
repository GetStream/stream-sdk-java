package io.getstream;

import static org.junit.jupiter.api.Assertions.*;

import io.getstream.exceptions.StreamApiException;
import io.getstream.models.*;
import java.util.*;
import java.util.stream.Collectors;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ChatScheduledMessageIntegrationTest extends ChatTestBase {

  // Error code the API returns when scheduled messages are off for the app.
  private static final int SCHEDULED_MESSAGES_DISABLED = 114;

  private final List<String> createdUserIds = new ArrayList<>();
  private String userId;
  private String channelId;
  // Set when the app supports scheduled messages: the feature is opt-in per app, and backends
  // without it ignore scheduled_at and send the message right away.
  private String scheduledMessageId;

  @BeforeAll
  void scheduleMessage() throws Exception {
    createdUserIds.addAll(createTestUsers(1));
    userId = createdUserIds.get(0);
    channelId = createTestChannelWithMembers(userId, List.of(userId));
    scheduledMessageId = schedule("Scheduled from the Java SDK");
  }

  @AfterAll
  void cleanup() {
    try {
      chat.deleteChannel(
              "messaging", channelId, DeleteChannelRequest.builder().HardDelete(true).build())
          .execute();
    } catch (Exception ignored) {
    }
    deleteUsersWithRetry(createdUserIds);
  }

  private static Date inOneHour() {
    return new Date(System.currentTimeMillis() + 60 * 60 * 1000);
  }

  // Returns null when the app cannot schedule messages.
  private String schedule(String text) throws Exception {
    try {
      var resp =
          chat.sendMessage(
                  "messaging",
                  channelId,
                  SendMessageRequest.builder()
                      .message(MessageRequest.builder().text(text).userID(userId).build())
                      .scheduledAt(inOneHour())
                      .build())
              .execute();
      var scheduled = resp.getData().getScheduledMessage();
      return scheduled == null ? null : scheduled.getId();
    } catch (StreamApiException e) {
      if (e.getCode() == SCHEDULED_MESSAGES_DISABLED) {
        return null;
      }
      throw e;
    }
  }

  private List<ScheduledMessageResponse> queryChannel() throws Exception {
    return chat.queryScheduledMessages(
            QueryScheduledMessagesRequest.builder()
                .userID(userId)
                .filter(ScheduledMessagesFilter.builder().cid("messaging:" + channelId).build())
                .build())
        .execute()
        .getData()
        .getScheduledMessages();
  }

  @Test
  @Order(1)
  void testQueryScheduledMessages() throws Exception {
    Assumptions.assumeTrue(scheduledMessageId != null, "scheduled messages are not enabled");

    List<ScheduledMessageResponse> scheduled = queryChannel();
    assertEquals(1, scheduled.size());
    ScheduledMessageResponse first = scheduled.get(0);
    assertEquals(scheduledMessageId, first.getId());
    assertEquals("scheduled", first.getStatus());
    assertEquals("messaging:" + channelId, first.getCid());
    assertEquals("Scheduled from the Java SDK", first.getMessage().getText());
    assertNotNull(first.getScheduledAt());
  }

  @Test
  @Order(2)
  void testUpdateScheduledMessage() throws Exception {
    Assumptions.assumeTrue(scheduledMessageId != null, "scheduled messages are not enabled");

    Date scheduledAt = inOneHour();
    var resp =
        chat.updateScheduledMessage(
                scheduledMessageId,
                UpdateScheduledMessageRequest.builder()
                    .scheduledAt(scheduledAt)
                    .message(MessageRequest.builder().text("Rescheduled from the Java SDK").build())
                    .build())
            .execute();

    var updated = resp.getData().getScheduledMessage();
    assertEquals("Rescheduled from the Java SDK", updated.getMessage().getText());
    assertEquals(scheduledAt.getTime(), updated.getScheduledAt().getTime());
  }

  @Test
  @Order(3)
  void testSendScheduledMessageNow() throws Exception {
    Assumptions.assumeTrue(scheduledMessageId != null, "scheduled messages are not enabled");

    var resp = chat.sendScheduledMessageNow(scheduledMessageId).execute();

    assertEquals(scheduledMessageId, resp.getData().getScheduledMessage().getId());
  }

  @Test
  @Order(4)
  void testDeleteScheduledMessage() throws Exception {
    Assumptions.assumeTrue(scheduledMessageId != null, "scheduled messages are not enabled");

    String id = schedule("Deleted before sending");
    assertNotNull(id);

    var resp = chat.deleteScheduledMessage(id).execute();
    assertEquals(id, resp.getData().getScheduledMessage().getId());
    assertEquals("canceled", resp.getData().getScheduledMessage().getStatus());

    List<String> remaining =
        queryChannel().stream().map(ScheduledMessageResponse::getId).collect(Collectors.toList());
    assertFalse(remaining.contains(id));
  }
}
