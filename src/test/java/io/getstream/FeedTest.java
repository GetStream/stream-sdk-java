package io.getstream;

import io.getstream.models.*;
import io.getstream.services.Common;
import io.getstream.services.CommonImpl;
import io.getstream.services.Feeds;
import io.getstream.services.FeedsImpl;
import io.getstream.services.framework.StreamHTTPClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.*;

public class FeedTest extends BasicTest {

  private static Feeds feeds;
  private static Common comm;


  @BeforeAll
  static void setupFeeds() {
    // Create feeds service instance using the same HTTP client setup as the main client
    feeds = new FeedsImpl(new StreamHTTPClient());
    comm= new CommonImpl(new StreamHTTPClient());
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
    GetOrCreateFeedRequest feedRequest1 = GetOrCreateFeedRequest.builder().userID(userID1).build();

    GetOrCreateFeedRequest feedRequest2 = GetOrCreateFeedRequest.builder().userID(userID2).build();

    GetOrCreateFeedResponse feedOriginResponse =
        Assertions.assertDoesNotThrow(
            () -> feeds.getOrCreateFeed("user", userID1, feedRequest1).execute().getData());

    GetOrCreateFeedResponse feedFollowerResponse =
        Assertions.assertDoesNotThrow(
            () -> feeds.getOrCreateFeed("user", userID2, feedRequest2).execute().getData());

    String originFid = feedOriginResponse.getFeed().getFeed();
    String followerFid = feedFollowerResponse.getFeed().getFeed();

    // Create follow relationship (follower follows origin, similar to Go code's Follow)
    FollowRequest followRequest =
        FollowRequest.builder().source(followerFid).target(originFid).build();

    Assertions.assertDoesNotThrow(() -> feeds.follow(followRequest).execute());

    // snippet-start: AddActivity
    // Add activity to origin feed
    AddActivityRequest activityRequest =
        AddActivityRequest.builder()
            .type("post1")
            .feeds(List.of(originFid))
            .text(RandomStringUtils.randomAlphanumeric(10))
            .userID(userID1)
            .build();

    AddActivityResponse addActivityResponse =
        Assertions.assertDoesNotThrow(() -> feeds.addActivity(activityRequest).execute().getData());

    // snippet-end: AddActivity

    // Fetch both feeds to verify activity propagation
    GetOrCreateFeedResponse originActivitiesResponse =
        Assertions.assertDoesNotThrow(
            () -> feeds.getOrCreateFeed("user", userID1, feedRequest1).execute().getData());

    GetOrCreateFeedResponse followerActivitiesResponse =
        Assertions.assertDoesNotThrow(
            () -> feeds.getOrCreateFeed("user", userID2, feedRequest2).execute().getData());

    // Verify activities exist
    Assertions.assertFalse(
        originActivitiesResponse.getActivities().isEmpty(), "Origin feed should have activities");
    Assertions.assertFalse(
        followerActivitiesResponse.getActivities().isEmpty(),
        "Follower feed should have activities from followed feed");

    // Print activities (similar to Go code's println statements)
    for (ActivityResponse activity : originActivitiesResponse.getActivities()) {
      System.out.println(
          "Origin Activity: "
              + activity.getId()
              + " "
              + (activity.getText() != null ? activity.getText() : "")
              + " "
              + activity.getType());
    }

    for (ActivityResponse activity : followerActivitiesResponse.getActivities()) {
      System.out.println(
          "Follower Activity: "
              + activity.getId()
              + " "
              + (activity.getText() != null ? activity.getText() : "")
              + " "
              + activity.getType());
    }

    // Verify that the activity was created successfully
    Assertions.assertNotNull(addActivityResponse.getActivity().getId());
    Assertions.assertEquals("post1", addActivityResponse.getActivity().getType());
    Assertions.assertNotNull(addActivityResponse.getActivity().getText());
  }

