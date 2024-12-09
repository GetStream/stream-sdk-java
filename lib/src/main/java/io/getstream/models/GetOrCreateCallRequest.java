package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class GetOrCreateCallRequest {

  @Nullable
  @JsonProperty("members_limit")
  private Integer membersLimit;

  @Nullable
  @JsonProperty("notify")
  private Boolean notify;

  @Nullable
  @JsonProperty("ring")
  private Boolean ring;

  @Nullable
  @JsonProperty("video")
  private Boolean video;

  @Nullable
  @JsonProperty("data")
  private CallRequest data;
}
