package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class UnreadCountsChannelType {

  @NotNull
  @JsonProperty("channel_count")
  private Integer channelCount;

  @NotNull
  @JsonProperty("channel_type")
  private String channelType;

  @NotNull
  @JsonProperty("unread_count")
  private Integer unreadCount;
}
