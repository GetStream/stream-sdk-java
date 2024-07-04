package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HuaweiConfig {

  @Nullable
  @JsonProperty("Disabled")
  private Boolean disabled;

  @Nullable
  @JsonProperty("id")
  private String id;

  @Nullable
  @JsonProperty("secret")
  private String secret;
}
