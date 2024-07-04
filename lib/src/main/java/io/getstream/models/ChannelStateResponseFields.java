package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.List;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChannelStateResponseFields {

  /** List of channel members */
  @NotNull
  @JsonProperty("members")
  private List<ChannelMember> members;

  /** List of channel messages */
  @NotNull
  @JsonProperty("messages")
  private List<MessageResponse> messages;

  /** List of pinned messages in the channel */
  @NotNull
  @JsonProperty("pinned_messages")
  private List<MessageResponse> pinnedMessages;

  @NotNull
  @JsonProperty("threads")
  private List<ThreadState> threads;

  /** Whether this channel is hidden or not */
  @Nullable
  @JsonProperty("hidden")
  private Boolean hidden;

  /** Messages before this date are hidden from the user */
  @Nullable
  @JsonProperty("hide_messages_before")
  private Date hideMessagesBefore;

  /** Number of channel watchers */
  @Nullable
  @JsonProperty("watcher_count")
  private Integer watcherCount;

  /** Pending messages that this user has sent */
  @Nullable
  @JsonProperty("pending_messages")
  private List<PendingMessage> pendingMessages;

  /** List of read states */
  @Nullable
  @JsonProperty("read")
  private List<ReadStateResponse> read;

  /** List of user who is watching the channel */
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
