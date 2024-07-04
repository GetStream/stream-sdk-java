package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import lombok.*;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChannelExport {

  @Nullable
  @JsonProperty("cid")
  private String cid;

  /** Channel ID */
  @Nullable
  @JsonProperty("id")
  private String id;

  /** Date to export messages since */
  @Nullable
  @JsonProperty("messages_since")
  private Date messagesSince;

  /** Date to export messages until */
  @Nullable
  @JsonProperty("messages_until")
  private Date messagesUntil;

  /** Channel type */
  @Nullable
  @JsonProperty("type")
  private String type;
}
