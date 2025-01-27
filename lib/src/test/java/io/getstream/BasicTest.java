package io.getstream;

import io.getstream.exceptions.StreamException;
import io.getstream.models.*;
import io.getstream.services.ChatService;
import io.getstream.services.CommonService;
import io.getstream.services.VideoService;
import io.getstream.services.framework.StreamSDKClient;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeoutException;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;

public class BasicTest {
  static StreamSDKClient client;

  protected static FullUserResponse testUser;
  protected static List<FullUserResponse> testUsers = new ArrayList<>();
  protected static ChannelStateResponse testChannelGetResponse;
  protected static ChannelResponse testChannel;
  protected static MessageResponse testMessage;
  static ChatService chat = StreamSDKClient.getInstance().chat();
  static CommonService common = StreamSDKClient.getInstance().common();
  static VideoService video = StreamSDKClient.getInstance().video();

  @BeforeAll
  static void setup() throws Exception {
    setProperties();
    cleanChannels();
    cleanChannelTypes();
    cleanBlocklists();
    cleanCommands();
    upsertUsers();
    createTestChannel();
    createTestMessage();
    pause();

    client = StreamSDKClient.getInstance();

    common = client.common();
    chat = client.chat();
    video = client.video();
  }

  private static void cleanChannels() throws StreamException, NullPointerException {
    while (true) {
      List<String> channels =
          chat
              .queryChannels(QueryChannelsRequest.builder().build())
              .execute()
              .getData()
              .getChannels()
              .stream()
              .map(channel -> channel.getChannel().getCid())
              .collect(Collectors.toList());

      if (channels.isEmpty()) {
        break;
      }

      var deleteManyResponse =
          chat.deleteChannels(
                  DeleteChannelsRequest.builder().cids(channels).hardDelete(true).build())
              .execute();
      String taskId = deleteManyResponse.getData().getTaskID();
      Assertions.assertNotNull(taskId);

      System.out.printf("Waiting for channel deletion task %s to complete...\n", taskId);

      while (true) {
        var response = common.getTask(taskId).execute();
        String status = response.getData().getStatus();

        if (status.equals("completed") || status.equals("ok")) {
          break;
        }
        if (status.equals("failed") || status.equals("error")) {
          throw new StreamException(
              String.format(
                  "Failed to delete channel(task_id: %s): %s",
                  response.getData().getTaskID(), status),
              (Throwable) null);
        }

        // wait for the channels to delete
        Assertions.assertDoesNotThrow(() -> Thread.sleep(500));
      }
    }
  }

  private static void cleanChannelTypes() throws Exception {
    chat.listChannelTypes()
        .execute()
        .getData()
        .getChannelTypes()
        .values()
        .forEach(
            channelType -> {
              try {
                chat.deleteChannelType(channelType.getName()).execute();
              } catch (Exception e) {
                // Do nothing. Happens when there are channels of that type
              }
            });
  }

  private static void cleanBlocklists() throws Exception {
    common
        .listBlockLists()
        .execute()
        .getData()
        .getBlocklists()
        .forEach(
            blocklist -> {
              try {
                common.deleteBlockList(blocklist.getName()).execute();
              } catch (Exception e) {
                // Do nothing this happens for built in
              }
            });
  }

  private static void cleanCommands() throws Exception {
    chat.listCommands()
        .execute()
        .getData()
        .getCommands()
        .forEach(
            command -> {
              try {
                chat.deleteCommand(command.getName()).execute();
              } catch (Exception e) {
                // Do nothing
              }
            });

    waitFor(
        () -> {
          var commands =
              Assertions.assertDoesNotThrow(
                  () -> chat.listCommands().execute().getData().getCommands());
          return commands.size() == 5; // Built-in 5 commands
        });
  }

  private static void createTestMessage() throws Exception {
    testMessage = sendTestMessage();
  }

  private static void createTestChannel() throws Exception {
    testChannelGetResponse = createRandomChannel();
    testChannel = testChannelGetResponse.getChannel();
  }

  static void upsertUsers() throws Exception {
    UserRequest testUserRequestObject =
        UserRequest.builder()
            .id(RandomStringUtils.randomAlphabetic(10))
            .name("Gandalf the Grey")
            .build();

    List<UserRequest> testUsersRequestObjects = new ArrayList<>();

    testUsersRequestObjects.add(testUserRequestObject);
    testUsersRequestObjects.add(
        UserRequest.builder()
            .id(RandomStringUtils.randomAlphabetic(10))
            .name("Frodo Baggins")
            .build());
    testUsersRequestObjects.add(
        UserRequest.builder()
            .id(RandomStringUtils.randomAlphabetic(10))
            .name("Frodo Baggins")
            .build());
    testUsersRequestObjects.add(
        UserRequest.builder()
            .id(RandomStringUtils.randomAlphabetic(10))
            .name("Samwise Gamgee")
            .build());

    UpdateUsersRequest updateUsersRequest =
        UpdateUsersRequest.builder()
            .users(
                testUsersRequestObjects.stream()
                    .collect(Collectors.toMap(UserRequest::getId, x -> x)))
            .build();

    testUsers =
        common.updateUsers(updateUsersRequest).execute().getData().getUsers().values().stream()
            .toList();
    testUser = testUsers.get(0);
  }

  static void setProperties() {
    System.setProperty(
        "java.util.logging.SimpleFormatter.format",
        "%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS %4$s %2$s %5$s%6$s%n");
  }

  protected static List<ChannelMember> buildChannelMembersList() {
    return testUsers.stream()
        .map(user -> ChannelMember.builder().userID(user.getId()).build())
        .collect(Collectors.toList());
  }

  protected static ChannelStateResponse createRandomChannel() throws Exception {
    return chat.getOrCreateChannel(
            "team",
            RandomStringUtils.randomAlphabetic(12),
            ChannelGetOrCreateRequest.builder()
                .data(
                    ChannelInput.builder()
                        .createdBy(UserRequest.builder().id(testUser.getId()).build())
                        .members(buildChannelMembersList())
                        .build())
                .build())
        .execute()
        .getData();
  }

  protected static MessageResponse sendTestMessage() throws Exception {
    String text = UUID.randomUUID().toString();
    MessageRequest messageRequest =
        MessageRequest.builder().text(text).userID(testUser.getId()).build();
    return chat.sendMessage(
            testChannel.getType(),
            testChannel.getId(),
            SendMessageRequest.builder().message(messageRequest).build())
        .execute()
        .getData()
        .getMessage();
  }

  /**
   * This is used to pause after creation, as there can be a small delay before we can act upon the
   * resource
   */
  protected static void pause() {
    try {
      java.lang.Thread.sleep(6000);
    } catch (InterruptedException e) {
      // Do nothing
    }
  }

  protected static void waitFor(Supplier<Boolean> predicate) {
    waitFor(predicate, 500L, 15000L);
  }

  protected static void waitFor(Supplier<Boolean> predicate, Long askInterval, Long timeout) {
    var start = System.currentTimeMillis();

    while (true) {
      if (timeout < (System.currentTimeMillis() - start)) {
        Assertions.fail(new TimeoutException());
      }

      if (Assertions.assertDoesNotThrow(predicate::get)) {
        return;
      }

      Assertions.assertDoesNotThrow(() -> java.lang.Thread.sleep(askInterval));
    }
  }
}
