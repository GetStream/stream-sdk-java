package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FirebaseConfigFields {

  @NotNull
  @JsonProperty("apn_template")
  private String apnTemplate;

  @NotNull
  @JsonProperty("data_template")
  private String dataTemplate;

  @NotNull
  @JsonProperty("enabled")
  private Boolean enabled;

  @NotNull
  @JsonProperty("notification_template")
  private String notificationTemplate;

  @Nullable
  @JsonProperty("credentials_json")
  private String credentialsJson;

  @Nullable
  @JsonProperty("server_key")
  private String serverKey;
}
