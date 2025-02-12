package io.getstream;

import io.getstream.exceptions.StreamException;
import io.getstream.models.*;
import io.getstream.models.framework.CallTokenClaims;
import io.getstream.services.Call;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

// DocTest is a class that contains the code snippets from the documentation.
// The code snippets are tested to ensure that they are correct and up-to-date.
@Disabled
public class DocTest extends BasicTest {

  @Test
  public void getStarted() throws Exception {
    // upserting a user
    client
        .updateUsers(
            UpdateUsersRequest.builder()
                .users(
                    Map.of(
                        "john",
                        UserRequest.builder()
                            .id("john")
                            .name("john")
                            .custom(Map.of("country", "NL"))
                            .build(),
                        "jane",
                        UserRequest.builder()
                            .id("jane")
                            .name("jane")
                            .custom(Map.of("country", "US"))
                            .build(),
                        "tom",
                        UserRequest.builder()
                            .id("tom")
                            .name("tom")
                            .custom(Map.of("country", "UK"))
                            .build(),
                        "sacha",
                        UserRequest.builder()
                            .id("sacha")
                            .name("sacha")
                            .custom(Map.of("country", "FR"))
                            .build()))
                .build())
        .execute();

    client.tokenBuilder().createToken("john", 24 * 60 * 60);

    // creating a call
    List<MemberRequest> members =
        List.of(
            MemberRequest.builder().userID("john").role("admin").build(),
            MemberRequest.builder().userID("jane").build());

    var call = new Call("default", UUID.randomUUID().toString(), video);
    call.getOrCreate(
        GetOrCreateCallRequest.builder()
            .data(
                CallRequest.builder()
                    .createdByID("sacha")
                    .members(members)
                    .custom(Map.of("color", "blue"))
                    .build())
            .build());

    members =
        List.of(
            MemberRequest.builder().userID("john").role("admin").build(),
            MemberRequest.builder().userID("jane").build(),
            MemberRequest.builder().userID("tom").build());

    call.updateCallMembers(UpdateCallMembersRequest.builder().updateMembers(members).build());

    call.updateCallMembers(
        UpdateCallMembersRequest.builder().removeMembers(List.of("jane")).build());

    // update custom data for a call
    call.update(UpdateCallRequest.builder().custom(Map.of("color", "red")).build());

    // update settings for this call
    call.update(
        UpdateCallRequest.builder()
            .settingsOverride(
                CallSettingsRequest.builder()
                    .screensharing(
                        ScreensharingSettingsRequest.builder()
                            .enabled(true)
                            .accessRequestEnabled(true)
                            .build())
                    .build())
            .build());
  }

