package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.List;
import java.util.Map;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class MessageHistoryEntryResponse {

  @JsonProperty("is_deleted")
  private Boolean isDeleted;

  @JsonProperty("message_id")
  private String messageID;

  @JsonProperty("message_updated_at")
  private Date messageUpdatedAt;

  @JsonProperty("message_updated_by_id")
  private String messageUpdatedByID;

  @JsonProperty("text")
  private String text;

  @JsonProperty("attachments")
  private List<Attachment> attachments;

  @JsonProperty("Custom")
  private Map<String, Object> custom;
}
