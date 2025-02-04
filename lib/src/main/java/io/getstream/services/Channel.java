package io.getstream.services;

import io.getstream.exceptions.StreamException;
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
      throws StreamException {
    return service.deleteChannel(this.channelType, this.channelID, request).execute();
  }

  public StreamResponse<DeleteChannelResponse> delete() throws StreamException {
    return this.delete(new DeleteChannelRequest());
  }

  public StreamResponse<UpdateChannelPartialResponse> updateChannelPartial(
      UpdateChannelPartialRequest request) throws StreamException {
    return service.updateChannelPartial(this.channelType, this.channelID, request).execute();
  }

  public StreamResponse<UpdateChannelPartialResponse> updateChannelPartial()
      throws StreamException {
    return this.updateChannelPartial(new UpdateChannelPartialRequest());
  }

  public StreamResponse<UpdateChannelResponse> update(UpdateChannelRequest request)
      throws StreamException {
    return service.updateChannel(this.channelType, this.channelID, request).execute();
  }

  public StreamResponse<UpdateChannelResponse> update() throws StreamException {
    return this.update(new UpdateChannelRequest());
  }

  public StreamResponse<EventResponse> sendEvent(SendEventRequest request) throws StreamException {
    return service.sendEvent(this.channelType, this.channelID, request).execute();
  }

  public StreamResponse<Response> deleteFile(DeleteFileRequest request) throws StreamException {
    return service.deleteFile(this.channelType, this.channelID, request).execute();
  }

  public StreamResponse<Response> deleteFile() throws StreamException {
    return this.deleteFile(new DeleteFileRequest());
  }

  public StreamResponse<FileUploadResponse> uploadFile(UploadFileRequest request)
      throws StreamException {
    return service.uploadFile(this.channelType, this.channelID, request).execute();
  }

  public StreamResponse<FileUploadResponse> uploadFile() throws StreamException {
    return this.uploadFile(new UploadFileRequest());
  }

  public StreamResponse<HideChannelResponse> hide(HideChannelRequest request)
      throws StreamException {
    return service.hideChannel(this.channelType, this.channelID, request).execute();
  }

  public StreamResponse<HideChannelResponse> hide() throws StreamException {
    return this.hide(new HideChannelRequest());
  }

  public StreamResponse<Response> deleteImage(DeleteImageRequest request) throws StreamException {
    return service.deleteImage(this.channelType, this.channelID, request).execute();
  }

  public StreamResponse<Response> deleteImage() throws StreamException {
    return this.deleteImage(new DeleteImageRequest());
  }

  public StreamResponse<ImageUploadResponse> uploadImage(UploadImageRequest request)
      throws StreamException {
    return service.uploadImage(this.channelType, this.channelID, request).execute();
  }

  public StreamResponse<ImageUploadResponse> uploadImage() throws StreamException {
    return this.uploadImage(new UploadImageRequest());
  }

  public StreamResponse<UpdateMemberPartialResponse> updateMemberPartial(
      @NotNull String userID, UpdateMemberPartialRequest request) throws StreamException {
    return service.updateMemberPartial(this.channelType, this.channelID, userID, request).execute();
  }

  public StreamResponse<UpdateMemberPartialResponse> updateMemberPartial(@NotNull String userID)
      throws StreamException {
    return this.updateMemberPartial(userID, new UpdateMemberPartialRequest());
  }

  public StreamResponse<SendMessageResponse> sendMessage(SendMessageRequest request)
      throws StreamException {
    return service.sendMessage(this.channelType, this.channelID, request).execute();
  }

  public StreamResponse<GetManyMessagesResponse> getManyMessages(GetManyMessagesRequest request)
      throws StreamException {
    return service.getManyMessages(this.channelType, this.channelID, request).execute();
  }

  public StreamResponse<ChannelStateResponse> getOrCreate(GetOrCreateChannelRequest request)
      throws StreamException {
    return service.getOrCreateChannel(this.channelType, this.channelID, request).execute();
  }

  public StreamResponse<ChannelStateResponse> getOrCreate() throws StreamException {
    return this.getOrCreate(new GetOrCreateChannelRequest());
  }

  public StreamResponse<MarkReadResponse> markRead(MarkReadRequest request) throws StreamException {
    return service.markRead(this.channelType, this.channelID, request).execute();
  }

  public StreamResponse<MarkReadResponse> markRead() throws StreamException {
    return this.markRead(new MarkReadRequest());
  }

  public StreamResponse<ShowChannelResponse> show(ShowChannelRequest request)
      throws StreamException {
    return service.showChannel(this.channelType, this.channelID, request).execute();
  }

  public StreamResponse<ShowChannelResponse> show() throws StreamException {
    return this.show(new ShowChannelRequest());
  }

  public StreamResponse<TruncateChannelResponse> truncate(TruncateChannelRequest request)
      throws StreamException {
    return service.truncateChannel(this.channelType, this.channelID, request).execute();
  }

  public StreamResponse<TruncateChannelResponse> truncate() throws StreamException {
    return this.truncate(new TruncateChannelRequest());
  }

  public StreamResponse<Response> markUnread(MarkUnreadRequest request) throws StreamException {
    return service.markUnread(this.channelType, this.channelID, request).execute();
  }

  public StreamResponse<Response> markUnread() throws StreamException {
    return this.markUnread(new MarkUnreadRequest());
  }

  // Helper templates for parameter signatures and calls in Java
}
