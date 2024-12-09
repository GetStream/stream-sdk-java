package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class VoteData {

  @Nullable
  @JsonProperty("answer_text")
  private String answerText;

  @Nullable
  @JsonProperty("option_id")
  private String optionID;

  @Nullable
  @JsonProperty("Option")
  private PollOptionResponseData option;
}
