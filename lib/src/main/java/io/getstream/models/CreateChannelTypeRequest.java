package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class CreateChannelTypeRequest {

  @NotNull
  @JsonProperty("automod")
  private String automod;

  @NotNull
  @JsonProperty("automod_behavior")
  private String automodBehavior;

  @NotNull
  @JsonProperty("max_message_length")
  private Integer maxMessageLength;

  @NotNull
  @JsonProperty("name")
  private String name;

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
  @JsonProperty("message_retention")
  private String messageRetention;

  @Nullable
  @JsonProperty("mutes")
  private Boolean mutes;

  @Nullable
  @JsonProperty("partition_size")
  private Integer partitionSize;

  @Nullable
  @JsonProperty("partition_ttl")
  private String partitionTtl;

  @Nullable
  @JsonProperty("polls")
  private Boolean polls;

  @Nullable
  @JsonProperty("push_notifications")
  private Boolean pushNotifications;

  @Nullable
  @JsonProperty("reactions")
  private Boolean reactions;

  @Nullable
  @JsonProperty("read_events")
  private Boolean readEvents;

  @Nullable
  @JsonProperty("replies")
  private Boolean replies;

  @Nullable
  @JsonProperty("search")
  private Boolean search;

  @Nullable
  @JsonProperty("skip_last_msg_update_for_system_msgs")
  private Boolean skipLastMsgUpdateForSystemMsgs;

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
  @JsonProperty("blocklists")
  private List<BlockListOptions> blocklists;

  @Nullable
  @JsonProperty("commands")
  private List<String> commands;

  @Nullable
  @JsonProperty("permissions")
  private List<PolicyRequest> permissions;

  @Nullable
  @JsonProperty("grants")
  private Map<String, List<String>> grants;
}
