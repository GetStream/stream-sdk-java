package io.getstream.services;

import com.fasterxml.jackson.core.type.TypeReference;
import io.getstream.exceptions.StreamException;
import io.getstream.models.*;
import io.getstream.services.framework.StreamHTTPClient;
import io.getstream.services.framework.StreamRequest;
import java.util.*;
import org.jetbrains.annotations.NotNull;

public class VideoImpl implements Video {
  private StreamHTTPClient client;

  public VideoImpl(StreamHTTPClient client) {
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
        null,
        new TypeReference<QueryUserFeedbackResponse>() {});
  }

  @NotNull
  public StreamRequest<QueryUserFeedbackResponse> queryUserFeedback() throws StreamException {
    return queryUserFeedback(new QueryUserFeedbackRequest());
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
        null,
        new TypeReference<QueryCallMembersResponse>() {});
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
        null,
        new TypeReference<QueryCallStatsResponse>() {});
  }

  @NotNull
  public StreamRequest<QueryCallStatsResponse> queryCallStats() throws StreamException {
    return queryCallStats(new QueryCallStatsRequest());
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
        pathParams,
        new TypeReference<GetCallResponse>() {});
  }

  @NotNull
  public StreamRequest<GetCallResponse> getCall(@NotNull String type, @NotNull String id)
      throws StreamException {
    return getCall(type, id, new GetCallRequest());
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
        pathParams,
        new TypeReference<UpdateCallResponse>() {});
  }

  @NotNull
  public StreamRequest<UpdateCallResponse> updateCall(@NotNull String type, @NotNull String id)
      throws StreamException {
    return updateCall(type, id, new UpdateCallRequest());
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
        pathParams,
        new TypeReference<GetOrCreateCallResponse>() {});
  }

  @NotNull
  public StreamRequest<GetOrCreateCallResponse> getOrCreateCall(
      @NotNull String type, @NotNull String id) throws StreamException {
    return getOrCreateCall(type, id, new GetOrCreateCallRequest());
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
        pathParams,
        new TypeReference<BlockUserResponse>() {});
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
        pathParams,
        new TypeReference<DeleteCallResponse>() {});
  }

  @NotNull
  public StreamRequest<DeleteCallResponse> deleteCall(@NotNull String type, @NotNull String id)
      throws StreamException {
    return deleteCall(type, id, new DeleteCallRequest());
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
        pathParams,
        new TypeReference<SendCallEventResponse>() {});
  }

  @NotNull
  public StreamRequest<SendCallEventResponse> sendCallEvent(
      @NotNull String type, @NotNull String id) throws StreamException {
    return sendCallEvent(type, id, new SendCallEventRequest());
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
        pathParams,
        new TypeReference<CollectUserFeedbackResponse>() {});
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
        pathParams,
        new TypeReference<GoLiveResponse>() {});
  }

  @NotNull
  public StreamRequest<GoLiveResponse> goLive(@NotNull String type, @NotNull String id)
      throws StreamException {
    return goLive(type, id, new GoLiveRequest());
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
        pathParams,
        new TypeReference<EndCallResponse>() {});
  }

  @NotNull
  public StreamRequest<EndCallResponse> endCall(@NotNull String type, @NotNull String id)
      throws StreamException {
    return endCall(type, id, new EndCallRequest());
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
        pathParams,
        new TypeReference<UpdateCallMembersResponse>() {});
  }

  @NotNull
  public StreamRequest<UpdateCallMembersResponse> updateCallMembers(
      @NotNull String type, @NotNull String id) throws StreamException {
    return updateCallMembers(type, id, new UpdateCallMembersRequest());
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
        pathParams,
        new TypeReference<MuteUsersResponse>() {});
  }

  @NotNull
  public StreamRequest<MuteUsersResponse> muteUsers(@NotNull String type, @NotNull String id)
      throws StreamException {
    return muteUsers(type, id, new MuteUsersRequest());
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
        pathParams,
        new TypeReference<PinResponse>() {});
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
        pathParams,
        new TypeReference<ListRecordingsResponse>() {});
  }

  @NotNull
  public StreamRequest<ListRecordingsResponse> listRecordings(
      @NotNull String type, @NotNull String id) throws StreamException {
    return listRecordings(type, id, new ListRecordingsRequest());
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
        pathParams,
        new TypeReference<GetCallReportResponse>() {});
  }

  @NotNull
  public StreamRequest<GetCallReportResponse> getCallReport(
      @NotNull String type, @NotNull String id) throws StreamException {
    return getCallReport(type, id, new GetCallReportRequest());
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
        pathParams,
        new TypeReference<StartRTMPBroadcastsResponse>() {});
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
        pathParams,
        new TypeReference<StopAllRTMPBroadcastsResponse>() {});
  }

  @NotNull
  public StreamRequest<StopAllRTMPBroadcastsResponse> stopAllRTMPBroadcasts(
      @NotNull String type, @NotNull String id) throws StreamException {
    return stopAllRTMPBroadcasts(type, id, new StopAllRTMPBroadcastsRequest());
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
        pathParams,
        new TypeReference<StopRTMPBroadcastsResponse>() {});
  }

  @NotNull
  public StreamRequest<StopRTMPBroadcastsResponse> stopRTMPBroadcast(
      @NotNull String type, @NotNull String id, @NotNull String name) throws StreamException {
    return stopRTMPBroadcast(type, id, name, new StopRTMPBroadcastRequest());
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
        pathParams,
        new TypeReference<StartHLSBroadcastingResponse>() {});
  }

  @NotNull
  public StreamRequest<StartHLSBroadcastingResponse> startHLSBroadcasting(
      @NotNull String type, @NotNull String id) throws StreamException {
    return startHLSBroadcasting(type, id, new StartHLSBroadcastingRequest());
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
        pathParams,
        new TypeReference<StartClosedCaptionsResponse>() {});
  }

  @NotNull
  public StreamRequest<StartClosedCaptionsResponse> startClosedCaptions(
      @NotNull String type, @NotNull String id) throws StreamException {
    return startClosedCaptions(type, id, new StartClosedCaptionsRequest());
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
        pathParams,
        new TypeReference<StartRecordingResponse>() {});
  }

  @NotNull
  public StreamRequest<StartRecordingResponse> startRecording(
      @NotNull String type, @NotNull String id) throws StreamException {
    return startRecording(type, id, new StartRecordingRequest());
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
        pathParams,
        new TypeReference<StartTranscriptionResponse>() {});
  }

  @NotNull
  public StreamRequest<StartTranscriptionResponse> startTranscription(
      @NotNull String type, @NotNull String id) throws StreamException {
    return startTranscription(type, id, new StartTranscriptionRequest());
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
        pathParams,
        new TypeReference<GetCallStatsResponse>() {});
  }

  @NotNull
  public StreamRequest<GetCallStatsResponse> getCallStats(
      @NotNull String type, @NotNull String id, @NotNull String session) throws StreamException {
    return getCallStats(type, id, session, new GetCallStatsRequest());
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
        pathParams,
        new TypeReference<StopHLSBroadcastingResponse>() {});
  }

  @NotNull
  public StreamRequest<StopHLSBroadcastingResponse> stopHLSBroadcasting(
      @NotNull String type, @NotNull String id) throws StreamException {
    return stopHLSBroadcasting(type, id, new StopHLSBroadcastingRequest());
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
        pathParams,
        new TypeReference<StopClosedCaptionsResponse>() {});
  }

  @NotNull
  public StreamRequest<StopClosedCaptionsResponse> stopClosedCaptions(
      @NotNull String type, @NotNull String id) throws StreamException {
    return stopClosedCaptions(type, id, new StopClosedCaptionsRequest());
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
        pathParams,
        new TypeReference<StopLiveResponse>() {});
  }

  @NotNull
  public StreamRequest<StopLiveResponse> stopLive(@NotNull String type, @NotNull String id)
      throws StreamException {
    return stopLive(type, id, new StopLiveRequest());
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
        pathParams,
        new TypeReference<StopRecordingResponse>() {});
  }

  @NotNull
  public StreamRequest<StopRecordingResponse> stopRecording(
      @NotNull String type, @NotNull String id) throws StreamException {
    return stopRecording(type, id, new StopRecordingRequest());
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
        pathParams,
        new TypeReference<StopTranscriptionResponse>() {});
  }

  @NotNull
  public StreamRequest<StopTranscriptionResponse> stopTranscription(
      @NotNull String type, @NotNull String id) throws StreamException {
    return stopTranscription(type, id, new StopTranscriptionRequest());
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
        pathParams,
        new TypeReference<ListTranscriptionsResponse>() {});
  }

  @NotNull
  public StreamRequest<ListTranscriptionsResponse> listTranscriptions(
      @NotNull String type, @NotNull String id) throws StreamException {
    return listTranscriptions(type, id, new ListTranscriptionsRequest());
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
        pathParams,
        new TypeReference<UnblockUserResponse>() {});
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
        pathParams,
        new TypeReference<UnpinResponse>() {});
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
        pathParams,
        new TypeReference<UpdateUserPermissionsResponse>() {});
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
        pathParams,
        new TypeReference<DeleteRecordingResponse>() {});
  }

  @NotNull
  public StreamRequest<DeleteRecordingResponse> deleteRecording(
      @NotNull String type, @NotNull String id, @NotNull String session, @NotNull String filename)
      throws StreamException {
    return deleteRecording(type, id, session, filename, new DeleteRecordingRequest());
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
        pathParams,
        new TypeReference<DeleteTranscriptionResponse>() {});
  }

  @NotNull
  public StreamRequest<DeleteTranscriptionResponse> deleteTranscription(
      @NotNull String type, @NotNull String id, @NotNull String session, @NotNull String filename)
      throws StreamException {
    return deleteTranscription(type, id, session, filename, new DeleteTranscriptionRequest());
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
        null,
        new TypeReference<QueryCallsResponse>() {});
  }

  @NotNull
  public StreamRequest<QueryCallsResponse> queryCalls() throws StreamException {
    return queryCalls(new QueryCallsRequest());
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
        null,
        new TypeReference<ListCallTypeResponse>() {});
  }

  @NotNull
  public StreamRequest<ListCallTypeResponse> listCallTypes() throws StreamException {
    return listCallTypes(new ListCallTypesRequest());
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
        null,
        new TypeReference<CreateCallTypeResponse>() {});
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
        pathParams,
        new TypeReference<Response>() {});
  }

  @NotNull
  public StreamRequest<Response> deleteCallType(@NotNull String name) throws StreamException {
    return deleteCallType(name, new DeleteCallTypeRequest());
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
        pathParams,
        new TypeReference<GetCallTypeResponse>() {});
  }

  @NotNull
  public StreamRequest<GetCallTypeResponse> getCallType(@NotNull String name)
      throws StreamException {
    return getCallType(name, new GetCallTypeRequest());
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
        pathParams,
        new TypeReference<UpdateCallTypeResponse>() {});
  }

  @NotNull
  public StreamRequest<UpdateCallTypeResponse> updateCallType(@NotNull String name)
      throws StreamException {
    return updateCallType(name, new UpdateCallTypeRequest());
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
        null,
        new TypeReference<GetEdgesResponse>() {});
  }

  @NotNull
  public StreamRequest<GetEdgesResponse> getEdges() throws StreamException {
    return getEdges(new GetEdgesRequest());
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
        null,
        new TypeReference<QueryAggregateCallStatsResponse>() {});
  }

  @NotNull
  public StreamRequest<QueryAggregateCallStatsResponse> queryAggregateCallStats()
      throws StreamException {
    return queryAggregateCallStats(new QueryAggregateCallStatsRequest());
  }

  @NotNull
  public Call call(String callType, String callID) {
    return new Call(callType, callID, client.video());
  }
}
