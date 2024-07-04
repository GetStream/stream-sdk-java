package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChannelGetOrCreateRequest {

  /** Whether this channel will be hidden for the user who created the channel or not */
  @Nullable
  @JsonProperty("hide_for_creator")
  private Boolean hideForCreator;

  /** Refresh channel state */
  @Nullable
  @JsonProperty("state")
  private Boolean state;

  @Nullable
  @JsonProperty("thread_unread_counts")
  private Boolean threadUnreadCounts;

  @Nullable
  @JsonProperty("data")
  private ChannelInput data;

  @Nullable
  @JsonProperty("members")
  private PaginationParams members;

  @Nullable
  @JsonProperty("messages")
  private MessagePaginationParams messages;

  @Nullable
  @JsonProperty("watchers")
  private PaginationParams watchers;
}
