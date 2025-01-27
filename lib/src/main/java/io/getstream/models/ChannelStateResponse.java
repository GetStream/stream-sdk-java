package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.List;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class ChannelStateResponse {

  @JsonProperty("duration")
  private String duration;

  @JsonProperty("members")
  private List<ChannelMember> members;

  @JsonProperty("messages")
  private List<MessageResponse> messages;

  @JsonProperty("pinned_messages")
  private List<MessageResponse> pinnedMessages;

  @JsonProperty("threads")
  private List<ThreadStateResponse> threads;

  @Nullable
  @JsonProperty("hidden")
  private Boolean hidden;

  @Nullable
  @JsonProperty("hide_messages_before")
  private Date hideMessagesBefore;

  @Nullable
  @JsonProperty("watcher_count")
  private Integer watcherCount;

  @Nullable
  @JsonProperty("pending_messages")
  private List<PendingMessageResponse> pendingMessages;

  @Nullable
  @JsonProperty("read")
  private List<ReadStateResponse> read;

  @Nullable
  @JsonProperty("watchers")
  private List<UserResponse> watchers;

  @Nullable
  @JsonProperty("channel")
  private ChannelResponse channel;

  @Nullable
  @JsonProperty("membership")
  private ChannelMember membership;

  @Nullable
  @JsonProperty("push_preferences")
  private ChannelPushPreferences pushPreferences;
}
