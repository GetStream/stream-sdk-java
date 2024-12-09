package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class AppResponseFields {

  @NotNull
  @JsonProperty("async_url_enrich_enabled")
  private Boolean asyncUrlEnrichEnabled;

  @NotNull
  @JsonProperty("auto_translation_enabled")
  private Boolean autoTranslationEnabled;

  @NotNull
  @JsonProperty("campaign_enabled")
  private Boolean campaignEnabled;

  @NotNull
  @JsonProperty("cdn_expiration_seconds")
  private Integer cdnExpirationSeconds;

  @NotNull
  @JsonProperty("custom_action_handler_url")
  private String customActionHandlerUrl;

  @NotNull
  @JsonProperty("disable_auth_checks")
  private Boolean disableAuthChecks;

  @NotNull
  @JsonProperty("disable_permissions_checks")
  private Boolean disablePermissionsChecks;

  @NotNull
  @JsonProperty("enforce_unique_usernames")
  private String enforceUniqueUsernames;

  @NotNull
  @JsonProperty("image_moderation_enabled")
  private Boolean imageModerationEnabled;

  @NotNull
  @JsonProperty("moderation_enabled")
  private Boolean moderationEnabled;

  @NotNull
  @JsonProperty("moderation_webhook_url")
  private String moderationWebhookUrl;

  @NotNull
  @JsonProperty("multi_tenant_enabled")
  private Boolean multiTenantEnabled;

  @NotNull
  @JsonProperty("name")
  private String name;

  @NotNull
  @JsonProperty("organization")
  private String organization;

  @NotNull
  @JsonProperty("permission_version")
  private String permissionVersion;

  @NotNull
  @JsonProperty("reminders_interval")
  private Integer remindersInterval;

  @NotNull
  @JsonProperty("sns_key")
  private String snsKey;

  @NotNull
  @JsonProperty("sns_secret")
  private String snsSecret;

  @NotNull
  @JsonProperty("sns_topic_arn")
  private String snsTopicArn;

  @NotNull
  @JsonProperty("sqs_key")
  private String sqsKey;

  @NotNull
  @JsonProperty("sqs_secret")
  private String sqsSecret;

  @NotNull
  @JsonProperty("sqs_url")
  private String sqsUrl;

  @NotNull
  @JsonProperty("suspended")
  private Boolean suspended;

  @NotNull
  @JsonProperty("suspended_explanation")
  private String suspendedExplanation;

  @NotNull
  @JsonProperty("video_provider")
  private String videoProvider;

  @NotNull
  @JsonProperty("webhook_url")
  private String webhookUrl;

  @NotNull
  @JsonProperty("user_search_disallowed_roles")
  private List<String> userSearchDisallowedRoles;

  @NotNull
  @JsonProperty("webhook_events")
  private List<String> webhookEvents;

  @NotNull
  @JsonProperty("call_types")
  private Map<String, CallType> callTypes;

  @NotNull
  @JsonProperty("channel_configs")
  private Map<String, ChannelConfig> channelConfigs;

  @NotNull
  @JsonProperty("file_upload_config")
  private FileUploadConfig fileUploadConfig;

  @NotNull
  @JsonProperty("grants")
  private Map<String, List<String>> grants;

  @NotNull
  @JsonProperty("image_upload_config")
  private FileUploadConfig imageUploadConfig;

  @NotNull
  @JsonProperty("policies")
  private Map<String, List<Policy>> policies;

  @NotNull
  @JsonProperty("push_notifications")
  private PushNotificationFields pushNotifications;

  @Nullable
  @JsonProperty("before_message_send_hook_url")
  private String beforeMessageSendHookUrl;

  @Nullable
  @JsonProperty("revoke_tokens_issued_before")
  private Date revokeTokensIssuedBefore;

  @Nullable
  @JsonProperty("allowed_flag_reasons")
  private List<String> allowedFlagReasons;

  @Nullable
  @JsonProperty("geofences")
  private List<GeofenceResponse> geofences;

  @Nullable
  @JsonProperty("image_moderation_labels")
  private List<String> imageModerationLabels;

  @Nullable
  @JsonProperty("agora_options")
  private Config agoraOptions;

  @Nullable
  @JsonProperty("datadog_info")
  private DataDogInfo datadogInfo;

  @Nullable
  @JsonProperty("hms_options")
  private Config hmsOptions;
}
