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

public interface ChatService {
  @POST("/api/v2/chat/campaigns/query")
  @NotNull
  StreamRequest<QueryCampaignsResponse> queryCampaigns(
      @Nullable @Body QueryCampaignsRequest queryCampaignsRequest);

  @GET("/api/v2/chat/campaigns/{id}")
  @NotNull
  StreamRequest<GetCampaignResponse> getCampaign(@NotNull @Path("id") String id);

  @POST("/api/v2/chat/campaigns/{id}/start")
  @NotNull
  StreamRequest<StartCampaignResponse> startCampaign(
      @NotNull @Path("id") String id, @Nullable @Body StartCampaignRequest startCampaignRequest);

  @POST("/api/v2/chat/campaigns/{id}/stop")
  @NotNull
  StreamRequest<CampaignResponse> scheduleCampaign(
      @NotNull @Path("id") String id, @Nullable @Body StopCampaignRequest stopCampaignRequest);

  @POST("/api/v2/chat/channels")
  @NotNull
  StreamRequest<QueryChannelsResponse> queryChannels(
      @Nullable @Body QueryChannelsRequest queryChannelsRequest);

  @POST("/api/v2/chat/channels/delete")
  @NotNull
  StreamRequest<DeleteChannelsResponse> deleteChannels(
      @NotNull @Body DeleteChannelsRequest deleteChannelsRequest);

  @POST("/api/v2/chat/channels/read")
  @NotNull
  StreamRequest<MarkReadResponse> markChannelsRead(
      @Nullable @Body MarkChannelsReadRequest markChannelsReadRequest);

  @POST("/api/v2/chat/channels/{type}/query")
  @NotNull
  StreamRequest<ChannelStateResponse> getOrCreateDistinctChannel(
      @NotNull @Path("type") String type,
      @Nullable @Body ChannelGetOrCreateRequest channelGetOrCreateRequest);

  @DELETE("/api/v2/chat/channels/{type}/{id}")
  @NotNull
  StreamRequest<DeleteChannelResponse> deleteChannel(
      @NotNull @Path("type") String type,
      @NotNull @Path("id") String id,
      @Nullable @Query("hard_delete") Boolean hardDelete);

  @PATCH("/api/v2/chat/channels/{type}/{id}")
  @NotNull
  StreamRequest<UpdateChannelPartialResponse> updateChannelPartial(
      @NotNull @Path("type") String type,
      @NotNull @Path("id") String id,
      @Nullable @Body UpdateChannelPartialRequest updateChannelPartialRequest);

  @POST("/api/v2/chat/channels/{type}/{id}")
  @NotNull
  StreamRequest<UpdateChannelResponse> updateChannel(
      @NotNull @Path("type") String type,
      @NotNull @Path("id") String id,
      @Nullable @Body UpdateChannelRequest updateChannelRequest);

  @POST("/api/v2/chat/channels/{type}/{id}/event")
  @NotNull
  StreamRequest<EventResponse> sendEvent(
      @NotNull @Path("type") String type,
      @NotNull @Path("id") String id,
      @NotNull @Body SendEventRequest sendEventRequest);

  @DELETE("/api/v2/chat/channels/{type}/{id}/file")
  @NotNull
  StreamRequest<Response> deleteFile(
      @NotNull @Path("type") String type,
      @NotNull @Path("id") String id,
      @Nullable @Query("url") String url);

  @POST("/api/v2/chat/channels/{type}/{id}/file")
  @NotNull
  StreamRequest<FileUploadResponse> uploadFile(
      @NotNull @Path("type") String type,
      @NotNull @Path("id") String id,
      @Nullable @Body FileUploadRequest fileUploadRequest);

  @POST("/api/v2/chat/channels/{type}/{id}/hide")
  @NotNull
  StreamRequest<HideChannelResponse> hideChannel(
      @NotNull @Path("type") String type,
      @NotNull @Path("id") String id,
      @Nullable @Body HideChannelRequest hideChannelRequest);

  @DELETE("/api/v2/chat/channels/{type}/{id}/image")
  @NotNull
  StreamRequest<Response> deleteImage(
      @NotNull @Path("type") String type,
      @NotNull @Path("id") String id,
      @Nullable @Query("url") String url);

