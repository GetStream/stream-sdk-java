package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HuaweiConfigFields {

  @NotNull
  @JsonProperty("enabled")
  private Boolean enabled;

  @Nullable
  @JsonProperty("id")
  private String id;

  @Nullable
  @JsonProperty("secret")
  private String secret;
}
