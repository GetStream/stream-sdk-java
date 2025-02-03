package io.getstream.services;

import io.getstream.models.*;
import io.getstream.models.framework.StreamResponse;
import org.jetbrains.annotations.NotNull;

public class Channel {
  private String channelType;
  private String channelID;
  private Chat service;

  public Channel(String channelType, String channelID, Chat service) {
    this.channelType = channelType;
    this.channelID = channelID;
    this.service = service;
  }

  // Operations

  public StreamResponse<DeleteChannelResponse> delete(DeleteChannelRequest request)
      throws Exception {
    return service.deleteChannel(this.channelType, this.channelID, request).execute();
  }

  public StreamResponse<UpdateChannelPartialResponse> updateChannelPartial(
      UpdateChannelPartialRequest request) throws Exception {
    return service.updateChannelPartial(this.channelType, this.channelID, request).execute();
  }

  public StreamResponse<UpdateChannelResponse> update(UpdateChannelRequest request)
      throws Exception {
    return service.updateChannel(this.channelType, this.channelID, request).execute();
  }

  public StreamResponse<EventResponse> sendEvent(SendEventRequest request) throws Exception {
    return service.sendEvent(this.channelType, this.channelID, request).execute();
  }

  public StreamResponse<Response> deleteFile(DeleteFileRequest request) throws Exception {
    return service.deleteFile(this.channelType, this.channelID, request).execute();
  }

  public StreamResponse<FileUploadResponse> uploadFile(UploadFileRequest request) throws Exception {
    return service.uploadFile(this.channelType, this.channelID, request).execute();
  }

  public StreamResponse<HideChannelResponse> hide(HideChannelRequest request) throws Exception {
    return service.hideChannel(this.channelType, this.channelID, request).execute();
  }

  public StreamResponse<Response> deleteImage(DeleteImageRequest request) throws Exception {
    return service.deleteImage(this.channelType, this.channelID, request).execute();
  }

  public StreamResponse<ImageUploadResponse> uploadImage(UploadImageRequest request)
      throws Exception {
    return service.uploadImage(this.channelType, this.channelID, request).execute();
  }

  public StreamResponse<UpdateMemberPartialResponse> updateMemberPartial(
      @NotNull String userID, UpdateMemberPartialRequest request) throws Exception {
    return service.updateMemberPartial(this.channelType, this.channelID, userID, request).execute();
  }

  public StreamResponse<SendMessageResponse> sendMessage(SendMessageRequest request)
      throws Exception {
    return service.sendMessage(this.channelType, this.channelID, request).execute();
  }

  public StreamResponse<GetManyMessagesResponse> getManyMessages(GetManyMessagesRequest request)
      throws Exception {
    return service.getManyMessages(this.channelType, this.channelID, request).execute();
  }

  public StreamResponse<ChannelStateResponse> getOrCreate(GetOrCreateChannelRequest request)
      throws Exception {
    return service.getOrCreateChannel(this.channelType, this.channelID, request).execute();
  }

  public StreamResponse<MarkReadResponse> markRead(MarkReadRequest request) throws Exception {
    return service.markRead(this.channelType, this.channelID, request).execute();
  }

  public StreamResponse<ShowChannelResponse> show(ShowChannelRequest request) throws Exception {
    return service.showChannel(this.channelType, this.channelID, request).execute();
  }

  public StreamResponse<TruncateChannelResponse> truncate(TruncateChannelRequest request)
      throws Exception {
    return service.truncateChannel(this.channelType, this.channelID, request).execute();
  }

  public StreamResponse<Response> markUnread(MarkUnreadRequest request) throws Exception {
    return service.markUnread(this.channelType, this.channelID, request).execute();
  }

  // Helper templates for parameter signatures and calls in Java
}
