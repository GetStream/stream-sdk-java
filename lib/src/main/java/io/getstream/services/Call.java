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

  public StreamResponse<GetCallResponse> get() throws StreamException {
    return this.get(new GetCallRequest());
  }

  @NotNull
  public StreamResponse<UpdateCallResponse> update(UpdateCallRequest request)
      throws StreamException {
    return service.updateCall(this.callType, this.callID, request).execute();
  }

  public StreamResponse<UpdateCallResponse> update() throws StreamException {
    return this.update(new UpdateCallRequest());
  }

  @NotNull
  public StreamResponse<GetOrCreateCallResponse> getOrCreate(GetOrCreateCallRequest request)
      throws StreamException {
    return service.getOrCreateCall(this.callType, this.callID, request).execute();
  }

  public StreamResponse<GetOrCreateCallResponse> getOrCreate() throws StreamException {
    return this.getOrCreate(new GetOrCreateCallRequest());
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

  public StreamResponse<DeleteCallResponse> delete() throws StreamException {
    return this.delete(new DeleteCallRequest());
  }

  @NotNull
  public StreamResponse<SendCallEventResponse> sendCallEvent(SendCallEventRequest request)
      throws StreamException {
    return service.sendCallEvent(this.callType, this.callID, request).execute();
  }

  public StreamResponse<SendCallEventResponse> sendCallEvent() throws StreamException {
    return this.sendCallEvent(new SendCallEventRequest());
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

  public StreamResponse<GoLiveResponse> goLive() throws StreamException {
    return this.goLive(new GoLiveRequest());
  }

  @NotNull
  public StreamResponse<EndCallResponse> end(EndCallRequest request) throws StreamException {
    return service.endCall(this.callType, this.callID, request).execute();
  }

  public StreamResponse<EndCallResponse> end() throws StreamException {
    return this.end(new EndCallRequest());
  }

  @NotNull
  public StreamResponse<UpdateCallMembersResponse> updateCallMembers(
      UpdateCallMembersRequest request) throws StreamException {
    return service.updateCallMembers(this.callType, this.callID, request).execute();
  }

  public StreamResponse<UpdateCallMembersResponse> updateCallMembers() throws StreamException {
    return this.updateCallMembers(new UpdateCallMembersRequest());
  }

  @NotNull
  public StreamResponse<MuteUsersResponse> muteUsers(MuteUsersRequest request)
      throws StreamException {
    return service.muteUsers(this.callType, this.callID, request).execute();
  }

  public StreamResponse<MuteUsersResponse> muteUsers() throws StreamException {
    return this.muteUsers(new MuteUsersRequest());
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

  public StreamResponse<ListRecordingsResponse> listRecordings() throws StreamException {
    return this.listRecordings(new ListRecordingsRequest());
  }

  @NotNull
  public StreamResponse<GetCallReportResponse> getCallReport(GetCallReportRequest request)
      throws StreamException {
    return service.getCallReport(this.callType, this.callID, request).execute();
  }

  public StreamResponse<GetCallReportResponse> getCallReport() throws StreamException {
    return this.getCallReport(new GetCallReportRequest());
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

  public StreamResponse<StopAllRTMPBroadcastsResponse> stopAllRTMPBroadcasts()
      throws StreamException {
    return this.stopAllRTMPBroadcasts(new StopAllRTMPBroadcastsRequest());
  }

  @NotNull
  public StreamResponse<StopRTMPBroadcastsResponse> stopRTMPBroadcast(
      @NotNull String name, StopRTMPBroadcastRequest request) throws StreamException {
    return service.stopRTMPBroadcast(this.callType, this.callID, name, request).execute();
  }

  public StreamResponse<StopRTMPBroadcastsResponse> stopRTMPBroadcast(@NotNull String name)
      throws StreamException {
    return this.stopRTMPBroadcast(name, new StopRTMPBroadcastRequest());
  }

  @NotNull
  public StreamResponse<StartHLSBroadcastingResponse> startHLSBroadcasting(
      StartHLSBroadcastingRequest request) throws StreamException {
    return service.startHLSBroadcasting(this.callType, this.callID, request).execute();
  }

  public StreamResponse<StartHLSBroadcastingResponse> startHLSBroadcasting()
      throws StreamException {
    return this.startHLSBroadcasting(new StartHLSBroadcastingRequest());
  }

  @NotNull
  public StreamResponse<StartClosedCaptionsResponse> startClosedCaptions(
      StartClosedCaptionsRequest request) throws StreamException {
    return service.startClosedCaptions(this.callType, this.callID, request).execute();
  }

  public StreamResponse<StartClosedCaptionsResponse> startClosedCaptions() throws StreamException {
    return this.startClosedCaptions(new StartClosedCaptionsRequest());
  }

  @NotNull
  public StreamResponse<StartRecordingResponse> startRecording(StartRecordingRequest request)
      throws StreamException {
    return service.startRecording(this.callType, this.callID, request).execute();
  }

  public StreamResponse<StartRecordingResponse> startRecording() throws StreamException {
    return this.startRecording(new StartRecordingRequest());
  }

  @NotNull
  public StreamResponse<StartTranscriptionResponse> startTranscription(
      StartTranscriptionRequest request) throws StreamException {
    return service.startTranscription(this.callType, this.callID, request).execute();
  }

  public StreamResponse<StartTranscriptionResponse> startTranscription() throws StreamException {
    return this.startTranscription(new StartTranscriptionRequest());
  }

  @NotNull
  public StreamResponse<GetCallStatsResponse> getCallStats(
      @NotNull String session, GetCallStatsRequest request) throws StreamException {
    return service.getCallStats(this.callType, this.callID, session, request).execute();
  }

  public StreamResponse<GetCallStatsResponse> getCallStats(@NotNull String session)
      throws StreamException {
    return this.getCallStats(session, new GetCallStatsRequest());
  }

  @NotNull
  public StreamResponse<StopHLSBroadcastingResponse> stopHLSBroadcasting(
      StopHLSBroadcastingRequest request) throws StreamException {
    return service.stopHLSBroadcasting(this.callType, this.callID, request).execute();
  }

  public StreamResponse<StopHLSBroadcastingResponse> stopHLSBroadcasting() throws StreamException {
    return this.stopHLSBroadcasting(new StopHLSBroadcastingRequest());
  }

  @NotNull
  public StreamResponse<StopClosedCaptionsResponse> stopClosedCaptions(
      StopClosedCaptionsRequest request) throws StreamException {
    return service.stopClosedCaptions(this.callType, this.callID, request).execute();
  }

  public StreamResponse<StopClosedCaptionsResponse> stopClosedCaptions() throws StreamException {
    return this.stopClosedCaptions(new StopClosedCaptionsRequest());
  }

  @NotNull
  public StreamResponse<StopLiveResponse> stopLive(StopLiveRequest request) throws StreamException {
    return service.stopLive(this.callType, this.callID, request).execute();
  }

  public StreamResponse<StopLiveResponse> stopLive() throws StreamException {
    return this.stopLive(new StopLiveRequest());
  }

  @NotNull
  public StreamResponse<StopRecordingResponse> stopRecording(StopRecordingRequest request)
      throws StreamException {
    return service.stopRecording(this.callType, this.callID, request).execute();
  }

  public StreamResponse<StopRecordingResponse> stopRecording() throws StreamException {
    return this.stopRecording(new StopRecordingRequest());
  }

  @NotNull
  public StreamResponse<StopTranscriptionResponse> stopTranscription(
      StopTranscriptionRequest request) throws StreamException {
    return service.stopTranscription(this.callType, this.callID, request).execute();
  }

  public StreamResponse<StopTranscriptionResponse> stopTranscription() throws StreamException {
    return this.stopTranscription(new StopTranscriptionRequest());
  }

  @NotNull
  public StreamResponse<ListTranscriptionsResponse> listTranscriptions(
      ListTranscriptionsRequest request) throws StreamException {
    return service.listTranscriptions(this.callType, this.callID, request).execute();
  }

  public StreamResponse<ListTranscriptionsResponse> listTranscriptions() throws StreamException {
    return this.listTranscriptions(new ListTranscriptionsRequest());
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

  public StreamResponse<DeleteRecordingResponse> deleteRecording(
      @NotNull String session, @NotNull String filename) throws StreamException {
    return this.deleteRecording(session, filename, new DeleteRecordingRequest());
  }

  @NotNull
  public StreamResponse<DeleteTranscriptionResponse> deleteTranscription(
      @NotNull String session, @NotNull String filename, DeleteTranscriptionRequest request)
      throws StreamException {
    return service
        .deleteTranscription(this.callType, this.callID, session, filename, request)
        .execute();
  }

  public StreamResponse<DeleteTranscriptionResponse> deleteTranscription(
      @NotNull String session, @NotNull String filename) throws StreamException {
    return this.deleteTranscription(session, filename, new DeleteTranscriptionRequest());
  }
}
