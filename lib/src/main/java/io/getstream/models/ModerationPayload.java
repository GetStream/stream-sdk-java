package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class ModerationPayload {

  @Nullable
  @JsonProperty("images")
  private List<String> images;

  @Nullable
  @JsonProperty("texts")
  private List<String> texts;

  @Nullable
  @JsonProperty("videos")
  private List<String> videos;

  @Nullable
  @JsonProperty("custom")
  private Map<String, Object> custom;
}
