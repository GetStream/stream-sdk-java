package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LimitInfo {

  /** The maximum number of calls allowed for the time window */
  @NotNull
  @JsonProperty("limit")
  private Integer limit;

  /** The number of remaining calls in the current window */
  @NotNull
  @JsonProperty("remaining")
  private Integer remaining;

  /** The Unix timestamp of the next window */
  @NotNull
  @JsonProperty("reset")
  private Integer reset;
}
