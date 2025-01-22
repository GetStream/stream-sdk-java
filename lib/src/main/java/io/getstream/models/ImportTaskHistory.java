package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class ImportTaskHistory {

  @JsonProperty("created_at")
  private Date createdAt;

  @JsonProperty("next_state")
  private String nextState;

  @JsonProperty("prev_state")
  private String prevState;
}
