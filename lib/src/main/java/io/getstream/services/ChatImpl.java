package io.getstream.services;

import io.getstream.exceptions.StreamException;
import io.getstream.models.*;
import io.getstream.services.framework.StreamRequest;
import io.getstream.services.framework.StreamSDKClient;
import java.util.*;
import org.jetbrains.annotations.NotNull;

public class ChatImpl implements Chat {
  private StreamSDKClient client;

  public ChatImpl(StreamSDKClient client) {
    this.client = client;
  }

  @NotNull
  public StreamRequest<QueryCampaignsResponse> queryCampaigns(QueryCampaignsRequest request)
      throws StreamException {

    return new StreamRequest<QueryCampaignsResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/chat/campaigns/query",
        request,
        null);
  }

  @NotNull
  public StreamRequest<GetCampaignResponse> getCampaign(
      @NotNull String id, GetCampaignRequest request) throws StreamException {
    var pathParams = Map.of("id", id);

    return new StreamRequest<GetCampaignResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "GET",
        "/api/v2/chat/campaigns/{id}",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<StartCampaignResponse> startCampaign(
      @NotNull String id, StartCampaignRequest request) throws StreamException {
    var pathParams = Map.of("id", id);

    return new StreamRequest<StartCampaignResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/chat/campaigns/{id}/start",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<CampaignResponse> scheduleCampaign(
      @NotNull String id, ScheduleCampaignRequest request) throws StreamException {
    var pathParams = Map.of("id", id);

    return new StreamRequest<CampaignResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/chat/campaigns/{id}/stop",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<QueryChannelsResponse> queryChannels(QueryChannelsRequest request)
      throws StreamException {

    return new StreamRequest<QueryChannelsResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/chat/channels",
        request,
        null);
  }

  @NotNull
  public StreamRequest<DeleteChannelsResponse> deleteChannels(DeleteChannelsRequest request)
      throws StreamException {

    return new StreamRequest<DeleteChannelsResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/chat/channels/delete",
        request,
        null);
  }

  @NotNull
  public StreamRequest<MarkReadResponse> markChannelsRead(MarkChannelsReadRequest request)
      throws StreamException {

    return new StreamRequest<MarkReadResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/chat/channels/read",
        request,
        null);
  }

  @NotNull
  public StreamRequest<ChannelStateResponse> getOrCreateDistinctChannel(
      @NotNull String type, GetOrCreateDistinctChannelRequest request) throws StreamException {
    var pathParams = Map.of("type", type);

    return new StreamRequest<ChannelStateResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/chat/channels/{type}/query",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<DeleteChannelResponse> deleteChannel(
      @NotNull String type, @NotNull String id, DeleteChannelRequest request)
      throws StreamException {
    var pathParams =
        Map.of(
            "type", type,
            "id", id);

    return new StreamRequest<DeleteChannelResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "DELETE",
        "/api/v2/chat/channels/{type}/{id}",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<UpdateChannelPartialResponse> updateChannelPartial(
      @NotNull String type, @NotNull String id, UpdateChannelPartialRequest request)
      throws StreamException {
    var pathParams =
        Map.of(
            "type", type,
            "id", id);

    return new StreamRequest<UpdateChannelPartialResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "PATCH",
        "/api/v2/chat/channels/{type}/{id}",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<UpdateChannelResponse> updateChannel(
      @NotNull String type, @NotNull String id, UpdateChannelRequest request)
      throws StreamException {
    var pathParams =
        Map.of(
            "type", type,
            "id", id);

    return new StreamRequest<UpdateChannelResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/chat/channels/{type}/{id}",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<EventResponse> sendEvent(
      @NotNull String type, @NotNull String id, SendEventRequest request) throws StreamException {
    var pathParams =
        Map.of(
            "type", type,
            "id", id);

    return new StreamRequest<EventResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/chat/channels/{type}/{id}/event",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<Response> deleteFile(
      @NotNull String type, @NotNull String id, DeleteFileRequest request) throws StreamException {
    var pathParams =
        Map.of(
            "type", type,
            "id", id);

    return new StreamRequest<Response>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "DELETE",
        "/api/v2/chat/channels/{type}/{id}/file",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<FileUploadResponse> uploadFile(
      @NotNull String type, @NotNull String id, UploadFileRequest request) throws StreamException {
    var pathParams =
        Map.of(
            "type", type,
            "id", id);

    return new StreamRequest<FileUploadResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/chat/channels/{type}/{id}/file",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<HideChannelResponse> hideChannel(
      @NotNull String type, @NotNull String id, HideChannelRequest request) throws StreamException {
    var pathParams =
        Map.of(
            "type", type,
            "id", id);

    return new StreamRequest<HideChannelResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/chat/channels/{type}/{id}/hide",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<Response> deleteImage(
      @NotNull String type, @NotNull String id, DeleteImageRequest request) throws StreamException {
    var pathParams =
        Map.of(
            "type", type,
            "id", id);

    return new StreamRequest<Response>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "DELETE",
        "/api/v2/chat/channels/{type}/{id}/image",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<ImageUploadResponse> uploadImage(
      @NotNull String type, @NotNull String id, UploadImageRequest request) throws StreamException {
    var pathParams =
        Map.of(
            "type", type,
            "id", id);

    return new StreamRequest<ImageUploadResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/chat/channels/{type}/{id}/image",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<UpdateMemberPartialResponse> updateMemberPartial(
      @NotNull String userID,
      @NotNull String type,
      @NotNull String id,
      UpdateMemberPartialRequest request)
      throws StreamException {
    var pathParams =
        Map.of(
            "user_id", userID,
            "type", type,
            "id", id);

    return new StreamRequest<UpdateMemberPartialResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "PATCH",
        "/api/v2/chat/channels/{type}/{id}/member/{user_id}",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<SendMessageResponse> sendMessage(
      @NotNull String type, @NotNull String id, SendMessageRequest request) throws StreamException {
    var pathParams =
        Map.of(
            "type", type,
            "id", id);

    return new StreamRequest<SendMessageResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/chat/channels/{type}/{id}/message",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<GetManyMessagesResponse> getManyMessages(
      @NotNull String type, @NotNull String id, GetManyMessagesRequest request)
      throws StreamException {
    var pathParams =
        Map.of(
            "type", type,
            "id", id);

    return new StreamRequest<GetManyMessagesResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "GET",
        "/api/v2/chat/channels/{type}/{id}/messages",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<ChannelStateResponse> getOrCreateChannel(
      @NotNull String type, @NotNull String id, GetOrCreateChannelRequest request)
      throws StreamException {
    var pathParams =
        Map.of(
            "type", type,
            "id", id);

    return new StreamRequest<ChannelStateResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/chat/channels/{type}/{id}/query",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<MarkReadResponse> markRead(
      @NotNull String type, @NotNull String id, MarkReadRequest request) throws StreamException {
    var pathParams =
        Map.of(
            "type", type,
            "id", id);

    return new StreamRequest<MarkReadResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/chat/channels/{type}/{id}/read",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<ShowChannelResponse> showChannel(
      @NotNull String type, @NotNull String id, ShowChannelRequest request) throws StreamException {
    var pathParams =
        Map.of(
            "type", type,
            "id", id);

    return new StreamRequest<ShowChannelResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/chat/channels/{type}/{id}/show",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<TruncateChannelResponse> truncateChannel(
      @NotNull String type, @NotNull String id, TruncateChannelRequest request)
      throws StreamException {
    var pathParams =
        Map.of(
            "type", type,
            "id", id);

    return new StreamRequest<TruncateChannelResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/chat/channels/{type}/{id}/truncate",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<Response> markUnread(
      @NotNull String type, @NotNull String id, MarkUnreadRequest request) throws StreamException {
    var pathParams =
        Map.of(
            "type", type,
            "id", id);

    return new StreamRequest<Response>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/chat/channels/{type}/{id}/unread",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<ListChannelTypesResponse> listChannelTypes(ListChannelTypesRequest request)
      throws StreamException {

    return new StreamRequest<ListChannelTypesResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "GET",
        "/api/v2/chat/channeltypes",
        request,
        null);
  }

  @NotNull
  public StreamRequest<CreateChannelTypeResponse> createChannelType(
      CreateChannelTypeRequest request) throws StreamException {

    return new StreamRequest<CreateChannelTypeResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/chat/channeltypes",
        request,
        null);
  }

  @NotNull
  public StreamRequest<Response> deleteChannelType(
      @NotNull String name, DeleteChannelTypeRequest request) throws StreamException {
    var pathParams = Map.of("name", name);

    return new StreamRequest<Response>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "DELETE",
        "/api/v2/chat/channeltypes/{name}",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<GetChannelTypeResponse> getChannelType(
      @NotNull String name, GetChannelTypeRequest request) throws StreamException {
    var pathParams = Map.of("name", name);

    return new StreamRequest<GetChannelTypeResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "GET",
        "/api/v2/chat/channeltypes/{name}",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<UpdateChannelTypeResponse> updateChannelType(
      @NotNull String name, UpdateChannelTypeRequest request) throws StreamException {
    var pathParams = Map.of("name", name);

    return new StreamRequest<UpdateChannelTypeResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "PUT",
        "/api/v2/chat/channeltypes/{name}",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<ListCommandsResponse> listCommands(ListCommandsRequest request)
      throws StreamException {

    return new StreamRequest<ListCommandsResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "GET",
        "/api/v2/chat/commands",
        request,
        null);
  }

  @NotNull
  public StreamRequest<CreateCommandResponse> createCommand(CreateCommandRequest request)
      throws StreamException {

    return new StreamRequest<CreateCommandResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/chat/commands",
        request,
        null);
  }

  @NotNull
  public StreamRequest<DeleteCommandResponse> deleteCommand(
      @NotNull String name, DeleteCommandRequest request) throws StreamException {
    var pathParams = Map.of("name", name);

    return new StreamRequest<DeleteCommandResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "DELETE",
        "/api/v2/chat/commands/{name}",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<GetCommandResponse> getCommand(
      @NotNull String name, GetCommandRequest request) throws StreamException {
    var pathParams = Map.of("name", name);

    return new StreamRequest<GetCommandResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "GET",
        "/api/v2/chat/commands/{name}",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<UpdateCommandResponse> updateCommand(
      @NotNull String name, UpdateCommandRequest request) throws StreamException {
    var pathParams = Map.of("name", name);

    return new StreamRequest<UpdateCommandResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "PUT",
        "/api/v2/chat/commands/{name}",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<ExportChannelsResponse> exportChannels(ExportChannelsRequest request)
      throws StreamException {

    return new StreamRequest<ExportChannelsResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/chat/export_channels",
        request,
        null);
  }

  @NotNull
  public StreamRequest<GetExportChannelsStatusResponse> getExportChannelsStatus(
      @NotNull String id, GetExportChannelsStatusRequest request) throws StreamException {
    var pathParams = Map.of("id", id);

    return new StreamRequest<GetExportChannelsStatusResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "GET",
        "/api/v2/chat/export_channels/{id}",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<MembersResponse> queryMembers(QueryMembersRequest request)
      throws StreamException {

    return new StreamRequest<MembersResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "GET",
        "/api/v2/chat/members",
        request,
        null);
  }

  @NotNull
  public StreamRequest<QueryMessageHistoryResponse> queryMessageHistory(
      QueryMessageHistoryRequest request) throws StreamException {

    return new StreamRequest<QueryMessageHistoryResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/chat/messages/history",
        request,
        null);
  }

  @NotNull
  public StreamRequest<DeleteMessageResponse> deleteMessage(
      @NotNull String id, DeleteMessageRequest request) throws StreamException {
    var pathParams = Map.of("id", id);

    return new StreamRequest<DeleteMessageResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "DELETE",
        "/api/v2/chat/messages/{id}",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<GetMessageResponse> getMessage(@NotNull String id, GetMessageRequest request)
      throws StreamException {
    var pathParams = Map.of("id", id);

    return new StreamRequest<GetMessageResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "GET",
        "/api/v2/chat/messages/{id}",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<UpdateMessageResponse> updateMessage(
      @NotNull String id, UpdateMessageRequest request) throws StreamException {
    var pathParams = Map.of("id", id);

    return new StreamRequest<UpdateMessageResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/chat/messages/{id}",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<UpdateMessagePartialResponse> updateMessagePartial(
      @NotNull String id, UpdateMessagePartialRequest request) throws StreamException {
    var pathParams = Map.of("id", id);

    return new StreamRequest<UpdateMessagePartialResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "PUT",
        "/api/v2/chat/messages/{id}",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<MessageResponse> runMessageAction(
      @NotNull String id, RunMessageActionRequest request) throws StreamException {
    var pathParams = Map.of("id", id);

    return new StreamRequest<MessageResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/chat/messages/{id}/action",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<MessageResponse> commitMessage(
      @NotNull String id, CommitMessageRequest request) throws StreamException {
    var pathParams = Map.of("id", id);

    return new StreamRequest<MessageResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/chat/messages/{id}/commit",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<SendReactionResponse> sendReaction(
      @NotNull String id, SendReactionRequest request) throws StreamException {
    var pathParams = Map.of("id", id);

    return new StreamRequest<SendReactionResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/chat/messages/{id}/reaction",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<DeleteReactionResponse> deleteReaction(
      @NotNull String id, @NotNull String type, DeleteReactionRequest request)
      throws StreamException {
    var pathParams =
        Map.of(
            "id", id,
            "type", type);

    return new StreamRequest<DeleteReactionResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "DELETE",
        "/api/v2/chat/messages/{id}/reaction/{type}",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<GetReactionsResponse> getReactions(
      @NotNull String id, GetReactionsRequest request) throws StreamException {
    var pathParams = Map.of("id", id);

    return new StreamRequest<GetReactionsResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "GET",
        "/api/v2/chat/messages/{id}/reactions",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<QueryReactionsResponse> queryReactions(
      @NotNull String id, QueryReactionsRequest request) throws StreamException {
    var pathParams = Map.of("id", id);

    return new StreamRequest<QueryReactionsResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/chat/messages/{id}/reactions",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<MessageResponse> translateMessage(
      @NotNull String id, TranslateMessageRequest request) throws StreamException {
    var pathParams = Map.of("id", id);

    return new StreamRequest<MessageResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/chat/messages/{id}/translate",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<UpdateMessageResponse> undeleteMessage(
      @NotNull String id, UndeleteMessageRequest request) throws StreamException {
    var pathParams = Map.of("id", id);

    return new StreamRequest<UpdateMessageResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/chat/messages/{id}/undelete",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<PollVoteResponse> castPollVote(
      @NotNull String messageID, @NotNull String pollID, CastPollVoteRequest request)
      throws StreamException {
    var pathParams =
        Map.of(
            "message_id", messageID,
            "poll_id", pollID);

    return new StreamRequest<PollVoteResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/chat/messages/{message_id}/polls/{poll_id}/vote",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<PollVoteResponse> removePollVote(
      @NotNull String messageID,
      @NotNull String pollID,
      @NotNull String voteID,
      RemovePollVoteRequest request)
      throws StreamException {
    var pathParams =
        Map.of(
            "message_id", messageID,
            "poll_id", pollID,
            "vote_id", voteID);

    return new StreamRequest<PollVoteResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "DELETE",
        "/api/v2/chat/messages/{message_id}/polls/{poll_id}/vote/{vote_id}",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<GetRepliesResponse> getReplies(
      @NotNull String parentID, GetRepliesRequest request) throws StreamException {
    var pathParams = Map.of("parent_id", parentID);

    return new StreamRequest<GetRepliesResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "GET",
        "/api/v2/chat/messages/{parent_id}/replies",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<QueryMessageFlagsResponse> queryMessageFlags(
      QueryMessageFlagsRequest request) throws StreamException {

    return new StreamRequest<QueryMessageFlagsResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "GET",
        "/api/v2/chat/moderation/flags/message",
        request,
        null);
  }

  @NotNull
  public StreamRequest<MuteChannelResponse> muteChannel(MuteChannelRequest request)
      throws StreamException {

    return new StreamRequest<MuteChannelResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/chat/moderation/mute/channel",
        request,
        null);
  }

  @NotNull
  public StreamRequest<UnmuteResponse> unmuteChannel(UnmuteChannelRequest request)
      throws StreamException {

    return new StreamRequest<UnmuteResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/chat/moderation/unmute/channel",
        request,
        null);
  }

  @NotNull
  public StreamRequest<PollResponse> createPoll(CreatePollRequest request) throws StreamException {

    return new StreamRequest<PollResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/chat/polls",
        request,
        null);
  }

  @NotNull
  public StreamRequest<PollResponse> updatePoll(UpdatePollRequest request) throws StreamException {

    return new StreamRequest<PollResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "PUT",
        "/api/v2/chat/polls",
        request,
        null);
  }

  @NotNull
  public StreamRequest<QueryPollsResponse> queryPolls(QueryPollsRequest request)
      throws StreamException {

    return new StreamRequest<QueryPollsResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/chat/polls/query",
        request,
        null);
  }

  @NotNull
  public StreamRequest<Response> deletePoll(@NotNull String pollID, DeletePollRequest request)
      throws StreamException {
    var pathParams = Map.of("poll_id", pollID);

    return new StreamRequest<Response>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "DELETE",
        "/api/v2/chat/polls/{poll_id}",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<PollResponse> getPoll(@NotNull String pollID, GetPollRequest request)
      throws StreamException {
    var pathParams = Map.of("poll_id", pollID);

    return new StreamRequest<PollResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "GET",
        "/api/v2/chat/polls/{poll_id}",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<PollResponse> updatePollPartial(
      @NotNull String pollID, UpdatePollPartialRequest request) throws StreamException {
    var pathParams = Map.of("poll_id", pollID);

    return new StreamRequest<PollResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "PATCH",
        "/api/v2/chat/polls/{poll_id}",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<PollOptionResponse> createPollOption(
      @NotNull String pollID, CreatePollOptionRequest request) throws StreamException {
    var pathParams = Map.of("poll_id", pollID);

    return new StreamRequest<PollOptionResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/chat/polls/{poll_id}/options",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<PollOptionResponse> updatePollOption(
      @NotNull String pollID, UpdatePollOptionRequest request) throws StreamException {
    var pathParams = Map.of("poll_id", pollID);

    return new StreamRequest<PollOptionResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "PUT",
        "/api/v2/chat/polls/{poll_id}/options",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<Response> deletePollOption(
      @NotNull String pollID, @NotNull String optionID, DeletePollOptionRequest request)
      throws StreamException {
    var pathParams =
        Map.of(
            "poll_id", pollID,
            "option_id", optionID);

    return new StreamRequest<Response>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "DELETE",
        "/api/v2/chat/polls/{poll_id}/options/{option_id}",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<PollOptionResponse> getPollOption(
      @NotNull String pollID, @NotNull String optionID, GetPollOptionRequest request)
      throws StreamException {
    var pathParams =
        Map.of(
            "poll_id", pollID,
            "option_id", optionID);

    return new StreamRequest<PollOptionResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "GET",
        "/api/v2/chat/polls/{poll_id}/options/{option_id}",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<PollVotesResponse> queryPollVotes(
      @NotNull String pollID, QueryPollVotesRequest request) throws StreamException {
    var pathParams = Map.of("poll_id", pollID);

    return new StreamRequest<PollVotesResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/chat/polls/{poll_id}/votes",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<UpsertPushPreferencesResponse> updatePushNotificationPreferences(
      UpdatePushNotificationPreferencesRequest request) throws StreamException {

    return new StreamRequest<UpsertPushPreferencesResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/chat/push_preferences",
        request,
        null);
  }

  @NotNull
  public StreamRequest<QueryBannedUsersResponse> queryBannedUsers(QueryBannedUsersRequest request)
      throws StreamException {

    return new StreamRequest<QueryBannedUsersResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "GET",
        "/api/v2/chat/query_banned_users",
        request,
        null);
  }

  @NotNull
  public StreamRequest<SearchResponse> search(SearchRequest request) throws StreamException {

    return new StreamRequest<SearchResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "GET",
        "/api/v2/chat/search",
        request,
        null);
  }

  @NotNull
  public StreamRequest<QuerySegmentsResponse> querySegments(QuerySegmentsRequest request)
      throws StreamException {

    return new StreamRequest<QuerySegmentsResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/chat/segments/query",
        request,
        null);
  }

  @NotNull
  public StreamRequest<Response> deleteSegment(@NotNull String id, DeleteSegmentRequest request)
      throws StreamException {
    var pathParams = Map.of("id", id);

    return new StreamRequest<Response>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "DELETE",
        "/api/v2/chat/segments/{id}",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<GetSegmentResponse> getSegment(@NotNull String id, GetSegmentRequest request)
      throws StreamException {
    var pathParams = Map.of("id", id);

    return new StreamRequest<GetSegmentResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "GET",
        "/api/v2/chat/segments/{id}",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<Response> deleteSegmentTargets(
      @NotNull String id, DeleteSegmentTargetsRequest request) throws StreamException {
    var pathParams = Map.of("id", id);

    return new StreamRequest<Response>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/chat/segments/{id}/deletetargets",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<Response> segmentTargetExists(
      @NotNull String id, @NotNull String targetID, SegmentTargetExistsRequest request)
      throws StreamException {
    var pathParams =
        Map.of(
            "id", id,
            "target_id", targetID);

    return new StreamRequest<Response>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "GET",
        "/api/v2/chat/segments/{id}/target/{target_id}",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<QuerySegmentTargetsResponse> querySegmentTargets(
      @NotNull String id, QuerySegmentTargetsRequest request) throws StreamException {
    var pathParams = Map.of("id", id);

    return new StreamRequest<QuerySegmentTargetsResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/chat/segments/{id}/targets/query",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<QueryThreadsResponse> queryThreads(QueryThreadsRequest request)
      throws StreamException {

    return new StreamRequest<QueryThreadsResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/chat/threads",
        request,
        null);
  }

  @NotNull
  public StreamRequest<GetThreadResponse> getThread(
      @NotNull String messageID, GetThreadRequest request) throws StreamException {
    var pathParams = Map.of("message_id", messageID);

    return new StreamRequest<GetThreadResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "GET",
        "/api/v2/chat/threads/{message_id}",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<UpdateThreadPartialResponse> updateThreadPartial(
      @NotNull String messageID, UpdateThreadPartialRequest request) throws StreamException {
    var pathParams = Map.of("message_id", messageID);

    return new StreamRequest<UpdateThreadPartialResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "PATCH",
        "/api/v2/chat/threads/{message_id}",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<WrappedUnreadCountsResponse> unreadCounts(UnreadCountsRequest request)
      throws StreamException {

    return new StreamRequest<WrappedUnreadCountsResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "GET",
        "/api/v2/chat/unread",
        request,
        null);
  }

  @NotNull
  public StreamRequest<UnreadCountsBatchResponse> unreadCountsBatch(
      UnreadCountsBatchRequest request) throws StreamException {

    return new StreamRequest<UnreadCountsBatchResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/chat/unread_batch",
        request,
        null);
  }

  @NotNull
  public StreamRequest<Response> sendUserCustomEvent(
      @NotNull String userID, SendUserCustomEventRequest request) throws StreamException {
    var pathParams = Map.of("user_id", userID);

    return new StreamRequest<Response>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/chat/users/{user_id}/event",
        request,
        pathParams);
  }

  @NotNull
  public Channel channel(String channelType, String channelID) {
    return new Channel(channelType, channelID, client.chat());
  }
}
