package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.NotNull;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class MessageHistoryEntryResponse {

  @NotNull
  @JsonProperty("is_deleted")
  private Boolean isDeleted;

  @NotNull
  @JsonProperty("message_id")
  private String messageID;

  @NotNull
  @JsonProperty("message_updated_at")
  private Date messageUpdatedAt;

  @NotNull
  @JsonProperty("message_updated_by_id")
  private String messageUpdatedByID;

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
