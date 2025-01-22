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
public class CreateChannelTypeResponse {

  @JsonProperty("automod")
  private String automod;

  @JsonProperty("automod_behavior")
  private String automodBehavior;

  @JsonProperty("connect_events")
  private Boolean connectEvents;

  @JsonProperty("created_at")
  private Date createdAt;

  @JsonProperty("custom_events")
  private Boolean customEvents;

  @JsonProperty("duration")
  private String duration;

  @JsonProperty("mark_messages_pending")
  private Boolean markMessagesPending;

  @JsonProperty("max_message_length")
  private Integer maxMessageLength;

  @JsonProperty("mutes")
  private Boolean mutes;

  @JsonProperty("name")
  private String name;

  @JsonProperty("polls")
  private Boolean polls;

  @JsonProperty("push_notifications")
  private Boolean pushNotifications;

  @JsonProperty("quotes")
  private Boolean quotes;

  @JsonProperty("reactions")
  private Boolean reactions;

  @JsonProperty("read_events")
  private Boolean readEvents;

  @JsonProperty("reminders")
  private Boolean reminders;

  @JsonProperty("replies")
  private Boolean replies;

  @JsonProperty("search")
  private Boolean search;

  @JsonProperty("skip_last_msg_update_for_system_msgs")
  private Boolean skipLastMsgUpdateForSystemMsgs;

  @JsonProperty("typing_events")
  private Boolean typingEvents;

  @JsonProperty("updated_at")
  private Date updatedAt;

  @JsonProperty("uploads")
  private Boolean uploads;

  @JsonProperty("url_enrichment")
  private Boolean urlEnrichment;

  @JsonProperty("commands")
  private List<String> commands;

  @JsonProperty("permissions")
  private List<PolicyRequest> permissions;

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
