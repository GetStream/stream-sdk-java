package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class CampaignMessageTemplate {

  @JsonProperty("poll_id")
  private String pollID;

  @JsonProperty("text")
  private String text;

  @JsonProperty("attachments")
  private List<Attachment> attachments;

  @JsonProperty("custom")
  private Map<String, Object> custom;
}