  @POST("/api/v2/chat/channels/{type}/{id}/image")
  @NotNull
  StreamRequest<ImageUploadResponse> uploadImage(
      @NotNull @Path("type") String type,
      @NotNull @Path("id") String id,
      @Nullable @Body ImageUploadRequest imageUploadRequest);

  @PATCH("/api/v2/chat/channels/{type}/{id}/member/{user_id}")
  @NotNull
  StreamRequest<UpdateMemberPartialResponse> updateMemberPartial(
      @NotNull @Path("user_id") String userID,
      @NotNull @Path("type") String type,
      @NotNull @Path("id") String id,
      @Nullable @Body UpdateMemberPartialRequest updateMemberPartialRequest);

  @POST("/api/v2/chat/channels/{type}/{id}/message")
  @NotNull
  StreamRequest<SendMessageResponse> sendMessage(
      @NotNull @Path("type") String type,
      @NotNull @Path("id") String id,
      @NotNull @Body SendMessageRequest sendMessageRequest);

  @GET("/api/v2/chat/channels/{type}/{id}/messages")
  @NotNull
  StreamRequest<GetManyMessagesResponse> getManyMessages(
      @NotNull @Path("type") String type,
      @NotNull @Path("id") String id,
      @NotNull @Query("ids") List<String> ids);

  @POST("/api/v2/chat/channels/{type}/{id}/query")
  @NotNull
  StreamRequest<ChannelStateResponse> getOrCreateChannel(
      @NotNull @Path("type") String type,
      @NotNull @Path("id") String id,
      @Nullable @Body ChannelGetOrCreateRequest channelGetOrCreateRequest);

  @POST("/api/v2/chat/channels/{type}/{id}/read")
  @NotNull
  StreamRequest<MarkReadResponse> markRead(
      @NotNull @Path("type") String type,
      @NotNull @Path("id") String id,
      @Nullable @Body MarkReadRequest markReadRequest);

  @POST("/api/v2/chat/channels/{type}/{id}/show")
  @NotNull
  StreamRequest<ShowChannelResponse> showChannel(
      @NotNull @Path("type") String type,
      @NotNull @Path("id") String id,
      @Nullable @Body ShowChannelRequest showChannelRequest);

  @POST("/api/v2/chat/channels/{type}/{id}/truncate")
  @NotNull
  StreamRequest<TruncateChannelResponse> truncateChannel(
      @NotNull @Path("type") String type,
      @NotNull @Path("id") String id,
      @Nullable @Body TruncateChannelRequest truncateChannelRequest);

  @POST("/api/v2/chat/channels/{type}/{id}/unread")
  @NotNull
  StreamRequest<Response> markUnread(
      @NotNull @Path("type") String type,
      @NotNull @Path("id") String id,
      @Nullable @Body MarkUnreadRequest markUnreadRequest);

  @GET("/api/v2/chat/channeltypes")
  @NotNull
  StreamRequest<ListChannelTypesResponse> listChannelTypes();

  @POST("/api/v2/chat/channeltypes")
  @NotNull
  StreamRequest<CreateChannelTypeResponse> createChannelType(
      @NotNull @Body CreateChannelTypeRequest createChannelTypeRequest);

  @DELETE("/api/v2/chat/channeltypes/{name}")
  @NotNull
  StreamRequest<Response> deleteChannelType(@NotNull @Path("name") String name);

  @GET("/api/v2/chat/channeltypes/{name}")
  @NotNull
  StreamRequest<GetChannelTypeResponse> getChannelType(@NotNull @Path("name") String name);

  @PUT("/api/v2/chat/channeltypes/{name}")
  @NotNull
  StreamRequest<UpdateChannelTypeResponse> updateChannelType(
      @NotNull @Path("name") String name,
      @NotNull @Body UpdateChannelTypeRequest updateChannelTypeRequest);

  @GET("/api/v2/chat/commands")
  @NotNull
  StreamRequest<ListCommandsResponse> listCommands();

  @POST("/api/v2/chat/commands")
  @NotNull
  StreamRequest<CreateCommandResponse> createCommand(
      @NotNull @Body CreateCommandRequest createCommandRequest);

  @DELETE("/api/v2/chat/commands/{name}")
  @NotNull
  StreamRequest<DeleteCommandResponse> deleteCommand(@NotNull @Path("name") String name);

  @GET("/api/v2/chat/commands/{name}")
  @NotNull
  StreamRequest<GetCommandResponse> getCommand(@NotNull @Path("name") String name);

