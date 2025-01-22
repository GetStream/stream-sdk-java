package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class UnreadCountsChannelType {

  @JsonProperty("channel_count")
  private Integer channelCount;

  @JsonProperty("channel_type")
  private String channelType;

  @JsonProperty("unread_count")
  private Integer unreadCount;
}
