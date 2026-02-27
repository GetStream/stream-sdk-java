package io.getstream;

import static org.junit.jupiter.api.Assertions.*;

import io.getstream.models.*;
import java.util.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ChatPollsIntegrationTest extends ChatTestBase {

  private final List<String> createdUserIds = new ArrayList<>();
  private final List<String> createdPollIds = new ArrayList<>();
  private final List<String> createdChannelIds = new ArrayList<>();

  @AfterAll
  void cleanup() {
    for (String pollId : createdPollIds) {
      try {
        if (!createdUserIds.isEmpty()) {
          client
              .deletePoll(pollId, DeletePollRequest.builder().UserID(createdUserIds.get(0)).build())
              .execute();
        }
      } catch (Exception ignored) {
      }
    }
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
  void testCreateAndQueryPoll() throws Exception {
    List<String> userIds = createTestUsers(1);
    createdUserIds.addAll(userIds);
    String userId = userIds.get(0);

    try {
      // Create a poll with options
      List<PollOptionInput> options =
          Arrays.asList(
              PollOptionInput.builder().text("Red").build(),
              PollOptionInput.builder().text("Blue").build(),
              PollOptionInput.builder().text("Green").build());

      var createResp =
          client
              .createPoll(
                  CreatePollRequest.builder()
                      .name("Favorite color?")
                      .description("Pick your favorite color")
                      .enforceUniqueVote(true)
                      .userID(userId)
                      .options(options)
                      .build())
              .execute();

      assertNotNull(createResp.getData().getPoll());
      String pollId = createResp.getData().getPoll().getId();
      assertNotNull(pollId);
      assertFalse(pollId.isEmpty());
      assertEquals("Favorite color?", createResp.getData().getPoll().getName());
      assertTrue(createResp.getData().getPoll().getEnforceUniqueVote());
      assertNotNull(createResp.getData().getPoll().getOptions());
      assertEquals(3, createResp.getData().getPoll().getOptions().size());

      createdPollIds.add(pollId);

      // Query polls with filter by ID
      var queryResp =
          client
              .queryPolls(
                  QueryPollsRequest.builder()
                      .UserID(userId)
                      .filter(Map.of("id", pollId))
                      .build())
              .execute();

      assertNotNull(queryResp.getData().getPolls());
      assertFalse(queryResp.getData().getPolls().isEmpty());
      assertEquals(pollId, queryResp.getData().getPolls().get(0).getId());
    } catch (Exception e) {
      String msg = e.getMessage();
      if (msg != null
          && (msg.contains("polls not enabled")
              || msg.contains("Poll")
              || msg.contains("feature not available"))) {
        Assumptions.assumeTrue(false, "Polls not enabled for this app: " + msg);
      }
      throw e;
    }
  }

  @Test
  @Order(2)
  void testCastPollVote() throws Exception {
    // Need two users: one creates the poll/channel/message, one votes
    List<String> userIds = createTestUsers(2);
    createdUserIds.addAll(userIds);
    String creatorId = userIds.get(0);
    String voterId = userIds.get(1);

    try {
      // Create a poll with two options
      List<PollOptionInput> options =
          Arrays.asList(
              PollOptionInput.builder().text("Yes").build(),
              PollOptionInput.builder().text("No").build());

      var createResp =
          client
              .createPoll(
                  CreatePollRequest.builder()
                      .name("Vote test poll")
                      .enforceUniqueVote(true)
                      .userID(creatorId)
                      .options(options)
                      .build())
              .execute();

      assertNotNull(createResp.getData().getPoll());
      String pollId = createResp.getData().getPoll().getId();
      assertNotNull(pollId);
      String optionId = createResp.getData().getPoll().getOptions().get(0).getId();
      assertNotNull(optionId);
      createdPollIds.add(pollId);

      // Create a channel with both users as members
      String channelId =
          createTestChannelWithMembers(creatorId, Arrays.asList(creatorId, voterId));
      createdChannelIds.add(channelId);

      // Send a message with the poll attached
      var sendResp =
          chat.sendMessage(
                  "messaging",
                  channelId,
                  SendMessageRequest.builder()
                      .message(
                          MessageRequest.builder()
                              .text("Please vote!")
                              .userID(creatorId)
                              .pollID(pollId)
                              .build())
                      .build())
              .execute();
      assertNotNull(sendResp.getData().getMessage().getId());
      String msgId = sendResp.getData().getMessage().getId();

      // Cast a vote as the voter
      var voteResp =
          chat.castPollVote(
                  msgId,
                  pollId,
                  CastPollVoteRequest.builder()
                      .userID(voterId)
                      .vote(VoteData.builder().optionID(optionId).build())
                      .build())
              .execute();

      assertNotNull(voteResp.getData().getVote());
      assertEquals(optionId, voteResp.getData().getVote().getOptionID());

      // Verify the poll now has 1 vote
      var getResp = client.getPoll(pollId).execute();
      assertNotNull(getResp.getData().getPoll());
      assertEquals(1, getResp.getData().getPoll().getVoteCount());

    } catch (Exception e) {
      String msg = e.getMessage();
      if (msg != null
          && (msg.contains("polls not enabled")
              || msg.contains("Poll")
              || msg.contains("feature not available"))) {
        Assumptions.assumeTrue(false, "Polls not enabled for this app: " + msg);
      }
      throw e;
    }
  }
}