  @PUT("/api/v2/chat/commands/{name}")
  @NotNull
  StreamRequest<UpdateCommandResponse> updateCommand(
      @NotNull @Path("name") String name, @NotNull @Body UpdateCommandRequest updateCommandRequest);

  @POST("/api/v2/chat/export_channels")
  @NotNull
  StreamRequest<ExportChannelsResponse> exportChannels(
      @NotNull @Body ExportChannelsRequest exportChannelsRequest);

  @GET("/api/v2/chat/export_channels/{id}")
  @NotNull
  StreamRequest<GetExportChannelsStatusResponse> getExportChannelsStatus(
      @NotNull @Path("id") String id);

  @GET("/api/v2/chat/members")
  @NotNull
  StreamRequest<MembersResponse> queryMembers(
      @Nullable @Query("payload") QueryMembersPayload payload);

  @POST("/api/v2/chat/messages/history")
  @NotNull
  StreamRequest<QueryMessageHistoryResponse> queryMessageHistory(
      @NotNull @Body QueryMessageHistoryRequest queryMessageHistoryRequest);

  @DELETE("/api/v2/chat/messages/{id}")
  @NotNull
  StreamRequest<DeleteMessageResponse> deleteMessage(
      @NotNull @Path("id") String id,
      @Nullable @Query("hard") Boolean hard,
      @Nullable @Query("deleted_by") String deletedBy);

  @GET("/api/v2/chat/messages/{id}")
  @NotNull
  StreamRequest<GetMessageResponse> getMessage(
      @NotNull @Path("id") String id,
      @Nullable @Query("show_deleted_message") Boolean showDeletedMessage);

  @POST("/api/v2/chat/messages/{id}")
  @NotNull
  StreamRequest<UpdateMessageResponse> updateMessage(
      @NotNull @Path("id") String id, @NotNull @Body UpdateMessageRequest updateMessageRequest);

  @PUT("/api/v2/chat/messages/{id}")
  @NotNull
  StreamRequest<UpdateMessagePartialResponse> updateMessagePartial(
      @NotNull @Path("id") String id,
      @Nullable @Body UpdateMessagePartialRequest updateMessagePartialRequest);

  @POST("/api/v2/chat/messages/{id}/action")
  @NotNull
  StreamRequest<MessageResponse> runMessageAction(
      @NotNull @Path("id") String id, @NotNull @Body MessageActionRequest messageActionRequest);

  @POST("/api/v2/chat/messages/{id}/commit")
  @NotNull
  StreamRequest<MessageResponse> commitMessage(
      @NotNull @Path("id") String id, @Nullable @Body CommitMessageRequest commitMessageRequest);

  @POST("/api/v2/chat/messages/{id}/reaction")
  @NotNull
  StreamRequest<SendReactionResponse> sendReaction(
      @NotNull @Path("id") String id, @NotNull @Body SendReactionRequest sendReactionRequest);

  @DELETE("/api/v2/chat/messages/{id}/reaction/{type}")
  @NotNull
  StreamRequest<DeleteReactionResponse> deleteReaction(
      @NotNull @Path("id") String id,
      @NotNull @Path("type") String type,
      @Nullable @Query("user_id") String userID);

  @GET("/api/v2/chat/messages/{id}/reactions")
  @NotNull
  StreamRequest<GetReactionsResponse> getReactions(
      @NotNull @Path("id") String id,
      @Nullable @Query("limit") Integer limit,
      @Nullable @Query("offset") Integer offset);

  @POST("/api/v2/chat/messages/{id}/reactions")
  @NotNull
  StreamRequest<QueryReactionsResponse> queryReactions(
      @NotNull @Path("id") String id, @Nullable @Body QueryReactionsRequest queryReactionsRequest);

  @POST("/api/v2/chat/messages/{id}/translate")
  @NotNull
  StreamRequest<MessageResponse> translateMessage(
      @NotNull @Path("id") String id,
      @NotNull @Body TranslateMessageRequest translateMessageRequest);

  @POST("/api/v2/chat/messages/{id}/undelete")
  @NotNull
  StreamRequest<UpdateMessageResponse> undeleteMessage(
      @NotNull @Path("id") String id, @NotNull @Body UpdateMessageRequest updateMessageRequest);

