package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class GetReviewQueueItemResponse {

  @JsonProperty("duration")
  private String duration;

  @JsonProperty("history")
  private List<ReviewQueueItemResponse> history;

  @Nullable
  @JsonProperty("item")
  private ReviewQueueItemResponse item;
}
