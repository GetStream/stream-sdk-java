package io.getstream.services;

import io.getstream.models.*;
import java.util.*;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface VideoService {
  @POST("/api/v2/video/call/members")
  @NotNull
  Call<QueryCallMembersResponse> queryCallMembers(
      @NotNull @Body QueryCallMembersRequest queryCallMembersRequest);

  @POST("/api/v2/video/call/stats")
  @NotNull
  Call<QueryCallStatsResponse> queryCallStats(
      @Nullable @Body QueryCallStatsRequest queryCallStatsRequest);

  @GET("/api/v2/video/call/{type}/{id}")
  @NotNull
  Call<GetCallResponse> getCall(
      @NotNull @Path("type") String type,
      @NotNull @Path("id") String id,
      @Nullable @Query("members_limit") Integer membersLimit,
      @Nullable @Query("ring") Boolean ring,
      @Nullable @Query("notify") Boolean notify);

  @PATCH("/api/v2/video/call/{type}/{id}")
  @NotNull
  Call<UpdateCallResponse> updateCall(
      @NotNull @Path("type") String type,
      @NotNull @Path("id") String id,
      @Nullable @Body UpdateCallRequest updateCallRequest);

  @POST("/api/v2/video/call/{type}/{id}")
  @NotNull
  Call<GetOrCreateCallResponse> getOrCreateCall(
      @NotNull @Path("type") String type,
      @NotNull @Path("id") String id,
      @Nullable @Body GetOrCreateCallRequest getOrCreateCallRequest);

  @POST("/api/v2/video/call/{type}/{id}/block")
  @NotNull
  Call<BlockUserResponse> blockUser(
      @NotNull @Path("type") String type,
      @NotNull @Path("id") String id,
      @NotNull @Body BlockUserRequest blockUserRequest);

  @POST("/api/v2/video/call/{type}/{id}/delete")
  @NotNull
  Call<DeleteCallResponse> deleteCall(
      @NotNull @Path("type") String type,
      @NotNull @Path("id") String id,
      @Nullable @Body DeleteCallRequest deleteCallRequest);

  @POST("/api/v2/video/call/{type}/{id}/event")
  @NotNull
  Call<SendCallEventResponse> sendCallEvent(
      @NotNull @Path("type") String type,
      @NotNull @Path("id") String id,
      @Nullable @Body SendCallEventRequest sendCallEventRequest);

  @POST("/api/v2/video/call/{type}/{id}/feedback/{session}")
  @NotNull
  Call<CollectUserFeedbackResponse> collectUserFeedback(
      @NotNull @Path("type") String type,
      @NotNull @Path("id") String id,
      @NotNull @Path("session") String session,
      @NotNull @Body CollectUserFeedbackRequest collectUserFeedbackRequest);

  @POST("/api/v2/video/call/{type}/{id}/go_live")
  @NotNull
  Call<GoLiveResponse> goLive(
      @NotNull @Path("type") String type,
      @NotNull @Path("id") String id,
      @Nullable @Body GoLiveRequest goLiveRequest);

  @POST("/api/v2/video/call/{type}/{id}/mark_ended")
  @NotNull
  Call<EndCallResponse> endCall(@NotNull @Path("type") String type, @NotNull @Path("id") String id);

  @POST("/api/v2/video/call/{type}/{id}/members")
  @NotNull
  Call<UpdateCallMembersResponse> updateCallMembers(
      @NotNull @Path("type") String type,
      @NotNull @Path("id") String id,
      @Nullable @Body UpdateCallMembersRequest updateCallMembersRequest);

  @POST("/api/v2/video/call/{type}/{id}/mute_users")
  @NotNull
  Call<MuteUsersResponse> muteUsers(
      @NotNull @Path("type") String type,
      @NotNull @Path("id") String id,
      @Nullable @Body MuteUsersRequest muteUsersRequest);

  @POST("/api/v2/video/call/{type}/{id}/pin")
  @NotNull
  Call<PinResponse> videoPin(
      @NotNull @Path("type") String type,
      @NotNull @Path("id") String id,
      @NotNull @Body PinRequest pinRequest);

  @GET("/api/v2/video/call/{type}/{id}/recordings")
  @NotNull
  Call<ListRecordingsResponse> listRecordings(
      @NotNull @Path("type") String type, @NotNull @Path("id") String id);

  @POST("/api/v2/video/call/{type}/{id}/start_broadcasting")
  @NotNull
  Call<StartHLSBroadcastingResponse> startHLSBroadcasting(
      @NotNull @Path("type") String type, @NotNull @Path("id") String id);

  @POST("/api/v2/video/call/{type}/{id}/start_recording")
  @NotNull
  Call<StartRecordingResponse> startRecording(
      @NotNull @Path("type") String type,
      @NotNull @Path("id") String id,
      @Nullable @Body StartRecordingRequest startRecordingRequest);

  @POST("/api/v2/video/call/{type}/{id}/start_transcription")
  @NotNull
  Call<StartTranscriptionResponse> startTranscription(
      @NotNull @Path("type") String type,
      @NotNull @Path("id") String id,
      @Nullable @Body StartTranscriptionRequest startTranscriptionRequest);

  @GET("/api/v2/video/call/{type}/{id}/stats/{session}")
  @NotNull
  Call<GetCallStatsResponse> getCallStats(
      @NotNull @Path("type") String type,
      @NotNull @Path("id") String id,
      @NotNull @Path("session") String session);

  @POST("/api/v2/video/call/{type}/{id}/stop_broadcasting")
  @NotNull
  Call<StopHLSBroadcastingResponse> stopHLSBroadcasting(
      @NotNull @Path("type") String type, @NotNull @Path("id") String id);

  @POST("/api/v2/video/call/{type}/{id}/stop_live")
  @NotNull
  Call<StopLiveResponse> stopLive(
      @NotNull @Path("type") String type, @NotNull @Path("id") String id);

  @POST("/api/v2/video/call/{type}/{id}/stop_recording")
  @NotNull
  Call<StopRecordingResponse> stopRecording(
      @NotNull @Path("type") String type, @NotNull @Path("id") String id);

  @POST("/api/v2/video/call/{type}/{id}/stop_transcription")
  @NotNull
  Call<StopTranscriptionResponse> stopTranscription(
      @NotNull @Path("type") String type, @NotNull @Path("id") String id);

  @GET("/api/v2/video/call/{type}/{id}/transcriptions")
  @NotNull
  Call<ListTranscriptionsResponse> listTranscriptions(
      @NotNull @Path("type") String type, @NotNull @Path("id") String id);

  @POST("/api/v2/video/call/{type}/{id}/unblock")
  @NotNull
  Call<UnblockUserResponse> unblockUser(
      @NotNull @Path("type") String type,
      @NotNull @Path("id") String id,
      @NotNull @Body UnblockUserRequest unblockUserRequest);

  @POST("/api/v2/video/call/{type}/{id}/unpin")
  @NotNull
  Call<UnpinResponse> videoUnpin(
      @NotNull @Path("type") String type,
      @NotNull @Path("id") String id,
      @NotNull @Body UnpinRequest unpinRequest);

  @POST("/api/v2/video/call/{type}/{id}/user_permissions")
  @NotNull
  Call<UpdateUserPermissionsResponse> updateUserPermissions(
      @NotNull @Path("type") String type,
      @NotNull @Path("id") String id,
      @NotNull @Body UpdateUserPermissionsRequest updateUserPermissionsRequest);

  @DELETE("/api/v2/video/call/{type}/{id}/{session}/recordings/{filename}")
  @NotNull
  Call<DeleteRecordingResponse> deleteRecording(
      @NotNull @Path("type") String type,
      @NotNull @Path("id") String id,
      @NotNull @Path("session") String session,
      @NotNull @Path("filename") String filename);

  @DELETE("/api/v2/video/call/{type}/{id}/{session}/transcriptions/{filename}")
  @NotNull
  Call<DeleteTranscriptionResponse> deleteTranscription(
      @NotNull @Path("type") String type,
      @NotNull @Path("id") String id,
      @NotNull @Path("session") String session,
      @NotNull @Path("filename") String filename);

  @POST("/api/v2/video/calls")
  @NotNull
  Call<QueryCallsResponse> queryCalls(@Nullable @Body QueryCallsRequest queryCallsRequest);

  @GET("/api/v2/video/calltypes")
  @NotNull
  Call<ListCallTypeResponse> listCallTypes();

  @POST("/api/v2/video/calltypes")
  @NotNull
  Call<CreateCallTypeResponse> createCallType(
      @NotNull @Body CreateCallTypeRequest createCallTypeRequest);

  @DELETE("/api/v2/video/calltypes/{name}")
  @NotNull
  Call<Response> deleteCallType(@NotNull @Path("name") String name);

  @GET("/api/v2/video/calltypes/{name}")
  @NotNull
  Call<GetCallTypeResponse> getCallType(@NotNull @Path("name") String name);

  @PUT("/api/v2/video/calltypes/{name}")
  @NotNull
  Call<UpdateCallTypeResponse> updateCallType(
      @NotNull @Path("name") String name,
      @Nullable @Body UpdateCallTypeRequest updateCallTypeRequest);

  @GET("/api/v2/video/edges")
  @NotNull
  Call<GetEdgesResponse> getEdges();
}
