package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataDogInfo {

  @Nullable
  @JsonProperty("api_key")
  private String apiKey;

  @Nullable
  @JsonProperty("enabled")
  private Boolean enabled;

  @Nullable
  @JsonProperty("site")
  private String site;
}
