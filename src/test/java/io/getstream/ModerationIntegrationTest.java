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
