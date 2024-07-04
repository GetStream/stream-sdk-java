package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.getstream.models.framework.RateLimit;
import io.getstream.models.framework.StreamResponseWithRateLimit;
import java.util.Date;
import java.util.List;
import java.util.Map;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CallResponse implements StreamResponseWithRateLimit {
  private RateLimit rateLimit;

  @NotNull
  @JsonProperty("backstage")
  private Boolean backstage;

  /** The unique identifier for a call (<type>:<id>) */
  @NotNull
  @JsonProperty("cid")
  private String cid;

  /** Date/time of creation */
  @NotNull
  @JsonProperty("created_at")
  private Date createdAt;

  @NotNull
  @JsonProperty("current_session_id")
  private String currentSessionId;

  /** Call ID */
  @NotNull
  @JsonProperty("id")
  private String id;

  @NotNull
  @JsonProperty("recording")
  private Boolean recording;

  @NotNull
  @JsonProperty("transcribing")
  private Boolean transcribing;

  /** The type of call */
  @NotNull
  @JsonProperty("type")
  private String type;

  /** Date/time of the last update */
  @NotNull
  @JsonProperty("updated_at")
  private Date updatedAt;

  @NotNull
  @JsonProperty("blocked_user_ids")
  private List<String> blockedUserIds;

  @NotNull
  @JsonProperty("created_by")
  private UserResponse createdBy;

  /** Custom data for this object */
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

  /** Date/time when the call ended */
  @Nullable
  @JsonProperty("ended_at")
  private Date endedAt;

  @Nullable
  @JsonProperty("join_ahead_time_seconds")
  private Integer joinAheadTimeSeconds;

  /** Date/time when the call will start */
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
