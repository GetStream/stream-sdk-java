package io.getstream;

import io.getstream.models.*;
import io.getstream.services.Feed;
import io.getstream.services.Feeds;
import io.getstream.services.ModerationImpl;
import io.getstream.services.framework.StreamSDKClient;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ModerationTest {
  static StreamSDKClient client;
  static ModerationImpl moderation;
  static Feeds feeds;
  static String testUserId;
  static String testUserId2;
  static String testModeratorId;
  static String testFeedId;
  static String testActivityId;

  @BeforeAll
  static void setup() throws Exception {
    client = new StreamSDKClient();
    moderation = new ModerationImpl(client.getHttpClient());
    feeds = client.feeds();

    // Create test users
    testUserId = "test-user-" + RandomStringUtils.randomAlphanumeric(8);
    testUserId2 = "test-user-2-" + RandomStringUtils.randomAlphanumeric(8);
    testModeratorId = "moderator-" + RandomStringUtils.randomAlphanumeric(8);

    Map<String, UserRequest> usersMap = new HashMap<>();
    usersMap.put(
        testUserId, UserRequest.builder().id(testUserId).name("Test User 1").role("user").build());
    usersMap.put(
        testUserId2,
        UserRequest.builder().id(testUserId2).name("Test User 2").role("user").build());
    usersMap.put(
        testModeratorId,
        UserRequest.builder().id(testModeratorId).name("Moderator").role("admin").build());

    UpdateUsersRequest updateUsersRequest = UpdateUsersRequest.builder().users(usersMap).build();
    client.updateUsers(updateUsersRequest).execute();

    // Create a test feed
    Feed testFeed = new Feed("user", testUserId, feeds);
    GetOrCreateFeedRequest feedRequest =
        GetOrCreateFeedRequest.builder().userID(testUserId).build();
    GetOrCreateFeedResponse feedResponse = testFeed.getOrCreate(feedRequest).getData();
    testFeedId = feedResponse.getFeed().getFeed();
  }

  @DisplayName("Can ban user with reason")
  @Test
  void testBanWithReason() throws Exception {
    // snippet-start: BanWithReason
    BanRequest request =
        BanRequest.builder()
            .targetUserID(testUserId)
            .reason("spam")
            .timeout(60) // 60 minutes
            .bannedByID(testModeratorId)
            .build();

    BanResponse response = moderation.ban(request).execute().getData();
    // snippet-end: BanWithReason

    Assertions.assertNotNull(response);
  }

  @DisplayName("Can flag activity")
  @Test
  void testFlagActivity() throws Exception {
    // Create an activity first
    AddActivityRequest activity =
        AddActivityRequest.builder()
            .type("post")
            .text("This is a test activity that might need moderation")
            .userID(testUserId)
            .feeds(List.of(testFeedId))
            .build();

    AddActivityResponse createResponse = feeds.addActivity(activity).execute().getData();
    String activityId = createResponse.getActivity().getId();
    testActivityId = activityId;

    // snippet-start: FlagActivity
    FlagRequest request =
        FlagRequest.builder()
            .entityType("activity")
            .entityID(activityId)
            .entityCreatorID(testUserId)
            .reason("inappropriate content")
            .userID(testUserId2)
            .build();

    FlagResponse response = moderation.flag(request).execute().getData();
    // snippet-end: FlagActivity

    Assertions.assertNotNull(response);
  }

  @DisplayName("Can mute user")
  @Test
  void testMuteUser() throws Exception {
    // snippet-start: MuteUser
    MuteRequest request =
        MuteRequest.builder()
            .targetIds(List.of(testUserId, testUserId2))
            .timeout(30) // 30 minutes
            .userID(testModeratorId)
            .build();

    MuteResponse response = moderation.mute(request).execute().getData();
    // snippet-end: MuteUser

    Assertions.assertNotNull(response);
  }

  @DisplayName("Can flag user")
  @Test
  void testFlagUser() throws Exception {
    // snippet-start: FlagUser
    FlagRequest request =
        FlagRequest.builder()
            .entityType("user")
            .entityID(testUserId)
            .entityCreatorID(testUserId)
            .reason("spam")
            .userID(testUserId2)
            .build();

    FlagResponse response = moderation.flag(request).execute().getData();
    // snippet-end: FlagUser

    Assertions.assertNotNull(response);
  }

  @DisplayName("Can check activity content")
  @Test
  void testCheckActivityContent() throws Exception {
    // Create an activity first
    AddActivityRequest activity =
        AddActivityRequest.builder()
            .type("post")
            .text("This is some content to moderate")
            .userID(testUserId)
            .feeds(List.of(testFeedId))
            .build();

    AddActivityResponse createResponse = feeds.addActivity(activity).execute().getData();
    String activityId = createResponse.getActivity().getId();

    // snippet-start: CheckActivityContent
    ModerationPayload payload =
        ModerationPayload.builder().texts(List.of("This is some content to moderate")).build();

    CheckRequest request =
        CheckRequest.builder()
            .entityType("activity")
            .entityID(activityId)
            .entityCreatorID(testUserId)
            .moderationPayload(payload)
            .build();

    CheckResponse response = moderation.check(request).execute().getData();
    // snippet-end: CheckActivityContent

    Assertions.assertNotNull(response);
  }

  @DisplayName("Can query review queue with filter")
  @Test
  void testQueryReviewQueueWithFilter() throws Exception {
    // snippet-start: QueryReviewQueueWithFilter
    Map<String, Object> filter = new HashMap<>();
    filter.put("status", "pending");

    QueryReviewQueueRequest request =
        QueryReviewQueueRequest.builder().filter(filter).limit(25).build();

    QueryReviewQueueResponse response = moderation.queryReviewQueue(request).execute().getData();
    // snippet-end: QueryReviewQueueWithFilter

    Assertions.assertNotNull(response);
  }

  @DisplayName("Can unban user")
  @Test
  void testUnbanUser() throws Exception {
    // First ban the user
    BanRequest banRequest =
        BanRequest.builder()
            .targetUserID(testUserId)
            .reason("test")
            .bannedByID(testModeratorId)
            .build();
    moderation.ban(banRequest).execute();

    // snippet-start: UnbanUser
    UnbanRequest request =
        UnbanRequest.builder().TargetUserID(testUserId).unbannedByID(testModeratorId).build();

    UnbanResponse response = moderation.unban(request).execute().getData();
    // snippet-end: UnbanUser

    Assertions.assertNotNull(response);
  }

  @DisplayName("Can query moderation logs")
  @Test
  void testQueryModerationLogs() throws Exception {
    // snippet-start: QueryModerationLogs
    QueryModerationLogsRequest request = QueryModerationLogsRequest.builder().limit(10).build();

    QueryModerationLogsResponse response =
        moderation.queryModerationLogs(request).execute().getData();
    // snippet-end: QueryModerationLogs

    Assertions.assertNotNull(response);
  }
}
