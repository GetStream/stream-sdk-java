package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.getstream.models.framework.RateLimit;
import io.getstream.models.framework.StreamResponseWithRateLimit;
import java.util.List;
import lombok.*;
import org.jetbrains.annotations.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UnreadCountsResponse implements StreamResponseWithRateLimit {
  private RateLimit rateLimit;

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
