package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class ChannelMutedEvent {

  @JsonProperty("created_at")
  private Date createdAt;

  @JsonProperty("type")
  private String type;
}
