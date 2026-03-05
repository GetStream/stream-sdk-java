package io.getstream;

import static org.junit.jupiter.api.Assertions.*;

import io.getstream.models.*;
import java.util.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ChatReactionIntegrationTest extends ChatTestBase {

  private final List<String> createdUserIds = new ArrayList<>();
  private final List<String> createdChannelIds = new ArrayList<>();

  @AfterAll
  void cleanup() {
    for (String channelId : createdChannelIds) {
      try {
        chat.deleteChannel(
                "messaging", channelId, DeleteChannelRequest.builder().HardDelete(true).build())
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
  void testSendAndGetReactions() throws Exception {
    List<String> userIds = createTestUsers(2);
    createdUserIds.addAll(userIds);
    String userId = userIds.get(0);
    String userId2 = userIds.get(1);

    String channelId = createTestChannelWithMembers(userId, Arrays.asList(userId, userId2));
    createdChannelIds.add(channelId);

    String messageId = sendTestMessage("messaging", channelId, userId, "React to this message");

    // Send a like from user2
    var sendResp =
        chat.sendReaction(
                messageId,
                SendReactionRequest.builder()
                    .reaction(ReactionRequest.builder().type("like").userID(userId2).build())
                    .build())
            .execute();
    assertNotNull(sendResp.getData().getReaction());
    assertEquals("like", sendResp.getData().getReaction().getType());
    assertEquals(userId2, sendResp.getData().getReaction().getUserID());

    // Send a love from user1
    chat.sendReaction(
            messageId,
            SendReactionRequest.builder()
                .reaction(ReactionRequest.builder().type("love").userID(userId).build())
                .build())
        .execute();

    // Get reactions, verify at least 2
    var getResp = chat.getReactions(messageId).execute();
    assertNotNull(getResp.getData().getReactions());
    assertTrue(getResp.getData().getReactions().size() >= 2);
  }

  @Test
  @Order(2)
  void testDeleteReaction() throws Exception {
    List<String> userIds = createTestUsers(1);
    createdUserIds.addAll(userIds);
    String userId = userIds.get(0);

    String channelId = createTestChannel(userId);
    createdChannelIds.add(channelId);

    String messageId =
        sendTestMessage("messaging", channelId, userId, "Message for reaction deletion");

    // Send a like reaction
    chat.sendReaction(
            messageId,
            SendReactionRequest.builder()
                .reaction(ReactionRequest.builder().type("like").userID(userId).build())
                .build())
        .execute();

    // Delete the reaction
    chat.deleteReaction(messageId, "like", DeleteReactionRequest.builder().UserID(userId).build())
        .execute();

    // Verify reaction is gone
    var getResp = chat.getReactions(messageId).execute();
    List<ReactionResponse> reactions = getResp.getData().getReactions();
    for (ReactionResponse r : reactions) {
      if (userId.equals(r.getUserID())) {
        assertNotEquals("like", r.getType(), "Like reaction should have been deleted");
      }
    }
  }

  @Test
  @Order(3)
  void testEnforceUniqueReaction() throws Exception {
    List<String> userIds = createTestUsers(1);
    createdUserIds.addAll(userIds);
    String userId = userIds.get(0);

    String channelId = createTestChannel(userId);
    createdChannelIds.add(channelId);

    String messageId =
        sendTestMessage("messaging", channelId, userId, "Message for unique reaction test");

    // Send first reaction (like) with enforce_unique
    chat.sendReaction(
            messageId,
            SendReactionRequest.builder()
                .reaction(ReactionRequest.builder().type("like").userID(userId).build())
                .enforceUnique(true)
                .build())
        .execute();

    // Send second reaction (love) with enforce_unique — should replace like
    chat.sendReaction(
            messageId,
            SendReactionRequest.builder()
                .reaction(ReactionRequest.builder().type("love").userID(userId).build())
                .enforceUnique(true)
                .build())
        .execute();

    // Verify user has exactly 1 reaction
    var getResp = chat.getReactions(messageId).execute();
    List<ReactionResponse> reactions = getResp.getData().getReactions();
    long userReactionCount = reactions.stream().filter(r -> userId.equals(r.getUserID())).count();
    assertEquals(1, userReactionCount, "EnforceUnique should keep only one reaction per user");
  }
}
