package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.getstream.models.framework.RateLimit;
import io.getstream.models.framework.StreamResponseWithRateLimit;
import java.util.Date;
import java.util.List;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChannelStateResponse implements StreamResponseWithRateLimit {
  private RateLimit rateLimit;

  @NotNull
  @JsonProperty("duration")
  private String duration;

  @NotNull
  @JsonProperty("members")
  private List<ChannelMember> members;

  @NotNull
  @JsonProperty("messages")
  private List<MessageResponse> messages;

  @NotNull
  @JsonProperty("pinned_messages")
  private List<MessageResponse> pinnedMessages;

  @NotNull
  @JsonProperty("threads")
  private List<ThreadState> threads;

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
  private List<PendingMessage> pendingMessages;

  @Nullable
  @JsonProperty("read")
  private List<ReadStateResponse> read;

  @Nullable
  @JsonProperty("watchers")
  private List<UserResponse> watchers;

  /** Represents channel in chat */
  @Nullable
  @JsonProperty("channel")
  private ChannelResponse channel;

  @Nullable
  @JsonProperty("membership")
  private ChannelMember membership;
}
