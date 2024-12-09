package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class Config {

  @NotNull
  @JsonProperty("app_certificate")
  private String appCertificate;

  @NotNull
  @JsonProperty("app_id")
  private String appID;

  @Nullable
  @JsonProperty("default_role")
  private String defaultRole;

  @Nullable
  @JsonProperty("role_map")
  private Map<String, String> roleMap;
}
