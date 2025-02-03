package io.getstream.services;

import io.getstream.exceptions.StreamException;
import io.getstream.models.*;
import io.getstream.models.framework.StreamResponse;
import org.jetbrains.annotations.NotNull;

@lombok.AllArgsConstructor
public class Call {
  private Video service;
  private String callType;
  private String callID;

  public Call(String callType, String callID, Video service) {
    this.callType = callType;
    this.callID = callID;
    this.service = service;
  }

  @NotNull
  public StreamResponse<GetCallResponse> get(GetCallRequest request) throws StreamException {
    return service.getCall(this.callType, this.callID, request).execute();
  }

  @NotNull
  public StreamResponse<UpdateCallResponse> update(UpdateCallRequest request)
      throws StreamException {
    return service.updateCall(this.callType, this.callID, request).execute();
  }

  @NotNull
  public StreamResponse<GetOrCreateCallResponse> getOrCreate(GetOrCreateCallRequest request)
      throws StreamException {
    return service.getOrCreateCall(this.callType, this.callID, request).execute();
  }

  @NotNull
  public StreamResponse<BlockUserResponse> blockUser(BlockUserRequest request)
      throws StreamException {
    return service.blockUser(this.callType, this.callID, request).execute();
  }

  @NotNull
  public StreamResponse<DeleteCallResponse> delete(DeleteCallRequest request)
      throws StreamException {
    return service.deleteCall(this.callType, this.callID, request).execute();
  }

  @NotNull
  public StreamResponse<SendCallEventResponse> sendCallEvent(SendCallEventRequest request)
      throws StreamException {
    return service.sendCallEvent(this.callType, this.callID, request).execute();
  }

  @NotNull
  public StreamResponse<CollectUserFeedbackResponse> collectUserFeedback(
      @NotNull String session, CollectUserFeedbackRequest request) throws StreamException {
    return service.collectUserFeedback(this.callType, this.callID, session, request).execute();
  }

  @NotNull
  public StreamResponse<GoLiveResponse> goLive(GoLiveRequest request) throws StreamException {
    return service.goLive(this.callType, this.callID, request).execute();
  }

  @NotNull
  public StreamResponse<EndCallResponse> end(EndCallRequest request) throws StreamException {
    return service.endCall(this.callType, this.callID, request).execute();
  }

  @NotNull
  public StreamResponse<UpdateCallMembersResponse> updateCallMembers(
      UpdateCallMembersRequest request) throws StreamException {
    return service.updateCallMembers(this.callType, this.callID, request).execute();
  }

  @NotNull
  public StreamResponse<MuteUsersResponse> muteUsers(MuteUsersRequest request)
      throws StreamException {
    return service.muteUsers(this.callType, this.callID, request).execute();
  }

  @NotNull
  public StreamResponse<PinResponse> videoPin(VideoPinRequest request) throws StreamException {
    return service.videoPin(this.callType, this.callID, request).execute();
  }

  @NotNull
  public StreamResponse<ListRecordingsResponse> listRecordings(ListRecordingsRequest request)
      throws StreamException {
    return service.listRecordings(this.callType, this.callID, request).execute();
  }

  @NotNull
  public StreamResponse<GetCallReportResponse> getCallReport(GetCallReportRequest request)
      throws StreamException {
    return service.getCallReport(this.callType, this.callID, request).execute();
  }

  @NotNull
  public StreamResponse<StartRTMPBroadcastsResponse> startRTMPBroadcasts(
      StartRTMPBroadcastsRequest request) throws StreamException {
    return service.startRTMPBroadcasts(this.callType, this.callID, request).execute();
  }

  @NotNull
  public StreamResponse<StopAllRTMPBroadcastsResponse> stopAllRTMPBroadcasts(
      StopAllRTMPBroadcastsRequest request) throws StreamException {
    return service.stopAllRTMPBroadcasts(this.callType, this.callID, request).execute();
  }

