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
public class CallResponse {

  @NotNull
  @JsonProperty("backstage")
  private Boolean backstage;

  @NotNull
  @JsonProperty("captioning")
  private Boolean captioning;

  @NotNull
  @JsonProperty("cid")
  private String cid;

  @NotNull
  @JsonProperty("created_at")
  private Date createdAt;

  @NotNull
  @JsonProperty("current_session_id")
  private String currentSessionID;

  @NotNull
  @JsonProperty("id")
  private String id;

  @NotNull
  @JsonProperty("recording")
  private Boolean recording;

  @NotNull
  @JsonProperty("transcribing")
  private Boolean transcribing;

  @NotNull
  @JsonProperty("type")
  private String type;

  @NotNull
  @JsonProperty("updated_at")
  private Date updatedAt;

  @NotNull
  @JsonProperty("blocked_user_ids")
  private List<String> blockedUserIds;

  @NotNull
  @JsonProperty("created_by")
  private UserResponse createdBy;

  @NotNull
  @JsonProperty("custom")
  private Map<String, Object> custom;

  @NotNull
  @JsonProperty("egress")
  private EgressResponse egress;

  @NotNull
  @JsonProperty("ingress")
  private CallIngressResponse ingress;

  @NotNull
  @JsonProperty("settings")
  private CallSettingsResponse settings;

  @Nullable
  @JsonProperty("ended_at")
  private Date endedAt;

  @Nullable
  @JsonProperty("join_ahead_time_seconds")
  private Integer joinAheadTimeSeconds;

  @Nullable
  @JsonProperty("starts_at")
  private Date startsAt;

  @Nullable
  @JsonProperty("team")
  private String team;

  @Nullable
  @JsonProperty("session")
  private CallSessionResponse session;

  @Nullable
  @JsonProperty("thumbnails")
  private ThumbnailResponse thumbnails;
}
