package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.Map;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ThreadParticipant {

  @NotNull
  @JsonProperty("app_pk")
  private Integer appPk;

  @NotNull
  @JsonProperty("channel_cid")
  private String channelCid;

  /** Date/time of creation */
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

  /** Left Thread At is the time when the user left the thread */
  @Nullable
  @JsonProperty("left_thread_at")
  private Date leftThreadAt;

  /** Thead ID is unique string identifier of the thread */
  @Nullable
  @JsonProperty("thread_id")
  private String threadId;

  /** User ID is unique string identifier of the user */
  @Nullable
  @JsonProperty("user_id")
  private String userId;

  /** Represents chat user */
  @Nullable
  @JsonProperty("user")
  private UserObject user;
}
