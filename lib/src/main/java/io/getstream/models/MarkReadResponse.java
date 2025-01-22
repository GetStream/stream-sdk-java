package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class MarkReadResponse {

  @JsonProperty("duration")
  private String duration;

  @Nullable
  @JsonProperty("event")
  private MessageReadEvent event;
}
