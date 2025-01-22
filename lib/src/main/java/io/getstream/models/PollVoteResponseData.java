package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class PollVoteResponseData {

  @JsonProperty("created_at")
  private Date createdAt;

  @JsonProperty("id")
  private String id;

  @JsonProperty("option_id")
  private String optionID;

  @JsonProperty("poll_id")
  private String pollID;

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
  private String userID;

  @Nullable
  @JsonProperty("user")
  private UserResponse user;
}
