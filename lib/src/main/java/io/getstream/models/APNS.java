package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class APNS {

  @NotNull
  @JsonProperty("body")
  private String body;

  @NotNull
  @JsonProperty("title")
  private String title;
}
