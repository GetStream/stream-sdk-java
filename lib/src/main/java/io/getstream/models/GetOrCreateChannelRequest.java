package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class GetOrCreateChannelRequest {

  @Nullable
  @JsonProperty("hide_for_creator")
  private Boolean hideForCreator;

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
