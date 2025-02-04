package io.getstream.services;

import io.getstream.exceptions.StreamException;
import io.getstream.models.*;
import io.getstream.services.framework.StreamRequest;
import java.util.*;
import org.jetbrains.annotations.NotNull;

public interface Chat {
  @NotNull
  public StreamRequest<QueryCampaignsResponse> queryCampaigns(QueryCampaignsRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<QueryCampaignsResponse> queryCampaigns() throws StreamException;

  @NotNull
  public StreamRequest<GetCampaignResponse> getCampaign(
      @NotNull String id, GetCampaignRequest request) throws StreamException;

  @NotNull
  public StreamRequest<GetCampaignResponse> getCampaign(@NotNull String id) throws StreamException;

  @NotNull
  public StreamRequest<StartCampaignResponse> startCampaign(
      @NotNull String id, StartCampaignRequest request) throws StreamException;

  @NotNull
  public StreamRequest<StartCampaignResponse> startCampaign(@NotNull String id)
      throws StreamException;

  @NotNull
  public StreamRequest<CampaignResponse> scheduleCampaign(
      @NotNull String id, ScheduleCampaignRequest request) throws StreamException;

  @NotNull
  public StreamRequest<CampaignResponse> scheduleCampaign(@NotNull String id)
      throws StreamException;

  @NotNull
  public StreamRequest<QueryChannelsResponse> queryChannels(QueryChannelsRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<QueryChannelsResponse> queryChannels() throws StreamException;

  @NotNull
  public StreamRequest<DeleteChannelsResponse> deleteChannels(DeleteChannelsRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<MarkReadResponse> markChannelsRead(MarkChannelsReadRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<MarkReadResponse> markChannelsRead() throws StreamException;

  @NotNull
  public StreamRequest<ChannelStateResponse> getOrCreateDistinctChannel(
      @NotNull String type, GetOrCreateDistinctChannelRequest request) throws StreamException;

  @NotNull
  public StreamRequest<ChannelStateResponse> getOrCreateDistinctChannel(@NotNull String type)
      throws StreamException;

  @NotNull
  public StreamRequest<DeleteChannelResponse> deleteChannel(
      @NotNull String type, @NotNull String id, DeleteChannelRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<DeleteChannelResponse> deleteChannel(
      @NotNull String type, @NotNull String id) throws StreamException;

  @NotNull
  public StreamRequest<UpdateChannelPartialResponse> updateChannelPartial(
      @NotNull String type, @NotNull String id, UpdateChannelPartialRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<UpdateChannelPartialResponse> updateChannelPartial(
      @NotNull String type, @NotNull String id) throws StreamException;

  @NotNull
  public StreamRequest<UpdateChannelResponse> updateChannel(
      @NotNull String type, @NotNull String id, UpdateChannelRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<UpdateChannelResponse> updateChannel(
      @NotNull String type, @NotNull String id) throws StreamException;

  @NotNull
  public StreamRequest<EventResponse> sendEvent(
      @NotNull String type, @NotNull String id, SendEventRequest request) throws StreamException;

  @NotNull
  public StreamRequest<Response> deleteFile(
      @NotNull String type, @NotNull String id, DeleteFileRequest request) throws StreamException;

  @NotNull
  public StreamRequest<Response> deleteFile(@NotNull String type, @NotNull String id)
      throws StreamException;

  @NotNull
  public StreamRequest<FileUploadResponse> uploadFile(
      @NotNull String type, @NotNull String id, UploadFileRequest request) throws StreamException;

  @NotNull
  public StreamRequest<FileUploadResponse> uploadFile(@NotNull String type, @NotNull String id)
      throws StreamException;

  @NotNull
  public StreamRequest<HideChannelResponse> hideChannel(
      @NotNull String type, @NotNull String id, HideChannelRequest request) throws StreamException;

  @NotNull
  public StreamRequest<HideChannelResponse> hideChannel(@NotNull String type, @NotNull String id)
      throws StreamException;

  @NotNull
  public StreamRequest<Response> deleteImage(
      @NotNull String type, @NotNull String id, DeleteImageRequest request) throws StreamException;

  @NotNull
  public StreamRequest<Response> deleteImage(@NotNull String type, @NotNull String id)
      throws StreamException;

  @NotNull
  public StreamRequest<ImageUploadResponse> uploadImage(
      @NotNull String type, @NotNull String id, UploadImageRequest request) throws StreamException;

  @NotNull
  public StreamRequest<ImageUploadResponse> uploadImage(@NotNull String type, @NotNull String id)
      throws StreamException;

  @NotNull
  public StreamRequest<UpdateMemberPartialResponse> updateMemberPartial(
      @NotNull String userID,
      @NotNull String type,
      @NotNull String id,
      UpdateMemberPartialRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<UpdateMemberPartialResponse> updateMemberPartial(
      @NotNull String userID, @NotNull String type, @NotNull String id) throws StreamException;

  @NotNull
  public StreamRequest<SendMessageResponse> sendMessage(
      @NotNull String type, @NotNull String id, SendMessageRequest request) throws StreamException;

  @NotNull
  public StreamRequest<GetManyMessagesResponse> getManyMessages(
      @NotNull String type, @NotNull String id, GetManyMessagesRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<ChannelStateResponse> getOrCreateChannel(
      @NotNull String type, @NotNull String id, GetOrCreateChannelRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<ChannelStateResponse> getOrCreateChannel(
      @NotNull String type, @NotNull String id) throws StreamException;

  @NotNull
  public StreamRequest<MarkReadResponse> markRead(
      @NotNull String type, @NotNull String id, MarkReadRequest request) throws StreamException;

  @NotNull
  public StreamRequest<MarkReadResponse> markRead(@NotNull String type, @NotNull String id)
      throws StreamException;

  @NotNull
  public StreamRequest<ShowChannelResponse> showChannel(
      @NotNull String type, @NotNull String id, ShowChannelRequest request) throws StreamException;

  @NotNull
  public StreamRequest<ShowChannelResponse> showChannel(@NotNull String type, @NotNull String id)
      throws StreamException;

  @NotNull
  public StreamRequest<TruncateChannelResponse> truncateChannel(
      @NotNull String type, @NotNull String id, TruncateChannelRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<TruncateChannelResponse> truncateChannel(
      @NotNull String type, @NotNull String id) throws StreamException;

  @NotNull
  public StreamRequest<Response> markUnread(
      @NotNull String type, @NotNull String id, MarkUnreadRequest request) throws StreamException;

  @NotNull
  public StreamRequest<Response> markUnread(@NotNull String type, @NotNull String id)
      throws StreamException;

  @NotNull
  public StreamRequest<ListChannelTypesResponse> listChannelTypes(ListChannelTypesRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<ListChannelTypesResponse> listChannelTypes() throws StreamException;

  @NotNull
  public StreamRequest<CreateChannelTypeResponse> createChannelType(
      CreateChannelTypeRequest request) throws StreamException;

  @NotNull
  public StreamRequest<Response> deleteChannelType(
      @NotNull String name, DeleteChannelTypeRequest request) throws StreamException;

  @NotNull
  public StreamRequest<Response> deleteChannelType(@NotNull String name) throws StreamException;

  @NotNull
  public StreamRequest<GetChannelTypeResponse> getChannelType(
      @NotNull String name, GetChannelTypeRequest request) throws StreamException;

  @NotNull
  public StreamRequest<GetChannelTypeResponse> getChannelType(@NotNull String name)
      throws StreamException;

  @NotNull
  public StreamRequest<UpdateChannelTypeResponse> updateChannelType(
      @NotNull String name, UpdateChannelTypeRequest request) throws StreamException;

  @NotNull
  public StreamRequest<ListCommandsResponse> listCommands(ListCommandsRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<ListCommandsResponse> listCommands() throws StreamException;

  @NotNull
  public StreamRequest<CreateCommandResponse> createCommand(CreateCommandRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<DeleteCommandResponse> deleteCommand(
      @NotNull String name, DeleteCommandRequest request) throws StreamException;

  @NotNull
  public StreamRequest<DeleteCommandResponse> deleteCommand(@NotNull String name)
      throws StreamException;

  @NotNull
  public StreamRequest<GetCommandResponse> getCommand(
      @NotNull String name, GetCommandRequest request) throws StreamException;

  @NotNull
  public StreamRequest<GetCommandResponse> getCommand(@NotNull String name) throws StreamException;

  @NotNull
  public StreamRequest<UpdateCommandResponse> updateCommand(
      @NotNull String name, UpdateCommandRequest request) throws StreamException;

  @NotNull
  public StreamRequest<ExportChannelsResponse> exportChannels(ExportChannelsRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<GetExportChannelsStatusResponse> getExportChannelsStatus(
      @NotNull String id, GetExportChannelsStatusRequest request) throws StreamException;

  @NotNull
  public StreamRequest<GetExportChannelsStatusResponse> getExportChannelsStatus(@NotNull String id)
      throws StreamException;

  @NotNull
  public StreamRequest<MembersResponse> queryMembers(QueryMembersRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<MembersResponse> queryMembers() throws StreamException;

  @NotNull
  public StreamRequest<QueryMessageHistoryResponse> queryMessageHistory(
      QueryMessageHistoryRequest request) throws StreamException;

  @NotNull
  public StreamRequest<DeleteMessageResponse> deleteMessage(
      @NotNull String id, DeleteMessageRequest request) throws StreamException;

  @NotNull
  public StreamRequest<DeleteMessageResponse> deleteMessage(@NotNull String id)
      throws StreamException;

  @NotNull
  public StreamRequest<GetMessageResponse> getMessage(@NotNull String id, GetMessageRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<GetMessageResponse> getMessage(@NotNull String id) throws StreamException;

  @NotNull
  public StreamRequest<UpdateMessageResponse> updateMessage(
      @NotNull String id, UpdateMessageRequest request) throws StreamException;

  @NotNull
  public StreamRequest<UpdateMessagePartialResponse> updateMessagePartial(
      @NotNull String id, UpdateMessagePartialRequest request) throws StreamException;

  @NotNull
  public StreamRequest<UpdateMessagePartialResponse> updateMessagePartial(@NotNull String id)
      throws StreamException;

  @NotNull
  public StreamRequest<MessageResponse> runMessageAction(
      @NotNull String id, RunMessageActionRequest request) throws StreamException;

  @NotNull
  public StreamRequest<MessageResponse> commitMessage(
      @NotNull String id, CommitMessageRequest request) throws StreamException;

  @NotNull
  public StreamRequest<MessageResponse> commitMessage(@NotNull String id) throws StreamException;

  @NotNull
  public StreamRequest<SendReactionResponse> sendReaction(
      @NotNull String id, SendReactionRequest request) throws StreamException;

  @NotNull
  public StreamRequest<DeleteReactionResponse> deleteReaction(
      @NotNull String id, @NotNull String type, DeleteReactionRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<DeleteReactionResponse> deleteReaction(
      @NotNull String id, @NotNull String type) throws StreamException;

  @NotNull
  public StreamRequest<GetReactionsResponse> getReactions(
      @NotNull String id, GetReactionsRequest request) throws StreamException;

  @NotNull
  public StreamRequest<GetReactionsResponse> getReactions(@NotNull String id)
      throws StreamException;

  @NotNull
  public StreamRequest<QueryReactionsResponse> queryReactions(
      @NotNull String id, QueryReactionsRequest request) throws StreamException;

  @NotNull
  public StreamRequest<QueryReactionsResponse> queryReactions(@NotNull String id)
      throws StreamException;

  @NotNull
  public StreamRequest<MessageResponse> translateMessage(
      @NotNull String id, TranslateMessageRequest request) throws StreamException;

  @NotNull
  public StreamRequest<UpdateMessageResponse> undeleteMessage(
      @NotNull String id, UndeleteMessageRequest request) throws StreamException;

  @NotNull
  public StreamRequest<PollVoteResponse> castPollVote(
      @NotNull String messageID, @NotNull String pollID, CastPollVoteRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<PollVoteResponse> castPollVote(
      @NotNull String messageID, @NotNull String pollID) throws StreamException;

  @NotNull
  public StreamRequest<PollVoteResponse> removePollVote(
      @NotNull String messageID,
      @NotNull String pollID,
      @NotNull String voteID,
      RemovePollVoteRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<PollVoteResponse> removePollVote(
      @NotNull String messageID, @NotNull String pollID, @NotNull String voteID)
      throws StreamException;

  @NotNull
  public StreamRequest<GetRepliesResponse> getReplies(
      @NotNull String parentID, GetRepliesRequest request) throws StreamException;

  @NotNull
  public StreamRequest<GetRepliesResponse> getReplies(@NotNull String parentID)
      throws StreamException;

  @NotNull
  public StreamRequest<QueryMessageFlagsResponse> queryMessageFlags(
      QueryMessageFlagsRequest request) throws StreamException;

  @NotNull
  public StreamRequest<QueryMessageFlagsResponse> queryMessageFlags() throws StreamException;

  @NotNull
  public StreamRequest<MuteChannelResponse> muteChannel(MuteChannelRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<MuteChannelResponse> muteChannel() throws StreamException;

  @NotNull
  public StreamRequest<UnmuteResponse> unmuteChannel(UnmuteChannelRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<UnmuteResponse> unmuteChannel() throws StreamException;

  @NotNull
  public StreamRequest<PollResponse> createPoll(CreatePollRequest request) throws StreamException;

  @NotNull
  public StreamRequest<PollResponse> updatePoll(UpdatePollRequest request) throws StreamException;

  @NotNull
  public StreamRequest<QueryPollsResponse> queryPolls(QueryPollsRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<QueryPollsResponse> queryPolls() throws StreamException;

  @NotNull
  public StreamRequest<Response> deletePoll(@NotNull String pollID, DeletePollRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<Response> deletePoll(@NotNull String pollID) throws StreamException;

  @NotNull
  public StreamRequest<PollResponse> getPoll(@NotNull String pollID, GetPollRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<PollResponse> getPoll(@NotNull String pollID) throws StreamException;

  @NotNull
  public StreamRequest<PollResponse> updatePollPartial(
      @NotNull String pollID, UpdatePollPartialRequest request) throws StreamException;

  @NotNull
  public StreamRequest<PollResponse> updatePollPartial(@NotNull String pollID)
      throws StreamException;

  @NotNull
  public StreamRequest<PollOptionResponse> createPollOption(
      @NotNull String pollID, CreatePollOptionRequest request) throws StreamException;

  @NotNull
  public StreamRequest<PollOptionResponse> updatePollOption(
      @NotNull String pollID, UpdatePollOptionRequest request) throws StreamException;

  @NotNull
  public StreamRequest<Response> deletePollOption(
      @NotNull String pollID, @NotNull String optionID, DeletePollOptionRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<Response> deletePollOption(@NotNull String pollID, @NotNull String optionID)
      throws StreamException;

  @NotNull
  public StreamRequest<PollOptionResponse> getPollOption(
      @NotNull String pollID, @NotNull String optionID, GetPollOptionRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<PollOptionResponse> getPollOption(
      @NotNull String pollID, @NotNull String optionID) throws StreamException;

  @NotNull
  public StreamRequest<PollVotesResponse> queryPollVotes(
      @NotNull String pollID, QueryPollVotesRequest request) throws StreamException;

  @NotNull
  public StreamRequest<PollVotesResponse> queryPollVotes(@NotNull String pollID)
      throws StreamException;

  @NotNull
  public StreamRequest<UpsertPushPreferencesResponse> updatePushNotificationPreferences(
      UpdatePushNotificationPreferencesRequest request) throws StreamException;

  @NotNull
  public StreamRequest<QueryBannedUsersResponse> queryBannedUsers(QueryBannedUsersRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<QueryBannedUsersResponse> queryBannedUsers() throws StreamException;

  @NotNull
  public StreamRequest<SearchResponse> search(SearchRequest request) throws StreamException;

  @NotNull
  public StreamRequest<SearchResponse> search() throws StreamException;

  @NotNull
  public StreamRequest<QuerySegmentsResponse> querySegments(QuerySegmentsRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<Response> deleteSegment(@NotNull String id, DeleteSegmentRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<Response> deleteSegment(@NotNull String id) throws StreamException;

  @NotNull
  public StreamRequest<GetSegmentResponse> getSegment(@NotNull String id, GetSegmentRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<GetSegmentResponse> getSegment(@NotNull String id) throws StreamException;

  @NotNull
  public StreamRequest<Response> deleteSegmentTargets(
      @NotNull String id, DeleteSegmentTargetsRequest request) throws StreamException;

  @NotNull
  public StreamRequest<Response> segmentTargetExists(
      @NotNull String id, @NotNull String targetID, SegmentTargetExistsRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<Response> segmentTargetExists(@NotNull String id, @NotNull String targetID)
      throws StreamException;

  @NotNull
  public StreamRequest<QuerySegmentTargetsResponse> querySegmentTargets(
      @NotNull String id, QuerySegmentTargetsRequest request) throws StreamException;

  @NotNull
  public StreamRequest<QuerySegmentTargetsResponse> querySegmentTargets(@NotNull String id)
      throws StreamException;

  @NotNull
  public StreamRequest<QueryThreadsResponse> queryThreads(QueryThreadsRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<QueryThreadsResponse> queryThreads() throws StreamException;

  @NotNull
  public StreamRequest<GetThreadResponse> getThread(
      @NotNull String messageID, GetThreadRequest request) throws StreamException;

  @NotNull
  public StreamRequest<GetThreadResponse> getThread(@NotNull String messageID)
      throws StreamException;

  @NotNull
  public StreamRequest<UpdateThreadPartialResponse> updateThreadPartial(
      @NotNull String messageID, UpdateThreadPartialRequest request) throws StreamException;

  @NotNull
  public StreamRequest<UpdateThreadPartialResponse> updateThreadPartial(@NotNull String messageID)
      throws StreamException;

  @NotNull
  public StreamRequest<WrappedUnreadCountsResponse> unreadCounts(UnreadCountsRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<WrappedUnreadCountsResponse> unreadCounts() throws StreamException;

  @NotNull
  public StreamRequest<UnreadCountsBatchResponse> unreadCountsBatch(
      UnreadCountsBatchRequest request) throws StreamException;

  @NotNull
  public StreamRequest<Response> sendUserCustomEvent(
      @NotNull String userID, SendUserCustomEventRequest request) throws StreamException;

  @NotNull
  public Channel channel(String channelType, String channelID);
}
