package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class ChannelExport {

  @Nullable
  @JsonProperty("cid")
  private String cid;

  @Nullable
  @JsonProperty("id")
  private String id;

  @Nullable
  @JsonProperty("messages_since")
  private Date messagesSince;

  @Nullable
  @JsonProperty("messages_until")
  private Date messagesUntil;

  @Nullable
  @JsonProperty("type")
  private String type;
}
