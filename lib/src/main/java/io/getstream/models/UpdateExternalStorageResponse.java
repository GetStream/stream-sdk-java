package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.getstream.models.framework.RateLimit;
import io.getstream.models.framework.StreamResponseWithRateLimit;
import lombok.*;
import org.jetbrains.annotations.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateExternalStorageResponse implements StreamResponseWithRateLimit {
  private RateLimit rateLimit;

  @NotNull
  @JsonProperty("bucket")
  private String bucket;

  /** Duration of the request in human-readable format */
  @NotNull
  @JsonProperty("duration")
  private String duration;

  @NotNull
  @JsonProperty("name")
  private String name;

  @NotNull
  @JsonProperty("path")
  private String path;

  @NotNull
  @JsonProperty("type")
  private String type;
}
