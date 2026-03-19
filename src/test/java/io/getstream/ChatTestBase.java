package io.getstream;

import io.getstream.models.*;
import java.util.*;
import java.util.stream.Collectors;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;

/**
 * Base class for chat integration tests. Extends BasicTest to inherit client initialization and
 * provides helpers for user/channel/message creation, cleanup, and task polling.
 */
public class ChatTestBase extends BasicTest {

  /**
   * Creates n users with unique IDs. Returns the list of created user IDs. Callers should register
   * cleanup via deleteUsersWithRetry in @AfterAll.
   */
  protected List<String> createTestUsers(int n) throws Exception {
    List<String> ids = new ArrayList<>();
    Map<String, UserRequest> users = new HashMap<>();
    for (int i = 0; i < n; i++) {
      String id = "tu-" + UUID.randomUUID().toString().replace("-", "").substring(0, 16);
      ids.add(id);
      users.put(id, UserRequest.builder().id(id).name("Test User " + id).role("user").build());
    }
    client.updateUsers(UpdateUsersRequest.builder().users(users).build()).execute();
    return ids;
  }

  /**
   * Creates a messaging channel with the given creator. Returns the channel ID. Callers should
   * hard-delete the channel in @AfterAll cleanup.
   */
  protected String createTestChannel(String creatorId) throws Exception {
    String channelId = "tc-" + RandomStringUtils.randomAlphabetic(12).toLowerCase();
    chat.getOrCreateChannel(
            "messaging",
            channelId,
            GetOrCreateChannelRequest.builder()
                .data(ChannelInput.builder().createdByID(creatorId).build())
                .build())
        .execute();
    return channelId;
  }

  /**
   * Creates a messaging channel with the given creator and members. Returns the channel ID. Callers
   * should hard-delete the channel in @AfterAll cleanup.
   */
  protected String createTestChannelWithMembers(String creatorId, List<String> memberIds)
      throws Exception {
    String channelId = "tc-" + RandomStringUtils.randomAlphabetic(12).toLowerCase();
    List<ChannelMemberRequest> members =
        memberIds.stream()
            .map(id -> ChannelMemberRequest.builder().userID(id).build())
            .collect(Collectors.toList());
    chat.getOrCreateChannel(
            "messaging",
            channelId,
            GetOrCreateChannelRequest.builder()
                .data(ChannelInput.builder().createdByID(creatorId).members(members).build())
                .build())
        .execute();
    return channelId;
  }

  /**
   * Sends a message to the specified channel as the given user. Returns the message ID. Asserts
   * that the message ID is non-null.
   */
  protected String sendTestMessage(String channelType, String channelId, String userId, String text)
      throws Exception {
    var resp =
        chat.sendMessage(
                channelType,
                channelId,
                SendMessageRequest.builder()
                    .message(MessageRequest.builder().text(text).userID(userId).build())
                    .build())
            .execute();
    Assertions.assertNotNull(resp.getData().getMessage().getId());
    return resp.getData().getMessage().getId();
  }

  /**
   * Hard-deletes users with retry on rate-limit errors. Retries up to 10 times with exponential
   * backoff (3s, 6s, 9s...).
   */
  protected void deleteUsersWithRetry(List<String> userIds) {
    for (int i = 0; i < 10; i++) {
      try {
        client
            .deleteUsers(
                DeleteUsersRequest.builder()
                    .userIds(userIds)
                    .user("hard")
                    .messages("hard")
                    .conversations("hard")
                    .build())
            .execute();
        return;
      } catch (Exception e) {
        String msg = e.getMessage();
        if (msg == null || !msg.contains("Too many requests")) return;
        try {
          Thread.sleep((long) (i + 1) * 3000L);
        } catch (InterruptedException ignored) {
        }
      }
    }
  }

  /**
   * Polls the given task ID until it reaches "completed" or "failed" status. Polls up to 30 times
   * with 1-second intervals.
   */
  protected void waitForTask(String taskId) throws Exception {
    for (int i = 0; i < 30; i++) {
      var result = client.getTask(taskId).execute();
      String status = result.getData().getStatus();
      if ("completed".equals(status) || "failed".equals(status)) return;
      Thread.sleep(1000);
    }
    Assertions.fail("Task " + taskId + " did not complete after 30 attempts");
  }

  /** Generates a random lowercase alphanumeric string of the given length. */
  protected String randomString(int n) {
    return RandomStringUtils.randomAlphanumeric(n).toLowerCase();
  }
}
