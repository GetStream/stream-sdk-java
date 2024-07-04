package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.List;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChannelConfig {

  @NotNull
  @JsonProperty("automod")
  private String automod;

  @NotNull
  @JsonProperty("automod_behavior")
  private String automodBehavior;

  @NotNull
  @JsonProperty("connect_events")
  private Boolean connectEvents;

  @NotNull
  @JsonProperty("created_at")
  private Date createdAt;

  @NotNull
  @JsonProperty("custom_events")
  private Boolean customEvents;

  @NotNull
  @JsonProperty("mark_messages_pending")
  private Boolean markMessagesPending;

  @NotNull
  @JsonProperty("max_message_length")
  private Integer maxMessageLength;

  @NotNull
  @JsonProperty("mutes")
  private Boolean mutes;

  @NotNull
  @JsonProperty("name")
  private String name;

  @NotNull
  @JsonProperty("polls")
  private Boolean polls;

  @NotNull
  @JsonProperty("push_notifications")
  private Boolean pushNotifications;

  @NotNull
  @JsonProperty("quotes")
  private Boolean quotes;

  @NotNull
  @JsonProperty("reactions")
  private Boolean reactions;

  @NotNull
  @JsonProperty("read_events")
  private Boolean readEvents;

  @NotNull
  @JsonProperty("reminders")
  private Boolean reminders;

  @NotNull
  @JsonProperty("replies")
  private Boolean replies;

  @NotNull
  @JsonProperty("search")
  private Boolean search;

  @NotNull
  @JsonProperty("typing_events")
  private Boolean typingEvents;

  @NotNull
  @JsonProperty("updated_at")
  private Date updatedAt;

  @NotNull
  @JsonProperty("uploads")
  private Boolean uploads;

  @NotNull
  @JsonProperty("url_enrichment")
  private Boolean urlEnrichment;

  /** List of commands that channel supports */
  @NotNull
  @JsonProperty("commands")
  private List<String> commands;

  @Nullable
  @JsonProperty("blocklist")
  private String blocklist;

  @Nullable
  @JsonProperty("blocklist_behavior")
  private String blocklistBehavior;

  @Nullable
  @JsonProperty("allowed_flag_reasons")
  private List<String> allowedFlagReasons;

  @Nullable
  @JsonProperty("blocklists")
  private List<BlockListOptions> blocklists;

  /** Sets thresholds for AI moderation */
  @Nullable
  @JsonProperty("automod_thresholds")
  private Thresholds automodThresholds;
}
