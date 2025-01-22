package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class HuaweiConfigFields {

  @JsonProperty("enabled")
  private Boolean enabled;

  @Nullable
  @JsonProperty("id")
  private String id;

  @Nullable
  @JsonProperty("secret")
  private String secret;
}