  @POST("/api/v2/chat/messages/{message_id}/polls/{poll_id}/vote")
  @NotNull
  StreamRequest<PollVoteResponse> castPollVote(
      @NotNull @Path("message_id") String messageID,
      @NotNull @Path("poll_id") String pollID,
      @Nullable @Body CastPollVoteRequest castPollVoteRequest);

  @DELETE("/api/v2/chat/messages/{message_id}/polls/{poll_id}/vote/{vote_id}")
  @NotNull
  StreamRequest<PollVoteResponse> removePollVote(
      @NotNull @Path("message_id") String messageID,
      @NotNull @Path("poll_id") String pollID,
      @NotNull @Path("vote_id") String voteID,
      @Nullable @Query("user_id") String userID);

  @GET("/api/v2/chat/messages/{parent_id}/replies")
  @NotNull
  StreamRequest<GetRepliesResponse> getReplies(
      @NotNull @Path("parent_id") String parentID,
      @Nullable @Query("limit") Integer limit,
      @Nullable @Query("offset") Integer offset,
      @Nullable @Query("id_gte") String idGte,
      @Nullable @Query("id_gt") String idGt,
      @Nullable @Query("id_lte") String idLte,
      @Nullable @Query("id_lt") String idLt,
      @Nullable @Query("created_at_after_or_equal") Date createdAtAfterOrEqual,
      @Nullable @Query("created_at_after") Date createdAtAfter,
      @Nullable @Query("created_at_before_or_equal") Date createdAtBeforeOrEqual,
      @Nullable @Query("created_at_before") Date createdAtBefore,
      @Nullable @Query("id_around") String idAround,
      @Nullable @Query("created_at_around") Date createdAtAround,
      @Nullable @Query("sort") List<SortParamRequest> sort);

  @GET("/api/v2/chat/moderation/flags/message")
  @NotNull
  StreamRequest<QueryMessageFlagsResponse> queryMessageFlags(
      @Nullable @Query("payload") QueryMessageFlagsPayload payload);

  @POST("/api/v2/chat/moderation/mute/channel")
  @NotNull
  StreamRequest<MuteChannelResponse> muteChannel(
      @Nullable @Body MuteChannelRequest muteChannelRequest);

  @POST("/api/v2/chat/moderation/unmute/channel")
  @NotNull
  StreamRequest<UnmuteResponse> unmuteChannel(
      @Nullable @Body UnmuteChannelRequest unmuteChannelRequest);

  @POST("/api/v2/chat/polls")
  @NotNull
  StreamRequest<PollResponse> createPoll(@NotNull @Body CreatePollRequest createPollRequest);

  @PUT("/api/v2/chat/polls")
  @NotNull
  StreamRequest<PollResponse> updatePoll(@NotNull @Body UpdatePollRequest updatePollRequest);

  @POST("/api/v2/chat/polls/query")
  @NotNull
  StreamRequest<QueryPollsResponse> queryPolls(
      @Nullable @Query("user_id") String userID,
      @Nullable @Body QueryPollsRequest queryPollsRequest);

  @DELETE("/api/v2/chat/polls/{poll_id}")
  @NotNull
  StreamRequest<Response> deletePoll(
      @NotNull @Path("poll_id") String pollID, @Nullable @Query("user_id") String userID);

  @GET("/api/v2/chat/polls/{poll_id}")
  @NotNull
  StreamRequest<PollResponse> getPoll(
      @NotNull @Path("poll_id") String pollID, @Nullable @Query("user_id") String userID);

  @PATCH("/api/v2/chat/polls/{poll_id}")
  @NotNull
  StreamRequest<PollResponse> updatePollPartial(
      @NotNull @Path("poll_id") String pollID,
      @Nullable @Body UpdatePollPartialRequest updatePollPartialRequest);

  @POST("/api/v2/chat/polls/{poll_id}/options")
  @NotNull
  StreamRequest<PollOptionResponse> createPollOption(
      @NotNull @Path("poll_id") String pollID,
      @NotNull @Body CreatePollOptionRequest createPollOptionRequest);

  @PUT("/api/v2/chat/polls/{poll_id}/options")
  @NotNull
  StreamRequest<PollOptionResponse> updatePollOption(
      @NotNull @Path("poll_id") String pollID,
      @NotNull @Body UpdatePollOptionRequest updatePollOptionRequest);

