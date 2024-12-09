package io.getstream.services;

import io.getstream.models.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import retrofit2.http.Body;
import retrofit2.http.Path;
import retrofit2.http.Query;

@lombok.AllArgsConstructor
public class Call {
  private String callType;
  private String callID;

  public GetCallResponse get(
      @Nullable @Query("members_limit") Integer membersLimit,
      @Nullable @Query("ring") Boolean ring,
      @Nullable @Query("notify") Boolean notify,
      @Nullable @Query("video") Boolean video)
      throws Exception {
    return new Video.getCall(this.callType, this.callID, membersLimit, ring, notify, video)
        .request();
  }

  public UpdateCallResponse update(@Nullable @Body UpdateCallRequest updateCallRequest)
      throws Exception {
    return new Video.updateCall(this.callType, this.callID, updateCallRequest).request();
  }

  public GetOrCreateCallResponse getOrCreate(
      @Nullable @Body GetOrCreateCallRequest getOrCreateCallRequest) throws Exception {
    return new Video.getOrCreateCall(this.callType, this.callID, getOrCreateCallRequest).request();
  }

  public BlockUserResponse blockUser(@NotNull @Body BlockUserRequest blockUserRequest)
      throws Exception {
    return new Video.blockUser(this.callType, this.callID, blockUserRequest).request();
  }

  public DeleteCallResponse delete(@Nullable @Body DeleteCallRequest deleteCallRequest)
      throws Exception {
    return new Video.deleteCall(this.callType, this.callID, deleteCallRequest).request();
  }

  public SendCallEventResponse sendCallEvent(
      @Nullable @Body SendCallEventRequest sendCallEventRequest) throws Exception {
    return new Video.sendCallEvent(this.callType, this.callID, sendCallEventRequest).request();
  }

  public CollectUserFeedbackResponse collectUserFeedback(
      @NotNull @Path("session") String session,
      @NotNull @Body CollectUserFeedbackRequest collectUserFeedbackRequest)
      throws Exception {
    return new Video.collectUserFeedback(
            this.callType, this.callID, session, collectUserFeedbackRequest)
        .request();
  }

  public GoLiveResponse goLive(@Nullable @Body GoLiveRequest goLiveRequest) throws Exception {
    return new Video.goLive(this.callType, this.callID, goLiveRequest).request();
  }

  public EndCallResponse end() throws Exception {
    return new Video.endCall(this.callType, this.callID).request();
  }

  public UpdateCallMembersResponse updateCallMembers(
      @Nullable @Body UpdateCallMembersRequest updateCallMembersRequest) throws Exception {
    return new Video.updateCallMembers(this.callType, this.callID, updateCallMembersRequest)
        .request();
  }

  public MuteUsersResponse muteUsers(@Nullable @Body MuteUsersRequest muteUsersRequest)
      throws Exception {
    return new Video.muteUsers(this.callType, this.callID, muteUsersRequest).request();
  }

  public PinResponse videoPin(@NotNull @Body PinRequest pinRequest) throws Exception {
    return new Video.videoPin(this.callType, this.callID, pinRequest).request();
  }

  public ListRecordingsResponse listRecordings() throws Exception {
    return new Video.listRecordings(this.callType, this.callID).request();
  }

  public StartRTMPBroadcastsResponse startRTMPBroadcasts(
      @NotNull @Body StartRTMPBroadcastsRequest startRTMPBroadcastsRequest) throws Exception {
    return new Video.startRTMPBroadcasts(this.callType, this.callID, startRTMPBroadcastsRequest)
        .request();
  }

  public StopAllRTMPBroadcastsResponse stopAllRTMPBroadcasts() throws Exception {
    return new Video.stopAllRTMPBroadcasts(this.callType, this.callID).request();
  }

  public StopRTMPBroadcastsResponse stopRTMPBroadcast(
      @NotNull @Path("name") String name,
      @Nullable @Body StopRTMPBroadcastsRequest stopRTMPBroadcastsRequest)
      throws Exception {
    return new Video.stopRTMPBroadcast(this.callType, this.callID, name, stopRTMPBroadcastsRequest)
        .request();
  }

  public StartHLSBroadcastingResponse startHLSBroadcasting() throws Exception {
    return new Video.startHLSBroadcasting(this.callType, this.callID).request();
  }

  public StartClosedCaptionsResponse startClosedCaptions() throws Exception {
    return new Video.startClosedCaptions(this.callType, this.callID).request();
  }

  public StartRecordingResponse startRecording(
      @Nullable @Body StartRecordingRequest startRecordingRequest) throws Exception {
    return new Video.startRecording(this.callType, this.callID, startRecordingRequest).request();
  }

  public StartTranscriptionResponse startTranscription(
      @Nullable @Body StartTranscriptionRequest startTranscriptionRequest) throws Exception {
    return new Video.startTranscription(this.callType, this.callID, startTranscriptionRequest)
        .request();
  }

  public GetCallStatsResponse getCallStats(@NotNull @Path("session") String session)
      throws Exception {
    return new Video.getCallStats(this.callType, this.callID, session).request();
  }

  public StopHLSBroadcastingResponse stopHLSBroadcasting() throws Exception {
    return new Video.stopHLSBroadcasting(this.callType, this.callID).request();
  }

  public StopClosedCaptionsResponse stopClosedCaptions() throws Exception {
    return new Video.stopClosedCaptions(this.callType, this.callID).request();
  }

  public StopLiveResponse stopLive(@Nullable @Body StopLiveRequest stopLiveRequest)
      throws Exception {
    return new Video.stopLive(this.callType, this.callID, stopLiveRequest).request();
  }

  public StopRecordingResponse stopRecording() throws Exception {
    return new Video.stopRecording(this.callType, this.callID).request();
  }

  public StopTranscriptionResponse stopTranscription() throws Exception {
    return new Video.stopTranscription(this.callType, this.callID).request();
  }

  public ListTranscriptionsResponse listTranscriptions() throws Exception {
    return new Video.listTranscriptions(this.callType, this.callID).request();
  }

  public UnblockUserResponse unblockUser(@NotNull @Body UnblockUserRequest unblockUserRequest)
      throws Exception {
    return new Video.unblockUser(this.callType, this.callID, unblockUserRequest).request();
  }

  public UnpinResponse videoUnpin(@NotNull @Body UnpinRequest unpinRequest) throws Exception {
    return new Video.videoUnpin(this.callType, this.callID, unpinRequest).request();
  }

  public UpdateUserPermissionsResponse updateUserPermissions(
      @NotNull @Body UpdateUserPermissionsRequest updateUserPermissionsRequest) throws Exception {
    return new Video.updateUserPermissions(this.callType, this.callID, updateUserPermissionsRequest)
        .request();
  }

  public DeleteRecordingResponse deleteRecording(
      @NotNull @Path("session") String session, @NotNull @Path("filename") String filename)
      throws Exception {
    return new Video.deleteRecording(this.callType, this.callID, session, filename).request();
  }

  public DeleteTranscriptionResponse deleteTranscription(
      @NotNull @Path("session") String session, @NotNull @Path("filename") String filename)
      throws Exception {
    return new Video.deleteTranscription(this.callType, this.callID, session, filename).request();
  }
}
