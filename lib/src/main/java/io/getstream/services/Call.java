package io.getstream.services;

import io.getstream.exceptions.StreamException;
import io.getstream.models.*;
import io.getstream.models.framework.StreamResponse;
import io.getstream.services.framework.StreamSDKClient;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import retrofit2.http.Body;
import retrofit2.http.Path;
import retrofit2.http.Query;

@lombok.AllArgsConstructor
public class Call {
  private VideoService service;
  private String callType;
  private String callID;

  public Call(String callType, String callID) {
    this.callType = callType;
    this.callID = callID;
    this.service = StreamSDKClient.getInstance().video();
  }

  public Call(String callType, String callID, VideoService service) {
    this.callType = callType;
    this.callID = callID;
    this.service = service;
  }

  public StreamResponse<GetCallResponse> get(
      @Nullable @Query("members_limit") Integer membersLimit,
      @Nullable @Query("ring") Boolean ring,
      @Nullable @Query("notify") Boolean notify,
      @Nullable @Query("video") Boolean video)
      throws StreamException {
    return service.getCall(this.callType, this.callID, membersLimit, ring, notify, video).execute();
  }

  public StreamResponse<UpdateCallResponse> update(
      @Nullable @Body UpdateCallRequest updateCallRequest) throws StreamException {
    return service.updateCall(this.callType, this.callID, updateCallRequest).execute();
  }

  public StreamResponse<GetOrCreateCallResponse> getOrCreate(
      @Nullable @Body GetOrCreateCallRequest getOrCreateCallRequest) throws StreamException {
    return service.getOrCreateCall(this.callType, this.callID, getOrCreateCallRequest).execute();
  }

  public StreamResponse<BlockUserResponse> blockUser(
      @NotNull @Body BlockUserRequest blockUserRequest) throws StreamException {
    return service.blockUser(this.callType, this.callID, blockUserRequest).execute();
  }

  public StreamResponse<DeleteCallResponse> delete(
      @Nullable @Body DeleteCallRequest deleteCallRequest) throws StreamException {
    return service.deleteCall(this.callType, this.callID, deleteCallRequest).execute();
  }

  public StreamResponse<SendCallEventResponse> sendCallEvent(
      @Nullable @Body SendCallEventRequest sendCallEventRequest) throws StreamException {
    return service.sendCallEvent(this.callType, this.callID, sendCallEventRequest).execute();
  }

  public StreamResponse<CollectUserFeedbackResponse> collectUserFeedback(
      @NotNull @Path("session") String session,
      @NotNull @Body CollectUserFeedbackRequest collectUserFeedbackRequest)
      throws StreamException {
    return service
        .collectUserFeedback(this.callType, this.callID, session, collectUserFeedbackRequest)
        .execute();
  }

  public StreamResponse<GoLiveResponse> goLive(@Nullable @Body GoLiveRequest goLiveRequest)
      throws StreamException {
    return service.goLive(this.callType, this.callID, goLiveRequest).execute();
  }

  public StreamResponse<EndCallResponse> end() throws StreamException {
    return service.endCall(this.callType, this.callID).execute();
  }

  public StreamResponse<UpdateCallMembersResponse> updateCallMembers(
      @Nullable @Body UpdateCallMembersRequest updateCallMembersRequest) throws StreamException {
    return service
        .updateCallMembers(this.callType, this.callID, updateCallMembersRequest)
        .execute();
  }

  public StreamResponse<MuteUsersResponse> muteUsers(
      @Nullable @Body MuteUsersRequest muteUsersRequest) throws StreamException {
    return service.muteUsers(this.callType, this.callID, muteUsersRequest).execute();
  }

  public StreamResponse<PinResponse> videoPin(@NotNull @Body PinRequest pinRequest)
      throws StreamException {
    return service.videoPin(this.callType, this.callID, pinRequest).execute();
  }

  public StreamResponse<ListRecordingsResponse> listRecordings() throws StreamException {
    return service.listRecordings(this.callType, this.callID).execute();
  }

  public StreamResponse<GetCallReportResponse> getCallReport(
      @Nullable @Query("session_id") String sessionID) throws StreamException {
    return service.getCallReport(this.callType, this.callID, sessionID).execute();
  }

  public StreamResponse<StartRTMPBroadcastsResponse> startRTMPBroadcasts(
      @NotNull @Body StartRTMPBroadcastsRequest startRTMPBroadcastsRequest) throws StreamException {
    return service
        .startRTMPBroadcasts(this.callType, this.callID, startRTMPBroadcastsRequest)
        .execute();
  }

