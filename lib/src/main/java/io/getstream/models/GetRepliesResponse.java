package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class GetRepliesResponse {

  @JsonProperty("duration")
  private String duration;

  @JsonProperty("messages")
  private List<MessageResponse> messages;
}