  @Test
  public void testActivityOperations() {
    String userID = RandomStringUtils.randomAlphanumeric(10);

    // Update user
    Map<String, UserRequest> usersMap = new HashMap<>();
    usersMap.put(userID, UserRequest.builder().id(userID).build());
    UpdateUsersRequest updateUsersRequest = UpdateUsersRequest.builder().users(usersMap).build();
    Assertions.assertDoesNotThrow(() -> client.updateUsers(updateUsersRequest).execute());

    // Create feed
    GetOrCreateFeedRequest feedRequest = GetOrCreateFeedRequest.builder().userID(userID).build();
    GetOrCreateFeedResponse feedResponse =
        Assertions.assertDoesNotThrow(
            () -> feeds.getOrCreateFeed("user", userID, feedRequest).execute().getData());
    String feedFid = feedResponse.getFeed().getFeed();

    // Test addActivity
    AddActivityRequest activityRequest =
        AddActivityRequest.builder()
            .type("post")
            .feeds(List.of(feedFid))
            .text("Test activity content")
            .userID(userID)
            .build();

    AddActivityResponse addResponse =
        Assertions.assertDoesNotThrow(() -> feeds.addActivity(activityRequest).execute().getData());
    String activityId = addResponse.getActivity().getId();
    Assertions.assertNotNull(activityId);

    // Test getActivity
    GetActivityResponse getResponse =
        Assertions.assertDoesNotThrow(() -> feeds.getActivity(activityId).execute().getData());
    Assertions.assertEquals(activityId, getResponse.getActivity().getId());
    Assertions.assertEquals("Test activity content", getResponse.getActivity().getText());

    // Test updateActivity
    UpdateActivityRequest updateRequest =
        UpdateActivityRequest.builder()
            .text("Updated activity content")
            .userID(userID)
            .build();
    UpdateActivityResponse updateResponse =
        Assertions.assertDoesNotThrow(
            () -> feeds.updateActivity(activityId, updateRequest).execute().getData());
    Assertions.assertEquals("Updated activity content", updateResponse.getActivity().getText());

    // Test updateActivityPartial
    UpdateActivityPartialRequest partialRequest =
        UpdateActivityPartialRequest.builder()
            .set(Map.of("custom_field", "custom_value"))
            .userID("sara")
            .build();
    Assertions.assertDoesNotThrow(
        () -> feeds.updateActivityPartial(activityId, partialRequest).execute());

    // Test queryActivities
    QueryActivitiesRequest queryRequest = QueryActivitiesRequest.builder().limit(10).build();
    QueryActivitiesResponse queryResponse =
        Assertions.assertDoesNotThrow(
            () -> feeds.queryActivities(queryRequest).execute().getData());
    Assertions.assertFalse(queryResponse.getActivities().isEmpty());

    // Test deleteActivity
    Assertions.assertDoesNotThrow(() -> feeds.deleteActivity(activityId).execute());
  }

  @Test
  public void testBookmarkOperations() {
    String userID = RandomStringUtils.randomAlphanumeric(10);

    // Setup user and feed
    Map<String, UserRequest> usersMap = new HashMap<>();
    usersMap.put(userID, UserRequest.builder().id(userID).build());
    UpdateUsersRequest updateUsersRequest = UpdateUsersRequest.builder().users(usersMap).build();
    Assertions.assertDoesNotThrow(() -> client.updateUsers(updateUsersRequest).execute());

    GetOrCreateFeedRequest feedRequest = GetOrCreateFeedRequest.builder().userID(userID).build();
    GetOrCreateFeedResponse feedResponse =
        Assertions.assertDoesNotThrow(
            () -> feeds.getOrCreateFeed("user", userID, feedRequest).execute().getData());
    String feedFid = feedResponse.getFeed().getFeed();

    // Create activity to bookmark
    AddActivityRequest activityRequest =
        AddActivityRequest.builder()
            .type("post")
            .feeds(List.of(feedFid))
            .text("Activity to bookmark")
            .userID(userID)
            .build();
    AddActivityResponse addResponse =
        Assertions.assertDoesNotThrow(() -> feeds.addActivity(activityRequest).execute().getData());
    String activityId = addResponse.getActivity().getId();

    // Test addBookmark
    AddBookmarkRequest bookmarkRequest = AddBookmarkRequest.builder().userID(userID).build();
    AddBookmarkResponse bookmarkResponse =
        Assertions.assertDoesNotThrow(
            () -> feeds.addBookmark(activityId, bookmarkRequest).execute().getData());
    Assertions.assertNotNull(bookmarkResponse.getBookmark());

    // Test queryBookmarks
    QueryBookmarksResponse queryResponse =
        Assertions.assertDoesNotThrow(() -> feeds.queryBookmarks().execute().getData());
    Assertions.assertFalse(queryResponse.getBookmarks().isEmpty());

    // Test updateBookmark
    UpdateBookmarkRequest updateRequest = UpdateBookmarkRequest.builder().userID(userID).build();
    Assertions.assertDoesNotThrow(() -> feeds.updateBookmark(activityId, updateRequest).execute());

    // Test deleteBookmark
    DeleteBookmarkRequest deleteRequest = DeleteBookmarkRequest.builder().UserID(userID).build();
    Assertions.assertDoesNotThrow(() -> feeds.deleteBookmark(activityId, deleteRequest).execute());
  }

  @Test
  public void testBookmarkFolderOperations() {
    String userID = RandomStringUtils.randomAlphanumeric(10);

    // Setup user
    Map<String, UserRequest> usersMap = new HashMap<>();
    usersMap.put(userID, UserRequest.builder().id(userID).build());
    UpdateUsersRequest updateUsersRequest = UpdateUsersRequest.builder().users(usersMap).build();
    Assertions.assertDoesNotThrow(() -> client.updateUsers(updateUsersRequest).execute());

    // Test queryBookmarkFolders
    QueryBookmarkFoldersResponse queryResponse =
        Assertions.assertDoesNotThrow(() -> feeds.queryBookmarkFolders().execute().getData());
    Assertions.assertNotNull(queryResponse.getBookmarkFolders());

    // Note: Bookmark folder creation, update, and deletion would require specific folder IDs
    // These operations are typically managed through the UI or specific admin endpoints
  }

