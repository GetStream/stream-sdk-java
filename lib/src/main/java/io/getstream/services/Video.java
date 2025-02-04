package io.getstream.services;

import io.getstream.exceptions.StreamException;
import io.getstream.models.*;
import io.getstream.services.framework.StreamRequest;
import java.util.*;
import org.jetbrains.annotations.NotNull;

public interface Video {
  @NotNull
  public StreamRequest<QueryUserFeedbackResponse> queryUserFeedback(
      QueryUserFeedbackRequest request) throws StreamException;

  @NotNull
  public StreamRequest<QueryUserFeedbackResponse> queryUserFeedback() throws StreamException;

  @NotNull
  public StreamRequest<QueryCallMembersResponse> queryCallMembers(QueryCallMembersRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<QueryCallStatsResponse> queryCallStats(QueryCallStatsRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<QueryCallStatsResponse> queryCallStats() throws StreamException;

  @NotNull
  public StreamRequest<GetCallResponse> getCall(
      @NotNull String type, @NotNull String id, GetCallRequest request) throws StreamException;

  @NotNull
  public StreamRequest<GetCallResponse> getCall(@NotNull String type, @NotNull String id)
      throws StreamException;

  @NotNull
  public StreamRequest<UpdateCallResponse> updateCall(
      @NotNull String type, @NotNull String id, UpdateCallRequest request) throws StreamException;

  @NotNull
  public StreamRequest<UpdateCallResponse> updateCall(@NotNull String type, @NotNull String id)
      throws StreamException;

  @NotNull
  public StreamRequest<GetOrCreateCallResponse> getOrCreateCall(
      @NotNull String type, @NotNull String id, GetOrCreateCallRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<GetOrCreateCallResponse> getOrCreateCall(
      @NotNull String type, @NotNull String id) throws StreamException;

  @NotNull
  public StreamRequest<BlockUserResponse> blockUser(
      @NotNull String type, @NotNull String id, BlockUserRequest request) throws StreamException;

  @NotNull
  public StreamRequest<DeleteCallResponse> deleteCall(
      @NotNull String type, @NotNull String id, DeleteCallRequest request) throws StreamException;

  @NotNull
  public StreamRequest<DeleteCallResponse> deleteCall(@NotNull String type, @NotNull String id)
      throws StreamException;

  @NotNull
  public StreamRequest<SendCallEventResponse> sendCallEvent(
      @NotNull String type, @NotNull String id, SendCallEventRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<SendCallEventResponse> sendCallEvent(
      @NotNull String type, @NotNull String id) throws StreamException;

  @NotNull
  public StreamRequest<CollectUserFeedbackResponse> collectUserFeedback(
      @NotNull String type,
      @NotNull String id,
      @NotNull String session,
      CollectUserFeedbackRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<GoLiveResponse> goLive(
      @NotNull String type, @NotNull String id, GoLiveRequest request) throws StreamException;

  @NotNull
  public StreamRequest<GoLiveResponse> goLive(@NotNull String type, @NotNull String id)
      throws StreamException;

  @NotNull
  public StreamRequest<EndCallResponse> endCall(
      @NotNull String type, @NotNull String id, EndCallRequest request) throws StreamException;

  @NotNull
  public StreamRequest<EndCallResponse> endCall(@NotNull String type, @NotNull String id)
      throws StreamException;

  @NotNull
  public StreamRequest<UpdateCallMembersResponse> updateCallMembers(
      @NotNull String type, @NotNull String id, UpdateCallMembersRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<UpdateCallMembersResponse> updateCallMembers(
      @NotNull String type, @NotNull String id) throws StreamException;

  @NotNull
  public StreamRequest<MuteUsersResponse> muteUsers(
      @NotNull String type, @NotNull String id, MuteUsersRequest request) throws StreamException;

  @NotNull
  public StreamRequest<MuteUsersResponse> muteUsers(@NotNull String type, @NotNull String id)
      throws StreamException;

  @NotNull
  public StreamRequest<PinResponse> videoPin(
      @NotNull String type, @NotNull String id, VideoPinRequest request) throws StreamException;

  @NotNull
  public StreamRequest<ListRecordingsResponse> listRecordings(
      @NotNull String type, @NotNull String id, ListRecordingsRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<ListRecordingsResponse> listRecordings(
      @NotNull String type, @NotNull String id) throws StreamException;

  @NotNull
  public StreamRequest<GetCallReportResponse> getCallReport(
      @NotNull String type, @NotNull String id, GetCallReportRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<GetCallReportResponse> getCallReport(
      @NotNull String type, @NotNull String id) throws StreamException;

  @NotNull
  public StreamRequest<StartRTMPBroadcastsResponse> startRTMPBroadcasts(
      @NotNull String type, @NotNull String id, StartRTMPBroadcastsRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<StopAllRTMPBroadcastsResponse> stopAllRTMPBroadcasts(
      @NotNull String type, @NotNull String id, StopAllRTMPBroadcastsRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<StopAllRTMPBroadcastsResponse> stopAllRTMPBroadcasts(
      @NotNull String type, @NotNull String id) throws StreamException;

  @NotNull
  public StreamRequest<StopRTMPBroadcastsResponse> stopRTMPBroadcast(
      @NotNull String type,
      @NotNull String id,
      @NotNull String name,
      StopRTMPBroadcastRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<StopRTMPBroadcastsResponse> stopRTMPBroadcast(
      @NotNull String type, @NotNull String id, @NotNull String name) throws StreamException;

  @NotNull
  public StreamRequest<StartHLSBroadcastingResponse> startHLSBroadcasting(
      @NotNull String type, @NotNull String id, StartHLSBroadcastingRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<StartHLSBroadcastingResponse> startHLSBroadcasting(
      @NotNull String type, @NotNull String id) throws StreamException;

  @NotNull
  public StreamRequest<StartClosedCaptionsResponse> startClosedCaptions(
      @NotNull String type, @NotNull String id, StartClosedCaptionsRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<StartClosedCaptionsResponse> startClosedCaptions(
      @NotNull String type, @NotNull String id) throws StreamException;

  @NotNull
  public StreamRequest<StartRecordingResponse> startRecording(
      @NotNull String type, @NotNull String id, StartRecordingRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<StartRecordingResponse> startRecording(
      @NotNull String type, @NotNull String id) throws StreamException;

  @NotNull
  public StreamRequest<StartTranscriptionResponse> startTranscription(
      @NotNull String type, @NotNull String id, StartTranscriptionRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<StartTranscriptionResponse> startTranscription(
      @NotNull String type, @NotNull String id) throws StreamException;

  @NotNull
  public StreamRequest<GetCallStatsResponse> getCallStats(
      @NotNull String type,
      @NotNull String id,
      @NotNull String session,
      GetCallStatsRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<GetCallStatsResponse> getCallStats(
      @NotNull String type, @NotNull String id, @NotNull String session) throws StreamException;

  @NotNull
  public StreamRequest<StopHLSBroadcastingResponse> stopHLSBroadcasting(
      @NotNull String type, @NotNull String id, StopHLSBroadcastingRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<StopHLSBroadcastingResponse> stopHLSBroadcasting(
      @NotNull String type, @NotNull String id) throws StreamException;

  @NotNull
  public StreamRequest<StopClosedCaptionsResponse> stopClosedCaptions(
      @NotNull String type, @NotNull String id, StopClosedCaptionsRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<StopClosedCaptionsResponse> stopClosedCaptions(
      @NotNull String type, @NotNull String id) throws StreamException;

  @NotNull
  public StreamRequest<StopLiveResponse> stopLive(
      @NotNull String type, @NotNull String id, StopLiveRequest request) throws StreamException;

  @NotNull
  public StreamRequest<StopLiveResponse> stopLive(@NotNull String type, @NotNull String id)
      throws StreamException;

  @NotNull
  public StreamRequest<StopRecordingResponse> stopRecording(
      @NotNull String type, @NotNull String id, StopRecordingRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<StopRecordingResponse> stopRecording(
      @NotNull String type, @NotNull String id) throws StreamException;

  @NotNull
  public StreamRequest<StopTranscriptionResponse> stopTranscription(
      @NotNull String type, @NotNull String id, StopTranscriptionRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<StopTranscriptionResponse> stopTranscription(
      @NotNull String type, @NotNull String id) throws StreamException;

  @NotNull
  public StreamRequest<ListTranscriptionsResponse> listTranscriptions(
      @NotNull String type, @NotNull String id, ListTranscriptionsRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<ListTranscriptionsResponse> listTranscriptions(
      @NotNull String type, @NotNull String id) throws StreamException;

  @NotNull
  public StreamRequest<UnblockUserResponse> unblockUser(
      @NotNull String type, @NotNull String id, UnblockUserRequest request) throws StreamException;

  @NotNull
  public StreamRequest<UnpinResponse> videoUnpin(
      @NotNull String type, @NotNull String id, VideoUnpinRequest request) throws StreamException;

  @NotNull
  public StreamRequest<UpdateUserPermissionsResponse> updateUserPermissions(
      @NotNull String type, @NotNull String id, UpdateUserPermissionsRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<DeleteRecordingResponse> deleteRecording(
      @NotNull String type,
      @NotNull String id,
      @NotNull String session,
      @NotNull String filename,
      DeleteRecordingRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<DeleteRecordingResponse> deleteRecording(
      @NotNull String type, @NotNull String id, @NotNull String session, @NotNull String filename)
      throws StreamException;

  @NotNull
  public StreamRequest<DeleteTranscriptionResponse> deleteTranscription(
      @NotNull String type,
      @NotNull String id,
      @NotNull String session,
      @NotNull String filename,
      DeleteTranscriptionRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<DeleteTranscriptionResponse> deleteTranscription(
      @NotNull String type, @NotNull String id, @NotNull String session, @NotNull String filename)
      throws StreamException;

  @NotNull
  public StreamRequest<QueryCallsResponse> queryCalls(QueryCallsRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<QueryCallsResponse> queryCalls() throws StreamException;

  @NotNull
  public StreamRequest<ListCallTypeResponse> listCallTypes(ListCallTypesRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<ListCallTypeResponse> listCallTypes() throws StreamException;

  @NotNull
  public StreamRequest<CreateCallTypeResponse> createCallType(CreateCallTypeRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<Response> deleteCallType(@NotNull String name, DeleteCallTypeRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<Response> deleteCallType(@NotNull String name) throws StreamException;

  @NotNull
  public StreamRequest<GetCallTypeResponse> getCallType(
      @NotNull String name, GetCallTypeRequest request) throws StreamException;

  @NotNull
  public StreamRequest<GetCallTypeResponse> getCallType(@NotNull String name)
      throws StreamException;

  @NotNull
  public StreamRequest<UpdateCallTypeResponse> updateCallType(
      @NotNull String name, UpdateCallTypeRequest request) throws StreamException;

  @NotNull
  public StreamRequest<UpdateCallTypeResponse> updateCallType(@NotNull String name)
      throws StreamException;

  @NotNull
  public StreamRequest<GetEdgesResponse> getEdges(GetEdgesRequest request) throws StreamException;

  @NotNull
  public StreamRequest<GetEdgesResponse> getEdges() throws StreamException;

  @NotNull
  public StreamRequest<QueryAggregateCallStatsResponse> queryAggregateCallStats(
      QueryAggregateCallStatsRequest request) throws StreamException;

  @NotNull
  public StreamRequest<QueryAggregateCallStatsResponse> queryAggregateCallStats()
      throws StreamException;

  @NotNull
  public Call call(String callType, String callID);
}
