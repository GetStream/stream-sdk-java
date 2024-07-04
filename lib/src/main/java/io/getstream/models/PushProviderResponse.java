package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.getstream.models.framework.RateLimit;
import io.getstream.models.framework.StreamResponseWithRateLimit;
import java.util.Date;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PushProviderResponse implements StreamResponseWithRateLimit {
  private RateLimit rateLimit;

  @NotNull
  @JsonProperty("created_at")
  private Date createdAt;

  @NotNull
  @JsonProperty("name")
  private String name;

  @NotNull
  @JsonProperty("type")
  private String type;

  @NotNull
  @JsonProperty("updated_at")
  private Date updatedAt;

  @Nullable
  @JsonProperty("apn_auth_key")
  private String apnAuthKey;

  @Nullable
  @JsonProperty("apn_auth_type")
  private String apnAuthType;

  @Nullable
  @JsonProperty("apn_development")
  private Boolean apnDevelopment;

  @Nullable
  @JsonProperty("apn_host")
  private String apnHost;

  @Nullable
  @JsonProperty("apn_key_id")
  private String apnKeyId;

  @Nullable
  @JsonProperty("apn_p12_cert")
  private String apnP12Cert;

  @Nullable
  @JsonProperty("apn_sandbox_certificate")
  private Boolean apnSandboxCertificate;

  @Nullable
  @JsonProperty("apn_supports_remote_notifications")
  private Boolean apnSupportsRemoteNotifications;

  @Nullable
  @JsonProperty("apn_supports_voip_notifications")
  private Boolean apnSupportsVoipNotifications;

  @Nullable
  @JsonProperty("apn_team_id")
  private String apnTeamId;

  @Nullable
  @JsonProperty("apn_topic")
  private String apnTopic;

  @Nullable
  @JsonProperty("description")
  private String description;

  @Nullable
  @JsonProperty("disabled_at")
  private Date disabledAt;

  @Nullable
  @JsonProperty("disabled_reason")
  private String disabledReason;

  @Nullable
  @JsonProperty("firebase_apn_template")
  private String firebaseApnTemplate;

  @Nullable
  @JsonProperty("firebase_credentials")
  private String firebaseCredentials;

  @Nullable
  @JsonProperty("firebase_data_template")
  private String firebaseDataTemplate;

  @Nullable
  @JsonProperty("firebase_host")
  private String firebaseHost;

  @Nullable
  @JsonProperty("firebase_notification_template")
  private String firebaseNotificationTemplate;

  @Nullable
  @JsonProperty("firebase_server_key")
  private String firebaseServerKey;

  @Nullable
  @JsonProperty("huawei_app_id")
  private String huaweiAppId;

  @Nullable
  @JsonProperty("huawei_app_secret")
  private String huaweiAppSecret;

  @Nullable
  @JsonProperty("xiaomi_app_secret")
  private String xiaomiAppSecret;

  @Nullable
  @JsonProperty("xiaomi_package_name")
  private String xiaomiPackageName;
}