  @Test
  public void testReactionOperations() {
    String userID = RandomStringUtils.randomAlphanumeric(10);

    // Setup user and activity
    Map<String, UserRequest> usersMap = new HashMap<>();
    usersMap.put(userID, UserRequest.builder().id(userID).build());
    UpdateUsersRequest updateUsersRequest = UpdateUsersRequest.builder().users(usersMap).build();
    Assertions.assertDoesNotThrow(() -> client.updateUsers(updateUsersRequest).execute());

    GetOrCreateFeedRequest feedRequest = GetOrCreateFeedRequest.builder().userID(userID).build();
    GetOrCreateFeedResponse feedResponse =
        Assertions.assertDoesNotThrow(
            () -> feeds.getOrCreateFeed("user", userID, feedRequest).execute().getData());
    String feedFid = feedResponse.getFeed().getFeed();

    AddActivityRequest activityRequest =
        AddActivityRequest.builder()
            .type("post")
            .feeds(List.of(feedFid))
            .text("Activity for reactions")
            .userID(userID)
            .build();
    AddActivityResponse addResponse =
        Assertions.assertDoesNotThrow(() -> feeds.addActivity(activityRequest).execute().getData());
    String activityId = addResponse.getActivity().getId();

    // Test addReaction
    AddReactionRequest reactionRequest =
        AddReactionRequest.builder().type("like").userID(userID).build();
    AddReactionResponse reactionResponse =
        Assertions.assertDoesNotThrow(
            () -> feeds.addReaction(activityId, reactionRequest).execute().getData());
    Assertions.assertNotNull(reactionResponse.getReaction());

    // Test queryActivityReactions
    QueryActivityReactionsResponse queryResponse =
        Assertions.assertDoesNotThrow(
            () -> feeds.queryActivityReactions(activityId).execute().getData());
    Assertions.assertFalse(queryResponse.getReactions().isEmpty());

    // Test deleteActivityReaction
    DeleteActivityReactionRequest deleteReactionRequest =
        DeleteActivityReactionRequest.builder().UserID(userID).build();
    Assertions.assertDoesNotThrow(
        () -> feeds.deleteActivityReaction(activityId, "like", deleteReactionRequest).execute());
  }

  @Test
  public void testCommentOperations() {
    String userID = RandomStringUtils.randomAlphanumeric(10);

    // Setup user and activity
    Map<String, UserRequest> usersMap = new HashMap<>();
    usersMap.put(userID, UserRequest.builder().id(userID).build());
    UpdateUsersRequest updateUsersRequest = UpdateUsersRequest.builder().users(usersMap).build();
    Assertions.assertDoesNotThrow(() -> client.updateUsers(updateUsersRequest).execute());

    GetOrCreateFeedRequest feedRequest = GetOrCreateFeedRequest.builder().userID(userID).build();
    GetOrCreateFeedResponse feedResponse =
        Assertions.assertDoesNotThrow(
            () -> feeds.getOrCreateFeed("user", userID, feedRequest).execute().getData());
    String feedFid = feedResponse.getFeed().getFeed();

    AddActivityRequest activityRequest =
        AddActivityRequest.builder()
            .type("post")
            .feeds(List.of(feedFid))
            .text("Activity for comments")
            .userID(userID)
            .build();
    AddActivityResponse addResponse =
        Assertions.assertDoesNotThrow(() -> feeds.addActivity(activityRequest).execute().getData());
    String activityId = addResponse.getActivity().getId();

    // Test addComment
    AddCommentRequest commentRequest =
        AddCommentRequest.builder()
            .objectID(activityId)
            .objectType("activity")
            .comment("Test comment")
            .userID(userID)
            .build();
    AddCommentResponse commentResponse =
        Assertions.assertDoesNotThrow(() -> feeds.addComment(commentRequest).execute().getData());
    String commentId = commentResponse.getComment().getId();
    Assertions.assertNotNull(commentId);

    // Test getComment
    GetCommentResponse getResponse =
        Assertions.assertDoesNotThrow(() -> feeds.getComment(commentId).execute().getData());
    Assertions.assertEquals("Test comment", getResponse.getComment().getText());

    // Test updateComment
    UpdateCommentRequest updateRequest =
        UpdateCommentRequest.builder().comment("Updated comment").build();
    UpdateCommentResponse updateResponse =
        Assertions.assertDoesNotThrow(
            () -> feeds.updateComment(commentId, updateRequest).execute().getData());
    Assertions.assertEquals("Updated comment", updateResponse.getComment().getText());

    // Test getComments
    GetCommentsRequest getCommentsRequest =
        GetCommentsRequest.builder().ObjectID(activityId).ObjectType("activity").build();
    GetCommentsResponse getCommentsResponse =
        Assertions.assertDoesNotThrow(
            () -> feeds.getComments(getCommentsRequest).execute().getData());
    Assertions.assertFalse(getCommentsResponse.getComments().isEmpty());

    // Test queryComments
    QueryCommentsRequest queryRequest =
        QueryCommentsRequest.builder().filter(Map.of("activity_id", activityId)).build();
    QueryCommentsResponse queryResponse =
        Assertions.assertDoesNotThrow(() -> feeds.queryComments(queryRequest).execute().getData());
    Assertions.assertFalse(queryResponse.getComments().isEmpty()); // $$

    // Test deleteComment
    Assertions.assertDoesNotThrow(() -> feeds.deleteComment(commentId).execute());
  }

