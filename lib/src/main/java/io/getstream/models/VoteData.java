package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VoteData {

  @Nullable
  @JsonProperty("answer_text")
  private String answerText;

  @Nullable
  @JsonProperty("option_id")
  private String optionId;

  @Nullable
  @JsonProperty("Option")
  private PollOption option;
}
