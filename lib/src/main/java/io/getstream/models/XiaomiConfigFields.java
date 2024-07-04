package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class XiaomiConfigFields {

  @NotNull
  @JsonProperty("enabled")
  private Boolean enabled;

  @Nullable
  @JsonProperty("package_name")
  private String packageName;

  @Nullable
  @JsonProperty("secret")
  private String secret;
}
