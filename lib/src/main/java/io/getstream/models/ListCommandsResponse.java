package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class ListCommandsResponse {

  @JsonProperty("duration")
  private String duration;

  @JsonProperty("commands")
  private List<Command> commands;
}
