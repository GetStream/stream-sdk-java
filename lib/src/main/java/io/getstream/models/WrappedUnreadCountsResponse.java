package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import org.jetbrains.annotations.NotNull;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class WrappedUnreadCountsResponse {

  @NotNull
  @JsonProperty("duration")
  private String duration;

  @NotNull
  @JsonProperty("total_unread_count")
  private Integer totalUnreadCount;

  @NotNull
  @JsonProperty("total_unread_threads_count")
  private Integer totalUnreadThreadsCount;

  @NotNull
  @JsonProperty("channel_type")
  private List<UnreadCountsChannelType> channelType;

  @NotNull
  @JsonProperty("channels")
  private List<UnreadCountsChannel> channels;

  @NotNull
  @JsonProperty("threads")
  private List<UnreadCountsThread> threads;
}