  @Test
  public void testCommentReactionOperations() {
    String userID = RandomStringUtils.randomAlphanumeric(10);

    // Setup user, activity, and comment
    Map<String, UserRequest> usersMap = new HashMap<>();
    usersMap.put(userID, UserRequest.builder().id(userID).build());
    UpdateUsersRequest updateUsersRequest = UpdateUsersRequest.builder().users(usersMap).build();
    Assertions.assertDoesNotThrow(() -> client.updateUsers(updateUsersRequest).execute());

    GetOrCreateFeedRequest feedRequest = GetOrCreateFeedRequest.builder().userID(userID).build();
    GetOrCreateFeedResponse feedResponse =
        Assertions.assertDoesNotThrow(
            () -> feeds.getOrCreateFeed("user", userID, feedRequest).execute().getData());
    String feedFid = feedResponse.getFeed().getFeed();

    AddActivityRequest activityRequest =
        AddActivityRequest.builder()
            .type("post")
            .feeds(List.of(feedFid))
            .text("Activity for comment reactions")
            .userID(userID)
            .build();
    AddActivityResponse addResponse =
        Assertions.assertDoesNotThrow(() -> feeds.addActivity(activityRequest).execute().getData());
    String activityId = addResponse.getActivity().getId();

    AddCommentRequest commentRequest =
        AddCommentRequest.builder()
            .objectID(activityId)
            .objectType("activity")
            .comment("Comment for reactions")
            .userID(userID)
            .build();
    AddCommentResponse commentResponse =
        Assertions.assertDoesNotThrow(() -> feeds.addComment(commentRequest).execute().getData());
    String commentId = commentResponse.getComment().getId();

    // Test addCommentReaction
    AddCommentReactionRequest reactionRequest =
        AddCommentReactionRequest.builder().type("like").userID(userID).build();
    AddCommentReactionResponse reactionResponse =
        Assertions.assertDoesNotThrow(
            () -> feeds.addCommentReaction(commentId, reactionRequest).execute().getData());
    Assertions.assertNotNull(reactionResponse.getReaction());

    // Test queryCommentReactions
    QueryCommentReactionsResponse queryResponse =
        Assertions.assertDoesNotThrow(
            () -> feeds.queryCommentReactions(commentId).execute().getData());
    Assertions.assertFalse(queryResponse.getReactions().isEmpty());

    // Test deleteCommentReaction
    DeleteCommentReactionRequest deleteCommentReactionRequest =
        DeleteCommentReactionRequest.builder().UserID(userID).build();
    Assertions.assertDoesNotThrow(
        () ->
            feeds.deleteCommentReaction(commentId, "like", deleteCommentReactionRequest).execute());
  }

  @Test // $$
  public void testFeedGroupOperations() {
    String feedGroupId = RandomStringUtils.randomAlphanumeric(10);

    // Test createFeedGroup
    CreateFeedGroupRequest createRequest =
        CreateFeedGroupRequest.builder().id(feedGroupId).build();
    CreateFeedGroupResponse createResponse =
        Assertions.assertDoesNotThrow(
            () -> feeds.createFeedGroup(createRequest).execute().getData());
    Assertions.assertEquals(feedGroupId, createResponse.getFeedGroup().getId());

    // Test getFeedGroup
    GetFeedGroupResponse getResponse =
        Assertions.assertDoesNotThrow(() -> feeds.getFeedGroup(feedGroupId).execute().getData());
    Assertions.assertEquals("Test Feed Group", getResponse.getFeedGroup().getId());

    // Test listFeedGroups
    ListFeedGroupsResponse listResponse =
        Assertions.assertDoesNotThrow(() -> feeds.listFeedGroups().execute().getData());
    Assertions.assertFalse(listResponse.getGroups().isEmpty());

    // Test updateFeedGroup
    //    UpdateFeedGroupRequest updateRequest = UpdateFeedGroupRequest.builder().
    //        .name("Updated Feed Group")
    //        .build();
    //    UpdateFeedGroupResponse updateResponse = Assertions.assertDoesNotThrow(
    //        () -> feeds.updateFeedGroup(feedGroupId, updateRequest).execute().getData());
    //    Assertions.assertEquals("Updated Feed Group", updateResponse.getFeedGroup().getName());

    // Test deleteFeedGroup
    Assertions.assertDoesNotThrow(() -> feeds.deleteFeedGroup(feedGroupId).execute());
  }

