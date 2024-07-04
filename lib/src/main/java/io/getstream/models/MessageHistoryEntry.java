package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.List;
import java.util.Map;
import lombok.*;
import org.jetbrains.annotations.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageHistoryEntry {

  @NotNull
  @JsonProperty("message_id")
  private String messageId;

  @NotNull
  @JsonProperty("message_updated_at")
  private Date messageUpdatedAt;

  @NotNull
  @JsonProperty("message_updated_by_id")
  private String messageUpdatedById;

  @NotNull
  @JsonProperty("text")
  private String text;

  @NotNull
  @JsonProperty("attachments")
  private List<Attachment> attachments;

  @NotNull
  @JsonProperty("Custom")
  private Map<String, Object> custom;
}
