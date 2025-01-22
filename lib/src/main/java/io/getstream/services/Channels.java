package io.getstream.services;

import io.getstream.models.*;
import io.getstream.models.framework.StreamResponse;
import io.getstream.services.framework.StreamSDKClient;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import retrofit2.http.Body;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class Channels {
  private String channelType;
  private String channelD;
  private ChatService service;

  // Constructor
  public Channels(String channelType, String channelD) {
    this.channelType = channelType;
    this.channelD = channelD;
    this.service = StreamSDKClient.getInstance().chat();
  }

  public Channels(String channelType, String channelD, ChatService service) {
    this.channelType = channelType;
    this.channelD = channelD;
    this.service = service;
  }

  // Operations

  public StreamResponse<DeleteChannelResponse> delete(
      @Nullable @Query("hard_delete") Boolean hardDelete) throws Exception {
    return service.deleteChannel(this.channelType, this.channelD, hardDelete).execute();
  }

  public StreamResponse<UpdateChannelPartialResponse> updateChannelPartial(
      @Nullable @Body UpdateChannelPartialRequest updateChannelPartialRequest) throws Exception {
    return service
        .updateChannelPartial(this.channelType, this.channelD, updateChannelPartialRequest)
        .execute();
  }

  public StreamResponse<UpdateChannelResponse> update(
      @Nullable @Body UpdateChannelRequest updateChannelRequest) throws Exception {
    return service.updateChannel(this.channelType, this.channelD, updateChannelRequest).execute();
  }

  public StreamResponse<EventResponse> sendEvent(@NotNull @Body SendEventRequest sendEventRequest)
      throws Exception {
    return service.sendEvent(this.channelType, this.channelD, sendEventRequest).execute();
  }

  public StreamResponse<Response> deleteFile(@Nullable @Query("url") String url) throws Exception {
    return service.deleteFile(this.channelType, this.channelD, url).execute();
  }

  public StreamResponse<FileUploadResponse> uploadFile(
      @Nullable @Body FileUploadRequest fileUploadRequest) throws Exception {
    return service.uploadFile(this.channelType, this.channelD, fileUploadRequest).execute();
  }

  public StreamResponse<HideChannelResponse> hide(
      @Nullable @Body HideChannelRequest hideChannelRequest) throws Exception {
    return service.hideChannel(this.channelType, this.channelD, hideChannelRequest).execute();
  }

  public StreamResponse<Response> deleteImage(@Nullable @Query("url") String url) throws Exception {
    return service.deleteImage(this.channelType, this.channelD, url).execute();
  }

  public StreamResponse<ImageUploadResponse> uploadImage(
      @Nullable @Body ImageUploadRequest imageUploadRequest) throws Exception {
    return service.uploadImage(this.channelType, this.channelD, imageUploadRequest).execute();
  }

  public StreamResponse<UpdateMemberPartialResponse> updateMemberPartial(
      @NotNull @Path("user_id") String userID,
      @Nullable @Body UpdateMemberPartialRequest updateMemberPartialRequest)
      throws Exception {
    return service
        .updateMemberPartial(this.channelType, this.channelD, userID, updateMemberPartialRequest)
        .execute();
  }

  public StreamResponse<SendMessageResponse> sendMessage(
      @NotNull @Body SendMessageRequest sendMessageRequest) throws Exception {
    return service.sendMessage(this.channelType, this.channelD, sendMessageRequest).execute();
  }

  public StreamResponse<GetManyMessagesResponse> getManyMessages(
      @NotNull @Query("ids") List<String> ids) throws Exception {
    return service.getManyMessages(this.channelType, this.channelD, ids).execute();
  }

  public StreamResponse<ChannelStateResponse> getOrCreate(
      @Nullable @Body ChannelGetOrCreateRequest channelGetOrCreateRequest) throws Exception {
    return service
        .getOrCreateChannel(this.channelType, this.channelD, channelGetOrCreateRequest)
        .execute();
  }

  public StreamResponse<MarkReadResponse> markRead(@Nullable @Body MarkReadRequest markReadRequest)
      throws Exception {
    return service.markRead(this.channelType, this.channelD, markReadRequest).execute();
  }

  public StreamResponse<ShowChannelResponse> show(
      @Nullable @Body ShowChannelRequest showChannelRequest) throws Exception {
    return service.showChannel(this.channelType, this.channelD, showChannelRequest).execute();
  }

  public StreamResponse<TruncateChannelResponse> truncate(
      @Nullable @Body TruncateChannelRequest truncateChannelRequest) throws Exception {
    return service
        .truncateChannel(this.channelType, this.channelD, truncateChannelRequest)
        .execute();
  }

  public StreamResponse<Response> markUnread(@Nullable @Body MarkUnreadRequest markUnreadRequest)
      throws Exception {
    return service.markUnread(this.channelType, this.channelD, markUnreadRequest).execute();
  }

  // Helper templates for parameter signatures and calls in Java
}
