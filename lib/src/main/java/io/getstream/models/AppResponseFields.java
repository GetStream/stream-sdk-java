package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class AppResponseFields {

  @JsonProperty("async_url_enrich_enabled")
  private Boolean asyncUrlEnrichEnabled;

  @JsonProperty("auto_translation_enabled")
  private Boolean autoTranslationEnabled;

  @JsonProperty("campaign_enabled")
  private Boolean campaignEnabled;

  @JsonProperty("cdn_expiration_seconds")
  private Integer cdnExpirationSeconds;

  @JsonProperty("custom_action_handler_url")
  private String customActionHandlerUrl;

  @JsonProperty("disable_auth_checks")
  private Boolean disableAuthChecks;

  @JsonProperty("disable_permissions_checks")
  private Boolean disablePermissionsChecks;

  @JsonProperty("enforce_unique_usernames")
  private String enforceUniqueUsernames;

  @JsonProperty("image_moderation_enabled")
  private Boolean imageModerationEnabled;

  @JsonProperty("moderation_enabled")
  private Boolean moderationEnabled;

  @JsonProperty("moderation_webhook_url")
  private String moderationWebhookUrl;

  @JsonProperty("multi_tenant_enabled")
  private Boolean multiTenantEnabled;

  @JsonProperty("name")
  private String name;

  @JsonProperty("organization")
  private String organization;

  @JsonProperty("permission_version")
  private String permissionVersion;

  @JsonProperty("reminders_interval")
  private Integer remindersInterval;

  @JsonProperty("sns_key")
  private String snsKey;

  @JsonProperty("sns_secret")
  private String snsSecret;

  @JsonProperty("sns_topic_arn")
  private String snsTopicArn;

  @JsonProperty("sqs_key")
  private String sqsKey;

  @JsonProperty("sqs_secret")
  private String sqsSecret;

  @JsonProperty("sqs_url")
  private String sqsUrl;

  @JsonProperty("suspended")
  private Boolean suspended;

  @JsonProperty("suspended_explanation")
  private String suspendedExplanation;

  @JsonProperty("webhook_url")
  private String webhookUrl;

  @JsonProperty("user_search_disallowed_roles")
  private List<String> userSearchDisallowedRoles;

  @JsonProperty("webhook_events")
  private List<String> webhookEvents;

  @JsonProperty("call_types")
  private Map<String, CallType> callTypes;

  @JsonProperty("channel_configs")
  private Map<String, ChannelConfig> channelConfigs;

  @JsonProperty("file_upload_config")
  private FileUploadConfig fileUploadConfig;

  @JsonProperty("grants")
  private Map<String, List<String>> grants;

  @JsonProperty("image_upload_config")
  private FileUploadConfig imageUploadConfig;

  @JsonProperty("policies")
  private Map<String, List<Policy>> policies;

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
  @JsonProperty("datadog_info")
  private DataDogInfo datadogInfo;
}
