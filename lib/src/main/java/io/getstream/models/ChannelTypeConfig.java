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
public class ChannelTypeConfig {

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
  @JsonProperty("skip_last_msg_update_for_system_msgs")
  private Boolean skipLastMsgUpdateForSystemMsgs;

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

  @NotNull
  @JsonProperty("commands")
  private List<Command> commands;

  @NotNull
  @JsonProperty("permissions")
  private List<PolicyRequest> permissions;

  @NotNull
  @JsonProperty("grants")
  private Map<String, List<String>> grants;

  @Nullable
  @JsonProperty("blocklist")
  private String blocklist;

  @Nullable
  @JsonProperty("blocklist_behavior")
  private String blocklistBehavior;

  @Nullable
  @JsonProperty("partition_size")
  private Integer partitionSize;

  @Nullable
  @JsonProperty("partition_ttl")
  private String partitionTtl;

  @Nullable
  @JsonProperty("allowed_flag_reasons")
  private List<String> allowedFlagReasons;

  @Nullable
  @JsonProperty("blocklists")
  private List<BlockListOptions> blocklists;

  @Nullable
  @JsonProperty("automod_thresholds")
  private Thresholds automodThresholds;
}