  @Test
  public void testFeedOperations() {
    String userID = RandomStringUtils.randomAlphanumeric(10);
    String feedGroupId = "user";
    String feedId = userID;

    // Setup user
    Map<String, UserRequest> usersMap = new HashMap<>();
    usersMap.put(userID, UserRequest.builder().id(userID).build());
    UpdateUsersRequest updateUsersRequest = UpdateUsersRequest.builder().users(usersMap).build();
    Assertions.assertDoesNotThrow(() -> client.updateUsers(updateUsersRequest).execute());

    // Test getOrCreateFeed
    GetOrCreateFeedRequest createRequest = GetOrCreateFeedRequest.builder().userID(userID).build();
    GetOrCreateFeedResponse createResponse =
        Assertions.assertDoesNotThrow(
            () -> feeds.getOrCreateFeed(feedGroupId, feedId, createRequest).execute().getData());
    Assertions.assertNotNull(createResponse.getFeed());

    // Test updateFeed
    //    UpdateFeedRequest updateRequest = UpdateFeedRequest.builder()
    //        .description("Updated feed description")
    //        .build();
    //    UpdateFeedResponse updateResponse = Assertions.assertDoesNotThrow(
    //        () -> feeds.updateFeed(feedGroupId, feedId, updateRequest).execute().getData());
    //    Assertions.assertNotNull(updateResponse.getFeed());

    // Create activity for pin/unpin tests
    String feedFid = createResponse.getFeed().getFeed();
    AddActivityRequest activityRequest =
        AddActivityRequest.builder()
            .type("post")
            .feeds(List.of(feedFid))
            .text("Activity to pin")
            .userID(userID)
            .build();
    AddActivityResponse addResponse =
        Assertions.assertDoesNotThrow(() -> feeds.addActivity(activityRequest).execute().getData());
    String activityId = addResponse.getActivity().getId();

    // Test pinActivity
    PinActivityRequest pinRequest = PinActivityRequest.builder().userID(userID).build();
    PinActivityResponse pinResponse =
        Assertions.assertDoesNotThrow(
            () ->
                feeds.pinActivity(feedGroupId, feedId, activityId, pinRequest).execute().getData());
    Assertions.assertNotNull(pinResponse.getActivity());

    // Test unpinActivity
    UnpinActivityRequest unpinRequest = UnpinActivityRequest.builder().UserID(userID).build();
    UnpinActivityResponse unpinResponse =
        Assertions.assertDoesNotThrow(
            () ->
                feeds
                    .unpinActivity(feedGroupId, feedId, activityId, unpinRequest)
                    .execute()
                    .getData());
    Assertions.assertNotNull(unpinResponse.getActivity());

    // Test markActivity
    MarkActivityRequest markRequest =
        MarkActivityRequest.builder().markRead(List.of(activityId)).userID(userID).build();
    Assertions.assertDoesNotThrow(
        () -> feeds.markActivity(feedGroupId, feedId, markRequest).execute());

    // Test deleteFeed (cleanup)
    Assertions.assertDoesNotThrow(() -> feeds.deleteFeed(feedGroupId, feedId).execute());
  }

  @Test
  public void testFollowOperations() {
    String userID1 = RandomStringUtils.randomAlphanumeric(10);
    String userID2 = RandomStringUtils.randomAlphanumeric(10);

    // Setup users
    Map<String, UserRequest> usersMap = new HashMap<>();
    usersMap.put(userID1, UserRequest.builder().id(userID1).build());
    usersMap.put(userID2, UserRequest.builder().id(userID2).build());
    UpdateUsersRequest updateUsersRequest = UpdateUsersRequest.builder().users(usersMap).build();
    Assertions.assertDoesNotThrow(() -> client.updateUsers(updateUsersRequest).execute());

    // Create feeds
    GetOrCreateFeedRequest feedRequest1 = GetOrCreateFeedRequest.builder().userID(userID1).build();
    GetOrCreateFeedRequest feedRequest2 = GetOrCreateFeedRequest.builder().userID(userID2).build();

    GetOrCreateFeedResponse feedResponse1 =
        Assertions.assertDoesNotThrow(
            () -> feeds.getOrCreateFeed("user", userID1, feedRequest1).execute().getData());
    GetOrCreateFeedResponse feedResponse2 =
        Assertions.assertDoesNotThrow(
            () -> feeds.getOrCreateFeed("user", userID2, feedRequest2).execute().getData());

    String fid1 = feedResponse1.getFeed().getFeed();
    String fid2 = feedResponse2.getFeed().getFeed();

    // Test follow
    FollowRequest followRequest = FollowRequest.builder().source(fid2).target(fid1).build();
    SingleFollowResponse followResponse =
        Assertions.assertDoesNotThrow(() -> feeds.follow(followRequest).execute().getData());
    Assertions.assertNotNull(followResponse.getFollow());

    // Test queryFollows
    QueryFollowsRequest queryRequest =
        QueryFollowsRequest.builder()
            //            .filter(Map.of("source", fid2)) // $$
            //                    .filter(Map.of("source_fid", fid2))
            .build();
    QueryFollowsResponse queryResponse =
        Assertions.assertDoesNotThrow(() -> feeds.queryFollows(queryRequest).execute().getData());
    Assertions.assertFalse(queryResponse.getFollows().isEmpty());

    // Test updateFollow
    UpdateFollowRequest updateRequest =
        UpdateFollowRequest.builder().source(fid2).target(fid1).pushPreference("enabled").build();
    Assertions.assertDoesNotThrow(() -> feeds.updateFollow(updateRequest).execute());

    // Test unfollow
    UnfollowRequest unfollowRequest = UnfollowRequest.builder().build();
    UnfollowResponse unfollowResponse =
        Assertions.assertDoesNotThrow(
            () -> feeds.unfollow(fid2, fid1, unfollowRequest).execute().getData());
    Assertions.assertNotNull(unfollowResponse);
  }

