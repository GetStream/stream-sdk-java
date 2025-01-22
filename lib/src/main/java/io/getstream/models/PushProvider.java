package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class PushProvider {

  @JsonProperty("created_at")
  private Date createdAt;

  @JsonProperty("name")
  private String name;

  @JsonProperty("type")
  private String type;

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
  private String apnKeyID;

  @Nullable
  @JsonProperty("apn_notification_template")
  private String apnNotificationTemplate;

  @Nullable
  @JsonProperty("apn_p12_cert")
  private String apnP12Cert;

  @Nullable
  @JsonProperty("apn_team_id")
  private String apnTeamID;

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
  private String huaweiAppID;

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
