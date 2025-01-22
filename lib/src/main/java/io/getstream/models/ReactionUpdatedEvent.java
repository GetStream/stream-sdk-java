package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class ReactionUpdatedEvent {

  @JsonProperty("channel_id")
  private String channelID;

  @JsonProperty("channel_type")
  private String channelType;

  @JsonProperty("cid")
  private String cid;

  @JsonProperty("created_at")
  private Date createdAt;

  @JsonProperty("message")
  private Message message;

  @JsonProperty("reaction")
  private Reaction reaction;

  @JsonProperty("type")
  private String type;

  @Nullable
  @JsonProperty("team")
  private String team;

  @Nullable
  @JsonProperty("user")
  private User user;
}
