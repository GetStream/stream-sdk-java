package io.getstream;

import static org.junit.jupiter.api.Assertions.*;

import io.getstream.models.*;
import io.getstream.services.ModerationImpl;
import java.util.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ModerationIntegrationTest extends ChatTestBase {

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
  @Order(3)
  void testFlagMessageAndUser() throws Exception {
    List<String> userIds = createTestUsers(2);
    createdUserIds.addAll(userIds);
    String userId = userIds.get(0);
    String flaggerId = userIds.get(1);

    // Create a channel with both users as members
    String channelId = createTestChannelWithMembers(userId, userIds);
    createdChannelIds.add(channelId);

    // Send a message to flag
    String msgId = sendTestMessage("messaging", channelId, userId, "Message to be flagged");

    ModerationImpl moderation = new ModerationImpl(client.getHttpClient());

    // Flag the message
    var flagMsgResp =
        moderation
            .flag(
                FlagRequest.builder()
                    .entityID(msgId)
                    .entityType("stream:chat:v1:message")
                    .entityCreatorID(userId)
                    .userID(flaggerId)
                    .reason("inappropriate content")
                    .build())
            .execute();
    assertNotNull(flagMsgResp.getData(), "Flag message response data should not be null");
    assertNotNull(flagMsgResp.getData().getItemID(), "Flag should return an item ID");

    // Flag the user
    var flagUserResp =
        moderation
            .flag(
                FlagRequest.builder()
                    .entityID(userId)
                    .entityType("stream:user")
                    .entityCreatorID(userId)
                    .userID(flaggerId)
                    .reason("spam")
                    .build())
            .execute();
    assertNotNull(flagUserResp.getData(), "Flag user response data should not be null");
    assertNotNull(flagUserResp.getData().getItemID(), "Flag user should return an item ID");
  }

  @Test
  @Order(2)
  void testMuteUnmuteUser() throws Exception {
    List<String> userIds = createTestUsers(2);
    createdUserIds.addAll(userIds);
    String muterId = userIds.get(0);
    String targetId = userIds.get(1);

    ModerationImpl moderation = new ModerationImpl(client.getHttpClient());

    // Mute target user as muter (no timeout)
    var muteResp =
        moderation
            .mute(
                MuteRequest.builder()
                    .targetIds(List.of(targetId))
                    .userID(muterId)
                    .build())
            .execute();

    assertNotNull(muteResp.getData(), "Mute response data should not be null");
    assertNotNull(muteResp.getData().getMutes(), "Mutes list should not be null");
    assertFalse(muteResp.getData().getMutes().isEmpty(), "Mutes list should not be empty");

    var mute = muteResp.getData().getMutes().get(0);
    assertNotNull(mute.getUser(), "Mute should have a User");
    assertNotNull(mute.getTarget(), "Mute should have a Target");
    assertNull(mute.getExpires(), "Mute without timeout should have no Expires");

    // Verify mute appears in QueryUsers for the muting user
    var queryResp =
        client
            .queryUsers(
                QueryUsersRequest.builder()
                    .Payload(
                        QueryUsersPayload.builder()
                            .filterConditions(Map.of("id", Map.of("$eq", muterId)))
                            .build())
                    .build())
            .execute();
    assertNotNull(queryResp.getData().getUsers());
    assertFalse(queryResp.getData().getUsers().isEmpty(), "Should find the muting user");
    var muterUser = queryResp.getData().getUsers().get(0);
    assertNotNull(muterUser.getMutes(), "User should have mutes after muting");
    assertFalse(muterUser.getMutes().isEmpty(), "User mutes list should not be empty");

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
  @Order(1)
  void testBanUnbanUser() throws Exception {
    List<String> userIds = createTestUsers(2);
    createdUserIds.addAll(userIds);
    String adminId = userIds.get(0);
    String targetId = userIds.get(1);

    // Create a channel with both users as members
    String channelId = createTestChannelWithMembers(adminId, userIds);
    createdChannelIds.add(channelId);
    String cid = "messaging:" + channelId;

    ModerationImpl moderation = new ModerationImpl(client.getHttpClient());

    // Ban target user from channel
    var banResp =
        moderation
            .ban(
                BanRequest.builder()
                    .targetUserID(targetId)
                    .bannedByID(adminId)
                    .channelCid(cid)
                    .reason("moderation test ban")
                    .build())
            .execute();
    assertNotNull(banResp.getData());

    // Verify ban via queryBannedUsers
    var bansResp =
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
    assertNotNull(bansResp.getData().getBans());
    boolean banFound =
        bansResp.getData().getBans().stream()
            .anyMatch(b -> b.getUser() != null && targetId.equals(b.getUser().getId()));
    assertTrue(banFound, "Target user should appear in banned list");

    // Unban the user from the channel
    moderation
        .unban(UnbanRequest.builder().TargetUserID(targetId).ChannelCid(cid).build())
        .execute();

    // Verify ban is removed after unban
    var bansAfter =
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
    boolean stillBanned =
        bansAfter.getData().getBans() != null
            && bansAfter.getData().getBans().stream()
                .anyMatch(b -> b.getUser() != null && targetId.equals(b.getUser().getId()));
    assertFalse(stillBanned, "Target user should not appear in banned list after unban");
  }
}