  @NotNull
  public StreamResponse<StopRTMPBroadcastsResponse> stopRTMPBroadcast(
      @NotNull String name, StopRTMPBroadcastRequest request) throws StreamException {
    return service.stopRTMPBroadcast(this.callType, this.callID, name, request).execute();
  }

  @NotNull
  public StreamResponse<StartHLSBroadcastingResponse> startHLSBroadcasting(
      StartHLSBroadcastingRequest request) throws StreamException {
    return service.startHLSBroadcasting(this.callType, this.callID, request).execute();
  }

  @NotNull
  public StreamResponse<StartClosedCaptionsResponse> startClosedCaptions(
      StartClosedCaptionsRequest request) throws StreamException {
    return service.startClosedCaptions(this.callType, this.callID, request).execute();
  }

  @NotNull
  public StreamResponse<StartRecordingResponse> startRecording(StartRecordingRequest request)
      throws StreamException {
    return service.startRecording(this.callType, this.callID, request).execute();
  }

  @NotNull
  public StreamResponse<StartTranscriptionResponse> startTranscription(
      StartTranscriptionRequest request) throws StreamException {
    return service.startTranscription(this.callType, this.callID, request).execute();
  }

  @NotNull
  public StreamResponse<GetCallStatsResponse> getCallStats(
      @NotNull String session, GetCallStatsRequest request) throws StreamException {
    return service.getCallStats(this.callType, this.callID, session, request).execute();
  }

  @NotNull
  public StreamResponse<StopHLSBroadcastingResponse> stopHLSBroadcasting(
      StopHLSBroadcastingRequest request) throws StreamException {
    return service.stopHLSBroadcasting(this.callType, this.callID, request).execute();
  }

  @NotNull
  public StreamResponse<StopClosedCaptionsResponse> stopClosedCaptions(
      StopClosedCaptionsRequest request) throws StreamException {
    return service.stopClosedCaptions(this.callType, this.callID, request).execute();
  }

  @NotNull
  public StreamResponse<StopLiveResponse> stopLive(StopLiveRequest request) throws StreamException {
    return service.stopLive(this.callType, this.callID, request).execute();
  }

  @NotNull
  public StreamResponse<StopRecordingResponse> stopRecording(StopRecordingRequest request)
      throws StreamException {
    return service.stopRecording(this.callType, this.callID, request).execute();
  }

  @NotNull
  public StreamResponse<StopTranscriptionResponse> stopTranscription(
      StopTranscriptionRequest request) throws StreamException {
    return service.stopTranscription(this.callType, this.callID, request).execute();
  }

  @NotNull
  public StreamResponse<ListTranscriptionsResponse> listTranscriptions(
      ListTranscriptionsRequest request) throws StreamException {
    return service.listTranscriptions(this.callType, this.callID, request).execute();
  }

  @NotNull
  public StreamResponse<UnblockUserResponse> unblockUser(UnblockUserRequest request)
      throws StreamException {
    return service.unblockUser(this.callType, this.callID, request).execute();
  }

  @NotNull
  public StreamResponse<UnpinResponse> videoUnpin(VideoUnpinRequest request)
      throws StreamException {
    return service.videoUnpin(this.callType, this.callID, request).execute();
  }

  @NotNull
  public StreamResponse<UpdateUserPermissionsResponse> updateUserPermissions(
      UpdateUserPermissionsRequest request) throws StreamException {
    return service.updateUserPermissions(this.callType, this.callID, request).execute();
  }

  @NotNull
  public StreamResponse<DeleteRecordingResponse> deleteRecording(
      @NotNull String session, @NotNull String filename, DeleteRecordingRequest request)
      throws StreamException {
    return service
        .deleteRecording(this.callType, this.callID, session, filename, request)
        .execute();
  }

  @NotNull
  public StreamResponse<DeleteTranscriptionResponse> deleteTranscription(
      @NotNull String session, @NotNull String filename, DeleteTranscriptionRequest request)
      throws StreamException {
    return service
        .deleteTranscription(this.callType, this.callID, session, filename, request)
        .execute();
  }
}