  @DELETE("/api/v2/chat/polls/{poll_id}/options/{option_id}")
  @NotNull
  StreamRequest<Response> deletePollOption(
      @NotNull @Path("poll_id") String pollID,
      @NotNull @Path("option_id") String optionID,
      @Nullable @Query("user_id") String userID);

  @GET("/api/v2/chat/polls/{poll_id}/options/{option_id}")
  @NotNull
  StreamRequest<PollOptionResponse> getPollOption(
      @NotNull @Path("poll_id") String pollID,
      @NotNull @Path("option_id") String optionID,
      @Nullable @Query("user_id") String userID);

  @POST("/api/v2/chat/polls/{poll_id}/votes")
  @NotNull
  StreamRequest<PollVotesResponse> queryPollVotes(
      @NotNull @Path("poll_id") String pollID,
      @Nullable @Query("user_id") String userID,
      @Nullable @Body QueryPollVotesRequest queryPollVotesRequest);

  @POST("/api/v2/chat/push_preferences")
  @NotNull
  StreamRequest<UpsertPushPreferencesResponse> updatePushNotificationPreferences(
      @NotNull @Body UpsertPushPreferencesRequest upsertPushPreferencesRequest);

  @GET("/api/v2/chat/query_banned_users")
  @NotNull
  StreamRequest<QueryBannedUsersResponse> queryBannedUsers(
      @Nullable @Query("payload") QueryBannedUsersPayload payload);

  @GET("/api/v2/chat/search")
  @NotNull
  StreamRequest<SearchResponse> search(@Nullable @Query("payload") SearchPayload payload);

  @POST("/api/v2/chat/segments/query")
  @NotNull
  StreamRequest<QuerySegmentsResponse> querySegments(
      @NotNull @Body QuerySegmentsRequest querySegmentsRequest);

  @DELETE("/api/v2/chat/segments/{id}")
  @NotNull
  StreamRequest<Response> deleteSegment(@NotNull @Path("id") String id);

  @GET("/api/v2/chat/segments/{id}")
  @NotNull
  StreamRequest<GetSegmentResponse> getSegment(@NotNull @Path("id") String id);

  @POST("/api/v2/chat/segments/{id}/deletetargets")
  @NotNull
  StreamRequest<Response> deleteSegmentTargets(
      @NotNull @Path("id") String id,
      @NotNull @Body DeleteSegmentTargetsRequest deleteSegmentTargetsRequest);

  @GET("/api/v2/chat/segments/{id}/target/{target_id}")
  @NotNull
  StreamRequest<Response> segmentTargetExists(
      @NotNull @Path("id") String id, @NotNull @Path("target_id") String targetID);

  @POST("/api/v2/chat/segments/{id}/targets/query")
  @NotNull
  StreamRequest<QuerySegmentTargetsResponse> querySegmentTargets(
      @NotNull @Path("id") String id,
      @Nullable @Body QuerySegmentTargetsRequest querySegmentTargetsRequest);

  @POST("/api/v2/chat/threads")
  @NotNull
  StreamRequest<QueryThreadsResponse> queryThreads(
      @Nullable @Body QueryThreadsRequest queryThreadsRequest);

  @GET("/api/v2/chat/threads/{message_id}")
  @NotNull
  StreamRequest<GetThreadResponse> getThread(
      @NotNull @Path("message_id") String messageID,
      @Nullable @Query("reply_limit") Integer replyLimit,
      @Nullable @Query("participant_limit") Integer participantLimit,
      @Nullable @Query("member_limit") Integer memberLimit);

  @PATCH("/api/v2/chat/threads/{message_id}")
  @NotNull
  StreamRequest<UpdateThreadPartialResponse> updateThreadPartial(
      @NotNull @Path("message_id") String messageID,
      @Nullable @Body UpdateThreadPartialRequest updateThreadPartialRequest);

  @GET("/api/v2/chat/unread")
  @NotNull
  StreamRequest<WrappedUnreadCountsResponse> unreadCounts();

  @POST("/api/v2/chat/unread_batch")
  @NotNull
  StreamRequest<UnreadCountsBatchResponse> unreadCountsBatch(
      @NotNull @Body UnreadCountsBatchRequest unreadCountsBatchRequest);

  @POST("/api/v2/chat/users/{user_id}/event")
  @NotNull
  StreamRequest<Response> sendUserCustomEvent(
      @NotNull @Path("user_id") String userID,
      @NotNull @Body SendUserCustomEventRequest sendUserCustomEventRequest);
}
