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
public class CallResponse {

  @JsonProperty("backstage")
  private Boolean backstage;

  @JsonProperty("captioning")
  private Boolean captioning;

  @JsonProperty("cid")
  private String cid;

  @JsonProperty("created_at")
  private Date createdAt;

  @JsonProperty("current_session_id")
  private String currentSessionID;

  @JsonProperty("id")
  private String id;

  @JsonProperty("recording")
  private Boolean recording;

  @JsonProperty("transcribing")
  private Boolean transcribing;

  @JsonProperty("type")
  private String type;

  @JsonProperty("updated_at")
  private Date updatedAt;

  @JsonProperty("blocked_user_ids")
  private List<String> blockedUserIds;

  @JsonProperty("created_by")
  private UserResponse createdBy;

  @JsonProperty("custom")
  private Map<String, Object> custom;

  @JsonProperty("egress")
  private EgressResponse egress;

  @JsonProperty("ingress")
  private CallIngressResponse ingress;

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
