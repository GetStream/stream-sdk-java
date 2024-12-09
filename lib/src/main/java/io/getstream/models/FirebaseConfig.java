package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class FirebaseConfig {

  @Nullable
  @JsonProperty("apn_template")
  private String apnTemplate;

  @Nullable
  @JsonProperty("credentials_json")
  private String credentialsJson;

  @Nullable
  @JsonProperty("data_template")
  private String dataTemplate;

  @Nullable
  @JsonProperty("Disabled")
  private Boolean disabled;

  @Nullable
  @JsonProperty("notification_template")
  private String notificationTemplate;

  @Nullable
  @JsonProperty("server_key")
  private String serverKey;
}
