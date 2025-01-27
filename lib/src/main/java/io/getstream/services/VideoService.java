package io.getstream.services;

import io.getstream.models.*;
import io.getstream.services.framework.StreamRequest;
import java.util.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface VideoService {
  @POST("/api/v2/video/call/feedback")
  @NotNull
  StreamRequest<QueryUserFeedbackResponse> queryUserFeedback(
      @Nullable @Query("full") Boolean full,
      @Nullable @Body QueryUserFeedbackRequest queryUserFeedbackRequest);

  @POST("/api/v2/video/call/members")
  @NotNull
  StreamRequest<QueryCallMembersResponse> queryCallMembers(
      @NotNull @Body QueryCallMembersRequest queryCallMembersRequest);

  @POST("/api/v2/video/call/stats")
  @NotNull
  StreamRequest<QueryCallStatsResponse> queryCallStats(
      @Nullable @Body QueryCallStatsRequest queryCallStatsRequest);

  @GET("/api/v2/video/call/{type}/{id}")
  @NotNull
  StreamRequest<GetCallResponse> getCall(
      @NotNull @Path("type") String type,
      @NotNull @Path("id") String id,
      @Nullable @Query("members_limit") Integer membersLimit,
      @Nullable @Query("ring") Boolean ring,
      @Nullable @Query("notify") Boolean notify,
      @Nullable @Query("video") Boolean video);

  @PATCH("/api/v2/video/call/{type}/{id}")
  @NotNull
  StreamRequest<UpdateCallResponse> updateCall(
      @NotNull @Path("type") String type,
      @NotNull @Path("id") String id,
      @Nullable @Body UpdateCallRequest updateCallRequest);

  @POST("/api/v2/video/call/{type}/{id}")
  @NotNull
  StreamRequest<GetOrCreateCallResponse> getOrCreateCall(
      @NotNull @Path("type") String type,
      @NotNull @Path("id") String id,
      @Nullable @Body GetOrCreateCallRequest getOrCreateCallRequest);

  @POST("/api/v2/video/call/{type}/{id}/block")
  @NotNull
  StreamRequest<BlockUserResponse> blockUser(
      @NotNull @Path("type") String type,
      @NotNull @Path("id") String id,
      @NotNull @Body BlockUserRequest blockUserRequest);

  @POST("/api/v2/video/call/{type}/{id}/delete")
  @NotNull
  StreamRequest<DeleteCallResponse> deleteCall(
      @NotNull @Path("type") String type,
      @NotNull @Path("id") String id,
      @Nullable @Body DeleteCallRequest deleteCallRequest);

  @POST("/api/v2/video/call/{type}/{id}/event")
  @NotNull
  StreamRequest<SendCallEventResponse> sendCallEvent(
      @NotNull @Path("type") String type,
      @NotNull @Path("id") String id,
      @Nullable @Body SendCallEventRequest sendCallEventRequest);

  @POST("/api/v2/video/call/{type}/{id}/feedback/{session}")
  @NotNull
  StreamRequest<CollectUserFeedbackResponse> collectUserFeedback(
      @NotNull @Path("type") String type,
      @NotNull @Path("id") String id,
      @NotNull @Path("session") String session,
      @NotNull @Body CollectUserFeedbackRequest collectUserFeedbackRequest);

  @POST("/api/v2/video/call/{type}/{id}/go_live")
  @NotNull
  StreamRequest<GoLiveResponse> goLive(
      @NotNull @Path("type") String type,
      @NotNull @Path("id") String id,
      @Nullable @Body GoLiveRequest goLiveRequest);

  @POST("/api/v2/video/call/{type}/{id}/mark_ended")
  @NotNull
  StreamRequest<EndCallResponse> endCall(
      @NotNull @Path("type") String type, @NotNull @Path("id") String id);

  @POST("/api/v2/video/call/{type}/{id}/members")
  @NotNull
  StreamRequest<UpdateCallMembersResponse> updateCallMembers(
      @NotNull @Path("type") String type,
      @NotNull @Path("id") String id,
      @Nullable @Body UpdateCallMembersRequest updateCallMembersRequest);

  @POST("/api/v2/video/call/{type}/{id}/mute_users")
  @NotNull
  StreamRequest<MuteUsersResponse> muteUsers(
      @NotNull @Path("type") String type,
      @NotNull @Path("id") String id,
      @Nullable @Body MuteUsersRequest muteUsersRequest);

  @POST("/api/v2/video/call/{type}/{id}/pin")
  @NotNull
  StreamRequest<PinResponse> videoPin(
      @NotNull @Path("type") String type,
      @NotNull @Path("id") String id,
      @NotNull @Body PinRequest pinRequest);

  @GET("/api/v2/video/call/{type}/{id}/recordings")
  @NotNull
  StreamRequest<ListRecordingsResponse> listRecordings(
      @NotNull @Path("type") String type, @NotNull @Path("id") String id);

  @GET("/api/v2/video/call/{type}/{id}/report")
  @NotNull
  StreamRequest<GetCallReportResponse> getCallReport(
      @NotNull @Path("type") String type,
      @NotNull @Path("id") String id,
      @Nullable @Query("session_id") String sessionID);

  @POST("/api/v2/video/call/{type}/{id}/rtmp_broadcasts")
  @NotNull
  StreamRequest<StartRTMPBroadcastsResponse> startRTMPBroadcasts(
      @NotNull @Path("type") String type,
      @NotNull @Path("id") String id,
      @NotNull @Body StartRTMPBroadcastsRequest startRTMPBroadcastsRequest);

  @POST("/api/v2/video/call/{type}/{id}/rtmp_broadcasts/stop")
  @NotNull
  StreamRequest<StopAllRTMPBroadcastsResponse> stopAllRTMPBroadcasts(
      @NotNull @Path("type") String type, @NotNull @Path("id") String id);

  @POST("/api/v2/video/call/{type}/{id}/rtmp_broadcasts/{name}/stop")
  @NotNull
  StreamRequest<StopRTMPBroadcastsResponse> stopRTMPBroadcast(
      @NotNull @Path("type") String type,
      @NotNull @Path("id") String id,
      @NotNull @Path("name") String name,
      @Nullable @Body StopRTMPBroadcastsRequest stopRTMPBroadcastsRequest);

  @POST("/api/v2/video/call/{type}/{id}/start_broadcasting")
  @NotNull
  StreamRequest<StartHLSBroadcastingResponse> startHLSBroadcasting(
      @NotNull @Path("type") String type, @NotNull @Path("id") String id);

  @POST("/api/v2/video/call/{type}/{id}/start_closed_captions")
  @NotNull
  StreamRequest<StartClosedCaptionsResponse> startClosedCaptions(
      @NotNull @Path("type") String type,
      @NotNull @Path("id") String id,
      @Nullable @Body StartClosedCaptionsRequest startClosedCaptionsRequest);

  @POST("/api/v2/video/call/{type}/{id}/start_recording")
  @NotNull
  StreamRequest<StartRecordingResponse> startRecording(
      @NotNull @Path("type") String type,
      @NotNull @Path("id") String id,
      @Nullable @Body StartRecordingRequest startRecordingRequest);

  @POST("/api/v2/video/call/{type}/{id}/start_transcription")
  @NotNull
  StreamRequest<StartTranscriptionResponse> startTranscription(
      @NotNull @Path("type") String type,
      @NotNull @Path("id") String id,
      @Nullable @Body StartTranscriptionRequest startTranscriptionRequest);

  @GET("/api/v2/video/call/{type}/{id}/stats/{session}")
  @NotNull
  StreamRequest<GetCallStatsResponse> getCallStats(
      @NotNull @Path("type") String type,
      @NotNull @Path("id") String id,
      @NotNull @Path("session") String session);

  @POST("/api/v2/video/call/{type}/{id}/stop_broadcasting")
  @NotNull
  StreamRequest<StopHLSBroadcastingResponse> stopHLSBroadcasting(
      @NotNull @Path("type") String type, @NotNull @Path("id") String id);

  @POST("/api/v2/video/call/{type}/{id}/stop_closed_captions")
  @NotNull
  StreamRequest<StopClosedCaptionsResponse> stopClosedCaptions(
      @NotNull @Path("type") String type,
      @NotNull @Path("id") String id,
      @Nullable @Body StopClosedCaptionsRequest stopClosedCaptionsRequest);

  @POST("/api/v2/video/call/{type}/{id}/stop_live")
  @NotNull
  StreamRequest<StopLiveResponse> stopLive(
      @NotNull @Path("type") String type,
      @NotNull @Path("id") String id,
      @Nullable @Body StopLiveRequest stopLiveRequest);

  @POST("/api/v2/video/call/{type}/{id}/stop_recording")
  @NotNull
  StreamRequest<StopRecordingResponse> stopRecording(
      @NotNull @Path("type") String type, @NotNull @Path("id") String id);

  @POST("/api/v2/video/call/{type}/{id}/stop_transcription")
  @NotNull
  StreamRequest<StopTranscriptionResponse> stopTranscription(
      @NotNull @Path("type") String type,
      @NotNull @Path("id") String id,
      @Nullable @Body StopTranscriptionRequest stopTranscriptionRequest);

  @GET("/api/v2/video/call/{type}/{id}/transcriptions")
  @NotNull
  StreamRequest<ListTranscriptionsResponse> listTranscriptions(
      @NotNull @Path("type") String type, @NotNull @Path("id") String id);

  @POST("/api/v2/video/call/{type}/{id}/unblock")
  @NotNull
  StreamRequest<UnblockUserResponse> unblockUser(
      @NotNull @Path("type") String type,
      @NotNull @Path("id") String id,
      @NotNull @Body UnblockUserRequest unblockUserRequest);

  @POST("/api/v2/video/call/{type}/{id}/unpin")
  @NotNull
  StreamRequest<UnpinResponse> videoUnpin(
      @NotNull @Path("type") String type,
      @NotNull @Path("id") String id,
      @NotNull @Body UnpinRequest unpinRequest);

  @POST("/api/v2/video/call/{type}/{id}/user_permissions")
  @NotNull
  StreamRequest<UpdateUserPermissionsResponse> updateUserPermissions(
      @NotNull @Path("type") String type,
      @NotNull @Path("id") String id,
      @NotNull @Body UpdateUserPermissionsRequest updateUserPermissionsRequest);

  @DELETE("/api/v2/video/call/{type}/{id}/{session}/recordings/{filename}")
  @NotNull
  StreamRequest<DeleteRecordingResponse> deleteRecording(
      @NotNull @Path("type") String type,
      @NotNull @Path("id") String id,
      @NotNull @Path("session") String session,
      @NotNull @Path("filename") String filename);

  @DELETE("/api/v2/video/call/{type}/{id}/{session}/transcriptions/{filename}")
  @NotNull
  StreamRequest<DeleteTranscriptionResponse> deleteTranscription(
      @NotNull @Path("type") String type,
      @NotNull @Path("id") String id,
      @NotNull @Path("session") String session,
      @NotNull @Path("filename") String filename);

  @POST("/api/v2/video/calls")
  @NotNull
  StreamRequest<QueryCallsResponse> queryCalls(@Nullable @Body QueryCallsRequest queryCallsRequest);

  @GET("/api/v2/video/calltypes")
  @NotNull
  StreamRequest<ListCallTypeResponse> listCallTypes();

  @POST("/api/v2/video/calltypes")
  @NotNull
  StreamRequest<CreateCallTypeResponse> createCallType(
      @NotNull @Body CreateCallTypeRequest createCallTypeRequest);

  @DELETE("/api/v2/video/calltypes/{name}")
  @NotNull
  StreamRequest<Response> deleteCallType(@NotNull @Path("name") String name);

  @GET("/api/v2/video/calltypes/{name}")
  @NotNull
  StreamRequest<GetCallTypeResponse> getCallType(@NotNull @Path("name") String name);

  @PUT("/api/v2/video/calltypes/{name}")
  @NotNull
  StreamRequest<UpdateCallTypeResponse> updateCallType(
      @NotNull @Path("name") String name,
      @Nullable @Body UpdateCallTypeRequest updateCallTypeRequest);

  @GET("/api/v2/video/edges")
  @NotNull
  StreamRequest<GetEdgesResponse> getEdges();

  @POST("/api/v2/video/stats")
  @NotNull
  StreamRequest<QueryAggregateCallStatsResponse> queryAggregateCallStats(
      @Nullable @Body QueryAggregateCallStatsRequest queryAggregateCallStatsRequest);
}