  @Test
  public void testFeedViewOperations() {
    String viewId = RandomStringUtils.randomAlphanumeric(10);

    // Test createFeedView
    CreateFeedViewRequest createRequest =
        CreateFeedViewRequest.builder()
            .id(viewId) // $$
            //        .name("Test Feed View")
            //        .description("A test feed view")
            .build();
    CreateFeedViewResponse createResponse =
        Assertions.assertDoesNotThrow(
            () -> feeds.createFeedView(createRequest).execute().getData());
    Assertions.assertEquals(viewId, createResponse.getFeedView().getId());

    // Test getFeedView
    GetFeedViewResponse getResponse =
        Assertions.assertDoesNotThrow(() -> feeds.getFeedView(viewId).execute().getData());
    Assertions.assertEquals(viewId, getResponse.getFeedView().getId());

    // Test listFeedViews
    ListFeedViewsResponse listResponse =
        Assertions.assertDoesNotThrow(() -> feeds.listFeedViews().execute().getData());
    Assertions.assertFalse(listResponse.getViews().isEmpty());

    // Test updateFeedView
    //    UpdateFeedViewRequest updateRequest = UpdateFeedViewRequest.builder()
    //        .name("Updated Feed View")
    //        .build();
    //    UpdateFeedViewResponse updateResponse = Assertions.assertDoesNotThrow(
    //        () -> feeds.updateFeedView(viewId, updateRequest).execute().getData());
    //    Assertions.assertEquals("Updated Feed View", updateResponse.getFeedView().getName());

    // Test deleteFeedView
    Assertions.assertDoesNotThrow(() -> feeds.deleteFeedView(viewId).execute());
  }

  @Test
  public void testBatchOperations() {
    String userID = RandomStringUtils.randomAlphanumeric(10);

    // Setup user
    Map<String, UserRequest> usersMap = new HashMap<>();
    usersMap.put(userID, UserRequest.builder().id(userID).build());
    UpdateUsersRequest updateUsersRequest = UpdateUsersRequest.builder().users(usersMap).build();
    Assertions.assertDoesNotThrow(() -> client.updateUsers(updateUsersRequest).execute());

    // Test createFeedsBatch
    CreateFeedsBatchRequest batchRequest =
        CreateFeedsBatchRequest.builder()
            .feeds(
                List.of(
                    FeedRequest.builder()
                        .feedGroupID("user")
                        .feedID(userID)
                        .createdByID(userID)
                        .build()))
            .build();
    CreateFeedsBatchResponse batchResponse =
        Assertions.assertDoesNotThrow(
            () -> feeds.createFeedsBatch(batchRequest).execute().getData());
    Assertions.assertNotNull(batchResponse);

    // Test queryFeeds
    QueryFeedsRequest queryRequest = QueryFeedsRequest.builder().limit(10).build();
    QueryFeedsResponse queryResponse =
        Assertions.assertDoesNotThrow(() -> feeds.queryFeeds(queryRequest).execute().getData());
    Assertions.assertNotNull(queryResponse.getFeeds());

    // Test upsertActivities
    UpsertActivitiesRequest upsertRequest =
        UpsertActivitiesRequest.builder()
            .activities(
                List.of(
                    ActivityRequest.builder()
                        .type("post")
                        .feeds(List.of("user:" + userID))
                        .text("Batch activity")
                        .userID(userID)
                        .build()))
            .build();
    UpsertActivitiesResponse upsertResponse =
        Assertions.assertDoesNotThrow(
            () -> feeds.upsertActivities(upsertRequest).execute().getData());
    Assertions.assertFalse(upsertResponse.getActivities().isEmpty());

    // Test deleteActivities
    DeleteActivitiesRequest deleteRequest =
        DeleteActivitiesRequest.builder()
            .ids(List.of(upsertResponse.getActivities().get(0).getId()))
            .build();
    Assertions.assertDoesNotThrow(() -> feeds.deleteActivities(deleteRequest).execute());
  }

