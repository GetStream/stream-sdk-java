package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class ThreadParticipant {

  @NotNull
  @JsonProperty("app_pk")
  private Integer appPk;

  @NotNull
  @JsonProperty("channel_cid")
  private String channelCid;

  @NotNull
  @JsonProperty("created_at")
  private Date createdAt;

  @NotNull
  @JsonProperty("last_read_at")
  private Date lastReadAt;

  @NotNull
  @JsonProperty("custom")
  private Map<String, Object> custom;

  @Nullable
  @JsonProperty("last_thread_message_at")
  private Date lastThreadMessageAt;

  @Nullable
  @JsonProperty("left_thread_at")
  private Date leftThreadAt;

  @Nullable
  @JsonProperty("thread_id")
  private String threadID;

  @Nullable
  @JsonProperty("user_id")
  private String userID;

  @Nullable
  @JsonProperty("user")
  private UserResponse user;
}
