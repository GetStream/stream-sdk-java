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
public class Reaction {

  /** Date/time of creation */
  @NotNull
  @JsonProperty("created_at")
  private Date createdAt;

  /** ID of a message user reacted to */
  @NotNull
  @JsonProperty("message_id")
  private String messageId;

  /** Reaction score. If not specified reaction has score of 1 */
  @NotNull
  @JsonProperty("score")
  private Integer score;

  /** The type of reaction (e.g. 'like', 'laugh', 'wow') */
  @NotNull
  @JsonProperty("type")
  private String type;

  /** Date/time of the last update */
  @NotNull
  @JsonProperty("updated_at")
  private Date updatedAt;

  @NotNull
  @JsonProperty("custom")
  private Map<String, Object> custom;

  /** ID of a user who reacted to a message */
  @Nullable
  @JsonProperty("user_id")
  private String userId;

  /** Represents chat user */
  @Nullable
  @JsonProperty("user")
  private UserObject user;
}