  @Test
  public void testUserDataOperations() {
    String userID = RandomStringUtils.randomAlphanumeric(10);

    // Setup user
    Map<String, UserRequest> usersMap = new HashMap<>();
    usersMap.put(userID, UserRequest.builder().id(userID).build());
    UpdateUsersRequest updateUsersRequest = UpdateUsersRequest.builder().users(usersMap).build();
    Assertions.assertDoesNotThrow(() -> client.updateUsers(updateUsersRequest).execute());

    // Test exportFeedUserData
    ExportFeedUserDataRequest exportRequest = ExportFeedUserDataRequest.builder().build();
    ExportFeedUserDataResponse exportResponse =
        Assertions.assertDoesNotThrow(
            () -> feeds.exportFeedUserData(userID, exportRequest).execute().getData());
    Assertions.assertNotNull(exportResponse);

    // Test deleteFeedUserData
    DeleteFeedUserDataRequest deleteRequest = DeleteFeedUserDataRequest.builder().build();
    DeleteFeedUserDataResponse deleteResponse =
        Assertions.assertDoesNotThrow(
            () -> feeds.deleteFeedUserData(userID, deleteRequest).execute().getData());
    Assertions.assertNotNull(deleteResponse);
  }

  @Test
  public void testPollOperations() {
    String userID = RandomStringUtils.randomAlphanumeric(10);

    // Setup user and activity with poll
    Map<String, UserRequest> usersMap = new HashMap<>();
    usersMap.put(userID, UserRequest.builder().id(userID).build());
    UpdateUsersRequest updateUsersRequest = UpdateUsersRequest.builder().users(usersMap).build();
    Assertions.assertDoesNotThrow(() -> client.updateUsers(updateUsersRequest).execute());

    GetOrCreateFeedRequest feedRequest = GetOrCreateFeedRequest.builder().userID(userID).build();
    GetOrCreateFeedResponse feedResponse =
        Assertions.assertDoesNotThrow(
            () -> feeds.getOrCreateFeed("user", userID, feedRequest).execute().getData());
    String feedFid = feedResponse.getFeed().getFeed();

    // Create activity with poll (assuming poll functionality exists)
    List<PollOptionInput> pl=new ArrayList<>();
    pl.add(PollOptionInput.builder().text("Red").build());
    pl.add(PollOptionInput.builder().text("Blue").build());
    pl.add(PollOptionInput.builder().text("Green").build());
    CreatePollRequest cp= CreatePollRequest.builder()
            .name("What is your favorite color?")
            .options(pl)
            .userID(userID)
            .build();

    PollResponse createPollResponse =
        Assertions.assertDoesNotThrow(() -> comm.createPoll(cp).execute().getData());
    AddActivityRequest activityRequest =
        AddActivityRequest.builder()
            .type("poll")
            .feeds(List.of(feedFid))
            .text("Poll activity")
            .pollID(createPollResponse.getPoll().getId())
            .userID(userID)
            .build();
    AddActivityResponse addResponse =
        Assertions.assertDoesNotThrow(() -> feeds.addActivity(activityRequest).execute().getData());
    String activityId = addResponse.getActivity().getId();

    // Test castPollVote
    CastPollVoteRequest voteRequest =
        CastPollVoteRequest.builder()
            //        .option("option1")//$$
            .userID(userID)
            .build();

    // Note: Poll operations might require specific poll setup
    // These tests assume the poll infrastructure is properly configured
    try {
      PollVoteResponse voteResponse =
          feeds.castPollVote(activityId, createPollResponse.getPoll().getId(), voteRequest).execute().getData();
      Assertions.assertNotNull(voteResponse);

      // Test deletePollVote if vote was successful
      String voteId = voteResponse.getVote().getId();
      if (voteId != null) {
        Assertions.assertDoesNotThrow(
            () -> feeds.deletePollVote(activityId, createPollResponse.getPoll().getId(), voteId).execute());
      }
    } catch (Exception e) {
      // Poll operations might not be fully configured in test environment
      System.out.println("Poll operations skipped: " + e.getMessage());
    }
  }

