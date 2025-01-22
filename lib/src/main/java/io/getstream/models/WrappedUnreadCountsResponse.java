package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class WrappedUnreadCountsResponse {

  @JsonProperty("duration")
  private String duration;

  @JsonProperty("total_unread_count")
  private Integer totalUnreadCount;

  @JsonProperty("total_unread_threads_count")
  private Integer totalUnreadThreadsCount;

  @JsonProperty("channel_type")
  private List<UnreadCountsChannelType> channelType;

  @JsonProperty("channels")
  private List<UnreadCountsChannel> channels;

  @JsonProperty("threads")
  private List<UnreadCountsThread> threads;
}
