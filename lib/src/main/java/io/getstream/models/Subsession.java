package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class Subsession {

  @NotNull
  @JsonProperty("ended_at")
  private Integer endedAt;

  @NotNull
  @JsonProperty("joined_at")
  private Integer joinedAt;

  @NotNull
  @JsonProperty("sfu_id")
  private String sfuID;

  @Nullable
  @JsonProperty("pub_sub_hint")
  private MediaPubSubHint pubSubHint;
}
