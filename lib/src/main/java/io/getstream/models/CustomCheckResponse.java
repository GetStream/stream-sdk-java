package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class CustomCheckResponse {

  @JsonProperty("duration")
  private String duration;

  @JsonProperty("id")
  private String id;

  @JsonProperty("status")
  private String status;

  @Nullable
  @JsonProperty("item")
  private ReviewQueueItemResponse item;
}