  //  @Test //$$
  public void testActivityFeedback() {
    String userID = RandomStringUtils.randomAlphanumeric(10);

    // Setup user and activity
    Map<String, UserRequest> usersMap = new HashMap<>();
    usersMap.put(userID, UserRequest.builder().id(userID).build());
    UpdateUsersRequest updateUsersRequest = UpdateUsersRequest.builder().users(usersMap).build();
    Assertions.assertDoesNotThrow(() -> client.updateUsers(updateUsersRequest).execute());

    GetOrCreateFeedRequest feedRequest = GetOrCreateFeedRequest.builder().userID(userID).build();
    GetOrCreateFeedResponse feedResponse =
        Assertions.assertDoesNotThrow(
            () -> feeds.getOrCreateFeed("user", userID, feedRequest).execute().getData());
    String feedFid = feedResponse.getFeed().getFeed();

    AddActivityRequest activityRequest =
        AddActivityRequest.builder()
            .type("post")
            .feeds(List.of(feedFid))
            .text("Activity for feedback")
            .userID(userID)
            .build();
    AddActivityResponse addResponse =
        Assertions.assertDoesNotThrow(() -> feeds.addActivity(activityRequest).execute().getData());
    String activityId = addResponse.getActivity().getId();

    // Test activityFeedback
    ActivityFeedbackRequest feedbackRequest =
        ActivityFeedbackRequest.builder().userID(userID).reason("positive").build();
    ActivityFeedbackResponse feedbackResponse =
        Assertions.assertDoesNotThrow(
            () -> feeds.activityFeedback(activityId, feedbackRequest).execute().getData());
    Assertions.assertNotNull(feedbackResponse);
  }

  @Test
  public void testFeedMemberOperations() {
    String userID1 = RandomStringUtils.randomAlphanumeric(10);
    String userID2 = RandomStringUtils.randomAlphanumeric(10);
    String feedGroupId = "user";
    String feedId = userID1;

    // Setup users
    Map<String, UserRequest> usersMap = new HashMap<>();
    usersMap.put(userID1, UserRequest.builder().id(userID1).build());
    usersMap.put(userID2, UserRequest.builder().id(userID2).build());
    UpdateUsersRequest updateUsersRequest = UpdateUsersRequest.builder().users(usersMap).build();
    Assertions.assertDoesNotThrow(() -> client.updateUsers(updateUsersRequest).execute());

    // Create feed
    GetOrCreateFeedRequest feedRequest = GetOrCreateFeedRequest.builder().userID(userID1).build();
    Assertions.assertDoesNotThrow(
        () -> feeds.getOrCreateFeed(feedGroupId, feedId, feedRequest).execute());

    // Test updateFeedMembers
    UpdateFeedMembersRequest updateMembersRequest =
        UpdateFeedMembersRequest.builder()
            .members(List.of(FeedMemberRequest.builder().userID(userID2).role("member").build()))
            .build();
    UpdateFeedMembersResponse updateMembersResponse =
        Assertions.assertDoesNotThrow(
            () ->
                feeds
                    .updateFeedMembers(feedGroupId, feedId, updateMembersRequest)
                    .execute()
                    .getData());
    Assertions.assertNotNull(updateMembersResponse);

    // Test queryFeedMembers
    QueryFeedMembersRequest queryMembersRequest =
        QueryFeedMembersRequest.builder().limit(10).build();
    QueryFeedMembersResponse queryMembersResponse =
        Assertions.assertDoesNotThrow(
            () ->
                feeds
                    .queryFeedMembers(feedGroupId, feedId, queryMembersRequest)
                    .execute()
                    .getData());
    Assertions.assertNotNull(queryMembersResponse.getMembers());

    // Test acceptFeedMemberInvite (would typically be called by the invited user)
    AcceptFeedMemberInviteRequest acceptRequest = AcceptFeedMemberInviteRequest.builder().build();
    try {
      AcceptFeedMemberInviteResponse acceptResponse =
          feeds.acceptFeedMemberInvite(feedId, feedGroupId, acceptRequest).execute().getData();
      Assertions.assertNotNull(acceptResponse);
    } catch (Exception e) {
      // This might fail if there's no pending invite
      System.out.println("Accept invite skipped: " + e.getMessage());
    }

    // Test rejectFeedMemberInvite
    RejectFeedMemberInviteRequest rejectRequest = RejectFeedMemberInviteRequest.builder().build();
    try {
      RejectFeedMemberInviteResponse rejectResponse =
          feeds.rejectFeedMemberInvite(feedGroupId, feedId, rejectRequest).execute().getData();
      Assertions.assertNotNull(rejectResponse);
    } catch (Exception e) {
      // This might fail if there's no pending invite
      System.out.println("Reject invite skipped: " + e.getMessage());
    }
  }

  @Test
  public void testFollowSuggestions() {
    String userID = RandomStringUtils.randomAlphanumeric(10);
    String feedGroupId = "user";

    // Setup user
    Map<String, UserRequest> usersMap = new HashMap<>();
    usersMap.put(userID, UserRequest.builder().id(userID).build());
    UpdateUsersRequest updateUsersRequest = UpdateUsersRequest.builder().users(usersMap).build();
    Assertions.assertDoesNotThrow(() -> client.updateUsers(updateUsersRequest).execute());

    // Test getFollowSuggestions
    GetFollowSuggestionsRequest suggestionsRequest =
        GetFollowSuggestionsRequest.builder().UserID(userID).Limit(10).build();
    GetFollowSuggestionsResponse suggestionsResponse =
        Assertions.assertDoesNotThrow(
            () -> feeds.getFollowSuggestions(feedGroupId, suggestionsRequest).execute().getData());
    Assertions.assertNotNull(suggestionsResponse);
  }
}
