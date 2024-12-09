package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class CheckPushRequest {

  @Nullable
  @JsonProperty("apn_template")
  private String apnTemplate;

  @Nullable
  @JsonProperty("firebase_data_template")
  private String firebaseDataTemplate;

  @Nullable
  @JsonProperty("firebase_template")
  private String firebaseTemplate;

  @Nullable
  @JsonProperty("message_id")
  private String messageID;

  @Nullable
  @JsonProperty("push_provider_name")
  private String pushProviderName;

  @Nullable
  @JsonProperty("push_provider_type")
  private String pushProviderType;

  @Nullable
  @JsonProperty("skip_devices")
  private Boolean skipDevices;

  @Nullable
  @JsonProperty("user_id")
  private String userID;

  @Nullable
  @JsonProperty("user")
  private UserRequest user;
}
