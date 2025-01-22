package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class VelocityFilterConfigRule {

  @JsonProperty("action")
  private String action;

  @JsonProperty("ban_duration")
  private Integer banDuration;

  @JsonProperty("cascading_action")
  private String cascadingAction;

  @JsonProperty("cascading_threshold")
  private Integer cascadingThreshold;

  @JsonProperty("check_message_context")
  private Boolean checkMessageContext;

  @JsonProperty("fast_spam_threshold")
  private Integer fastSpamThreshold;

  @JsonProperty("fast_spam_ttl")
  private Integer fastSpamTtl;

  @JsonProperty("ip_ban")
  private Boolean ipBan;

  @JsonProperty("shadow_ban")
  private Boolean shadowBan;

  @JsonProperty("slow_spam_threshold")
  private Integer slowSpamThreshold;

  @JsonProperty("slow_spam_ttl")
  private Integer slowSpamTtl;

  @Nullable
  @JsonProperty("slow_spam_ban_duration")
  private Integer slowSpamBanDuration;
}
