package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class APNConfigFields {

  @JsonProperty("development")
  private Boolean development;

  @JsonProperty("enabled")
  private Boolean enabled;

  @JsonProperty("notification_template")
  private String notificationTemplate;

  @Nullable
  @JsonProperty("auth_key")
  private String authKey;

  @Nullable
  @JsonProperty("auth_type")
  private String authType;

  @Nullable
  @JsonProperty("bundle_id")
  private String bundleID;

  @Nullable
  @JsonProperty("host")
  private String host;

  @Nullable
  @JsonProperty("key_id")
  private String keyID;

  @Nullable
  @JsonProperty("p12_cert")
  private String p12Cert;

  @Nullable
  @JsonProperty("team_id")
  private String teamID;
}
