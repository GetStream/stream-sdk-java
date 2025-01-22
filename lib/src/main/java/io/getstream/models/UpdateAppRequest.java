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
public class UpdateAppRequest {

  @Nullable
  @JsonProperty("async_url_enrich_enabled")
  private Boolean asyncUrlEnrichEnabled;

  @Nullable
  @JsonProperty("auto_translation_enabled")
  private Boolean autoTranslationEnabled;

  @Nullable
  @JsonProperty("before_message_send_hook_url")
  private String beforeMessageSendHookUrl;

  @Nullable
  @JsonProperty("cdn_expiration_seconds")
  private Integer cdnExpirationSeconds;

  @Nullable
  @JsonProperty("channel_hide_members_only")
  private Boolean channelHideMembersOnly;

  @Nullable
  @JsonProperty("custom_action_handler_url")
  private String customActionHandlerUrl;

  @Nullable
  @JsonProperty("disable_auth_checks")
  private Boolean disableAuthChecks;

  @Nullable
  @JsonProperty("disable_permissions_checks")
  private Boolean disablePermissionsChecks;

  @Nullable
  @JsonProperty("enforce_unique_usernames")
  private String enforceUniqueUsernames;

  @Nullable
  @JsonProperty("feeds_moderation_enabled")
  private Boolean feedsModerationEnabled;

  @Nullable
  @JsonProperty("feeds_v2_region")
  private String feedsV2Region;

  @Nullable
  @JsonProperty("image_moderation_enabled")
  private Boolean imageModerationEnabled;

  @Nullable
  @JsonProperty("migrate_permissions_to_v2")
  private Boolean migratePermissionsToV2;

  @Nullable
  @JsonProperty("moderation_enabled")
  private Boolean moderationEnabled;

  @Nullable
  @JsonProperty("moderation_webhook_url")
  private String moderationWebhookUrl;

  @Nullable
  @JsonProperty("multi_tenant_enabled")
  private Boolean multiTenantEnabled;

  @Nullable
  @JsonProperty("permission_version")
  private String permissionVersion;

  @Nullable
  @JsonProperty("reminders_interval")
  private Integer remindersInterval;

  @Nullable
  @JsonProperty("reminders_max_members")
  private Integer remindersMaxMembers;

  @Nullable
  @JsonProperty("revoke_tokens_issued_before")
  private Date revokeTokensIssuedBefore;

  @Nullable
  @JsonProperty("sns_key")
  private String snsKey;

  @Nullable
  @JsonProperty("sns_secret")
  private String snsSecret;

  @Nullable
  @JsonProperty("sns_topic_arn")
  private String snsTopicArn;

  @Nullable
  @JsonProperty("sqs_key")
  private String sqsKey;

  @Nullable
  @JsonProperty("sqs_secret")
  private String sqsSecret;

  @Nullable
  @JsonProperty("sqs_url")
  private String sqsUrl;

  @Nullable
  @JsonProperty("webhook_url")
  private String webhookUrl;

  @Nullable
  @JsonProperty("allowed_flag_reasons")
  private List<String> allowedFlagReasons;

  @Nullable
  @JsonProperty("image_moderation_block_labels")
  private List<String> imageModerationBlockLabels;

  @Nullable
  @JsonProperty("image_moderation_labels")
  private List<String> imageModerationLabels;

  @Nullable
  @JsonProperty("user_search_disallowed_roles")
  private List<String> userSearchDisallowedRoles;

  @Nullable
  @JsonProperty("webhook_events")
  private List<String> webhookEvents;

  @Nullable
  @JsonProperty("apn_config")
  private APNConfig apnConfig;

  @Nullable
  @JsonProperty("async_moderation_config")
  private AsyncModerationConfiguration asyncModerationConfig;

  @Nullable
  @JsonProperty("datadog_info")
  private DataDogInfo datadogInfo;

  @Nullable
  @JsonProperty("file_upload_config")
  private FileUploadConfig fileUploadConfig;

  @Nullable
  @JsonProperty("firebase_config")
  private FirebaseConfig firebaseConfig;

  @Nullable
  @JsonProperty("grants")
  private Map<String, List<String>> grants;

  @Nullable
  @JsonProperty("huawei_config")
  private HuaweiConfig huaweiConfig;

  @Nullable
  @JsonProperty("image_upload_config")
  private FileUploadConfig imageUploadConfig;

  @Nullable
  @JsonProperty("push_config")
  private PushConfig pushConfig;

  @Nullable
  @JsonProperty("xiaomi_config")
  private XiaomiConfig xiaomiConfig;
}
