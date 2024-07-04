package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PollVote {

  @NotNull
  @JsonProperty("created_at")
  private Date createdAt;

  @NotNull
  @JsonProperty("id")
  private String id;

  @NotNull
  @JsonProperty("option_id")
  private String optionId;

  @NotNull
  @JsonProperty("poll_id")
  private String pollId;

  @NotNull
  @JsonProperty("updated_at")
  private Date updatedAt;

  @Nullable
  @JsonProperty("answer_text")
  private String answerText;

  @Nullable
  @JsonProperty("is_answer")
  private Boolean isAnswer;

  @Nullable
  @JsonProperty("user_id")
  private String userId;

  /** Represents chat user */
  @Nullable
  @JsonProperty("user")
  private UserObject user;
}
