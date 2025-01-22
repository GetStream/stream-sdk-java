package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class CustomCheckFlag {

  @JsonProperty("type")
  private String type;

  @Nullable
  @JsonProperty("reason")
  private String reason;

  @Nullable
  @JsonProperty("labels")
  private List<String> labels;

  @Nullable
  @JsonProperty("custom")
  private Map<String, Object> custom;
}
