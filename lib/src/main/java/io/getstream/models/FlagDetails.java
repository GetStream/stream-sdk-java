package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class FlagDetails {

  @JsonProperty("original_text")
  private String originalText;

  @JsonProperty("Extra")
  private Map<String, Object> extra;

  @Nullable
  @JsonProperty("automod")
  private AutomodDetails automod;
}
