package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class FlagDetails {

  @NotNull
  @JsonProperty("original_text")
  private String originalText;

  @NotNull
  @JsonProperty("Extra")
  private Map<String, Object> extra;

  @Nullable
  @JsonProperty("automod")
  private AutomodDetails automod;
}
