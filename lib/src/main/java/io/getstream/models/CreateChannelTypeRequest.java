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
public class CreateChannelTypeRequest {

  /** Enables automatic message moderation */
  @NotNull
  @JsonProperty("automod")
  private String automod;

  /** Sets behavior of automatic moderation */
  @NotNull
  @JsonProperty("automod_behavior")
  private String automodBehavior;

  /** Number of maximum message characters */
  @NotNull
  @JsonProperty("max_message_length")
  private Integer maxMessageLength;

  /** Channel type name */
  @NotNull
  @JsonProperty("name")
  private String name;

  /** Name of the blocklist to use */
  @Nullable
  @JsonProperty("blocklist")
  private String blocklist;

  /** Sets behavior of blocklist */
  @Nullable
  @JsonProperty("blocklist_behavior")
  private String blocklistBehavior;

  /** Connect events support */
  @Nullable
  @JsonProperty("connect_events")
  private Boolean connectEvents;

  /** Enables custom events */
  @Nullable
  @JsonProperty("custom_events")
  private Boolean customEvents;

  /** Marks messages as pending by default */
  @Nullable
  @JsonProperty("mark_messages_pending")
  private Boolean markMessagesPending;

  @Nullable
  @JsonProperty("message_retention")
  private String messageRetention;

  /** Enables mutes */
  @Nullable
  @JsonProperty("mutes")
  private Boolean mutes;

  /** Enables polls */
  @Nullable
  @JsonProperty("polls")
  private Boolean polls;

  /** Enables push notifications */
  @Nullable
  @JsonProperty("push_notifications")
  private Boolean pushNotifications;

  /** Enables message reactions */
  @Nullable
  @JsonProperty("reactions")
  private Boolean reactions;

  /** Read events support */
  @Nullable
  @JsonProperty("read_events")
  private Boolean readEvents;

  /** Enables message replies (threads) */
  @Nullable
  @JsonProperty("replies")
  private Boolean replies;

  /** Enables message search */
  @Nullable
  @JsonProperty("search")
  private Boolean search;

  /** Typing events support */
  @Nullable
  @JsonProperty("typing_events")
  private Boolean typingEvents;

  /** Enables file uploads */
  @Nullable
  @JsonProperty("uploads")
  private Boolean uploads;

  /** Enables URL enrichment */
  @Nullable
  @JsonProperty("url_enrichment")
  private Boolean urlEnrichment;

  @Nullable
  @JsonProperty("blocklists")
  private List<BlockListOptions> blocklists;

  /** List of commands that channel supports */
  @Nullable
  @JsonProperty("commands")
  private List<String> commands;

  /** List of permissions for the channel type */
  @Nullable
  @JsonProperty("permissions")
  private List<PolicyRequest> permissions;

  @Nullable
  @JsonProperty("grants")
  private Map<String, List<String>> grants;
}
