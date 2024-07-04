package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class APNConfig {

  @Nullable
  @JsonProperty("auth_key")
  private String authKey;

  @Nullable
  @JsonProperty("auth_type")
  private String authType;

  @Nullable
  @JsonProperty("bundle_id")
  private String bundleId;

  @Nullable
  @JsonProperty("development")
  private Boolean development;

  @Nullable
  @JsonProperty("Disabled")
  private Boolean disabled;

  @Nullable
  @JsonProperty("host")
  private String host;

  @Nullable
  @JsonProperty("key_id")
  private String keyId;

  @Nullable
  @JsonProperty("notification_template")
  private String notificationTemplate;

  @Nullable
  @JsonProperty("p12_cert")
  private String p12Cert;

  @Nullable
  @JsonProperty("team_id")
  private String teamId;
}