  @Test
  public void authentication() throws Exception {
    // creating a user
    client
        .updateUsers(
            UpdateUsersRequest.builder()
                .users(
                    Map.of(
                        "john",
                        UserRequest.builder()
                            .id("john")
                            .name("john")
                            .custom(Map.of("country", "NL"))
                            .build()))
                .build())
        .execute();

    var userRequest =
        UserRequest.builder().id("john").name("john").custom(Map.of("country", "NL")).build();

    // updating users
    client
        .updateUsers(
            UpdateUsersRequest.builder().users(Map.of(userRequest.getId(), userRequest)).build())
        .execute();

    // or using partial update
    client
        .updateUsersPartial(
            UpdateUsersPartialRequest.builder()
                .users(
                    List.of(
                        UpdateUserPartialRequest.builder()
                            .id(userRequest.getId())
                            .set(Map.of("country", "US"))
                            .build()))
                .build())
        .execute();

    client
        .updateUsers(
            UpdateUsersRequest.builder()
                .users(Map.of("todeactivate", UserRequest.builder().id("todeactivate").build()))
                .build())
        .execute();

    // deactivate a user
    client.deactivateUser("todeactivate", DeactivateUserRequest.builder().build()).execute();

    client.reactivateUser("todeactivate", ReactivateUserRequest.builder().build()).execute();

    var taskId =
        client
            .deactivateUsers(
                DeactivateUsersRequest.builder().userIds(List.of("todeactivate")).build())
            .execute()
            .getData()
            .getTaskID();

    while (true) {
      var response = client.getTask(taskId).execute();
      String status = response.getData().getStatus();

      if (status.equals("completed") || status.equals("ok")) {
        break;
      }
      if (status.equals("failed") || status.equals("error")) {
        throw new StreamException(
            String.format(
                "Failed to deactivate user(task_id: %s): %s",
                response.getData().getTaskID(), status),
            (Throwable) null);
      }

      // wait for the channels to delete
      Assertions.assertDoesNotThrow(() -> Thread.sleep(500));
    }

    taskId =
        client
            .reactivateUsers(
                ReactivateUsersRequest.builder().userIds(List.of("todeactivate")).build())
            .execute()
            .getData()
            .getTaskID();

    while (true) {
      var response = client.getTask(taskId).execute();
      String status = response.getData().getStatus();

      if (status.equals("completed") || status.equals("ok")) {
        break;
      }
      if (status.equals("failed") || status.equals("error")) {
        throw new StreamException(
            String.format(
                "Failed to reactivate user(task_id: %s): %s",
                response.getData().getTaskID(), status),
            (Throwable) null);
      }

      // wait for the channels to delete
      Assertions.assertDoesNotThrow(() -> Thread.sleep(500));
    }

    var response =
        client.exportUsers(ExportUsersRequest.builder().userIds(List.of("john")).build()).execute();
    var taskID = response.getData().getTaskID();

    // get information about the task
    var taskStatus = client.getTask(taskID).execute();

    // just an example, in reality it can take a few seconds for a task to be processed
    if (taskStatus.getData().getStatus().equals("completed")) {
      System.out.println("Export is completed");
    }

    client
        .updateUsers(
            UpdateUsersRequest.builder()
                .users(Map.of("todelete", UserRequest.builder().id("todelete").build()))
                .build())
        .execute();

    // delete a user
    taskID =
        client
            .deleteUsers(DeleteUsersRequest.builder().userIds(List.of("todelete")).build())
            .execute()
            .getData()
            .getTaskID();

    // wait for the task to complete
    while (true) {
      var taskResponse = client.getTask(taskID).execute();
      String status = taskResponse.getData().getStatus();

      if (status.equals("completed") || status.equals("ok")) {
        break;
      }
      if (status.equals("failed") || status.equals("error")) {
        throw new StreamException(
            String.format(
                "Failed to delete user(task_id: %s): %s", response.getData().getTaskID(), status),
            (Throwable) null);
      }

      // wait for the channels to delete
      Assertions.assertDoesNotThrow(() -> Thread.sleep(500));
    }

    client
        .restoreUsers(RestoreUsersRequest.builder().userIds(List.of("todelete")).build())
        .execute();

    // create a token for user john
    var userToken = client.tokenBuilder().createToken("john", 24 * 60 * 60);

    // call tokens
    var callToken =
        client
            .tokenBuilder()
            .createCallToken(
                new CallTokenClaims(
                    "user-id", "admin", List.of("default:call1", "livestream:call2"), 3600));
  }

  @Test
  public void calls() throws StreamException {
    List<MemberRequest> members =
        List.of(
            MemberRequest.builder().userID("myself").build(),
            MemberRequest.builder().userID("john").build());

    var call = new Call("default", UUID.randomUUID().toString(), client.video());
    call.getOrCreate(
        GetOrCreateCallRequest.builder()
            .data(CallRequest.builder().createdByID("myself").members(members).build())
            .ring(true)
            .build());

    members =
        List.of(
            MemberRequest.builder().userID("myself").build(),
            MemberRequest.builder().userID("john").build());

    call = new Call("default", UUID.randomUUID().toString(), client.video());
    call.getOrCreate(
        GetOrCreateCallRequest.builder()
            .data(CallRequest.builder().createdByID("myself").members(members).build())
            .notify(true)
            .build());
    try {
      client.video().deleteCallType("customType").execute();
    } catch (StreamException e) {
      // ignore
    }

    client
        .video()
        .createCallType(
            CreateCallTypeRequest.builder()
                .name("customType")
                .grants(
                    Map.of(
                        "admin",
                        List.of(
                            OwnCapability.SEND_AUDIO.toString(),
                            OwnCapability.SEND_VIDEO.toString(),
                            OwnCapability.MUTE_USERS.toString()),
                        "user",
                        List.of(
                            OwnCapability.SEND_AUDIO.toString(),
                            OwnCapability.SEND_VIDEO.toString())))
                .build())
        .execute();

    client
        .video()
        .updateCallType(
            "default",
            UpdateCallTypeRequest.builder()
                .settings(
                    CallSettingsRequest.builder()
                        .session(
                            SessionSettingsRequest.builder().inactivityTimeoutSeconds(300).build())
                        .build())
                .build())
        .execute();

    call = new Call("default", UUID.randomUUID().toString(), client.video());
    call.getOrCreate(
        GetOrCreateCallRequest.builder()
            .data(
                CallRequest.builder()
                    .createdByID("john")
                    .settingsOverride(
                        CallSettingsRequest.builder()
                            .limits(
                                LimitsSettingsRequest.builder().maxDurationSeconds(3600).build())
                            .build())
                    .build())
            .build());
    // send a custom event to all users watching the call
    call.sendCallEvent(
        SendCallEventRequest.builder()
            .custom(Map.of("render-animation", "balloons"))
            .userID("john")
            .build());

    call.videoPin(
        VideoPinRequest.builder().userID("user-id-to-pin").sessionID("session-id").build());
    call.videoUnpin(
        VideoUnpinRequest.builder().userID("user-id-to-unpin").sessionID("session-id").build());

    call.update(
        UpdateCallRequest.builder()
            .settingsOverride(
                CallSettingsRequest.builder()
                    .limits(LimitsSettingsRequest.builder().maxDurationSeconds(3600).build())
                    .build())
            .build());

    call.update(
        UpdateCallRequest.builder()
            .settingsOverride(
                CallSettingsRequest.builder()
                    .limits(LimitsSettingsRequest.builder().maxDurationSeconds(0).build())
                    .build())
            .build());

    call.end();
  }

