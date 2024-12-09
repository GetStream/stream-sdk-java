package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class VelocityFilterConfigRule {

  @NotNull
  @JsonProperty("action")
  private String action;

  @NotNull
  @JsonProperty("ban_duration")
  private Integer banDuration;

  @NotNull
  @JsonProperty("cascading_action")
  private String cascadingAction;

  @NotNull
  @JsonProperty("cascading_threshold")
  private Integer cascadingThreshold;

  @NotNull
  @JsonProperty("check_message_context")
  private Boolean checkMessageContext;

  @NotNull
  @JsonProperty("fast_spam_threshold")
  private Integer fastSpamThreshold;

  @NotNull
  @JsonProperty("fast_spam_ttl")
  private Integer fastSpamTtl;

  @NotNull
  @JsonProperty("ip_ban")
  private Boolean ipBan;

  @NotNull
  @JsonProperty("shadow_ban")
  private Boolean shadowBan;

  @NotNull
  @JsonProperty("slow_spam_threshold")
  private Integer slowSpamThreshold;

  @NotNull
  @JsonProperty("slow_spam_ttl")
  private Integer slowSpamTtl;

  @Nullable
  @JsonProperty("slow_spam_ban_duration")
  private Integer slowSpamBanDuration;
}
