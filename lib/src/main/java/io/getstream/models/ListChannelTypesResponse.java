package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class ListChannelTypesResponse {

  @JsonProperty("duration")
  private String duration;

  @JsonProperty("channel_types")
  private Map<String, ChannelTypeConfig> channelTypes;
}
