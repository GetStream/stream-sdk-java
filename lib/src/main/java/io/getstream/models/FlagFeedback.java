package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.List;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class FlagFeedback {

  @JsonProperty("created_at")
  private Date createdAt;

  @JsonProperty("message_id")
  private String messageID;

  @JsonProperty("labels")
  private List<Label> labels;
}
