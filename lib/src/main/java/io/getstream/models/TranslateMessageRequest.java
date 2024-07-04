package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TranslateMessageRequest {

  /** Language to translate message to */
  @NotNull
  @JsonProperty("language")
  private String language;
}