  @Test
  public void queryCalls() throws StreamException {
    // default sorting
    client.video().queryCalls(QueryCallsRequest.builder().build()).execute();

    // sorting and pagination
    var response =
        client
            .video()
            .queryCalls(
                QueryCallsRequest.builder()
                    .sort(
                        List.of(
                            SortParamRequest.builder().field("starts_at").direction(-1).build()))
                    .limit(2)
                    .build())
            .execute();

    // loading next page
    client
        .video()
        .queryCalls(
            QueryCallsRequest.builder()
                .sort(List.of(SortParamRequest.builder().field("starts_at").direction(-1).build()))
                .limit(2)
                .next(response.getData().getNext())
                .build())
        .execute();

    client
        .video()
        .queryCalls(
            QueryCallsRequest.builder().filterConditions(Map.of("backstage", false)).build())
        .execute();

    Instant inNext30Mins = Instant.now().plus(30, ChronoUnit.MINUTES);
    client
        .video()
        .queryCalls(
            QueryCallsRequest.builder()
                .filterConditions(Map.of("starts_at", inNext30Mins.toString()))
                .build())
        .execute();

    client
        .video()
        .queryCalls(QueryCallsRequest.builder().filterConditions(Map.of("ongoing", true)).build())
        .execute();

    client
        .video()
        .updateCallType(
            "default",
            UpdateCallTypeRequest.builder()
                .settings(
                    CallSettingsRequest.builder()
                        .limits(LimitsSettingsRequest.builder().maxDurationSeconds(3600).build())
                        .build())
                .build())
        .execute();

    client
        .video()
        .updateCallType(
            "default",
            UpdateCallTypeRequest.builder()
                .settings(
                    CallSettingsRequest.builder()
                        .limits(LimitsSettingsRequest.builder().maxDurationSeconds(0).build())
                        .build())
                .build())
        .execute();
  }

  @Test
  @Disabled // we dont update apps on CI
  public void multiTenant() throws StreamException {
    // shows the current status
    var app = client.getApp().execute();
    System.out.println(app.getData().getApp().getMultiTenantEnabled());

    // enables teams
    client.updateApp(UpdateAppRequest.builder().multiTenantEnabled(true).build()).execute();

    client
        .updateUsers(
            UpdateUsersRequest.builder()
                .users(
                    Map.of(
                        "john",
                        UserRequest.builder().id("john").teams(List.of("red", "blue")).build()))
                .build())
        .execute();

    var call = new Call("default", "callID", client.video());
    call.getOrCreate(
        GetOrCreateCallRequest.builder()
            .data(CallRequest.builder().team("blue").createdByID("<user_id>").build())
            .build());

    client
        .queryUsers(
            QueryUsersRequest.builder()
                .Payload(
                    QueryUsersPayload.builder()
                        .filterConditions(
                            Map.of("name", "Nick", "teams", Map.of("$in", List.of("red", "blue"))))
                        .build())
                .build())
        .execute();

    // search for users that are not part of any team
    client
        .queryUsers(
            QueryUsersRequest.builder()
                .Payload(
                    QueryUsersPayload.builder().filterConditions(Map.of("teams", null)).build())
                .build())
        .execute();

    var callCID = "default:callID";

    client
        .video()
        .queryCalls(
            QueryCallsRequest.builder()
                .filterConditions(Map.of("cid", callCID, "team", Map.of("$eq", "blue")))
                .build())
        .execute();

    // retrieve calls without a team
    client
        .video()
        .queryCalls(
            QueryCallsRequest.builder()
                .filterConditions(
                    Map.of(
                        "cid",
                        callCID,
                        "team",
                        new HashMap<>() {
                          {
                            put("$eq", null);
                          }
                        }))
                .build())
        .execute();
  }
}
