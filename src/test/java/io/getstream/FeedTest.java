 package io.getstream;

 import io.getstream.models.*;
 import io.getstream.services.Feeds;
 import io.getstream.services.FeedsImpl;
 import io.getstream.services.framework.StreamHTTPClient;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import org.apache.commons.lang3.RandomStringUtils;
 import org.junit.jupiter.api.*;

 public class FeedTest extends BasicTest {

  private static Feeds feeds;

  @BeforeAll
  static void setupFeeds() {
    // Create feeds service instance using the same HTTP client setup as the main client
    feeds = new FeedsImpl(new StreamHTTPClient());
  }

  @Test
  public void testFeedsFollowAndActivity() {
    // Generate random user IDs (similar to Go code's getRandomString function)
    String userID1 = RandomStringUtils.randomAlphanumeric(10);
    String userID2 = RandomStringUtils.randomAlphanumeric(10);

    // Update users (similar to Go code's UpdateUsers)
    Map<String, UserRequest> usersMap = new HashMap<>();
    usersMap.put(userID1, UserRequest.builder().id(userID1).build());
    usersMap.put(userID2, UserRequest.builder().id(userID2).build());

    UpdateUsersRequest updateUsersRequest = UpdateUsersRequest.builder().users(usersMap).build();

    Assertions.assertDoesNotThrow(() -> client.updateUsers(updateUsersRequest).execute());

    // Create feeds for both users (similar to Go code's GetOrCreateFeed)
    GetOrCreateFeedRequest feedRequest1 =
 GetOrCreateFeedRequest.builder().userID(userID1).build();

    GetOrCreateFeedRequest feedRequest2 =
 GetOrCreateFeedRequest.builder().userID(userID2).build();

    var feedOriginResponse =
        Assertions.assertDoesNotThrow(
            () -> feeds.getOrCreateFeed("user", userID1, feedRequest1).execute());

    var feedFollowerResponse =
        Assertions.assertDoesNotThrow(
            () -> feeds.getOrCreateFeed("user", userID2, feedRequest2).execute());

    String originFid = feedOriginResponse.getData().getFeed().getFid();
    String followerFid = feedFollowerResponse.getData().getFeed().getFid();

    // Create follow relationship (follower follows origin, similar to Go code's Follow)
    FollowRequest followRequest =
        FollowRequest.builder().sourceFid(followerFid).targetFid(originFid).build();

    Assertions.assertDoesNotThrow(() -> feeds.follow(followRequest).execute());

    // Add activity to origin feed (similar to Go code's AddActivity)
    AddActivityRequest activityRequest =
        AddActivityRequest.builder()
            .type("post1")
            .feeds(List.of(originFid))
            .text(RandomStringUtils.randomAlphanumeric(10))
            .userID(userID1)
            .build();

    var addActivityResponse =
        Assertions.assertDoesNotThrow(() -> feeds.addActivity(activityRequest).execute());

    // Fetch both feeds to verify activity propagation
    var originActivitiesResponse =
        Assertions.assertDoesNotThrow(
            () -> feeds.getOrCreateFeed("user", userID1, feedRequest1).execute());

    var followerActivitiesResponse =
        Assertions.assertDoesNotThrow(
            () -> feeds.getOrCreateFeed("user", userID2, feedRequest2).execute());

    // Verify activities exist
    Assertions.assertFalse(
        originActivitiesResponse.getData().getActivities().isEmpty(),
        "Origin feed should have activities");
    Assertions.assertFalse(
        followerActivitiesResponse.getData().getActivities().isEmpty(),
        "Follower feed should have activities from followed feed");

    // Print activities (similar to Go code's println statements)
    for (ActivityResponse activity : originActivitiesResponse.getData().getActivities()) {
      System.out.println(
          "Origin Activity: "
              + activity.getId()
              + " "
              + (activity.getText() != null ? activity.getText() : "")
              + " "
              + activity.getType());
    }

    for (ActivityResponse activity : followerActivitiesResponse.getData().getActivities()) {
      System.out.println(
          "Follower Activity: "
              + activity.getId()
              + " "
              + (activity.getText() != null ? activity.getText() : "")
              + " "
              + activity.getType());
    }

    // Verify that the activity was created successfully
    Assertions.assertNotNull(addActivityResponse.getData().getActivity().getId());
    Assertions.assertEquals("post1", addActivityResponse.getData().getActivity().getType());
    Assertions.assertNotNull(addActivityResponse.getData().getActivity().getText());
  }
 }
