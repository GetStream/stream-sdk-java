package io.getstream.services;

import io.getstream.exceptions.StreamException;
import io.getstream.models.*;
import io.getstream.services.framework.StreamRequest;
import io.getstream.services.framework.StreamSDKClient;
import java.util.*;
import org.jetbrains.annotations.NotNull;

public class VideoImpl implements Video {
  private StreamSDKClient client;

  public VideoImpl(StreamSDKClient client) {
    this.client = client;
  }

  @NotNull
  public StreamRequest<QueryUserFeedbackResponse> queryUserFeedback(
      QueryUserFeedbackRequest request) throws StreamException {

    return new StreamRequest<QueryUserFeedbackResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/video/call/feedback",
        request,
        null);
  }

  @NotNull
  public StreamRequest<QueryCallMembersResponse> queryCallMembers(QueryCallMembersRequest request)
      throws StreamException {

    return new StreamRequest<QueryCallMembersResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/video/call/members",
        request,
        null);
  }

  @NotNull
  public StreamRequest<QueryCallStatsResponse> queryCallStats(QueryCallStatsRequest request)
      throws StreamException {

    return new StreamRequest<QueryCallStatsResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/video/call/stats",
        request,
        null);
  }

  @NotNull
  public StreamRequest<GetCallResponse> getCall(
      @NotNull String type, @NotNull String id, GetCallRequest request) throws StreamException {
    var pathParams =
        Map.of(
            "type", type,
            "id", id);

    return new StreamRequest<GetCallResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "GET",
        "/api/v2/video/call/{type}/{id}",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<UpdateCallResponse> updateCall(
      @NotNull String type, @NotNull String id, UpdateCallRequest request) throws StreamException {
    var pathParams =
        Map.of(
            "type", type,
            "id", id);

    return new StreamRequest<UpdateCallResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "PATCH",
        "/api/v2/video/call/{type}/{id}",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<GetOrCreateCallResponse> getOrCreateCall(
      @NotNull String type, @NotNull String id, GetOrCreateCallRequest request)
      throws StreamException {
    var pathParams =
        Map.of(
            "type", type,
            "id", id);

    return new StreamRequest<GetOrCreateCallResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/video/call/{type}/{id}",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<BlockUserResponse> blockUser(
      @NotNull String type, @NotNull String id, BlockUserRequest request) throws StreamException {
    var pathParams =
        Map.of(
            "type", type,
            "id", id);

    return new StreamRequest<BlockUserResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/video/call/{type}/{id}/block",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<DeleteCallResponse> deleteCall(
      @NotNull String type, @NotNull String id, DeleteCallRequest request) throws StreamException {
    var pathParams =
        Map.of(
            "type", type,
            "id", id);

    return new StreamRequest<DeleteCallResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/video/call/{type}/{id}/delete",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<SendCallEventResponse> sendCallEvent(
      @NotNull String type, @NotNull String id, SendCallEventRequest request)
      throws StreamException {
    var pathParams =
        Map.of(
            "type", type,
            "id", id);

    return new StreamRequest<SendCallEventResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/video/call/{type}/{id}/event",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<CollectUserFeedbackResponse> collectUserFeedback(
      @NotNull String type,
      @NotNull String id,
      @NotNull String session,
      CollectUserFeedbackRequest request)
      throws StreamException {
    var pathParams =
        Map.of(
            "type", type,
            "id", id,
            "session", session);

    return new StreamRequest<CollectUserFeedbackResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/video/call/{type}/{id}/feedback/{session}",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<GoLiveResponse> goLive(
      @NotNull String type, @NotNull String id, GoLiveRequest request) throws StreamException {
    var pathParams =
        Map.of(
            "type", type,
            "id", id);

    return new StreamRequest<GoLiveResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/video/call/{type}/{id}/go_live",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<EndCallResponse> endCall(
      @NotNull String type, @NotNull String id, EndCallRequest request) throws StreamException {
    var pathParams =
        Map.of(
            "type", type,
            "id", id);

    return new StreamRequest<EndCallResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/video/call/{type}/{id}/mark_ended",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<UpdateCallMembersResponse> updateCallMembers(
      @NotNull String type, @NotNull String id, UpdateCallMembersRequest request)
      throws StreamException {
    var pathParams =
        Map.of(
            "type", type,
            "id", id);

    return new StreamRequest<UpdateCallMembersResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/video/call/{type}/{id}/members",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<MuteUsersResponse> muteUsers(
      @NotNull String type, @NotNull String id, MuteUsersRequest request) throws StreamException {
    var pathParams =
        Map.of(
            "type", type,
            "id", id);

    return new StreamRequest<MuteUsersResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/video/call/{type}/{id}/mute_users",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<PinResponse> videoPin(
      @NotNull String type, @NotNull String id, VideoPinRequest request) throws StreamException {
    var pathParams =
        Map.of(
            "type", type,
            "id", id);

    return new StreamRequest<PinResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/video/call/{type}/{id}/pin",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<ListRecordingsResponse> listRecordings(
      @NotNull String type, @NotNull String id, ListRecordingsRequest request)
      throws StreamException {
    var pathParams =
        Map.of(
            "type", type,
            "id", id);

    return new StreamRequest<ListRecordingsResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "GET",
        "/api/v2/video/call/{type}/{id}/recordings",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<GetCallReportResponse> getCallReport(
      @NotNull String type, @NotNull String id, GetCallReportRequest request)
      throws StreamException {
    var pathParams =
        Map.of(
            "type", type,
            "id", id);

    return new StreamRequest<GetCallReportResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "GET",
        "/api/v2/video/call/{type}/{id}/report",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<StartRTMPBroadcastsResponse> startRTMPBroadcasts(
      @NotNull String type, @NotNull String id, StartRTMPBroadcastsRequest request)
      throws StreamException {
    var pathParams =
        Map.of(
            "type", type,
            "id", id);

    return new StreamRequest<StartRTMPBroadcastsResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/video/call/{type}/{id}/rtmp_broadcasts",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<StopAllRTMPBroadcastsResponse> stopAllRTMPBroadcasts(
      @NotNull String type, @NotNull String id, StopAllRTMPBroadcastsRequest request)
      throws StreamException {
    var pathParams =
        Map.of(
            "type", type,
            "id", id);

    return new StreamRequest<StopAllRTMPBroadcastsResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/video/call/{type}/{id}/rtmp_broadcasts/stop",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<StopRTMPBroadcastsResponse> stopRTMPBroadcast(
      @NotNull String type,
      @NotNull String id,
      @NotNull String name,
      StopRTMPBroadcastRequest request)
      throws StreamException {
    var pathParams =
        Map.of(
            "type", type,
            "id", id,
            "name", name);

    return new StreamRequest<StopRTMPBroadcastsResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/video/call/{type}/{id}/rtmp_broadcasts/{name}/stop",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<StartHLSBroadcastingResponse> startHLSBroadcasting(
      @NotNull String type, @NotNull String id, StartHLSBroadcastingRequest request)
      throws StreamException {
    var pathParams =
        Map.of(
            "type", type,
            "id", id);

    return new StreamRequest<StartHLSBroadcastingResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/video/call/{type}/{id}/start_broadcasting",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<StartClosedCaptionsResponse> startClosedCaptions(
      @NotNull String type, @NotNull String id, StartClosedCaptionsRequest request)
      throws StreamException {
    var pathParams =
        Map.of(
            "type", type,
            "id", id);

    return new StreamRequest<StartClosedCaptionsResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/video/call/{type}/{id}/start_closed_captions",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<StartRecordingResponse> startRecording(
      @NotNull String type, @NotNull String id, StartRecordingRequest request)
      throws StreamException {
    var pathParams =
        Map.of(
            "type", type,
            "id", id);

    return new StreamRequest<StartRecordingResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/video/call/{type}/{id}/start_recording",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<StartTranscriptionResponse> startTranscription(
      @NotNull String type, @NotNull String id, StartTranscriptionRequest request)
      throws StreamException {
    var pathParams =
        Map.of(
            "type", type,
            "id", id);

    return new StreamRequest<StartTranscriptionResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/video/call/{type}/{id}/start_transcription",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<GetCallStatsResponse> getCallStats(
      @NotNull String type,
      @NotNull String id,
      @NotNull String session,
      GetCallStatsRequest request)
      throws StreamException {
    var pathParams =
        Map.of(
            "type", type,
            "id", id,
            "session", session);

    return new StreamRequest<GetCallStatsResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "GET",
        "/api/v2/video/call/{type}/{id}/stats/{session}",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<StopHLSBroadcastingResponse> stopHLSBroadcasting(
      @NotNull String type, @NotNull String id, StopHLSBroadcastingRequest request)
      throws StreamException {
    var pathParams =
        Map.of(
            "type", type,
            "id", id);

    return new StreamRequest<StopHLSBroadcastingResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/video/call/{type}/{id}/stop_broadcasting",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<StopClosedCaptionsResponse> stopClosedCaptions(
      @NotNull String type, @NotNull String id, StopClosedCaptionsRequest request)
      throws StreamException {
    var pathParams =
        Map.of(
            "type", type,
            "id", id);

    return new StreamRequest<StopClosedCaptionsResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/video/call/{type}/{id}/stop_closed_captions",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<StopLiveResponse> stopLive(
      @NotNull String type, @NotNull String id, StopLiveRequest request) throws StreamException {
    var pathParams =
        Map.of(
            "type", type,
            "id", id);

    return new StreamRequest<StopLiveResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/video/call/{type}/{id}/stop_live",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<StopRecordingResponse> stopRecording(
      @NotNull String type, @NotNull String id, StopRecordingRequest request)
      throws StreamException {
    var pathParams =
        Map.of(
            "type", type,
            "id", id);

    return new StreamRequest<StopRecordingResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/video/call/{type}/{id}/stop_recording",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<StopTranscriptionResponse> stopTranscription(
      @NotNull String type, @NotNull String id, StopTranscriptionRequest request)
      throws StreamException {
    var pathParams =
        Map.of(
            "type", type,
            "id", id);

    return new StreamRequest<StopTranscriptionResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/video/call/{type}/{id}/stop_transcription",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<ListTranscriptionsResponse> listTranscriptions(
      @NotNull String type, @NotNull String id, ListTranscriptionsRequest request)
      throws StreamException {
    var pathParams =
        Map.of(
            "type", type,
            "id", id);

    return new StreamRequest<ListTranscriptionsResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "GET",
        "/api/v2/video/call/{type}/{id}/transcriptions",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<UnblockUserResponse> unblockUser(
      @NotNull String type, @NotNull String id, UnblockUserRequest request) throws StreamException {
    var pathParams =
        Map.of(
            "type", type,
            "id", id);

    return new StreamRequest<UnblockUserResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/video/call/{type}/{id}/unblock",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<UnpinResponse> videoUnpin(
      @NotNull String type, @NotNull String id, VideoUnpinRequest request) throws StreamException {
    var pathParams =
        Map.of(
            "type", type,
            "id", id);

    return new StreamRequest<UnpinResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/video/call/{type}/{id}/unpin",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<UpdateUserPermissionsResponse> updateUserPermissions(
      @NotNull String type, @NotNull String id, UpdateUserPermissionsRequest request)
      throws StreamException {
    var pathParams =
        Map.of(
            "type", type,
            "id", id);

    return new StreamRequest<UpdateUserPermissionsResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/video/call/{type}/{id}/user_permissions",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<DeleteRecordingResponse> deleteRecording(
      @NotNull String type,
      @NotNull String id,
      @NotNull String session,
      @NotNull String filename,
      DeleteRecordingRequest request)
      throws StreamException {
    var pathParams =
        Map.of(
            "type", type,
            "id", id,
            "session", session,
            "filename", filename);

    return new StreamRequest<DeleteRecordingResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "DELETE",
        "/api/v2/video/call/{type}/{id}/{session}/recordings/{filename}",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<DeleteTranscriptionResponse> deleteTranscription(
      @NotNull String type,
      @NotNull String id,
      @NotNull String session,
      @NotNull String filename,
      DeleteTranscriptionRequest request)
      throws StreamException {
    var pathParams =
        Map.of(
            "type", type,
            "id", id,
            "session", session,
            "filename", filename);

    return new StreamRequest<DeleteTranscriptionResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "DELETE",
        "/api/v2/video/call/{type}/{id}/{session}/transcriptions/{filename}",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<QueryCallsResponse> queryCalls(QueryCallsRequest request)
      throws StreamException {

    return new StreamRequest<QueryCallsResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/video/calls",
        request,
        null);
  }

  @NotNull
  public StreamRequest<ListCallTypeResponse> listCallTypes(ListCallTypesRequest request)
      throws StreamException {

    return new StreamRequest<ListCallTypeResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "GET",
        "/api/v2/video/calltypes",
        request,
        null);
  }

  @NotNull
  public StreamRequest<CreateCallTypeResponse> createCallType(CreateCallTypeRequest request)
      throws StreamException {

    return new StreamRequest<CreateCallTypeResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/video/calltypes",
        request,
        null);
  }

  @NotNull
  public StreamRequest<Response> deleteCallType(@NotNull String name, DeleteCallTypeRequest request)
      throws StreamException {
    var pathParams = Map.of("name", name);

    return new StreamRequest<Response>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "DELETE",
        "/api/v2/video/calltypes/{name}",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<GetCallTypeResponse> getCallType(
      @NotNull String name, GetCallTypeRequest request) throws StreamException {
    var pathParams = Map.of("name", name);

    return new StreamRequest<GetCallTypeResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "GET",
        "/api/v2/video/calltypes/{name}",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<UpdateCallTypeResponse> updateCallType(
      @NotNull String name, UpdateCallTypeRequest request) throws StreamException {
    var pathParams = Map.of("name", name);

    return new StreamRequest<UpdateCallTypeResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "PUT",
        "/api/v2/video/calltypes/{name}",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<GetEdgesResponse> getEdges(GetEdgesRequest request) throws StreamException {

    return new StreamRequest<GetEdgesResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "GET",
        "/api/v2/video/edges",
        request,
        null);
  }

  @NotNull
  public StreamRequest<QueryAggregateCallStatsResponse> queryAggregateCallStats(
      QueryAggregateCallStatsRequest request) throws StreamException {

    return new StreamRequest<QueryAggregateCallStatsResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/video/stats",
        request,
        null);
  }

  @NotNull
  public Call call(String callType, String callID) {
    return new Call(callType, callID, client.video());
  }
}
