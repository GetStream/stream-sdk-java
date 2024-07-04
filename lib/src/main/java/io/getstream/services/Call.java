package io.getstream.services;

import io.getstream.models.*;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import retrofit2.http.Body;
import retrofit2.http.Path;
import retrofit2.http.Query;

@AllArgsConstructor
public class Call {
  private String callType;
  private String callID;

  public GetCallResponse Get(
      @Nullable @Query("members_limit") Integer membersLimit,
      @Nullable @Query("ring") Boolean ring,
      @Nullable @Query("notify") Boolean notify)
      throws Exception {
    return new Video.GetCall(this.callType, this.callID, membersLimit, ring, notify).request();
  }

  public UpdateCallResponse Update(@Nullable @Body UpdateCallRequest updateCallRequest)
      throws Exception {
    return new Video.UpdateCall(this.callType, this.callID, updateCallRequest).request();
  }

  public GetOrCreateCallResponse GetOrCreate(
      @Nullable @Body GetOrCreateCallRequest getOrCreateCallRequest) throws Exception {
    return new Video.GetOrCreateCall(this.callType, this.callID, getOrCreateCallRequest).request();
  }

  public BlockUserResponse BlockUser(@NotNull @Body BlockUserRequest blockUserRequest)
      throws Exception {
    return new Video.BlockUser(this.callType, this.callID, blockUserRequest).request();
  }

  public DeleteCallResponse Delete(@Nullable @Body DeleteCallRequest deleteCallRequest)
      throws Exception {
    return new Video.DeleteCall(this.callType, this.callID, deleteCallRequest).request();
  }

  public SendCallEventResponse SendCallEvent(
      @Nullable @Body SendCallEventRequest sendCallEventRequest) throws Exception {
    return new Video.SendCallEvent(this.callType, this.callID, sendCallEventRequest).request();
  }

  public CollectUserFeedbackResponse CollectUserFeedback(
      @NotNull @Path("session") String session,
      @NotNull @Body CollectUserFeedbackRequest collectUserFeedbackRequest)
      throws Exception {
    return new Video.CollectUserFeedback(
            this.callType, this.callID, session, collectUserFeedbackRequest)
        .request();
  }

  public GoLiveResponse GoLive(@Nullable @Body GoLiveRequest goLiveRequest) throws Exception {
    return new Video.GoLive(this.callType, this.callID, goLiveRequest).request();
  }

  public EndCallResponse End() throws Exception {
    return new Video.EndCall(this.callType, this.callID).request();
  }

  public UpdateCallMembersResponse UpdateCallMembers(
      @Nullable @Body UpdateCallMembersRequest updateCallMembersRequest) throws Exception {
    return new Video.UpdateCallMembers(this.callType, this.callID, updateCallMembersRequest)
        .request();
  }

  public MuteUsersResponse MuteUsers(@Nullable @Body MuteUsersRequest muteUsersRequest)
      throws Exception {
    return new Video.MuteUsers(this.callType, this.callID, muteUsersRequest).request();
  }

  public PinResponse VideoPin(@NotNull @Body PinRequest pinRequest) throws Exception {
    return new Video.VideoPin(this.callType, this.callID, pinRequest).request();
  }

  public ListRecordingsResponse ListRecordings() throws Exception {
    return new Video.ListRecordings(this.callType, this.callID).request();
  }

  public StartHLSBroadcastingResponse StartHLSBroadcasting() throws Exception {
    return new Video.StartHLSBroadcasting(this.callType, this.callID).request();
  }

  public StartRecordingResponse StartRecording(
      @Nullable @Body StartRecordingRequest startRecordingRequest) throws Exception {
    return new Video.StartRecording(this.callType, this.callID, startRecordingRequest).request();
  }

  public StartTranscriptionResponse StartTranscription(
      @Nullable @Body StartTranscriptionRequest startTranscriptionRequest) throws Exception {
    return new Video.StartTranscription(this.callType, this.callID, startTranscriptionRequest)
        .request();
  }

  public GetCallStatsResponse GetCallStats(@NotNull @Path("session") String session)
      throws Exception {
    return new Video.GetCallStats(this.callType, this.callID, session).request();
  }

  public StopHLSBroadcastingResponse StopHLSBroadcasting() throws Exception {
    return new Video.StopHLSBroadcasting(this.callType, this.callID).request();
  }

  public StopLiveResponse StopLive() throws Exception {
    return new Video.StopLive(this.callType, this.callID).request();
  }

  public StopRecordingResponse StopRecording() throws Exception {
    return new Video.StopRecording(this.callType, this.callID).request();
  }

  public StopTranscriptionResponse StopTranscription() throws Exception {
    return new Video.StopTranscription(this.callType, this.callID).request();
  }

  public ListTranscriptionsResponse ListTranscriptions() throws Exception {
    return new Video.ListTranscriptions(this.callType, this.callID).request();
  }

  public UnblockUserResponse UnblockUser(@NotNull @Body UnblockUserRequest unblockUserRequest)
      throws Exception {
    return new Video.UnblockUser(this.callType, this.callID, unblockUserRequest).request();
  }

  public UnpinResponse VideoUnpin(@NotNull @Body UnpinRequest unpinRequest) throws Exception {
    return new Video.VideoUnpin(this.callType, this.callID, unpinRequest).request();
  }

  public UpdateUserPermissionsResponse UpdateUserPermissions(
      @NotNull @Body UpdateUserPermissionsRequest updateUserPermissionsRequest) throws Exception {
    return new Video.UpdateUserPermissions(this.callType, this.callID, updateUserPermissionsRequest)
        .request();
  }

  public DeleteRecordingResponse DeleteRecording(
      @NotNull @Path("session") String session, @NotNull @Path("filename") String filename)
      throws Exception {
    return new Video.DeleteRecording(this.callType, this.callID, session, filename).request();
  }

  public DeleteTranscriptionResponse DeleteTranscription(
      @NotNull @Path("session") String session, @NotNull @Path("filename") String filename)
      throws Exception {
    return new Video.DeleteTranscription(this.callType, this.callID, session, filename).request();
  }
}
