package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class ChannelFrozenEvent {

  @JsonProperty("channel_id")
  private String channelID;

  @JsonProperty("channel_type")
  private String channelType;

  @JsonProperty("cid")
  private String cid;

  @JsonProperty("created_at")
  private Date createdAt;

  @JsonProperty("type")
  private String type;
}
