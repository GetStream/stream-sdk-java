package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Subsession {

  @NotNull
  @JsonProperty("ended_at")
  private Integer endedAt;

  @NotNull
  @JsonProperty("joined_at")
  private Integer joinedAt;

  @NotNull
  @JsonProperty("sfu_id")
  private String sfuId;

  @Nullable
  @JsonProperty("pub_sub_hint")
  private MediaPubSubHint pubSubHint;
}
