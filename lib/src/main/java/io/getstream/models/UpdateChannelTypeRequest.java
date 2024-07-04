package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateChannelTypeRequest {

  @NotNull
  @JsonProperty("automod")
  private String automod;

  @NotNull
  @JsonProperty("automod_behavior")
  private String automodBehavior;

  @NotNull
  @JsonProperty("max_message_length")
  private Integer maxMessageLength;

  @Nullable
  @JsonProperty("blocklist")
  private String blocklist;

  @Nullable
  @JsonProperty("blocklist_behavior")
  private String blocklistBehavior;

  @Nullable
  @JsonProperty("connect_events")
  private Boolean connectEvents;

  @Nullable
  @JsonProperty("custom_events")
  private Boolean customEvents;

  @Nullable
  @JsonProperty("mark_messages_pending")
  private Boolean markMessagesPending;

  @Nullable
  @JsonProperty("mutes")
  private Boolean mutes;

  @Nullable
  @JsonProperty("polls")
  private Boolean polls;

  @Nullable
  @JsonProperty("push_notifications")
  private Boolean pushNotifications;

  @Nullable
  @JsonProperty("quotes")
  private Boolean quotes;

  @Nullable
  @JsonProperty("reactions")
  private Boolean reactions;

  @Nullable
  @JsonProperty("read_events")
  private Boolean readEvents;

  @Nullable
  @JsonProperty("reminders")
  private Boolean reminders;

  @Nullable
  @JsonProperty("replies")
  private Boolean replies;

  @Nullable
  @JsonProperty("search")
  private Boolean search;

  @Nullable
  @JsonProperty("typing_events")
  private Boolean typingEvents;

  @Nullable
  @JsonProperty("uploads")
  private Boolean uploads;

  @Nullable
  @JsonProperty("url_enrichment")
  private Boolean urlEnrichment;

  @Nullable
  @JsonProperty("allowed_flag_reasons")
  private List<String> allowedFlagReasons;

  @Nullable
  @JsonProperty("blocklists")
  private List<BlockListOptions> blocklists;

  /** List of commands that channel supports */
  @Nullable
  @JsonProperty("commands")
  private List<String> commands;

  @Nullable
  @JsonProperty("permissions")
  private List<PolicyRequest> permissions;

  /** Sets thresholds for AI moderation */
  @Nullable
  @JsonProperty("automod_thresholds")
  private Thresholds automodThresholds;

  @Nullable
  @JsonProperty("grants")
  private Map<String, List<String>> grants;
}
