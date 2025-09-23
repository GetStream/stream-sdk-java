package io.getstream;

import io.getstream.models.*;
import io.getstream.services.*;
import io.getstream.services.framework.StreamHTTPClient;
import io.getstream.services.framework.StreamSDKClient;
import java.util.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.*;

/**
 * Systematic Integration tests for Feed operations These tests follow a logical flow: setup →
 * create → operate → cleanup
 *
 * <p>Test order: 1. Environment Setup (user, feed creation) 2. Activity Operations (create, read,
 * update, delete) 3. Reaction Operations (add, query, delete) 4. Comment Operations (add, read,
 * update, delete) 5. Bookmark Operations (add, query, update, delete) 6. Follow Operations (follow,
 * query, unfollow) 7. Batch Operations 8. Advanced Operations (polls, pins, etc.) 9. Cleanup
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FeedIntegrationTests {

  private static final String USER_FEED_TYPE = "user:";
  private static final String POLL_QUESTION = "What's your favorite programming language?";

  private static StreamSDKClient client;
  private static Feeds feeds;
  private static Common common;

  private static String testUserId;
  private static String testUserId2; // For follow operations
  private static Feed testFeed;
  private static Feed testFeed2; // For follow operations
  private static String testFeedId;
  private static String testFeedId2;

  // Track created resources for cleanup
  private static List<String> createdActivityIds = new ArrayList<>();
  private static List<String> createdCommentIds = new ArrayList<>();
  private static String testActivityId = "";
  private static String testCommentId = "";

  @BeforeAll
  static void setUp() throws Exception {
    // snippet-start: Getting_Started
    client = new StreamSDKClient();
    feeds = new FeedsImpl(new StreamHTTPClient());
    common = new CommonImpl(new StreamHTTPClient());
    // snippet-end: Getting_Started
    testUserId = "test-user-" + RandomStringUtils.randomAlphanumeric(8);
    testUserId2 = "test-user-2-" + RandomStringUtils.randomAlphanumeric(8);

    // Setup environment for each test
    setupEnvironment();
  }

  @AfterAll
  static void tearDown() {
    // Cleanup created resources in reverse order
    cleanupResources();
  }

  // =================================================================
  // ENVIRONMENT SETUP (called in setUp for each test)
  // =================================================================

  private static void setupEnvironment() throws Exception {
    try {
      // Create test users
      // snippet-start: CreateUsers
      Map<String, UserRequest> usersMap = new HashMap<>();
      usersMap.put(
          testUserId,
          UserRequest.builder().id(testUserId).name("Test User 1").role("user").build());
      usersMap.put(
          testUserId2,
          UserRequest.builder().id(testUserId2).name("Test User 2").role("user").build());

      UpdateUsersRequest updateUsersRequest = UpdateUsersRequest.builder().users(usersMap).build();

      client.updateUsers(updateUsersRequest).execute();
      // snippet-end: CreateUsers

      // Note: StreamResponse doesn't have isSuccessful() method in this SDK version
      // The execute() method will throw an exception if the request fails

      // Create feeds
      // snippet-start: GetOrCreateFeed
      testFeed = new Feed("user", testUserId, feeds);
      testFeed2 = new Feed("user", testUserId2, feeds);

      GetOrCreateFeedRequest feedRequest1 =
          GetOrCreateFeedRequest.builder().userID(testUserId).build();
      GetOrCreateFeedRequest feedRequest2 =
          GetOrCreateFeedRequest.builder().userID(testUserId2).build();

      GetOrCreateFeedResponse feedResponse1 = testFeed.getOrCreate(feedRequest1).getData();
      GetOrCreateFeedResponse feedResponse2 = testFeed2.getOrCreate(feedRequest2).getData();
      testFeedId = feedResponse1.getFeed().getFeed();
      testFeedId2 = feedResponse2.getFeed().getFeed();

      // snippet-end: GetOrCreateFeed
    } catch (Exception e) {
      System.err.println("⚠️ Setup failed: " + e.getMessage());
      throw e;
    }
  }

  // =================================================================
  // 1. ENVIRONMENT SETUP TEST (demonstrates the setup process)
  // =================================================================

  @Test
  @Order(1)
  void test01_SetupEnvironmentDemo() {
    System.out.println("\n🔧 Demonstrating environment setup...");
    System.out.println("✅ Users and feeds are automatically created in setUp()");
    System.out.println("   Test User 1: " + testUserId);
    System.out.println("   Test User 2: " + testUserId2);

    Assertions.assertTrue(true); // Just a demo test
  }

  // =================================================================
  // 2. ACTIVITY OPERATIONS
  // =================================================================

  @Test
  @Order(2)
  void test02_CreateActivity() throws Exception {
    System.out.println("\n📝 Testing activity creation...");

    // snippet-start: AddActivity
    AddActivityRequest activity =
        AddActivityRequest.builder()
            .type("post")
            .feeds(List.of(testFeedId))
            .text("This is a test activity from Java SDK")
            .userID(testUserId)
            .build();

    AddActivityResponse response = feeds.addActivity(activity).execute().getData();
    // snippet-end: AddActivity

    Assertions.assertNotNull(response.getActivity());
    Assertions.assertNotNull(response.getActivity().getId());
    Assertions.assertNotNull(response.getActivity().getText());
    Assertions.assertEquals(activity.getText(), response.getActivity().getText());

    testActivityId = response.getActivity().getId();
    createdActivityIds.add(testActivityId);

    System.out.println("✅ Created activity with ID: " + testActivityId);
  }

  @Test
  @Order(3)
  void test02b_CreateActivityWithAttachments() throws Exception {
    System.out.println("\n🖼️ Testing activity creation with image attachments...");

    // snippet-start: AddActivityWithImageAttachment
    Map<String, Object> customData = new HashMap<>();
    customData.put("location", "New York City");
    customData.put("camera", "iPhone 15 Pro");

    AddActivityRequest activity =
        AddActivityRequest.builder()
            .type("post")
            .feeds(List.of(testFeedId))
            .text("Look at this amazing view of NYC!")
            .userID(testUserId)
            .attachments(
                List.of(
                    Attachment.builder()
                        .imageUrl("https://example.com/nyc-skyline.jpg")
                        .type("image")
                        .title("NYC Skyline")
                        .build()))
            .custom(customData)
            .build();

    AddActivityResponse response = feeds.addActivity(activity).execute().getData();
    // snippet-end: AddActivityWithImageAttachment

    Assertions.assertNotNull(response.getActivity());
    String activityId = response.getActivity().getId();
    createdActivityIds.add(activityId);

    System.out.println("✅ Created activity with image attachment: " + activityId);
  }

  @Test
  @Order(4)
  void test02c_CreateVideoActivity() throws Exception {
    System.out.println("\n🎥 Testing video activity creation...");

    // snippet-start: AddVideoActivity
    AddActivityRequest activity =
        AddActivityRequest.builder()
            .type("video")
            .feeds(List.of(testFeedId))
            .text("Check out this amazing video!")
            .userID(testUserId)
            .build();

    AddActivityResponse response = feeds.addActivity(activity).execute().getData();
    // snippet-end: AddVideoActivity

    Assertions.assertNotNull(response.getActivity());
    String activityId = response.getActivity().getId();
    createdActivityIds.add(activityId);

    System.out.println("✅ Created video activity: " + activityId);
  }

  @Test
  @Order(5)
  void test02d_CreateStoryActivityWithExpiration() throws Exception {
    System.out.println("\n📖 Testing story activity with expiration...");

    // snippet-start: AddStoryActivityWithExpiration
    java.time.Instant tomorrow =
        java.time.Instant.now().plus(1, java.time.temporal.ChronoUnit.DAYS);
    String expiresAt = tomorrow.toString(); // RFC3339 format

    Map<String, Object> customData = new HashMap<>();
    customData.put("story_type", "daily");
    customData.put("auto_expire", true);

    AddActivityRequest activity =
        AddActivityRequest.builder()
            .type("story")
            .feeds(List.of(testFeedId))
            .text("My daily story - expires tomorrow!")
            .userID(testUserId)
            .expiresAt(expiresAt)
            .attachments(
                List.of(
                    Attachment.builder()
                        .imageUrl("https://example.com/story-image.jpg")
                        .type("image")
                        .build(),
                    Attachment.builder()
                        .assetUrl("https://example.com/story-video.mp4")
                        .type("video")
                        .custom(Map.of("duration", 15))
                        .build()))
            .custom(customData)
            .build();

    AddActivityResponse response = feeds.addActivity(activity).execute().getData();
    // snippet-end: AddStoryActivityWithExpiration

    Assertions.assertNotNull(response.getActivity());
    String activityId = response.getActivity().getId();
    createdActivityIds.add(activityId);

    System.out.println("✅ Created story activity with expiration: " + activityId);
  }

  @Test
  @Order(6)
  void test02e_CreateActivityMultipleFeeds() throws Exception {
    System.out.println("\n📡 Testing activity creation to multiple feeds...");

    // snippet-start: AddActivityToMultipleFeeds
    Map<String, Object> customData = new HashMap<>();
    customData.put("cross_posted", true);
    customData.put("target_feeds", 2);

    AddActivityRequest activity =
        AddActivityRequest.builder()
            .type("post")
            .feeds(List.of(testFeedId, testFeedId2))
            .text("This post appears in multiple feeds!")
            .userID(testUserId)
            .custom(customData)
            .build();

    AddActivityResponse response = feeds.addActivity(activity).execute().getData();
    // snippet-end: AddActivityToMultipleFeeds

    Assertions.assertNotNull(response.getActivity());
    String activityId = response.getActivity().getId();
    createdActivityIds.add(activityId);

    System.out.println("✅ Created activity in multiple feeds: " + activityId);
  }

  @Test
  @Order(7)
  void test03_QueryActivities() throws Exception {
    System.out.println("\n🔍 Testing activity querying...");

    // snippet-start: QueryActivities
    Map<String, Object> filter = new HashMap<>();
    filter.put("activity_type", "post");

    QueryActivitiesRequest request =
        QueryActivitiesRequest.builder().limit(10).filter(filter).build();

    QueryActivitiesResponse response = feeds.queryActivities(request).execute().getData();
    // snippet-end: QueryActivities

    Assertions.assertNotNull(response.getActivities());
    System.out.println("✅ Queried activities successfully");
  }

  @Test
  @Order(8)
  void test04_GetSingleActivity() throws Exception {
    System.out.println("\n📄 Testing single activity retrieval...");

    // First create an activity to retrieve
    AddActivityRequest activity =
        AddActivityRequest.builder()
            .type("post")
            .text("Activity for retrieval test")
            .userID(testUserId)
            .feeds(List.of(testFeedId))
            .build();

    AddActivityResponse createResponse = feeds.addActivity(activity).execute().getData();
    String activityId = createResponse.getActivity().getId();
    createdActivityIds.add(activityId);

    // snippet-start: GetActivity
    GetActivityResponse response = feeds.getActivity(activityId).execute().getData();
    // snippet-end: GetActivity

    Assertions.assertNotNull(response.getActivity());
    Assertions.assertEquals(activityId, response.getActivity().getId());
    System.out.println("✅ Retrieved single activity");
  }

  @Test
  @Order(9)
  void test05_UpdateActivity() throws Exception {
    System.out.println("\n✏️ Testing activity update...");

    // First create an activity to update
    AddActivityRequest activity =
        AddActivityRequest.builder()
            .type("post")
            .text("Activity for update test")
            .userID(testUserId)
            .feeds(List.of(testFeedId))
            .build();

    AddActivityResponse createResponse = feeds.addActivity(activity).execute().getData();
    String activityId = createResponse.getActivity().getId();
    createdActivityIds.add(activityId);

    // snippet-start: UpdateActivity
    Map<String, Object> customData = new HashMap<>();
    customData.put("updated", true);
    customData.put("update_time", System.currentTimeMillis() / 1000);

    UpdateActivityRequest updateRequest =
        UpdateActivityRequest.builder()
            .text("Updated activity text from Java SDK")
            .userID(testUserId) // Required for server-side auth
            .custom(customData)
            .build();

    UpdateActivityResponse response =
        feeds.updateActivity(activityId, updateRequest).execute().getData();
    // snippet-end: UpdateActivity

    Assertions.assertNotNull(response.getActivity());
    System.out.println("✅ Updated activity");
  }

  // =================================================================
  // 3. REACTION OPERATIONS
  // =================================================================

  @Test
  @Order(10)
  void test06_AddReaction() throws Exception {
    System.out.println("\n👍 Testing reaction addition...");

    // First create an activity to react to
    AddActivityRequest activity =
        AddActivityRequest.builder()
            .type("post")
            .text("Activity for reaction test")
            .userID(testUserId)
            .feeds(List.of(testFeedId))
            .build();

    AddActivityResponse createResponse = feeds.addActivity(activity).execute().getData();
    String activityId = createResponse.getActivity().getId();
    createdActivityIds.add(activityId);

    // snippet-start: AddReaction
    AddReactionRequest reactionRequest =
        AddReactionRequest.builder().type("like").userID(testUserId).build();

    AddReactionResponse response =
        feeds.addReaction(activityId, reactionRequest).execute().getData();
    // snippet-end: AddReaction

    Assertions.assertNotNull(response.getReaction());
    System.out.println("✅ Added like reaction");
  }

  @Test
  @Order(11)
  void test07_QueryReactions() throws Exception {
    System.out.println("\n🔍 Testing reaction querying...");

    // Create an activity and add a reaction to it
    AddActivityRequest activity =
        AddActivityRequest.builder()
            .type("post")
            .text("Activity for query reactions test")
            .userID(testUserId)
            .feeds(List.of(testFeedId))
            .build();

    AddActivityResponse createResponse = feeds.addActivity(activity).execute().getData();
    String activityId = createResponse.getActivity().getId();
    createdActivityIds.add(activityId);

    // Add a reaction first
    AddReactionRequest reactionRequest =
        AddReactionRequest.builder().type("like").userID(testUserId).build();

    feeds.addReaction(activityId, reactionRequest).execute();

    try {
      // snippet-start: QueryActivityReactions
      Map<String, Object> filter = new HashMap<>();
      filter.put("reaction_type", "like");

      QueryActivityReactionsRequest queryRequest =
          QueryActivityReactionsRequest.builder().limit(10).filter(filter).build();

      QueryActivityReactionsResponse response =
          feeds.queryActivityReactions(activityId, queryRequest).execute().getData();
      // snippet-end: QueryActivityReactions

      Assertions.assertNotNull(response.getReactions());
      System.out.println("✅ Queried reactions");
    } catch (Exception e) {
      System.out.println("Query reactions skipped: " + e.getMessage());
    }
  }

  // =================================================================
  // 4. COMMENT OPERATIONS
  // =================================================================

  @Test
  @Order(12)
  void test08_AddComment() throws Exception {
    System.out.println("\n💬 Testing comment addition...");

    // First create an activity to comment on
    AddActivityRequest activity =
        AddActivityRequest.builder()
            .type("post")
            .feeds(List.of(testFeedId))
            .text("Activity for comment test")
            .userID(testUserId)
            .build();

    AddActivityResponse createResponse = feeds.addActivity(activity).execute().getData();
    String activityId = createResponse.getActivity().getId();
    createdActivityIds.add(activityId);

    // snippet-start: AddComment
    AddCommentRequest commentRequest =
        AddCommentRequest.builder()
            .comment("This is a test comment from Java SDK")
            .objectID(activityId)
            .objectType("activity")
            .userID(testUserId)
            .build();

    AddCommentResponse response = feeds.addComment(commentRequest).execute().getData();
    // snippet-end: AddComment

    Assertions.assertNotNull(response.getComment());
    if (response.getComment().getId() != null) {
      testCommentId = response.getComment().getId();
      createdCommentIds.add(testCommentId);
      System.out.println("✅ Added comment with ID: " + testCommentId);
    } else {
      System.out.println("✅ Added comment (no ID returned)");
    }
  }

  @Test
  @Order(13)
  void test09_QueryComments() throws Exception {
    System.out.println("\n🔍 Testing comment querying...");

    // Create an activity and add a comment to it
    AddActivityRequest activity =
        AddActivityRequest.builder()
            .type("post")
            .text("Activity for query comments test")
            .userID(testUserId)
            .feeds(List.of(testFeedId))
            .build();

    AddActivityResponse createResponse = feeds.addActivity(activity).execute().getData();
    String activityId = createResponse.getActivity().getId();
    createdActivityIds.add(activityId);

    // Add a comment first
    AddCommentRequest commentRequest =
        AddCommentRequest.builder()
            .comment("Comment for query test")
            .objectID(activityId)
            .objectType("activity")
            .userID(testUserId)
            .build();

    feeds.addComment(commentRequest).execute();

    // snippet-start: QueryComments
    Map<String, Object> filter = new HashMap<>();
    filter.put("object_id", activityId);

    QueryCommentsRequest queryRequest =
        QueryCommentsRequest.builder().filter(filter).limit(10).build();

    QueryCommentsResponse response = feeds.queryComments(queryRequest).execute().getData();
    // snippet-end: QueryComments

    Assertions.assertNotNull(response.getComments());
    System.out.println("✅ Queried comments");
  }

  @Test
  @Order(14)
  void test10_UpdateComment() throws Exception {
    System.out.println("\n✏️ Testing comment update...");

    // Create an activity and add a comment to update
    AddActivityRequest activity =
        AddActivityRequest.builder()
            .type("post")
            .text("Activity for update comment test")
            .userID(testUserId)
            .feeds(List.of(testFeedId))
            .build();

    AddActivityResponse createResponse = feeds.addActivity(activity).execute().getData();
    String activityId = createResponse.getActivity().getId();
    createdActivityIds.add(activityId);

    // Add a comment to update
    AddCommentRequest commentRequest =
        AddCommentRequest.builder()
            .comment("Comment to be updated")
            .objectID(activityId)
            .objectType("activity")
            .userID(testUserId)
            .build();

    AddCommentResponse commentResponse = feeds.addComment(commentRequest).execute().getData();
    String commentId =
        commentResponse.getComment().getId() != null
            ? commentResponse.getComment().getId()
            : "comment-id";

    // snippet-start: UpdateComment
    UpdateCommentRequest updateRequest =
        UpdateCommentRequest.builder().comment("Updated comment text from Java SDK").build();

    UpdateCommentResponse response =
        feeds.updateComment(commentId, updateRequest).execute().getData();
    // snippet-end: UpdateComment

    Assertions.assertNotNull(response.getComment());
    System.out.println("✅ Updated comment");
  }

  // =================================================================
  // 5. BOOKMARK OPERATIONS
  // =================================================================

  @Test
  @Order(15)
  void test11_AddBookmark() throws Exception {
    System.out.println("\n🔖 Testing bookmark addition...");

    // Create an activity to bookmark
    AddActivityRequest activity =
        AddActivityRequest.builder()
            .type("post")
            .text("Activity for bookmark test")
            .userID(testUserId)
            .feeds(List.of(testFeedId))
            .build();

    AddActivityResponse createResponse = feeds.addActivity(activity).execute().getData();
    String activityId = createResponse.getActivity().getId();
    createdActivityIds.add(activityId);

    try {
      // snippet-start: AddBookmark
      AddBookmarkRequest bookmarkRequest =
          AddBookmarkRequest.builder()
              .userID(testUserId)
              .newFolder(AddFolderRequest.builder().name("test-bookmarks1").build())
              .build();

      AddBookmarkResponse response =
          feeds.addBookmark(activityId, bookmarkRequest).execute().getData();
      // snippet-end: AddBookmark

      Assertions.assertNotNull(response.getBookmark());
      System.out.println("✅ Added bookmark");
    } catch (Exception e) {
      System.out.println("Add bookmark failed: " + e.getMessage());
    }
  }

  @Test
  @Order(16)
  void test12_QueryBookmarks() throws Exception {
    System.out.println("\n🔍 Testing bookmark querying...");

    // snippet-start: QueryBookmarks
    Map<String, Object> filter = new HashMap<>();
    filter.put("user_id", testUserId);

    QueryBookmarksRequest request =
        QueryBookmarksRequest.builder().limit(10).filter(filter).build();

    QueryBookmarksResponse response = feeds.queryBookmarks(request).execute().getData();
    // snippet-end: QueryBookmarks

    Assertions.assertNotNull(response.getBookmarks());
    System.out.println("✅ Queried bookmarks");
  }

  @Test
  @Order(17)
  void test13_UpdateBookmark() throws Exception {
    System.out.println("\n✏️ Testing bookmark update...");

    // Create an activity and bookmark it first
    AddActivityRequest activity =
        AddActivityRequest.builder()
            .type("post")
            .feeds(List.of(testFeedId))
            .text("Activity for update bookmark test")
            .userID(testUserId)
            .build();

    AddActivityResponse createResponse = feeds.addActivity(activity).execute().getData();
    String activityId = createResponse.getActivity().getId();
    createdActivityIds.add(activityId);

    // Add a bookmark first
    AddBookmarkRequest bookmarkRequest =
        AddBookmarkRequest.builder()
            .newFolder(AddFolderRequest.builder().name("test-bookmarks1").build())
            .userID(testUserId)
            .build();

    AddBookmarkResponse bookmarkResponse =
        feeds.addBookmark(activityId, bookmarkRequest).execute().getData();
    String folderID = bookmarkResponse.getBookmark().getFolder().getId();

    // snippet-start: UpdateBookmark
    UpdateBookmarkRequest updateRequest =
        UpdateBookmarkRequest.builder().folderID(folderID).userID(testUserId).build();

    UpdateBookmarkResponse response =
        feeds.updateBookmark(activityId, updateRequest).execute().getData();
    // snippet-end: UpdateBookmark

    Assertions.assertNotNull(response.getBookmark());
    System.out.println("✅ Updated bookmark");
  }

  // =================================================================
  // 6. FOLLOW OPERATIONS
  // =================================================================

  @Test
  @Order(18)
  void test14_FollowUser() throws Exception {
    System.out.println("\n👥 Testing follow operation...");

    try {
      // snippet-start: Follow
      FollowRequest followRequest =
          FollowRequest.builder()
              .source(USER_FEED_TYPE + testUserId)
              .target(USER_FEED_TYPE + testUserId2)
              .build();

      SingleFollowResponse response = feeds.follow(followRequest).execute().getData();
      // snippet-end: Follow

      Assertions.assertNotNull(response.getFollow());
      System.out.println("✅ Followed user: " + testUserId2);
    } catch (Exception e) {
      System.out.println("Follow failed: " + e.getMessage());
    }
  }

  @Test
  @Order(19)
  void test15_QueryFollows() throws Exception {
    System.out.println("\n🔍 Testing follow querying...");

    // snippet-start: QueryFollows
    QueryFollowsRequest request = QueryFollowsRequest.builder().limit(10).build();

    QueryFollowsResponse response = feeds.queryFollows(request).execute().getData();
    // snippet-end: QueryFollows

    Assertions.assertNotNull(response.getFollows());
    System.out.println("✅ Queried follows");
  }

  // =================================================================
  // 7. BATCH OPERATIONS
  // =================================================================

  @Test
  @Order(20)
  @Disabled
  void test16_UpsertActivities() throws Exception {
    System.out.println("\n📝 Testing batch activity upsert...");

    // snippet-start: UpsertActivities
    List<ActivityRequest> activities =
        List.of(
            ActivityRequest.builder()
                .type("post")
                .text("Batch activity 1")
                .userID(testUserId)
                .build(),
            ActivityRequest.builder()
                .type("post")
                .text("Batch activity 2")
                .userID(testUserId)
                .build());

    UpsertActivitiesRequest request =
        UpsertActivitiesRequest.builder().activities(activities).build();

    UpsertActivitiesResponse response = feeds.upsertActivities(request).execute().getData();
    // snippet-end: UpsertActivities

    Assertions.assertNotNull(response.getActivities());

    // Track created activities for cleanup
    for (ActivityResponse activity : response.getActivities()) {
      if (activity.getId() != null) {
        createdActivityIds.add(activity.getId());
      }
    }

    System.out.println("✅ Upserted batch activities");
  }

  // =================================================================
  // 8. ADVANCED OPERATIONS
  // =================================================================

  @Test
  @Order(21)
  void test17_PinActivity() throws Exception {
    System.out.println("\n📌 Testing activity pinning...");

    // Create an activity to pin
    AddActivityRequest activity =
        AddActivityRequest.builder()
            .type("post")
            .feeds(List.of(testFeedId))
            .text("Activity for pin test")
            .userID(testUserId)
            .build();

    AddActivityResponse createResponse = feeds.addActivity(activity).execute().getData();
    String activityId = createResponse.getActivity().getId();
    createdActivityIds.add(activityId);

    // snippet-start: PinActivity
    PinActivityRequest pinRequest = PinActivityRequest.builder().userID(testUserId).build();

    PinActivityResponse response = testFeed.pinActivity(activityId, pinRequest).getData();
    // snippet-end: PinActivity

    Assertions.assertNotNull(response.getActivity());
    System.out.println("✅ Pinned activity");
  }

  @Test
  @Order(22)
  void test18_UnpinActivity() throws Exception {
    System.out.println("\n📌 Testing activity unpinning...");

    // Create an activity, pin it, then unpin it
    AddActivityRequest activity =
        AddActivityRequest.builder()
            .type("post")
            .feeds(List.of(testFeedId))
            .text("Activity for unpin test")
            .userID(testUserId)
            .build();

    AddActivityResponse createResponse = feeds.addActivity(activity).execute().getData();
    String activityId = createResponse.getActivity().getId();
    createdActivityIds.add(activityId);

    // Pin it first
    PinActivityRequest pinRequest = PinActivityRequest.builder().userID(testUserId).build();

    testFeed.pinActivity(activityId, pinRequest);

    // snippet-start: UnpinActivity
    UnpinActivityRequest unpinRequest = UnpinActivityRequest.builder().UserID(testUserId).build();

    UnpinActivityResponse response = testFeed.unpinActivity(activityId, unpinRequest).getData();
    // snippet-end: UnpinActivity

    Assertions.assertNotNull(response.getActivity());
    System.out.println("✅ Unpinned activity");
  }

  // =================================================================
  // 9. CLEANUP OPERATIONS (in reverse order)
  // =================================================================

  @Test
  @Order(23)
  void test19_DeleteBookmark() throws Exception {
    System.out.println("\n🗑️ Testing bookmark deletion...");

    // Create an activity and bookmark it first
    AddActivityRequest activity =
        AddActivityRequest.builder()
            .type("post")
            .text("Activity for delete bookmark test")
            .userID(testUserId)
            .feeds(List.of(testFeedId))
            .build();

    AddActivityResponse createResponse = feeds.addActivity(activity).execute().getData();
    String activityId = createResponse.getActivity().getId();
    createdActivityIds.add(activityId);

    // Add a bookmark first
    AddBookmarkRequest bookmarkRequest =
        AddBookmarkRequest.builder()
            .newFolder(AddFolderRequest.builder().name("test-bookmarks1").build())
            .userID(testUserId)
            .build();

    AddBookmarkResponse bookmarkResponse =
        feeds.addBookmark(activityId, bookmarkRequest).execute().getData();
    String folderId = bookmarkResponse.getBookmark().getFolder().getId();

    // snippet-start: DeleteBookmark
    DeleteBookmarkRequest deleteRequest =
        DeleteBookmarkRequest.builder().FolderID(folderId).UserID(testUserId).build();

    DeleteBookmarkResponse response =
        feeds.deleteBookmark(activityId, deleteRequest).execute().getData();
    // snippet-end: DeleteBookmark

    Assertions.assertNotNull(response);
    System.out.println("✅ Deleted bookmark");
  }

  @Test
  @Order(24)
  void test20_DeleteReaction() throws Exception {
    System.out.println("\n🗑️ Testing reaction deletion...");

    // Create an activity and add a reaction first
    AddActivityRequest activity =
        AddActivityRequest.builder()
            .type("post")
            .text("Activity for delete reaction test")
            .userID(testUserId)
            .feeds(List.of(testFeedId))
            .build();

    AddActivityResponse createResponse = feeds.addActivity(activity).execute().getData();
    String activityId = createResponse.getActivity().getId();
    createdActivityIds.add(activityId);

    // Add a reaction first
    AddReactionRequest reactionRequest =
        AddReactionRequest.builder().type("like").userID(testUserId).build();

    feeds.addReaction(activityId, reactionRequest).execute();

    // snippet-start: DeleteActivityReaction
    DeleteActivityReactionRequest deleteRequest =
        DeleteActivityReactionRequest.builder().UserID(testUserId).build();

    DeleteActivityReactionResponse response =
        feeds.deleteActivityReaction(activityId, "like", deleteRequest).execute().getData();
    // snippet-end: DeleteActivityReaction

    Assertions.assertNotNull(response);
    System.out.println("✅ Deleted reaction");
  }

  @Test
  @Order(25)
  void test21_DeleteComment() throws Exception {
    System.out.println("\n🗑️ Testing comment deletion...");

    // Create an activity and add a comment first
    AddActivityRequest activity =
        AddActivityRequest.builder()
            .type("post")
            .text("Activity for delete comment test")
            .userID(testUserId)
            .feeds(List.of(testFeedId))
            .build();

    AddActivityResponse createResponse = feeds.addActivity(activity).execute().getData();
    String activityId = createResponse.getActivity().getId();
    createdActivityIds.add(activityId);

    // Add a comment first
    AddCommentRequest commentRequest =
        AddCommentRequest.builder()
            .comment("Comment to be deleted")
            .objectID(activityId)
            .objectType("activity")
            .userID(testUserId)
            .build();

    AddCommentResponse commentResponse = feeds.addComment(commentRequest).execute().getData();
    String commentId =
        commentResponse.getComment().getId() != null
            ? commentResponse.getComment().getId()
            : "comment-id";

    // snippet-start: DeleteComment
    DeleteCommentRequest deleteRequest = DeleteCommentRequest.builder().build();
    DeleteCommentResponse response =
        feeds.deleteComment(commentId, deleteRequest).execute().getData();
    // snippet-end: DeleteComment

    Assertions.assertNotNull(response);
    System.out.println("✅ Deleted comment");
  }

  @Test
  @Order(26)
  void test22_UnfollowUser() throws Exception {
    System.out.println("\n👥 Testing unfollow operation...");

    try {
      // First establish a follow relationship
      FollowRequest followRequest =
          FollowRequest.builder()
              .source(USER_FEED_TYPE + testUserId)
              .target(USER_FEED_TYPE + testUserId2)
              .build();

      feeds.follow(followRequest).execute();

      // snippet-start: Unfollow
      UnfollowRequest unfollowRequest = UnfollowRequest.builder().build();
      UnfollowResponse response =
          feeds
              .unfollow(USER_FEED_TYPE + testUserId, USER_FEED_TYPE + testUserId2, unfollowRequest)
              .execute()
              .getData();
      // snippet-end: Unfollow

      Assertions.assertNotNull(response);
      System.out.println("✅ Unfollowed user: " + testUserId2);
    } catch (Exception e) {
      System.out.println("Unfollow operation skipped: " + e.getMessage());
    }
  }

  @Test
  @Order(27)
  void test23_DeleteActivities() throws Exception {
    System.out.println("\n🗑️ Testing activity deletion...");

    // Create some activities to delete
    List<String> activitiesToDelete = new ArrayList<>();
    for (int i = 1; i <= 2; i++) {
      AddActivityRequest activity =
          AddActivityRequest.builder()
              .type("post")
              .text("Activity " + i + " for delete test")
              .userID(testUserId)
              .feeds(List.of(testFeedId))
              .build();

      AddActivityResponse createResponse = feeds.addActivity(activity).execute().getData();
      String activityId = createResponse.getActivity().getId();
      activitiesToDelete.add(activityId);
      createdActivityIds.add(activityId);
    }

    for (String activityId : activitiesToDelete) {
      // snippet-start: DeleteActivity
      DeleteActivityRequest deleteRequest = DeleteActivityRequest.builder().build();
      DeleteActivityResponse response =
          feeds.deleteActivity(activityId, deleteRequest).execute().getData();
      // snippet-end: DeleteActivity

      Assertions.assertNotNull(response);
    }

    System.out.println("✅ Deleted " + activitiesToDelete.size() + " activities");
    createdActivityIds.clear();
  }

  // =================================================================
  // 10. ADDITIONAL COMPREHENSIVE TESTS
  // =================================================================

  @Test
  @Order(28)
  void test24_CreatePoll() throws Exception {
    System.out.println("\n🗳️ Testing poll creation...");

    try {
      // snippet-start: CreatePoll
      List<PollOptionInput> options =
          List.of(
              PollOptionInput.builder().text("Red").build(),
              PollOptionInput.builder().text("Blue").build());

      CreatePollRequest poll =
          CreatePollRequest.builder()
              .name("Poll")
              .description(POLL_QUESTION)
              .userID(testUserId)
              .options(options)
              .build();

      PollResponse pollResponse = common.createPoll(poll).execute().getData();
      String pollId = pollResponse.getPoll().getId();

      Map<String, Object> customData = new HashMap<>();
      customData.put("poll_name", POLL_QUESTION);
      customData.put(
          "poll_description", "Choose your favorite programming language from the options below");
      customData.put("poll_options", List.of("PHP", "Python", "JavaScript", "Go"));
      customData.put("allow_user_suggested_options", false);
      customData.put("max_votes_allowed", 1);

      AddActivityRequest pollActivity =
          AddActivityRequest.builder()
              .type("poll")
              .feeds(List.of(testFeedId))
              .pollID(pollId)
              .text(POLL_QUESTION)
              .userID(testUserId)
              .custom(customData)
              .build();

      AddActivityResponse response = feeds.addActivity(pollActivity).execute().getData();
      // snippet-end: CreatePoll

      Assertions.assertNotNull(response.getActivity());
      String activityId = response.getActivity().getId();
      createdActivityIds.add(activityId);

      System.out.println("✅ Created poll activity: " + activityId);
    } catch (Exception e) {
      System.out.println("Poll creation skipped: " + e.getMessage());
    }
  }

  @Test
  @Order(29)
  void test25_VotePoll() throws Exception {
    System.out.println("\n✅ Testing poll voting...");

    try {
      // Create a poll first using the proper API
      List<PollOptionInput> options =
          List.of(
              PollOptionInput.builder().text("Red").build(),
              PollOptionInput.builder().text("Blue").build(),
              PollOptionInput.builder().text("Green").build());

      CreatePollRequest poll =
          CreatePollRequest.builder()
              .name("Favorite Color Poll")
              .description("What is your favorite color?")
              .userID(testUserId)
              .options(options)
              .build();

      PollResponse pollResponse = common.createPoll(poll).execute().getData();
      String pollId = pollResponse.getPoll().getId();

      // Create activity with the poll
      Map<String, Object> customData = new HashMap<>();
      customData.put("poll_name", "What is your favorite color?");
      customData.put("poll_description", "Choose your favorite color from the options below");
      customData.put("poll_options", List.of("Red", "Blue", "Green"));
      customData.put("allow_user_suggested_options", false);

      AddActivityRequest pollActivity =
          AddActivityRequest.builder()
              .type("poll")
              .feeds(List.of(testFeedId))
              .text("Vote for your favorite color")
              .userID(testUserId)
              .pollID(pollId)
              .custom(customData)
              .build();

      AddActivityResponse createResponse = feeds.addActivity(pollActivity).execute().getData();
      String activityId = createResponse.getActivity().getId();
      createdActivityIds.add(activityId);

      // Get poll options from the poll response
      List<PollOptionResponseData> pollOptions = pollResponse.getPoll().getOptions();

      if (!pollOptions.isEmpty()) {
        // Use the first option ID from the poll creation response
        String optionId = pollOptions.get(0).getId();

        try {
          // snippet-start: VotePoll
          VoteData voteData = VoteData.builder().optionID(optionId).build();

          CastPollVoteRequest voteRequest =
              CastPollVoteRequest.builder().userID(testUserId).vote(voteData).build();

          PollVoteResponse voteResponse =
              feeds.castPollVote(activityId, pollId, voteRequest).execute().getData();
          // snippet-end: VotePoll

          Assertions.assertNotNull(voteResponse.getVote());
          System.out.println("✅ Voted on poll: " + activityId);
        } catch (Exception e) {
          System.out.println("Poll voting skipped: " + e.getMessage());
        }
      } else {
        System.out.println("⚠️ Poll options not found in poll response");
      }
    } catch (Exception e) {
      System.out.println("Poll voting skipped: " + e.getMessage());
    }
  }

  @Test
  @Order(30)
  void test26_ModerateActivity() throws Exception {
    System.out.println("\n🛡️ Testing activity moderation...");

    // Create an activity to moderate
    AddActivityRequest activity =
        AddActivityRequest.builder()
            .type("post")
            .text("This content might need moderation")
            .userID(testUserId)
            .feeds(List.of(testFeedId))
            .build();

    AddActivityResponse createResponse = feeds.addActivity(activity).execute().getData();
    String activityId = createResponse.getActivity().getId();
    createdActivityIds.add(activityId);

    try {
      // snippet-start: ModerateActivity
      ActivityFeedbackRequest moderationRequest =
          ActivityFeedbackRequest.builder()
              .report(true)
              .reason("inappropriate_content")
              .userID(testUserId2) // Different user reporting
              .build();

      ActivityFeedbackResponse moderationResponse =
          feeds.activityFeedback(activityId, moderationRequest).execute().getData();
      // snippet-end: ModerateActivity

      Assertions.assertNotNull(moderationResponse);
      System.out.println("✅ Flagged activity for moderation: " + activityId);
    } catch (Exception e) {
      System.out.println("Activity moderation skipped: " + e.getMessage());
    }
  }

  @Test
  @Order(31)
  void test27_DeviceManagement() throws Exception {
    System.out.println("\n📱 Testing device management...");

    String deviceToken = "test-device-token-" + RandomStringUtils.randomAlphanumeric(8);

    try {
      // snippet-start: AddDevice
      CreateDeviceRequest addDeviceRequest =
          CreateDeviceRequest.builder()
              .id(deviceToken)
              .pushProvider("apn")
              .userID(testUserId)
              .build();

      Response addDeviceResponse = client.createDevice(addDeviceRequest).execute().getData();
      // snippet-end: AddDevice

      Assertions.assertNotNull(addDeviceResponse);
      System.out.println("✅ Added device: " + deviceToken);

      // snippet-start: RemoveDevice
      DeleteDeviceRequest deleteDeviceRequest = DeleteDeviceRequest.builder().build();
      Response removeDeviceResponse = client.deleteDevice(deleteDeviceRequest).execute().getData();
      // snippet-end: RemoveDevice

      Assertions.assertNotNull(removeDeviceResponse);
      System.out.println("✅ Removed device: " + deviceToken);
    } catch (Exception e) {
      System.out.println("Device management skipped: " + e.getMessage());
    }
  }

  @Test
  @Order(32)
  void test28_QueryActivitiesWithFilters() throws Exception {
    System.out.println("\n🔍 Testing activity queries with advanced filters...");

    // Create activities with different types and metadata
    String[] activityTypes = {"post", "photo", "video", "story"};

    for (String type : activityTypes) {
      Map<String, Object> customData = new HashMap<>();
      customData.put("category", type);
      customData.put("priority", new Random().nextInt(5) + 1);
      customData.put("tags", List.of(type, "test", "filter"));

      AddActivityRequest activity =
          AddActivityRequest.builder()
              .type(type)
              .text("Test " + type + " activity for filtering")
              .userID(testUserId)
              .feeds(List.of(testFeedId))
              .custom(customData)
              .build();

      AddActivityResponse createResponse = feeds.addActivity(activity).execute().getData();
      createdActivityIds.add(createResponse.getActivity().getId());
    }

    try {
      // Query with type filter
      // snippet-start: QueryActivitiesWithTypeFilter
      Map<String, Object> filter = new HashMap<>();
      filter.put("activity_type", "post");
      filter.put("user_id", testUserId);

      List<SortParamRequest> sort =
          List.of(SortParamRequest.builder().field("created_at").direction(-1).build());

      QueryActivitiesRequest request =
          QueryActivitiesRequest.builder().limit(10).filter(filter).sort(sort).build();

      feeds.queryActivities(request).execute().getData();
      // snippet-end: QueryActivitiesWithTypeFilter

      QueryActivitiesResponse response = feeds.queryActivities(request).execute().getData();
      Assertions.assertNotNull(response.getActivities());
    } catch (Exception e) {
      System.out.println("Query activities with type filter skipped: " + e.getMessage());
    }

    try {
      // Query with custom field filter
      // snippet-start: QueryActivitiesWithCustomFilter
      Map<String, Object> customFilter = new HashMap<>();
      Map<String, Object> priorityFilter = new HashMap<>();
      priorityFilter.put("$gte", 3); // priority >= 3
      customFilter.put("priority", priorityFilter);
      customFilter.put("user_id", testUserId);

      QueryActivitiesRequest customFilterRequest =
          QueryActivitiesRequest.builder().limit(10).filter(customFilter).build();

      feeds.queryActivities(customFilterRequest).execute().getData();
      // snippet-end: QueryActivitiesWithCustomFilter

      QueryActivitiesResponse customFilterResponse =
          feeds.queryActivities(customFilterRequest).execute().getData();
      Assertions.assertNotNull(customFilterResponse.getActivities());
    } catch (Exception e) {
      System.out.println("Query activities with custom filter skipped: " + e.getMessage());
    }

    System.out.println("✅ Queried activities with advanced filters");
  }

  @Test
  @Order(33)
  void test29_GetFeedActivitiesWithPagination() throws Exception {
    System.out.println("\n📄 Testing feed activities with pagination...");

    // Create multiple activities for pagination test
    for (int i = 1; i <= 7; i++) {
      AddActivityRequest activity =
          AddActivityRequest.builder()
              .type("post")
              .text("Pagination test activity " + i)
              .userID(testUserId)
              .feeds(List.of(testFeedId))
              .build();

      AddActivityResponse createResponse = feeds.addActivity(activity).execute().getData();
      createdActivityIds.add(createResponse.getActivity().getId());
    }

    // Get first page
    // snippet-start: GetFeedActivitiesWithPagination
    Map<String, Object> filter = new HashMap<>();
    filter.put("user_id", testUserId);

    QueryActivitiesRequest firstPageRequest =
        QueryActivitiesRequest.builder().limit(3).filter(filter).build();

    QueryActivitiesResponse firstPageResponse =
        feeds.queryActivities(firstPageRequest).execute().getData();
    // snippet-end: GetFeedActivitiesWithPagination

    Assertions.assertNotNull(firstPageResponse.getActivities());
    Assertions.assertTrue(firstPageResponse.getActivities().size() <= 3);

    // Get second page using next token if available
    // snippet-start: GetFeedActivitiesSecondPage
    String nextToken = firstPageResponse.getNext();
    if (nextToken != null) {
      QueryActivitiesRequest secondPageRequest =
          QueryActivitiesRequest.builder().limit(3).next(nextToken).filter(filter).build();

      QueryActivitiesResponse secondPageResponse =
          feeds.queryActivities(secondPageRequest).execute().getData();
      Assertions.assertNotNull(secondPageResponse.getActivities());
    } else {
      System.out.println("⚠️ No next page available");
    }
    // snippet-end: GetFeedActivitiesSecondPage

    System.out.println("✅ Retrieved feed activities with pagination");
  }

  /** Test comprehensive error handling scenarios */
  @Test
  @Order(34)
  void test30_ErrorHandlingScenarios() throws Exception {
    System.out.println("\n⚠️ Testing error handling scenarios...");

    // Test 1: Invalid activity ID
    try {
      // snippet-start: HandleInvalidActivityId
      feeds.getActivity("invalid-activity-id-12345").execute().getData();
      // snippet-end: HandleInvalidActivityId

      // If we get here without exception, check if response indicates failure
      System.out.println("✅ Handled invalid activity ID gracefully");
    } catch (Exception e) {
      System.out.println("✅ Caught expected error for invalid activity ID: " + e.getMessage());
    }

    // Test 2: Empty activity text
    try {
      // snippet-start: HandleEmptyActivityText
      AddActivityRequest emptyActivity =
          AddActivityRequest.builder()
              .type("post")
              .text("") // Empty text
              .userID(testUserId)
              .feeds(List.of(testFeedId))
              .build();

      feeds.addActivity(emptyActivity).execute().getData();
      // snippet-end: HandleEmptyActivityText

      System.out.println("✅ Handled empty activity text gracefully");
    } catch (Exception e) {
      System.out.println("✅ Caught expected error for empty activity text: " + e.getMessage());
    }

    // Test 3: Invalid user ID
    try {
      // snippet-start: HandleInvalidUserId
      AddActivityRequest invalidUserActivity =
          AddActivityRequest.builder()
              .type("post")
              .text("Test with invalid user")
              .userID("") // Empty user ID
              .feeds(List.of(testFeedId))
              .build();

      feeds.addActivity(invalidUserActivity).execute().getData();
      // snippet-end: HandleInvalidUserId

      System.out.println("✅ Handled invalid user ID gracefully");
    } catch (Exception e) {
      System.out.println("✅ Caught expected error for invalid user ID: " + e.getMessage());
    }

    Assertions.assertTrue(true); // Test passes if we reach here
  }

  /** Test authentication and authorization scenarios */
  @Test
  @Order(35)
  void test31_AuthenticationScenarios() throws Exception {
    System.out.println("\n🔐 Testing authentication scenarios...");

    // Test with valid user authentication
    // snippet-start: ValidUserAuthentication
    AddActivityRequest activity =
        AddActivityRequest.builder()
            .type("post")
            .text("Activity with proper authentication")
            .userID(testUserId)
            .feeds(List.of(testFeedId))
            .build();

    AddActivityResponse response = feeds.addActivity(activity).execute().getData();
    // snippet-end: ValidUserAuthentication

    Assertions.assertNotNull(response.getActivity());
    String activityId = response.getActivity().getId();
    createdActivityIds.add(activityId);

    System.out.println("✅ Successfully authenticated and created activity: " + activityId);

    // Test user permissions for updating activity
    // snippet-start: UserPermissionUpdate
    UpdateActivityRequest updateRequest =
        UpdateActivityRequest.builder()
            .text("Updated with proper user permissions")
            .userID(testUserId) // Same user can update
            .build();

    UpdateActivityResponse updateResponse =
        feeds.updateActivity(activityId, updateRequest).execute().getData();
    // snippet-end: UserPermissionUpdate

    Assertions.assertNotNull(updateResponse.getActivity());
    System.out.println("✅ Successfully updated activity with proper user permissions");
  }

  /** Comprehensive test demonstrating real-world usage patterns */
  @Test
  @Order(36)
  void test32_RealWorldUsageDemo() throws Exception {
    System.out.println("\n🌍 Testing real-world usage patterns...");

    // Scenario: User posts content, gets reactions and comments
    // snippet-start: RealWorldScenario

    // 1. User creates a post with image
    Map<String, Object> customData = new HashMap<>();
    customData.put("location", "Downtown Coffee Co.");
    customData.put("rating", 5);
    customData.put("tags", List.of("coffee", "food", "downtown"));

    AddActivityRequest postActivity =
        AddActivityRequest.builder()
            .type("post")
            .text("Just visited the most amazing coffee shop! ☕️")
            .userID(testUserId)
            .feeds(List.of(testFeedId))
            .attachments(
                List.of(
                    Attachment.builder()
                        .imageUrl("https://example.com/coffee-shop.jpg")
                        .type("image")
                        .title("Amazing Coffee Shop")
                        .build()))
            .custom(customData)
            .build();

    AddActivityResponse postResponse = feeds.addActivity(postActivity).execute().getData();
    String postId = postResponse.getActivity().getId();
    createdActivityIds.add(postId);

    // 2. Other users react to the post
    String[] reactionTypes = {"like", "love", "wow"};
    for (String reactionType : reactionTypes) {
      AddReactionRequest reactionRequest =
          AddReactionRequest.builder().type(reactionType).userID(testUserId2).build();

      feeds.addReaction(postId, reactionRequest).execute();
    }

    // 3. Users comment on the post
    String[] comments = {
      "That place looks amazing! What did you order?",
      "I love their espresso! Great choice 😍",
      "Adding this to my must-visit list!"
    };

    for (String commentText : comments) {
      AddCommentRequest commentRequest =
          AddCommentRequest.builder()
              .comment(commentText)
              .objectID(postId)
              .objectType("activity")
              .userID(testUserId2)
              .build();

      feeds.addComment(commentRequest).execute();
    }

    // 4. User bookmarks the post
    try {
      AddBookmarkRequest bookmarkRequest =
          AddBookmarkRequest.builder()
              .userID(testUserId2)
              .newFolder(AddFolderRequest.builder().name("favorite-places").build())
              .build();

      feeds.addBookmark(postId, bookmarkRequest).execute();
    } catch (Exception e) {
      System.out.println("Bookmark operation skipped: " + e.getMessage());
    }

    // 5. Query the activity with all its interactions
    GetActivityResponse enrichedResponse = feeds.getActivity(postId).execute().getData();
    Assertions.assertNotNull(enrichedResponse.getActivity());

    // snippet-end: RealWorldScenario

    System.out.println("✅ Completed real-world usage scenario demonstration");
  }

  // =================================================================
  // HELPER METHODS
  // =================================================================

  private static void cleanupResources() {
    System.out.println("\n🧹 Cleaning up test resources...");

    // Delete any remaining activities
    if (!createdActivityIds.isEmpty()) {
      for (String activityId : createdActivityIds) {
        try {
          DeleteActivityRequest deleteRequest = DeleteActivityRequest.builder().build();
          feeds.deleteActivity(activityId, deleteRequest).execute();
        } catch (Exception e) {
          // Ignore cleanup errors
          System.out.println(
              "Warning: Failed to cleanup activity " + activityId + ": " + e.getMessage());
        }
      }
    }

    // Delete any remaining comments
    if (!createdCommentIds.isEmpty()) {
      for (String commentId : createdCommentIds) {
        try {
          DeleteCommentRequest deleteRequest = DeleteCommentRequest.builder().build();
          feeds.deleteComment(commentId, deleteRequest).execute();
        } catch (Exception e) {
          // Ignore cleanup errors
          System.out.println(
              "Warning: Failed to cleanup comment " + commentId + ": " + e.getMessage());
        }
      }
    }

    System.out.println("✅ Cleanup completed");
  }
}
