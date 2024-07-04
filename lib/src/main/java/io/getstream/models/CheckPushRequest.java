package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckPushRequest {

  /** Push message template for APN */
  @Nullable
  @JsonProperty("apn_template")
  private String apnTemplate;

  /** Push message data template for Firebase */
  @Nullable
  @JsonProperty("firebase_data_template")
  private String firebaseDataTemplate;

  /** Push message template for Firebase */
  @Nullable
  @JsonProperty("firebase_template")
  private String firebaseTemplate;

  /** Message ID to send push notification for */
  @Nullable
  @JsonProperty("message_id")
  private String messageId;

  /** Name of push provider */
  @Nullable
  @JsonProperty("push_provider_name")
  private String pushProviderName;

  /** Push provider type */
  @Nullable
  @JsonProperty("push_provider_type")
  private String pushProviderType;

  /** Don't require existing devices to render templates */
  @Nullable
  @JsonProperty("skip_devices")
  private Boolean skipDevices;

  @Nullable
  @JsonProperty("user_id")
  private String userId;

  @Nullable
  @JsonProperty("user")
  private UserRequest user;
}