  public StreamResponse<StopAllRTMPBroadcastsResponse> stopAllRTMPBroadcasts()
      throws StreamException {
    return service.stopAllRTMPBroadcasts(this.callType, this.callID).execute();
  }

  public StreamResponse<StopRTMPBroadcastsResponse> stopRTMPBroadcast(
      @NotNull @Path("name") String name,
      @Nullable @Body StopRTMPBroadcastsRequest stopRTMPBroadcastsRequest)
      throws StreamException {
    return service
        .stopRTMPBroadcast(this.callType, this.callID, name, stopRTMPBroadcastsRequest)
        .execute();
  }

  public StreamResponse<StartHLSBroadcastingResponse> startHLSBroadcasting()
      throws StreamException {
    return service.startHLSBroadcasting(this.callType, this.callID).execute();
  }

  public StreamResponse<StartClosedCaptionsResponse> startClosedCaptions(
      @Nullable @Body StartClosedCaptionsRequest startClosedCaptionsRequest)
      throws StreamException {
    return service
        .startClosedCaptions(this.callType, this.callID, startClosedCaptionsRequest)
        .execute();
  }

  public StreamResponse<StartRecordingResponse> startRecording(
      @Nullable @Body StartRecordingRequest startRecordingRequest) throws StreamException {
    return service.startRecording(this.callType, this.callID, startRecordingRequest).execute();
  }

  public StreamResponse<StartTranscriptionResponse> startTranscription(
      @Nullable @Body StartTranscriptionRequest startTranscriptionRequest) throws StreamException {
    return service
        .startTranscription(this.callType, this.callID, startTranscriptionRequest)
        .execute();
  }

  public StreamResponse<GetCallStatsResponse> getCallStats(@NotNull @Path("session") String session)
      throws StreamException {
    return service.getCallStats(this.callType, this.callID, session).execute();
  }

  public StreamResponse<StopHLSBroadcastingResponse> stopHLSBroadcasting() throws StreamException {
    return service.stopHLSBroadcasting(this.callType, this.callID).execute();
  }

  public StreamResponse<StopClosedCaptionsResponse> stopClosedCaptions(
      @Nullable @Body StopClosedCaptionsRequest stopClosedCaptionsRequest) throws StreamException {
    return service
        .stopClosedCaptions(this.callType, this.callID, stopClosedCaptionsRequest)
        .execute();
  }

  public StreamResponse<StopLiveResponse> stopLive(@Nullable @Body StopLiveRequest stopLiveRequest)
      throws StreamException {
    return service.stopLive(this.callType, this.callID, stopLiveRequest).execute();
  }

  public StreamResponse<StopRecordingResponse> stopRecording() throws StreamException {
    return service.stopRecording(this.callType, this.callID).execute();
  }

  public StreamResponse<StopTranscriptionResponse> stopTranscription(
      @Nullable @Body StopTranscriptionRequest stopTranscriptionRequest) throws StreamException {
    return service
        .stopTranscription(this.callType, this.callID, stopTranscriptionRequest)
        .execute();
  }

  public StreamResponse<ListTranscriptionsResponse> listTranscriptions() throws StreamException {
    return service.listTranscriptions(this.callType, this.callID).execute();
  }

  public StreamResponse<UnblockUserResponse> unblockUser(
      @NotNull @Body UnblockUserRequest unblockUserRequest) throws StreamException {
    return service.unblockUser(this.callType, this.callID, unblockUserRequest).execute();
  }

  public StreamResponse<UnpinResponse> videoUnpin(@NotNull @Body UnpinRequest unpinRequest)
      throws StreamException {
    return service.videoUnpin(this.callType, this.callID, unpinRequest).execute();
  }

  public StreamResponse<UpdateUserPermissionsResponse> updateUserPermissions(
      @NotNull @Body UpdateUserPermissionsRequest updateUserPermissionsRequest)
      throws StreamException {
    return service
        .updateUserPermissions(this.callType, this.callID, updateUserPermissionsRequest)
        .execute();
  }

  public StreamResponse<DeleteRecordingResponse> deleteRecording(
      @NotNull @Path("session") String session, @NotNull @Path("filename") String filename)
      throws StreamException {
    return service.deleteRecording(this.callType, this.callID, session, filename).execute();
  }

  public StreamResponse<DeleteTranscriptionResponse> deleteTranscription(
      @NotNull @Path("session") String session, @NotNull @Path("filename") String filename)
      throws StreamException {
    return service.deleteTranscription(this.callType, this.callID, session, filename).execute();
  }
}
